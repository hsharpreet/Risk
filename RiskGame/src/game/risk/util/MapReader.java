package game.risk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapReader {
	
	public static void main(String[] args) throws Exception {
		System.out.println("MapReader main method...");
		RiskMap riskMap = new MapReader().readMap();
		System.out.println(riskMap.getMap());
		System.out.println(riskMap.getTerritories().keySet().size());
	}

	public RiskMap readMap() throws Exception {

		File inputFile = new File("World.map");
		BufferedReader br = new BufferedReader(new FileReader(inputFile));

		String line = "";

		HashMap<String, String> mapDetails = new HashMap<String, String>();
		HashMap<String, String> continents = new HashMap<String, String>();
		HashMap<String, Territory> territories = new HashMap<String, Territory>();
		

		while ((line = br.readLine()) != null) {
			if (line.equalsIgnoreCase("[Map]")) {
				while (!(line = br.readLine()).equalsIgnoreCase("") || line.startsWith("[")) {
					String[] columns = line.split("=");
					mapDetails.put(columns[0], columns[1]);
				}
				System.out.println("map : " + mapDetails);
			} else if (line.equalsIgnoreCase("[Continents]")) {
				while (!(line = br.readLine()).equalsIgnoreCase("") || line.startsWith("[")) {
					String[] columns = line.split("=");
					continents.put(columns[0], columns[1]);
				}
			} else if (line.equalsIgnoreCase("[Territories]")) {
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
						System.out.println(territoryObj);
					}
				}

			}
		}
		br.close();
		RiskMap riskMap = new RiskMap(mapDetails, continents, territories);
		return riskMap;
	}

}
