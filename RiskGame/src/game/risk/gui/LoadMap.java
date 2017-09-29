package game.risk.gui;

import java.awt.event.*;import java.util.HashMap;

import javax.swing.*;

import game.risk.util.MapReader;
import game.risk.util.RiskMap;    
public class LoadMap {  
public static void main(String[] args) throws Exception 

{  
	
	
    JFrame f=new JFrame("MAP GUI");  
    
    JButton b;
    JLabel l1,l2;  
    JComboBox cb1,cb2;
    
    
    
    MapReader mapreader = new MapReader();
    RiskMap riskmap = mapreader.readMap();
    
    riskmap.getContinents();
    
        
    
    b=new JButton("Click Here");  
    l1=new JLabel("Continents");  
    l2=new JLabel("Countries"); 
    cb1=new JComboBox(riskmap.getContinents().keySet().toArray());
    cb2=new JComboBox(riskmap.getTerritories().keySet().toArray());
    
    //b.setBounds(155,110,100,35);  
    l1.setBounds(40,48, 100,30); 
    l2.setBounds(250,48, 100,30);
    cb1.setBounds(40, 50,190,120); 
    cb2.setBounds(250, 50,190,120); 
    
    
    f.add(cb1);  
    f.add(cb2);
    f.add(l1);
    f.add(l2);
    f.add(b);
    
    
    
    b.addActionListener(new ActionListener(){  
    public void actionPerformed(ActionEvent e){  
            b.setText("hey buddy");
        }  
    });  
    
    f.setSize(500,500);  
    f.setLayout(null);  
    f.setVisible(true);   
    
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}  
}  