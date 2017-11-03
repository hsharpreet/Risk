package game.risk.model;

import game.risk.gui.AttackGUIPanel;
import game.risk.gui.PlayerPanel;
import game.risk.gui.RiskGame;
import game.risk.util.MapDetails;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Player {

	int myIndex;
	Player player[];
	MapDetails mapDetails;

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
	private Map<Territory, Integer> territorAndArmies;// = new HashMap<String,
														// String>();
	private boolean turn;
	private boolean isComputer;
	private Color territorAndArmiesColor;

	public Player(int myIndex, Player player[], MapDetails mapDetails) {
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

	void nextIndexToEnableButton(int i) {
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
					"Startup Phase Done.\nIn next phase every player has option of reinforcement, attack and fortification.");
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

	public void reinforcement() {
		for (int i = 0; i < player.length; i++) {
			int n = player[i].currentGameStaticsList.size() / 3;
			if (n < 3) {
				n = 3;
			}
			// Check if all terrotries are in same continent
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
		player[0].getPlayerPanel().btReinforcement.setEnabled(true);
	}

	public class PlaceInfantryClickListener implements ActionListener {

		int i;

		public PlaceInfantryClickListener(int i) {
			this.i = i;
		}

		public void actionPerformed(ActionEvent e) {
			if (player[i].infantriesAvailable > 0) {
				int index = player[i].getPlayerPanel().jtCountriesAndArmies.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "Select terriotary first");
				} else {
					player[i].currentGameStaticsList.get(index).infantries++;
					player[i].infantriesAvailable--;
					player[i].getPlayerPanel().lbAvailableArmies
							.setText("Available Infantries : " + player[i].infantriesAvailable);
					player[i].currentGameStaticsTableModel.fireTableDataChanged();
					player[i].getPlayerPanel().btPlaceInfantry.setEnabled(false);
					nextIndexToEnableButton(i);

				}
			} else {
				JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "No army available");

			}
		}
	}

	void nextPlayerTurn(int i) {
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
		}

	}

	public class ReinforcementClickListener implements ActionListener {

		int i;

		public ReinforcementClickListener(int i) {
			this.i = i;
		}

		public void actionPerformed(ActionEvent e) {
			if (player[i].infantriesAvailable > 0) {
				int index = player[i].getPlayerPanel().jtCountriesAndArmies.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "Select terriotary first");
				} else {
					player[i].currentGameStaticsList.get(index).infantries++;
					player[i].infantriesAvailable--;
					player[i].getPlayerPanel().lbAvailableArmies
							.setText("Available Infantries : " + player[i].infantriesAvailable);
					player[i].currentGameStaticsTableModel.fireTableDataChanged();

					if (player[i].infantriesAvailable == 0) {
						player[i].getPlayerPanel().btReinforcement.setEnabled(false);
						int ans = JOptionPane.showConfirmDialog(player[i].getPlayerPanel(),
								"Player : " + (i + 1) + "\nDo you want to do attack ?", "Attack Confirmition",
								JOptionPane.YES_NO_OPTION);
						if (ans == JOptionPane.YES_OPTION) {
							JDialog dialog = new JDialog();
							dialog.add(new AttackGUIPanel(player, i, player[i].currentGameStaticsTableModel, player[i].currentGameStaticsList, mapDetails));
							dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
							dialog.setSize(1020, 600);
							dialog.setVisible(true);
						} else {

						}
					}

				}
			} else {
				JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "No army available");

			}
		}
	}

	public class FortificationClickListener implements ActionListener {

		int i;

		public FortificationClickListener(int i) {
			this.i = i;
		}

		public void actionPerformed(ActionEvent e) {
			int tableIndex = player[i].getPlayerPanel().jtCountriesAndArmies.getSelectedRow();
			int listIndex = player[i].getPlayerPanel().lsNeighbour.getSelectedIndex();
			if (tableIndex == -1) {
				JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "Select source territory first");
			} else if (listIndex == -1) {
				JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "Select destination territory first");
			} else {
				String destinationTerritory = player[i].getPlayerPanel().lsNeighbour.getSelectedValue();
				boolean isDestinationMyOwnCountry = false;
				for (int j = 0; j < player[i].currentGameStaticsList.size(); j++) {
					if (player[i].currentGameStaticsList.get(j).territory.getName().equals(destinationTerritory)) {
						isDestinationMyOwnCountry = true;
						break;
					}
				}
				if (isDestinationMyOwnCountry) {
					if (player[i].currentGameStaticsList.get(tableIndex).infantries > 1) {

						player[i].currentGameStaticsList.get(tableIndex).infantries--;

						for (int j = 0; j < player[i].currentGameStaticsList.size(); j++) {
							if (player[i].currentGameStaticsList.get(j).territory.getName().equals(destinationTerritory)) {
								player[i].currentGameStaticsList.get(j).infantries++;
								player[i].currentGameStaticsTableModel.fireTableDataChanged();
								break;
							}
						}
					} else {
						JOptionPane.showMessageDialog(player[i].getPlayerPanel(),
								"Source territory must have more that 1 infantries for fortification");
					}
				} else {
					JOptionPane.showMessageDialog(player[i].getPlayerPanel(),
							"Destination territory must be your territory");
				}
			}
		}
	}

	public class OkClickListener implements ActionListener {

		int i;

		public OkClickListener(int i) {
			this.i = i;
		}

		public void actionPerformed(ActionEvent e) {

			nextPlayerTurn(i);
		}
	}

}
