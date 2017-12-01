package game.risk.controller;

import game.risk.model.entities.Player;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 * Class that creates listener for fortification
 * 
 * @author Team
 *
 */
public class FortificationClickListener implements ActionListener {

	int i;
	Player player[];

	/**
	 * A constructor
	 * 
	 * @param i
	 *            an integer value
	 * @param player
	 *            an array of player class
	 */
	public FortificationClickListener(int i, Player player[]) {
		this.i = i;
		this.player = player;
	}

	/**
	 * Method to move the armies to the players territories
	 * 
	 * @param e
	 *            object of Action event
	 */
	public void actionPerformed(ActionEvent e) {
		player[0].fortificationStrategy(i, player[0], player[0].infantriesAvailable);
	}
}
