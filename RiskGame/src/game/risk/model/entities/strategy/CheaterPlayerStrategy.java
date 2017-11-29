package game.risk.model.entities.strategy;

import java.awt.Dialog;
import java.io.Serializable;
import java.util.logging.Level;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import game.risk.gui.AttackGUIPanel;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

public class CheaterPlayerStrategy implements PlayerStrategy,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheaterPlayerStrategy() {
		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
				"Strategy: Cheater Player Strategy");
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
		for (int x = 0; x < player.currentGameStaticsList.size(); x++) {
			
			if (player.currentGameStaticsList.get(x).infantries == 0) {
				player.currentGameStaticsList.get(x).infantries = 1;
			} else {
				player.currentGameStaticsList.get(x).infantries = player.currentGameStaticsList.get(x).infantries * 2;
			}
			player.infantriesAvailable = 0;
			player.getPlayerPanel().lbAvailableArmies.setText("Available Infantries : " + player.infantriesAvailable);

		}
		player.setMessage("Reinforcement Phase\r\nPlayer - " + player.getName() + " has doubled his armies.");
		player.notifyObservers();
		player.currentGameStaticsTableModel.fireTableDataChanged();
		return 1;

	}

	@Override
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
	public int fortificationStrategy(int i, Player player, int army) {
		return 0;
	}

}
