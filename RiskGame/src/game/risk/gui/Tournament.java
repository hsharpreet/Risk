package game.risk.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import game.risk.model.TournamentModel;
import game.risk.model.entities.TournamentResult;

/**
 * Class to create GUI for Tournament
 * 
 * @author Team
 */
public class Tournament extends javax.swing.JFrame {
	File mapFile;

	/**
	 * A constructor
	 */
	public Tournament() {

		initComponents();
	}

	@SuppressWarnings("unchecked")
	/**
	 * A method to initialize the variables
	 */
	private void initComponents() {

		jPanel2 = new javax.swing.JPanel();
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
		tfMap1.setFocusable(false);
		tfMap2 = new javax.swing.JTextField();
		tfMap2.setFocusable(false);
		tfMap3 = new javax.swing.JTextField();
		tfMap3.setFocusable(false);
		tfMap4 = new javax.swing.JTextField();
		tfMap4.setFocusable(false);
		tfMap5 = new javax.swing.JTextField();
		tfMap5.setFocusable(false);
		taResult = new javax.swing.JTextArea();
		taResult.setFocusable(false);
		btMap1 = new javax.swing.JButton();
		btMap2 = new javax.swing.JButton();
		btMap3 = new javax.swing.JButton();
		btMap4 = new javax.swing.JButton();
		btMap5 = new javax.swing.JButton();
		lbGames = new javax.swing.JLabel();
		lbTurns = new javax.swing.JLabel();
		lbResult = new javax.swing.JLabel();
		cbGames = new javax.swing.JComboBox();
		cbTurns = new javax.swing.JComboBox();
		jScrollPane1 = new javax.swing.JScrollPane();
		btPlayTournament = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Tournament");

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
				new String[] { "Select", "Aggressive", "Benevolent", "Random", "Cheater" }));
		cbPlayer4.setToolTipText("");

		lbMap1.setText("Map 1");

		lbMap2.setText("Map 2");

		lbMap3.setText("Map 3");

		lbMap4.setText("Map 4");

		lbMap5.setText("Map 5");

		lbResult.setText("Tournament Result");
		/**
		 * A listener for textfield
		 */
		tfMap2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tfMap2ActionPerformed(evt);
			}
		});
		/**
		 * A listener for Browse button
		 */
		btMap1.setText("Browse");
		btMap1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btMap1ActionPerformed(evt);
			}
		});
		/**
		 * A listener for Browse button
		 */
		btMap2.setText("Browse");
		btMap2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btMap2ActionPerformed(evt);
			}
		});
		/**
		 * A listener for Browse button
		 */
		btMap3.setText("Browse");
		btMap3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btMap3ActionPerformed(evt);
			}
		});
		/**
		 * A listener for Browse button
		 */
		btMap4.setText("Browse");
		btMap4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btMap4ActionPerformed(evt);
			}
		});
		/**
		 * A listener for Browse button
		 */
		btMap5.setText("Browse");
		btMap5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btMap5ActionPerformed(evt);
			}
		});
		taResult.setColumns(20);
		taResult.setRows(5);
		jScrollPane1.setViewportView(taResult);
		lbGames.setText("No. Of Games");

		lbTurns.setText("No. Of Turns");

		cbGames.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
		cbGames.setToolTipText("");

		cbTurns.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "10", "15", "20", "25", "30", "35", "40", "45", "50" }));

		btPlayTournament.setText("Play Tournament");
		/**
		 * A listener for Play Tournament button
		 */
		btPlayTournament.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String[] players = { cbPlayer1.getSelectedItem().toString(), cbPlayer2.getSelectedItem().toString(),
						cbPlayer3.getSelectedItem().toString(), cbPlayer4.getSelectedItem().toString() };

				String[] maps = { tfMap1.getText(), tfMap2.getText(), tfMap3.getText(), tfMap4.getText() };
				int game_count = Integer.parseInt((String) cbGames.getSelectedItem());
				int turns = Integer.parseInt((String) cbTurns.getSelectedItem());

				TournamentModel t = new TournamentModel(players, maps, game_count, turns);
				t.startTournament();
				List<TournamentResult> resultList = t.getTournamentResult();
				StringBuffer resultString = new StringBuffer();
				for (TournamentResult tr : resultList) {
					resultString.append(tr.getMapName()).append(" ").append("Game:").append(tr.getGameIndex())
							.append(" ").append(tr.getWinnerName()).append("\n");
				}
				taResult.setText(resultString.toString());

			}
		});
		// adding swing components into panel
		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
												jPanel2Layout.createSequentialGroup().addComponent(lbMap5)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(tfMap5))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
												jPanel2Layout.createSequentialGroup().addComponent(lbMap4)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(tfMap4, javax.swing.GroupLayout.PREFERRED_SIZE,
																115, javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(jPanel2Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(btMap4).addComponent(btMap5)))
								.addGroup(jPanel2Layout.createSequentialGroup()
										.addGroup(jPanel2Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(lbPlayer1).addComponent(cbPlayer1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(66, 66, 66)
														.addGroup(jPanel2Layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(lbPlayer2).addComponent(cbPlayer2,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
																jPanel2Layout.createSequentialGroup()
																		.addComponent(lbMap1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(tfMap1))
														.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
																jPanel2Layout.createSequentialGroup()
																		.addComponent(lbMap2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(tfMap2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				115,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(jPanel2Layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(btMap1).addComponent(btMap2)))
												.addGroup(jPanel2Layout.createSequentialGroup().addComponent(lbMap3)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(tfMap3, javax.swing.GroupLayout.PREFERRED_SIZE,
																115, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(btMap3)))
										.addGap(57, 57, 57)
										.addGroup(jPanel2Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lbPlayer3)
												.addComponent(cbPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lbGames).addComponent(lbTurns))
										.addGap(66, 66, 66)
										.addGroup(jPanel2Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
										jPanel2Layout.createSequentialGroup()
												.addComponent(btPlayTournament, javax.swing.GroupLayout.PREFERRED_SIZE,
														202, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(48, 48, 48)))
						.addComponent(lbResult).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559,
								javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(94, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(28, 28, 28)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lbPlayer1).addComponent(lbPlayer2).addComponent(lbPlayer3)
								.addComponent(lbPlayer4))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(cbPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cbPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cbPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cbPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(22, 22, 22)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lbMap1)
								.addComponent(tfMap1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btMap1))
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lbMap2)
								.addComponent(tfMap2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btMap2).addComponent(lbGames)
								.addComponent(cbGames, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lbMap3)
								.addComponent(tfMap3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btMap3).addComponent(lbTurns)
								.addComponent(cbTurns, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lbMap4)
								.addComponent(tfMap4, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btMap4))
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lbMap5)
								.addComponent(tfMap5, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btMap5).addComponent(btPlayTournament))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(lbResult)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(59, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 665, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
								.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 394, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
								.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE))));

		pack();
	}

	/**
	 * Method to create event handler for text field
	 * 
	 * @param evt
	 *            an action event component
	 */
	private void tfMap2ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	/**
	 * Method to create event handler for map button
	 * 
	 * @param evt
	 *            an action event component
	 */
	private void btMap1ActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser ch = new JFileChooser();
		int ans = ch.showOpenDialog(this);
		if (ans == JFileChooser.APPROVE_OPTION) {
			mapFile = ch.getSelectedFile();
			tfMap1.setText(mapFile.getName());
			tfMap1.setToolTipText(mapFile.getPath());
		}
	}

	/**
	 * Method to create event handler for map button
	 * 
	 * @param evt
	 *            an action event component
	 * 
	 */
	private void btMap2ActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser ch = new JFileChooser();
		int ans = ch.showOpenDialog(this);
		if (ans == JFileChooser.APPROVE_OPTION) {
			mapFile = ch.getSelectedFile();
			tfMap2.setText(mapFile.getName());
			tfMap2.setToolTipText(mapFile.getPath());
		}
	}

	/**
	 * Method to create event handler for map button
	 * 
	 * @param evt
	 *            an action event component
	 *
	 */
	private void btMap3ActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser ch = new JFileChooser();
		int ans = ch.showOpenDialog(this);
		if (ans == JFileChooser.APPROVE_OPTION) {
			mapFile = ch.getSelectedFile();
			tfMap3.setText(mapFile.getName());
			tfMap3.setToolTipText(mapFile.getPath());
		}
	}

	/**
	 * 
	 * Method to create event handler for map button
	 * 
	 * @param evt
	 *            an action event component
	 */
	private void btMap4ActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser ch = new JFileChooser();
		int ans = ch.showOpenDialog(this);
		if (ans == JFileChooser.APPROVE_OPTION) {
			mapFile = ch.getSelectedFile();
			tfMap4.setText(mapFile.getName());
			tfMap4.setToolTipText(mapFile.getPath());
		}
	}

	/**
	 * Method to create event handler for map button
	 * 
	 * @param evt
	 *            an action event component
	 * 
	 */

	private void btMap5ActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser ch = new JFileChooser();
		int ans = ch.showOpenDialog(this);
		if (ans == JFileChooser.APPROVE_OPTION) {
			mapFile = ch.getSelectedFile();
			tfMap5.setText(mapFile.getName());
			tfMap5.setToolTipText(mapFile.getPath());
		}
	}

	/**
	 * Method to create event handler for play tournament button
	 * 
	 * @param evt
	 *            an action
	 */
	private void btPlayTournamentActionPerformed(java.awt.event.ActionEvent evt) {

	}
/**
 * The main Method
 * @param args a String parameter
 */
	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Tournament.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Tournament.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Tournament.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Tournament.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Tournament().setVisible(true);
			}
		});
	}

	// Variables declaration
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
	private javax.swing.JPanel jPanel2;
	private javax.swing.JLabel lbGames;
	private javax.swing.JScrollPane jScrollPane1;
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
	private javax.swing.JLabel lbResult;
	private javax.swing.JTextField tfMap1;
	private javax.swing.JTextField tfMap2;
	private javax.swing.JTextField tfMap3;
	private javax.swing.JTextField tfMap4;
	private javax.swing.JTextField tfMap5;
	private javax.swing.JTextArea taResult;
	// End of variables declaration
}
