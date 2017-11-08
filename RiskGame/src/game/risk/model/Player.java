package game.risk.model;

import game.risk.listener.ReinforcementClickListener;
import game.risk.util.Territory;
import game.risk.util.CurrentGameStaticsTableModel;
import game.risk.util.NeighbourListModel;
import game.risk.util.CurrentGameStatics;
import game.risk.gui.AttackGUIPanel;
import game.risk.gui.PlayerPanel;
import game.risk.listener.FortificationClickListener;
import game.risk.listener.MyListSelectionListener;
import game.risk.listener.OkClickListener;
import game.risk.listener.PlaceInfantryClickListener;
import game.risk.gui.RiskGame;
import game.risk.util.LoggerUtility;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;
import game.risk.util.CustomLogRecord;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Player extends Observable {

	int myIndex;
	Player player[];
	RiskMap mapDetails;
	public int infantriesAvailable;
	public int infantriesTotal;
	public CurrentGameStaticsTableModel currentGameStaticsTableModel;
	public List<CurrentGameStatics> currentGameStaticsList;
	public NeighbourListModel neighbourListModel;
	public List<String> neighbours;
	private PlayerPanel playerPanel;
	private String name;
	private int totalNoOfArmies;
	private int currentNoOfArmies;
	private Map<Territory, Integer> territorAndArmies;
	private boolean turn;
	private boolean isComputer;
	private Color territorAndArmiesColor;
	RiskGame riskGameNew;

	public Player(int myIndex, Player player[], RiskMap mapDetails) {
		this.mapDetails = mapDetails;
		this.myIndex = myIndex;
		this.player = player;
	}

	public Color getTerritorAndArmiesColor() {
		return territorAndArmiesColor;
	}

	public void setTerritorAndArmiesColor(Color territorAndArmiesColor) {
		this.territorAndArmiesColor = territorAndArmiesColor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalNoOfArmies() {
		return totalNoOfArmies;
	}

	public void setTotalNoOfArmies(int totalNoOfArmies) {
		this.totalNoOfArmies = totalNoOfArmies;
	}

	public int getCurrentNoOfArmies() {
		return currentNoOfArmies;
	}

	public void setCurrentNoOfArmies(int currentNoOfArmies) {
		this.currentNoOfArmies = currentNoOfArmies;
	}

	public Map<Territory, Integer> getTerritorAndArmies() {
		return territorAndArmies;
	}

	public void setTerritorAndArmies(Map<Territory, Integer> territorAndArmies) {
		this.territorAndArmies = territorAndArmies;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public boolean isComputer() {
		return isComputer;
	}

	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}

	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}

	public void setPlayerPanel(PlayerPanel playerPanel) {
		this.playerPanel = playerPanel;
	}

	public void reinforcement() {

		for (int i = 0; i < player.length; i++) {
			int n = player[i].currentGameStaticsList.size() / 3;
			if (n < 3) {
				n = 3;
			}
			// Check if all territories are in same continent
			boolean flag = true;
			String firstContinent = player[i].currentGameStaticsList.get(0).territory.getContinent();
			for (int j = 1; j < player[i].currentGameStaticsList.size(); j++) {
				String continent = player[i].currentGameStaticsList.get(j).territory.getContinent();
				if (!firstContinent.equals(continent)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				int p = Integer.parseInt(mapDetails.getContinents().get(firstContinent));
				n = n + p;
			}
			player[i].infantriesTotal += n;
			player[i].getPlayerPanel().lbTotalArmies.setText("Total Infantry : " + player[i].infantriesTotal);
			player[i].infantriesAvailable = n;
			player[i].getPlayerPanel().lbMessage.setText("Message : You have gotton " + n + " new infantries");
			player[i].getPlayerPanel().lbAvailableArmies
					.setText("Available Infantries : " + player[i].infantriesAvailable);

			player[i].getPlayerPanel().btReinforcement.setEnabled(false);
			player[i].getPlayerPanel().btFortification.setEnabled(false);
			player[i].getPlayerPanel().btOk.setEnabled(false);
		}
		player[0].setMessage("Player - " + (0 + 1) + " entered into Reinforcement Phase");
		player[0].notifyObservers();
		player[0].getPlayerPanel().btReinforcement.setEnabled(true);

		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
				"Player - " + (0 + 1) + "entered into Reinforcement Phase");
		LoggerUtility.consoleHandler.publish(logRecord);

	}

	public void attack(int i) {
		int ans = JOptionPane.showConfirmDialog(player[i].getPlayerPanel(),
				"Player : " + (i + 1) + "\nDo you want to do attack ?", "Attack Confirmition",
				JOptionPane.YES_NO_OPTION);
		if (ans == JOptionPane.YES_OPTION) {

			JDialog dialog = new JDialog();
			dialog.add(new AttackGUIPanel(dialog, player, i, player[i].currentGameStaticsTableModel,
					player[i].currentGameStaticsList, mapDetails));
			dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			dialog.setSize(1020, 600);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					Player.this.currentGameStaticsTableModel.fireTableDataChanged();

					String percentageString = "";
					int totalTerritories = mapDetails.getTerritories().size();
					for (int i = 0; i < player.length; i++) {
						double per = (player[i].currentGameStaticsList.size() * 100.0) / totalTerritories;
						DecimalFormat df = new DecimalFormat("##.##");
						per = Double.parseDouble(df.format(per));
						if (i < player.length - 1)
							percentageString += per + ",";
						else
							percentageString += per;
					}

					setMessage("Percentage " + percentageString);

					int ans = JOptionPane.showConfirmDialog(player[i].getPlayerPanel(),
							"Player - " + (i + 1) + "\nDo you want to do fortification ?", "Fortification Confirmition",
							JOptionPane.YES_NO_OPTION);

					if (ans == JOptionPane.YES_OPTION) {
						player[i].getPlayerPanel().btFortification.setEnabled(true);
						player[i].getPlayerPanel().btOk.setEnabled(true);

						player[i].setMessage("Player " + (i + 1) + " entered into Fortification Phase");
						player[i].notifyObservers();

						CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
								"Player " + (i + 1) + " entered into Fortification Phase");
						LoggerUtility.consoleHandler.publish(logRecord);

					} else {
						player[i].nextPlayerTurn(i);
					}
				}
			});
			dialog.setVisible(true);
		} else {
			int ans1 = JOptionPane.showConfirmDialog(player[i].getPlayerPanel(),
					"Player - " + (i + 1) + "\nDo you want to do fortification ?", "Fortification Confirmition",
					JOptionPane.YES_NO_OPTION);

			if (ans1 == JOptionPane.YES_OPTION) {
				player[i].getPlayerPanel().btFortification.setEnabled(true);
				player[i].getPlayerPanel().btOk.setEnabled(true);

				CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
						"Player " + (i + 1) + " entered into Fortification Phase");
				LoggerUtility.consoleHandler.publish(logRecord);

				player[i].setMessage("Player " + (i + 1) + " entered into Fortification Phase");
				player[i].notifyObservers();
			} else {
				player[i].nextPlayerTurn(i);
			}
		}
	}

	public void nextPlayerTurn(int i) {

		player[i].getPlayerPanel().lbMessage.setText("");
		i++;
		if (i == player.length) {
			reinforcement();
		} else {
			for (int j = 0; j < player.length; j++) {
				player[j].getPlayerPanel().btReinforcement.setEnabled(false);
				player[j].getPlayerPanel().btFortification.setEnabled(false);
				player[j].getPlayerPanel().btOk.setEnabled(false);
			}
			player[i].getPlayerPanel().btReinforcement.setEnabled(true);
			player[i].setMessage("Player - " + (i + 1) + " entered into Reinforcement Phase");
			player[i].notifyObservers();

			CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
					"Player - " + (i + 1) + " entered into Reinforcement Phase");
			LoggerUtility.consoleHandler.publish(logRecord);

		}

	}

	public void nextIndexToEnableButton(int i) {
		// if all players have 0 available infantries.. means startup phase done
		boolean flag = true;
		for (int j = 0; j < player.length; j++) {
			if (player[j].infantriesAvailable > 0) {
				flag = false;
				break;
			}
		}
		if (flag) {
			JOptionPane.showMessageDialog(playerPanel,
					"Startup Phase Done.\nIn next phase every player has option of \nreinforcement, attack and fortification.");
			reinforcement();
		} else {
			i++;
			if (i == player.length) {
				i = 0;
			}
			if (player[i].infantriesAvailable > 0) {
				player[i].getPlayerPanel().btPlaceInfantry.setEnabled(true);
			} else {
				nextIndexToEnableButton(i);
			}
		}

	}

	public void bindListeners() {
		playerPanel.btPlaceInfantry.addActionListener(new PlaceInfantryClickListener(myIndex, player));
		playerPanel.jtCountriesAndArmies.getSelectionModel()
				.addListSelectionListener(new MyListSelectionListener(myIndex, player));
		playerPanel.btFortification.addActionListener(new FortificationClickListener(myIndex, player));
		playerPanel.btOk.addActionListener(new OkClickListener(myIndex, player));
		playerPanel.btReinforcement.addActionListener(new ReinforcementClickListener(myIndex, player));
	}

	private String message;

	public void setMessage(String message) {
		this.message = message;
		setChanged();
		notifyObservers();
	}

	public String getMessage() {
		return message;
	}

}
