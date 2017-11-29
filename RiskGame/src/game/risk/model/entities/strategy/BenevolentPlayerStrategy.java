package game.risk.model.entities.strategy;

import java.awt.Dialog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import game.risk.gui.AttackGUIPanel;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

public class BenevolentPlayerStrategy implements PlayerStrategy, Serializable   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BenevolentPlayerStrategy() {
		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
				"Strategy: Benevolent Player Strategy");
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
			int index = 0;
			for(int j=0; j<player.currentGameStaticsList.size();j++){
				if(player.currentGameStaticsList.get(j).infantries == 0){
					val.add(j);
				}
			}
			if(val.size() == 0){
				for(int j=0; j<player.currentGameStaticsList.size();j++){
					if(player.currentGameStaticsList.get(j).infantries == 1){
						val.add(j);
					}
				}
			}
			
			if(val.size() == 0){
				for(int j=0; j<player.currentGameStaticsList.size();j++){
					if(player.currentGameStaticsList.get(j).infantries == 2){
						val.add(j);
					}
				}
			}
			
			if(val.size() == 0){
				for(int j=0; j<player.currentGameStaticsList.size();j++){
					if(player.currentGameStaticsList.get(j).infantries <= 4){
						val.add(j);
					}
				}
				index = Collections.min(val);
			}
			
			if(val.size() > 0){
				index = val.get(0);
			}
			

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
		}else {
			JOptionPane.showMessageDialog(player.getPlayerPanel(), "No army available");
		}
		return 0;

	}

	@Override
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
	public int fortificationStrategy(int i, Player player, int army) {
		// TODO Auto-generated method stub
		return 0;
	}

}
