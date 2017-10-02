package game.risk.gui;

import java.awt.Color;
import java.awt.List;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import javax.swing.*;

import game.risk.util.MapReader;
import game.risk.util.MapWriter;
import game.risk.util.RiskMap;    
public class LoadMap {  
public static void main(String[] args) throws Exception 

{  
	
	
    JFrame f=new JFrame("MAP GUI");  
    
    JButton addCountry,addContinent , addadjacentcountry , submitNewCountry ;
    JLabel l1,l2 , newcountrynamelabel ,continentfornewcountry , adjacenttonewcountry;  
    JComboBox cb1,cb2;
    JTextField newcountryname;
    JComboBox selectContinentForNewCountry ; 
    JComboBox selectAdjacentCountry;
    LinkedHashSet<String> adjacentCountriesToNewCountry = new LinkedHashSet<>();
   
    
    
    MapReader mapreader = new MapReader();
    RiskMap riskmap = mapreader.readMap();
    
    riskmap.getContinents();
    
        
    
    addCountry=new JButton("+");
    addContinent=new JButton("+");
    addadjacentcountry= new JButton("+");
    submitNewCountry= new JButton("Submit data");
    
    l1=new JLabel("Continents");  
    l2=new JLabel("Countries"); 
    newcountrynamelabel= new JLabel("Enter Name");
    continentfornewcountry = new JLabel("Continent");
    adjacenttonewcountry = new JLabel("Select Link");
    cb1=new JComboBox(riskmap.getContinents().keySet().toArray());
    cb2=new JComboBox(riskmap.getTerritories().keySet().toArray());
    newcountryname= new JTextField();
    
    selectContinentForNewCountry = new JComboBox(riskmap.getContinents().keySet().toArray());
    selectAdjacentCountry = new JComboBox(riskmap.getTerritories().keySet().toArray());
    
    addCountry.setBounds(640,80,30,20);
    addContinent.setBounds(230,80,30,20);
    l1.setBounds(40,48, 100,30); 
    l2.setBounds(450,48, 100,30);
    cb1.setBounds(40, 80,190,20); 
    cb2.setBounds(450, 80,190,20); 
    
    newcountryname.setBounds(300,250,120,40);
    selectContinentForNewCountry.setBounds(430 , 250, 120,40);
    selectAdjacentCountry.setBounds(560 , 250 , 120 , 40);
    
    newcountrynamelabel.setBounds(300 , 200 , 120 , 40);
    continentfornewcountry.setBounds(430 ,200,120,40 );
    adjacenttonewcountry.setBounds(560 , 200 ,120 , 40);
    addadjacentcountry.setBounds(690 , 255 , 20,20);
    submitNewCountry.setBounds(430 , 320 , 120,50);
    f.add(l1);
    f.add(l2);
    f.add(cb1);  
    f.add(cb2);
    
    
    f.add(addContinent);
    f.add(addCountry);
    f.add(newcountryname);
    f.add(selectContinentForNewCountry);
    f.add(selectAdjacentCountry);
    f.add(newcountrynamelabel);
    f.add(continentfornewcountry);
    f.add(adjacenttonewcountry);
    f.add(addadjacentcountry);
    f.add(submitNewCountry);
    
    newcountryname.setVisible(false);
    selectContinentForNewCountry.setVisible(false);
    selectAdjacentCountry.setVisible(false);
    newcountrynamelabel.setVisible(false);
    continentfornewcountry.setVisible(false);
    adjacenttonewcountry.setVisible(false);
    addadjacentcountry.setVisible(false);
    submitNewCountry.setVisible(false);
   
  
    addContinent.addActionListener(new ActionListener(){  
    	
    public void actionPerformed(ActionEvent e){ 
    	
    	LoadMap loadmap = new LoadMap();
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
    
    
    addCountry.addActionListener(new ActionListener(){  
    	
        public void actionPerformed(ActionEvent e){ 
        	
       newcountryname.setVisible(true);
       selectContinentForNewCountry.setVisible(true);
       selectAdjacentCountry.setVisible(true);

       newcountrynamelabel.setVisible(true);
       continentfornewcountry.setVisible(true);
       adjacenttonewcountry.setVisible(true);
       addadjacentcountry.setVisible(true);
       submitNewCountry.setVisible(true);
       
        	
            }  
        });  
    
 addadjacentcountry.addActionListener(new ActionListener(){  
    	
        public void actionPerformed(ActionEvent e)
        { 
        
        	if(!(newcountryname.getText().trim().isEmpty()))
        	{
        	adjacentCountriesToNewCountry.add((String) selectAdjacentCountry.getSelectedItem());
        	
       // 	System.out.println("country new------> "+newcountryname.getText());
         //	System.out.println("adjacent country------> "+selectAdjacentCountry.getSelectedItem());
         // System.out.println("list -- >" +adjacentCountriesToNewCountry.getItemCount());
            
        	
         }  
        
        else
        {
        	JOptionPane.showMessageDialog(f,"Please fill something in country name");  
        }
        
        
        }
        });  
    
      submitNewCountry.addActionListener(new ActionListener(){  
    	
        public void actionPerformed(ActionEvent e)
        
        { 
        	
        	MapWriter writeTerritory = new MapWriter();
        	
        	String newCountryEntry = "";
        	String newCountryName = newcountryname.getText();
        String continentOfNewCountry = (String) selectContinentForNewCountry.getSelectedItem();
        
        newCountryEntry = newCountryName+","+"0"+","+"0"+","+continentOfNewCountry;
        
        for (String temp : adjacentCountriesToNewCountry) 
        {
			newCountryEntry = newCountryEntry+","+temp;
		}
        
        
        System.out.println("entry--" +newCountryEntry);
            
        try {
			writeTerritory.addTerritory(newCountryEntry);
		} 
        
        catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        JOptionPane.showMessageDialog(f,"Territory with continent "+continentOfNewCountry+" and specified links has been added");  
        
        newcountryname.setVisible(false);
        selectContinentForNewCountry.setVisible(false);
        selectAdjacentCountry.setVisible(false);

        newcountrynamelabel.setVisible(false);
        continentfornewcountry.setVisible(false);
        adjacenttonewcountry.setVisible(false);
        addadjacentcountry.setVisible(false);
        submitNewCountry.setVisible(false);
         }
        
        });  
    
    
 /*   addCountry.addActionListener(new ActionListener(){  
    	
        public void actionPerformed(ActionEvent e){ 
        	
        
        	JTextField name_Country = new JTextField();
        	JComboBox combobox_continents;
        	JComboBox combobox_countries;
        	
     
        	combobox_continents= new JComboBox(riskmap.getContinents().keySet().toArray());
        	combobox_countries = new JComboBox(riskmap.getTerritories().keySet().toArray());
        	Object[] message = {
        	    "Name:", name_Country,
        	    "Select Continent of new country:", combobox_continents,
        	    "Select adjacent countries to new country:", combobox_countries
        	};

        	int option = JOptionPane.showConfirmDialog(f, message, "Continent Details", JOptionPane.OK_CANCEL_OPTION);
        	if (option == JOptionPane.OK_OPTION) 
        	
        	{
        	    MapWriter writeContinent = new MapWriter();

        	} 
        	
        	else {
        	    System.out.println("Action canceled");
        	}

            }  
        });  */
    
    
    f.setSize(800,500);  
    f.setLayout(null);  
    f.setVisible(true);   
    
    
    
    
    

    
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}  



}  