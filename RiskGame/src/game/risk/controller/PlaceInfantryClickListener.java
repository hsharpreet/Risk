
package game.risk.controller;

import game.risk.util.LoggerUtility;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import game.risk.model.entities.Player;
import game.risk.util.CustomLogRecord;
import javax.swing.JOptionPane;

/**
 * Class to move and place the infantry in territories owned by the player
 * 
 * @author Team
 *
 */
public class PlaceInfantryClickListener implements ActionListener {

	int i;
	Player player[];

	/**
	 * A constructor
	 * 
	 * @param i
	 *            an integer value
	 * @param player
	 *            an array of Player class
	 */
	public PlaceInfantryClickListener(int i, Player player[]) {
		this.i = i;
		this.player = player;
	}

	/**
	 * Method to change and place the infantry in the territories owned by the
	 * player
	 */
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
				player[i].nextIndexToEnableButton(i);

				player[i].setMessage("Startup Phase\r\nPlayer - " + (i + 1) + " has placed infantry in "
						+ player[i].currentGameStaticsList.get(index).territory.getName().toUpperCase()
						+ " and turn switched to next player");
				player[i].notifyObservers();

				CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
						"Player - " + (i + 1) + " has placed infantry in "
								+ player[i].currentGameStaticsList.get(index).territory.getName().toUpperCase()
								+ " and turn switched to next player");
				LoggerUtility.consoleHandler.publish(logRecord);

			}
		} else {
			JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "No army available");

		}
	}
}
