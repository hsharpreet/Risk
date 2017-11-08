package game.risk.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;

import game.risk.gui.MapFromScratch;
import game.risk.model.validation.ValidateMapWriter;
import game.risk.util.MapReader;
import game.risk.util.Territory;

/**
 * Class to write in the World.map file
 * 
 * @author Team
 *
 */
public class MapWriter {

	String mapFileName;// the map file

	public MapWriter(String mapFileName) {
		this.mapFileName = mapFileName;
	}

	/**
	 * Method to add continent in the file with its name and the number of armies
	 * 
	 * @param name
	 *            the name of the continent
	 * @param value
	 *            the value of the armies which a player receives during
	 *            reenforcement if he owns all the countries of the continent
	 * @throws IOException
	 */
	public void addContinent(String name, String value) throws IOException {
		String newContinent = name + "=" + value;
		File inputFile = new File(mapFileName);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		File outFile = new File("temp.map");// the file path
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) {// writing the continents
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Continents]")) {
				printWriter.println(newContinent);
			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);
	}

	/**
	 * Method to delete continent by writing it into temp.map from world.map
	 * 
	 * @param name
	 *            the name of the continents
	 * @throws Exception 
	 */
	public String deleteContinent(String name, Map<String,Territory> territoriesOfContinent) throws Exception {
		String status="OK";
		File inputFile = new File(mapFileName);// file path to read from
		File copyOfInputFile = new File(mapFileName+"_copy");
		Files.copy(inputFile.toPath(), copyOfInputFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
		for (Territory territoryToDelete : territoriesOfContinent.values()) {
			deleteTerritoriesOfContinentDeleted(territoryToDelete.getName());
		}
		MapReader mapReader = new MapReader();
		RiskMap copyRiskMap = mapReader.readMap(mapFileName+"_copy");
		if(copyRiskMap==null){  // check copy of map for unconnected continent after deleting the countries of given continent
			copyOfInputFile.delete();
			return "ERROR";
		}
		else{
			BufferedReader br = new BufferedReader(new FileReader(copyOfInputFile));
			File outFile = new File("temp.map");// file path to write
			FileOutputStream outStream = new FileOutputStream(outFile);
			PrintWriter printWriter = new PrintWriter(outStream);
			String thisLine = "";
			while ((thisLine = br.readLine()) != null) {
				if (!thisLine.toLowerCase().contains(name.toLowerCase())) {
					printWriter.println(thisLine);
				}
			}
			printWriter.flush();
			printWriter.close();
			br.close();
			copyOfInputFile.delete();
			inputFile.delete();
			outFile.renameTo(inputFile);
		}
		
		return status;
	}

	/**
	 * Method to add territory
	 * 
	 * @param name
	 *            the name of the territory
	 * @throws IOException
	 */
	public void addTerritory(String name) throws IOException {
		String newTerritory = name;
		File inputFile = new File(mapFileName);// file path to read from
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		File outFile = new File("temp.map");// file path to write
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) {
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Territories]")) {
				printWriter.println(newTerritory);
				
			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);
	}

	/**
	 * Method to add new country neighbors to territories
	 * 
	 * @param newCountryEntry
	 *            the entry of new country
	 * @param adjacentCountriesToNewCountry
	 *            the adjacent countries of the new country
	 * @throws Exception
	 */
	public void addNewCountryLinkToTerritories(String newCountryEntry,
			LinkedHashSet<String> adjacentCountriesToNewCountry) throws Exception {
		String newTerritory = newCountryEntry;
		File inputFile = new File(mapFileName);// file path to read from
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		File outFile = new File("temp.map");// file path to write
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) {
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Territories]")) {
				while ((thisLine = br.readLine()) != null && thisLine != "") {
					for (String element : adjacentCountriesToNewCountry) {
						String[] columns = thisLine.split(",");
						if (columns[0].equals(element)) {
							thisLine = thisLine + "," + newCountryEntry;
						}
					}
					printWriter.println(thisLine);
				}
			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);
	}

	/**
	 * Method to delete territory from world.map
	 * 
	 * @param territoryToDelete
	 *            the territory to be deleted
	 * @return status status of the process
	 * @throws Exception
	 */
	public String deleteTerritory(String territoryToDelete) throws Exception {
		ValidateMapWriter validateMapWriter = new ValidateMapWriter();
		String status = "OK";
		if (!validateMapWriter.checkAdjacentTerritoryLinkBeforeDelete(territoryToDelete, mapFileName)) {
			return "ERROR";
		}
		File inputFile = new File(mapFileName);// file path to read from
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		ArrayList<String> territoryList = new ArrayList<>();
		File outFile = new File("temp.map");// file path to write
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) {
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Territories]")) {// reading the file to delete the territory
				while ((thisLine = br.readLine()) != null && thisLine != "") {
					String[] columns = thisLine.split(",");
					if (columns[0].equals(territoryToDelete)) {
						for (int i = 4; i < columns.length; i++) {
							territoryList.add(columns[i]);
						
						}
						continue;
					}
					printWriter.println(thisLine);
				}
			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		status = deleteLinksOfDeletedTerritoryFromOthers(territoryList, territoryToDelete);
		System.out.println("deleting cuntry status: "+status);
		if(status.equals("ERROR_UNCONNECTED_CONTINENT")){
			outFile.delete();
		}
		else {
			inputFile.delete();
			outFile.renameTo(inputFile);
		}
		return status;
	}


	/**
	 * Method to delete neighbors of the deleted territory from the file
	 * 
	 * @param territoryList
	 *            the list of the territories
	 * @param territoryToDelete
	 *            the territory to be deleted
	 * @throws Exception
	 */
	private String deleteLinksOfDeletedTerritoryFromOthers(ArrayList<String> territoryList, String territoryToDelete)
			throws Exception {
		ArrayList<String> listOfTerritories = territoryList;
		String territory = territoryToDelete;
		for (int i = 0; i < listOfTerritories.size(); i++) {
			File inputFile = new File("temp.map");// file path to read
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			File outFile = new File("temp1.map");// file path to write
			FileOutputStream outStream = new FileOutputStream(outFile);
			PrintWriter printWriter = new PrintWriter(outStream);
			String thisLine = "";
			while ((thisLine = br.readLine()) != null) {
				printWriter.println(thisLine);
				if (thisLine.equalsIgnoreCase("[Territories]")) {// reading the file to delete the link
					String modifiedLink = "";
					while ((thisLine = br.readLine()) != null && thisLine != "") {
						String[] columns = thisLine.split(",");
						if (columns[0].equalsIgnoreCase(listOfTerritories.get(i))) {
							for (int j = 0; j < columns.length; j++) {
								if (j < 4) {
									modifiedLink = modifiedLink.concat(columns[j] + ",");
								}
								if (j >= 4) {
									if (columns[j].equalsIgnoreCase(territory)) {

									} else {
										modifiedLink = modifiedLink.concat(columns[j] + ",");
									}
								}
							}
							printWriter.println(modifiedLink.substring(0, modifiedLink.length() - 1));
						} else {
							printWriter.println(thisLine);
						}
					}
				}
			}
			printWriter.flush();
			printWriter.close();
			br.close();
			inputFile.delete();
			outFile.renameTo(inputFile);
		}
		MapReader mapReader = new MapReader();
		RiskMap tempRiskMap = mapReader.readMap("temp.map");
		if(tempRiskMap==null){  // check temp map for unconnected continent after deleting the country
			return "ERROR_UNCONNECTED_CONTINENT";
		}
		return "OK";
	}


	/**
	 * Method to assign new continent to the territory
	 * 
	 * @param selectedItem
	 *            the selected continent
	 * @param territory
	 *            the territory to which the continent has to be assigned
	 */
	public String assignNewContinent(String selectedItem, String territory) {
		try {
			String newContinent = selectedItem;
			String territorySelected = territory;
			File inputFile = new File(mapFileName);
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			File outFile = new File("temp.map");
			FileOutputStream outStream = new FileOutputStream(outFile);
			PrintWriter printWriter = new PrintWriter(outStream);
			String thisLine = "";
			while ((thisLine = br.readLine()) != null) {
				printWriter.println(thisLine);
				if (thisLine.equalsIgnoreCase("[Territories]")) {
					String newLine = "";
					while ((thisLine = br.readLine()) != null && thisLine != "") {
						String[] columns = thisLine.split(",");
						if (columns.length >= 5) {
							if (columns[0].equals(territorySelected)) {
								columns[3] = newContinent;
								for (int i = 0; i < columns.length; i++) {
									newLine = newLine.concat(columns[i] + ",");
								}
								newLine = newLine.substring(0, newLine.length() - 1);
								printWriter.println(newLine);
							} else {
								printWriter.println(thisLine);
							}
						} else {
							printWriter.println(thisLine);
						}
					}
				}
			}
			printWriter.flush();
			printWriter.close();
			br.close();
			MapReader mapReader = new MapReader();
			RiskMap tempRiskMap = mapReader.readMap("temp.map");
			if(tempRiskMap==null){  // check temp map for unconnected continent after deleting the country
				outFile.delete();
				return "ERROR_UNCONNECTED_CONTINENT";
			}
			inputFile.delete();
			outFile.renameTo(inputFile);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "OK";
	}

	/**
	 * Method to delete the neighbor
	 * 
	 * @param link
	 *            the link to be deleted
	 * @param country
	 *            the country whose link has to be deleted
	 * @return status
	 */

	public String deleteLink(String link, String country) {
		ValidateMapWriter validate = new ValidateMapWriter();// object of validation class
		String status = "OK";
		try {
			ArrayList<String> al = new ArrayList<>();
			al.add(link);

			if (validate.checkTerritoryLinkBeforeDeleteLink(country, link, mapFileName)) {
				status=deleteLinksOfmodifiedTerritory(al, country);
			}
			else {
				status="ERROR";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

	/**
	 * Method to delete neighbors of modified territory
	 * 
	 * @param link
	 *            an array list of links
	 * @param country
	 *            the country whose link will be deleted
	 * @throws Exception
	 */
	private String deleteLinksOfmodifiedTerritory(ArrayList<String> link, String country) throws Exception{
		ArrayList<String> link_name = link;
		String country_name = country;
		File inputFile = new File(mapFileName);// file path to read from
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		File outFile = new File("temp.map");// file path to write to
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) {
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Territories]")) {
				String modifiedLink = "";
				while ((thisLine = br.readLine()) != null && thisLine != "") {
					String[] columns = thisLine.split(",");// splitting the element of the array

					if (columns[0].equalsIgnoreCase(link_name.get(0))) {
						for (int j = 0; j < columns.length; j++) {
							if (j < 4) {
								modifiedLink = modifiedLink.concat(columns[j] + ",");
							}
							if (j >= 4) {
								if (columns[j].equalsIgnoreCase(country_name)) {

								} else {
									modifiedLink = modifiedLink.concat(columns[j] + ",");
								}
							}
						}
						printWriter.println(modifiedLink.substring(0, modifiedLink.length() - 1));
					}

					else if (columns[0].equalsIgnoreCase(country_name)) {
						for (int j = 0; j < columns.length; j++) {
							if (j < 4) {
								modifiedLink = modifiedLink.concat(columns[j] + ",");
							}
							if (j >= 4) {
								if (columns[j].equalsIgnoreCase(link_name.get(0))) {

								} else {
									modifiedLink = modifiedLink.concat(columns[j] + ",");
								}
							}
						}
						printWriter.println(modifiedLink.substring(0, modifiedLink.length() - 1));
					}

					else {
						printWriter.println(thisLine);
					}
					modifiedLink = "";
				}
			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		MapReader mapReader = new MapReader();
		RiskMap tempRiskMap = mapReader.readMap("temp.map");
		if(tempRiskMap==null){  // check temp map for unconnected continent after deleting the country
			outFile.delete();
			return "ERROR_UNCONNECTED_CONTINENT";
		}
		inputFile.delete();
		outFile.renameTo(inputFile);
		return "OK";	
		}

	/**
	 * Method to add neighbors if the neighbor does not exist already
	 * 
	 * @param selectedItem
	 *            the selected link
	 * @param text
	 *            the country to which the link will be added
	 * @return the status of the process
	 * @throws Exception
	 */
	public String addLink(String selectedItem, String text) throws Exception{
		ValidateMapWriter validate = new ValidateMapWriter();
		String link = selectedItem;
		String country = text;
		File inputFile = new File(mapFileName);// file to read from
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		File outFile = new File("temp.map");// file to write to
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) {
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Territories]")) {
				String modifiedLink = "";
				while ((thisLine = br.readLine()) != null && thisLine != "") {
					String[] columns = thisLine.split(",");
					if (columns[0].equalsIgnoreCase(country)) {
						if (validate.validateAddLink(country, link, thisLine) == false) {
							return "ERROR_LinkAlreadyExists";
						} else if (validate.validateAddLinkAddingItself(country, link, thisLine) == false) {
							return "ERROR_AddingItselfInLink";
						} else {
							modifiedLink = modifiedLink.concat(thisLine + "," + link);
							printWriter.println(modifiedLink);

						}
					} else {
						printWriter.println(thisLine);
					}
				}

			}
		}

		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);

		return "OK";
	}

	/**
	 * Method to delete territories of the deleted continent
	 * 
	 * @param terittoryToDelete
	 *            the territory to be deleted
	 * @throws Exception
	 */
	public void deleteTerritoriesOfContinentDeleted(String terittoryToDelete) throws Exception {
		ValidateMapWriter validate = new ValidateMapWriter();
		String status = "OK";
		String territoryToDelete = terittoryToDelete;
		File inputFile = new File(mapFileName+"_copy");// file path to read from
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		ArrayList<String> territoryList = new ArrayList<>();
		File outFile = new File("temp.map");// file path to write to
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) {
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Territories]")) {
				while ((thisLine = br.readLine()) != null && thisLine != "") {
					String[] columns = thisLine.split(",");// splitting the element
					if (columns[0].equals(territoryToDelete)) {// deleting the territory

						for (int i = 4; i < columns.length; i++) {
							territoryList.add(columns[i]);
						}
						continue;
					}
					printWriter.println(thisLine);
				}
			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		
	    deleteLinksOfDeletedTerritoryFromOthers(territoryList, territoryToDelete);
	    inputFile.delete();
		outFile.renameTo(inputFile);
	}
	
	public String saveNewMapFromSracth(MapFromScratch mfs) throws Exception {
		String status = "";

		File outputFile = new File(mfs.getMapName());
		if (!outputFile.exists()) {
			BufferedWriter outStream = new BufferedWriter(new FileWriter(mfs.getMapName()));

			String partMap = "[Map]" + "\n" + "author=" + mfs.getAuthorVal() + "\n" + "warn=" + mfs.getWarnVal() + "\n"
					+ "image=" + mfs.getImageVal() + "\n" + "wrap=" + mfs.getWrapVal() + "\n" + "scroll="
					+ mfs.getScrollVal() + "\n" + "\n";

			String partCont = "[Continents]" + "\n" + mfs.getContName() + "=" + mfs.getContVal() + "\n" + "\n";
			String partTerri = "[Territories]" + "\n" + mfs.getTerritoryName() + "," + mfs.getTerritoryCordX() + ","
					+ mfs.getTerritoryCordY() + "," + mfs.getTerritoryContinent() + "\n" + "\n";

			// writing the map file
			outStream.write(partMap);
			outStream.write(partCont);
			outStream.write(partTerri);
			status = "OK";
			outStream.close();
		} else {
			status = "Map file already exists, choose another name.";
		}

		return status;
	}

}
