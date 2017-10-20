package game.risk.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import game.risk.model.validation.ValidateMapReader;

/**
 * Class to read the Map
 * 
 * @author Team
 *
 */
public class MapReader {
	
	/**
	 * Method to get Countries of the continents from the file
	 * @param selectedContinent
	 *            the continent whose countries have to be listed
	 * @param path
	 *            the path of the file
	 * @return countriesList list of the countries of the continents
	 */
	public Map<String, Territory> getTerritoriesOfContinent(String selectedContinent, String path) {
		HashMap<String, Territory> territories = new HashMap<String, Territory>();
		try {
			File inputFile = new File(path);// Code to read from temp.map
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String thisLine = "";

			while ((thisLine = br.readLine()) != null) {
				if (thisLine.equalsIgnoreCase("[Territories]")) {
					while ((thisLine = br.readLine()) != null && thisLine != " ") {
						String[] columns = thisLine.split(",");
						if (columns.length >= 5) {
							if (columns[3].equals(selectedContinent)) {
								Territory territoryObj = new Territory();
								territoryObj.setName(columns[0]);
								territoryObj.setCoordinateX(columns[1]);
								territoryObj.setCoordinateY(columns[2]);
								territoryObj.setContinent(columns[3]);
								List<String> tempNeighboursList = new ArrayList<String>();
								for (int i = 4; i < columns.length; i++)
									tempNeighboursList.add(columns[i]);
								territoryObj.setNeighbouringTerritories(tempNeighboursList);
								territories.put(territoryObj.getName(), territoryObj);
							}
						}
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return territories;
	}

	/**
	 * Method to readMap for MapEditor
	 * 
	 * @param MapFile
	 *            The map file
	 * @throws Exception
	 */
	public RiskMap readMap(String MapFile) throws Exception {

		if (MapFile.equalsIgnoreCase("") || MapFile.equals(null)) {
			MapFile = "World.map";
		}
		File inputFile = new File(MapFile);// reading the file
		BufferedReader br = new BufferedReader(new FileReader(inputFile));

		String line = "";

		HashMap<String, String> mapDetails = new HashMap<String, String>();
		HashMap<String, String> continents = new HashMap<String, String>();
		HashMap<String, Territory> territories = new HashMap<String, Territory>();

		while ((line = br.readLine()) != null) {// writing the map details
			if (line.equalsIgnoreCase("[Map]")) {
				while (!(line = br.readLine()).equalsIgnoreCase("") || line.startsWith("[")) {
					String[] columns = line.split("=");
					mapDetails.put(columns[0], columns[1]);
				}
			
			} else if (line.equalsIgnoreCase("[Continents]")) {// writing the continents in file
				while (!(line = br.readLine()).equalsIgnoreCase("") || line.startsWith("[")) {
					String[] columns = line.split("=");
					continents.put(columns[0], columns[1]);
				}
			} else if (line.equalsIgnoreCase("[Territories]")) {// writing the territories in file
				while ((line = br.readLine()) != null && line != "") {
					String[] columns = line.split(",");
					if (columns.length > 1) {
						Territory territoryObj = new Territory();
						territoryObj.setName(columns[0]);
						territoryObj.setCoordinateX(columns[1]);
						territoryObj.setCoordinateY(columns[2]);
						territoryObj.setContinent(columns[3]);
						List<String> tempNeighboursList = new ArrayList<String>();
						for (int i = 4; i < columns.length; i++)
							tempNeighboursList.add(columns[i]);
						territoryObj.setNeighbouringTerritories(tempNeighboursList);
						territories.put(territoryObj.getName(), territoryObj);
					
					}
				}

			}
		}
		br.close();
		boolean isValidMap = new ValidateMapReader().isMapValid(continents, territories);
		return isValidMap ? new RiskMap(mapDetails, continents, territories) : null;
	}

	/**
	 * Method to get the neighbors of the countries from the file
	 * 
	 * @param countryName
	 *            the name of the country
	 * @param path
	 *            the path of the file
	 * @return links array list of links
	 */
	public ArrayList getLinksOfCountry(String countryName, File path) {
		ArrayList<String> links = new ArrayList<>();
		try {
			String countrySelected = countryName;// reading the file
			File inputFile = new File(path.getName());
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String thisLine = "";
			while ((thisLine = br.readLine()) != null) {
				if (thisLine.equalsIgnoreCase("[Territories]")) {

					while ((thisLine = br.readLine()) != null && thisLine != " ") {
						String[] columns = thisLine.split(",");
						if (columns[0].equals(countryName)) {
							for (int i = 4; i < columns.length; i++) {
								links.add(columns[i]);
							}
						}
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return links;
	}

	// New Function
	/**
	 * Method to readMapFile for Game play
	 * 
	 * @param mapFilePath
	 *            path of file
	 * @return null
	 */
	public static RiskMap readMapFile(String mapFilePath) {
		try { 
			RiskMap riskMap = null;

			FileReader fr = new FileReader(mapFilePath);
			BufferedReader br = new BufferedReader(fr);
			HashMap<String, String> continents = new HashMap<>();
			HashMap<String, Territory> territories = new HashMap<>();

			while (true) {
				String s = br.readLine();
				if (s == null) {
					break;
				} else if (s.equalsIgnoreCase("[Continents]")) {

					while (true) {// writing the continents in the file
						String continentEntryLine = br.readLine();
						if (continentEntryLine.equals("")) {
							break;
						}
						String columns[] = continentEntryLine.split("=");
						continents.put(columns[0], columns[1]);
					}
				} else if (s.equalsIgnoreCase("[Territories]")) {
					while (true) {// writing the territories in the file
						String territoryEntryLine = br.readLine();
						if (territoryEntryLine == null) {
							break;
						} else if (territoryEntryLine.trim().isEmpty()) {
							continue;
						}
						String columns[] = territoryEntryLine.split(",");
						Territory territoryObject = new Territory();
						territoryObject.setName(columns[0]);
						territoryObject.setCoordinateX(columns[1]);
						territoryObject.setCoordinateY(columns[2]);
						territoryObject.setContinent(columns[3]);
						List<String> neighbouringTerritories = new ArrayList<>();
						for (int i = 4; i < columns.length; i++) {
							neighbouringTerritories.add(columns[i]);
						}
						territoryObject.setNeighbouringTerritories(neighbouringTerritories);
						territories.put(territoryObject.getName(), territoryObject);
					}
				}
			}

			boolean isValid = new ValidateMapReader().isMapValid(continents, territories);
			return isValid ? new RiskMap(continents, territories) : null;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Method to get the territory by taking name from the user
	 * 
	 * @param name
	 *            the name from the user
	 * @param territoryList
	 *            the hashMap consisting of territories
	 * @return t the territory if the territory is found
	 * @return null if the territory is not found
	 */
	public Territory getTerritoryByName(String name, Map<String, Territory> territoryList) {
		for (Territory t : territoryList.values()) {
			if (t.getName().equalsIgnoreCase(name)) {
				return t;
			}
		}
		return null;
	}
}
