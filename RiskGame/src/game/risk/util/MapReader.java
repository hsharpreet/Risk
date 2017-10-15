package game.risk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import game.risk.model.Territory;

public class MapReader {
	
	public static void main(String[] args) throws Exception {
		System.out.println("MapReader main method...");
		RiskMap riskMap = new MapReader().readMap("World.map");
		System.out.println(riskMap.getMap());
		//System.out.println(riskMap.getTerritories().keySet().size());
		//new MapReader().getContinentOfACountry("", "World.map");
	}

	public static Map<String, ArrayList<String>> continetAndItsCountries(String path){
		MapReader mapReader = new MapReader();
		MapWriter mapWriter = new MapWriter(path);
		RiskMap riskmap;
		Map<String, ArrayList<String>> continetAndItsCountries = new HashMap<String, ArrayList<String>>();
		try {
			riskmap = mapReader.readMap(path);
			Object[] continents = riskmap.getContinents().keySet().toArray();
			
			for(int i = 0 ; i <continents.length ; i++){
				continetAndItsCountries.put(continents[i].toString(), mapReader.getCountriesOfContinent(continents[i].toString(), path));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return continetAndItsCountries;
	}
	
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
							if (columns[3].equals(selectedContinent)){
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
	
	public String getContinentOfACountry(String country, String path) {
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		map = continetAndItsCountries(path);
		String continent = "";
		
		for(Entry<String, ArrayList<String>> e: map.entrySet()){
		    if(e.getValue().contains(country)){
		    	continent = e.getKey();
		    }
		}
		
		return continent;
	}
	
	public RiskMap readMap(String MapFile) throws Exception {

		if(MapFile.equalsIgnoreCase("") || MapFile.equals(null)){
			MapFile = "World.map";
		}
		File inputFile = new File(MapFile);
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
	
	public ArrayList getLinksOfCountry(String countryName, File path){
		ArrayList<String> links = new ArrayList<>();
		try{	
			String countrySelected = countryName;
			File inputFile = new File(path.getName());
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String thisLine = "";
			while ((thisLine = br.readLine()) != null) {
				if (thisLine.equalsIgnoreCase("[Territories]")) {
					
					while ((thisLine = br.readLine()) != null && thisLine != " ") {
						String[] columns = thisLine.split(",");
						if (columns[0].equals(countryName)){
							for(int i = 4; i<columns.length ; i++)
							{
								links.add(columns[i]);
							}
						}
						
						// printWriter.println(newTerritory);
					}
					//return links;
				}
			}
			br.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return links;
	}

}
