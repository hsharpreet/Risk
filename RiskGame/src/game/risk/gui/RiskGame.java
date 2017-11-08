package game.risk.gui;

import game.risk.gui.PlayerPanel;
import game.risk.util.CurrentGameStatics;
import game.risk.util.CurrentGameStaticsTableModel;
import game.risk.util.NeighbourListModel;
import game.risk.model.RiskMap;
import game.risk.model.MapWriter;
import game.risk.model.Player;
import game.risk.util.LoggerUtility;
import game.risk.util.Territory;
import game.risk.util.CustomLogRecord;
import game.risk.util.MapReader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.*;
import java.util.logging.Level;
import javax.swing.JLabel;

/**
 * RiskGame is the main class of this game which represents view
 * 
 * @author Team
 * @version 1.0.0
 *
 */
public class RiskGame extends javax.swing.JFrame implements Observer {

	File mapFile;
	RiskMap mapDetails;
	int totalArmies[] = { 40, 35, 30, 25, 20 };
	Color colors[] = new Color[6];

	Player player[];

	JLabel labels[] = new JLabel[6];

	/**
	 * A constructor
	 */
	public RiskGame() {
		initComponents();

		labels[0] = lbPlayer1;
		labels[1] = lbPlayer2;
		labels[2] = lbPlayer3;
		labels[3] = lbPlayer4;
		labels[4] = lbPlayer5;
		labels[5] = lbPlayer6;

		for (int i = 0; i < labels.length; i++) {
			labels[i].setVisible(false);
		}

		setSize(1300, 600);
		taObserverMessage.setLineWrap(true);

		colors[0] = new Color(244, 198, 196);
		colors[1] = new Color(245, 220, 167);
		colors[2] = new Color(168, 244, 182);
		colors[3] = new Color(222, 171, 241);
		colors[4] = new Color(187, 208, 225);
		colors[5] = new Color(210, 194, 130);

		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Risk Game Started");
		LoggerUtility.consoleHandler.publish(logRecord);
	}

	/**
	 * This method is called from the constructor to initialize the form components.
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() {

		jPanelTop = new javax.swing.JPanel(); // swing Components
		tfMapFile = new javax.swing.JTextField();
		jLabelSelectMap = new javax.swing.JLabel();
		btBrowse = new javax.swing.JButton();
		jLabelPlayerCount = new javax.swing.JLabel();
		cbPlayerCount = new javax.swing.JComboBox<>();

		btLoad = new javax.swing.JButton();
		btMapEditor = new javax.swing.JButton();
		btmapFromScratch = new JButton("M.F.S.");
		btmapFromScratch.setToolTipText("Map from Scratch");

		jScrollPane1 = new javax.swing.JScrollPane();
		jpPlayground = new javax.swing.JPanel();

		jScrollPane2 = new javax.swing.JScrollPane();
		taObserverMessage = new javax.swing.JTextArea();
		jLabel3 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();

		lbPlayer1 = new javax.swing.JLabel();
		lbPlayer2 = new javax.swing.JLabel();
		lbPlayer3 = new javax.swing.JLabel();
		lbPlayer4 = new javax.swing.JLabel();
		lbPlayer5 = new javax.swing.JLabel();
		lbPlayer6 = new javax.swing.JLabel();

		jLabel4 = new javax.swing.JLabel();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Risk Game");
		setSize(new java.awt.Dimension(1000, 700));
		getContentPane().setLayout(null);

		jPanelTop.setBackground(new java.awt.Color(204, 204, 204));
		jPanelTop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		tfMapFile.setFocusable(false);

		jLabelSelectMap.setText("Select Map File");

		btBrowse.setText("...");
		/**
		 * A listener for browse button
		 */
		btBrowse.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btBrowseActionPerformed(evt);
			}
		});

		jLabelPlayerCount.setText("Players count");

		cbPlayerCount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5", "6" }));

		btMapEditor.setText("Edit Map");
		/**
		 * A listener for map editor button
		 */
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
					JOptionPane.showMessageDialog(jpPlayground, tfMapFile.getText() + " load Map failed. Try again !");
				}
			}

		});
		/**
		 * A listener for load button
		 */
		btLoad.setText("Start Game");
		btLoad.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				if (tfMapFile.getText().toLowerCase().endsWith(".map")) {
					btLoadActionPerformed(evt);
				} else {
					JOptionPane.showMessageDialog(jpPlayground, "Map File could not read or invalid file. Try again !");
				}
			}
		});

		/**
		 * A listener for map from scratch button.
		 */
		btmapFromScratch.addActionListener(new ActionListener() {
			boolean check = true;
			javax.swing.JTextField tfMapName = new javax.swing.JTextField();
			javax.swing.JTextField tfAuthorName = new javax.swing.JTextField("Team 13");
			javax.swing.JTextField tfWarn = new javax.swing.JTextField("yes");
			javax.swing.JTextField tfImage = new javax.swing.JTextField("default.bmp");
			javax.swing.JTextField tfWrap = new javax.swing.JTextField("yes");
			javax.swing.JTextField tfScroll = new javax.swing.JTextField("horizontal");

			javax.swing.JTextField tfContinentName = new javax.swing.JTextField();
			javax.swing.JTextField tfContinentValue = new javax.swing.JTextField();

			javax.swing.JTextField tfTerritoryName = new javax.swing.JTextField();
			javax.swing.JTextField tfTerritoryXAxis = new javax.swing.JTextField("0");
			javax.swing.JTextField tfTerritoryYAxis = new javax.swing.JTextField("0");
			javax.swing.JTextField tfTerritoryContinent = new javax.swing.JTextField("abc");
			MapFromScratch newMap = new MapFromScratch();
			int option = 0;
			Object[] map = { "Map name:", tfMapName, "Author Name:", tfAuthorName, "Continent Name", tfContinentName,
					"Continent Value", tfContinentValue, "Territory Name", tfTerritoryName, "Territory X Cord",
					tfTerritoryXAxis, "Territory Y Cord", tfTerritoryYAxis, "Continent:", tfTerritoryContinent };

			public void actionPerformed(ActionEvent e) {

				tfAuthorName.setEnabled(false);
				tfWarn.setEnabled(false);
				tfImage.setEnabled(false);
				tfWrap.setEnabled(false);
				tfScroll.setEnabled(false);
				tfTerritoryXAxis.setEnabled(false);
				tfTerritoryYAxis.setEnabled(false);
				tfTerritoryContinent.setEnabled(false);

				tfTerritoryContinent.setText("Automatically assigned.");

				tfContinentName.addKeyListener(new KeyListener() {

					@Override
					public void keyTyped(KeyEvent e) {
					}

					@Override
					public void keyReleased(KeyEvent e) {
						tfTerritoryContinent.setText(tfContinentName.getText());
					}

					@Override
					public void keyPressed(KeyEvent e) {
					}
				});

				while (true) {
					if (dialog() == JOptionPane.OK_OPTION) {
						if (tfMapName.getText().trim().isEmpty() || tfContinentName.getText().trim().isEmpty()
								|| tfContinentValue.getText().trim().isEmpty()
								|| tfTerritoryName.getText().trim().isEmpty()
								|| tfTerritoryContinent.getText().trim().isEmpty()) {

							JOptionPane.showMessageDialog(jpPlayground, "All fields are mandatory, cannot be empty.");

						} else if (!tfContinentValue.getText().trim().matches("-?\\d+(\\.\\d+)?")) {
							JOptionPane.showMessageDialog(jpPlayground, "Continent Value should be a number.");
						} else {
							newMap.setMapName(tfMapName.getText().trim() + ".map");

							newMap.setAuthorVal(tfAuthorName.getText().trim());
							newMap.setWarnVal(tfWarn.getText().trim());
							newMap.setImageVal(tfImage.getText().trim());
							newMap.setWarnVal(tfWrap.getText().trim());
							newMap.setScrollVal(tfScroll.getText().trim());

							newMap.setContName(tfContinentName.getText().trim());
							newMap.setContVal(tfContinentValue.getText().trim());

							newMap.setTerritoryName(tfTerritoryName.getText().trim());
							newMap.setTerritoryCordX(tfTerritoryXAxis.getText().trim());
							newMap.setTerritoryCordY(tfTerritoryYAxis.getText().trim());
							newMap.setTerritoryContinent(tfTerritoryContinent.getText().trim());
							MapWriter mapWriter = new MapWriter("mapTemplate.map");
							String status = "OK";
							try {
								status = mapWriter.saveNewMapFromSracth(newMap);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							if (!status.equals("OK")) {
								JOptionPane.showMessageDialog(jpPlayground, "Error: " + status);
							} else {
								JOptionPane.showMessageDialog(jpPlayground, "Saved successfully.");
								MapEditor editor = new MapEditor(newMap.getMapName());
								try {
									editor.loadMap();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}

							break;
						}

					} else {
						break;
					}
				}
			}

			/**
			 * a method to create a dialogue box
			 * 
			 * @return the message
			 */
			private int dialog() {
				return JOptionPane.showConfirmDialog(jpPlayground, map, "Map from Scratch",
						JOptionPane.OK_CANCEL_OPTION);
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
						.addGap(59, 59, 59)
						.addComponent(btMapEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(59, 59, 59).addComponent(btmapFromScratch, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
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
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btmapFromScratch, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE))

						.addGap(312, 312, 312)));

		getContentPane().add(jPanelTop);
		jPanelTop.setBounds(10, 10, 960, 60);

		jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		jpPlayground.setBackground(new java.awt.Color(204, 204, 204));
		jpPlayground.setPreferredSize(new java.awt.Dimension(1000, 100));
		jpPlayground.setLayout(new javax.swing.BoxLayout(jpPlayground, javax.swing.BoxLayout.LINE_AXIS));
		jScrollPane1.setViewportView(jpPlayground);

		getContentPane().add(jScrollPane1);
		jScrollPane1.setBounds(10, 90, 960, 420);

		taObserverMessage.setColumns(20);
		taObserverMessage.setFont(new java.awt.Font("Monospaced", 0, 18));
		taObserverMessage.setRows(5);
		taObserverMessage.setWrapStyleWord(true);
		jScrollPane2.setViewportView(taObserverMessage);

		getContentPane().add(jScrollPane2);
		jScrollPane2.setBounds(980, 90, 270, 170);

		jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel3.setText("My Observer");
		getContentPane().add(jLabel3);
		jLabel3.setBounds(990, 60, 230, 30);

		jPanel2.setBackground(new java.awt.Color(102, 102, 102));
		jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		lbPlayer1.setFont(new java.awt.Font("Tahoma", 1, 14));
		lbPlayer1.setForeground(new java.awt.Color(204, 255, 255));
		lbPlayer1.setText("");

		lbPlayer2.setFont(new java.awt.Font("Tahoma", 1, 14));
		lbPlayer2.setForeground(new java.awt.Color(204, 255, 204));
		lbPlayer2.setText("");

		lbPlayer3.setFont(new java.awt.Font("Tahoma", 1, 14));
		lbPlayer3.setForeground(new java.awt.Color(255, 255, 204));
		lbPlayer3.setText("");

		lbPlayer4.setFont(new java.awt.Font("Tahoma", 1, 14));
		lbPlayer4.setForeground(new java.awt.Color(255, 204, 204));
		lbPlayer4.setText("");

		lbPlayer5.setFont(new java.awt.Font("Tahoma", 1, 14));
		lbPlayer5.setForeground(new java.awt.Color(255, 241, 186));
		lbPlayer5.setText("");

		lbPlayer6.setFont(new java.awt.Font("Tahoma", 1, 14));
		lbPlayer6.setForeground(new java.awt.Color(204, 153, 255));
		lbPlayer6.setText("");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
						.addContainerGap(67, Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(lbPlayer6, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
								.addGroup(jPanel2Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(lbPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, 191,
												Short.MAX_VALUE)
										.addComponent(lbPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lbPlayer3, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lbPlayer4, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lbPlayer5, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(16, 16, 16)
						.addComponent(lbPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(lbPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(lbPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(lbPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(lbPlayer5, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lbPlayer6, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		getContentPane().add(jPanel2);
		jPanel2.setBounds(980, 290, 270, 220);

		jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel4.setText("Territory Strength View");
		getContentPane().add(jLabel4);
		jLabel4.setBounds(990, 270, 220, 14);

		btMapEditor.setVisible(true);
		btmapFromScratch.setVisible(true);

		pack();
	}

	/**
	 * browse button action performed, browse and select file
	 * 
	 * @param evt
	 *            an action event component
	 */
	private void btBrowseActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser ch = new JFileChooser();
		int ans = ch.showOpenDialog(this);
		if (ans == JFileChooser.APPROVE_OPTION) {
			mapFile = ch.getSelectedFile();
			tfMapFile.setText(mapFile.getName());
			tfMapFile.setToolTipText(mapFile.getPath());
			CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Map File Loaded into Game");
			LoggerUtility.consoleHandler.publish(logRecord);
		}
	}

	/**
	 * load button action performed, loads map to playground
	 * 
	 * @param evt
	 */
	private void btLoadActionPerformed(java.awt.event.ActionEvent evt) {
		if (mapFile != null) {
			mapDetails = MapReader.readMapFile(mapFile.getPath());
			if (mapDetails != null) {
				
				btMapEditor.setVisible(false);
				btmapFromScratch.setVisible(false);
				
				for (int i = 0; i < labels.length; i++) {
					labels[i].setVisible(false);
				}
				int playerCount = Integer.parseInt((String) cbPlayerCount.getSelectedItem());

				for (int i = 0; i < playerCount; i++) {
					labels[i].setVisible(true);
				}

				player = new Player[playerCount];
				jpPlayground.removeAll();
				jpPlayground.setPreferredSize(new Dimension(370 * playerCount, 400));
				for (int i = 0; i < playerCount; i++) {
					player[i] = new Player(i, player, mapDetails);
					player[i].addObserver(RiskGame.this);
					player[i].setPlayerPanel(new PlayerPanel());
					player[i].bindListeners();
					player[i].currentGameStaticsList = new ArrayList<>();
					player[i].currentGameStaticsTableModel = new CurrentGameStaticsTableModel(
							player[i].currentGameStaticsList);
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
				ArrayList<String> keyList = new ArrayList<>(territories.keySet());
				Collections.shuffle(keyList);

				int p = 0;
				while (true) {
					try {
						for (int i = 0; i < playerCount; i++) {
							Territory t = territories.get(keyList.get(p));
							CurrentGameStatics cgs = new CurrentGameStatics(1, t, i);
							player[i].currentGameStaticsList.add(cgs);
							p++;
						}
					} catch (Exception ex) {
						break;
					}
				}
				for (int i = 0; i < playerCount; i++) {
					player[i].infantriesAvailable = (totalArmies[playerCount - 2])
							- (player[i].currentGameStaticsList.size());
					player[i].getPlayerPanel().lbAvailableArmies
							.setText("Available Infantries : " + player[i].infantriesAvailable);
					player[i].currentGameStaticsTableModel.fireTableDataChanged(); // Display data in table

					player[i].getPlayerPanel().btPlaceInfantry.setEnabled(false);
					player[i].getPlayerPanel().btReinforcement.setEnabled(false);
					player[i].getPlayerPanel().btFortification.setEnabled(false);
					player[i].getPlayerPanel().btOk.setEnabled(false);

					double per = (player[i].currentGameStaticsList.size() * 100.0) / territories.size();
					DecimalFormat df = new DecimalFormat("##.##");
					per = Double.parseDouble(df.format(per));
					labels[i].setText("Player " + (i + 1) + " - " + per + "%");

				}
				player[0].getPlayerPanel().btPlaceInfantry.setEnabled(true);

				CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
						"Territories are distributed randomly among players");
				LoggerUtility.consoleHandler.publish(logRecord);

			} else {
				JOptionPane.showMessageDialog(this, "Map File could not read or invalid map file. Try again !");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Map File could not read. Try again !");
		}
	}

	/**
	 * The main class
	 * 
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
	private javax.swing.JButton btmapFromScratch;
	private javax.swing.JComboBox<String> cbPlayerCount;
	private javax.swing.JLabel jLabelPlayerCount;
	private javax.swing.JLabel jLabelSelectMap;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JPanel jPanelTop;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JPanel jpPlayground;
	private javax.swing.JLabel lbPlayer1;
	private javax.swing.JLabel lbPlayer2;
	private javax.swing.JLabel lbPlayer3;
	private javax.swing.JLabel lbPlayer4;
	private javax.swing.JLabel lbPlayer5;
	private javax.swing.JLabel lbPlayer6;
	public javax.swing.JTextArea taObserverMessage;
	private javax.swing.JTextField tfMapFile;

	/**
	 * A method to implement observable pattern
	 * 
	 * @param o
	 *            an object of observable class
	 * @param arg
	 *            an object of object class
	 */
	public void update(Observable o, Object arg) {

		Player playerObservable = (Player) o;

		String message = playerObservable.getMessage();
		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Observable : " + message);
		LoggerUtility.consoleHandler.publish(logRecord);
		if (message.startsWith("Percentage ")) {
			String percentageValues = message.substring(11);
			String values[] = percentageValues.split(",");
			for (int i = 0; i < values.length; i++) {
				labels[i].setText("Player " + (i + 1) + " - " + values[i] + "%");
			}
		} else {
			taObserverMessage.setText(message);
		}

	}
}
