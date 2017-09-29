package game.risk.gui;

import java.awt.event.*;import java.util.HashMap;

import javax.swing.*;

import game.risk.util.MapReader;
import game.risk.util.RiskMap;    
public class LoadMap {  
public static void main(String[] args) throws Exception 

{  
	
	
    JFrame f=new JFrame("LOAD MAP GUI");  
    
    JButton b=new JButton("Click Here");  
    
    JLabel l1,l2;  
    l1=new JLabel("Continents");  
    l1.setBounds(40,48, 100,30);  
    
    MapReader mapreader = new MapReader();
    RiskMap riskmap = mapreader.readMap();
    
    riskmap.getContinents();
    
    JComboBox cb=new JComboBox(riskmap.getContinents().keySet().toArray());    
    cb.setBounds(40, 50,190,120);    
    f.add(cb);     
    f.add(l1);
    
    
    b.setBounds(55,110,100,35);  
    b.addActionListener(new ActionListener(){  
    public void actionPerformed(ActionEvent e){  
            
        }  
    });  
    
    f.setSize(500,500);  
    f.setLayout(null);  
    f.setVisible(true);   
}  
}  