package game.risk.util;

import java.util.HashMap;

import game.risk.model.Territory;

public class ValidateMapReader {

	public boolean isMapValid(HashMap<String, String> continents, HashMap<String, Territory> territories) {
		if (checkCountriesExistInValidContinent(continents, territories) && checkNeighbouringCountriesExist(territories)
				&& checkNeighbouringCountriesSymmetric(territories)) {
			return true;
		}
		return false;

	}

	public boolean checkCountriesExistInValidContinent(HashMap<String, String> continents,
			HashMap<String, Territory> territories) {
		for (Territory t : territories.values()) {
			if (!continents.keySet().contains(t.getContinent())) {
				return false;
			}
		}
		return true;
	}

	public boolean checkNeighbouringCountriesExist(HashMap<String, Territory> territories) {
		for (Territory t : territories.values()) {
			if (t.getNeighbouringTerritories().isEmpty()) {
				return false;
			}
		}
		return true;
	}

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
