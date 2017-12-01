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
 * The Class to create Aggressive player strategy
 * 
 * @author Team
 *
 */
public class AggressivePlayerStrategy implements PlayerStrategy, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public AggressivePlayerStrategy() {
		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Strategy: Aggressive Player Strategy");
		LoggerUtility.consoleHandler.publish(logRecord);
	}

	@Override
	/**
	 * Function to place available infantry
	 * 
	 * @param i
	 *            Player Index
	 * @param player
	 *            Object Of Player Class
	 * @param army
	 *            no. of armies
	 * @return An integer value
	 */
	public int placeInfantoryStrategy(int i, Player player, int army) {

		if (player.infantriesAvailable > 0) {
			int loop = (player.infantriesAvailable > 0) ? 1 : 0;
			int index = 0;
			// int army = loop= 1;
			if (army > 0) {
				loop = army;
				player.setMessage("Player has finished with armies, comp will place all their left armies now!");
			}
			for (int x = 0; x < loop; x++) {
				if (player.currentGameStaticsList.size() > 0) {
					index = randomNumber(player.currentGameStaticsList.size());
					player.currentGameStaticsList.get(index).infantries++;
					player.infantriesAvailable--;
					player.getPlayerPanel().lbAvailableArmies
							.setText("Available Infantries : " + player.infantriesAvailable);
					player.currentGameStaticsTableModel.fireTableDataChanged();
					player.setMessage("Startup Phase\r\nPlayer - " + player.getName() + " has placed infantry in "
							+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase()
							+ " and turn switched to next player");
					player.notifyObservers();
				} else {
					return 0;
				}
			}

		}

		return 0;
	}

	@Override
	/**
	 * Function For Reinforcement
	 * 
	 * @param i
	 *            Player Index
	 * @param player
	 *            Object Of Player Class
	 * @param army
	 *            no. of armies
	 * @return An integer value
	 */
	public int reinforcementStrategy(int i, Player player, int army) {
		int loop = (player.infantriesAvailable > 0) ? 1 : 0;

		if (army > 0) {
			// loop = (player.infantriesAvailable > 0) ? 1 :
			// player.infantriesAvailable;
			loop = army;
			CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Human has finished with reinforcing armies, "
					+ player.getName() + " will place all their left armies now!");
			LoggerUtility.consoleHandler.publish(logRecord);
		}

		if (player.infantriesAvailable > 0) {
			for (int x = 0; x < loop; x++) {
				if (player.currentGameStaticsList.size() > 0) {
					ArrayList<Integer> val = new ArrayList<Integer>();
					ArrayList<Integer> valIndex = new ArrayList<Integer>();

					for (int j = 0; j < player.currentGameStaticsList.size(); j++) {
						val.add(j);
						valIndex.add(player.currentGameStaticsList.get(j).infantries);
					}

					int index = 0;
					if (val.size() > 0) {
						int max = Collections.max(valIndex);
						index = val.get(valIndex.indexOf(max));
					}

					player.currentGameStaticsList.get(index).infantries++;
					player.infantriesAvailable--;
					player.getPlayerPanel().lbAvailableArmies
							.setText("Available Infantries : " + player.infantriesAvailable);

					player.setMessage("Player - " + player.getName() + " has placed infantry in "
							+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase());

					if (player.infantriesAvailable == 0) {
						return 1;
					}

				}

			}
		}
		player.setMessage("Player - " + player.getName() + " has reinforced his strongest territory. ");
		player.currentGameStaticsTableModel.fireTableDataChanged();

		player.notifyObservers();
		return 0;

	}

	@Override
	/**
	 * Function of attack Strategy of aggressive player
	 * 
	 * @param player
	 *            List of all the player
	 * @param i
	 *            No. of players
	 * @param aggressive
	 *            aggressive player from all the player list
	 * @param mapDetails
	 *            Detail of map
	 * @return An integer value
	 */
	public int attackStrategy(Player[] player, int i, Player aggressive, RiskMap mapDetails) {

		aggressive.setMessage("Player " + aggressive.getName() + " entered into ATTACK Phase");
		aggressive.notifyObservers();

		JDialog dialog = new JDialog();
		dialog.add(new AttackGUIPanel(dialog, player, i, aggressive.currentGameStaticsTableModel,
				aggressive.currentGameStaticsList, mapDetails));
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setSize(1020, 600);
		return 0;

	}

	@Override
	/**
	 * Method to fortify countries
	 * 
	 * @param i
	 *            No. of players
	 * @param aggresivePlayer
	 *            Aggressive Player from all the player list
	 * @param army
	 *            no. of armies
	 * @return An integer value
	 */
	public int fortificationStrategy(int i, Player aggresivePlayer, int army) {
		List<String> playerTerritoriesNames = new ArrayList<>();
		if(aggresivePlayer.currentGameStaticsList.size() > 0){
			for (int j = 0; j < aggresivePlayer.currentGameStaticsList.size(); j++) {
				playerTerritoriesNames.add(aggresivePlayer.currentGameStaticsList.get(j).territory.getName());
			}
			boolean flag = true;
			// Array of territories which have highest army but dont get fortified
			List<String> highestArmyTerritories = new ArrayList<>();
			while (flag) {
				int maxArmy = 0;
				int maxArmyIndex = -1;
				for (int j = 0; j < aggresivePlayer.currentGameStaticsList.size(); j++) {
					if (aggresivePlayer.currentGameStaticsList.get(j).infantries > maxArmy && !highestArmyTerritories
							.contains(aggresivePlayer.currentGameStaticsList.get(j).territory.getName())) {
						maxArmy = aggresivePlayer.currentGameStaticsList.get(j).infantries;
						maxArmyIndex = j;
					}
				}
				int count = 0;
				if (maxArmyIndex == -1) {
					aggresivePlayer.setMessage("Fortification Phase\r\nPlayer - " + aggresivePlayer.getName()
							+ " cant fortify as it has no country which can move its armies to neighbours  ");
					aggresivePlayer.notifyObservers();
					return 0;
				} else {
					for (String neighbour : aggresivePlayer.currentGameStaticsList.get(maxArmyIndex).territory
							.getNeighbouringTerritories()) {
						if (playerTerritoriesNames.contains(neighbour)) {
							int index = playerTerritoriesNames.indexOf(neighbour);
							if (aggresivePlayer.currentGameStaticsList.get(index).infantries > 1) {
								count++;
								aggresivePlayer.currentGameStaticsList.get(index).infantries--;
								aggresivePlayer.currentGameStaticsList.get(maxArmyIndex).infantries++;
								aggresivePlayer.setMessage("Fortification Phase\r\nPlayer - " + aggresivePlayer.getName()
										+ " has transfered 1 infantry from "
										+ aggresivePlayer.currentGameStaticsList.get(index).territory.getName() + " to "
										+ aggresivePlayer.currentGameStaticsList.get(maxArmyIndex).territory.getName());
								aggresivePlayer.notifyObservers();
							}
						}
					}
					if (count > 0) {
						flag = false;

					} else if (count == 0) {
						highestArmyTerritories
								.add(aggresivePlayer.currentGameStaticsList.get(maxArmyIndex).territory.getName());
					}
				}
			}
			aggresivePlayer.currentGameStaticsTableModel.fireTableDataChanged();
			aggresivePlayer.setMessage("Player - " + aggresivePlayer.getName() + " fortification phase ended  ");
			aggresivePlayer.notifyObservers();
			return 0;
		}
		return 0;
		}
}