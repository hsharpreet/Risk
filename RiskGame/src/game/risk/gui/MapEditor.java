package game.risk.gui;

import java.awt.Color;
import java.awt.List;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import javax.swing.*;

import game.risk.model.Territory;
import game.risk.util.MapReader;
import game.risk.util.MapWriter;
import game.risk.util.RiskMap;
import game.risk.util.Validation;

/**
 * A java class to Load the Map
 * 
 * @author Team
 *
 */
public class MapEditor {
	/**
	 * The Main method.
	 * 
	 * @param args
	 * 
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception

	{

	}

	public static void loadMap(String file) throws Exception {

		String MAP_FILE_NAME = file;
		JFrame mapEditorFrame = new JFrame("MAP GUI");
		// Declaration
		JButton addCountry, addContinent, deleteContinent, addadjacentcountry, submitNewCountry, deleteCountry,
				changeContinent, assignNewContinent, viewCountriesOfContinent, deleteCountryLink,
				deleteSelectedLinkOfCountry, countrySelectedToShowLinksToDeleteButton, addCountryLink,
				countrySelectedToShowLinksToAddButton, saveMap;
		JLabel continentLabel, countryLabel, newcountrynamelabel, continentfornewcountry, adjacenttonewcountry,
				selectedTerritoryToModify, presentContinent, selectNewContinentToAssign, continentSelected,
				countriesOfSelectedContinent, countrySelectedToShowLinksToDeleteLabel, linksOfSelectedCountryLabel;
		JComboBox continentsComboBox, countryComboBox;
		JTextField newcountryname, territorySelected, presentContinentField, selectedContinentField,
				countrySelectedToShowLinksToDeleteTF;
		JComboBox selectContinentForNewCountry;
		JComboBox selectAdjacentCountry, selectModifiedContinentCB, countriesOfSelectedContinentCB,
				linksOfSelectedCountryCB;
		LinkedHashSet<String> adjacentCountriesToNewCountry = new LinkedHashSet<>();

		MapReader mapreader = new MapReader();// creating object of class
												// MapReader
		RiskMap riskmap = mapreader.readMap(MAP_FILE_NAME);

		riskmap.getContinents();// call to get the continents.

		// Creating the GUI
		addCountry = new JButton("Add New Country");
		deleteCountry = new JButton("Delete Selected Country");
		changeContinent = new JButton("Change Continent");
		deleteCountryLink = new JButton("Delete Link");
		addCountryLink = new JButton("Add Link");
		addContinent = new JButton("Add");
		deleteContinent = new JButton("Delete");
		viewCountriesOfContinent = new JButton("View Countries");

		addadjacentcountry = new JButton("Add");
		submitNewCountry = new JButton("Submit data");
		assignNewContinent = new JButton("Assign new continent");

		continentLabel = new JLabel("Continents");
		countryLabel = new JLabel("Countries");
		newcountrynamelabel = new JLabel("Enter Name");
		continentfornewcountry = new JLabel("Continent");
		adjacenttonewcountry = new JLabel("Select Link");
		selectedTerritoryToModify = new JLabel("Selected Country");
		selectNewContinentToAssign = new JLabel("Select New Continent");
		presentContinent = new JLabel("Present Continent");
		continentSelected = new JLabel("Selected Continent");
		countriesOfSelectedContinent = new JLabel("Countries Present");

		DefaultComboBoxModel continentComboBoxModel = new DefaultComboBoxModel<>(
				riskmap.getContinents().keySet().toArray());
		continentsComboBox = new JComboBox(continentComboBoxModel);
		DefaultComboBoxModel countriesComboBoxModel = new DefaultComboBoxModel<>(
				riskmap.getTerritories().keySet().toArray());
		countryComboBox = new JComboBox(countriesComboBoxModel);
		selectModifiedContinentCB = new JComboBox(continentComboBoxModel);
		countriesOfSelectedContinentCB = new JComboBox<>();

		newcountryname = new JTextField();
		territorySelected = new JTextField();
		presentContinentField = new JTextField();
		selectedContinentField = new JTextField();

		countrySelectedToShowLinksToDeleteLabel = new JLabel("Selected Country");
		countrySelectedToShowLinksToDeleteTF = new JTextField();
		countrySelectedToShowLinksToDeleteButton = new JButton("Delete Link");
		countrySelectedToShowLinksToAddButton = new JButton("Add Link");
		linksOfSelectedCountryLabel = new JLabel("Link to Modify");
		saveMap = new JButton("Go Back");
		linksOfSelectedCountryCB = new JComboBox<>();

		selectContinentForNewCountry = new JComboBox(continentComboBoxModel);
		selectAdjacentCountry = new JComboBox(riskmap.getTerritories().keySet().toArray());
		// Setting the coordinates
		addCountry.setBounds(640, 40, 160, 30);
		deleteCountry.setBounds(640, 70, 160, 30);
		changeContinent.setBounds(640, 100, 160, 30);
		assignNewContinent.setBounds(560, 320, 120, 40);
		addContinent.setBounds(230, 80, 60, 20);
		viewCountriesOfContinent.setBounds(220, 110, 150, 30);
		deleteContinent.setBounds(292, 80, 90, 20);
		continentSelected.setBounds(40, 250, 150, 20);
		countriesOfSelectedContinent.setBounds(200, 250, 150, 20);
		selectedContinentField.setBounds(40, 280, 150, 20);
		countriesOfSelectedContinentCB.setBounds(200, 280, 150, 20);
		deleteCountryLink.setBounds(640, 130, 160, 30);
		addCountryLink.setBounds(640, 160, 160, 30);

		continentLabel.setBounds(40, 48, 100, 30);
		countryLabel.setBounds(450, 48, 100, 30);
		continentsComboBox.setBounds(40, 80, 190, 20);
		countryComboBox.setBounds(450, 80, 190, 20);

		newcountryname.setBounds(300, 250, 120, 40);
		selectContinentForNewCountry.setBounds(430, 250, 120, 40);
		selectAdjacentCountry.setBounds(560, 250, 120, 40);

		territorySelected.setBounds(430, 250, 120, 40);
		selectModifiedContinentCB.setBounds(680, 250, 120, 40);
		selectedTerritoryToModify.setBounds(430, 200, 120, 40);
		selectNewContinentToAssign.setBounds(680, 200, 120, 40);
		presentContinent.setBounds(560, 200, 120, 40);
		presentContinentField.setBounds(560, 250, 120, 40);

		newcountrynamelabel.setBounds(300, 200, 120, 40);
		continentfornewcountry.setBounds(430, 200, 120, 40);
		adjacenttonewcountry.setBounds(560, 200, 120, 40);
		addadjacentcountry.setBounds(690, 250, 60, 40);
		submitNewCountry.setBounds(430, 320, 120, 50);

		countrySelectedToShowLinksToDeleteLabel.setBounds(430, 200, 120, 40);
		countrySelectedToShowLinksToDeleteTF.setBounds(430, 250, 120, 40);
		countrySelectedToShowLinksToDeleteButton.setBounds(430, 320, 120, 50);
		linksOfSelectedCountryCB.setBounds(560, 250, 120, 40);
		linksOfSelectedCountryLabel.setBounds(560, 200, 120, 40);
		countrySelectedToShowLinksToAddButton.setBounds(430, 320, 120, 50);
		saveMap.setBounds(450, 400, 80, 30);

		// appending the components
		mapEditorFrame.add(continentLabel);
		mapEditorFrame.add(countryLabel);
		mapEditorFrame.add(continentsComboBox);
		mapEditorFrame.add(countryComboBox);

		mapEditorFrame.add(addContinent);
		mapEditorFrame.add(deleteContinent);
		mapEditorFrame.add(addCountry);
		mapEditorFrame.add(newcountryname);
		mapEditorFrame.add(selectContinentForNewCountry);
		mapEditorFrame.add(selectAdjacentCountry);
		mapEditorFrame.add(newcountrynamelabel);
		mapEditorFrame.add(continentfornewcountry);
		mapEditorFrame.add(adjacenttonewcountry);
		mapEditorFrame.add(addadjacentcountry);
		mapEditorFrame.add(submitNewCountry);
		mapEditorFrame.add(deleteCountry);
		mapEditorFrame.add(changeContinent);
		mapEditorFrame.add(territorySelected);
		mapEditorFrame.add(selectModifiedContinentCB);
		mapEditorFrame.add(selectedTerritoryToModify);
		mapEditorFrame.add(selectNewContinentToAssign);
		mapEditorFrame.add(assignNewContinent);
		mapEditorFrame.add(presentContinent);
		mapEditorFrame.add(presentContinentField);
		mapEditorFrame.add(viewCountriesOfContinent);
		mapEditorFrame.add(continentSelected);
		mapEditorFrame.add(countriesOfSelectedContinent);
		mapEditorFrame.add(selectedContinentField);
		mapEditorFrame.add(countriesOfSelectedContinentCB);
		mapEditorFrame.add(deleteCountryLink);
		mapEditorFrame.add(addCountryLink);
		mapEditorFrame.add(countrySelectedToShowLinksToAddButton);

		mapEditorFrame.add(countrySelectedToShowLinksToDeleteButton);
		mapEditorFrame.add(countrySelectedToShowLinksToDeleteTF);
		mapEditorFrame.add(countrySelectedToShowLinksToDeleteLabel);
		mapEditorFrame.add(linksOfSelectedCountryCB);
		mapEditorFrame.add(linksOfSelectedCountryLabel);
		mapEditorFrame.add(saveMap);

		// setting the visibility of the components
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
		countrySelectedToShowLinksToAddButton.setVisible(false);

		/**
		 * A method to add continent to the file.
		 */
		addContinent.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				MapEditor loadmap = new MapEditor();
				JTextField name = new JTextField();
				JTextField value = new JTextField();
				Object[] message = { "Name:", name, "Value:", value };

				int option = JOptionPane.showConfirmDialog(mapEditorFrame, message, "Continent Details",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) 
				{ // if user selects ok option
					// if user selects ok option
					// if field value or name is empty show this message
					if (name.getText().trim().isEmpty() || value.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(mapEditorFrame, "Field cannot be empty.");

					}
					// if field value is not a number.
					if (!value.getText().matches("-?\\d+(\\.\\d+)?")) {
						JOptionPane.showMessageDialog(mapEditorFrame, "Value should be a number.");

					} else {
						// if field value not empty write field value in file
						MapWriter writeContinent = new MapWriter(MAP_FILE_NAME);
						try {
							writeContinent.addContinent(name.getText(), value.getText());
							continentComboBoxModel.addElement(name.getText());
							JOptionPane.showMessageDialog(mapEditorFrame, "Continent Added Sucessfully");

						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					}
				}

				else {
					System.out.println("Action Canceled");
				}
			}

		});
		/**
		 * A method to delete continent from the file which also deletes all the
		 * countries in the continent
		 */
		deleteContinent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean status;
				int option = JOptionPane.showConfirmDialog(mapEditorFrame,
						"All countries in this continent will also be deleted.Do you want to proceed?",
						"Delete Continent", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION)// if user selects ok option
				{
					MapWriter mapWriter = new MapWriter(MAP_FILE_NAME);
					Validation validate = new Validation();

					try {
						String continentSelected = continentsComboBox.getSelectedItem().toString();
						Map<String, Territory> territoriesList = mapreader.getTerritoriesOfContinent(continentSelected,
								MAP_FILE_NAME);

						status = validate.checkTerritoriesBeforeDeletingContinent(continentSelected, MAP_FILE_NAME);
						if (status) {
							for (Territory territoryToDelete : territoriesList.values()) {
								mapWriter.deleteTerritoriesOfContinentDeleted(territoryToDelete.getName());
							}
							mapWriter.deleteContinent((String) continentsComboBox.getSelectedItem());
							continentComboBoxModel.removeElementAt(continentsComboBox.getSelectedIndex());
							for (Territory territoryToDelete : territoriesList.values()) {
								countriesComboBoxModel.removeElement(territoryToDelete.getName());
							}
						}

						else {
							JOptionPane.showMessageDialog(mapEditorFrame,
									"Cannot proceed : Deletion of some country will lead to invalid map");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}

				else {
					// if user does not press OK
					System.out.println("Delete Country Action canceled");
				}
			}
		});
		/**
		 * A method to add country to the file World.map
		 */
		addCountry.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// setting the visibility of components

				territorySelected.setVisible(false);
				selectModifiedContinentCB.setVisible(false);
				selectedTerritoryToModify.setVisible(false);
				selectNewContinentToAssign.setVisible(false);
				assignNewContinent.setVisible(false);
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
				countrySelectedToShowLinksToAddButton.setVisible(false);

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
		/**
		 * a method to add adjacent countries tothe world.map
		 */
		addadjacentcountry.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (!(newcountryname.getText().trim().isEmpty()))// if user adds a country
				{
					adjacentCountriesToNewCountry.add((String) selectAdjacentCountry.getSelectedItem());
				}

				else {
					JOptionPane.showMessageDialog(mapEditorFrame, "Please fill something in country name");// if the field is empty
					// show this message.
				}

			}
		});
		/**
		 * A method which adds a new country with selected continent and at
		 * least one adjacent country .
		 */
		submitNewCountry.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)

			{

				if (!(newcountryname.getText().trim().isEmpty())) {

					MapWriter writeTerritory = new MapWriter(MAP_FILE_NAME);// Adding country to file

					String newCountryEntry = "";
					String newCountryName = newcountryname.getText();
					String continentOfNewCountry = (String) selectContinentForNewCountry.getSelectedItem();

					newCountryEntry = newCountryName + "," + "0" + "," + "0" + "," + continentOfNewCountry;

					for (String temp : adjacentCountriesToNewCountry) {
						newCountryEntry = newCountryEntry + "," + temp;
					}

					System.out.println("entry--" + newCountryEntry);

					try {
						writeTerritory.addTerritory(newCountryEntry);
						writeTerritory.addNewCountryLinkToTerritories(newcountryname.getText(),
								adjacentCountriesToNewCountry);
						countriesComboBoxModel.addElement(newcountryname.getText());
					}

					catch (IOException e1) {
						
						e1.printStackTrace();
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}

					JOptionPane.showMessageDialog(mapEditorFrame, "Territory with continent " + continentOfNewCountry
							+ " and specified links has been added");
					// setting up the visibility
					newcountryname.setVisible(false);
					selectContinentForNewCountry.setVisible(false);
					selectAdjacentCountry.setVisible(false);

					newcountrynamelabel.setVisible(false);
					continentfornewcountry.setVisible(false);
					adjacenttonewcountry.setVisible(false);
					addadjacentcountry.setVisible(false);
					submitNewCountry.setVisible(false);
				}

				else {
					JOptionPane.showMessageDialog(mapEditorFrame, "Please fill something in country name"); // if field is empty show this message.

				}
			}

		});

		/**
		 * A method to delete country from the file if there is no link to any
		 * territory
		 * 
		 */

		deleteCountry.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String status;

				String terittoryToDelete = countryComboBox.getSelectedItem().toString();

				MapWriter deleteTerritory = new MapWriter(MAP_FILE_NAME);

				try {
					status = deleteTerritory.deleteTerritory(terittoryToDelete);

					if (status.equalsIgnoreCase("OK")) {
						// confirmation message
						JOptionPane.showMessageDialog(mapEditorFrame, "Territory Deleted");
						countriesComboBoxModel.removeElementAt(countryComboBox.getSelectedIndex());
					} else {
						// if territory is still linked with another territory.
						JOptionPane.showMessageDialog(mapEditorFrame,
								"Action cannot be performed as some territory has just one link to this territory");
					}
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}

			}
		});
		/**
		 * A method which displays the country selected, present continent and
		 * drop down of the new continent which user wants to select
		 */
		changeContinent.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)

			{ // changing the continent
				String status;

				String terittorySelected = countryComboBox.getSelectedItem().toString();

				MapWriter mp = new MapWriter(MAP_FILE_NAME);

				// setting up the visibility of the components
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
				presentContinentField.setText(riskmap.getTerritories().get(territorySelected.getText()).getContinent());
				presentContinentField.setFocusable(false);
			}
		});

		/**
		 * A method to assign new continent selected by the user in the previous
		 * method.
		 */
		assignNewContinent.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// changes to the file to assign new country
				MapWriter mp = new MapWriter(MAP_FILE_NAME);
				territorySelected.setText(countryComboBox.getSelectedItem().toString());
				territorySelected.setFocusable(false);

				try {
					mp.assignNewContinent((String) selectModifiedContinentCB.getSelectedItem(),
							territorySelected.getText());
					JOptionPane.showMessageDialog(mapEditorFrame, "Territory " + territorySelected.getText()
							+ " is assigned new continent " + selectModifiedContinentCB.getSelectedItem());
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}

			}

		});
		/**
		 * A method to list out all the countries of the continents
		 */
		viewCountriesOfContinent.addActionListener(new ActionListener() {
			ArrayList countriesListData = new ArrayList<String>();

			public void actionPerformed(ActionEvent e) {
				// Setting the visibility of the components
				newcountryname.setVisible(false);
				selectContinentForNewCountry.setVisible(false);
				selectAdjacentCountry.setVisible(false);
				newcountrynamelabel.setVisible(false);
				continentfornewcountry.setVisible(false);
				adjacenttonewcountry.setVisible(false);
				addadjacentcountry.setVisible(false);
				submitNewCountry.setVisible(false);

				continentSelected.setVisible(true);
				countriesOfSelectedContinent.setVisible(true);
				selectedContinentField.setVisible(true);
				countriesOfSelectedContinentCB.setVisible(true);

				selectedContinentField.setText(continentsComboBox.getSelectedItem().toString());
				selectedContinentField.setFocusable(false);

				MapWriter mp = new MapWriter(MAP_FILE_NAME);

				try {
					Map<String, Territory> countriesListData;// display countries from array list.
					countriesOfSelectedContinentCB.removeAllItems();
					countriesListData = mapreader
							.getTerritoriesOfContinent(continentsComboBox.getSelectedItem().toString(), MAP_FILE_NAME);

					for (Territory territory : countriesListData.values()) {
						countriesOfSelectedContinentCB.addItem(territory.getName());
					}

					System.out.println(countriesListData.size());

				} catch (Exception e1) {
					
					e1.printStackTrace();
				}

			}

		});
		/**
		 * A method to delete the neighbors of the countries
		 */
		deleteCountryLink.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)

			{

				// Setting up the visibility of the components.
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

				countrySelectedToShowLinksToAddButton.setVisible(false);

				countrySelectedToShowLinksToDeleteButton.setVisible(true);
				countrySelectedToShowLinksToDeleteTF.setVisible(true);
				countrySelectedToShowLinksToDeleteLabel.setVisible(true);
				linksOfSelectedCountryCB.setVisible(true);
				linksOfSelectedCountryLabel.setVisible(true);

				countrySelectedToShowLinksToDeleteTF.setText(countryComboBox.getSelectedItem().toString());
				countrySelectedToShowLinksToDeleteTF.setFocusable(false);

				MapWriter mp = new MapWriter(MAP_FILE_NAME);

				try {
					java.util.List<String> countriesLink = new ArrayList<String>();
					countriesLink.clear();
					linksOfSelectedCountryCB.removeAllItems();
					countriesLink = riskmap.getTerritories().get(countryComboBox.getSelectedItem().toString())
							.getNeighbouringTerritories();

					for (int i = 0; i < countriesLink.size(); i++) {
						linksOfSelectedCountryCB.addItem(countriesLink.get(i));
					}

				} catch (Exception e1) {
					
					e1.printStackTrace();
				}

			}

		});
		/**
		 * A method to show neighbors of selected countries to be deleted if
		 * territory does not have any link to any other territory.
		 */
		countrySelectedToShowLinksToDeleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)

			{

				MapWriter mp = new MapWriter(MAP_FILE_NAME);

				try {
					String s = mp.deleteLink((String) linksOfSelectedCountryCB.getSelectedItem(),
							countrySelectedToShowLinksToDeleteTF.getText());

					if (s.equalsIgnoreCase("OK")) {

						JOptionPane.showMessageDialog(mapEditorFrame, "Territory link Deleted");// confirmation of link deletion
					} else {
						// if link cannot be deleted
						JOptionPane.showMessageDialog(mapEditorFrame,
								"Action cannot be performed as some territory has just one link to this territory or this territory just have one link");

					}

				} catch (Exception e1) {
					
					e1.printStackTrace();
				}

			}

		});
		/**
		 * A method to add neighbors to the country.
		 */

		addCountryLink.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)

			{

				// Setting up the visibility of the components
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

				countrySelectedToShowLinksToDeleteButton.setVisible(false);

				// countrySelectedToShowLinksToDeleteButton.setVisible(true);
				countrySelectedToShowLinksToAddButton.setVisible(true);
				countrySelectedToShowLinksToDeleteTF.setVisible(true);
				countrySelectedToShowLinksToDeleteLabel.setVisible(true);
				linksOfSelectedCountryCB.setVisible(true);
				linksOfSelectedCountryLabel.setVisible(true);

				countrySelectedToShowLinksToDeleteTF.setText(countryComboBox.getSelectedItem().toString());
				countrySelectedToShowLinksToDeleteTF.setFocusable(false);

				MapWriter mp = new MapWriter(MAP_FILE_NAME);

				try {
					for (int i = 0; i < riskmap.getTerritories().keySet().toArray().length; i++) {
						linksOfSelectedCountryCB.addItem(riskmap.getTerritories().keySet().toArray()[i]);// adding
																											// the
																											// links
					}

				} catch (Exception e1) {
					
					e1.printStackTrace();
				}

			}

		});
		/**
		 * Method to create a button to show neighbors of the selected country
		 */
		countrySelectedToShowLinksToAddButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)

			{
				MapWriter mw = new MapWriter(MAP_FILE_NAME);

				try {
					String s = mw.addLink((String) linksOfSelectedCountryCB.getSelectedItem(),
							countrySelectedToShowLinksToDeleteTF.getText());

					if (s.equalsIgnoreCase("OK")) {
						mw.addLink(countrySelectedToShowLinksToDeleteTF.getText(),
								(String) linksOfSelectedCountryCB.getSelectedItem());
						JOptionPane.showMessageDialog(mapEditorFrame, "Territory link added");
					} else if (s.equalsIgnoreCase("ERROR_LinkAlreadyExists")) {
						JOptionPane.showMessageDialog(mapEditorFrame, "Link already present");
					} else {
						JOptionPane.showMessageDialog(mapEditorFrame, "Cannot add itself as adjacent country");
					}

				} catch (Exception e1) {
					
					e1.printStackTrace();
				}

			}

		});

		saveMap.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// setting the visibility of components

				mapEditorFrame.dispose();
			}
		});

		mapEditorFrame.setSize(900, 500);
		mapEditorFrame.setLayout(null);
		mapEditorFrame.setVisible(true);

		mapEditorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}