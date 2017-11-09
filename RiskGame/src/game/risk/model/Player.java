package game.risk.model;

import game.risk.listener.ReinforcementClickListener;
import game.risk.util.Territory;
import game.risk.util.CurrentGameStaticsTableModel;
import game.risk.util.NeighbourListModel;
import game.risk.util.CurrentGameStatics;
import game.risk.gui.AttackGUIPanel;
import game.risk.gui.PlayerPanel;
import game.risk.gui.ExchangeCardPanel;
import game.risk.listener.FortificationClickListener;
import game.risk.listener.MyListSelectionListener;
import game.risk.listener.OkClickListener;
import game.risk.listener.PlaceInfantryClickListener;
import game.risk.gui.RiskGame;
import game.risk.util.LoggerUtility;
import game.risk.util.MapReader;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;
import game.risk.util.CustomLogRecord;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * A Class which stores details of the player
 * 
 * @author Team
 *
 */
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
	RiskGame riskGame;
	private int nVal = 0;
	
	/**
	 * A constructor to initialize myIndex, Player and MapDetails
	 * 
	 * @param myIndex
	 *            an integer value
	 * @param player
	 *            an array of player class
	 * @param mapDetails
	 *            an object of RiskMap class
	 */
	public Player(RiskGame riskGame, int myIndex, Player player[], RiskMap mapDetails) {
		this.riskGame = riskGame;
		this.mapDetails = mapDetails;
		this.myIndex = myIndex;
		this.player = player;
	}

	/**
	 * method to get the color of territories of armies and territories
	 * 
	 * @return territorAndArmiesColor the color
	 */
	public Color getTerritorAndArmiesColor() {
		return territorAndArmiesColor;
	}

	/**
	 * A method to set the color of territories and armies in the class attributes
	 * 
	 * @param territorAndArmiesColor
	 *            the class attribute
	 */
	public void setTerritorAndArmiesColor(Color territorAndArmiesColor) {
		this.territorAndArmiesColor = territorAndArmiesColor;
	}

	/**
	 * A method to get the name of the player
	 * 
	 * @return name the name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the name of the player in the class attribute
	 * 
	 * @param name
	 *            the name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get the total number of armies of the player
	 * 
	 * @return totalNoOfArmies the total number of the armies
	 */
	public int getTotalNoOfArmies() {
		return totalNoOfArmies;
	}

	/**
	 * Method to set the total number of armies in the class attribute
	 * 
	 * @param totalNoOfArmies
	 *            the number of armies
	 */
	public void setTotalNoOfArmies(int totalNoOfArmies) {
		this.totalNoOfArmies = totalNoOfArmies;
	}

	/**
	 * Method to get the current number of armies
	 * 
	 * @return currentNoArmies the number of armies
	 */
	public int getCurrentNoOfArmies() {
		return currentNoOfArmies;
	}

	/**
	 * Method to set the current number of armies in the class attributes
	 * 
	 * @param currentNoOfArmies
	 *            the number of armies
	 */
	public void setCurrentNoOfArmies(int currentNoOfArmies) {
		this.currentNoOfArmies = currentNoOfArmies;
	}

	/**
	 * Method to get the territory and armies in a hashMap
	 * 
	 * @return territorAndArmies a hashMap
	 */
	public Map<Territory, Integer> getTerritorAndArmies() {
		return territorAndArmies;
	}

	/**
	 * Method to set the territory and armies inn the class attributes
	 * 
	 * @param territorAndArmies
	 *            a hashMap containing the territories and Armies
	 */
	public void setTerritorAndArmies(Map<Territory, Integer> territorAndArmies) {
		this.territorAndArmies = territorAndArmies;
	}

	/**
	 * Method to check the turn of the player
	 * 
	 * @return turn a boolean variable
	 */
	public boolean isTurn() {
		return turn;
	}

	/**
	 * Method to set the turn of the player
	 * 
	 * @param turn
	 *            a boolean variable
	 */
	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	/**
	 * method to check if the player is a computer
	 * 
	 * @return isComputer a boolean variable
	 */
	public boolean isComputer() {
		return isComputer;
	}

	/**
	 * method to set the value of Computer
	 * 
	 * @param isComputer
	 *            a boolean variable
	 */
	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}

	/**
	 * Method to get the player panel
	 * 
	 * @return PlayerPanel The panel
	 */
	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}

	/**
	 * Method to set the playerPanel
	 * 
	 * @param playerPanel
	 *            the panel
	 */
	public void setPlayerPanel(PlayerPanel playerPanel) {
		this.playerPanel = playerPanel;
	}

	/**
	 * A method to get the continents which are occupied by the player
	 * 
	 * @param playerIndex
	 *            an integer variable
	 * @return occupiedContinentByPlayer an arrayList of the continents
	 */
	public List<String> getOccupiedContinentsByPlayer(int playerIndex) {
		List<String> occupiedContinentByPlayer = new ArrayList<>();
		int j = playerIndex;
		Map<String, List<String>> playerContinents = new HashMap<String, List<String>>();
		for (int k = 0; k < player[j].currentGameStaticsList.size(); k++) {
			String continentName = player[j].currentGameStaticsList.get(k).territory.getContinent();
			String territoryName = player[j].currentGameStaticsList.get(k).territory.getName();
			if (playerContinents.get(continentName) == null) {
				playerContinents.put(continentName, new ArrayList<String>());
				playerContinents.get(continentName).add(territoryName);
			} else {
				playerContinents.get(continentName).add(territoryName);
			}
		}
		MapReader reader = new MapReader();
		Map<String, List<String>> mapContinents = reader.getcontinentsWithCountries(mapDetails);
		for (String playerContinent : playerContinents.keySet()) {
			List<String> allTerritoriesOfContinent = mapContinents.get(playerContinent);
			allTerritoriesOfContinent.removeAll(playerContinents.get(playerContinent));
			if (allTerritoriesOfContinent.size() == 0) {
				occupiedContinentByPlayer.add(playerContinent);
			}
		}
		return occupiedContinentByPlayer;
	}

	
	int m;

	int i;
	/**
	 * A method to perform the reinforcement by the player
	 */
	public void reinforcement() {

		for (i = 0; i < player.length; i++) {
			int n = calculateReinformentArmies(i);

			// Infantreis on the basis of risk card
			boolean havingRiskCards = false;
			for (int ii = 0; ii < riskGame.alCards.size(); ii++) {
				if (riskGame.alCards.get(ii).getPlayer() == i) {
					havingRiskCards = true;
					break;
				}
			}

			if (havingRiskCards) {
				JDialog dialog = new JDialog();
				ExchangeCardPanel panel = new ExchangeCardPanel();

				ArrayList<JCheckBox> alcheckbox = new ArrayList<>();

				addCards(panel, alcheckbox, i);

				panel.btCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				panel.btTrade.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (alcheckbox.size() < 3) {
							JOptionPane.showMessageDialog(panel, "You have insufficient cards");
						} else {
							int count = 0;
							for (int i = 0; i < alcheckbox.size(); i++) {
								if (alcheckbox.get(i).isSelected()) {
									count++;
								}
							}
							if (count == 3) {
								String cardDesigns[] = new String[3];
								int index = 0;
								for (int i = 0; i < alcheckbox.size(); i++) {
									if (alcheckbox.get(i).isSelected()) {
										cardDesigns[index] = alcheckbox.get(i).getLabel().split("-")[1].trim();
										index++;
									}
								}
								if ((cardDesigns[0].equals(cardDesigns[1]))
										&& (cardDesigns[1].equals(cardDesigns[2]))) {
									m += player[myIndex].riskGame.cardTurnIndex * 5;
									player[myIndex].riskGame.cardTurnIndex++;
									for (int k = 0; k < player[myIndex].riskGame.alCards.size(); k++) {
										for (int l = 0; l < cardDesigns.length; l++) {
											if (player[myIndex].riskGame.alCards.get(k).getTerritory().getName()
													.endsWith(cardDesigns[l])) {
												player[myIndex].riskGame.alCards.get(k).setPlayer(-1);
												break;
											}
										}
									}
									alcheckbox.clear();
									addCards(panel, alcheckbox, i);

								} else if (!(cardDesigns[0].equals(cardDesigns[1]))
										&& !(cardDesigns[1].equals(cardDesigns[2]))) {
									m += player[myIndex].riskGame.cardTurnIndex * 5;
									player[myIndex].riskGame.cardTurnIndex++;

									for (int k = 0; k < player[myIndex].riskGame.alCards.size(); k++) {
										for (int l = 0; l < cardDesigns.length; l++) {
											if (player[myIndex].riskGame.alCards.get(k).getTerritory().getName()
													.endsWith(cardDesigns[l])) {
												player[myIndex].riskGame.alCards.get(k).setPlayer(-1);

												break;
											}
										}
									}
									alcheckbox.clear();
									addCards(panel, alcheckbox, i);

								}
							} else {
								JOptionPane.showMessageDialog(panel, "Only 3 cards  can be selected");
							}
						}

					}
				});
				int armiesFromCard = m;
				dialog.add(panel);
				dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				dialog.setSize(350, 350);
				dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				dialog.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						setMessage("RiskCardInfantries, Player" + i + ",Got infantries from risk card " + armiesFromCard);
						notifyObservers();
					}
				});
				dialog.setVisible(true);
			}

			player[i].infantriesTotal += (n + m);
			player[i].getPlayerPanel().lbTotalArmies.setText("Total Infantry : " + player[i].infantriesTotal);
			player[i].infantriesAvailable = n + m;
			player[i].getPlayerPanel().lbMessage1.setText("Got infantries from continent " + (n-nVal));
			player[i].getPlayerPanel().lbMessage2.setText("Got infantries from terrotries " + nVal);
			player[i].getPlayerPanel().lbMessage3.setText("Got infantries from risk card " + m);
			player[i].getPlayerPanel().lbMessage4.setText("Got total infantries " + (n + m));
			//player[i].getPlayerPanel().lbMessage1.setText("Message : You have gotton " + n + " new infantries");
			player[i].getPlayerPanel().lbAvailableArmies
					.setText("Available Infantries : " + player[i].infantriesAvailable);

			player[i].getPlayerPanel().btReinforcement.setEnabled(false);
			player[i].getPlayerPanel().btFortification.setEnabled(false);
			player[i].getPlayerPanel().btOk.setEnabled(false);
			n = 0;
			m = 0;
		}
		player[0].setMessage("Player - " + (0 + 1) + " entered into Reinforcement Phase");
		player[0].notifyObservers();
		player[0].getPlayerPanel().btReinforcement.setEnabled(true);

		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
				"Player - " + (0 + 1) + "entered into Reinforcement Phase");
		LoggerUtility.consoleHandler.publish(logRecord);
		i = 0;
	}

	/**
	 * Method to calculate the reinforcement armies of the player
	 * 
	 * @param playerIndex
	 *            the index of the player
	 * @return reinformentArmies the number of armies
	 */
	public int calculateReinformentArmies(int playerIndex) {
		int reinformentArmies = player[playerIndex].currentGameStaticsList.size() / 3;
		if (reinformentArmies < 3) {
			reinformentArmies = 3;
		}
		nVal = reinformentArmies;
		List<String> occupiedContinents = getOccupiedContinentsByPlayer(playerIndex);
		for (String oc : occupiedContinents) {
			int p = Integer.parseInt(mapDetails.getContinents().get(oc));
			reinformentArmies = reinformentArmies + p;
		}
		return reinformentArmies;
	}
	
	public void addCards(ExchangeCardPanel panel, ArrayList<JCheckBox> alcheckbox, int i) {
		panel.jpAvailableCards.removeAll();
		panel.jpAvailableCards.repaint();

		for (int ii = 0; ii < riskGame.alCards.size(); ii++) {
			if (riskGame.alCards.get(ii).getPlayer() == i) {
				JCheckBox cb = new JCheckBox(riskGame.alCards.get(ii).getTerritory().getName() + " - "
						+ riskGame.alCards.get(ii).getCardDesign());
				panel.jpAvailableCards.add(cb);
				alcheckbox.add(cb);
			}
		}
	}

	/**
	 * a method for the attack phase
	 * 
	 * @param i
	 *            an integer value
	 */
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

	/**
	 * Method to get the next player turn
	 * 
	 * @param i
	 *            an integer value
	 */
	public void nextPlayerTurn(int i) {

		player[i].getPlayerPanel().lbMessage1.setText("");
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

	/**
	 * Method to check the next index to enable the start phase button
	 * 
	 * @param i
	 *            an integer value
	 */
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
			setMessage(
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

	/**
	 * a listener for PlaceInfantry button, fortification button, ok button and
	 * reinforcement button
	 */
	public void bindListeners() {
		playerPanel.btPlaceInfantry.addActionListener(new PlaceInfantryClickListener(myIndex, player));
		playerPanel.jtCountriesAndArmies.getSelectionModel()
				.addListSelectionListener(new MyListSelectionListener(myIndex, player));
		playerPanel.btFortification.addActionListener(new FortificationClickListener(myIndex, player));
		playerPanel.btOk.addActionListener(new OkClickListener(myIndex, player));
		playerPanel.btReinforcement.addActionListener(new ReinforcementClickListener(myIndex, player));
	}

	private String message;

	/**
	 * Method to set the message in the class attribute
	 * 
	 * @param message
	 *            a String variable
	 */
	public void setMessage(String message) {
		this.message = message;
		setChanged();
		notifyObservers();
	}

	/**
	 * Method to get the message
	 * 
	 * @return a String variable
	 */
	public String getMessage() {
		return message;
	}

}
