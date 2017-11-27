package game.risk.model.entities.strategy;

import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.logging.Level;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import game.risk.gui.AttackGUIPanel;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

public class HumanStrategy implements PlayerStrategy {

	public HumanStrategy() {
		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
				"Strategy: Human Strategy");
		LoggerUtility.consoleHandler.publish(logRecord);
	}
	@Override
	public int placeInfantoryStrategy(int i, Player player, int army) {
		
		int index = player.getPlayerPanel().jtCountriesAndArmies.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(player.getPlayerPanel(), "Select terriotary first");
		} else {

			player.currentGameStaticsList.get(index).infantries++;
			player.infantriesAvailable--;
			player.getPlayerPanel().lbAvailableArmies.setText("Available Infantries : " + player.infantriesAvailable);
			player.currentGameStaticsTableModel.fireTableDataChanged();
			player.getPlayerPanel().btPlaceInfantry.setEnabled(false);
			// player.nextIndexToEnableButton(i);

			player.setMessage("Startup Phase\r\nPlayer - " + player.getName() + " has placed infantry in "
					+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase()
					+ " and turn switched to next player");
			player.notifyObservers();

			CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
					"Player - " + (i + 1) + " has placed infantry in "
							+ player.currentGameStaticsList.get(index).territory.getName().toUpperCase()
							+ " and turn switched to next player");
			LoggerUtility.consoleHandler.publish(logRecord);

		}

		return 0;
	}

	@Override
	public int reinforcementStrategy(int i, Player player, int army) {
		if (player.infantriesAvailable > 0) {
			int index = player.getPlayerPanel().jtCountriesAndArmies.getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(player.getPlayerPanel(), "Select terriotary first");
			} else {
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
					//player.getPlayerPanel().btReinforcement.setEnabled(false);
					//player.attack(i);
					return 1;
				}

			}
		} else {
			JOptionPane.showMessageDialog(player.getPlayerPanel(), "No army available");

		}
		return 0;
	}

	@Override
	public int attackStrategy(Player[] player, int i, Player player2, RiskMap mapDetails) {
		int ans = JOptionPane.showConfirmDialog(player[i].getPlayerPanel(),
				"Player : " + player[i].getName() + "\nDo you want to do attack ?", "Attack Confirmition",
				JOptionPane.YES_NO_OPTION);
		if (ans == JOptionPane.YES_OPTION) {

			JDialog dialog = new JDialog();
			dialog.add(new AttackGUIPanel(dialog, player, i, player[i].currentGameStaticsTableModel,
					player[i].currentGameStaticsList, mapDetails));
			dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			dialog.setSize(1020, 600);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					player2.currentGameStaticsTableModel.fireTableDataChanged();

					String percentageString = "";
					int totalTerritories = mapDetails.getTerritories().size();
					for (int i = 0; i < player.length; i++) {
						double per = (player[i].currentGameStaticsList.size() * 100.0) / totalTerritories;
						DecimalFormat df = new DecimalFormat("##.##");
						per = Double.parseDouble(df.format(per));
						if (i < player.length - 1)
							percentageString += per + ",";
						else
							percentageString += per;
					}

					player2.setMessage("Percentage " + percentageString);

					int ans = JOptionPane.showConfirmDialog(player[i].getPlayerPanel(),
							"Player - " + (i + 1) + "\nDo you want to do fortification ?", "Fortification Confirmition",
							JOptionPane.YES_NO_OPTION);

					if (ans == JOptionPane.YES_OPTION) {
						player[i].getPlayerPanel().btFortification.setEnabled(true);
						player[i].getPlayerPanel().btOk.setEnabled(true);

						player[i].setMessage("Player " + (i + 1) + " entered into Fortification Phase");
						player[i].notifyObservers();

						CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
								"Player " + (i + 1) + " entered into Fortification Phase");
						LoggerUtility.consoleHandler.publish(logRecord);

					} else {
						player[i].nextPlayerTurn(i);
					}
				}
			});
			dialog.setVisible(true);
		}
		return 0;
	}

	@Override
	public int fortificationStrategy() {
		// TODO Auto-generated method stub
		return 0;
	}

}
