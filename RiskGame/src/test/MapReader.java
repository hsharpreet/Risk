package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapReader {

	public static void main(String[] args) throws Exception {
		
		File inputFile = new File("World.map");
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		
		//File outputFile = new File("Files/input/movies01.csv");
		//BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		
		String line = "";
		
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> continents = new HashMap<String, String>();
		ArrayList<Territories> territories = new ArrayList<Territories>();
		
		while((line = br.readLine()) != null){
			if(line.equalsIgnoreCase("[Map]")){
		        while (!(line = br.readLine()).equalsIgnoreCase("") || line.startsWith("[")) {
		            String[] columns = line.split("=");
		            map.put(columns[0], columns[1]);
		        }
		        System.out.println(map);
			}else if(line.equalsIgnoreCase("[Continents]")){
				while (!(line = br.readLine()).equalsIgnoreCase("") || line.startsWith("[")) {
		            String[] columns = line.split("=");
		            continents.put(columns[0], columns[1]);
		        }
			}else if(line.equalsIgnoreCase("[Territories]")){
				while ((line = br.readLine()) != null && line  != "") {
		            String[] columns = line.split(",",4);
		            for(int i=0; i< columns.length && columns.length>3;i++){
		            	Territories t = new Territories();
		            	t.setTerritoryName(columns[0]);
		            	System.out.println("NAME: "+columns[0]);
		            	t.setTerritoryCoordinateX(columns[1]);
		            	t.setTerritoryCoordinateY(columns[2]);
		            	
		            	List<String> tempNeighboursList = new ArrayList<String>();
		            	tempNeighboursList.add(columns[3]);
		            	t.setTerritoryNeighbours(tempNeighboursList);
		            	territories.add(t);
		            	break;
		            }
		        }
				break;
			}
		}
		/*System.out.println(map);
		System.out.println(continents);
		for(Territories t : territories){
			System.out.print(t.getTerritoryName()+" ");
		}*/
		br.close();
		//bw.close();
	}

}
