/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.risk.gui;

import game.risk.model.AttackModel;
import game.risk.model.CurrentGameStatics;
import game.risk.model.CurrentGameStaticsTableModel;
import game.risk.model.Player;
import game.risk.model.TempTableModel;
import game.risk.util.MapDetails;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Team
 */
public class AttackGUIPanel extends javax.swing.JPanel {

	public AttackGUIPanel(Player[] player, int myIndex, CurrentGameStaticsTableModel tm, List<CurrentGameStatics> list,
			MapDetails mapDetails) {
		initComponents();
		lbPlayer.setText("Player : " + (myIndex + 1));
		new AttackModel(this, player, myIndex, tm, list, mapDetails);
	}

	@SuppressWarnings("unchecked")

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jtMain = new javax.swing.JTable();
		lbPlayer = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		btAttack = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		lbplayer1 = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		lbplayer2 = new javax.swing.JLabel();
		cbplayer1 = new javax.swing.JComboBox<>();
		cbplayer2 = new javax.swing.JComboBox<>();
		jScrollPane3 = new javax.swing.JScrollPane();
		jtOther = new javax.swing.JTable();

		setLayout(null);

		jtMain.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jtMain.setRowHeight(25);
		jScrollPane1.setViewportView(jtMain);

		add(jScrollPane1);
		jScrollPane1.setBounds(10, 40, 270, 250);

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

		jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel1.setLayout(null);

		jPanel2.setBackground(new java.awt.Color(255, 102, 102));
		jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 51)));
		jPanel2.setLayout(null);

		lbplayer1.setFont(new java.awt.Font("Tahoma", 1, 18));
		lbplayer1.setForeground(new java.awt.Color(255, 255, 255));
		jPanel2.add(lbplayer1);
		lbplayer1.setBounds(20, 10, 110, 20);

		jPanel1.add(jPanel2);
		jPanel2.setBounds(10, 10, 130, 40);

		jPanel4.setBackground(new java.awt.Color(0, 204, 153));
		jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 102)));
		jPanel4.setLayout(null);

		lbplayer2.setFont(new java.awt.Font("Tahoma", 1, 18));
		lbplayer2.setForeground(new java.awt.Color(255, 255, 255));
		jPanel4.add(lbplayer2);
		lbplayer2.setBounds(20, 10, 110, 20);

		jPanel1.add(jPanel4);
		jPanel4.setBounds(160, 10, 130, 40);

		cbplayer1.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		jPanel1.add(cbplayer1);
		cbplayer1.setBounds(40, 60, 80, 30);

		cbplayer2.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		jPanel1.add(cbplayer2);
		cbplayer2.setBounds(180, 60, 80, 30);

		add(jPanel1);
		jPanel1.setBounds(680, 40, 300, 250);

		jtOther.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jtOther.setRowHeight(25);
		jScrollPane3.setViewportView(jtOther);

		add(jScrollPane3);
		jScrollPane3.setBounds(290, 40, 380, 200);
	}

	// Variables declaration
	public javax.swing.JButton btAttack;
	private javax.swing.JComboBox<String> cbplayer1;
	private javax.swing.JComboBox<String> cbplayer2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane3;
	public javax.swing.JTable jtMain;
	public javax.swing.JTable jtOther;
	private javax.swing.JLabel lbPlayer;
	public javax.swing.JLabel lbplayer1;
	public javax.swing.JLabel lbplayer2;
	// End of variables declaration
}
