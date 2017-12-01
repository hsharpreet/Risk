package game.risk.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

import game.risk.gui.PlayerPanel;
import game.risk.gui.RiskGame;
import game.risk.model.entities.Card;
import game.risk.model.entities.CurrentGameStatics;
import game.risk.model.entities.CurrentGameStaticsTableModel;
import game.risk.model.entities.NeighbourListModel;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.model.entities.Territory;
import game.risk.model.entities.TournamentResult;
import game.risk.model.entities.strategy.AggressivePlayerStrategy;
import game.risk.model.entities.strategy.BenevolentPlayerStrategy;
import game.risk.model.entities.strategy.CheaterPlayerStrategy;
import game.risk.model.entities.strategy.HumanStrategy;
import game.risk.model.entities.strategy.RandomPlayerStrategy;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

/**
 * Class to create tournament model
 * 
 * @author Team
 *
 */
public class TournamentModel implements Observer

{
	ArrayList<String> playersTypes = new ArrayList<>();
	ArrayList<String> mapNames = new ArrayList<>();
	int gameCount;
	int playerTurns;
	int totalArmies[] = { 40, 35, 30, 25, 20 };
	List<TournamentResult> tournamentResult;

	/**
	 * A constructor
	 * 
	 * @param players
	 *            a string array of name of the players
	 * @param maps
	 *            a string array
	 * @param game_count
	 *            an integer to calculate the game count
	 * @param turns
	 *            an integer
	 */
	public TournamentModel(String[] players, String[] maps, int game_count, int turns) {
		getRealPlayers(players);
		getRealMaps(maps);
		gameCount = game_count;
		playerTurns = turns;
		tournamentResult = new ArrayList<>();

	}

	/**
	 * Method to get the players playing the game in the current time
	 * 
	 * @param p
	 *            a string array
	 */
	private void getRealPlayers(String[] p) {

		for (int i = 0; i < p.length; i++) {
			if (!(p[i].equals("Select"))) {
				// //System.out.println("hiii");
				this.playersTypes.add(p[i]);

			}
		}
	}

	/**
	 * Method to get the real map being used in the game in the current time
	 * 
	 * @param m
	 *            a string array
	 */
	private void getRealMaps(String[] m) {

		for (int i = 0; i < m.length; i++) {
			if (m[i] != null && (m[i].endsWith(".map"))) {
				this.mapNames.add(m[i]);

			}

		}

	}

	/**
	 * Method to get the tournament result
	 * 
	 * @return an array list
	 */
	public List<TournamentResult> getTournamentResult() {
		return tournamentResult;
	}

	/**
	 * Method to start the tournament
	 */
	public void startTournament()

	{
		Player player[];
		RiskMap mapDetails;

		for (String mapName : mapNames) {
			mapDetails = MapReader.readMapFile(mapName);
			CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
					"-------------Map" + mapName + "--------------");
			LoggerUtility.consoleHandler.publish(logRecord);

			for (int gameIndex = 0; gameIndex < gameCount; gameIndex++) {

				logRecord = new CustomLogRecord(Level.INFO, "----------Game+" + (gameIndex + 1) + "--------------");
				LoggerUtility.consoleHandler.publish(logRecord);

				TournamentResult tResult = new TournamentResult();
				tResult.setMapName(mapName);
				tResult.setGameIndex(gameIndex + 1);
				int currentTurn = playerTurns;
				player = new Player[playersTypes.size()];

				for (int playerIndex = 0; playerIndex < playersTypes.size(); playerIndex++) {

					player[playerIndex] = new Player(null, playerIndex, player, mapDetails);
					player[playerIndex].setComputer(true);
					player[playerIndex].setName(playersTypes.get(playerIndex) + "" + playerIndex);
					player[playerIndex].addObserver(TournamentModel.this);

					if (playersTypes.get(playerIndex).equalsIgnoreCase("Aggressive")) {
						// logRecord = new CustomLogRecord(Level.INFO, "Strategy: Aggressive");
						// LoggerUtility.consoleHandler.publish(logRecord);
						player[playerIndex].setStrategy(new AggressivePlayerStrategy());

					} else if (playersTypes.get(playerIndex).equalsIgnoreCase("Benevolent")) {
						// logRecord = new CustomLogRecord(Level.INFO, "Strategy: Benevolent Player
						// Strategy");
						// LoggerUtility.consoleHandler.publish(logRecord);
						player[playerIndex].setStrategy(new BenevolentPlayerStrategy());

					} else if (playersTypes.get(playerIndex).equalsIgnoreCase("Random")) {
						// logRecord = new CustomLogRecord(Level.INFO, "Strategy: Random Player
						// Strategy");
						// LoggerUtility.consoleHandler.publish(logRecord);
						player[playerIndex].setStrategy(new RandomPlayerStrategy());

					} else {
						// logRecord = new CustomLogRecord(Level.INFO, "Strategy: Cheater Player
						// Strategy");
						// LoggerUtility.consoleHandler.publish(logRecord);
						player[playerIndex].setStrategy(new CheaterPlayerStrategy());

					}

					// player[playerIndex] = new Player(RiskGame.this, i, player, mapDetails);
					player[playerIndex].currentGameStaticsList = new ArrayList<>();
					player[playerIndex].currentGameStaticsTableModel = new CurrentGameStaticsTableModel(
							player[playerIndex].currentGameStaticsList);
					player[playerIndex].infantriesTotal = totalArmies[playersTypes.size() - 2];

					player[playerIndex].setPlayerPanel(new PlayerPanel());

				}
				// Placing random territories to each player
				HashMap<String, Territory> territories = mapDetails.getTerritories();
				ArrayList<String> keyList = new ArrayList<>(territories.keySet());
				Collections.shuffle(keyList);

				int p = 0;
				while (true) {
					try {
						for (int i = 0; i < playersTypes.size(); i++) {
							Territory t = territories.get(keyList.get(p));
							CurrentGameStatics cgs = new CurrentGameStatics(1, t, i);
							player[i].currentGameStaticsList.add(cgs);
							p++;

						}
					} catch (Exception ex) {
						break;
					}
				}
				// placing infantries to all players
				for (int i = 0; i < playersTypes.size(); i++) {
					player[i].infantriesAvailable = (totalArmies[playersTypes.size() - 2])
							- (player[i].currentGameStaticsList.size());
					player[i].placeInfantoryStrategy(i, player[i], player[i].infantriesAvailable);
					// System.out.println("player Name ---" + player[i].getName());
					for (CurrentGameStatics cgs : player[i].currentGameStaticsList) {
						// System.out.println("player territory ---" + cgs.territory.getName() + " " +
						// cgs.infantries);
					}

				}

				logRecord = new CustomLogRecord(Level.INFO, "----------END OF START UP PHASE--------------");
				LoggerUtility.consoleHandler.publish(logRecord);

				// Start of the turn and start of reinforcement
				while (currentTurn > 0) {

					logRecord = new CustomLogRecord(Level.INFO, "------------Turn" + currentTurn + "-----------");
					LoggerUtility.consoleHandler.publish(logRecord);

					boolean winner = false;
					for (int i = 0; i < playersTypes.size(); i++) {
						logRecord = new CustomLogRecord(Level.INFO, "-------Start of Reinforcement Phase--------");
						LoggerUtility.consoleHandler.publish(logRecord);

						int reinforcedArmies = player[i].calculateReinformentArmiesInitially(i);
						player[i].infantriesTotal += reinforcedArmies;
						player[i].infantriesAvailable = reinforcedArmies;
						player[i].reinforcementStrategy(i, player[i], player[i].infantriesAvailable);
						// System.out.println("player Name ---" + player[i].getName());
						for (CurrentGameStatics cgs : player[i].currentGameStaticsList) {
							// System.out.println("player territory ---" + cgs.territory.getName() + " " +
							// cgs.infantries);
						}

						logRecord = new CustomLogRecord(Level.INFO,
								"------------End of Reinforcement Phase-----------");
						LoggerUtility.consoleHandler.publish(logRecord);

						logRecord = new CustomLogRecord(Level.INFO, "------------Start of Attack Phase-----------");
						LoggerUtility.consoleHandler.publish(logRecord);

						player[i].attackStrategy(player, i, player[i], mapDetails);

						logRecord = new CustomLogRecord(Level.INFO, "------------End of Attack Phase-----------");
						LoggerUtility.consoleHandler.publish(logRecord);

						if (player[i].currentGameStaticsList.size() == mapDetails.getTerritories().size()) {
							winner = true;
							tResult.setWinnerName(player[i].getName());
							break;
						}
						logRecord = new CustomLogRecord(Level.INFO,
								"------------Start of Fortification Phase-----------");
						LoggerUtility.consoleHandler.publish(logRecord);
						logRecord = new CustomLogRecord(Level.INFO,
								"------------End of Fortification Phase-----------");
						LoggerUtility.consoleHandler.publish(logRecord);
						player[i].fortificationStrategy(i, player[i], player[i].infantriesAvailable);
						for (CurrentGameStatics cgs : player[i].currentGameStaticsList) {
							// System.out .println("player territory ---" + cgs.territory.getName() + " " +
							// cgs.infantries);
						}
					}
					currentTurn--;
					if (winner == true) {
						break;
					}
				}
				if (tResult.getWinnerName() == null) {
					tResult.setWinnerName("Draw");
				}
				tournamentResult.add(tResult);
			}
		}
	}

	@Override
	/**
	 * Method to create observer pattern
	 * 
	 * @param o
	 *            an object of observable class
	 * @param arg
	 *            an object of object class
	 */
	public void update(Observable o, Object arg) {
		Player playerObservable = (Player) o;
		String message = playerObservable.getMessage();
		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Observable : " + message);
		LoggerUtility.consoleHandler.publish(logRecord);

	}
}
