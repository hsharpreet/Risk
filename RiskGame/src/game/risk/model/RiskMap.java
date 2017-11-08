package game.risk.util;

import game.risk.model.Territory;
import java.util.HashMap;

/**
 * 
 * @author Team
 *
 */
public class MapDetails {

	private HashMap<String, String> continents;
	private HashMap<String, Territory> territories;

	/**
	 * 
	 * @param continents
	 * @param territories
	 */
	public MapDetails(HashMap<String, String> continents, HashMap<String, Territory> territories) {
		this.continents = continents;
		this.territories = territories;
	}

	/**
	 * 
	 * @return continents
	 */
	public HashMap<String, String> getContinents() {
		return continents;
	}

	/**
	 * 
	 * @return territories
	 */
	public HashMap<String, Territory> getTerritories() {
		return territories;
	}

}
