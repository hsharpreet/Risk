package game.risk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import game.risk.model.MapReader;
import game.risk.model.MapWriter;
import game.risk.model.entities.RiskMap;
import game.risk.model.entities.Territory;
import game.risk.model.validation.ValidateMapWriter;

/**
 * A class to load and edit the map
 * 
 * @author Team
 *
 */
public class MapEditor {

	private MapReader mapReader;
	private RiskMap riskMap;
	private String mapFile;

	private LinkedHashSet<String> adjacentCountriesToNewCountry;

	public MapEditor(String mapFile) {

		this.mapFile = mapFile;

		mapReader = new MapReader();
		try {
			riskMap = mapReader.readMap(mapFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that loads the riskMap object on GUI
	 * 
	 * @throws Exception
	 *             runtime exception
	 */
	public void loadMap() throws Exception {
		JFrame mapEditorFrame = new JFrame("MAP GUI");

		// Declaration
		JButton addCountry, addContinent, deleteContinent, addAdjacentCountry, submitNewCountry, deleteCountry,
				changeContinent, assignNewContinent, viewCountriesOfContinent, deleteCountryLink,
				deleteSelectedLinkOfCountry, countrySelectedToShowLinksToDeleteButton, addCountryLink,
				countrySelectedToShowLinksToAddButton, saveMap;

		JLabel continentLabel, countryLabel, newCountryNameLabel, continentForNewCountry, adjacentToNewCountry,
				selectedTerritoryToModify, presentContinent, selectNewContinentToAssign, continentSelected,
				countriesOfSelectedContinent, countrySelectedToShowLinksToDeleteLabel, linksOfSelectedCountryLabel;

		JTextField newcountryname, territorySelected, presentContinentField, selectedContinentField,
				countrySelectedToShowLinksToDeleteTF;

		JComboBox selectContinentForNewCountry, selectAdjacentCountry, selectModifiedContinentCB,
				countriesOfSelectedContinentCB, linksOfSelectedCountryCB, continentsComboBox, countryComboBox;

		// Creating the GUI
		addCountry = new JButton("Add New Country");
		deleteCountry = new JButton("Delete Selected Country");
		changeContinent = new JButton("Change Continent");
		deleteCountryLink = new JButton("Delete Neighbour");
		addCountryLink = new JButton("Add Neighbour");
		addContinent = new JButton("Add New Continent");
		deleteContinent = new JButton("Delete Selected Continent");
		viewCountriesOfContinent = new JButton("View Countries Of Continent");
		addAdjacentCountry = new JButton("Add");
		submitNewCountry = new JButton("Submit data");
		assignNewContinent = new JButton("Change Continent");

		continentLabel = new JLabel("Continents");
		countryLabel = new JLabel("Countries");
		newCountryNameLabel = new JLabel("Enter Name");
		continentForNewCountry = new JLabel("Continent");
		adjacentToNewCountry = new JLabel("Select Neighbour");
		selectedTerritoryToModify = new JLabel("Selected Country");
		selectNewContinentToAssign = new JLabel("Select New Continent");
		presentContinent = new JLabel("Present Continent");
		continentSelected = new JLabel("Selected Continent");
		countriesOfSelectedContinent = new JLabel("Countries Present");

		DefaultComboBoxModel continentComboBoxModel = new DefaultComboBoxModel<>(
				riskMap.getContinents().keySet().toArray());
		continentsComboBox = new JComboBox(continentComboBoxModel);
		DefaultComboBoxModel countriesComboBoxModel = new DefaultComboBoxModel<>(
				riskMap.getTerritories().keySet().toArray());
		countryComboBox = new JComboBox(countriesComboBoxModel);
		selectModifiedContinentCB = new JComboBox(continentComboBoxModel);
		countriesOfSelectedContinentCB = new JComboBox<>();

		newcountryname = new JTextField();
		territorySelected = new JTextField();
		presentContinentField = new JTextField();
		selectedContinentField = new JTextField();

		countrySelectedToShowLinksToDeleteLabel = new JLabel("Selected Country");
		countrySelectedToShowLinksToDeleteTF = new JTextField();
		countrySelectedToShowLinksToDeleteButton = new JButton("Delete Neighbour");
		countrySelectedToShowLinksToAddButton = new JButton("Add Neighbour");
		linksOfSelectedCountryLabel = new JLabel("Neighbour to Modify");
		saveMap = new JButton("Go Back");
		linksOfSelectedCountryCB = new JComboBox<>();

		selectContinentForNewCountry = new JComboBox(continentComboBoxModel);
		selectAdjacentCountry = new JComboBox(countriesComboBoxModel);
		// Setting the coordinates
		addCountry.setBounds(770, 143, 160, 30);
		deleteCountry.setBounds(770, 23, 160, 30);
		changeContinent.setBounds(770, 53, 160, 30);
		assignNewContinent.setBounds(670, 300, 120, 30);
		addContinent.setBounds(230, 83, 180, 30);
		viewCountriesOfContinent.setBounds(230, 53, 180, 30);
		deleteContinent.setBounds(230, 23, 180, 30);
		continentSelected.setBounds(40, 200, 150, 30);
		countriesOfSelectedContinent.setBounds(200, 200, 150, 30);
		selectedContinentField.setBounds(40, 225, 150, 30);
		countriesOfSelectedContinentCB.setBounds(200, 225, 150, 30);
		deleteCountryLink.setBounds(770, 83, 160, 30);
		addCountryLink.setBounds(770, 113, 160, 30);

		continentLabel.setBounds(40, 0, 100, 30);
		countryLabel.setBounds(580, 0, 100, 30);
		continentsComboBox.setBounds(40, 24, 190, 30);
		countryComboBox.setBounds(580, 24, 190, 30);

		newcountryname.setBounds(460, 225, 120, 30);
		selectContinentForNewCountry.setBounds(590, 225, 120, 30);
		selectAdjacentCountry.setBounds(720, 225, 120, 30);

		territorySelected.setBounds(540, 225, 120, 30);
		selectModifiedContinentCB.setBounds(810, 225, 120, 30);
		selectedTerritoryToModify.setBounds(540, 200, 120, 30);
		selectNewContinentToAssign.setBounds(810, 200, 120, 30);
		presentContinent.setBounds(670, 200, 120, 30);
		presentContinentField.setBounds(670, 225, 120, 30);

		newCountryNameLabel.setBounds(460, 200, 120, 30);
		continentForNewCountry.setBounds(590, 200, 120, 30);
		adjacentToNewCountry.setBounds(720, 200, 120, 30);
		addAdjacentCountry.setBounds(870, 225, 60, 30);
		submitNewCountry.setBounds(590, 300, 120, 30);

		countrySelectedToShowLinksToDeleteLabel.setBounds(670, 200, 120, 30);
		countrySelectedToShowLinksToDeleteTF.setBounds(670, 225, 120, 30);
		countrySelectedToShowLinksToDeleteButton.setBounds(730, 300, 120, 30);
		linksOfSelectedCountryCB.setBounds(810, 225, 120, 30);
		linksOfSelectedCountryLabel.setBounds(810, 200, 120, 30);
		countrySelectedToShowLinksToAddButton.setBounds(730, 300, 120, 30);
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
		mapEditorFrame.add(newCountryNameLabel);
		mapEditorFrame.add(continentForNewCountry);
		mapEditorFrame.add(adjacentToNewCountry);
		mapEditorFrame.add(addAdjacentCountry);
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
		newCountryNameLabel.setVisible(false);
		continentForNewCountry.setVisible(false);
		adjacentToNewCountry.setVisible(false);
		addAdjacentCountry.setVisible(false);
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
				MapWriter mapWriter = new MapWriter(mapFile);
				LinkedHashSet<String> neighbouringCountryList = new LinkedHashSet<>();
				String newCountryEntry = "";
				JTextField name = new JTextField();
				JTextField value = new JTextField();
				JTextField newCountry = new JTextField();

				JComboBox neighbours = new JComboBox(riskMap.getTerritories().keySet().toArray());
				Object[] message = { "Name:", name, "Value:", value, "New Country:", newCountry, "Neighbour:",
						neighbours };

				int option = JOptionPane.showConfirmDialog(mapEditorFrame, message, "Continent Details",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) { // if user selects ok option
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

						try {
							mapWriter.addContinent(name.getText(), value.getText());
							continentComboBoxModel.addElement(name.getText());
							neighbouringCountryList.add((String) neighbours.getSelectedItem());
							newCountryEntry = newCountry.getText() + "," + "0" + "," + "0" + "," + name.getText() + ","
									+ neighbours.getSelectedItem();
							mapWriter.addTerritory(newCountryEntry);
							mapWriter.addNewCountryLinkToTerritories(newCountry.getText(), neighbouringCountryList);

							countriesComboBoxModel.addElement(newCountry.getText());

							JOptionPane.showMessageDialog(mapEditorFrame, "Continent Added Sucessfully");
							riskMap = mapReader.readMap(mapFile);

						} catch (Exception e1) {

							e1.printStackTrace();
						}
					}
				}

				else {

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
				if (option == JOptionPane.OK_OPTION)// if user selects OK option
				{
					MapWriter mapWriter = new MapWriter(mapFile);
					ValidateMapWriter validate = new ValidateMapWriter();

					try {
						String continentSelected = continentsComboBox.getSelectedItem().toString();
						Map<String, Territory> territoriesList = mapReader.getTerritoriesOfContinent(continentSelected,
								mapFile);

						status = validate.checkTerritoriesBeforeDeletingContinent(continentSelected, mapFile);
						if (status) {
							String deleteStatus = mapWriter
									.deleteContinent((String) continentsComboBox.getSelectedItem(), territoriesList);
							if (deleteStatus == "OK") {
								continentComboBoxModel.removeElementAt(continentsComboBox.getSelectedIndex());
								for (Territory territoryToDelete : territoriesList.values()) {
									countriesComboBoxModel.removeElement(territoryToDelete.getName());
								}
								JOptionPane.showMessageDialog(mapEditorFrame, "Continent deleted");
								riskMap = mapReader.readMap(mapFile);
							} else {
								JOptionPane.showMessageDialog(mapEditorFrame,
										"Cannot proceed : Deletion of some country will lead to invalid map");
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

				newcountryname.setText("");
				newcountryname.setVisible(true);
				selectContinentForNewCountry.setVisible(true);
				selectAdjacentCountry.setVisible(true);
				newCountryNameLabel.setVisible(true);
				continentForNewCountry.setVisible(true);
				adjacentToNewCountry.setVisible(true);
				addAdjacentCountry.setVisible(true);
				submitNewCountry.setVisible(true);
				adjacentCountriesToNewCountry = new LinkedHashSet<>();

			}
		});

		/**
		 * a method to add adjacent countries to the world.map
		 */
		addAdjacentCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!(newcountryname.getText().trim().isEmpty()))// if user adds a country
				{
					adjacentCountriesToNewCountry.add((String) selectAdjacentCountry.getSelectedItem());
				}

				else {
					JOptionPane.showMessageDialog(mapEditorFrame, "Please fill something in country name");// if the
																											// field is
																											// empty
																											// show this
																											// message

				}

			}
		});

		/**
		 * A method which adds a new country with selected continent and at least one
		 * adjacent country .
		 */
		submitNewCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!(newcountryname.getText().trim().isEmpty())) {

					MapWriter writeTerritory = new MapWriter(mapFile);// Adding country to file

					String newCountryEntry = "";
					String newCountryName = newcountryname.getText();
					String continentOfNewCountry = (String) selectContinentForNewCountry.getSelectedItem();

					newCountryEntry = newCountryName + "," + "0" + "," + "0" + "," + continentOfNewCountry;
					try {
						if (new ValidateMapWriter().checkLinkedTerritoriesWithSelectedContinent(continentOfNewCountry,
								new ArrayList<>(adjacentCountriesToNewCountry), mapFile)) {
							for (String temp : adjacentCountriesToNewCountry) {
								newCountryEntry = newCountryEntry + "," + temp;
							}

							writeTerritory.addTerritory(newCountryEntry);
							writeTerritory.addNewCountryLinkToTerritories(newcountryname.getText(),
									adjacentCountriesToNewCountry);
							countriesComboBoxModel.addElement(newcountryname.getText());

							JOptionPane.showMessageDialog(mapEditorFrame, "Territory with continent "
									+ continentOfNewCountry + " and specified links has been added");
							riskMap = mapReader.readMap(mapFile);
							// setting up the visibility
							newcountryname.setVisible(false);
							selectContinentForNewCountry.setVisible(false);
							selectAdjacentCountry.setVisible(false);

							newCountryNameLabel.setVisible(false);
							continentForNewCountry.setVisible(false);
							adjacentToNewCountry.setVisible(false);
							addAdjacentCountry.setVisible(false);
							submitNewCountry.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(mapEditorFrame,
									"Select atleast one neighbouring country from " + continentOfNewCountry);
						}

					}

					catch (IOException e1) {

						e1.printStackTrace();
					} catch (Exception e1) {

						e1.printStackTrace();
					}
				}

				else {
					JOptionPane.showMessageDialog(mapEditorFrame, "Please fill something in country name"); // if field
																											// is empty
																											// show this
																											// message.

				}
			}

		});

		/**
		 * A method to delete country from the file if there is no link to any territory
		 * 
		 */

		deleteCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String status;

				String terittoryToDelete = countryComboBox.getSelectedItem().toString();

				MapWriter deleteTerritory = new MapWriter(mapFile);

				try {
					status = deleteTerritory.deleteTerritory(terittoryToDelete);

					if (status.equalsIgnoreCase("OK")) {
						// confirmation message
						JOptionPane.showMessageDialog(mapEditorFrame, "Territory Deleted");
						countriesComboBoxModel.removeElementAt(countryComboBox.getSelectedIndex());
						riskMap = mapReader.readMap(mapFile);
					} else if (status.equalsIgnoreCase("ERROR_UNCONNECTED_CONTINENT")) {
						// if territory deletion results in unconnected continent
						JOptionPane.showMessageDialog(mapEditorFrame, "Action cannot be performed as deletion of "
								+ terittoryToDelete + " results in unconnected continent");
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
		 * A method which displays the country selected, present continent and drop down
		 * of the new continent which user wants to select
		 */
		changeContinent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // changing the continent
				// setting up the visibility of the components
				newcountryname.setVisible(false);
				selectContinentForNewCountry.setVisible(false);
				selectAdjacentCountry.setVisible(false);
				newCountryNameLabel.setVisible(false);
				continentForNewCountry.setVisible(false);
				adjacentToNewCountry.setVisible(false);
				addAdjacentCountry.setVisible(false);
				submitNewCountry.setVisible(false);
				countrySelectedToShowLinksToAddButton.setVisible(false);
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
				presentContinentField.setText(riskMap.getTerritories().get(territorySelected.getText()).getContinent());
				presentContinentField.setFocusable(false);
			}
		});

		/**
		 * A method to assign new continent selected by the user in the previous method.
		 */
		assignNewContinent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// changes to the file to assign new country
				MapWriter mp = new MapWriter(mapFile);
				territorySelected.setText(countryComboBox.getSelectedItem().toString());
				territorySelected.setFocusable(false);
				if (presentContinentField.getText()
						.equalsIgnoreCase((String) selectModifiedContinentCB.getSelectedItem())) {
					JOptionPane.showMessageDialog(mapEditorFrame,
							"This continent is already assigned , please select other");
				} else {
					try {
						String status = mp.assignNewContinent((String) selectModifiedContinentCB.getSelectedItem(),
								territorySelected.getText());
						if (status.equalsIgnoreCase("OK")) {
							JOptionPane.showMessageDialog(mapEditorFrame, "Territory " + territorySelected.getText()
									+ " is assigned new continent " + selectModifiedContinentCB.getSelectedItem());
							riskMap = mapReader.readMap(mapFile);
						} else {
							// if change continent leads to unconnected continent
							JOptionPane.showMessageDialog(mapEditorFrame,
									"Action cannot be performed as it results in unconnected continent");
						}
					} catch (Exception e1) {

						e1.printStackTrace();
					}

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
				newCountryNameLabel.setVisible(false);
				continentForNewCountry.setVisible(false);
				adjacentToNewCountry.setVisible(false);
				addAdjacentCountry.setVisible(false);
				submitNewCountry.setVisible(false);

				continentSelected.setVisible(true);
				countriesOfSelectedContinent.setVisible(true);
				selectedContinentField.setVisible(true);
				countriesOfSelectedContinentCB.setVisible(true);

				selectedContinentField.setText(continentsComboBox.getSelectedItem().toString());
				selectedContinentField.setFocusable(false);

				MapWriter mp = new MapWriter(mapFile);

				try {
					Map<String, Territory> countriesListData;// display countries from array list.
					countriesOfSelectedContinentCB.removeAllItems();
					countriesListData = mapReader
							.getTerritoriesOfContinent(continentsComboBox.getSelectedItem().toString(), mapFile);

					for (Territory territory : countriesListData.values()) {
						countriesOfSelectedContinentCB.addItem(territory.getName());
					}

				} catch (Exception e1) {

					e1.printStackTrace();
				}

			}

		});

		/**
		 * A method to delete the neighbors of the countries
		 */
		deleteCountryLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Setting up the visibility of the components.
				newcountryname.setVisible(false);
				selectContinentForNewCountry.setVisible(false);
				selectAdjacentCountry.setVisible(false);
				newCountryNameLabel.setVisible(false);
				continentForNewCountry.setVisible(false);
				adjacentToNewCountry.setVisible(false);
				addAdjacentCountry.setVisible(false);
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

				MapWriter mp = new MapWriter(mapFile);

				try {
					java.util.List<String> countriesLink = new ArrayList<String>();
					countriesLink.clear();
					linksOfSelectedCountryCB.removeAllItems();
					countriesLink = riskMap.getTerritories().get(countryComboBox.getSelectedItem().toString())
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
		 * A method to show neighbors of selected countries to be deleted if territory
		 * does not have any link to any other territory.
		 */
		countrySelectedToShowLinksToDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MapWriter mp = new MapWriter(mapFile);

				try {
					String s = mp.deleteLink((String) linksOfSelectedCountryCB.getSelectedItem(),
							countrySelectedToShowLinksToDeleteTF.getText());

					if (s.equalsIgnoreCase("OK")) {

						JOptionPane.showMessageDialog(mapEditorFrame, "Territory link Deleted");// confirmation of link
																								// deletion
						riskMap = mapReader.readMap(mapFile);
						countrySelectedToShowLinksToDeleteButton.setVisible(false);
						countrySelectedToShowLinksToDeleteTF.setVisible(false);
						countrySelectedToShowLinksToDeleteLabel.setVisible(false);
						linksOfSelectedCountryCB.setVisible(false);
						linksOfSelectedCountryLabel.setVisible(false);
						countrySelectedToShowLinksToDeleteButton.setVisible(false);
					} else if (s.equalsIgnoreCase("ERROR_UNCONNECTED_CONTINENT")) {
						// if link cannot be deleted due to unconnected graph
						JOptionPane.showMessageDialog(mapEditorFrame,
								"Action cannot be performed as deletion result in unconnected continent");

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
			public void actionPerformed(ActionEvent e) {

				// Setting up the visibility of the components
				newcountryname.setVisible(false);
				selectContinentForNewCountry.setVisible(false);
				selectAdjacentCountry.setVisible(false);
				newCountryNameLabel.setVisible(false);
				continentForNewCountry.setVisible(false);
				adjacentToNewCountry.setVisible(false);
				addAdjacentCountry.setVisible(false);
				submitNewCountry.setVisible(false);

				territorySelected.setVisible(false);
				selectModifiedContinentCB.setVisible(false);
				selectedTerritoryToModify.setVisible(false);
				selectNewContinentToAssign.setVisible(false);
				assignNewContinent.setVisible(false);
				presentContinent.setVisible(false);
				presentContinentField.setVisible(false);

				countrySelectedToShowLinksToDeleteButton.setVisible(false);

				countrySelectedToShowLinksToAddButton.setVisible(true);
				countrySelectedToShowLinksToDeleteTF.setVisible(true);
				countrySelectedToShowLinksToDeleteLabel.setVisible(true);
				linksOfSelectedCountryCB.setVisible(true);
				linksOfSelectedCountryLabel.setVisible(true);

				countrySelectedToShowLinksToDeleteTF.setText(countryComboBox.getSelectedItem().toString());
				countrySelectedToShowLinksToDeleteTF.setFocusable(false);

				MapWriter mp = new MapWriter(mapFile);

				try {
					for (int i = 0; i < riskMap.getTerritories().keySet().toArray().length; i++) {
						linksOfSelectedCountryCB.addItem(riskMap.getTerritories().keySet().toArray()[i]);// adding the
																											// links

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
		
		/**
		 * Method to create a button to show neighbors of the selected country
		 */
		countrySelectedToShowLinksToAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MapWriter mw = new MapWriter(mapFile);

				try {
					String s = mw.addLink((String) linksOfSelectedCountryCB.getSelectedItem(),
							countrySelectedToShowLinksToDeleteTF.getText());

					if (s.equalsIgnoreCase("OK")) {
						mw.addLink(countrySelectedToShowLinksToDeleteTF.getText(),
								(String) linksOfSelectedCountryCB.getSelectedItem());
						JOptionPane.showMessageDialog(mapEditorFrame, "Territory link added");
						riskMap = mapReader.readMap(mapFile);
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
		mapEditorFrame.setSize(1000, 600);
		mapEditorFrame.setLayout(null);
		mapEditorFrame.setVisible(true);

		mapEditorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Method to get RiskMap object
	 * 
	 * @return RiskMap object of RiskMap after reading the map file
	 */
	public RiskMap getRiskMap() {
		return riskMap;
	}

}