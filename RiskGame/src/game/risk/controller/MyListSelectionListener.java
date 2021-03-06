package game.risk.controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import game.risk.model.entities.Player;

/**
 * A class to create a listener for creating a list of the player details
 * 
 * @author Team
 *
 */
public class MyListSelectionListener implements ListSelectionListener {

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
	public MyListSelectionListener(int i, Player player[]) {
		this.i = i;
		this.player = player;
	}

	@Override
	/**
	 * Method which is called whenever a selection is changed
	 * 
	 * @param e
	 *            object of event that characterize change in selection
	 */
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			int index = player[i].getPlayerPanel().jtCountriesAndArmies.getSelectedRow();
			if (index >= 0) {
				player[i].neighbourListModel.neighbours = player[i].currentGameStaticsList.get(index).territory
						.getNeighbouringTerritories();
				player[i].getPlayerPanel().lsNeighbour.updateUI();

			}
		}
	}

}