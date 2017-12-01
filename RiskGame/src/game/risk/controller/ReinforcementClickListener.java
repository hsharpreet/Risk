
package game.risk.controller;

import game.risk.util.LoggerUtility;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import game.risk.gui.RiskGame;
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

		boolean flag = false;

		int j = 0;
		if (player[0].infantriesAvailable > 0) {
				if (player[0].reinforcementStrategy(j, player[j], 0) == 1) {
					//reinforcement for Human is finished
					player[0].attackInitialization(0);
					flag = true;
				}
			if (flag) {
				player[0].getPlayerPanel().btReinforcement.setEnabled(false);
				//player[0].nextPlayerTurn(1);
			}
		}
	}
}
