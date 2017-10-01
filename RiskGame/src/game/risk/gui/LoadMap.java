package game.risk.gui;

import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;

import game.risk.util.MapReader;
import game.risk.util.MapWriter;
import game.risk.util.RiskMap;    
public class LoadMap {  
public static void main(String[] args) throws Exception 

{  
	
	
    JFrame f=new JFrame("MAP GUI");  
    
    JButton b1,addContinent;
    JLabel l1,l2;  
    JComboBox cb1,cb2;
   
    
    
    MapReader mapreader = new MapReader();
    RiskMap riskmap = mapreader.readMap();
    
    riskmap.getContinents();
    
        
    
    b1=new JButton("Click Here");
    addContinent=new JButton("+");
    l1=new JLabel("Continents");  
    l2=new JLabel("Countries"); 
    cb1=new JComboBox(riskmap.getContinents().keySet().toArray());
    cb2=new JComboBox(riskmap.getTerritories().keySet().toArray());
    
   // b1.setBounds(155,210,100,35);
    addContinent.setBounds(230,80,30,20);
    l1.setBounds(40,48, 100,30); 
    l2.setBounds(350,48, 100,30);
    cb1.setBounds(40, 80,190,20); 
    cb2.setBounds(350, 50,190,120); 
    
    
    f.add(cb1);  
    f.add(cb2);
    f.add(l1);
    f.add(l2);
    f.add(b1);
    f.add(addContinent);
    
   
    
    addContinent.addActionListener(new ActionListener(){  
    public void actionPerformed(ActionEvent e){  
    	JTextField name = new JTextField();
    	JTextField value = new JTextField();
    	Object[] message = {
    	    "Name:", name,
    	    "Value:", value
    	};

    	int option = JOptionPane.showConfirmDialog(f, message, "Continent Details", JOptionPane.OK_CANCEL_OPTION);
    	if (option == JOptionPane.OK_OPTION) 
    	
    	{
    	    MapWriter writeContinent = new MapWriter();
    	    try {
				writeContinent.addContinent(name.getText(), value.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

    	} 
    	
    	else {
    	    System.out.println("Action canceled");
    	}

        }  
    });  
    
    f.setSize(800,500);  
    f.setLayout(null);  
    f.setVisible(true);   
    
    
    
    
    

    
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}  
}  