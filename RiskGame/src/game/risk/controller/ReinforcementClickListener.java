
package game.risk.controller;

import game.risk.util.LoggerUtility;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import game.risk.model.entities.Player;
import game.risk.util.CustomLogRecord;
import javax.swing.JOptionPane;

/**
 * method to create a listener for reinforcement which places the infantry in
 * the territory
 * 
 * @author Team
 *
 */
public class ReinforcementClickListener implements ActionListener {

	int i;
	Player player[];

	/**
	 * a constructor
	 * 
	 * @param i
	 *            an integer value
	 * @param player
	 *            an array of player class
	 */
	public ReinforcementClickListener(int i, Player player[]) {
		this.i = i;
		this.player = player;
	}

	/**
	 * Method to perform the action of reinforcement
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

				player[i].setMessage("Reinforcement Phase\r\nPlayer - " + (i + 1) + " has placed infantry in "
						+ player[i].currentGameStaticsList.get(index).territory.getName().toUpperCase());
				player[i].notifyObservers();

				CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
						"Player - " + (i + 1) + " has placed infantry in "
								+ player[i].currentGameStaticsList.get(index).territory.getName().toUpperCase());
				LoggerUtility.consoleHandler.publish(logRecord);

				if (player[i].infantriesAvailable == 0) {
					player[i].getPlayerPanel().btReinforcement.setEnabled(false);
					player[i].attack(i);
				}

			}
		} else {
			JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "No army available");

		}
	}
}
