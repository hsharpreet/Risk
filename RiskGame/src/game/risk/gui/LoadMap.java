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
/**
 * A java class to Load the Map 
 * @author Simran
 *
 */
public class LoadMap {
/**
 * The Main method.	
 * @param args
 * @throws Exception
 */
public static void main(String[] args) throws Exception 

{  
	
	String MAP_FILE_NAME = "World.map";
    JFrame f=new JFrame("MAP GUI");  
    
    JButton addCountry,addContinent ,deleteContinent, addadjacentcountry , submitNewCountry , deleteCountry ,changeContinent ,assignNewContinent, viewCountriesOfContinent , deleteCountryLink , deleteSelectedLinkOfCountry ,countrySelectedToShowLinksToDeleteButton ;
    JLabel continentLabel,countryLabel , newcountrynamelabel ,continentfornewcountry , adjacenttonewcountry ,selectedTerritoryToModify , presentContinent, selectNewContinentToAssign , continentSelected , countriesOfSelectedContinent ,countrySelectedToShowLinksToDeleteLabel , linksOfSelectedCountryLabel;  
    JComboBox continentsComboBox,countryComboBox;
    JTextField newcountryname , territorySelected  , presentContinentField , selectedContinentField , countrySelectedToShowLinksToDeleteTF ;
    JComboBox selectContinentForNewCountry ; 
    JComboBox selectAdjacentCountry , selectModifiedContinentCB , countriesOfSelectedContinentCB , linksOfSelectedCountryCB;
    LinkedHashSet<String> adjacentCountriesToNewCountry = new LinkedHashSet<>();
   
    
    
    MapReader mapreader = new MapReader();
    RiskMap riskmap = mapreader.readMap("World.map");
    
    riskmap.getContinents();
    
        
    //Creating the GUI
    addCountry=new JButton("ADD NEW COUNTRY");
    deleteCountry=new JButton("DELETE SELECTED COUNTRY");
    changeContinent=new JButton("Change Continent");
    deleteCountryLink = new JButton("Delete Link");
    addContinent=new JButton("+");
    deleteContinent= new JButton("-");
    viewCountriesOfContinent = new JButton("View Countries");    
    
    addadjacentcountry= new JButton("+");
    submitNewCountry= new JButton("Submit data");
    assignNewContinent= new JButton("Assign new continent");
    
    continentLabel=new JLabel("Continents");  
    countryLabel=new JLabel("Countries"); 
    newcountrynamelabel= new JLabel("Enter Name");
    continentfornewcountry = new JLabel("Continent");
    adjacenttonewcountry = new JLabel("Select Link");
    selectedTerritoryToModify = new JLabel("Selected Country");
    selectNewContinentToAssign = new JLabel("Select New Continent");
    presentContinent = new JLabel("Present Continent");
    continentSelected = new JLabel("Selected Continent");
    countriesOfSelectedContinent = new JLabel("Countries Present");
    
    DefaultComboBoxModel continentComboBoxModel= new DefaultComboBoxModel<>(riskmap.getContinents().keySet().toArray());
    continentsComboBox=new JComboBox(continentComboBoxModel);
    DefaultComboBoxModel countriesComboBoxModel = new DefaultComboBoxModel<>(riskmap.getTerritories().keySet().toArray());
    countryComboBox=new JComboBox(countriesComboBoxModel);
    selectModifiedContinentCB= new JComboBox(riskmap.getContinents().keySet().toArray());
    countriesOfSelectedContinentCB=new JComboBox<>();
    
    newcountryname= new JTextField();
    territorySelected = new JTextField();
    presentContinentField = new JTextField();
    selectedContinentField = new JTextField();
    
    countrySelectedToShowLinksToDeleteLabel = new JLabel("Selected Country");
    countrySelectedToShowLinksToDeleteTF = new JTextField();
    countrySelectedToShowLinksToDeleteButton = new JButton("Delete Link");
    linksOfSelectedCountryLabel = new JLabel("Links Of Country");
    linksOfSelectedCountryCB = new JComboBox<>();
   
    
    
    selectContinentForNewCountry = new JComboBox(continentComboBoxModel);
    selectAdjacentCountry = new JComboBox(riskmap.getTerritories().keySet().toArray());
    // Setting the coordinates
    addCountry.setBounds(640,40,160,30);
    deleteCountry.setBounds(640 ,70,160,30);
    changeContinent.setBounds(640,100,160,30);
    assignNewContinent.setBounds(560,320,120,40);
    addContinent.setBounds(230,80,30,20);
    viewCountriesOfContinent.setBounds(220,110 ,150,30);
    deleteContinent.setBounds(270, 80, 30, 20);
    continentSelected.setBounds(40,250 , 150,20);
    countriesOfSelectedContinent.setBounds(200 , 250 , 150 ,20);
    selectedContinentField.setBounds(40, 280 , 150 ,20);
    countriesOfSelectedContinentCB.setBounds(200 ,280 , 150 ,20);
    deleteCountryLink.setBounds(640 , 130 , 160 ,30);
    
    continentLabel.setBounds(40,48, 100,30); 
    countryLabel.setBounds(450,48, 100,30);
    continentsComboBox.setBounds(40, 80,190,20); 
    countryComboBox.setBounds(450, 80,190,20); 
    
    newcountryname.setBounds(300,250,120,40);
    selectContinentForNewCountry.setBounds(430 , 250, 120,40);
    selectAdjacentCountry.setBounds(560 , 250 , 120 , 40);
    
    territorySelected.setBounds(430 ,250,120,40);
    selectModifiedContinentCB.setBounds(680 , 250 , 120 , 40);
    selectedTerritoryToModify.setBounds(430 , 200, 120,40 );
    selectNewContinentToAssign.setBounds(680 , 200 ,120 , 40);
    presentContinent.setBounds(560,200,120,40);
    presentContinentField.setBounds(560 , 250,120,40);
    
    newcountrynamelabel.setBounds(300 , 200 , 120 , 40);
    continentfornewcountry.setBounds(430 ,200,120,40 );
    adjacenttonewcountry.setBounds(560 , 200 ,120 , 40);
    addadjacentcountry.setBounds(690 , 255 , 20,20);
    submitNewCountry.setBounds(430 , 320 , 120,50);
    
    countrySelectedToShowLinksToDeleteLabel.setBounds(430 , 200, 120,40 );
    countrySelectedToShowLinksToDeleteTF.setBounds(430 ,250,120,40);
    countrySelectedToShowLinksToDeleteButton.setBounds(430 , 320 , 120,50);
    linksOfSelectedCountryCB.setBounds(560 , 250 , 120 , 40);
    linksOfSelectedCountryLabel.setBounds(560,200,120,40);
     
    
    f.add(continentLabel);
    f.add(countryLabel);
    f.add(continentsComboBox);  
    f.add(countryComboBox);
    
    
    f.add(addContinent);
    f.add(deleteContinent);
    f.add(addCountry);
    f.add(newcountryname);
    f.add(selectContinentForNewCountry);
    f.add(selectAdjacentCountry);
    f.add(newcountrynamelabel);
    f.add(continentfornewcountry);
    f.add(adjacenttonewcountry);
    f.add(addadjacentcountry);
    f.add(submitNewCountry);
    f.add(deleteCountry);
    f.add(changeContinent);
    f.add(territorySelected);
    f.add(selectModifiedContinentCB);
    f.add(selectedTerritoryToModify);
    f.add(selectNewContinentToAssign);
    f.add(assignNewContinent);
    f.add(presentContinent);
    f.add(presentContinentField);
    f.add(viewCountriesOfContinent);
    f.add(continentSelected);
    f.add(countriesOfSelectedContinent);
    f.add(selectedContinentField);
    f.add(countriesOfSelectedContinentCB);
    f.add(deleteCountryLink);
    
    f.add(countrySelectedToShowLinksToDeleteButton);
    f.add(countrySelectedToShowLinksToDeleteTF);
    f.add(countrySelectedToShowLinksToDeleteLabel);
    f.add(linksOfSelectedCountryCB);
    f.add(linksOfSelectedCountryLabel);
    
    
    newcountryname.setVisible(false);
    selectContinentForNewCountry.setVisible(false);
    selectAdjacentCountry.setVisible(false);
    newcountrynamelabel.setVisible(false);
    continentfornewcountry.setVisible(false);
    adjacenttonewcountry.setVisible(false);
    addadjacentcountry.setVisible(false);
    submitNewCountry.setVisible(false);
    territorySelected.setVisible(false);
    selectModifiedContinentCB.setVisible(false);
    selectedTerritoryToModify.setVisible(false);
    selectNewContinentToAssign.setVisible(false);
    assignNewContinent.setVisible(false);
    selectContinentForNewCountry.setVisible(false);
    presentContinent.setVisible(false);
    
    presentContinentField.setVisible(false);
    continentSelected.setVisible(false);
    countriesOfSelectedContinent.setVisible(false);
    selectedContinentField.setVisible(false);
    countriesOfSelectedContinentCB.setVisible(false);
    
    countrySelectedToShowLinksToDeleteButton.setVisible(false);
    countrySelectedToShowLinksToDeleteTF.setVisible(false);
    countrySelectedToShowLinksToDeleteLabel.setVisible(false);
    linksOfSelectedCountryCB.setVisible(false);
    linksOfSelectedCountryLabel.setVisible(false);
    
    
    
   
    // Method to add continent
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
    		if(name.getText().trim().isEmpty() || value.getText().trim().isEmpty()) 
        	{
        		JOptionPane.showMessageDialog(f,"Field cannot be empty");
        	
        	}
    		else
    		{
    	    MapWriter writeContinent = new MapWriter(MAP_FILE_NAME);
    	    try {
				writeContinent.addContinent(name.getText(), value.getText());
				continentComboBoxModel.addElement(name.getText());
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		}
    	} 
    	
    	else {
    	    System.out.println("Action canceled");
    	}
    }
         
    });  
    // Method to delete the continent
    deleteContinent.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){ 
        	int option = JOptionPane.showConfirmDialog(f, "All countries in this continent will also be deleted.Do you want to proceed?", "Delete Continent", JOptionPane.OK_CANCEL_OPTION);
        	if (option == JOptionPane.OK_OPTION) 
        	{ System.out.println();
        	    MapWriter mapWriter = new MapWriter(MAP_FILE_NAME);
        	    try {
        	    	ArrayList<String> countriesListData = mapWriter.getCountriesOfContinent(continentsComboBox.getSelectedItem().toString());
        	    	mapWriter.deleteContinent((String)continentsComboBox.getSelectedItem());
        	    	System.out.println("Deleted continent "+continentsComboBox.getSelectedItem());
        	    	continentComboBoxModel.removeElementAt(continentsComboBox.getSelectedIndex());
        	    	for(String country: countriesListData){
        	    		countriesComboBoxModel.removeElement(country);
        	    	}
    				
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
        	} 
        	
        	else {
        	    System.out.println("Delete Country Action canceled");
        	}
            }  
        });
    //  Method to add country
    addCountry.addActionListener(new ActionListener(){  
    	
        public void actionPerformed(ActionEvent e){ 
        	
         	territorySelected.setVisible(false);
            selectModifiedContinentCB.setVisible(false);
            selectedTerritoryToModify.setVisible(false);
            selectNewContinentToAssign.setVisible(false);
            assignNewContinent.setVisible(false);
            presentContinent.setVisible(false);
            presentContinentField.setVisible(false);
        	
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
    // Method to add adjacent countries
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
    // Method to submit new country
      submitNewCountry.addActionListener(new ActionListener(){  
    	
        public void actionPerformed(ActionEvent e)
        
        { 
        	
        	if(!(newcountryname.getText().trim().isEmpty()))
        	{
        	
        	MapWriter writeTerritory = new MapWriter(MAP_FILE_NAME);
        	
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
			writeTerritory.addNewCountryLinkToTerritories(newcountryname.getText() , adjacentCountriesToNewCountry);
			countriesComboBoxModel.addElement(newcountryname.getText());
		} 
        
        catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
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
        	
        	  else{
        			JOptionPane.showMessageDialog(f,"Please fill something in country name");  
        		  
              }
        }
      
        
        });  
      
      // Method to delete country
      deleteCountry.addActionListener(new ActionListener(){  
      	
    	    public void actionPerformed(ActionEvent e)
    	    
    	    { 
    	    	String status;
    	    	
    	    	String terittoryToDelete = countryComboBox.getSelectedItem().toString();
    	    	
    	    	MapWriter deleteTerritory = new MapWriter(MAP_FILE_NAME);
    	    	
    	    	try {
			status = deleteTerritory.deleteTerritory(terittoryToDelete);
			
			if(status.equalsIgnoreCase("OK"))
			{
				JOptionPane.showMessageDialog(f,"Territory Deleted");  
				countriesComboBoxModel.removeElementAt(countryComboBox.getSelectedIndex());
			}
			else
			{
				JOptionPane.showMessageDialog(f,"Action cannot be performed as some territory has just one link to this territory");  
			}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    	
    	    	

    	        }  
    	    });  
      //Method to change continent
      changeContinent.addActionListener(new ActionListener(){  
        	
  	    public void actionPerformed(ActionEvent e)
  	    
  	    { 
  	    	String status;
  	    	
  	    	String terittorySelected = countryComboBox.getSelectedItem().toString();
  	    	
  	    	MapWriter mp = new MapWriter(MAP_FILE_NAME);
  	    	
  	    	
  	    newcountryname.setVisible(false);
  	    selectContinentForNewCountry.setVisible(false);
  	    selectAdjacentCountry.setVisible(false);
  	    newcountrynamelabel.setVisible(false);
  	    continentfornewcountry.setVisible(false);
  	    adjacenttonewcountry.setVisible(false);
  	    addadjacentcountry.setVisible(false);
  	    submitNewCountry.setVisible(false);
  	     
  	    countrySelectedToShowLinksToDeleteButton.setVisible(false);
	    countrySelectedToShowLinksToDeleteTF.setVisible(false);
	    countrySelectedToShowLinksToDeleteLabel.setVisible(false);
	    linksOfSelectedCountryCB.setVisible(false);
	    linksOfSelectedCountryLabel.setVisible(false);
  	    	
  	    	territorySelected.setVisible(true);
        selectModifiedContinentCB.setVisible(true);
        selectedTerritoryToModify.setVisible(true);
  	    selectNewContinentToAssign.setVisible(true);
  	    assignNewContinent.setVisible(true);
    	    presentContinent.setVisible(true);
        presentContinentField.setVisible(true);
  	    
  	    	territorySelected.setText(countryComboBox.getSelectedItem().toString());
  	    	territorySelected.setFocusable(false);
 	    presentContinentField.setText(mp.getPresentContinent(territorySelected.getText()));
 	    presentContinentField.setFocusable(false);
  	        }  
  	    });  
    
      // Method to assign new continent
      assignNewContinent.addActionListener(new ActionListener(){  
      	
          public void actionPerformed(ActionEvent e)
          { 
          
        	MapWriter mp = new MapWriter(MAP_FILE_NAME);
        	territorySelected.setText(countryComboBox.getSelectedItem().toString());
    	    	territorySelected.setFocusable(false);
       
    	    	try 
          {
  			mp.assignNewContinent((String) selectModifiedContinentCB.getSelectedItem() ,territorySelected.getText());
  			
  		} catch (Exception e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}
    	    	
    	    	
    	        }  
          
          
          });  
      // Method to view countries of continents
      viewCountriesOfContinent.addActionListener(new ActionListener(){  
    	  ArrayList countriesListData = new ArrayList<String>();
          public void actionPerformed(ActionEvent e)
          { 
          
        	    continentSelected.setVisible(true);
        	    countriesOfSelectedContinent.setVisible(true);
        	    selectedContinentField.setVisible(true);
        	    countriesOfSelectedContinentCB.setVisible(true);  
        	    
        	    selectedContinentField.setText(continentsComboBox.getSelectedItem().toString());
        	    selectedContinentField.setFocusable(false);
        	  
        	MapWriter mp = new MapWriter(MAP_FILE_NAME);
       
    	    	try 
          {
  			ArrayList countriesListData = new ArrayList<String>();
  			countriesListData.clear();
  			countriesOfSelectedContinentCB.removeAllItems();
  			countriesListData = mp.getCountriesOfContinent(continentsComboBox.getSelectedItem().toString());
  			
  			for(int i = 0 ; i<countriesListData.size() ; i++)
  			{
  				countriesOfSelectedContinentCB.addItem(countriesListData.get(i));
  			}
  			
  			System.out.println(countriesListData.size());
  			
  		} catch (Exception e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}
    	    	
    	    	
    	        }  
          
          
          });  
      
      deleteCountryLink.addActionListener(new ActionListener(){  
      	 
          public void actionPerformed(ActionEvent e)
         
          { 
        	  
        	  
        	    newcountryname.setVisible(false);
        	    selectContinentForNewCountry.setVisible(false);
        	    selectAdjacentCountry.setVisible(false);
        	    newcountrynamelabel.setVisible(false);
        	    continentfornewcountry.setVisible(false);
        	    adjacenttonewcountry.setVisible(false);
        	    addadjacentcountry.setVisible(false);
        	    submitNewCountry.setVisible(false);
        	    	
        	    	territorySelected.setVisible(false);
            selectModifiedContinentCB.setVisible(false);
            selectedTerritoryToModify.setVisible(false);
        	    selectNewContinentToAssign.setVisible(false);
        	    assignNewContinent.setVisible(false);
            presentContinent.setVisible(false);
            presentContinentField.setVisible(false);
            
         	
            countrySelectedToShowLinksToDeleteButton.setVisible(true);
      	    countrySelectedToShowLinksToDeleteTF.setVisible(true);
      	    countrySelectedToShowLinksToDeleteLabel.setVisible(true);
      	    linksOfSelectedCountryCB.setVisible(true);
      	    linksOfSelectedCountryLabel.setVisible(true);
      	    
      		countrySelectedToShowLinksToDeleteTF.setText(countryComboBox.getSelectedItem().toString());
      	    	countrySelectedToShowLinksToDeleteTF.setFocusable(false);
        	  
      	  	MapWriter mp = new MapWriter(MAP_FILE_NAME);
            
	    	try 
      {
			ArrayList countriesLink = new ArrayList<String>();
			countriesLink.clear();
			linksOfSelectedCountryCB.removeAllItems();
			countriesLink = mp.getLinksOfCountry(countryComboBox.getSelectedItem().toString());
			
			for(int i = 0 ; i<countriesLink.size() ; i++)
			{
			linksOfSelectedCountryCB.addItem(countriesLink.get(i));
			}
			
			System.out.println(countriesLink.size());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    	
      	    	
          } 
          
          });  
      
      countrySelectedToShowLinksToDeleteButton.addActionListener(new ActionListener(){  
     	 
          public void actionPerformed(ActionEvent e)
         
          { 
      //  	System.out.println("hiiiiiiiiiiiiiii");
      	  	MapWriter mp = new MapWriter(MAP_FILE_NAME);
            
       
    	    	try 
          {
  			String s =mp.deleteLink((String) linksOfSelectedCountryCB.getSelectedItem() , countrySelectedToShowLinksToDeleteTF.getText());
  			
  			if(s.equalsIgnoreCase("OK"))
			{
				JOptionPane.showMessageDialog(f,"Territory link Deleted");  
			}
			else
			{
				JOptionPane.showMessageDialog(f,"Action cannot be performed as some territory has just one link to this territory or this territory just have one link");  
			}

  			
  		} catch (Exception e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}
	    	
      	    	
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
        	    MapWriter writeContinent = new MapWriter(MAP_FILE_NAME);

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