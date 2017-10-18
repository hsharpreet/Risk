package game.risk.util;

import java.util.HashMap;

import game.risk.model.Territory;
/**
 * Class to validate map reader 
 * @author Team
 *
 */
public class ValidateMapReader {
/**
 *
 * Method to check if the map is valid
 * @param continents a hashMap of continents
 * @param territories a hashMap of territory
 * @return true if the map is valid
 * @return false if the map is not valid
 * 
 */ 
	public boolean isMapValid(HashMap<String, String> continents, HashMap<String, Territory> territories) {
		if (checkCountriesExistInValidContinent(continents, territories) && checkNeighbouringCountriesExist(territories)
				&& checkNeighbouringCountriesSymmetric(territories)) {
			return true;
		}
		return false;

	}
/**
 * Method to check that countries exist in valid continents
 * @param continents a hashMap of the continents
 * @param territories a hashMap of the territory
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
 * Method to check if the neighboring countries exist
 * @param territories a hashMap of territories 
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
 * Method to check if neighboring countries are symmetric
 * @param territories a hashMap of territory
 * @return false if neighboring countries are not symmetric
 * @return true if neighboring countries are symmetric
 */
	public boolean checkNeighbouringCountriesSymmetric(HashMap<String, Territory> territories) {
		for (Territory t : territories.values()) {
			if (!t.getNeighbouringTerritories().isEmpty()) {
				for (String neighbouringTerritoryName : t.getNeighbouringTerritories()) {
					if(!checkCountryExistInNeighbouringCountry(t.getName(), neighbouringTerritoryName, territories)) {
						return false; 
					}
				}
			}
		}
		return true;
	}
/**
 * Method to check if country exist in neighboring country
 * @param mainCountry the main country whose neighbors have to be checked
 * @param neighbouringTerritoryName the names of the neighboring countries
 * @param territories a hashMap of territories
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

}
