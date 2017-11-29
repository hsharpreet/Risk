package game.risk.model.entities.strategy;

import java.awt.Dialog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;

import javax.swing.JDialog;

import game.risk.gui.AttackGUIPanel;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

public class AggressivePlayerStrategy  implements PlayerStrategy{

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
				//loop = (player.infantriesAvailable > 0) ? 1 : player.infantriesAvailable;
				loop = army;
				CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
						"Player has finished with armies, comp will place all their left armies now!");
				LoggerUtility.consoleHandler.publish(logRecord);
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
					if(player.currentGameStaticsList.get(j).infantries < 7){
						val.add(j);
					}else{
						player.setMessage("Player - " + player.getName() + " has made "
								+ player.currentGameStaticsList.get(j).territory.getName().toUpperCase()+" strogest with 7 armies.");
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
	public int fortificationStrategy(int i, Player player, int army) {
		System.out.println("Aggressive Player strategy attack");
		return 0;
	}
	
}