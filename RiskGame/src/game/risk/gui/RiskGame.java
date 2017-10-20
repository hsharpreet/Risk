/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.risk.gui;

import game.risk.gui.PlayerPanel.CurrentGameStatics;
import game.risk.model.Territory;
import game.risk.util.MapReader;
import game.risk.util.RiskMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.*;

public class RiskGame extends javax.swing.JFrame {

	File mapFile;
	RiskMap mapDetails;
	int totalArmies[] = { 40, 35, 30, 25, 20 };
	PlayerPanel playerPanel[];
	Color colors[] = new Color[6];

	public RiskGame() {
		initComponents();
		setSize(1000, 600);
		colors[0] = new Color(244, 198, 196);
		colors[1] = new Color(245, 220, 167);
		colors[2] = new Color(168, 244, 182);
		colors[3] = new Color(222, 171, 241);
		colors[4] = new Color(187, 208, 225);
		colors[5] = new Color(210, 194, 130);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanelTop = new javax.swing.JPanel();
		tfMapFile = new javax.swing.JTextField();
		jLabelSelectMap = new javax.swing.JLabel();
		btBrowse = new javax.swing.JButton();
		jLabelPlayerCount = new javax.swing.JLabel();
		cbPlayerCount = new javax.swing.JComboBox<>();
		btLoad = new javax.swing.JButton();
		btMapEditor = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jpPlayground = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Risk Game");
		setSize(new java.awt.Dimension(1000, 700));
		getContentPane().setLayout(null);

		jPanelTop.setBackground(new java.awt.Color(204, 204, 204));
		jPanelTop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		tfMapFile.setFocusable(false);

		jLabelSelectMap.setText("Select Map File");

		btBrowse.setText("...");
		btBrowse.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btBrowseActionPerformed(evt);
			}
		});

		jLabelPlayerCount.setText("Players count");

		cbPlayerCount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5", "6" }));

		btMapEditor.setText("Edit Map");
		btMapEditor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MapEditor editor = new MapEditor();
				try {
					editor.loadMap(tfMapFile.getText());
				} catch (Exception e1) {
					System.out.println("Error while loading this Map:"+tfMapFile.getText());
					JOptionPane.showMessageDialog(jpPlayground, "Load Map failed. Try again !");
				}
			}

		});

		btLoad.setText("Load");
		btLoad.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				if (tfMapFile.getText().endsWith(".map") || tfMapFile.getText().endsWith(".MAP")) {
					btLoadActionPerformed(evt);
				} else {
					JOptionPane.showMessageDialog(jpPlayground, "Map File could not read or invalid file. Try again !");
				}
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanelTop);
		jPanelTop.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(9, 9, 9)
						.addComponent(jLabelSelectMap, javax.swing.GroupLayout.PREFERRED_SIZE, 84,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(10, 10, 10)
						.addComponent(tfMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 132,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(12, 12, 12)
						.addComponent(btBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(58, 58, 58)
						.addComponent(jLabelPlayerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 97,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(10, 10, 10)
						.addComponent(cbPlayerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(59, 59, 59)
						.addComponent(btLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(59, 59, 59).addComponent(btMapEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(59, 59, 59)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(13, 13, 13)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabelSelectMap, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(tfMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabelPlayerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cbPlayerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btMapEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE))

						.addGap(15, 15, 15)));

		getContentPane().add(jPanelTop);
		jPanelTop.setBounds(10, 10, 960, 60);

		jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		jpPlayground.setBackground(new java.awt.Color(204, 204, 204));
		jpPlayground.setPreferredSize(new java.awt.Dimension(1000, 100));
		jpPlayground.setLayout(new javax.swing.BoxLayout(jpPlayground, javax.swing.BoxLayout.X_AXIS));
		jScrollPane1.setViewportView(jpPlayground);

		getContentPane().add(jScrollPane1);
		jScrollPane1.setBounds(10, 90, 960, 420);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btBrowseActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_btBrowseActionPerformed
	{// GEN-HEADEREND:event_btBrowseActionPerformed
		JFileChooser ch = new JFileChooser();
		int ans = ch.showOpenDialog(this);
		if (ans == JFileChooser.APPROVE_OPTION) {
			mapFile = ch.getSelectedFile();
			tfMapFile.setText(mapFile.getName());
			tfMapFile.setToolTipText(mapFile.getPath());
		}
	}// GEN-LAST:event_btBrowseActionPerformed

	private void btLoadActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_btLoadActionPerformed
	{// GEN-HEADEREND:event_btLoadActionPerformed
		if (mapFile != null) {
			mapDetails = MapReader.readMapFile(mapFile.getPath());
			if (mapDetails != null) {
				int playerCount = Integer.parseInt((String) cbPlayerCount.getSelectedItem());
				playerPanel = new PlayerPanel[playerCount];
				jpPlayground.removeAll();
				jpPlayground.setPreferredSize(new Dimension(370 * playerCount, 400));
				for (int i = 0; i < playerCount; i++) {
					playerPanel[i] = new PlayerPanel();
					playerPanel[i].setBackground(colors[i]);
					playerPanel[i].lbPlayer.setText("Player : " + (i + 1));
					playerPanel[i].infantriesTotal = totalArmies[playerCount - 2];
					playerPanel[i].lbTotalArmies.setText("Total Infantry : " + totalArmies[playerCount - 2]);
					jpPlayground.add(playerPanel[i]);
				}

				HashMap<String, Territory> territories = mapDetails.getTerritories();
				Iterator it = territories.keySet().iterator();
				while (it.hasNext()) {
					try {
						for (int i = 0; i < playerCount; i++) {
							Territory t = territories.get(it.next());
							CurrentGameStatics cgs = playerPanel[i].new CurrentGameStatics(1, t);
							playerPanel[i].currentGameStaticsList.add(cgs);

						}
					} catch (Exception ex) {
						System.out.println("Exception");
						break;
					}
				}
				for (int i = 0; i < playerCount; i++) {
					playerPanel[i].infantriesAvailable = (totalArmies[playerCount - 2]) - (playerPanel[i].currentGameStaticsList.size());
					playerPanel[i].lbAvailableArmies
							.setText("Available Infantries : " + playerPanel[i].infantriesAvailable);
					playerPanel[i].currentGameStaticsTableModel.fireTableDataChanged();

					playerPanel[i].btPlaceInfantry.addActionListener(new PlaceInfantryClickListener(i));
					playerPanel[i].btFortification.addActionListener(new FortificationClickListener(i));
					playerPanel[i].btOk.addActionListener(new OkClickListener(i));
					playerPanel[i].btPlaceInfantry.setEnabled(false);
					playerPanel[i].btReinforcement.setEnabled(false);
					playerPanel[i].btFortification.setEnabled(false);
					playerPanel[i].btOk.setEnabled(false);

				}
				playerPanel[0].btPlaceInfantry.setEnabled(true);

				this.setSize(this.getWidth() + 1, this.getHeight() + 1);
				this.setSize(this.getWidth() - 1, this.getHeight() - 1);

			} else {
				JOptionPane.showMessageDialog(this, "Map File could not read. Try again !");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Map File could not read. Try again !");
		}
	}// GEN-LAST:event_btLoadActionPerformed

	public class PlaceInfantryClickListener implements ActionListener {

		int i;

		public PlaceInfantryClickListener(int i) {
			this.i = i;
		}

		public void actionPerformed(ActionEvent e) {
			if (playerPanel[i].infantriesAvailable > 0) {
				int index = playerPanel[i].jtCountriesAndArmies.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(playerPanel[i], "Select terriotary first");
				} else {
					playerPanel[i].currentGameStaticsList.get(index).infantries++;
					playerPanel[i].infantriesAvailable--;
					playerPanel[i].lbAvailableArmies
							.setText("Available Infantries : " + playerPanel[i].infantriesAvailable);
					playerPanel[i].currentGameStaticsTableModel.fireTableDataChanged();
					playerPanel[i].btPlaceInfantry.setEnabled(false);
					nextIndexToEnableButton(i);

				}
			} else {
				JOptionPane.showMessageDialog(playerPanel[i], "No army available");

			}
		}
	}

	void nextIndexToEnableButton(int i) {
		boolean flag = true;
		for (int j = 0; j < playerPanel.length; j++) {
			if (playerPanel[j].infantriesAvailable > 0) {
				flag = false;
				break;
			}
		}
		if (flag) {
			JOptionPane.showMessageDialog(RiskGame.this,
					"Startup Phase Done.\nIn next phase every player has option of reinforcement and fortification.\nDon't forget to press button OK after fortification done !!");
			reinforcement(true);
		} else {
			i++;
			if (i == playerPanel.length) {
				i = 0;
			}
			if (playerPanel[i].infantriesAvailable > 0) {
				playerPanel[i].btPlaceInfantry.setEnabled(true);
			} else {
				nextIndexToEnableButton(i);
			}
		}

	}

	public void reinforcement(boolean firstCallToThisFunction) {
		for (int i = 0; i < playerPanel.length; i++) {
			int n = playerPanel[i].currentGameStaticsList.size() / 3;
			if (n < 3) {
				n = 3;
			}
			// Check if all terrotries are in same continent
			boolean flag = true;
			String firstContinent = playerPanel[i].currentGameStaticsList.get(0).territory.getContinent();
			for (int j = 1; j < playerPanel[i].currentGameStaticsList.size(); j++) {
				String continent = playerPanel[i].currentGameStaticsList.get(j).territory.getContinent();
				if (!firstContinent.equals(continent)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				int p = Integer.parseInt(mapDetails.getContinents().get(firstContinent));
				n = n + p;
			}
			playerPanel[i].infantriesTotal += n;
			playerPanel[i].lbTotalArmies.setText("Total Infantry : " + playerPanel[i].infantriesTotal);
			playerPanel[i].infantriesAvailable = n;
			playerPanel[i].lbMessage.setText("Message : You have gotton " + n + " new infantries");
			playerPanel[i].lbAvailableArmies.setText("Available Infantries : " + playerPanel[i].infantriesAvailable);
			if (firstCallToThisFunction) {
				playerPanel[i].btReinforcement.addActionListener(new ReinforcementClickListener(i));
			}
			playerPanel[i].btReinforcement.setEnabled(false);
			playerPanel[i].btFortification.setEnabled(false);
			playerPanel[i].btOk.setEnabled(false);
		}
		playerPanel[0].btReinforcement.setEnabled(true);
	}

	void nextPlayerTurn(int i) {
		playerPanel[i].lbMessage.setText("");
		i++;
		if (i == playerPanel.length) {
			reinforcement(false);
		} else {
			for (int j = 0; j < playerPanel.length; j++) {
				playerPanel[j].btReinforcement.setEnabled(false);
				playerPanel[j].btFortification.setEnabled(false);
				playerPanel[j].btOk.setEnabled(false);
			}
			playerPanel[i].btReinforcement.setEnabled(true);
		}

	}

	public class ReinforcementClickListener implements ActionListener {

		int i;

		public ReinforcementClickListener(int i) {
			this.i = i;
		}

		public void actionPerformed(ActionEvent e) {
			if (playerPanel[i].infantriesAvailable > 0) {
				int index = playerPanel[i].jtCountriesAndArmies.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(playerPanel[i], "Select terriotary first");
				} else {
					playerPanel[i].currentGameStaticsList.get(index).infantries++;
					playerPanel[i].infantriesAvailable--;
					playerPanel[i].lbAvailableArmies
							.setText("Available Infantries : " + playerPanel[i].infantriesAvailable);
					playerPanel[i].currentGameStaticsTableModel.fireTableDataChanged();

					if (playerPanel[i].infantriesAvailable == 0) {
						playerPanel[i].btReinforcement.setEnabled(false);
						int ans = JOptionPane.showConfirmDialog(playerPanel[i],
								"Player : " + (i + 1) + "\nDo you want to do fortification ?",
								"Fortification Confirmition", JOptionPane.YES_NO_OPTION);
						if (ans == JOptionPane.YES_OPTION) {
							playerPanel[i].btFortification.setEnabled(true);
							playerPanel[i].btOk.setEnabled(true);
						} else {
							nextPlayerTurn(i);
						}
					}

				}
			} else {
				JOptionPane.showMessageDialog(playerPanel[i], "No army available");

			}
		}
	}

	public class FortificationClickListener implements ActionListener {

		int i;

		public FortificationClickListener(int i) {
			this.i = i;
		}

		public void actionPerformed(ActionEvent e) {
			int tableIndex = playerPanel[i].jtCountriesAndArmies.getSelectedRow();
			int listIndex = playerPanel[i].lsNeighbour.getSelectedIndex();
			if (tableIndex == -1) {
				JOptionPane.showMessageDialog(playerPanel[i], "Select source territory first");
			} else if (listIndex == -1) {
				JOptionPane.showMessageDialog(playerPanel[i], "Select destination territory first");
			} else {
				String destinationTerritory = playerPanel[i].lsNeighbour.getSelectedValue();
				boolean isDestinationMyOwnCountry = false;
				for (int j = 0; j < playerPanel[i].currentGameStaticsList.size(); j++) {
					if (playerPanel[i].currentGameStaticsList.get(j).territory.getName().equals(destinationTerritory)) {
						isDestinationMyOwnCountry = true;
						break;
					}
				}
				if (isDestinationMyOwnCountry) {
					if (playerPanel[i].currentGameStaticsList.get(tableIndex).infantries > 1) {

						playerPanel[i].currentGameStaticsList.get(tableIndex).infantries--;

						for (int j = 0; j < playerPanel[i].currentGameStaticsList.size(); j++) {
							if (playerPanel[i].currentGameStaticsList.get(j).territory.getName().equals(destinationTerritory)) {
								playerPanel[i].currentGameStaticsList.get(j).infantries++;
								playerPanel[i].currentGameStaticsTableModel.fireTableDataChanged();
								break;
							}
						}
					} else {
						JOptionPane.showMessageDialog(playerPanel[i],
								"Source territory must have more that 1 infantries for fortification");
					}
				} else {
					JOptionPane.showMessageDialog(playerPanel[i], "Destination territory must be your territory");
				}
			}
		}
	}

	public class OkClickListener implements ActionListener {

		int i;

		public OkClickListener(int i) {
			this.i = i;
		}

		public void actionPerformed(ActionEvent e) {

			nextPlayerTurn(i);
		}
	}

	public void fortification(int i) {
		System.out.println("Fortification Called : " + i);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting
		// code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
		 * html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(RiskGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(RiskGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(RiskGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(RiskGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new RiskGame().setVisible(true);
			}
		});
	}

	// Variables declaration
	private javax.swing.JButton btBrowse;
	private javax.swing.JButton btLoad;
	private javax.swing.JButton btMapEditor;
	private javax.swing.JComboBox<String> cbPlayerCount;
	private javax.swing.JLabel jLabelPlayerCount;
	private javax.swing.JLabel jLabelSelectMap;
	private javax.swing.JPanel jPanelTop;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JPanel jpPlayground;
	private javax.swing.JTextField tfMapFile;
}
