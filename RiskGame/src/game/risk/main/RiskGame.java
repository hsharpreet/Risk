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
import javax.swing.BoxLayout;
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

/**
 * A class to control the game
 * @author Team
 *
 */
public class RiskGame {
  // Declaration
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
	private static File filePath;
	/**
	 * constructor
	 */
	public RiskGame() {
		jFrame = new JFrame("RISK");
		gameDriver = new GameDriver();
		status = customJLabel("", 30, Color.BLACK);
		middleStartPanel = customHeaderAndFooterPanel();
	}
	/**
	 * Method to chech files in folder
	 * @param folderPath
	 * 
	 */
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
	/**
	 * a method for header panel
	 * @param status
	 * 
	 */
	public static JPanel headerPanel(String status){
		//setting the header panel
		JPanel headerPanel = customHeaderAndFooterPanel();
		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	        
        headerPanel.setPreferredSize(new Dimension(600, 40));
        headerPanel.add(customJLabel(RiskGameConstants.GAME_NAME, 30, Color.BLACK));
        headerPanel.add(customJLabel(status, 20, Color.BLACK));
		return headerPanel;
	}
	/**
	 * Method for footerpanel
	 * @return
	 */
	public static JPanel footerPanel(){
		// setting the footer panel
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
	/**
	 * Method to set the game panels
	 * @param middlePanel a Jpanel
	 * @param status Status of the panal
	 */
	public void setGamePanels(JPanel middlePanel, String status){
        // setting the middle panel
        middlePanel.setLayout(new GridLayout(0,1));
        
        jFrame.getContentPane().add(headerPanel(status), BorderLayout.NORTH);
        jFrame.getContentPane().add(middlePanel, BorderLayout.CENTER);
        jFrame.getContentPane().add(footerPanel(), BorderLayout.SOUTH);
        jFrame.pack();

        jFrame.setSize(800,800);  
        //jFrame.setLayout(new GridBagLayout());
        //setting the visibility
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
/**
 * Method to set the start panel
 * @param files
 * @return a Jpanel
 */
	public JPanel startPanel(ArrayList<String> files){
		// setting the middle start panel
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
		// setting the ok button
		JButton okButton = customJButton(RiskGameConstants.OK, 15);
		temp.add(okButton); 
		JButton cancelButton = customJButton(RiskGameConstants.CANCEL, 15);
		temp.add(cancelButton); 
		/**
		 * Method to select the map
		 */
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listOfMaps.getSelectedValue()!= null){
					mapSeleted = listOfMaps.getSelectedValue();
					
					SELECTED_MAP = mapSeleted+".map";
					filePath = new File(RiskGameConstants.FOLDER_FOR_MAPS_PATH+"/"+SELECTED_MAP);
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
		/**
		 * Method for cancel button
		 */
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		middleStartPanel.add(temp);
		return middleStartPanel; 
	}
	/**
	 * Method for second panel
	 * @param mapSeleted map selected by the user
	 * @return a Jpanel
	 */
	public JPanel secondPanel(String mapSeleted){
		//middleStartPanel = customHeaderAndFooterPanel();
		
		middleStartPanel.add(customJLabel(RiskGameConstants.NO_OF_COMPUTERS, 20, Color.BLACK));
		
		String noOfPlayers[]={"1", "2", "3", "4"};        
	    JComboBox cb=new JComboBox(noOfPlayers);    
	    middleStartPanel.add(cb);        
		
		JPanel temp = customHeaderAndFooterPanel();
		temp.setLayout(new FlowLayout(FlowLayout.CENTER));
		// setting the button
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
		/**
		 * Method for cancel button
		 */
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		status.setText("Set computers");
		temp.setVisible(true);
		middleStartPanel.add(temp);
		return middleStartPanel; 
	}
	/**
	 * Method for setting default number of armies randomly for players 
	 * @return an integer storing the number of armies
	 */
	public int defaultNoOfArmiesPerPlayerIntially(){
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
	/**
	 * Method for setting third panel
	 * @param SELECTED_MAP map selected by user
	 * @param TOTAL_PLAYERS number pf players
	 * @return a Jpanel
	 */
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
			
			SET_NO_OF_CONTINETS = continents.size();//number of continents
			SET_NO_OF_TERRITORIES = territories.size();//number of continents
			//ArrayList<Territory> Territories = new ArrayList<Territory>();
			ArrayList<Player> players  = new ArrayList<Player>();//list of players
			int noOfArmiesPerPlayerIntial = defaultNoOfArmiesPerPlayerIntially();
			for(int i=1;i<=TOTAL_PLAYERS; i++){
				
				if(i ==1){
					Player human = new Player();// setting territories and armies for human user
					human.setName("H");
					human.setComputer(false);
					human.setTotalNoOfArmies(noOfArmiesPerPlayerIntial);
					human.setTerritorAndArmiesColor(RiskGameConstants.colorArray[0]);
					Map<Territory,Integer> territoriesAndArmies = new HashMap<Territory, Integer>();
					//int sizeOfTerritoriesPerPlayer = listOfAssignedTerritories.get(1).size();
					ArrayList<Territory> allTerritoriesPerPlayer  = new ArrayList<Territory>();
					for(Territory t : listOfAssignedTerritories.get(i)){
						territoriesAndArmies.put(t, 1);
						allTerritoriesPerPlayer.add(t);
					}
					int noOfAssignedArmiesOnTerritoriesIntially = listOfAssignedTerritories.size();
					territoryJButton(allTerritoriesPerPlayer, human);
					
					human.setTerritorAndArmies(territoriesAndArmies);
					human.setTerritorAndArmiesColor(RiskGameConstants.colorArray[0]);
					human.setCurrentNoOfArmies(human.getTotalNoOfArmies()-noOfAssignedArmiesOnTerritoriesIntially);
					//gameDriver.setPlayer(human);
					players.add(human);
				}
				else{
					Player comp = new Player();// setting territories and armies for computer player
					comp.setName("C"+(i-1));
					comp.setComputer(true);
					comp.setTotalNoOfArmies(noOfArmiesPerPlayerIntial);
					comp.setTerritorAndArmiesColor(RiskGameConstants.colorArray[i-1]);
					Map<Territory,Integer> territoriesAndArmies = new HashMap<Territory, Integer>();
					//int sizeOfTerritoriesPerPlayer = listOfAssignedTerritories.get(i).size();
					
					ArrayList<Territory> allTerritoriesPerComp  = new ArrayList<Territory>();
					for(Territory t : listOfAssignedTerritories.get(i)){
						territoriesAndArmies.put(t, 1);
						allTerritoriesPerComp.add(t);
					}
					int noOfAssignedArmiesOnTerritoriesIntially = listOfAssignedTerritories.size();
					territoryJButton(allTerritoriesPerComp, comp);
					comp.setTerritorAndArmies(territoriesAndArmies);
					comp.setCurrentNoOfArmies(comp.getTotalNoOfArmies()-noOfAssignedArmiesOnTerritoriesIntially);
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
	    status.setText("UPDATE");
	    
	    middleStartPanel.setVisible(true);   
	    middleStartPanel.setLayout(new FlowLayout()); 
		return middleStartPanel;
	}	
	
/**
 * The main method
 * @param args
 */
	public static void main(String[] args) {
		RiskGame riskGame = new RiskGame();// an object of RiskGame class
		JPanel middlePanel = customHeaderAndFooterPanel();
		ArrayList<String> files = riskGame.getMapFiles(RiskGameConstants.FOLDER_FOR_MAPS_PATH);
		Boolean mapAvailable = (files.size()>0)? true: false;
		
		if(mapAvailable){
			middlePanel = riskGame.startPanel(files);// setting the map
			riskGame.setGamePanels(middlePanel, "Map Selection");
		}else{
			middlePanel.add(customJLabel("NO MAPS AVAILABLE, Check folder", 20, Color.RED));
			riskGame.setGamePanels(middlePanel, "Map Selection");
		}
		//riskGame.setGamePanels(middlePanel, "Map Selection");
		
	}
/**
 * Method for custom label 
 * @param name name of the label
 * @param font font for the label
 * @param color color of the label
 * @return a Jpanel
 */
	private static JLabel customJLabel(String name, int font, Color color) {
		JLabel tempLabel = new JLabel(name);
		tempLabel.setFont(new Font("Helvetica", Font.BOLD, font));
		tempLabel.setForeground(color);
		return tempLabel;
	}
/**
 * A method for creating custom button
 * @param name name of the button
 * @param font font of the button
 * @return a Jbutton
 */
	private static JButton customJButton(String name, int font) {
		JButton tempButton = new JButton(name);
		tempButton.setSize(10,10);
		tempButton.setFont(new Font("Helvetica", Font.BOLD, font));
		
		return tempButton;
	}
	
	
/**
 * Method for creating a button for territory
 * @param onlyTerritories territories of player
 * @param p the player
 */
	private static void territoryJButton(ArrayList<Territory> onlyTerritories, Player p) {
		
		JButton button;
		JLabel label;
		JLabel label2;
		JLabel label3;
		for(int k=0; k<onlyTerritories.size();k++){// setting the display
			//territoryJButton(human.getName(), human.getTerritorAndArmiesColor());
			button = customJButton(onlyTerritories.get(k).getName(), 12);
	        //button.setText("BUTTON"+i);
	        button.setOpaque(true);
	     	
	        button.setForeground((new Color((int) (Math.random() * (double) (0xFFFFFF)))));
	        button.setFocusPainted(true);
	        //button.setFont(new Font("Tahoma", Font.BOLD, 12));
	        //button.setMinimumSize(new Dimension(3,3));
	        
	        label = customJLabel(p.getName(), 10, p.getTerritorAndArmiesColor());
	        label.setVisible(true);
	        label.setOpaque(false);  
	        
	        label2 = customJLabel("-1"+new MapReader().getContinentOfACountry(onlyTerritories.get(k).getName(), 
	        		filePath.getName()), 10, p.getTerritorAndArmiesColor());
	        label2.setVisible(true);
	        label2.setOpaque(false);  
	       // label.setFont(new Font("Tahoma", Font.BOLD, 10));
	       // label.setBounds(x, y-15, 60, 20);
	        //label.setForeground(Color.BLACK);
	        
	        //label.setBackground(Color.red);
	        label3 = customJLabel(" - 1", 10, p.getTerritorAndArmiesColor());
	        JPanel jp2 = new JPanel();
	        jp2.setVisible(true);
	        jp2.setLayout(new FlowLayout());
	        jp2.add(label);
	        jp2.add(label2);
	        
	        JPanel jp = new JPanel();
	        jp.setVisible(true);
	        jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));

	        //jp.setLayout(new GridLayout(3,1));
	        jp.add(jp2);
	        jp.add(label2);
	        jp.add(button);
	        jp.setBorder(BorderFactory.createMatteBorder(5, 1, 1, 1, Color.BLACK));
	        jp.setOpaque(true);
	        
	        button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(e.getActionCommand());
					/*JLabel c = (JLabel)jp.getComponent(1);
					System.out.println(c.getText());
					c.setText("haaaaaaaaaa");*/
				}
			});
	        
	        middleStartPanel.add(jp);
		}
	}
	/**
	 * Method for custom header and footer
	 * @return a Jpanel
	 */
	private static JPanel customHeaderAndFooterPanel(){
        JPanel result = new JPanel();
        result.setBorder(new EmptyBorder(50, 10, 10, 10));
        result.setPreferredSize(new Dimension(800, 40));
        result.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        return result;
    }
}
