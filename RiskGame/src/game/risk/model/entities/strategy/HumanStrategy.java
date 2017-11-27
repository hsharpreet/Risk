package game.risk.model.entities.strategy;

import java.util.logging.Level;

import javax.swing.JOptionPane;

import game.risk.model.entities.Player;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

public class HumanStrategy implements PlayerStrategy {

	@Override
	public int placeInfantory(int i, Player player, int army) {
System.out.println("HumanStrategy was called");
		int index = player.getPlayerPanel().jtCountriesAndArmies.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(player.getPlayerPanel(), "Select terriotary first");
		} else {

			player.currentGameStaticsList.get(index).infantries++;
			player.infantriesAvailable--;
			player.getPlayerPanel().lbAvailableArmies
					.setText("Available Infantries : " + player.infantriesAvailable);
			player.currentGameStaticsTableModel.fireTableDataChanged();
			player.getPlayerPanel().btPlaceInfantry.setEnabled(false);
			//player.nextIndexToEnableButton(i);

			player.setMessage("Startup Phase\r\nPlayer - " + (i + 1) + " has placed infantry in "
					+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase()
					+ " and turn switched to next player");
			player.notifyObservers();

			CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
					"Player - " + (i + 1) + " has placed infantry in "
							+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase()
							+ " and turn switched to next player");
			LoggerUtility.consoleHandler.publish(logRecord);
			
		}
	
		return 0;
	}

	@Override
	public int reinforcement() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int fortification() {
		// TODO Auto-generated method stub
		return 0;
	}

}
