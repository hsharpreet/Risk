package game.risk.model;

import game.risk.util.TempGameStatics;
import game.risk.util.Territory;
import game.risk.util.TempTableModel;
import game.risk.util.CurrentGameStaticsTableModel;
import game.risk.util.CurrentGameStatics;
import game.risk.gui.AttackGUIPanel;
import game.risk.util.LoggerUtility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.concurrent.*;
import java.util.logging.Level;
import game.risk.util.CustomLogRecord;
import javax.swing.JDialog;

/**
 * A class to create the attack logic
 * 
 * @author Team
 *
 */
public class AttackLogic {

	JDialog dialog;
	Player[] player;
	int myIndex;
	CurrentGameStaticsTableModel currentGameStaticsTableModel;
	List<TempGameStatics> tempGameStaticsList;
	AttackGUIPanel attackPanel;
	TempTableModel tempTableModel;
	RiskMap mapDetails;
	List<CurrentGameStatics> currentGameStaticsList;

	int index1, index2;

	/**
	 * A constructor to initialize attributes of the class
	 * 
	 * @param dialog
	 *            a Jdialog swing component
	 * @param attackPanel
	 *            an object of AttackGuiPanel class
	 * @param player
	 *            an array of Player class
	 * @param myIndex
	 *            an integer
	 * @param tm
	 *            an object of CurrentGameStaticsTableModel class
	 * @param list
	 *            an arrayList of CurrentGameStatics class
	 * @param mapDetails
	 *            an object of RiskMap Clas
	 */
	public AttackLogic(JDialog dialog, AttackGUIPanel attackPanel, Player[] player, int myIndex,
			CurrentGameStaticsTableModel tm, List<CurrentGameStatics> list, RiskMap mapDetails) {
		this.dialog = dialog;
		this.mapDetails = mapDetails;
		this.attackPanel = attackPanel;
		this.player = player;
		this.myIndex = myIndex;
		this.currentGameStaticsTableModel = tm;
		this.currentGameStaticsList = list;
		this.tempGameStaticsList = new ArrayList<>();
		this.attackPanel.jtMain.setModel(this.currentGameStaticsTableModel);
		this.tempTableModel = new TempTableModel(tempGameStaticsList);
		this.attackPanel.jtOther.setModel(tempTableModel);
		this.attackPanel.btRoleDice.setEnabled(false);
		/**
		 * A listener for main jtable
		 */
		this.attackPanel.jtMain.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {

					int index = AttackLogic.this.attackPanel.jtMain.getSelectedRow();
					if (index != -1) {
						AttackLogic.this.tempGameStaticsList.clear();
						updateTempList(index);
						AttackLogic.this.attackPanel.lbSelectedCountryPlayer1
								.setText(list.get(index).territory.getName());
						AttackLogic.this.attackPanel.cbplayer1.removeAllItems();
						AttackLogic.this.attackPanel.cbplayer2.removeAllItems();
					}
				}
			}
		});
		/**
		 * A listener for Attack Button
		 */
		this.attackPanel.btAttack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (checkIfMoreAttackPossible()) {
					index1 = AttackLogic.this.attackPanel.jtMain.getSelectedRow();
					index2 = AttackLogic.this.attackPanel.jtOther.getSelectedRow();

					if (index1 == -1 || index2 == -1) {
						JOptionPane.showMessageDialog(AttackLogic.this.attackPanel,
								"Please select both SOURCE and DESTINATION");
					} else {
						AttackLogic.this.attackPanel.lbplayer1.setText("Player - " + (myIndex + 1));

						if (tempGameStaticsList.get(index2).isOwn) {
							JOptionPane.showMessageDialog(AttackLogic.this.attackPanel,
									"DESTINATION is your own territory.\nYou can't attack on your own territory");
						} else if (list.get(index1).infantries == 1) {
							JOptionPane.showMessageDialog(AttackLogic.this.attackPanel,
									"SOURCE has only 1 infantry. So can't attack from this territory");
						} else {

							player[myIndex].setMessage("Attack Phase\r\nPlayer - " + (myIndex + 1)
									+ " is going to attack from " + list.get(index1).territory.getName().toUpperCase()
									+ " to " + tempGameStaticsList.get(index2).territory.getName().toUpperCase());
							player[myIndex].notifyObservers();

							CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
									"Player - " + (myIndex + 1) + " is going to attack from "
											+ list.get(index1).territory.getName().toUpperCase() + " to "
											+ tempGameStaticsList.get(index2).territory.getName().toUpperCase());
							LoggerUtility.consoleHandler.publish(logRecord);

							// Update Comboboxes
							updateComboboxes();

							AttackLogic.this.attackPanel.lbInfantriesPlayer1.setText(list.get(index1).infantries + "");
							AttackLogic.this.attackPanel.lbInfantriesPlayer2
									.setText(tempGameStaticsList.get(index2).infantries + "");

							AttackLogic.this.attackPanel.btAttack.setEnabled(false);
							AttackLogic.this.attackPanel.btCloseAttackPhase.setEnabled(false);
							AttackLogic.this.attackPanel.btRoleDice.setEnabled(true);
						}
					}
				} else {

					JOptionPane.showMessageDialog(attackPanel,
							"Your each territory has only 1 infantry.\nSo no more attach possible now");
					AttackLogic.this.dialog.dispose();
				}

			}
		});
		/**
		 * a listener for other Jtable
		 */
		this.attackPanel.jtOther.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					int index = AttackLogic.this.attackPanel.jtOther.getSelectedRow();
					if (index != -1) {
						attackPanel.lbplayer2.setText("Player - " + (tempGameStaticsList.get(index).player + 1));
						attackPanel.lbSelectedCountryPlayer2
								.setText(tempGameStaticsList.get(index).territory.getName());
						AttackLogic.this.attackPanel.cbplayer1.removeAllItems();
						AttackLogic.this.attackPanel.cbplayer2.removeAllItems();
					}
				}
			}
		});
		/**
		 * A listener for RoleDice button
		 */
		this.attackPanel.btRoleDice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (checkIfMoreAttackPossible()) {

				} else {

				}
				int n1 = Integer.parseInt(AttackLogic.this.attackPanel.cbplayer1.getSelectedItem().toString());
				int n2 = Integer.parseInt(AttackLogic.this.attackPanel.cbplayer2.getSelectedItem().toString());
				// arrays to store dice role number for player
				int diceValuesPlayer1[] = new int[n1];
				int diceValuesPlayer2[] = new int[n2];

				for (int i = 0; i < diceValuesPlayer1.length; i++) {
					diceValuesPlayer1[i] = ThreadLocalRandom.current().nextInt(1, 6 + 1);
				}

				for (int i = 0; i < diceValuesPlayer2.length; i++) {
					diceValuesPlayer2[i] = ThreadLocalRandom.current().nextInt(1, 6 + 1);
				}

				for (int i = 0; i < diceValuesPlayer1.length; i++) {
					diceValuesPlayer1[i] *= -1;
				}

				for (int i = 0; i < diceValuesPlayer2.length; i++) {
					diceValuesPlayer2[i] *= -1;
				}

				Arrays.sort(diceValuesPlayer1);
				Arrays.sort(diceValuesPlayer2);

				for (int i = 0; i < diceValuesPlayer1.length; i++) {
					diceValuesPlayer1[i] *= -1;
				}

				for (int i = 0; i < diceValuesPlayer2.length; i++) {
					diceValuesPlayer2[i] *= -1;
				}

				String s1 = "";
				String s2 = "";

				for (int i = 0; i < diceValuesPlayer1.length; i++) {
					s1 += diceValuesPlayer1[i] + "  ";
				}

				for (int i = 0; i < diceValuesPlayer2.length; i++) {
					s2 += diceValuesPlayer2[i] + "  ";
				}

				AttackLogic.this.attackPanel.lbDiceResultsPlayer1.setText(s1);
				AttackLogic.this.attackPanel.lbDiceResultsPlayer2.setText(s2);

				int smallArrayLength = diceValuesPlayer1.length < diceValuesPlayer2.length ? diceValuesPlayer1.length
						: diceValuesPlayer2.length;

				for (int i = 0; i < smallArrayLength; i++) {
					if (diceValuesPlayer1[i] > diceValuesPlayer2[i]) {

						tempGameStaticsList.get(index2).infantries--;
						AttackLogic.this.attackPanel.lbInfantriesPlayer2
								.setText(tempGameStaticsList.get(index2).infantries + "");

						if (tempGameStaticsList.get(index2).infantries == 0) {

							// remove from old list and add this to current player's list
							for (int a = 0; a < player.length; a++) {
								for (int b = 0; b < player[a].currentGameStaticsList.size(); b++) {
									if (player[a].currentGameStaticsList.get(b).territory.getName()
											.equals(tempGameStaticsList.get(index2).territory.getName())) {
										player[a].currentGameStaticsList.get(b).player = AttackLogic.this.myIndex;
										player[a].currentGameStaticsList.get(b).infantries = n1;
										AttackLogic.this.currentGameStaticsList.get(index1).infantries -= n1;
										player[myIndex].currentGameStaticsList
												.add(player[a].currentGameStaticsList.get(b));
										player[a].currentGameStaticsList.remove(b);
										//break;
									}
								}
							}
							
							// Assign Card to player
                            for (int p = 0; p < player[myIndex].riskGame.alCards.size(); p++)
                            {
                                if (player[myIndex].riskGame.alCards.get(p).getPlayer() == -1)
                                {
                                    player[myIndex].riskGame.alCards.get(p).setPlayer(myIndex);
                                    break;
                                }
                            }

							CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
									"Player - " + (myIndex + 1) + " won and placed " + n1 + " infantries in "
											+ (tempGameStaticsList.get(index2).territory.getName()));
							LoggerUtility.consoleHandler.publish(logRecord);

							JOptionPane.showMessageDialog(AttackLogic.this.attackPanel,
									"Player - " + (myIndex + 1) + " won and placed " + n1 + " infantries in "
											+ (tempGameStaticsList.get(index2).territory.getName()));

							AttackLogic.this.attackPanel.btAttack.setEnabled(true);
							AttackLogic.this.attackPanel.btCloseAttackPhase.setEnabled(true);
							AttackLogic.this.attackPanel.btRoleDice.setEnabled(false);

							tempGameStaticsList.remove(index2);
							AttackLogic.this.tempTableModel.fireTableDataChanged();
							AttackLogic.this.currentGameStaticsTableModel.fireTableDataChanged();
							break;
						}
					} else {
						list.get(index1).infantries--;
						AttackLogic.this.attackPanel.lbInfantriesPlayer1.setText(list.get(index1).infantries + "");
						if (list.get(index1).infantries == 1) {
							CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Player - " + (myIndex + 1)
									+ " has left only 1 infantry, so no more dice rolling possible for this territory");
							LoggerUtility.consoleHandler.publish(logRecord);

							JOptionPane.showMessageDialog(AttackLogic.this.attackPanel, "Player - " + (myIndex + 1)
									+ " has left only 1 infantry, so no more dice rolling possible for this territory");
							AttackLogic.this.attackPanel.btAttack.setEnabled(true);
							AttackLogic.this.attackPanel.btCloseAttackPhase.setEnabled(true);
							AttackLogic.this.attackPanel.btRoleDice.setEnabled(false);
							AttackLogic.this.tempTableModel.fireTableDataChanged();
							AttackLogic.this.currentGameStaticsTableModel.fireTableDataChanged();
							break;
						}
					}
					updateComboboxes();
				}
				AttackLogic.this.tempTableModel.fireTableDataChanged();
				AttackLogic.this.currentGameStaticsTableModel.fireTableDataChanged();

				if (!checkIfMoreAttackPossible()) {
					JOptionPane.showMessageDialog(attackPanel,
							"Your each territory has only 1 infantry.\nSo no more attach possible now");
					AttackLogic.this.dialog.dispose();
				}

			}
		});
		/**
		 * A listener for CloseAttackPhase button
		 */
		this.attackPanel.btCloseAttackPhase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateOriginalListFromTempList();
				AttackLogic.this.dialog.dispose();
			}
		});

		player[myIndex].setMessage("Player - " + (myIndex + 1) + " into attack phase");
		player[myIndex].notifyObservers();

		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Player - " + (myIndex + 1) + " into attack phase");
		LoggerUtility.consoleHandler.publish(logRecord);

	}

	/**
	 * a method to update the comboboxes containing the player options for dice
	 */
	public void updateComboboxes() {
		AttackLogic.this.attackPanel.cbplayer1.removeAllItems();
		AttackLogic.this.attackPanel.cbplayer2.removeAllItems();
		int player1OptionsForDice, player2OptionsForDice;

		if (currentGameStaticsList.get(index1).infantries == 2) {
			player1OptionsForDice = 1;
		} else if (currentGameStaticsList.get(index1).infantries == 3) {
			player1OptionsForDice = 2;
		} else {
			player1OptionsForDice = 3;
		}

		if (tempGameStaticsList.get(index2).infantries == 1) {
			player2OptionsForDice = 1;
		} else {
			player2OptionsForDice = 2;
		}

		for (int i = 1; i <= player1OptionsForDice; i++) {
			AttackLogic.this.attackPanel.cbplayer1.addItem(i + "");
		}
		for (int i = 1; i <= player2OptionsForDice; i++) {
			AttackLogic.this.attackPanel.cbplayer2.addItem(i + "");
		}
	}

	/**
	 * A method to update the TempList which will be used to update the original
	 * list
	 * 
	 * @param index
	 *            an integer variable
	 */
	void updateTempList(int index) {

		HashMap<String, Territory> territories = mapDetails.getTerritories();
		Iterator it = territories.keySet().iterator();
		List<String> ls = player[myIndex].currentGameStaticsList.get(index).territory.getNeighbouringTerritories();
		while (it.hasNext()) {
			try {
				Territory t = territories.get(it.next().toString());
				for (int j = 0; j < ls.size(); j++) {
					if (ls.get(j).equals(t.getName())) {
						TempGameStatics tgs = new TempGameStatics(t);
						tempGameStaticsList.add(tgs);
						break;
					}
				}
			} catch (Exception ex) {
				break;
			}
		}

		for (int i = 0; i < tempGameStaticsList.size(); i++) {
			for (int j = 0; j < player.length; j++) {
				String tn1 = tempGameStaticsList.get(i).territory.getName();
				for (int k = 0; k < player[j].currentGameStaticsList.size(); k++) {
					String tn2 = player[j].currentGameStaticsList.get(k).territory.getName();
					if (tn1.equals(tn2)) {
						tempGameStaticsList.get(i).infantries = player[j].currentGameStaticsList.get(k).infantries;
						tempGameStaticsList.get(i).player = j;

						if (j == myIndex) {
							tempGameStaticsList.get(i).isOwn = true;
						}
						break;
					}
				}
			}
		}

		tempTableModel.fireTableDataChanged();
	}

	/**
	 * A method to update the original list from the Templist
	 */
	void updateOriginalListFromTempList() {
		for (int i = 0; i < player.length; i++) {
			for (int j = 0; j < tempGameStaticsList.size(); j++) {
				for (int k = 0; k < player[i].currentGameStaticsList.size(); k++) {
					CurrentGameStatics cgs = player[i].currentGameStaticsList.get(k);
					TempGameStatics tgs = tempGameStaticsList.get(j);

					if (cgs.territory.getName().equals(tgs.territory.getName())) {
						cgs.infantries = tgs.infantries;
						cgs.player = tgs.player;
					}

				}
			}
		}
	}

	/**
	 * A method to check if more attack is possible by checking the infantries
	 * 
	 * @return flag a boolean variable
	 */
	boolean checkIfMoreAttackPossible() {
		boolean flag = false;
		for (CurrentGameStatics cgs : currentGameStaticsList) {
			if (cgs.infantries >= 1) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			AttackLogic.this.attackPanel.btAttack.setEnabled(false);
			AttackLogic.this.attackPanel.btCloseAttackPhase.setEnabled(false);
			AttackLogic.this.attackPanel.btRoleDice.setEnabled(false);

		}
		return flag;
	}

}
