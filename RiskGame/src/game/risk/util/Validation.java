package game.risk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import game.risk.model.Territory;

/**
 * Class to validate MapWriter
 * 
 * @author Team
 *
 */
public class Validation

{
	/**
	 * Method to validate AddLink method and check if the neighbor can be added
	 * 
	 * @param countryName
	 *            the name of the country
	 * @param linkName
	 *            the name of the link
	 * @param thisLine
	 * @return true if the neighbor can be added
	 * @return false if the neighbor cannot be added
	 * @throws Exception
	 */
	public boolean validateAddLink(String countryName, String linkName, String thisLine) throws Exception {
		String country = countryName;
		String link = linkName;
		String line = thisLine;
		String[] columns = thisLine.split(",");
		if (columns[0].equalsIgnoreCase(country)) {
			for (int j = 4; j < columns.length; j++) {
				if (columns[j].equals(link)) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Method to validate that a country cannot select itself to add as neighbor
	 * 
	 * @param countryName
	 *            the name of the country
	 * @param linkName
	 *            the name of the link
	 * @param thisLine
	 * @return false if country can add itself as link
	 * @return true if country cannot add itself as link
	 * @throws Exception
	 */
	public boolean validateAddLinkAddingItself(String countryName, String linkName, String thisLine) throws Exception {
		if (countryName.equals(linkName)) {
			return false;
		}
		return true;

	}

	/**
	 * Method to validate whether territory could be deleted if neighboring
	 * countries are more than 1
	 * 
	 * @param link
	 *            the link to be deleted
	 * @param mapFileName
	 *            the name of the map file
	 * @return true if the link delete
	 * @return false if the link do not delete
	 * @throws Exception
	 */
	public boolean validateLinkToDelete(String link, String mapFileName) throws Exception {
		String linked_country = link;
		String mapFile = mapFileName;
		String line = "";
		File inputFile = new File(mapFile);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		while ((line = br.readLine()) != null) {
			if (line.equalsIgnoreCase("[Territories]")) {
				while ((line = br.readLine()) != null) {
					String[] column_adjacentTerritory = line.split(",");
					// if neighbouring countries are more than 1
					if (linked_country.equals(column_adjacentTerritory[0]) && column_adjacentTerritory.length > 5) {

						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Method to check delete continent option to check that countries to be deleted
	 * after deleting continent will not leave map as invalid
	 * 
	 * @param countriesListData
	 *            the list of countries
	 * @param mapFileName
	 *            the file name
	 * @return status the status of the process
	 * @throws Exception
	 */
	public boolean checkTerritoriesBeforeDeletingContinent(String continent, String mapFile) throws Exception {
		MapReader mapReader = new MapReader();
		RiskMap riskMap = mapReader.readMap(mapFile);
		Map<String, Territory> territoryListForContinent = mapReader.getTerritoriesOfContinent(continent, mapFile);
		for (Territory territory : territoryListForContinent.values()) {
			for (String neighbour : territory.getNeighbouringTerritories()) {
				Territory neighbourTerritory = mapReader.getTerritoryByName(neighbour, riskMap.getTerritories());
				neighbourTerritory.getNeighbouringTerritories().removeAll(territoryListForContinent.keySet());
				if (!neighbourTerritory.getContinent().equalsIgnoreCase(continent)
						&& neighbourTerritory.getNeighbouringTerritories().size() == 0) {
					return false;
				}
			}

		}
		return true;
	}

	/**
	 * Method to check before deleting a territory that all its adjacent territories
	 * does not have just 1 adjacent territory i.e the one we wish to delete
	 * 
	 * @param thisLine
	 * @param mapFileName
	 * @return false if adjacent territories have more than 1 adjacent territory
	 * @return true if adjacent territories have just 1 adjacent territory
	 * @throws Exception
	 */
	public boolean checkAdjacentTerritoryLinkBeforeDelete(String territoryToBeDeleted, String mapFileName)
			throws Exception {
		MapReader mapReader = new MapReader();
		RiskMap riskMap = mapReader.readMap(mapFileName);
		Territory territory = mapReader.getTerritoryByName(territoryToBeDeleted, riskMap.getTerritories());
		for (String neighbour : territory.getNeighbouringTerritories()) {
			Territory neighbourTerritory = mapReader.getTerritoryByName(neighbour, riskMap.getTerritories());
			neighbourTerritory.getNeighbouringTerritories().remove(territoryToBeDeleted);
			if (neighbourTerritory.getNeighbouringTerritories().size() == 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkTerritoryLinkBeforeDeleteLink(String territory,String link, String mapFileName)
			throws Exception {
		MapReader mapReader = new MapReader();
		RiskMap riskMap = mapReader.readMap(mapFileName);
		Territory t = mapReader.getTerritoryByName(territory, riskMap.getTerritories());
		Territory neighbouring= mapReader.getTerritoryByName(link, riskMap.getTerritories());
		if(t.getNeighbouringTerritories().size()==1){
			return false;
		}
		if(neighbouring.getNeighbouringTerritories().size()==1){
			return false;
		}
		return true;
	}
	

}
