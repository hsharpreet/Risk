package game.risk.util;

import java.util.ArrayList;
import java.util.HashMap;

public class RiskMap {
	
	HashMap<String, String> map ;
	HashMap<String, String> continents ;
	ArrayList<Territory> territories ;
	
	public RiskMap(HashMap<String, String> map, HashMap<String, String> continents, ArrayList<Territory> territories) {
		super();
		this.map = map;
		this.continents = continents;
		this.territories = territories;
	}
	public HashMap<String, String> getMap() {
		return map;
	}
	public HashMap<String, String> getContinents() {
		return continents;
	}
	public ArrayList<Territory> getTerritories() {
		return territories;
	}
	
	
}
