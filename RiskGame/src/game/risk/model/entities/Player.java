package game.risk.model.entities;

import game.risk.controller.FortificationClickListener;
import game.risk.controller.MyListSelectionListener;
import game.risk.controller.OkClickListener;
import game.risk.controller.PlaceInfantryClickListener;
import game.risk.controller.ReinforcementClickListener;
import game.risk.gui.AttackGUIPanel;
import game.risk.gui.PlayerPanel;
import game.risk.gui.ExchangeCardPanel;
import game.risk.gui.RiskGame;
import game.risk.model.MapReader;
import game.risk.model.entities.strategy.PlayerStrategy;
import game.risk.util.LoggerUtility;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.TimeUnit;
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
public class Player extends Observable implements Serializable {

	private static final long serialVersionUID = 1L;
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
	transient public RiskGame riskGame;
	private int nVal = 0;
	private PlayerStrategy strategy;
	private String phase;
	public static boolean endOfGame = false;

	/**
	 * A constructor to initialize myIndex, Player and MapDetails
	 * 
	 * @param riskGame
	 *            object of RiskGame
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
	 * Method to set the value into class attributes
	 * 
	 * @param strategy
	 *            the value of strategy
	 */
	public void setStrategy(PlayerStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * Method for fortification strategy
	 * 
	 * @param i
	 *            index number
	 * @param p
	 *            an object of player class
	 * @param army
	 *            number of armies
	 * @return Fortification Strategy
	 */
	public int fortificationStrategy(int i, Player p, int army) {
		return this.strategy.fortificationStrategy(i, p, army);
	}

	/**
	 * Method for place infantory strategy
	 * 
	 * @param i
	 *            index number
	 * @param player
	 *            an object of player class
	 * @param army
	 *            number of armies
	 * @return place Infantory Strategy
	 */
	public int placeInfantoryStrategy(int i, Player player, int army) {
		return this.strategy.placeInfantoryStrategy(i, player, army);
	}

	/**
	 * 
	 * Method for reinforcement strategy
	 * 
	 * @param i
	 *            index number
	 * @param player
	 *            an object of player class
	 * @param army
	 *            number of armies
	 * @return reinforcement strategy
	 */
	public int reinforcementStrategy(int i, Player player, int army) {
		return this.strategy.reinforcementStrategy(i, player, army);
	}

	/**
	 * Method for attack strategy
	 * 
	 * @param player
	 *            array of player class
	 * @param i
	 *            index number
	 * @param player2
	 *            an object of player class
	 * @param mapDetails
	 *            an object of riskMap
	 * @return An integer
	 */
	public int attackStrategy(Player[] player, int i, Player player2, RiskMap mapDetails) {
		return this.strategy.attackStrategy(player, i, player2, mapDetails);
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
	 * @return isComputer true when player is computer
	 */
	public boolean isComputer() {
		return isComputer;
	}

	/**
	 * method to set the value of Computer
	 * 
	 * @param isComputer
	 *            player is computer
	 */
	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}

	/**
	 * Method to get the player panel
	 * 
	 * @return playerPanel The panel
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
	 * Method to get Strategy
	 * 
	 * @return strategy type of strategy
	 */
	public PlayerStrategy getStrategy() {
		return strategy;
	}

	/**
	 * Method to get phase of player
	 * 
	 * @return phase a phase of player
	 */
	public String getPhase() {
		return phase;
	}

	/**
	 * Method to set phase of player
	 * 
	 * @param phase
	 *            phase of player
	 */
	public void setPhase(String phase) {
		this.phase = phase;
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
	public void reinforcementInitialization() {

		setMessage("Reinforcement Initialization");
		for (i = 0; i < player.length; i++) {
			int n = calculateReinformentArmiesInitially(i);

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
						setMessage(
								"RiskCardInfantries," + ((i-1 <0) ? i:i-1) + ",Got infantries from risk card " + armiesFromCard);
						notifyObservers();
					}
				});
				dialog.setVisible(true);
			}

			player[i].infantriesTotal += (n + m);
			player[i].getPlayerPanel().lbTotalArmies.setText("Total Infantry : " + player[i].infantriesTotal);
			player[i].infantriesAvailable = n + m;
			player[i].getPlayerPanel().lbMessage1.setText("Got infantries from continent " + (n - nVal));
			player[i].getPlayerPanel().lbMessage2.setText("Got infantries from terrotries " + nVal);
			player[i].getPlayerPanel().lbMessage3.setText("Got infantries from risk card " + m);
			player[i].getPlayerPanel().lbMessage4.setText("Got total infantries " + (n + m));

			player[i].getPlayerPanel().lbAvailableArmies
					.setText("Available Infantries : " + player[i].infantriesAvailable);

			player[i].getPlayerPanel().btReinforcement.setEnabled(false);
			player[i].getPlayerPanel().btFortification.setEnabled(false);
			player[i].getPlayerPanel().btOk.setEnabled(false);
			n = 0;
			m = 0;
		}
		player[0].getPlayerPanel().btReinforcement.setEnabled(true);
		player[0].setMessage("Player - " + player[0].getName() + " entered into Reinforcement Phase");
		player[0].notifyObservers();

		if(player[0].currentGameStaticsList.size() == 0){
			player[0].getPlayerPanel().btReinforcement.setEnabled(false);
			if(!Player.endOfGame){
				player[0].nextPlayerTurn(100);
			}
		}
	}

	/**
	 * Method to calculate the reinforcement armies of the player
	 * 
	 * @param playerIndex
	 *            the index of the player
	 * @return reinformentArmies the number of armies
	 */
	public int calculateReinformentArmiesInitially(int playerIndex) {
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
	
	/**
	 * Method for adding Cards
	 * 
	 * @param panel
	 *            card panel
	 * @param alcheckbox
	 *            selected cards
	 * @param i
	 *            player no.
	 */
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
	 * @param i player no.
	 * @return true on existing attack strategy
	 */
	public boolean attackInitialization(int i) {
		player[0].setPhase(GamePhaseEnum.ATTACK.name());
		player[0].getPlayerPanel().btReinforcement.setEnabled(false);
		player[0].attackStrategy(player, i, player[i], mapDetails);
		return true;
	}

	/**
	 * Method to check and calculate valid fortification of armies
	 * 
	 * @param destinationTerritory
	 *            destination territory on which army has to be moved
	 * @param index
	 *            source territory index
	 * @param playerIndex
	 *            player index
	 */
	public void fortification(String destinationTerritory, int index, int playerIndex) {
		int i = playerIndex;
		boolean isDestinationMyOwnCountry = false;
		for (int j = 0; j < player[i].currentGameStaticsList.size(); j++) {
			if (player[i].currentGameStaticsList.get(j).territory.getName().equals(destinationTerritory)) {
				isDestinationMyOwnCountry = true;
				break;
			}
		}
		if (isDestinationMyOwnCountry) {
			if (player[i].currentGameStaticsList.get(index).infantries > 1) {

				player[i].currentGameStaticsList.get(index).infantries--;

				for (int j = 0; j < player[i].currentGameStaticsList.size(); j++) {
					if (player[i].currentGameStaticsList.get(j).territory.getName().equals(destinationTerritory)) {
						player[i].currentGameStaticsList.get(j).infantries++;
						player[i].setMessage(
								"Fortification Phase\r\nPlayer - " + (i + 1) + " has transfered 1 infantry from "
										+ player[i].currentGameStaticsList.get(index).territory.getName() + " to "
										+ destinationTerritory);
						player[i].notifyObservers();

						CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
								"Player - " + (i + 1) + " has transfered 1 infantry from "
										+ player[i].currentGameStaticsList.get(index).territory.getName() + " to "
										+ destinationTerritory);
						LoggerUtility.consoleHandler.publish(logRecord);
						break;
					}
				}
			}
		}
	}

	public void updateTables() {
		for (int i = 0; i < player.length; i++) {
			try {
				TimeUnit.MILLISECONDS.sleep(10);
				player[i].currentGameStaticsTableModel.fireTableDataChanged();
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * Method to update percentage
	 * 
	 * @param p
	 *            an object of player class
	 * @param phase
	 *            a string attribute
	 */
	private void updatePercentage(Player p, String phase) {
		String percentageString = "";
		int totalTerritories = mapDetails.getTerritories().size();
		for (int i2 = 0; i2 < player.length; i2++) {
			double per = (player[i2].currentGameStaticsList.size() * 100.0) / totalTerritories;
			DecimalFormat df = new DecimalFormat("##.##");
			per = Double.parseDouble(df.format(per));
			if (i2 < player.length - 1)
				percentageString += per + ",";
			else
				percentageString += per;
		}

		p.setMessage("Percentage " + percentageString);
		p.setMessage("Player - " + p.getName() + " entered into " + phase + " Phase");
	}

	/**
	 * Method to get the next player turn
	 * 
	 * @param i
	 *            player no.
	 */
	public void nextPlayerTurn(int i) {
		int total = 0;
		for (int j = 0; j < player.length; j++) {
			if (player[j].currentGameStaticsList.size() > 0) {
				total +=0;
			}
		}
		
		boolean automatic = false;
		
		if(i == 100){
			automatic = true;
		}
		for (int j = 1; j < player.length; j++) {
			player[j].getPlayerPanel().btReinforcement.setEnabled(false);
			player[j].getPlayerPanel().btFortification.setEnabled(false);
			player[j].getPlayerPanel().btOk.setEnabled(false);
		}

		player[0].getPlayerPanel().btReinforcement.setEnabled(true);

		if (!automatic) {
			for (int k = 1; k < player.length; k++) {

				try {
					TimeUnit.MILLISECONDS.sleep(10);
					updatePercentage(player[k], "REINFORCEMENT");
					player[k].reinforcementStrategy(k, player[k], player[k].infantriesAvailable);
					updateTables();

					updatePercentage(player[k], "ATTACK");
					player[k].attackStrategy(player, k, player[k], mapDetails);
					updateTables();

					TimeUnit.MILLISECONDS.sleep(10);
					updatePercentage(player[k], "FORTIFICATION");
					player[k].fortificationStrategy(k, player[k], player[k].infantriesAvailable);
					updateTables();

					if (player[0].currentGameStaticsList.size() == 0) {
						updateTables();
						automatic = true;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			reinforcementInitialization();
		}

		if (automatic) {
			player[0].setMessage("Player - AUTOMATION entered into Phase");
			boolean check = true;
			while (check) {
				for (int k = 1; k < player.length; k++) {

					updatePercentage(player[k], "REINFORCEMENT");
					//player[k].reinforcementStrategy(k, player[k], player[k].infantriesAvailable);
					updateTables();

					updatePercentage(player[k], "ATTACK");
					player[k].attackStrategy(player, k, player[k], mapDetails);
					updateTables();

					updatePercentage(player[k], "FORTIFICATION");
					//player[k].fortificationStrategy(k, player[k], player[k].infantriesAvailable);
					updateTables();

				}

				for (int j = 1; j < player.length; j++) {
					if (player[j].infantriesAvailable > 0) {
						check = false;
						break;
					}
					if(total == player[j].currentGameStaticsList.size()){
						check = false;
						break;
					}
				}
			}
		}
	}

	/**
	 * Method to check the next index to enable the start phase button
	 * 
	 * @param i player no.
	 * @return flag to enable buttons
	 */
	public boolean nextIndexToEnableButton(int i) {
		// if all players have 0 available infantries.. means startup phase done
		boolean flag = true;
		for (int j = 0; j < player.length; j++) {
			if (player[j].infantriesAvailable > 0) {
				player[0].getPlayerPanel().btPlaceInfantry.setEnabled(true);
				i = 0;
				flag = false;
				break;
			}
		}
		if (flag) {
			JOptionPane.showMessageDialog(playerPanel,
					"Startup Phase Done.\nIn next phase every player has option of \nreinforcement, attack and fortification.");
			setMessage(
					"Startup Phase Done.\nIn next phase every player has option of \nreinforcement, attack and fortification.");
			player[i].setPhase(GamePhaseEnum.REINFORCEMENT.name());
			reinforcementInitialization();
		}
		return flag;
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
