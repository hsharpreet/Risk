package game.risk.model.entities.strategy;

import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import game.risk.gui.AttackGUIPanel;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

public class HumanStrategy implements PlayerStrategy , Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

					int ans = JOptionPane.showConfirmDialog(player[i].getPlayerPanel(),
							"Player - " + (i + 1) + "\nDo you want to do fortification ?", "Fortification Confirmition",
							JOptionPane.YES_NO_OPTION);

					if (ans == JOptionPane.YES_OPTION) {
						player[i].getPlayerPanel().btFortification.setEnabled(true);
						player[i].getPlayerPanel().btOk.setEnabled(true);

						player[i].setMessage("Player " + player[i].getName() + " entered into Fortification Phase");
						player[i].notifyObservers();

					} else {
						player[i].nextPlayerTurn(1);
					}
				}
			});
			dialog.setVisible(true);
		}else{
			player2.nextPlayerTurn(1);
		}
		return 0;
	}

	@Override
	public int fortificationStrategy(int i, Player human, int army) {

		int tableIndex = human.getPlayerPanel().jtCountriesAndArmies.getSelectedRow();
		int listIndex = human.getPlayerPanel().lsNeighbour.getSelectedIndex();
		
		ArrayList<String> terrList = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
		
		for(int j=0; j< human.currentGameStaticsList.size(); j++){
			list.add(human.currentGameStaticsList.get(j).territory.getName());
		}
		
		for(int j=0; j< list.size(); j++){

			for(int jj=0; jj< human.currentGameStaticsList.get(j).territory.getNeighbouringTerritories().size(); jj++){
				String destinationTerritory = human.currentGameStaticsList.get(j).territory.getNeighbouringTerritories().get(jj);
				if(list.contains(destinationTerritory) && human.currentGameStaticsList.get(j).infantries >1){
					terrList.add(j+":"+jj);
				}
			}
		}
		int possibleMoves = terrList.size();
		
		System.out.println("HUMAN "+possibleMoves+" - "+terrList+" - "+list.size());

		if (tableIndex == -1) {
			JOptionPane.showMessageDialog(human.getPlayerPanel(), "Select source territory first");
		} else if (listIndex == -1) {
			JOptionPane.showMessageDialog(human.getPlayerPanel(), "Select destination territory first");
		} else {
			String destinationTerritory = human.getPlayerPanel().lsNeighbour.getSelectedValue();
			boolean isDestinationMyOwnCountry = false;
			 
			
			for (int j = 0; j < human.currentGameStaticsList.size(); j++) {
				if (human.currentGameStaticsList.get(j).territory.getName().equals(destinationTerritory)) {
					isDestinationMyOwnCountry = true;
					break;
				}
			}
			if (isDestinationMyOwnCountry) {
				if (human.currentGameStaticsList.get(tableIndex).infantries > 1) {

					human.currentGameStaticsList.get(tableIndex).infantries--;

					for (int j = 0; j < human.currentGameStaticsList.size(); j++) {
						if (human.currentGameStaticsList.get(j).territory.getName().equals(destinationTerritory)) {
							human.currentGameStaticsList.get(j).infantries++;
							human.setMessage(
									"Fortification Phase\r\nPlayer - " + (i + 1) + " has transfered 1 infantry from "
											+ human.currentGameStaticsList.get(tableIndex).territory.getName()
											+ " to " + destinationTerritory);
							human.notifyObservers();

							CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
									"Player - " + (i + 1) + " has transfered 1 infantry from "
											+ human.currentGameStaticsList.get(tableIndex).territory.getName()
											+ " to " + destinationTerritory);
							LoggerUtility.consoleHandler.publish(logRecord);

							human.currentGameStaticsTableModel.fireTableDataChanged();
							break;
						}
					}
				} else {
					JOptionPane.showMessageDialog(human.getPlayerPanel(),
							"Source territory must have more that 1 infantries for fortification");
				}
			} else {
				JOptionPane.showMessageDialog(human.getPlayerPanel(),
						"Destination territory must be your territory");
			}
		}
	
		return 0;
	}

}