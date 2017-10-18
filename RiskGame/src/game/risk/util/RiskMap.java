package game.risk.util;

import java.util.HashMap;

import game.risk.model.Territory;
/**
 * A class which stores information about the map, continents and continents
 * @author Team
 *
 */
public class RiskMap {
	//Declaration
	HashMap<String, String> map ;
	HashMap<String, String> continents ;
	HashMap<String, Territory> territories ;
	/**
	 * A constructor
	 * @param map a hashMap for MAP
	 * @param continents a hashMap for continents
	 * @param territories a hashMap for territories
	 */
	public RiskMap(HashMap<String, String> map, HashMap<String, String> continents, HashMap<String, Territory> territories) {
		super();
		this.map = map;
		this.continents = continents;
		this.territories = territories;
	}
	/**
	 * A constructor
	 * @param continents a hashMap of continents
	 * @param territories a hashMap of territories
	 */
	public RiskMap(HashMap<String, String> continents, HashMap<String, Territory> territories) {
		super();
		this.continents = continents;
		this.territories = territories;
	}
	/**
	 * A constructor
	 */
	public RiskMap() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Method to get Map and store it into a hashMap
	 * @return map 
	 */
	public HashMap<String, String> getMap() {
		return map;
	}
	/**
	 * Method to get continents and store it into a hashMap
	 * @return Continents 
	 */
	public HashMap<String, String> getContinents() {
		return continents;
	}
	/**
	 * Method to get territories and store it into a hashMap
	 * @return territories
	 */
	public HashMap<String, Territory> getTerritories() {
		return territories;
	}
	
	
}
