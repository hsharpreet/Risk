package game.risk.gui;

public class Tournament extends javax.swing.JPanel {

	/**
	 * Creates new form Tournament
	 */
	public Tournament() {
		initComponents();
	}

	@SuppressWarnings("unchecked")

	private void initComponents() {

		lbPlayer1 = new javax.swing.JLabel();
		lbPlayer2 = new javax.swing.JLabel();
		lbPlayer3 = new javax.swing.JLabel();
		lbPlayer4 = new javax.swing.JLabel();
		cbPlayer1 = new javax.swing.JComboBox();
		cbPlayer2 = new javax.swing.JComboBox();
		cbPlayer3 = new javax.swing.JComboBox();
		cbPlayer4 = new javax.swing.JComboBox();
		lbMap1 = new javax.swing.JLabel();
		lbMap2 = new javax.swing.JLabel();
		lbMap3 = new javax.swing.JLabel();
		lbMap4 = new javax.swing.JLabel();
		lbMap5 = new javax.swing.JLabel();
		tfMap1 = new javax.swing.JTextField();
		tfMap2 = new javax.swing.JTextField();
		tfMap3 = new javax.swing.JTextField();
		tfMap4 = new javax.swing.JTextField();
		tfMap5 = new javax.swing.JTextField();
		btMap1 = new javax.swing.JButton();
		btMap2 = new javax.swing.JButton();
		btMap3 = new javax.swing.JButton();
		btMap4 = new javax.swing.JButton();
		btMap5 = new javax.swing.JButton();
		lbGames = new javax.swing.JLabel();
		lbTurns = new javax.swing.JLabel();
		cbGames = new javax.swing.JComboBox();
		cbTurns = new javax.swing.JComboBox();
		btPlayTournament = new javax.swing.JButton();

		lbPlayer1.setText("Player 1");

		lbPlayer2.setText("Player 2");

		lbPlayer3.setText("Player 3");

		lbPlayer4.setText("Player 4");

		cbPlayer1.setModel(
				new javax.swing.DefaultComboBoxModel(new String[] { "Aggressive", "Benevolent", "Random", "Cheater" }));

		cbPlayer2.setModel(
				new javax.swing.DefaultComboBoxModel(new String[] { "Benevolent", "Aggressive", "Random", "Cheater" }));

		cbPlayer3.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Select", "Aggressive", "Benevolent", "Random", "Cheater" }));

		cbPlayer4.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Select", "Aggressive", "Benevolent", "Random", "Cheater4" }));
		cbPlayer4.setToolTipText("");

		lbMap1.setText("Map 1");

		lbMap2.setText("Map 2");

		lbMap3.setText("Map 3");

		lbMap4.setText("Map 4");

		lbMap5.setText("Map 5");

		tfMap2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tfMap2ActionPerformed(evt);
			}
		});

		btMap1.setText("Browse");
		btMap1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btMap1ActionPerformed(evt);
			}
		});

		btMap2.setText("Browse");
		btMap2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btMap2ActionPerformed(evt);
			}
		});

		btMap3.setText("Browse");
		btMap3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btMap3ActionPerformed(evt);
			}
		});

		btMap4.setText("Browse");
		btMap4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btMap4ActionPerformed(evt);
			}
		});

		btMap5.setText("Browse");
		btMap5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btMap5ActionPerformed(evt);
			}
		});

		lbGames.setText("No. Of Games");

		lbTurns.setText("No. Of Turns");

		cbGames.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
		cbGames.setToolTipText("");

		cbTurns.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));

		btPlayTournament.setText("Play Tournament");
		btPlayTournament.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btPlayTournamentActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
										layout.createSequentialGroup().addComponent(lbMap5)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(tfMap5))
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
										layout.createSequentialGroup().addComponent(lbMap4)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(tfMap4, javax.swing.GroupLayout.PREFERRED_SIZE, 115,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(btMap4).addComponent(btMap5)))
						.addGroup(layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lbPlayer1).addComponent(cbPlayer1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(66, 66, 66)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lbPlayer2).addComponent(cbPlayer2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup().addGroup(layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
												layout.createSequentialGroup().addComponent(lbMap1)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(tfMap1))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
												layout.createSequentialGroup().addComponent(lbMap2)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(tfMap2, javax.swing.GroupLayout.PREFERRED_SIZE,
																115, javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(btMap1).addComponent(btMap2)))
								.addGroup(layout.createSequentialGroup().addComponent(lbMap3)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(tfMap3, javax.swing.GroupLayout.PREFERRED_SIZE, 115,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(btMap3)))
								.addGap(57, 57, 57)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lbPlayer3)
										.addComponent(cbPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lbGames).addComponent(lbTurns))
								.addGap(66, 66, 66)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(cbTurns, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cbGames, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cbPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lbPlayer4)))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup()
										.addComponent(btPlayTournament, javax.swing.GroupLayout.PREFERRED_SIZE, 202,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(48, 48, 48)))
				.addContainerGap(16, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(28, 28, 28)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lbPlayer1)
						.addComponent(lbPlayer2).addComponent(lbPlayer3).addComponent(lbPlayer4))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(cbPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(cbPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(cbPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(cbPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(22, 22, 22)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lbMap1)
						.addComponent(tfMap1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btMap1))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lbMap2)
						.addComponent(tfMap2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btMap2).addComponent(lbGames)
						.addComponent(cbGames, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lbMap3)
						.addComponent(tfMap3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btMap3).addComponent(lbTurns)
						.addComponent(cbTurns, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lbMap4)
						.addComponent(tfMap4, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btMap4))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lbMap5)
						.addComponent(tfMap5, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btMap5).addComponent(btPlayTournament))
				.addContainerGap(48, Short.MAX_VALUE)));
	}

	private void tfMap2ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void btMap1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btMap2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btMap3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btMap4ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btMap5ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btPlayTournamentActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btMap1;
	private javax.swing.JButton btMap2;
	private javax.swing.JButton btMap3;
	private javax.swing.JButton btMap4;
	private javax.swing.JButton btMap5;
	private javax.swing.JButton btPlayTournament;
	private javax.swing.JComboBox cbGames;
	private javax.swing.JComboBox cbPlayer1;
	private javax.swing.JComboBox cbPlayer2;
	private javax.swing.JComboBox cbPlayer3;
	private javax.swing.JComboBox cbPlayer4;
	private javax.swing.JComboBox cbTurns;
	private javax.swing.JLabel lbGames;
	private javax.swing.JLabel lbMap1;
	private javax.swing.JLabel lbMap2;
	private javax.swing.JLabel lbMap3;
	private javax.swing.JLabel lbMap4;
	private javax.swing.JLabel lbMap5;
	private javax.swing.JLabel lbPlayer1;
	private javax.swing.JLabel lbPlayer2;
	private javax.swing.JLabel lbPlayer3;
	private javax.swing.JLabel lbPlayer4;
	private javax.swing.JLabel lbTurns;
	private javax.swing.JTextField tfMap1;
	private javax.swing.JTextField tfMap2;
	private javax.swing.JTextField tfMap3;
	private javax.swing.JTextField tfMap4;
	private javax.swing.JTextField tfMap5;
	// End of variables declaration
}
