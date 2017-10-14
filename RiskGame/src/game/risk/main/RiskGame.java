package game.risk.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import game.risk.model.DistributeCountries;
import game.risk.model.GameDriver;
import game.risk.model.Player;
import game.risk.model.Territory;
import game.risk.util.MapReader;
import game.risk.util.RiskGameConstants;
import game.risk.util.RiskMap;


public class RiskGame {

	private static String mapSeleted = "";
	private static int computersSeleted = 0;
	private static JFrame jFrame = null;
	private static GameDriver gameDriver = null;

	private static String SELECTED_MAP;
	private static int SELECTED_NO_OF_COMPUTERS;
	private static int NO_OF_HUMAN = 1;
	private static int SET_NO_OF_CONTINETS;
	private static int SET_NO_OF_TERRITORIES;
	private static int TOTAL_PLAYERS;
	
	private static JLabel status;
	private static JPanel middleStartPanel;
	
	public RiskGame() {
		jFrame = new JFrame("RISK");
		gameDriver = new GameDriver();
		status = customJLabel("STATUS", 30, Color.BLACK);
		middleStartPanel = customHeaderAndFooterPanel();
	}
	
	public ArrayList<String> getMapFiles(String folderPath){
		ArrayList<String> files = new ArrayList<String>();
		File folder = new File(folderPath);
		File[] listOfFiles = null;
		if(folder.exists()){
			listOfFiles = folder.listFiles();
		}
		
		  if(listOfFiles != null){
			  for (int i = 0; i < listOfFiles.length; i++) {
				  if (listOfFiles[i].isFile() && listOfFiles[i].toString().endsWith(".map")) {
					  files.add(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length()-4));
				  }
			  }
		  }
		  
		return files;
	} 
	
	public static JPanel headerPanel(String status){
		JPanel headerPanel = customHeaderAndFooterPanel();
		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	        
        headerPanel.setPreferredSize(new Dimension(600, 40));
        headerPanel.add(customJLabel(RiskGameConstants.GAME_NAME, 30, Color.BLACK));
        headerPanel.add(customJLabel(status, 20, Color.BLACK));
		return headerPanel;
	}
	
	public static JPanel footerPanel(){
		JPanel footerPanel = customHeaderAndFooterPanel();
		//status.setLayout(new BorderLayout());//.CENTER);
		status.setHorizontalAlignment(JLabel.CENTER);
		status.setVerticalAlignment(JLabel.CENTER);
		status.setHorizontalAlignment(SwingConstants.RIGHT);
status.setAlignmentX(Component.RIGHT_ALIGNMENT);
		footerPanel.add(status, BorderLayout.CENTER);
		footerPanel.setPreferredSize(new Dimension(600, 40));
        footerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		return footerPanel;
	}
	
	public void setGamePanels(JPanel middlePanel, String status){
        
        middlePanel.setLayout(new GridLayout(0,1));
        
        jFrame.getContentPane().add(headerPanel(status), BorderLayout.NORTH);
        jFrame.getContentPane().add(middlePanel, BorderLayout.CENTER);
        jFrame.getContentPane().add(footerPanel(), BorderLayout.SOUTH);
        jFrame.pack();

        jFrame.setSize(800,800);  
        //jFrame.setLayout(new GridBagLayout());
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	public JPanel startPanel(ArrayList<String> files){
		middleStartPanel = customHeaderAndFooterPanel();
		DefaultListModel<String> tempList = new DefaultListModel<>(); 
		for(String temp: files){
			tempList.addElement(temp);  
		}
		JList<String> listOfMaps = new JList<String>(tempList);
		
		JLabel warning = customJLabel("Select a Map?", 15, Color.RED);
		
		
		middleStartPanel.add(customJLabel(RiskGameConstants.SELECT_MAP, 20, Color.BLACK));
		middleStartPanel.add(warning);
		warning.setVisible(false);
		middleStartPanel.add(listOfMaps); 
		
		JPanel temp = customHeaderAndFooterPanel();
		temp.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton okButton = customJButton(RiskGameConstants.OK, 15);
		temp.add(okButton); 
		JButton cancelButton = customJButton(RiskGameConstants.CANCEL, 15);
		temp.add(cancelButton); 
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listOfMaps.getSelectedValue()!= null){
					mapSeleted = listOfMaps.getSelectedValue();
					
					SELECTED_MAP = mapSeleted+".map";
					
					middleStartPanel.removeAll();
					middleStartPanel.revalidate();
					middleStartPanel.repaint();
					middleStartPanel = secondPanel(SELECTED_MAP);
				}else{
					warning.setVisible(true);
					Timer timer = new Timer(3000, new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			            	warning.setVisible(false);
			            }
			        });
			        timer.start();
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		middleStartPanel.add(temp);
		return middleStartPanel; 
	}
	
	public JPanel secondPanel(String mapSeleted){
		//middleStartPanel = customHeaderAndFooterPanel();
		
		middleStartPanel.add(customJLabel(RiskGameConstants.NO_OF_COMPUTERS, 20, Color.BLACK));
		
		String noOfPlayers[]={"1", "2", "3", "4"};        
	    JComboBox cb=new JComboBox(noOfPlayers);    
	    middleStartPanel.add(cb);        
		
		JPanel temp = customHeaderAndFooterPanel();
		temp.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton okButton = customJButton(RiskGameConstants.OK, 15);
		temp.add(okButton); 
		JButton cancelButton = customJButton(RiskGameConstants.CANCEL, 15);
		temp.add(cancelButton); 
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cb.getSelectedItem()!= null){
					//mapSeleted = listOfMaps.getSelectedValue();
					computersSeleted = Integer.parseInt((String) cb.getSelectedItem());
					SELECTED_NO_OF_COMPUTERS = computersSeleted;
					System.out.println(computersSeleted);
					middleStartPanel.removeAll();
					middleStartPanel.revalidate();
					middleStartPanel.repaint();
					
					TOTAL_PLAYERS = SELECTED_NO_OF_COMPUTERS + NO_OF_HUMAN;
					
					middleStartPanel = thirdPanel(SELECTED_MAP, TOTAL_PLAYERS);
				}else{
					Timer timer = new Timer(3000, new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {

			            }
			        });
			        timer.start();
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		temp.setVisible(true);
		middleStartPanel.add(temp);
		return middleStartPanel; 
	}
	
	public int noOfArmiesPerPlayrIntially(){
		int noOfArmiesPerPlayer = 0;
		if(TOTAL_PLAYERS == 2){
			noOfArmiesPerPlayer = 40;
		} else if(TOTAL_PLAYERS == 3){
			noOfArmiesPerPlayer = 35;
		} else if(TOTAL_PLAYERS == 4){
			noOfArmiesPerPlayer = 30;
		} else if(TOTAL_PLAYERS == 5){
			noOfArmiesPerPlayer = 25;
		}
		
		return noOfArmiesPerPlayer;
		
	}
	
	public JPanel thirdPanel(String SELECTED_MAP, int TOTAL_PLAYERS){
		//middleStartPanel = customHeaderAndFooterPanel();
		MapReader mapreader = new MapReader();
		Map<Integer, List<Territory>> listOfAssignedTerritories = null;
	    try {
			RiskMap riskMap = mapreader.readMap(SELECTED_MAP);
			//HashMap<String, String> map = riskMap.getMap();
			HashMap<String, String> continents = riskMap.getContinents();
			HashMap<String, Territory> territories = riskMap.getTerritories();
			
			listOfAssignedTerritories = DistributeCountries.getCountriesPerPlayer(TOTAL_PLAYERS, 
					 new ArrayList<Territory>(territories.values()));
			
			SET_NO_OF_CONTINETS = continents.size();
			SET_NO_OF_TERRITORIES = territories.size();
			//ArrayList<Territory> Territories = new ArrayList<Territory>();
			ArrayList<Player> players  = new ArrayList<Player>();
			int noOfArmiesPerPlayerIntial = noOfArmiesPerPlayrIntially();
			for(int i=0;i<TOTAL_PLAYERS; i++){
				
				if(i ==0){
					Player human = new Player();
					human.setName("H");
					human.setComputer(false);
					human.setNoOfArmies(noOfArmiesPerPlayerIntial);
					human.setTerritorAndArmiesColor(RiskGameConstants.colorArray[0]);
					Map<Territory,Integer> territoriesAndArmies = new HashMap<Territory, Integer>();
					int sizeOfTerritoriesPerPlayer = listOfAssignedTerritories.get(1).size();
					ArrayList<Territory> onlyTerritories  = new ArrayList<Territory>();
					for(Territory t : listOfAssignedTerritories.get(1)){
						territoriesAndArmies.put(t, 1);
						onlyTerritories.add(t);
					}
					int territoriesAndArmiesNo = listOfAssignedTerritories.size();
					territoryJButton(middleStartPanel, onlyTerritories, human);
					
					human.setTerritorAndArmies(territoriesAndArmies);
					human.setTerritorAndArmiesColor(RiskGameConstants.colorArray[0]);
					human.setNoOfArmies(human.getNoOfArmies()-sizeOfTerritoriesPerPlayer);
					//gameDriver.setPlayer(human);m
					players.add(human);
				}
				else{
					Player comp = new Player();
					comp.setName("C"+i);
					comp.setComputer(true);
					comp.setNoOfArmies(noOfArmiesPerPlayerIntial);
					comp.setTerritorAndArmiesColor(RiskGameConstants.colorArray[i]);
					Map<Territory,Integer> territoriesAndArmies = new HashMap<Territory, Integer>();
					int sizeOfTerritoriesPerPlayer = listOfAssignedTerritories.get(i+1).size();
					
					ArrayList<Territory> onlyTerritoriesForComp  = new ArrayList<Territory>();
					for(Territory t : listOfAssignedTerritories.get(i)){
						territoriesAndArmies.put(t, 1);
						onlyTerritoriesForComp.add(t);
					}
					
					territoryJButton(middleStartPanel, onlyTerritoriesForComp, comp);
					comp.setTerritorAndArmies(territoriesAndArmies);
					comp.setNoOfArmies(comp.getNoOfArmies()-sizeOfTerritoriesPerPlayer);
					players.add(comp);
				}
				
			}
			gameDriver.setListPlayer(players);
			/*for(int i=0 ; i<players.size();i++){
				Player player = (Player) players.get(i);
				//int territorrySize = player.getTerritorAndArmies().size();
				//ArrayList<JButton> listOfButtons = new ArrayList<JButton>();
				
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    middleStartPanel.setVisible(true);   
	    middleStartPanel.setLayout(new FlowLayout()); 
		return middleStartPanel;
	}	
	

	public static void main(String[] args) {
		RiskGame riskGame = new RiskGame();
		JPanel middlePanel = customHeaderAndFooterPanel();
		String folderPath = "Maps";
		ArrayList<String> files = riskGame.getMapFiles(folderPath);
		Boolean mapAvailable = (files.size()>0)? true: false;
		
		if(mapAvailable){
			middlePanel = riskGame.startPanel(files);
			riskGame.setGamePanels(middlePanel, "Map Selection");
		}else{
			middlePanel.add(customJLabel("NO MAPS AVAILABLE, Check folder", 20, Color.RED));
			riskGame.setGamePanels(middlePanel, "Map Selection");
		}
		//riskGame.setGamePanels(middlePanel, "Map Selection");
		
	}

	private static JLabel customJLabel(String name, int font, Color color) {
		JLabel tempLabel = new JLabel(name);
		tempLabel.setFont(new Font("Helvetica", Font.BOLD, font));
		tempLabel.setForeground(color);
		return tempLabel;
	}

	private static JButton customJButton(String name, int font) {
		JButton tempButton = new JButton(name);
		tempButton.setSize(10,10);
		tempButton.setFont(new Font("Helvetica", Font.BOLD, font));
		
		return tempButton;
	}

	private static void territoryJButton(JPanel panel, ArrayList<Territory> onlyTerritories, Player p) {
		
		JButton button;
		JLabel label;
		for(int k=0; k<onlyTerritories.size();k++){
			//territoryJButton(human.getName(), human.getTerritorAndArmiesColor());
			button = customJButton(p.getName(), 12);
	        //button.setText("BUTTON"+i);
	        button.setOpaque(true);
	     	
	        button.setForeground(new Color(Color.HSBtoRGB((float) Math.random(),(float)0.5,(float)0.5)));
	        button.setFocusPainted(true);
	        //button.setFont(new Font("Tahoma", Font.BOLD, 12));
	        //button.setMinimumSize(new Dimension(3,3));
	        
	        label = customJLabel(onlyTerritories.get(k).getName(), 10, p.getTerritorAndArmiesColor());
	        label.setVisible(true);
	        label.setOpaque(false);  
	        
	       // label.setFont(new Font("Tahoma", Font.BOLD, 10));
	       // label.setBounds(x, y-15, 60, 20);
	        //label.setForeground(Color.BLACK);
	        
	        //label.setBackground(Color.red);
	        button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(e.getActionCommand());
					
				}
			});
	        
	        JPanel jp = new JPanel();
	        jp.setVisible(true);
	        jp.setLayout(new GridLayout(2, 1));
	        jp.add(label);
	        jp.add(button);
	        
	        middleStartPanel.add(jp);
		}
		
		JSeparator separator = new JSeparator();
		 separator.setBorder(new LineBorder(Color.GRAY));
		 separator.setPreferredSize(new Dimension(800, 1));
		 separator.setOrientation(SwingConstants.HORIZONTAL);
		 middleStartPanel.add(new JSeparator());
		

	}
	
	private static JPanel customHeaderAndFooterPanel(){
        JPanel result = new JPanel();
        result.setBorder(new EmptyBorder(50, 10, 10, 10));
        result.setPreferredSize(new Dimension(800, 40));
        result.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        return result;
    }
}
