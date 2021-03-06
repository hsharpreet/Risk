package game.risk.model.entities;

import java.io.Serializable;
import java.util.HashMap;

/**
 * RiskMap class to get map details regarding continent and territory
 * 
 * @author team
 */
public class RiskMap implements Serializable {

	private static final long serialVersionUID = 1L;
	// Declaration
	HashMap<String, String> map;
	HashMap<String, String> continents;
	HashMap<String, Territory> territories;

	/**
	 * A constructor
	 * 
	 * @param map
	 *            a hashMap for MAP
	 * @param continents
	 *            a hashMap for continents
	 * @param territories
	 *            a hashMap for territories
	 */
	public RiskMap(HashMap<String, String> map, HashMap<String, String> continents,
			HashMap<String, Territory> territories) {
		super();
		this.map = map;
		this.continents = continents;
		this.territories = territories;
	}

	/**
	 * A constructor
	 * 
	 * @param continents
	 *            a hashMap of continents
	 * @param territories
	 *            a hashMap of territories
	 */
	public RiskMap(HashMap<String, String> continents, HashMap<String, Territory> territories) {
		super();
		this.continents = continents;
		this.territories = territories;
	}

	/**
	 * Method to get Map and store it into a hashMap
	 * 
	 * @return map a hashMap to store details
	 */
	public HashMap<String, String> getMap() {
		return map;
	}

	/**
	 * Method to get continents and store it into a hashMap
	 * 
	 * @return Continents the name of the continents
	 */
	public HashMap<String, String> getContinents() {
		return continents;
	}

	/**
	 * Method to get territories and store it into a hashMap
	 * 
	 * @return territories the name of the territories
	 */
	public HashMap<String, Territory> getTerritories() {
		return territories;
	}

}
