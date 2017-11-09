package game.risk.controller;

import game.risk.model.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Method to create a listener to pass the turn to the next player
 * 
 * @author Team
 *
 */
public class OkClickListener implements ActionListener {

	int i;
	Player player[];

	/**
	 * a constructor
	 * 
	 * @param i
	 *            an integer
	 * @param player
	 *            an array of Player class
	 */
	public OkClickListener(int i, Player player[]) {
		this.i = i;
		this.player = player;
	}

	/**
	 * Method to pass the turn to the next player
	 */
	public void actionPerformed(ActionEvent e) {

		player[i].nextPlayerTurn(i);
	}
}