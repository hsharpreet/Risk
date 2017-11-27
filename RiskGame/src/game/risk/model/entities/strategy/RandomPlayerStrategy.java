package game.risk.model.entities.strategy;

import java.util.logging.Level;

import game.risk.model.entities.Player;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

public class RandomPlayerStrategy implements PlayerStrategy {

	@Override
	public int placeInfantory(int i, Player player, int army) {
		System.out.println("RandomPlayerStrategy was called");
		if (player.infantriesAvailable > 0) {
			int loop = (player.infantriesAvailable > 0) ? 1 : 0;
			int index = 0;
			//int army = loop= 1;
			if (army > 0) {
				loop = (player.infantriesAvailable > 0) ? 1 : player.infantriesAvailable;
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
	public int reinforcement() {
		System.out.println("Random Player strategy reinforcement");
		return 0;
	}

	@Override
	public int attack() {
		System.out.println("Random Player strategy attack");
		return 0;
	}

	@Override
	public int fortification() {
		System.out.println("Random Player strategy fortify");
		return 0;
	}
}