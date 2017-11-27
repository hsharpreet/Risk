
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
		boolean flag = false;
		for (int j = 0; j < player.length; j++) {
			if (player[j].infantriesAvailable > 0) {
				player[j].placeInfantoryStrategy(j, player[j], 0);
				player[j].nextIndexToEnableButton(j);
			}
			if (player[0].infantriesAvailable == 0)
				flag = true;
		}
		if (flag) {
			for (int j = 1; j < player.length; j++) {
				if (player[j].infantriesAvailable > 0) {
					player[j].placeInfantoryStrategy(j, player[j], player[j].infantriesAvailable);
					player[j].nextIndexToEnableButton(j);
				}
			}
		}

	}
		
		
		
	/*if (player[i].infantriesAvailable > 0) {
			player[i].placeInfantoryStrategy( i, player[i]);
			if (!player[i].isComputer()) {/*
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
					//player[i].nextIndexToEnableButton(i);

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
			}
			changeComps(player[0].infantriesAvailable);
		} else {
			//changeComps(true);
			JOptionPane.showMessageDialog(player[0].getPlayerPanel(), "No army available");

		}

	}

	private void changeComps(int army) {
		for (int j = 1; j < player.length; j++) {
			if (player[j].infantriesAvailable > 0) {
				int loop = (player[j].infantriesAvailable > 0) ? 1 : player[j].infantriesAvailable;
				int index = 0;
				if (army == 0) {
					loop = player[j].infantriesAvailable;
					CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
							"Player has finished with armies, comp will place all their left armies now!");
					LoggerUtility.consoleHandler.publish(logRecord);
				}
				for (int x = 0; x < loop; x++) {
					index = randomNumber(player[j].currentGameStaticsList.size());
					player[j].currentGameStaticsList.get(index).infantries++;
					player[j].infantriesAvailable--;
					player[j].getPlayerPanel().lbAvailableArmies
							.setText("Available Infantries : " + player[j].infantriesAvailable);
					player[j].currentGameStaticsTableModel.fireTableDataChanged();
				}

				player[j].getPlayerPanel().btPlaceInfantry.setEnabled(false);
				player[j].nextIndexToEnableButton(i);

				player[j].setMessage("Startup Phase\r\nPlayer - " + (j) + " has placed infantry in "
						+ player[j].currentGameStaticsList.get(index).territory.getName().toUpperCase()
						+ " and turn switched to next player");
				player[j].notifyObservers();

				CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
						"Player - " + (j) + " has placed infantry in "
								+ player[j].currentGameStaticsList.get(index).territory.getName().toUpperCase()
								+ " and turn switched to next player");
				LoggerUtility.consoleHandler.publish(logRecord);
			}
			
		}

	}*/

}
