
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
	 * 
	 * @param e
	 *            Object of ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		boolean flag = false;
		for (int j = 0; j < player.length; j++) {
			if (player[j].infantriesAvailable > 0) {
				player[j].placeInfantoryStrategy(j, player[j], 0);

			}
			if (player[0].infantriesAvailable == 0)
				flag = true;
			else {
				player[0].getPlayerPanel().btPlaceInfantry.setEnabled(true);
			}
		}
		if (flag) {
			for (int j = 1; j < player.length; j++) {
				if (player[j].infantriesAvailable > 0) {
					player[j].placeInfantoryStrategy(j, player[j], player[j].infantriesAvailable);
					// player[j].nextIndexToEnableButton(j);
				}
			}
			player[0].nextIndexToEnableButton(0);
		}

	}
}
