package game.risk.model.entities.strategy;

import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import game.risk.gui.AttackGUIPanel;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

public class RandomPlayerStrategy implements PlayerStrategy {

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
                try {
					TimeUnit.MILLISECONDS.sleep(1000);
					System.out.println("waiting for 1 sec");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
				CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
						"Player - " + player.getName() + " has placed infantry in "
								+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase()
								+ " and turn switched to next player");
				LoggerUtility.consoleHandler.publish(logRecord);
			}

			player.getPlayerPanel().btPlaceInfantry.setEnabled(false);
			
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

			
				int index = randomNumber(player.currentGameStaticsList.size());// player.getPlayerPanel().jtCountriesAndArmies.getSelectedRow();

				player.currentGameStaticsList.get(index).infantries++;
				player.infantriesAvailable--;
				player.getPlayerPanel().lbAvailableArmies
						.setText("Available Infantries : " + player.infantriesAvailable);
				player.currentGameStaticsTableModel.fireTableDataChanged();

				player.setMessage("Reinforcement Phase\r\nPlayer - " + player.getName() + " has placed infantry in "
						+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase());
				player.notifyObservers();

				CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
						"Player - " + player.getName() + " has placed infantry in "
								+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase());
				LoggerUtility.consoleHandler.publish(logRecord);

				if (player.infantriesAvailable == 0) {
					// player.getPlayerPanel().btReinforcement.setEnabled(false);
					// player.attack(i);
					return 1;
				}

		}
		}else {
			JOptionPane.showMessageDialog(player.getPlayerPanel(), "No army available");
		}
		return 0;

	}

	@Override
	public int attackStrategy(Player player[],int ii, Player player2, RiskMap mapDetails) {

				String percentageString = "";
				int totalTerritories = mapDetails.getTerritories().size();
				for (int i = 0; i < player.length; i++) {
					double per = (player2.currentGameStaticsList.size() * 100.0) / totalTerritories;
					DecimalFormat df = new DecimalFormat("##.##");
					per = Double.parseDouble(df.format(per));
					if (i < player.length - 1)
						percentageString += per + ",";
					else
						percentageString += per;
				}

				player2.setMessage("Percentage " + percentageString);

				int ans = JOptionPane.showConfirmDialog(player2.getPlayerPanel(),
						"Player - " + (ii + 1) + "\nDo you want to do fortification ?", "Fortification Confirmition",
						JOptionPane.YES_NO_OPTION);

				if (ans == JOptionPane.YES_OPTION) {
					player2.getPlayerPanel().btFortification.setEnabled(true);
					player2.getPlayerPanel().btOk.setEnabled(true);

					player2.setMessage("Player " + (ii + 1) + " entered into Fortification Phase");
					player2.notifyObservers();

					CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
							"Player " + (ii + 1) + " entered into Fortification Phase");
					LoggerUtility.consoleHandler.publish(logRecord);

				} else {
					player2.nextPlayerTurn(ii);
				}
			
	
		return 0;
	}

	@Override
	public int fortificationStrategy() {
		System.out.println("Random Player strategy fortify");
		return 0;
	}
}