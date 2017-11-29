package game.risk.model.entities.strategy;

import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import game.risk.gui.AttackGUIPanel;
import game.risk.model.AttackPhase;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

public class RandomPlayerStrategy implements PlayerStrategy, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RandomPlayerStrategy() {
		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
				"Strategy: Random Player Strategy");
		LoggerUtility.consoleHandler.publish(logRecord);
	}
	
	@Override
	public int placeInfantoryStrategy(int i, Player player, int army) {
		
		if (player.infantriesAvailable > 0) {
			int loop = (player.infantriesAvailable > 0) ? 1 : 0;
			int index = 0;
			//int army = loop= 1;
			if (army > 0) {
				//loop = (player.infantriesAvailable > 0) ? 1 : player.infantriesAvailable;
				loop = army;
				CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
						"Human has finished with armies, computers will place all their left armies now!");
				LoggerUtility.consoleHandler.publish(logRecord);
			}
			for (int x = 0; x < loop; x++) {

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
			}

			player.getPlayerPanel().btPlaceInfantry.setEnabled(false);
			
		}
		
	
		return 0;
	}
	
	@Override
	public int reinforcementStrategy(int i, Player player, int army) {
		int loop = (player.infantriesAvailable > 0) ? 1 : 0;

		if (army > 0) {
			loop = army;
			CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Human has finished with reinforcing armies, "
					+ player.getName() + " will place all their left armies now!");
			LoggerUtility.consoleHandler.publish(logRecord);
		}

		if (player.infantriesAvailable > 0) {
			for (int x = 0; x < loop; x++) {

				int index = randomNumber(player.currentGameStaticsList.size());

				player.currentGameStaticsList.get(index).infantries++;
				player.infantriesAvailable--;
				player.getPlayerPanel().lbAvailableArmies
						.setText("Available Infantries : " + player.infantriesAvailable);
				player.currentGameStaticsTableModel.fireTableDataChanged();

				player.setMessage("Reinforcement Phase\r\nPlayer - " + player.getName() + " has placed infantry in "
						+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase());
				player.notifyObservers();

				if (player.infantriesAvailable == 0) {
					return 1;
				}

			}
		} else {
			CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
					"No army available");
			LoggerUtility.consoleHandler.publish(logRecord);
		}
		return 0;

	}

	@Override
	public int attackStrategy(Player player[], int ii, Player random, RiskMap mapDetails) {

		random.setMessage("Player " + random.getName() + " entered into ATTACK Phase");
		random.notifyObservers();

		JDialog dialog = new JDialog();
		dialog.add(new AttackGUIPanel(dialog, player, ii, random.currentGameStaticsTableModel,
				random.currentGameStaticsList, mapDetails));
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		//dialog.setSize(1020, 600);
		// dialog.setVisible(true);
		// new AttackPhase(player, ii, random.currentGameStaticsTableModel,
		// random.currentGameStaticsList, mapDetails);
		return 0;
	}

	@Override
	public int fortificationStrategy(int i, Player random, int army) {

		ArrayList<String> terrList = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();

		for (int j = 0; j < random.currentGameStaticsList.size(); j++) {
			list.add(random.currentGameStaticsList.get(j).territory.getName());
		}

		for (int j = 0; j < list.size(); j++) {

			for (int jj = 0; jj < random.currentGameStaticsList.get(j).territory.getNeighbouringTerritories()
					.size(); jj++) {
				String destinationTerritory = random.currentGameStaticsList.get(j).territory
						.getNeighbouringTerritories().get(jj);

				if (list.contains(destinationTerritory) && random.currentGameStaticsList.get(j).infantries > 1) {
					terrList.add(j + ":" + destinationTerritory);
				}
			}
		}
		int possibleMoves = terrList.size();
		int randomMoves = new Random().nextInt(possibleMoves);

		for (int k = 0; k < randomMoves; k++) {
			int minus = Integer.parseInt(terrList.get(k).split(":")[0]);

			String destinationTerritory = terrList.get(k).split(":")[1];
			int plus = list.indexOf(destinationTerritory);

			random.currentGameStaticsList.get(minus).infantries--;
			random.currentGameStaticsList.get(plus).infantries++;

			random.setMessage("Fortification Phase\r\nPlayer - " + random.getName() + " has transfered 1 infantry from "
					+ random.currentGameStaticsList.get(minus).territory.getName() + " to " + destinationTerritory);
			random.notifyObservers();

			random.currentGameStaticsTableModel.fireTableDataChanged();
		}

		return 0;
	}
}