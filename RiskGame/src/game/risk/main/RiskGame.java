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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import game.risk.util.MapReader;
import game.risk.util.RiskConstants;
import game.risk.util.RiskMap;
import game.risk.util.Territory;

public class RiskGame {

	static ArrayList<String> files = null;
	static String mapSeleted = "";
	static JFrame jFrame = null;
	
	public RiskGame() {
		files = new ArrayList<String>();
		jFrame = new JFrame();
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
					  //files.add(listOfFiles[i].getAbsolutePath());
					  files.add(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length()-4));
				  }
			  }
		  }
		  
		return mapAvailable;
	} 
	
	public void setGamePanels(JPanel middlePanel, String status){
        JPanel headerPanel = getPanel();
        JPanel footerPanel = getPanel();
        
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        headerPanel.setPreferredSize(new Dimension(600, 40));
        headerPanel.add(customJLabel(RiskConstants.GAME_NAME, 30, Color.BLACK));
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
		
		
		middleStartPanel.add(customJLabel(RiskConstants.SELECT_MAP, 20, Color.BLACK));
		middleStartPanel.add(warning);
		warning.setVisible(false);
		middleStartPanel.add(listOfMaps); 
		
		JPanel temp = getPanel();
		temp.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton okButton = customJButton(RiskConstants.OK, 15);
		temp.add(okButton); 
		JButton cancelButton = customJButton(RiskConstants.CANCEL, 15);
		temp.add(cancelButton); 
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listOfMaps.getSelectedValue()!= null){
					mapSeleted = listOfMaps.getSelectedValue();
					
					RiskConstants.SELECTED_MAP = mapSeleted;
					
					/*middleStartPanel.removeAll();
					middleStartPanel.revalidate();
					middleStartPanel.repaint();*/
					secondPanel(mapSeleted+".map");
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
		MapReader mapreader = new MapReader();
	    try {
			RiskMap riskMap = mapreader.readMap(mapSeleted);
			HashMap<String, String> map = riskMap.getMap();
			HashMap<String, String> continents = riskMap.getContinents();
			HashMap<String, Territory> territories = riskMap.getTerritories();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		RiskGame riskGame = new RiskGame();
		JPanel middlePanel = getPanel();
		
		Boolean mapAvailable = riskGame.getMapFiles("Maps");
		
		if(mapAvailable){
			middlePanel = riskGame.startPanel();
		}else{
			middlePanel.add(customJLabel("NO MAPS AVAILABLE, Check folder", 20, Color.RED));
		}
		
		//riskGame.setGamePanels(middlePanel, "Map Selection");
		
		riskGame.setGamePanels(middlePanel, RiskConstants.SELECTED_MAP);
		
		
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
        result.setBorder(new EmptyBorder(10, 10, 10, 10));
        result.setPreferredSize(new Dimension(600, 40));
        result.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        return result;
    }
}
