package game.risk.model.entities.strategy;

import java.awt.Dialog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import game.risk.gui.AttackGUIPanel;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

/**
 * Class to create Cheater Player Strategy Pattern
 * 
 * @author Team
 */
public class CheaterPlayerStrategy implements PlayerStrategy, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * A constructor
	 */
	public CheaterPlayerStrategy() {
		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Strategy: Cheater Player Strategy");
		LoggerUtility.consoleHandler.publish(logRecord);
	}

	@Override
	/**
	 * 
	 * Method to place available infantry
	 * 
	 * @param i
	 *            Player Index
	 * @param player
	 *            Object Of Player Class
	 * @param army
	 *            no. of armies
	 * @return an integer value
	 */
	public int placeInfantoryStrategy(int i, Player player, int army) {

		if (player.infantriesAvailable > 0) {
			int loop = (player.infantriesAvailable > 0) ? 1 : 0;
			int index = 0;
			// int army = loop= 1;
			if (army > 0) {
				// loop = (player.infantriesAvailable > 0) ? 1 :
				// player.infantriesAvailable;
				loop = army;
				CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
						"Player has finished with armies, comp will place all their left armies now!");
				LoggerUtility.consoleHandler.publish(logRecord);
			}
			for (int x = 0; x < loop; x++) {
				if (player.currentGameStaticsList.size() > 0) {
					index = randomNumber(player.currentGameStaticsList.size());
					player.currentGameStaticsList.get(index).infantries++;
					player.infantriesAvailable--;
					player.getPlayerPanel().lbAvailableArmies
							.setText("Available Infantries : " + player.infantriesAvailable);
					player.currentGameStaticsTableModel.fireTableDataChanged();
				}

			}

			player.setMessage("Startup Phase\r\nPlayer - " + player.getName() + " has placed infantry in "
					+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase()
					+ " and turn switched to next player");
			player.notifyObservers();

		}
		return 0;
	}

	@Override
	/**
	 * Method For Reinforcement
	 * 
	 * @param i
	 *            Player Index
	 * @param player
	 *            Object Of Player Class
	 * @param army
	 *            no. of armies
	 * @return an integer value
	 */
	public int reinforcementStrategy(int i, Player player, int army) {

		if (player.currentGameStaticsList.size() > 0) {
			for (int x = 0; x < player.currentGameStaticsList.size(); x++) {

				if (player.currentGameStaticsList.get(x).infantries == 0) {
					player.currentGameStaticsList.get(x).infantries = 1;
				} else {
					player.currentGameStaticsList.get(x).infantries = player.currentGameStaticsList.get(x).infantries
							* 2;
				}
				player.infantriesAvailable = 0;
				player.getPlayerPanel().lbAvailableArmies
						.setText("Available Infantries : " + player.infantriesAvailable);
				player.currentGameStaticsTableModel.fireTableDataChanged();
			}
			player.setMessage("Reinforcement Phase\r\nPlayer - " + player.getName() + " has doubled his armies.");
			player.notifyObservers();

			return 1;
		}
		return 0;
	}

	@Override
	/**
	 * Method for attack strategy
	 * 
	 * @param player
	 *            array of player class
	 * @param i
	 *            Player index
	 * @param cheater
	 *            attribute of Player class
	 * @param mapDetails
	 *            an object of RiskMap
	 * @return an integer value
	 */
	public int attackStrategy(Player[] player, int i, Player cheater, RiskMap mapDetails) {

		cheater.setMessage("Player " + cheater.getName() + " entered into ATTACK Phase");
		cheater.notifyObservers();

		JDialog dialog = new JDialog();
		dialog.add(new AttackGUIPanel(dialog, player, i, cheater.currentGameStaticsTableModel,
				cheater.currentGameStaticsList, mapDetails));
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setSize(1020, 600);
		// dialog.setVisible(true);
		// new AttackPhase(player, ii, random.currentGameStaticsTableModel,
		// random.currentGameStaticsList, mapDetails);
		return 0;

	}

	@Override
	/**
	 * Method for fortification
	 * 
	 * @param i
	 *            Player index
	 * @param cheater
	 *            an object of Player class
	 * @param army
	 *            Number of armies
	 * @return an integer value
	 */
	public int fortificationStrategy(int i, Player cheater, int army) {

		ArrayList<String> nonList = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> neighlist = new ArrayList<String>();

		if (cheater.currentGameStaticsList.size() > 0) {
			for (int j = 0; j < cheater.currentGameStaticsList.size(); j++) {
				list.add(cheater.currentGameStaticsList.get(j).territory.getName());
			}

			for (int j = 0; j < list.size(); j++) {
				neighlist = new ArrayList<>();
				for (int jj = 0; jj < cheater.currentGameStaticsList.get(j).territory.getNeighbouringTerritories()
						.size(); jj++) {
					String neighbour = cheater.currentGameStaticsList.get(j).territory.getNeighbouringTerritories()
							.get(jj);
					neighlist.add(neighbour);
				}
				boolean allMine = true;
				for (int jj = 0; jj < neighlist.size(); jj++) {
					if (!list.contains(neighlist.get(jj))) {
						int infant = cheater.currentGameStaticsList.get(j).infantries;
						if (infant > 0) {
							cheater.setMessage("Fortification Phase\r\nPlayer - " + cheater.getName()
									+ " has doubled infantries on "
									+ cheater.currentGameStaticsList.get(j).territory.getName() + " from " + infant
									+ " to " + infant * 2);
							cheater.currentGameStaticsList.get(j).infantries = infant * 2;
						}
						cheater.notifyObservers();
						cheater.currentGameStaticsTableModel.fireTableDataChanged();
						allMine = false;
						break;
					}
				}

				if (allMine) {
					nonList.add(j + ":" + list.get(j));
				}
			}
			return 1;
		}
		return 0;
	}

}
