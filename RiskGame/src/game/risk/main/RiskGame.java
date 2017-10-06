package game.risk.main;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import game.risk.model.GameDriver;
import game.risk.model.Player;
import game.risk.model.Territory;
import game.risk.util.MapReader;
import game.risk.util.RiskGameConstants;
import game.risk.util.RiskMap;

public class RiskGame {

	static ArrayList<String> files = null;
	static String mapSeleted = "";
	static int computersSeleted = 0;
	static JFrame jFrame = null;
	static GameDriver gameDriver = null;

	
	public RiskGame() {
		files = new ArrayList<String>();
		jFrame = new JFrame("RISK");
		gameDriver = new GameDriver();

	}
	
	public Boolean getMapFiles(String s){
		Boolean mapAvailable = false;
		File folder = new File(s);
		File[] listOfFiles = null;
		if(folder.exists()){
			listOfFiles = folder.listFiles();
		}
		
		  if(listOfFiles != null){
			  mapAvailable = true;
			  for (int i = 0; i < listOfFiles.length; i++) {
				  if (listOfFiles[i].isFile() && listOfFiles[i].toString().endsWith(".map")) {
					  files.add(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length()-4));
				  }
			  }
		  }
		  
		return mapAvailable;
	} 
	
	public JPanel headerPanel(){
		
		return null;
	}
	
	public JPanel footerPanel(){
		
		return null;
	}
	
	public void setGamePanels(JPanel middlePanel, String status){
        JPanel headerPanel = getPanel();
        JPanel footerPanel = getPanel();
        
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        headerPanel.setPreferredSize(new Dimension(600, 40));
        headerPanel.add(customJLabel(RiskGameConstants.GAME_NAME, 30, Color.BLACK));
        headerPanel.add(customJLabel(status, 20, Color.BLACK));
        
        middlePanel.setLayout(new GridLayout(0,1));
        
        footerPanel.setPreferredSize(new Dimension(600, 40));
        footerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        jFrame.getContentPane().add(headerPanel, BorderLayout.NORTH);
        jFrame.getContentPane().add(middlePanel, BorderLayout.CENTER);
        jFrame.getContentPane().add(footerPanel, BorderLayout.SOUTH);
        jFrame.pack();

        jFrame.setSize(600,600);  
        //jFrame.setLayout(new GridBagLayout());
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	public JPanel startPanel(){
		JPanel middleStartPanel = getPanel();
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
		
		JPanel temp = getPanel();
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
					
					RiskGameConstants.SELECTED_MAP = mapSeleted+".map";
					
					middleStartPanel.removeAll();
					middleStartPanel.revalidate();
					middleStartPanel.repaint();
					middleStartPanel.add(secondPanel(RiskGameConstants.SELECTED_MAP));
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
		JPanel middleSecondPanel = getPanel();
		
		middleSecondPanel.add(customJLabel(RiskGameConstants.NO_OF_COMPUTERS, 20, Color.BLACK));
		
		String noOfPlayers[]={"1", "2", "3", "4"};        
	    JComboBox cb=new JComboBox(noOfPlayers);    
	    middleSecondPanel.add(cb);        
		
		JPanel temp = getPanel();
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
					RiskGameConstants.SELECTED_NO_OF_COMPUTERS = computersSeleted;
					System.out.println(computersSeleted);
					middleSecondPanel.removeAll();
					middleSecondPanel.revalidate();
					middleSecondPanel.repaint();
					
					RiskGameConstants.TOTAL_PLAYERS = RiskGameConstants.SELECTED_NO_OF_COMPUTERS + RiskGameConstants.NO_OF_HUMAN;
					
					middleSecondPanel.add(thirdPanel(RiskGameConstants.SELECTED_MAP, RiskGameConstants.TOTAL_PLAYERS));
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
		middleSecondPanel.add(temp);
		return middleSecondPanel; 
	}
	
	public int noOfArmiesIntially(){
		int temp = 0;
		if(RiskGameConstants.TOTAL_PLAYERS == 2){
			temp = 40;
		} else if(RiskGameConstants.TOTAL_PLAYERS == 3){
			temp = 35;
		} else if(RiskGameConstants.TOTAL_PLAYERS == 4){
			temp = 30;
		} else if(RiskGameConstants.TOTAL_PLAYERS == 5){
			temp = 25;
		}
		
		return temp;
		
	}
	
	
	
	public JPanel thirdPanel(String SELECTED_MAP, int TOTAL_PLAYERS){
		MapReader mapreader = new MapReader();
		 List<Territory> listOfAssignedTerritories = null;
	    try {
			RiskMap riskMap = mapreader.readMap(SELECTED_MAP);
			//HashMap<String, String> map = riskMap.getMap();
			HashMap<String, String> continents = riskMap.getContinents();
			HashMap<String, Territory> territories = riskMap.getTerritories();
			
			listOfAssignedTerritories = getCountriesList(RiskGameConstants.TOTAL_PLAYERS, 
					 territories);
			
			RiskGameConstants.SET_NO_OF_CONTINETS = continents.size();
			RiskGameConstants.SET_NO_OF_TERRITORIES = territories.size();
			//ArrayList<Territory> Territories = new ArrayList<Territory>();
			ArrayList<Player> players  = new ArrayList<Player>();
			for(int i=0;i<=RiskGameConstants.SELECTED_NO_OF_COMPUTERS; i++){
				
				if(i ==0){
					Player human = new Player();
					human.setName("H");
					human.setComputer(false);
					human.setNoOfArmies(noOfArmiesIntially());
					//gameDriver.setPlayer(human);
					players.add(human);
				}
				else{
					Player comp = new Player();
					comp.setName("C"+i);
					comp.setComputer(true);
					comp.setNoOfArmies(noOfArmiesIntially());
					players.add(comp);
				}
				
			}
			
			for(int i=1 ; i<players.size();i++){
			
			 Player player = (Player) players.get(i);
			
			 //p.setTerritorAndArmies(listOfAssignedTerritories);
			}
			gameDriver.setListPlayer(players);
			
			if(TOTAL_PLAYERS > 1 && TOTAL_PLAYERS <6){
				for(;;){
					
				}
			}else{
				throw new Exception("TOTAL_PLAYERS >1 or <6? "+TOTAL_PLAYERS);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	private List<Territory> getCountriesList(int TOTAL_PLAYERS, HashMap<String,Territory> territories) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		RiskGame riskGame = new RiskGame();
		JPanel middlePanel = getPanel();
		
		Boolean mapAvailable = riskGame.getMapFiles("Maps");
		
		if(mapAvailable){
			middlePanel = riskGame.startPanel();
			riskGame.setGamePanels(middlePanel, "Map Selection");
		}else{
			middlePanel.add(customJLabel("NO MAPS AVAILABLE, Check folder", 20, Color.RED));
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

	private static JPanel getPanel(){
        JPanel result = new JPanel();
        result.setBorder(new EmptyBorder(50, 10, 10, 10));
        result.setPreferredSize(new Dimension(600, 40));
        result.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        return result;
    }
}
