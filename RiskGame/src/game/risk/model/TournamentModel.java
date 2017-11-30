package game.risk.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import game.risk.model.entities.strategy.AggressivePlayerStrategy;
import game.risk.model.entities.strategy.BenevolentPlayerStrategy;
import game.risk.model.entities.strategy.CheaterPlayerStrategy;
import game.risk.model.entities.strategy.HumanStrategy;
import game.risk.model.entities.strategy.RandomPlayerStrategy;
import game.risk.util.CustomLogRecord;

public class TournamentModel

{
	ArrayList<String> playersTypes = new ArrayList<>();
	ArrayList<String> mapNames = new ArrayList<>();
	int gameCount;
	int playerTurns;
	int totalArmies[] = { 40, 35, 30, 25, 20 };
	
	public TournamentModel(String[] players, String[] maps, int game_count, int turns) {
		getRealPlayers(players);
		getRealMaps(maps);
		gameCount = game_count;
		playerTurns = turns;

	}

	private void getRealPlayers(String[] p) {

		for (int i = 0; i < p.length ; i++) {
			if (!(p[i].equals("Select"))) {
				// System.out.println("hiii");
				this.playersTypes.add(p[i]);

			}
		}
	}

	private void getRealMaps(String[] m) {
		this.mapNames.add("World.map");
		/*for (int i = 0; i < m.length; i++) {
			if (m[i]!=null||!(m[i].equals(""))) {

				this.mapNames.add(m[i]);

			}
		}*/
	}

	public void startTournament()

	{
		Player player[];
		RiskMap mapDetails;
		
		for (String mapName : mapNames) {
			mapDetails = MapReader.readMapFile(mapName);
			
			for (int gameIndex = 0 ;gameIndex <gameCount ; gameIndex++ ) 
			{
			player = new Player[playersTypes.size()];

			for (int playerIndex = 0; playerIndex < playersTypes.size(); playerIndex++) {
				
					player[playerIndex] = new Player(null, playerIndex, player, mapDetails);
					player[playerIndex].setComputer(true);
					player[playerIndex].setName(playersTypes.get(playerIndex) + "" + playerIndex);

					if (playersTypes.get(playerIndex).equalsIgnoreCase("Aggressive")) {
					//	logRecord = new CustomLogRecord(Level.INFO, "Strategy: Aggressive");
						// LoggerUtility.consoleHandler.publish(logRecord);
						player[playerIndex].setStrategy(new AggressivePlayerStrategy());

					} else if (playersTypes.get(playerIndex).equalsIgnoreCase("Benevolent")) {
						//logRecord = new CustomLogRecord(Level.INFO, "Strategy: Benevolent Player Strategy");
						// LoggerUtility.consoleHandler.publish(logRecord);
						player[playerIndex].setStrategy(new BenevolentPlayerStrategy());

					} else if (playersTypes.get(playerIndex).equalsIgnoreCase("Random")) {
						//logRecord = new CustomLogRecord(Level.INFO, "Strategy: Random Player Strategy");
						// LoggerUtility.consoleHandler.publish(logRecord);
						player[playerIndex].setStrategy(new RandomPlayerStrategy());

					} else {
						//logRecord = new CustomLogRecord(Level.INFO, "Strategy: Cheater Player Strategy");
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
			//Placing random territories to each player
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
         	//placing infantries to all players
         	for (int i = 0; i < playersTypes.size(); i++)
         	{
         		player[i].infantriesAvailable = (totalArmies[playersTypes.size() - 2])
				- (player[i].currentGameStaticsList.size());
			player[i].placeInfantoryStrategy(i, player[i], player[i].infantriesAvailable)	;
				System.out.println("player Name  ---" +player[i].getName());
				for(CurrentGameStatics cgs: player[i].currentGameStaticsList) {
				System.out.println("player territory  ---" +cgs.territory.getName()+" "+cgs.infantries);
				}
				
				
			}
         	System.out.println("------------End of Startup Phase-----------") ;
         
         	//Start of the turn and start of reinforcement
         while(playerTurns > 0)
         {
         	for (int i = 0; i < playersTypes.size(); i++)
         	{
         		player[i].calculateReinformentArmiesInitially(i);
         		player[i].reinforcementStrategy(i, player[i], player[i].infantriesAvailable)	;
				System.out.println("player Name  ---" +player[i].getName());
				for(CurrentGameStatics cgs: player[i].currentGameStaticsList) {
				System.out.println("player territory  ---" +cgs.territory.getName()+" "+cgs.infantries);
				}

		}
         }
		}
	}
}
}
