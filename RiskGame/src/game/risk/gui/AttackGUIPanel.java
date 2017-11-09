
package game.risk.gui;

import game.risk.model.AttackLogic;
import game.risk.model.CurrentGameStatics;
import game.risk.model.CurrentGameStaticsTableModel;
import game.risk.model.RiskMap;
import game.risk.model.Player;

import java.util.List;
import javax.swing.JDialog;

/**
 * A class to create GUI of attack phase
 * 
 * @author Team
 *
 */

public class AttackGUIPanel extends javax.swing.JPanel {

	public AttackGUIPanel(JDialog dialog, Player[] player, int myIndex, CurrentGameStaticsTableModel tm,
			List<CurrentGameStatics> list, RiskMap mapDetails) {
		initComponents();
		lbPlayer.setText("Player : " + (myIndex + 1));
		new AttackLogic(dialog, this, player, myIndex, tm, list, mapDetails);
	}

	/**
	 * A method to create the swing components to create the GUI of the attack phase
	 */
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();// swing components
		jtMain = new javax.swing.JTable();
		lbPlayer = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		btAttack = new javax.swing.JButton();
		jpDiceRolling = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		lbplayer1 = new javax.swing.JLabel();
		lbSelectedCountryPlayer1 = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		lbplayer2 = new javax.swing.JLabel();
		lbSelectedCountryPlayer2 = new javax.swing.JLabel();
		cbplayer1 = new javax.swing.JComboBox<>();
		cbplayer2 = new javax.swing.JComboBox<>();
		btRoleDice = new javax.swing.JButton();
		lbInfantriesPlayer1 = new javax.swing.JLabel();
		lbInfantriesPlayer2 = new javax.swing.JLabel();
		lbDiceResultsPlayer1 = new javax.swing.JLabel();
		lbDiceResultsPlayer2 = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		jtOther = new javax.swing.JTable();
		btCloseAttackPhase = new javax.swing.JButton();

		setLayout(null);

		jtMain.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jtMain.setRowHeight(25);
		jScrollPane1.setViewportView(jtMain);

		add(jScrollPane1);
		jScrollPane1.setBounds(10, 40, 270, 300);

		lbPlayer.setFont(new java.awt.Font("Tahoma", 1, 11));
		lbPlayer.setText("Player : ");
		add(lbPlayer);
		lbPlayer.setBounds(10, 20, 250, 14);

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel1.setText("Neighbour Terrotries");
		add(jLabel1);
		jLabel1.setBounds(290, 20, 290, 14);

		btAttack.setText("Attack");
		add(btAttack);
		btAttack.setBounds(290, 250, 380, 40);

		jpDiceRolling.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		jPanel2.setBackground(new java.awt.Color(255, 102, 102));
		jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 51)));
		jPanel2.setLayout(null);

		lbplayer1.setFont(new java.awt.Font("Tahoma", 1, 12));
		lbplayer1.setForeground(new java.awt.Color(255, 255, 255));
		jPanel2.add(lbplayer1);
		lbplayer1.setBounds(20, 0, 110, 20);

		lbSelectedCountryPlayer1.setFont(new java.awt.Font("Tahoma", 1, 11)); 
		jPanel2.add(lbSelectedCountryPlayer1);
		lbSelectedCountryPlayer1.setBounds(10, 30, 120, 10);

		jPanel4.setBackground(new java.awt.Color(0, 204, 153));
		jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 102)));
		jPanel4.setLayout(null);

		lbplayer2.setFont(new java.awt.Font("Tahoma", 1, 12)); 
		lbplayer2.setForeground(new java.awt.Color(255, 255, 255));
		jPanel4.add(lbplayer2);
		lbplayer2.setBounds(20, 0, 110, 20);

		lbSelectedCountryPlayer2.setFont(new java.awt.Font("Tahoma", 1, 11)); 
		jPanel4.add(lbSelectedCountryPlayer2);
		lbSelectedCountryPlayer2.setBounds(10, 30, 120, 20);

		btRoleDice.setText("Roll Dice");

		lbInfantriesPlayer1.setFont(new java.awt.Font("Tahoma", 1, 11)); 
		lbInfantriesPlayer1.setText("---");

		lbInfantriesPlayer2.setFont(new java.awt.Font("Tahoma", 1, 11)); 
		lbInfantriesPlayer2.setText("---");

		lbDiceResultsPlayer1.setFont(new java.awt.Font("Tahoma", 1, 11)); 
		lbDiceResultsPlayer1.setText("---");

		lbDiceResultsPlayer2.setFont(new java.awt.Font("Tahoma", 1, 11)); 
		lbDiceResultsPlayer2.setText("---");

		javax.swing.GroupLayout jpDiceRollingLayout = new javax.swing.GroupLayout(jpDiceRolling);
		jpDiceRolling.setLayout(jpDiceRollingLayout);
		jpDiceRollingLayout.setHorizontalGroup(jpDiceRollingLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jpDiceRollingLayout.createSequentialGroup().addGroup(jpDiceRollingLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								jpDiceRollingLayout.createSequentialGroup().addContainerGap().addComponent(btRoleDice,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGroup(jpDiceRollingLayout.createSequentialGroup().addGroup(jpDiceRollingLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jpDiceRollingLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												jpDiceRollingLayout.createSequentialGroup().addContainerGap()
														.addComponent(cbplayer1, javax.swing.GroupLayout.PREFERRED_SIZE,
																80, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(jpDiceRollingLayout.createSequentialGroup().addGap(78, 78, 78)
												.addComponent(lbInfantriesPlayer1,
														javax.swing.GroupLayout.PREFERRED_SIZE, 47,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGroup(jpDiceRollingLayout.createSequentialGroup().addGap(9, 9, 9).addComponent(
										jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGroup(jpDiceRollingLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDiceRollingLayout
												.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
												.addComponent(lbInfantriesPlayer2,
														javax.swing.GroupLayout.PREFERRED_SIZE, 29,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(59, 59, 59))
										.addGroup(jpDiceRollingLayout.createSequentialGroup()
												.addGroup(jpDiceRollingLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(jpDiceRollingLayout.createSequentialGroup()
																.addGap(33, 33, 33).addComponent(cbplayer2,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 80,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(jpDiceRollingLayout.createSequentialGroup()
																.addGap(20, 20, 20).addComponent(jPanel4,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 130,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGap(0, 0, Short.MAX_VALUE))))
						.addGroup(jpDiceRollingLayout.createSequentialGroup().addGap(48, 48, 48)
								.addComponent(lbDiceResultsPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 83,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lbDiceResultsPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 72,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		jpDiceRollingLayout.setVerticalGroup(jpDiceRollingLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jpDiceRollingLayout.createSequentialGroup().addGap(9, 9, 9)
						.addGroup(jpDiceRollingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(18, 18, 18)
						.addGroup(jpDiceRollingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lbInfantriesPlayer1).addComponent(lbInfantriesPlayer2))
						.addGap(18, 18, 18)
						.addGroup(jpDiceRollingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(cbplayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cbplayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addComponent(btRoleDice, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(jpDiceRollingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lbDiceResultsPlayer1).addComponent(lbDiceResultsPlayer2))
						.addGap(19, 19, 19)));

		add(jpDiceRolling);
		jpDiceRolling.setBounds(680, 40, 300, 300);

		jtOther.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jtOther.setRowHeight(25);
		jScrollPane3.setViewportView(jtOther);

		add(jScrollPane3);
		jScrollPane3.setBounds(290, 40, 380, 200);

		btCloseAttackPhase.setText("Close Attack Phase");
		add(btCloseAttackPhase);
		btCloseAttackPhase.setBounds(290, 300, 380, 40);
	}

	// Variables declaration and the swing component
	public javax.swing.JButton btAttack;
	public javax.swing.JButton btCloseAttackPhase;
	public javax.swing.JButton btRoleDice;
	public javax.swing.JComboBox<String> cbplayer1;
	public javax.swing.JComboBox<String> cbplayer2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane3;
	public javax.swing.JPanel jpDiceRolling;
	public javax.swing.JTable jtMain;
	public javax.swing.JTable jtOther;
	public javax.swing.JLabel lbDiceResultsPlayer1;
	public javax.swing.JLabel lbDiceResultsPlayer2;
	public javax.swing.JLabel lbInfantriesPlayer1;
	public javax.swing.JLabel lbInfantriesPlayer2;
	private javax.swing.JLabel lbPlayer;
	public javax.swing.JLabel lbSelectedCountryPlayer1;
	public javax.swing.JLabel lbSelectedCountryPlayer2;
	public javax.swing.JLabel lbplayer1;
	public javax.swing.JLabel lbplayer2;

}
