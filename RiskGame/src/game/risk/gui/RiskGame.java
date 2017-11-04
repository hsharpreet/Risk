package game.risk.gui;

import game.risk.gui.PlayerPanel;
import game.risk.model.CurrentGameStatics;
import game.risk.model.CurrentGameStaticsTableModel;
import game.risk.model.NeighbourListModel;
import game.risk.model.Player;
import game.risk.model.Territory;
import game.risk.util.MapDetails;
import game.risk.util.MapReader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * RiskGame is the main class of this game which represents view
 * 
 * @author Team
 * @version 1.0.0
 *
 */
public class RiskGame extends javax.swing.JFrame {

	File mapFile;
	MapDetails mapDetails;
	int totalArmies[] = { 40, 35, 30, 25, 20 };
	Color colors[] = new Color[6];

	Player player[];
	
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
	 * This method is called from the constructor to initialize the form components.
	 */
	@SuppressWarnings("unchecked")
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
				MapEditor editor = new MapEditor(tfMapFile.getText());
				try {
					if (editor.getRiskMap() != null) {
						editor.loadMap();
					} else {
						JOptionPane.showMessageDialog(jpPlayground, "Map File is invalid. Try again !");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(jpPlayground, tfMapFile.getText()+ " load Map failed. Try again !");
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

		/**
		 * Top panel in the game holds browse, load and edit map buttons
		 */
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
	}

	/**
	 * browse button action performed, browse and select file
	 * @param evt
	 */
	private void btBrowseActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser ch = new JFileChooser();
		int ans = ch.showOpenDialog(this);
		if (ans == JFileChooser.APPROVE_OPTION) {
			mapFile = ch.getSelectedFile();
			tfMapFile.setText(mapFile.getName());
			tfMapFile.setToolTipText(mapFile.getPath());
		}
	}

	/**
	 * load button action performed, loads map to playground
	 * @param evt
	 */
	private void btLoadActionPerformed(java.awt.event.ActionEvent evt) {
		if (mapFile != null) {
			 mapDetails = MapReader.readMapFile(mapFile.getPath());
			if (mapDetails != null) {
				int playerCount = Integer.parseInt((String) cbPlayerCount.getSelectedItem());
				player = new Player[playerCount];
				jpPlayground.removeAll();
				jpPlayground.setPreferredSize(new Dimension(370 * playerCount, 400));
				for (int i = 0; i < playerCount; i++) {
					 player[i] = new Player(i,player,mapDetails);
	                    player[i].setPlayerPanel(new PlayerPanel());
	                    player[i].bindListeners();
	                    player[i].currentGameStaticsList = new ArrayList<>();
	                    player[i].currentGameStaticsTableModel = new CurrentGameStaticsTableModel(player[i].currentGameStaticsList);
	                    player[i].getPlayerPanel().jtCountriesAndArmies.setModel(player[i].currentGameStaticsTableModel);

	                    player[i].neighbours = new ArrayList<>();

	                    player[i].neighbourListModel = new NeighbourListModel(player[i].neighbours);

	                    player[i].getPlayerPanel().lsNeighbour.setModel(player[i].neighbourListModel);
	                    player[i].getPlayerPanel().setBackground(colors[i]);
	                    player[i].getPlayerPanel().lbPlayer.setText("Player : " + (i + 1));
	                    player[i].infantriesTotal = totalArmies[playerCount - 2];
	                    player[i].getPlayerPanel().lbTotalArmies.setText("Total Infantries : " + player[i].infantriesTotal);
	                    jpPlayground.add(player[i].getPlayerPanel());
				}

				HashMap<String, Territory> territories = mapDetails.getTerritories();
				Iterator it = territories.keySet().iterator();
				while (it.hasNext()) {
					try {
						for (int i = 0; i < playerCount; i++) {
							Territory t = territories.get(it.next());
							CurrentGameStatics cgs = new CurrentGameStatics(1, t, i);
                            player[i].currentGameStaticsList.add(cgs);

						}
					} catch (Exception ex) {
						break;
					}
				}
				for (int i = 0; i < playerCount; i++) {
					player[i].infantriesAvailable = (totalArmies[playerCount - 2]) - (player[i].currentGameStaticsList.size());
                    player[i].getPlayerPanel().lbAvailableArmies.setText(
                    		"Available Infantries : " + player[i].infantriesAvailable);
                    player[i].currentGameStaticsTableModel.fireTableDataChanged(); //Display data in table

                    
                    player[i].getPlayerPanel().btPlaceInfantry.setEnabled(false);
                    player[i].getPlayerPanel().btReinforcement.setEnabled(false);
                    player[i].getPlayerPanel().btFortification.setEnabled(false);
                    player[i].getPlayerPanel().btOk.setEnabled(false);

                }
                player[0].getPlayerPanel().btPlaceInfantry.setEnabled(true);

				this.setSize(this.getWidth() + 1, this.getHeight() + 1);
				this.setSize(this.getWidth() - 1, this.getHeight() - 1);

			} else {
				JOptionPane.showMessageDialog(this, "Map File is invalid. Try again !");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Map File could not read. Try again !");
		}
	}

	/**
	 * Class to handle Place Infantry button action and manipulates all related values for each player
	 * @param evt
	 */
	//public class PlaceInfantryClickListener implements ActionListener {

	//}

	/**
	 * Changing turn for each player for Startup phase, 
	 * also starts reinforcement phase when no available armies.
	 * @param evt
	 */
	void nextIndexToEnableButton(int i) {
		

	}

	/**
	 * Starting reinforcement phase for each player
	 * @param firstCallToThisFunction boolean to call button action listener only once
	 */
	//public void reinforcement(boolean firstCallToThisFunction) {
		
	//}

	/**
	 * Reinforcement: changing turn for each player for Startup phase, 
	 * also starts reinforcement phase when no available armies.
	 * @param i represent current player
	 */
	void nextPlayerTurn(int i) {
		
	}

	/**
	 * Class to handle Reinforcement button action and manipulates all related values for each player
	 * @param evt
	 */
	//public class ReinforcementClickListener implements ActionListener {
		
	//}

	/**
	 * Class to handle Fortification button action and manipulates all related values for each player
	 * @param evt
	 */
	//public class FortificationClickListener implements ActionListener {
		
	//}


	/**
	 * @param args
	 *            the command line arguments
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
			java.util.logging.Logger.getLogger(RiskGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(RiskGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(RiskGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(RiskGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		

		
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
