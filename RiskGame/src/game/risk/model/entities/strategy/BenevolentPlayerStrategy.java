package game.risk.model.entities.strategy;

import java.awt.Dialog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import javax.swing.JDialog;
import game.risk.gui.AttackGUIPanel;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

/**
 * Class to create Benevolent Player Strategy
 * 
 * @author Team
 *
 */
public class BenevolentPlayerStrategy implements PlayerStrategy, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * A constructor to initialize the objects
	 */
	public BenevolentPlayerStrategy() {
		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Strategy: Benevolent Player Strategy");
		LoggerUtility.consoleHandler.publish(logRecord);
	}

	@Override
	/**
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
			if (army > 0) {
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
		int loop = (player.infantriesAvailable > 0) ? 1 : 0;

		if (army > 0) {
			loop = army;
			CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Human has finished with reinforcing armies, "
					+ player.getName() + " will place all their left armies now!");
			LoggerUtility.consoleHandler.publish(logRecord);
		}

		if (player.infantriesAvailable > 0) {
			while (player.infantriesAvailable > 0) {
				int minArmy = Integer.MAX_VALUE;
				int minArmyIndex = -1;
				if (player.currentGameStaticsList.size() > 0) {
					for (int j = 0; j < player.currentGameStaticsList.size(); j++) {
						if (player.currentGameStaticsList.get(j).infantries < minArmy) {
							minArmy = player.currentGameStaticsList.get(j).infantries;
							minArmyIndex = j;
						}
					}
					player.currentGameStaticsList.get(minArmyIndex).infantries++;
					player.infantriesAvailable--;
					player.getPlayerPanel().lbAvailableArmies
							.setText("Available Infantries : " + player.infantriesAvailable);
					player.setMessage("Reinforcement Phase\r\nPlayer - " + player.getName() + " has placed infantry in "
							+ player.currentGameStaticsList.get(minArmyIndex).territory.getName().toUpperCase());
					player.notifyObservers();
					player.currentGameStaticsTableModel.fireTableDataChanged();
				}

			}
		} else {
			player.setMessage("Reinforcement Phase\r\nPlayer - " + player.getName() + " has no armies available");
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
	 * @param benevolent
	 *            attribute of Player class
	 * @param mapDetails
	 *            an object of RiskMap
	 * @return an integer value
	 */
	public int attackStrategy(Player[] player, int i, Player benevolent, RiskMap mapDetails) {

		benevolent.setMessage("Player " + benevolent.getName() + " entered into ATTACK Phase");
		benevolent.notifyObservers();

		JDialog dialog = new JDialog();
		dialog.add(new AttackGUIPanel(dialog, player, i, benevolent.currentGameStaticsTableModel,
				benevolent.currentGameStaticsList, mapDetails));
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setSize(1020, 600);
		return 0;

	}

	@Override
	/**
	 * 
	 * Method for fortification
	 * 
	 * @param i
	 *            Player index
	 * @param player
	 *            an object of Player class
	 * @param army
	 *            Number of armies
	 * @return an integer value
	 */
	public int fortificationStrategy(int i, Player player, int army) {
		if (player.currentGameStaticsList.size() > 0) {
			List<String> playerAllNeighbours = new ArrayList<>();
			for (int j = 0; j < player.currentGameStaticsList.size(); j++) {
				for (int jj = 0; jj < player.currentGameStaticsList.get(j).territory.getNeighbouringTerritories()
						.size(); jj++) {
					String destinationTerritory = player.currentGameStaticsList.get(j).territory
							.getNeighbouringTerritories().get(jj);
					playerAllNeighbours.add(destinationTerritory);
				}
			}
			boolean flag = true;
			while (flag) {
				int maxArmy = 0;
				int maxArmyIndex = -1;
				for (int j = 0; j < player.currentGameStaticsList.size(); j++) {
					if (player.currentGameStaticsList.get(j).infantries > maxArmy) {
						maxArmy = player.currentGameStaticsList.get(j).infantries;
						maxArmyIndex = j;
					}
				}

				int minArmy = Integer.MAX_VALUE;
				int minArmyIndex = -1;
				for (int j = 0; j < player.currentGameStaticsList.size(); j++) {
					if (player.currentGameStaticsList.get(j).infantries < minArmy) {
						if (playerAllNeighbours.contains(player.currentGameStaticsList.get(j).territory.getName())) {
							minArmy = player.currentGameStaticsList.get(j).infantries;
							minArmyIndex = j;
						}
					}
				}
				if ((maxArmy - minArmy) > 1) {
					player.currentGameStaticsList.get(maxArmyIndex).infantries--;
					player.currentGameStaticsList.get(minArmyIndex).infantries++;
					CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
							"Fortification Phase\r\nPlayer - " + player.getName() + " has transfered 1 infantry from "
									+ player.currentGameStaticsList.get(maxArmyIndex).territory.getName() + " to "
									+ player.currentGameStaticsList.get(minArmyIndex).territory.getName());
					LoggerUtility.consoleHandler.publish(logRecord);
					player.setMessage(
							"Fortification Phase\r\nPlayer - " + player.getName() + " has transfered 1 infantry from "
									+ player.currentGameStaticsList.get(maxArmyIndex).territory.getName() + " to "
									+ player.currentGameStaticsList.get(minArmyIndex).territory.getName());
					player.notifyObservers();

					player.currentGameStaticsTableModel.fireTableDataChanged();
				} else {
					flag = false;
					player.setMessage("Fortification Phase\r\nPlayer - " + player.getName() + " can't fortify ");
					player.notifyObservers();
				}
			}
			player.setMessage("Fortification Phase\r\nPlayer - " + player.getName() + " fortification ended ");
			player.notifyObservers();
			return 0;
		}
		return 0;
	}

}
