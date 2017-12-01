package game.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.risk.model.entities.Player;

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
	 * 
	 * @param e
	 *            Object of ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		if(!Player.endOfGame){
			player[i].nextPlayerTurn(1);
		}
	}
}