package game.risk.model.validation;

import java.util.HashMap;
import java.util.List;

import game.risk.model.MapReader;
import game.risk.model.entities.Territory;

/**
 * Class to validate map reader
 * 
 * @author Team
 * @see MapReader
 *
 */
public class ValidateMapReader {
	/**
	 *
	 * Method to check if the map is valid
	 * 
	 * @param continents
	 *            a hashMap of continents
	 * @param territories
	 *            a hashMap of territory
	 * @return true if the map is valid
	 * @return false if the map is not valid
	 * 
	 */
	public boolean isMapValid(HashMap<String, String> continents, HashMap<String, Territory> territories) {
		if (continents.size() == 1 && territories.size() == 1) {
			return checkSingleContinentValid(continents, territories);
		} else if (checkCountriesExistInValidContinent(continents, territories)
				&& checkNeighbouringCountriesExist(territories) && checkNeighbouringCountriesSymmetric(territories)
				&& checkConnectedContinent(continents, territories)) {
			return true;
		}
		return false;
	}

	/**
	 * Method to check if the single continent has single territory which has that
	 * single continent
	 * 
	 * @param continents
	 *            HashMap Containing continent
	 * @param territories
	 *            HashMap Containing Territories
	 * @return true if valid single continent
	 * @return false if invalid single continent
	 */
	public boolean checkSingleContinentValid(HashMap<String, String> continents,
			HashMap<String, Territory> territories) {
		String singleContinent = (String) continents.keySet().toArray()[0];
		Territory singleTerritory = (Territory) territories.values().toArray()[0];
		if (singleTerritory.getContinent().equalsIgnoreCase(singleContinent)) {
			return true;
		}
		return false;
	}

	/**
	 * Method to check that countries exist in valid continents
	 * 
	 * @param continents
	 *            a hashMap of the continents
	 * @param territories
	 *            a hashMap of the territory
	 * @return false if countries do not exist in valid continent
	 * @return true if countries exist in valid continent
	 */
	public boolean checkCountriesExistInValidContinent(HashMap<String, String> continents,
			HashMap<String, Territory> territories) {
		for (Territory t : territories.values()) {
			if (!continents.keySet().contains(t.getContinent())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to check if the neighboring countries exist for a country
	 * 
	 * @param territories
	 *            a hashMap of territories
	 * @return false if neighboring countries do not exist
	 * @return true if neighboring countries exist
	 */
	public boolean checkNeighbouringCountriesExist(HashMap<String, Territory> territories) {
		for (Territory t : territories.values()) {
			if (t.getNeighbouringTerritories().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to check if neighboring countries are symmetric i.e. if A is neighbor
	 * of a B then B is a neighbor of A.
	 * 
	 * @param territories
	 *            a hashMap of territory
	 * @return false if neighboring countries are not symmetric
	 * @return true if neighboring countries are symmetric
	 */
	public boolean checkNeighbouringCountriesSymmetric(HashMap<String, Territory> territories) {
		for (Territory t : territories.values()) {
			if (!t.getNeighbouringTerritories().isEmpty()) {
				for (String neighbouringTerritoryName : t.getNeighbouringTerritories()) {
					if (!checkCountryExistInNeighbouringCountry(t.getName(), neighbouringTerritoryName, territories)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Method to check if country exists as neighboring country for the country
	 * 
	 * @param mainCountry
	 *            the main country whose neighbors have to be checked
	 * @param neighbouringTerritoryName
	 *            the names of the neighboring countries
	 * @param territories
	 *            a hashMap of territories
	 * @return false if country does not exist in neighboring countries
	 * @return true if country exist in neighboring countries
	 */
	private boolean checkCountryExistInNeighbouringCountry(String mainCountry, String neighbouringTerritoryName,
			HashMap<String, Territory> territories) {
		for (Territory t : territories.values()) {
			if (t.getName().equalsIgnoreCase(neighbouringTerritoryName)
					&& !t.getNeighbouringTerritories().contains(mainCountry)) {
				return false;
			}
		}
		return true;
	}

	private boolean checkConnectedContinent(HashMap<String, String> continents,
			HashMap<String, Territory> territories) {
		for (String continent : continents.keySet()) {
			MapReader reader = new MapReader();
			List<Territory> territoriesOfContinent = reader.getTerritoriesOfContinent(continent, territories);
			for (Territory t : territoriesOfContinent) {
				int count = 0; // neighbours in same continent
				List<String> neighbouringTerritories = t.getNeighbouringTerritories();
				for (String neighbour : neighbouringTerritories) {
					Territory neighbourTerritory = reader.getTerritoryByName(neighbour, territories);
					if (territoriesOfContinent.contains(neighbourTerritory)) {
						count++;
					}
				}
				if (count == 0 && territoriesOfContinent.size() > 1) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isValidMapFileExtention(String mapfileName) {
		if (mapfileName == null || !mapfileName.endsWith(".map")) {
			return false;
		}
		return true;
	}
}