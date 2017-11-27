package game.risk.model.entities.strategy;

import java.util.logging.Level;

import javax.swing.JOptionPane;

import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

public class AggressivePlayerStrategy  implements PlayerStrategy{

	public AggressivePlayerStrategy(){
		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
				"Strategy: Aggressive Player Strategy");
		LoggerUtility.consoleHandler.publish(logRecord);
	}
	
	@Override
	public int placeInfantoryStrategy(int i, Player player, int army) {
		
		if (player.infantriesAvailable > 0) {
			int loop = (player.infantriesAvailable > 0) ? 1 : 0;
			int index = 0;
			//int army = loop= 1;
			if (army > 0) {
				//loop = (player.infantriesAvailable > 0) ? 1 : player.infantriesAvailable;
				loop = army;
				CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
						"Player has finished with armies, comp will place all their left armies now!");
				LoggerUtility.consoleHandler.publish(logRecord);
			}
			for (int x = 0; x < loop; x++) {
				index = randomNumber(player.currentGameStaticsList.size());
				player.currentGameStaticsList.get(index).infantries++;
				player.infantriesAvailable--;
				player.getPlayerPanel().lbAvailableArmies
						.setText("Available Infantries : " + player.infantriesAvailable);
				player.currentGameStaticsTableModel.fireTableDataChanged();
			}

			player.getPlayerPanel().btPlaceInfantry.setEnabled(false);
			//player.nextIndexToEnableButton(i);

			player.setMessage("Startup Phase\r\nPlayer - " + player.getName() + " has placed infantry in "
					+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase()
					+ " and turn switched to next player");
			player.notifyObservers();

			CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
					"Player - " + player.getName() + " has placed infantry in "
							+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase()
							+ " and turn switched to next player");
			LoggerUtility.consoleHandler.publish(logRecord);
		}
		
	
		return 0;
	}
	
	@Override
	public int reinforcementStrategy(int i, Player player, int army) {
		int loop = (player.infantriesAvailable > 0) ? 1 : 0;

		if (army > 0) {
			//loop = (player.infantriesAvailable > 0) ? 1 : player.infantriesAvailable;
			loop = army;
			CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Human has finished with reinforcing armies, "
					+ player.getName() + " will place all their left armies now!");
			LoggerUtility.consoleHandler.publish(logRecord);
		}

		if (player.infantriesAvailable > 0) {
		for (int x = 0; x < loop; x++) {

			
				int index = randomNumber(player.currentGameStaticsList.size());// player.getPlayerPanel().jtCountriesAndArmies.getSelectedRow();

				player.currentGameStaticsList.get(index).infantries++;
				player.infantriesAvailable--;
				player.getPlayerPanel().lbAvailableArmies
						.setText("Available Infantries : " + player.infantriesAvailable);
				player.currentGameStaticsTableModel.fireTableDataChanged();

				player.setMessage("Reinforcement Phase\r\nPlayer - " + player.getName() + " has placed infantry in "
						+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase());
				player.notifyObservers();

				CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
						"Player - " + player.getName() + " has placed infantry in "
								+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase());
				LoggerUtility.consoleHandler.publish(logRecord);

				if (player.infantriesAvailable == 0) {
					// player.getPlayerPanel().btReinforcement.setEnabled(false);
					// player.attack(i);
					return 1;
				}

		}
		}else {
			JOptionPane.showMessageDialog(player.getPlayerPanel(), "No army available");
		}
		return 0;

	}

	@Override
	public int attackStrategy(Player[] player, int i, Player player2, RiskMap mapDetails) {
		System.out.println("Aggressive Player strategy attack");
		return 0;
	}

	@Override
	public int fortificationStrategy() {
		System.out.println("Aggressive Player strategy attack");
		return 0;
	}
	
}