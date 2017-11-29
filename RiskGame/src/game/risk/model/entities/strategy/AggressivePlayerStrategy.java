package game.risk.model.entities.strategy;

import java.awt.Dialog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;

import javax.swing.JDialog;

import game.risk.gui.AttackGUIPanel;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

public class AggressivePlayerStrategy  implements PlayerStrategy, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AggressivePlayerStrategy(){
		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
				"Strategy: Aggressive Player Strategy");
		LoggerUtility.consoleHandler.publish(logRecord);
	}
	
	@Override
	public int placeInfantoryStrategy(int i, Player player, int army) {
		
		if (player.infantriesAvailable > 0) {
			int loop = (player.infantriesAvailable > 0) ? 1 : 0;
			int index = 0;
			//int army = loop= 1;
			if (army > 0) {
				loop = army;
				player.setMessage("Player has finished with armies, comp will place all their left armies now!");
			}
			for (int x = 0; x < loop; x++) {
				index = randomNumber(player.currentGameStaticsList.size());
				player.currentGameStaticsList.get(index).infantries++;
				player.infantriesAvailable--;
				player.getPlayerPanel().lbAvailableArmies
						.setText("Available Infantries : " + player.infantriesAvailable);
				player.currentGameStaticsTableModel.fireTableDataChanged();
			}

			player.getPlayerPanel().btPlaceInfantry.setEnabled(false);
			//player.nextIndexToEnableButton(i);

			player.setMessage("Startup Phase\r\nPlayer - " + player.getName() + " has placed infantry in "
					+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase()
					+ " and turn switched to next player");
			player.notifyObservers();

		}
		
	
		return 0;
	}
	
	@Override
	public int reinforcementStrategy(int i, Player player, int army) {
		int loop = (player.infantriesAvailable > 0) ? 1 : 0;

		if (army > 0) {
			//loop = (player.infantriesAvailable > 0) ? 1 : player.infantriesAvailable;
			loop = army;
			CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Human has finished with reinforcing armies, "
					+ player.getName() + " will place all their left armies now!");
			LoggerUtility.consoleHandler.publish(logRecord);
		}

		if (player.infantriesAvailable > 0) {
			for (int x = 0; x < loop; x++) {
				
				ArrayList<Integer> val = new ArrayList<Integer>();
				
				for(int j=0; j<player.currentGameStaticsList.size();j++){
					if(player.currentGameStaticsList.get(j).infantries < 6){
						val.add(j);
					}else{
						player.setMessage("Player - " + player.getName() + " has made "
								+ player.currentGameStaticsList.get(j).territory.getName().toUpperCase()+" strogest with 6 armies.");
					}
				}
				
				int index = 0;
				if(val.size() > 0){
					index = Collections.max(val);
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
		player.setMessage("Player - " + player.getName() + " has reinforced his strongest territory. ");
		player.currentGameStaticsTableModel.fireTableDataChanged();

		
		player.notifyObservers();
		return 0;

	}

	@Override
	public int attackStrategy(Player[] player, int i, Player aggressive, RiskMap mapDetails) {

		aggressive.setMessage("Player " + aggressive.getName() + " entered into ATTACK Phase");
		aggressive.notifyObservers();

		JDialog dialog = new JDialog();
		dialog.add(new AttackGUIPanel(dialog, player, i, aggressive.currentGameStaticsTableModel,
				aggressive.currentGameStaticsList, mapDetails));
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setSize(1020, 600);
		// dialog.setVisible(true);
		// new AttackPhase(player, ii, random.currentGameStaticsTableModel,
		// random.currentGameStaticsList, mapDetails);
		return 0;

	}

	@Override
	public int fortificationStrategy(int i, Player random, int army) {

		ArrayList<String> terrList = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();

		ArrayList<String> maxArmy = new ArrayList<String>();
		ArrayList<String> minArmy = new ArrayList<String>();
		
		for (int j = 0; j < random.currentGameStaticsList.size(); j++) {
			list.add(random.currentGameStaticsList.get(j).territory.getName());
		}

		for (int j = 0; j < list.size(); j++) {
			for (int jj = 0; jj < random.currentGameStaticsList.get(j).territory.getNeighbouringTerritories()
					.size(); jj++) {
				String destinationTerritory = random.currentGameStaticsList.get(j).territory
						.getNeighbouringTerritories().get(jj);

				if (list.contains(destinationTerritory) && random.currentGameStaticsList.get(j).infantries > 6) {
					maxArmy.add(j + ":" + destinationTerritory);
				}
				if(maxArmy.size() >= 5){
					break;
				}
			}
			if(maxArmy.size() < 5){
				for (int jj = 0; jj < random.currentGameStaticsList.get(j).territory.getNeighbouringTerritories()
						.size(); jj++) {
					String destinationTerritory = random.currentGameStaticsList.get(j).territory
							.getNeighbouringTerritories().get(jj);

					if (list.contains(destinationTerritory) && random.currentGameStaticsList.get(j).infantries > 4) {
						maxArmy.add(j + ":" + destinationTerritory);
					}
					if(maxArmy.size() >= 5){
						break;
					}
				}
			}
			
			if(maxArmy.size() < 5){
				for (int jj = 0; jj < random.currentGameStaticsList.get(j).territory.getNeighbouringTerritories()
						.size(); jj++) {
					String destinationTerritory = random.currentGameStaticsList.get(j).territory
							.getNeighbouringTerritories().get(jj);

					if (list.contains(destinationTerritory) && random.currentGameStaticsList.get(j).infantries > 2) {
						maxArmy.add(j + ":" + destinationTerritory);
					}
					if(maxArmy.size() >= 5){
						break;
					}
				}
			}
		}
		
		for (int j = 0; j < list.size(); j++) {
			if (random.currentGameStaticsList.get(j).infantries  == 0) {
				minArmy.add(j+"");
			}
		}
		while(minArmy.size() <=5){
			for (int j = 0; j < list.size(); j++) {
				if (random.currentGameStaticsList.get(j).infantries  <= 2) {
					minArmy.add(j+"");
				}
			}
		}
		
		for(int j = 0; j < minArmy.size(); j++){
			//TODO
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