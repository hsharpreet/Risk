package game.risk.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import game.risk.model.entities.RiskMap;
import game.risk.model.entities.Territory;
import game.risk.model.validation.ValidateMapReader;

/**
 * Class to read the Map
 * 
 * @author Team
 *
 */
public class MapReader {

	public Map<String, List<String>> getcontinentsWithCountries(RiskMap riskMap) {
		Map<String, List<String>> continentAndItsCountries = new HashMap<String, List<String>>();
		Set<String> continentList = riskMap.getContinents().keySet();

		for (String continent : continentList) {
			List<Territory> territoryList = getTerritoriesOfContinent(continent, riskMap.getTerritories());
			List<String> territoryNameList = getTerritoryNameList(territoryList);
			continentAndItsCountries.put(continent, territoryNameList);
		}

		return continentAndItsCountries;
	}

	/**
	 * Method to get the list containing the territories
	 * 
	 * @param territoryList
	 *            an array list of territories
	 * @return nameList an arrayList
	 */
	public List<String> getTerritoryNameList(List<Territory> territoryList) {
		List<String> nameList = new ArrayList<>();
		for (Territory t : territoryList) {
			nameList.add(t.getName());
		}
		return nameList;
	}

	/**
	 * Method which reads the continents and its countries and writes it into a
	 * hashMap
	 * 
	 * @param path
	 *            the path of the file
	 * @return continentAndItsCountries a hashMap
	 */
	public static Map<String, ArrayList<String>> continetAndItsCountries(String path) {
		MapReader mapReader = new MapReader();
		RiskMap riskmap;
		Map<String, ArrayList<String>> continetAndItsCountries = new HashMap<String, ArrayList<String>>();
		try {
			riskmap = mapReader.readMap(path);
			Object[] continents = riskmap.getContinents().keySet().toArray();

			for (int i = 0; i < continents.length; i++) {
				continetAndItsCountries.put(continents[i].toString(),
						mapReader.getCountriesOfContinent(continents[i].toString(), path));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return continetAndItsCountries;
	}

	/**
	 * Method to get the countries of the continents and stores it into an arrayList
	 * 
	 * @param selectedContinent
	 *            the continent Selected
	 * @param path
	 *            the map file
	 * @return countriesList An arrayList
	 */
	public ArrayList<String> getCountriesOfContinent(String selectedContinent, String path) {
		ArrayList<String> countriesList = new ArrayList<String>();
		try {
			File inputFile = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			File outFile = new File("temp.map");
			String thisLine = "";
			while ((thisLine = br.readLine()) != null) {
				if (thisLine.equalsIgnoreCase("[Territories]")) {
					while ((thisLine = br.readLine()) != null && thisLine != " ") {
						String[] columns = thisLine.split(",");
						if (columns.length >= 5) {
							if (columns[3].equals(selectedContinent)) {
								countriesList.add(columns[0]);
							}
						}
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countriesList;
	}

	/**
	 * Method to get the continent of the country
	 * 
	 * @param country
	 *            the name of the country
	 * @param path
	 *            the map file
	 * @return Continent a string variable
	 */
	public String getContinentOfACountry(String country, String path) {
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		map = continetAndItsCountries(path);
		String continent = "";

		for (Entry<String, ArrayList<String>> e : map.entrySet()) {
			if (e.getValue().contains(country)) {
				continent = e.getKey();
			}
		}

		return continent;
	}

	/**
	 * Method to get Countries of the continents from the file
	 * 
	 * @param selectedContinent
	 *            the continent whose countries have to be listed
	 * 
	 * @param path
	 *            the path of the file
	 * 
	 * @return countriesList list of the countries of the continents
	 */
	public Map<String, Territory> getTerritoriesOfContinent(String selectedContinent, String path) {
		HashMap<String, Territory> territories = new HashMap<String, Territory>();
		try {
			File inputFile = new File(path);
			// Code to read from temp.map
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String thisLine = "";

			while ((thisLine = br.readLine()) != null) {
				if (thisLine.equalsIgnoreCase("[Territories]")) {
					while ((thisLine = br.readLine()) != null && thisLine != " ") {
						String[] columns = thisLine.split(",");
						if (columns.length >= 4) {
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
	 * 
	 * @throws Exception
	 *             unchecked
	 * @return RiskMap
	 */
	public RiskMap readMap(String MapFile) throws Exception {
		if (!new ValidateMapReader().isValidMapFileExtention(MapFile)) {
			return null;
		}

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

	/**
	 * Method to readMapFile for Game play
	 * 
	 * @param mapFilePath
	 *            path of file
	 * @return null
	 */
	public static RiskMap readMapFile(String mapFilePath) {
		try {

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
			if (t.getName().equalsIgnoreCase(name.trim())) {
				return t;
			}
		}
		return null;
	}

	/**
	 * Method returns territories of selected continent
	 * 
	 * @param selectedContinent
	 *            name of Continent
	 * @param territories
	 *            name of territory
	 * @return selectedContinentTerritories
	 */

	public List<Territory> getTerritoriesOfContinent(String selectedContinent, HashMap<String, Territory> territories) {
		List<Territory> selectedContinentTerritories = new ArrayList<>();

		for (Territory t : territories.values()) {
			if (t.getContinent().equalsIgnoreCase(selectedContinent)) {
				selectedContinentTerritories.add(t);
			}
		}
		return selectedContinentTerritories;
	}
}
