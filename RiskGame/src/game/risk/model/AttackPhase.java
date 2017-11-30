package game.risk.model;

import game.risk.gui.AttackGUIPanel;
import game.risk.model.entities.CurrentGameStatics;
import game.risk.model.entities.CurrentGameStaticsTableModel;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.model.entities.TempGameStatics;
import game.risk.model.entities.TempTableModel;
import game.risk.model.entities.Territory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.concurrent.*;
import javax.swing.JDialog;

/**
 * A class to create the attack logic
 * 
 * @author Team
 *
 */
public class AttackPhase {

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
	 *            an object of RiskMap Class
	 */
	public AttackPhase(JDialog dialog, AttackGUIPanel attackPanel, Player[] player, int myIndex,
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

		if (!player[myIndex].getName().toLowerCase().contains("random") && player[myIndex].isComputer()) {
			player[myIndex].setMessage("Computer - " + player[myIndex].getName() + " I don't want to attack");
			System.out.println("I don't want to attack");
			return;
		}

		if (player[myIndex].isComputer()) {

			ArrayList<String> attackList = new ArrayList<String>();

			for (int x = 0; x < list.size(); x++) {
				if (list.get(x).infantries > 1) {
					// attacker.add(x);

					getIndexFromMainTable(list, x);
					for (int xx = 0; xx < tempGameStaticsList.size(); xx++) {
						if (!tempGameStaticsList.get(xx).isOwn) {
							attackList.add(x + ":" + xx);
						}
					}
				}
			}
			int noOfAttacks = 0;
			if (attackList.size() < 1) {
				return;
			} else {
				noOfAttacks = new Random().nextInt(attackList.size());
			}

			player[myIndex].setMessage("Computer - " + player[myIndex].getName() + " into attack phase, will do "
					+ noOfAttacks + " random attacks, out of " + attackList.size() + " possible attacks");

			for (int i = 0; i < noOfAttacks;) {
				int choice = new Random().nextInt(attackList.size());

				if (attackList.size() != 0) {
					int index11 = Integer.valueOf(attackList.get(choice).split(":")[0]);
					int index22 = Integer.valueOf(attackList.get(choice).split(":")[1]);
					this.index1 = index11;
					this.index2 = index22;

					AttackPhase.this.tempGameStaticsList.clear();
					getIndexFromMainTable(list, index11);

					if (checkIfMoreAttackPossible()) {
						try {
							automateAttackButton(list, index11, index22);
							updateComboboxes();
							performBtRoleDiceClick(list, index11, index22);
							TimeUnit.MILLISECONDS.sleep(200);
							attackList.remove(choice);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						i++;
					} else {
						return;
					}
				} else {
					return;
				}

				updateOriginalListFromTempList();
				AttackPhase.this.dialog.dispose();

				player[myIndex].notifyObservers();

				player[myIndex].setMessage("Player - " + player[myIndex].getName() + " into attack phase");
			}
		}
		/**
		 * Method for creating listeners for attack Panel
		 */
		this.attackPanel.jtMain.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {

					int index = AttackPhase.this.attackPanel.jtMain.getSelectedRow();
					if (index != -1) {
						AttackPhase.this.tempGameStaticsList.clear();
						updateTempList(index);
						AttackPhase.this.attackPanel.lbSelectedCountryPlayer1
								.setText(list.get(index).territory.getName());
						AttackPhase.this.attackPanel.cbplayer1.removeAllItems();
						AttackPhase.this.attackPanel.cbplayer2.removeAllItems();
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
					index1 = AttackPhase.this.attackPanel.jtMain.getSelectedRow();
					index2 = AttackPhase.this.attackPanel.jtOther.getSelectedRow();

					if (index1 == -1 || index2 == -1) {
						JOptionPane.showMessageDialog(AttackPhase.this.attackPanel,
								"Please select both SOURCE and DESTINATION");
					} else {
						AttackPhase.this.attackPanel.lbplayer1.setText("Player - " + player[myIndex].getName());

						if (tempGameStaticsList.get(index2).isOwn) {
							JOptionPane.showMessageDialog(AttackPhase.this.attackPanel,
									"DESTINATION is your own territory.\nYou can't attack on your own territory");
						} else if (list.get(index1).infantries == 1) {
							JOptionPane.showMessageDialog(AttackPhase.this.attackPanel,
									"SOURCE has only 1 infantry. So can't attack from this territory");
						} else {

							player[myIndex].setMessage("Attack Phase\r\nPlayer - " + player[myIndex].getName()
									+ " is going to attack from " + list.get(index1).territory.getName().toUpperCase()
									+ " to " + tempGameStaticsList.get(index2).territory.getName().toUpperCase());
							player[myIndex].notifyObservers();

							// Update Combo boxes
							updateComboboxes();

							AttackPhase.this.attackPanel.lbInfantriesPlayer1.setText(list.get(index1).infantries + "");
							AttackPhase.this.attackPanel.lbInfantriesPlayer2
									.setText(tempGameStaticsList.get(index2).infantries + "");

							AttackPhase.this.attackPanel.btAttack.setEnabled(false);
							AttackPhase.this.attackPanel.btCloseAttackPhase.setEnabled(false);
							AttackPhase.this.attackPanel.btRoleDice.setEnabled(true);
						}
					}
				} else {

					JOptionPane.showMessageDialog(attackPanel,
							"Your each territory has only 1 infantry.\nSo no more attach possible now");
					AttackPhase.this.dialog.dispose();
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
					int index = AttackPhase.this.attackPanel.jtOther.getSelectedRow();
					if (index != -1) {
						attackPanel.lbplayer2.setText("Player - " + (tempGameStaticsList.get(index).player + 1));
						attackPanel.lbSelectedCountryPlayer2
								.setText(tempGameStaticsList.get(index).territory.getName());
						AttackPhase.this.attackPanel.cbplayer1.removeAllItems();
						AttackPhase.this.attackPanel.cbplayer2.removeAllItems();
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
				int n1 = Integer.parseInt(AttackPhase.this.attackPanel.cbplayer1.getSelectedItem().toString());
				int n2 = Integer.parseInt(AttackPhase.this.attackPanel.cbplayer2.getSelectedItem().toString());
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

				AttackPhase.this.attackPanel.lbDiceResultsPlayer1.setText(s1);
				AttackPhase.this.attackPanel.lbDiceResultsPlayer2.setText(s2);

				int smallArrayLength = diceValuesPlayer1.length < diceValuesPlayer2.length ? diceValuesPlayer1.length
						: diceValuesPlayer2.length;

				for (int i = 0; i < smallArrayLength; i++) {
					if (diceValuesPlayer1[i] > diceValuesPlayer2[i]) {

						tempGameStaticsList.get(index2).infantries--;
						AttackPhase.this.attackPanel.lbInfantriesPlayer2
								.setText(tempGameStaticsList.get(index2).infantries + "");

						if (tempGameStaticsList.get(index2).infantries == 0) {

							// remove from old list and add this to current
							// player's list
							for (int a = 0; a < player.length; a++) {
								for (int b = 0; b < player[a].currentGameStaticsList.size(); b++) {
									if (player[a].currentGameStaticsList.get(b).territory.getName()
											.equals(tempGameStaticsList.get(index2).territory.getName())) {
										player[a].currentGameStaticsList.get(b).player = AttackPhase.this.myIndex;
										player[a].currentGameStaticsList.get(b).infantries = n1;
										AttackPhase.this.currentGameStaticsList.get(index1).infantries -= n1;
										player[myIndex].currentGameStaticsList
												.add(player[a].currentGameStaticsList.get(b));
										player[a].currentGameStaticsList.remove(b);
										// break;
									}
								}
							}

							// Assign Card to player
							for (int p = 0; p < player[myIndex].riskGame.alCards.size(); p++) {
								if (player[myIndex].riskGame.alCards.get(p).getPlayer() == -1) {
									player[myIndex].riskGame.alCards.get(p).setPlayer(myIndex);
									break;
								}
							}

							player[myIndex].setMessage("Player - " + player[myIndex].getName() + " won and placed " + n1
									+ " infantries in " + (tempGameStaticsList.get(index2).territory.getName()));

							JOptionPane.showMessageDialog(AttackPhase.this.attackPanel,
									"Player - " + player[myIndex].getName() + " won and placed " + n1
											+ " infantries in "
											+ (tempGameStaticsList.get(index2).territory.getName()));

							AttackPhase.this.attackPanel.btAttack.setEnabled(true);
							AttackPhase.this.attackPanel.btCloseAttackPhase.setEnabled(true);
							AttackPhase.this.attackPanel.btRoleDice.setEnabled(false);

							tempGameStaticsList.remove(index2);
							AttackPhase.this.tempTableModel.fireTableDataChanged();
							AttackPhase.this.currentGameStaticsTableModel.fireTableDataChanged();
							break;
						}
					} else {
						list.get(index1).infantries--;
						AttackPhase.this.attackPanel.lbInfantriesPlayer1.setText(list.get(index1).infantries + "");
						if (list.get(index1).infantries == 1) {
							player[myIndex].setMessage("Player - " + player[myIndex].getName()
									+ " has left only 1 infantry, so no more dice rolling possible for this territory");

							JOptionPane.showMessageDialog(AttackPhase.this.attackPanel, "Player - "
									+ player[myIndex].getName()
									+ " has left only 1 infantry, so no more dice rolling possible for this territory");
							AttackPhase.this.attackPanel.btAttack.setEnabled(true);
							AttackPhase.this.attackPanel.btCloseAttackPhase.setEnabled(true);
							AttackPhase.this.attackPanel.btRoleDice.setEnabled(false);
							AttackPhase.this.tempTableModel.fireTableDataChanged();
							AttackPhase.this.currentGameStaticsTableModel.fireTableDataChanged();
							break;
						}
					}
				}
				AttackPhase.this.tempTableModel.fireTableDataChanged();
				AttackPhase.this.currentGameStaticsTableModel.fireTableDataChanged();

				if (!checkIfMoreAttackPossible()) {
					player[myIndex]
							.setMessage("Your each territory has only 1 infantry.\nSo no more attach possible now");
					AttackPhase.this.dialog.dispose();
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
				AttackPhase.this.dialog.dispose();
			}
		});

		player[myIndex].setMessage("Player - " + player[myIndex].getName() + " FINISHED with attack phase");
		player[myIndex].notifyObservers();

	}

	/**
	 * Method to get index from the Main table
	 * 
	 * @param list
	 *            an array list
	 * @param index11
	 *            index number
	 * @return an integer value
	 */
	private int getIndexFromMainTable(List<CurrentGameStatics> list, int index11) {

		if (index11 != -1) {
			AttackPhase.this.tempGameStaticsList.clear();
			updateTempList(index11);
			AttackPhase.this.attackPanel.cbplayer1.removeAllItems();
			AttackPhase.this.attackPanel.cbplayer2.removeAllItems();
		}
		return index11 < 0 ? 0 : index11;
	}

	/**
	 * Method to get index from other table
	 * 
	 * @param list
	 *            an array list
	 * @param index22
	 *            index number
	 * @return an integer value
	 */
	private int getIndexFromOtherTable(List<CurrentGameStatics> list, int index22) {
		if (index22 != -1) {
			AttackPhase.this.attackPanel.cbplayer1.removeAllItems();
			AttackPhase.this.attackPanel.cbplayer2.removeAllItems();
		}

		return index22;
	}

	/**
	 * Method to automate the attack button
	 * 
	 * @param list
	 *            an array list
	 * @param index1
	 *            index number
	 * @param index2
	 *            index number
	 */
	private void automateAttackButton(List<CurrentGameStatics> list, int index1, int index2) {

		AttackPhase.this.attackPanel.lbplayer1.setText("Player - " + player[myIndex].getName());

		String msg = "Attack Phase\r\nPlayer - " + player[myIndex].getName() + " is going to attack from "
				+ list.get(index1).territory.getName().toUpperCase() + " with " + list.get(index1).infantries + " to "
				+ tempGameStaticsList.get(index2).territory.getName().toUpperCase() + " with "
				+ tempGameStaticsList.get(index2).infantries;
		player[myIndex].setMessage(msg);
		player[myIndex].notifyObservers();

		AttackPhase.this.attackPanel.lbInfantriesPlayer1.setText(list.get(index1).infantries + "");
		AttackPhase.this.attackPanel.lbInfantriesPlayer2.setText(tempGameStaticsList.get(index2).infantries + "");
	}

	/**
	 * Method to create button for rolling the dice
	 * 
	 * @param list
	 *            an array list
	 * @param index1
	 *            index number
	 * @param index2
	 *            index number
	 */
	private void performBtRoleDiceClick(List<CurrentGameStatics> list, int index1, int index2) {

		int n1 = AttackPhase.this.attackPanel.cbplayer1.getItemCount();
		int n2 = AttackPhase.this.attackPanel.cbplayer2.getItemCount();

		Integer[] diceValuesPlayer1 = new Integer[1];
		Integer[] diceValuesPlayer2 = new Integer[1];

		String s1 = "";
		String s2 = "";

		for (int i = 0; i < diceValuesPlayer1.length; i++) {
			diceValuesPlayer1[i] = ThreadLocalRandom.current().nextInt(1, 6 + 1);
			s1 += diceValuesPlayer1[i] + "  ";
		}

		for (int i = 0; i < diceValuesPlayer2.length; i++) {
			diceValuesPlayer2[i] = ThreadLocalRandom.current().nextInt(1, 6 + 1);
			s2 += diceValuesPlayer2[i] + "  ";
		}

		Arrays.sort(diceValuesPlayer1, Collections.reverseOrder());
		Arrays.sort(diceValuesPlayer2, Collections.reverseOrder());

		AttackPhase.this.attackPanel.lbDiceResultsPlayer1.setText(s1);
		AttackPhase.this.attackPanel.lbDiceResultsPlayer2.setText(s2);

		player[myIndex].setMessage("Dice values player1: " + s1 + " & player2: " + s2);

		int smallArrayLength = diceValuesPlayer1.length < diceValuesPlayer2.length ? diceValuesPlayer1.length
				: diceValuesPlayer2.length;

		for (int i = 0; i < 1; i++) {
			String attacker = currentGameStaticsList.get(index1).territory.getName();
			String underAttack = tempGameStaticsList.get(index2).territory.getName();
			if (diceValuesPlayer1[i] > diceValuesPlayer2[i]) {

				tempGameStaticsList.get(index2).infantries--;
				AttackPhase.this.attackPanel.lbInfantriesPlayer2
						.setText(tempGameStaticsList.get(index2).infantries + "");

				player[myIndex].setMessage("Player - " + player[myIndex].getName() + " won and placed " + n1
						+ " infantries on " + (tempGameStaticsList.get(index2).territory.getName()));

				if (tempGameStaticsList.get(index2).infantries == 0) {

					// remove from old list and add this to current player's
					// list
					for (int a = 0; a < player.length; a++) {
						for (int b = 0; b < player[a].currentGameStaticsList.size(); b++) {
							if (player[a].currentGameStaticsList.get(b).territory.getName()
									.equals(tempGameStaticsList.get(index2).territory.getName())) {
								player[a].currentGameStaticsList.get(b).player = AttackPhase.this.myIndex;
								player[a].currentGameStaticsList.get(b).infantries = n1;
								AttackPhase.this.currentGameStaticsList.get(index1).infantries -= n1;
								player[myIndex].currentGameStaticsList.add(player[a].currentGameStaticsList.get(b));
								player[a].currentGameStaticsList.remove(b);
								break;
							}
						}
						break;
					}

					// Assign Card to player
					for (int p = 0; p < player[myIndex].riskGame.alCards.size(); p++) {
						if (player[myIndex].riskGame.alCards.get(p).getPlayer() == -1) {
							player[myIndex].riskGame.alCards.get(p).setPlayer(myIndex);
							break;
						}
					}

					tempGameStaticsList.remove(index2);
					break;
				}
			} else {
				currentGameStaticsList.get(index1).infantries--;
				tempGameStaticsList.get(index2).infantries++;

				player[myIndex].setMessage("Player - " + player[myIndex].getName() + " LOST " + n1 + " infantries to "
						+ (tempGameStaticsList.get(index2).territory.getName()));

				if (list.get(index1).infantries == 1) {
					player[myIndex].setMessage("Player - " + player[myIndex].getName()
							+ " has left only 1 infantry, so no more dice rolling possible for this territory");
					break;
				}
				break;
			}

		}
		AttackPhase.this.tempTableModel.fireTableDataChanged();
		AttackPhase.this.currentGameStaticsTableModel.fireTableDataChanged();
	}

	/**
	 * a method to update the comboboxes containing the player options for dice
	 */
	public void updateComboboxes() {
		AttackPhase.this.attackPanel.cbplayer1.removeAllItems();
		AttackPhase.this.attackPanel.cbplayer2.removeAllItems();
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
			AttackPhase.this.attackPanel.cbplayer1.addItem(i + "");
		}
		for (int i = 1; i <= player2OptionsForDice; i++) {
			AttackPhase.this.attackPanel.cbplayer2.addItem(i + "");
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
						// System.out.println(cgs.territory.getName() + " -cgs-
						// "+ cgs.infantries);
						// System.out.println(tgs.territory.getName() + " -tgs-
						// "+ tgs.infantries);
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
			AttackPhase.this.attackPanel.btAttack.setEnabled(false);
			AttackPhase.this.attackPanel.btCloseAttackPhase.setEnabled(false);
			AttackPhase.this.attackPanel.btRoleDice.setEnabled(false);

		}
		return flag;
	}

}
