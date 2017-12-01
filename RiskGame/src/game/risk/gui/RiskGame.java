package game.risk.gui;

import game.risk.gui.PlayerPanel;
import game.risk.model.GameReader;
import game.risk.model.GameWriter;
import game.risk.model.MapReader;
import game.risk.model.MapWriter;
import game.risk.model.entities.Card;
import game.risk.model.entities.CurrentGameStatics;
import game.risk.model.entities.CurrentGameStaticsTableModel;
import game.risk.model.entities.GamePhaseEnum;
import game.risk.model.entities.NeighbourListModel;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.model.entities.Territory;
import game.risk.model.entities.strategy.AggressivePlayerStrategy;
import game.risk.model.entities.strategy.BenevolentPlayerStrategy;
import game.risk.model.entities.strategy.CheaterPlayerStrategy;
import game.risk.model.entities.strategy.HumanStrategy;
import game.risk.model.entities.strategy.RandomPlayerStrategy;
import game.risk.util.LoggerUtility;
import game.risk.util.CustomLogRecord;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

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
	File savedFile; // file of saved game
	RiskMap mapDetails;
	int totalArmies[] = { 40, 35, 30, 25, 20 };
	Color colors[] = new Color[6];

	public int cardTurnIndex = 1;

	Player player[];
	public ArrayList<Card> alCards;
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

		alCards = new ArrayList<>();

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
		tfLoadedGameFile = new javax.swing.JTextField();
		jLabelSelectMap = new javax.swing.JLabel();
		btBrowse = new javax.swing.JButton();
		jLabelPlayerCount = new javax.swing.JLabel();
		cbPlayerCount = new javax.swing.JComboBox<>();

		btLoad = new javax.swing.JButton();
		btLoadGame = new javax.swing.JButton("Load Game");
		btTournament = new javax.swing.JButton("Play T.M.");
		btMapEditor = new javax.swing.JButton();
		btmapFromScratch = new JButton("M.F.S.");
		btmapFromScratch.setToolTipText("Map from Scratch");
		btSave = new JButton("Save Game");
		btSave.setEnabled(false);

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
		tfLoadedGameFile.setFocusable(false);
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
		btTournament.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btTournamentActionPerformed(evt);
			}
		});
		btLoadGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btLoadGameActionPerformed(evt);
			}
		});
		btSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				GameWriter gameWriter = new GameWriter(tfMapFile.getText());
				gameWriter.saveGame(player);
				JOptionPane.showMessageDialog(jpPlayground, "All Players state have saved");
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
					btSave.setEnabled(true);
					int playerCount = Integer.parseInt((String) cbPlayerCount.getSelectedItem());
					HashMap<Integer, String> playerType = new HashMap<Integer, String>();
					if (selectPlayerTypes(playerCount, playerType)) {
						btLoadActionPerformed(playerCount, playerType);
					}

				} else {
					btSave.setEnabled(false);
					JOptionPane.showMessageDialog(jpPlayground, "Map File could not read or invalid file. Try again !");
				}
			}

			private boolean selectPlayerTypes(int playerCount, HashMap<Integer, String> playerType) {
				boolean retr = false;

				javax.swing.ButtonGroup[] groups = new javax.swing.ButtonGroup[playerCount];

				javax.swing.JPanel panel = new javax.swing.JPanel(new GridLayout(0, 5));
				for (int i = 1; i < playerCount; i++) {
					// javax.swing.ButtonGroup group = new javax.swing.ButtonGroup();
					javax.swing.JRadioButton aggressive = new javax.swing.JRadioButton("Aggressive", true);
					javax.swing.JRadioButton benevolent = new javax.swing.JRadioButton("Benevolent");
					javax.swing.JRadioButton random = new javax.swing.JRadioButton("Random");
					javax.swing.JRadioButton cheater = new javax.swing.JRadioButton("Cheater");

					aggressive.setActionCommand("Aggressive");
					benevolent.setActionCommand("Benevolent");
					random.setActionCommand("Random");
					cheater.setActionCommand("Cheater");
					groups[i] = new javax.swing.ButtonGroup();
					groups[i].add(aggressive);
					groups[i].add(benevolent);
					groups[i].add(random);
					groups[i].add(cheater);
					// group.add(new JButton());

					panel.add(new JLabel("Computer " + i + ": "));
					panel.add(aggressive);
					panel.add(benevolent);
					panel.add(random);
					panel.add(cheater);
				}

				panel.setVisible(true);
				Object[] map = { "Select player type: ", panel };

				if (JOptionPane.showConfirmDialog(jpPlayground, map, "Map from Scratch",
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					Component[] c = panel.getComponents();

					for (int j = 0; j < groups.length; j++) {
						javax.swing.ButtonGroup group = groups[j];
						if (group != null) {
							// System.out.println(group.getSelection().getActionCommand());
							String player = group.getSelection().getActionCommand();
							playerType.put(j, player);
						}
					}
					retr = true;
				}
				return retr;
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
						.addGap(5, 5, 5)
						.addComponent(tfMapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 132,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(5, 5, 5)
						.addComponent(btBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20)
						.addComponent(jLabelPlayerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 97,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(5, 5, 5)
						.addComponent(cbPlayerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(10, 10, 10)
						.addComponent(btLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20)
						.addComponent(btMapEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20)

						.addGap(20, 20, 20)
						.addComponent(btmapFromScratch, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(10, 10, 10)
						.addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20)
						.addComponent(tfLoadedGameFile, javax.swing.GroupLayout.PREFERRED_SIZE, 132,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(12, 12, 12)
						.addComponent(btLoadGame, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(btTournament, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
								javax.swing.GroupLayout.PREFERRED_SIZE)));
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
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(tfLoadedGameFile, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btLoadGame, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btTournament, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE))

						.addGap(312, 312, 312)));

		getContentPane().add(jPanelTop);
		jPanelTop.setBounds(10, 10, 1250, 60);

		jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		jpPlayground.setBackground(new java.awt.Color(204, 204, 204));
		jpPlayground.setPreferredSize(new java.awt.Dimension(1000, 100));
		jpPlayground.setLayout(new javax.swing.BoxLayout(jpPlayground, javax.swing.BoxLayout.LINE_AXIS));
		jScrollPane1.setViewportView(jpPlayground);

		getContentPane().add(jScrollPane1);
		jScrollPane1.setBounds(10, 90, 960, 420);

		taObserverMessage.setColumns(20);
		taObserverMessage.setFont(new java.awt.Font("Monospaced", 0, 12));
		taObserverMessage.setRows(5);
		taObserverMessage.setWrapStyleWord(true);
		taObserverMessage.setEditable(false);
		jScrollPane2.setViewportView(taObserverMessage);

		getContentPane().add(jScrollPane2);
		jScrollPane2.setBounds(980, 90, 270, 170);

		jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel3.setText("Phase view");
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
		jLabel4.setText("World Domination View");
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
	 * Method for Load button
	 * 
	 * @param evt
	 *            an action event component
	 */
	private void btLoadGameActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser ch = new JFileChooser();
		int ans = ch.showOpenDialog(this);
		if (ans == JFileChooser.APPROVE_OPTION) {
			savedFile = ch.getSelectedFile();
			tfLoadedGameFile.setText(savedFile.getName());
			tfLoadedGameFile.setToolTipText(savedFile.getPath());
			if (tfLoadedGameFile.getText().toLowerCase().endsWith(".ser")) {
				GameReader gameReader = new GameReader(tfLoadedGameFile.getText());
				player=gameReader.readGame();
				String mapFileName = savedFile.getName().split("_")[0];
				mapFileName+=".map";
				mapFile = new File(mapFileName);
				mapDetails = MapReader.readMapFile(mapFile.getPath());
				loadSavedPlayersOnPanel();
				btSave.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(jpPlayground, "Loaded Game File could not read or invalid file. Try again !");
			}
			CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Saved File Loaded into Game");
			LoggerUtility.consoleHandler.publish(logRecord);
		}
	}
	
	private void loadSavedPlayersOnPanel() {

		btMapEditor.setVisible(false);
		btmapFromScratch.setVisible(false);

		for (int i = 0; i < labels.length; i++) {
			labels[i].setVisible(false);
		}

		for (int i = 0; i < player.length; i++) {
			labels[i].setVisible(true);
		}

		jpPlayground.removeAll();
		jpPlayground.setPreferredSize(new Dimension(370 * player.length, 400));
		CustomLogRecord logRecord = null;

		for (int i = 0; i < player.length; i++) {

			// player[i] = new Player(RiskGame.this, i, player, mapDetails);
			player[i].addObserver(RiskGame.this);
			player[i].riskGame = RiskGame.this;
			// player[i].getPlayerPanel().lbTotalArmies.setText("Total
			// Infantries : " + player[i].infantriesTotal);
			if (i == 0) {
				player[i].bindListeners();
				player[i].getPlayerPanel().lbPlayer.setText("Human Player : " + (i + 1));
			} else {
				player[i].bindListeners();
				player[i].getPlayerPanel().lbPlayer.setText(player[i].getName() + " Player: " + (i + 1));
			}
			jpPlayground.add(player[i].getPlayerPanel());

		}
		int p = 0;
		int m = 0;
		String cardDesigns[] = { "infantry", "cavalry", "artillery" };
				for (Territory t : mapDetails.getTerritories().values()) {
					alCards.add(new Card(t, cardDesigns[m]));
					p++;
					m++;
					if (m == 3)
						m = 0;
				}

		for (int i = 0; i < player.length; i++) {
			/*player[i].getPlayerPanel().lbAvailableArmies
					.setText("Available Infantries : " + player[i].infantriesAvailable);*/
			player[i].getPlayerPanel().btPlaceInfantry.setEnabled(false);
			player[i].getPlayerPanel().btReinforcement.setEnabled(false);
			player[i].getPlayerPanel().btFortification.setEnabled(false);
			player[i].getPlayerPanel().btOk.setEnabled(false);
			// Display data in table
			player[i].currentGameStaticsTableModel.fireTableDataChanged();
			if (i == 0) {
				if (player[i].infantriesAvailable > 0) {
					if (player[i].getPhase().equalsIgnoreCase(GamePhaseEnum.STARTUP.name())) {
						player[i].getPlayerPanel().btPlaceInfantry.setEnabled(true);
					} else if (player[i].getPhase().equalsIgnoreCase(GamePhaseEnum.REINFORCEMENT.name())) {
						player[i].getPlayerPanel().btReinforcement.setEnabled(true);
					}
				}
				if (player[i].getPhase().equalsIgnoreCase(GamePhaseEnum.FORTIFICATION.name())) {
					player[i].getPlayerPanel().btFortification.setEnabled(true);
					player[i].getPlayerPanel().btOk.setEnabled(true);
				}
			}
			
			double per = (player[i].currentGameStaticsList.size() * 100.0) / mapDetails.getTerritories().size();
			DecimalFormat df = new DecimalFormat("##.##");
			per = Double.parseDouble(df.format(per));
			if (per != 100) {
				labels[i].setText("Player " + (i + 1) + " - " + per + "%");
			} else {
				labels[i].setText("Player " + (i + 1) + " - " + "WINS");
				jpPlayground.removeAll();
				jpPlayground.revalidate();
				jpPlayground.repaint();
			}
		}

	}

	/**
	 * Method for tournament button
	 * 
	 * @param evt
	 *            an action event component
	 */
	private void btTournamentActionPerformed(java.awt.event.ActionEvent evt) {
		// TournamentTest tour=new TournamentTest();
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Tournament().setVisible(true);

			}
		});

	}

	/**
	 * load button action performed, loads map to playground
	 * 
	 * @param playerCount
	 *            number of players
	 * @param playerType
	 *            a hashMap
	 * @param evt
	 *            an action event component
	 */
	private void btLoadActionPerformed(int playerCount, HashMap<Integer, String> playerType) {
		if (mapFile != null) {
			mapDetails = MapReader.readMapFile(mapFile.getPath());
			if (mapDetails != null) {

				btMapEditor.setVisible(false);
				btmapFromScratch.setVisible(false);

				for (int i = 0; i < labels.length; i++) {
					labels[i].setVisible(false);
				}

				for (int i = 0; i < playerCount; i++) {
					labels[i].setVisible(true);
				}

				player = new Player[playerCount];
				jpPlayground.removeAll();
				jpPlayground.setPreferredSize(new Dimension(370 * playerCount, 400));
				CustomLogRecord logRecord = null;

				for (int i = 0; i < playerCount; i++) {
					if (i == 0) {
						// human for player index 0
						player[i] = new Player(RiskGame.this, i, player, mapDetails);
						player[i].setComputer(false);
						player[i].setName("Human");
						player[i].setStrategy(new HumanStrategy());
						logRecord = new CustomLogRecord(Level.INFO, "Strategy: HUMAN");
						// LoggerUtility.consoleHandler.publish(logRecord);
					} else {
						player[i] = new Player(RiskGame.this, i, player, mapDetails);
						player[i].setComputer(true);
						player[i].setName(playerType.get(i) + "" + i);

						if (playerType.get(i).equalsIgnoreCase("aggressive")) {
							logRecord = new CustomLogRecord(Level.INFO, "Strategy: Aggressive");
							// LoggerUtility.consoleHandler.publish(logRecord);
							player[i].setStrategy(new AggressivePlayerStrategy());

						} else if (playerType.get(i).equalsIgnoreCase("benevolent")) {
							logRecord = new CustomLogRecord(Level.INFO, "Strategy: Benevolent Player Strategy");
							// LoggerUtility.consoleHandler.publish(logRecord);
							player[i].setStrategy(new BenevolentPlayerStrategy());

						} else if (playerType.get(i).equalsIgnoreCase("random")) {
							logRecord = new CustomLogRecord(Level.INFO, "Strategy: Random Player Strategy");
							// LoggerUtility.consoleHandler.publish(logRecord);
							player[i].setStrategy(new RandomPlayerStrategy());

						} else {
							logRecord = new CustomLogRecord(Level.INFO, "Strategy: Cheater Player Strategy");
							// LoggerUtility.consoleHandler.publish(logRecord);
							player[i].setStrategy(new CheaterPlayerStrategy());

						}

					}
					// player[i] = new Player(RiskGame.this, i, player, mapDetails);
					player[i].addObserver(RiskGame.this);
					player[i].setPlayerPanel(new PlayerPanel());

					player[i].currentGameStaticsList = new ArrayList<>();
					player[i].currentGameStaticsTableModel = new CurrentGameStaticsTableModel(
							player[i].currentGameStaticsList);
					player[i].getPlayerPanel().jtCountriesAndArmies.setModel(player[i].currentGameStaticsTableModel);

					player[i].neighbours = new ArrayList<>();

					player[i].neighbourListModel = new NeighbourListModel(player[i].neighbours);

					player[i].getPlayerPanel().lsNeighbour.setModel(player[i].neighbourListModel);
					player[i].getPlayerPanel().setBackground(colors[i]);

					player[i].infantriesTotal = totalArmies[playerCount - 2];
					player[i].getPlayerPanel().lbTotalArmies.setText("Total Infantries : " + player[i].infantriesTotal);
					if (i == 0) {
						player[i].bindListeners();
						player[i].getPlayerPanel().lbPlayer.setText("Human Player : " + (i + 1));
					} else {
						player[i].bindListeners();
						player[i].getPlayerPanel().lbPlayer.setText(playerType.get(i) + " Player: " + (i + 1));
					}
					jpPlayground.add(player[i].getPlayerPanel());

				}

				HashMap<String, Territory> territories = mapDetails.getTerritories();
				ArrayList<String> keyList = new ArrayList<>(territories.keySet());
				Collections.shuffle(keyList);

				int p = 0;
				int m = 0;
				String cardDesigns[] = { "infantry", "cavalry", "artillery" };
				while (true) {
					try {
						for (int i = 0; i < playerCount; i++) {
							Territory t = territories.get(keyList.get(p));
							CurrentGameStatics cgs = new CurrentGameStatics(1, t, i);
							player[i].currentGameStaticsList.add(cgs);
							alCards.add(new Card(t, cardDesigns[m]));
							p++;
							m++;
							if (m == 3)
								m = 0;
						}
					} catch (Exception ex) {
						break;
					}
				}
				for (int i = 0; i < playerCount; i++) {
					player[i].setPhase(GamePhaseEnum.STARTUP.name());
					player[i].infantriesAvailable =(totalArmies[playerCount - 2])- (player[i].currentGameStaticsList.size());
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
					if (per != 100) {
						labels[i].setText("Player " + (i + 1) + " - " + per + "%");
					} else {
						labels[i].setText("Player " + (i + 1) + " - " + "WINS");
						jpPlayground.removeAll();
						jpPlayground.revalidate();
						jpPlayground.repaint();
					}
				}

				player[0].getPlayerPanel().btPlaceInfantry.setEnabled(true);

				logRecord = new CustomLogRecord(Level.INFO, "Territories are distributed randomly among players");
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
	//button to save game
	private javax.swing.JButton btSave;
	private javax.swing.JButton btLoad;
	//button to load game
	private javax.swing.JButton btLoadGame;
	private javax.swing.JButton btTournament;
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
	private javax.swing.JTextField tfLoadedGameFile;

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
		String taMsg = "";
		if (message.startsWith("Percentage ")) {
			String percentageValues = message.substring(11);
			String values[] = percentageValues.split(",");
			for (int i = 0; i < values.length; i++) {
				if (!values[i].contains("100")) {
					labels[i].setText("Player " + (i + 1) + " - " + values[i] + "%");
				} else {
					labels[i].setText("Player " + (i + 1) + " - " + "WINS");
					jpPlayground.removeAll();
					jpPlayground.revalidate();
					jpPlayground.repaint();
					break;
				}

			}
		} else if (message.startsWith("RiskCardInfantries")) {
			String values[] = message.split(",");
			int index = Integer.parseInt(values[1]);
			taMsg += taObserverMessage.getText() + "\n" + message + "\n";
			taObserverMessage.setText(taMsg);
			player[index].getPlayerPanel().lbMessage3.setText(values[2]);

		} else {
			taMsg += taObserverMessage.getText() + "\n" + message + "\n";
			taObserverMessage.setText(taMsg);
		}

		if (taMsg.length() >= 1000) {
			taMsg = taMsg.substring(taMsg.length() - 1000, taMsg.length());
			taObserverMessage.setText(taMsg);
		}

	}
}
