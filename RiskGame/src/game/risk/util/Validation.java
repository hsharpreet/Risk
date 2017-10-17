package game.risk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Validation

{
	public boolean validateAddLink(String countryName, String linkName, String thisLine) throws Exception {
		String country = countryName;
		String link = linkName;
		String line = thisLine;
		String[] columns = thisLine.split(",");
		if (columns[0].equalsIgnoreCase(country)) {
			for (int j = 4; j < columns.length; j++) {
				if (columns[j].equals(link)) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean validateAddLinkAddingItself(String countryName, String linkName, String thisLine) throws Exception {
		if (countryName.equals(linkName)) {
			return false;
		}
		return true;

	}

	public boolean validateLinkToDelete(String link, String mapFileName) throws Exception {
		String linked_country = link;
		String mapFile = mapFileName;
		String line = "";
		File inputFile = new File(mapFile);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		while ((line = br.readLine()) != null) {
			if (line.equalsIgnoreCase("[Territories]")) {
				while ((line = br.readLine()) != null) {
					String[] column_adjacentTerritory = line.split(",");
					if (linked_country.equals(column_adjacentTerritory[0]) && column_adjacentTerritory.length > 5) {
						
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//this function is called on delete continent option to check that countries to be deleted after deleting continent will not leave map as invalid
	public String  checkTerritoriesBeforeDeletingContinent(ArrayList<String> countriesListData , String mapFileName) throws Exception
	{
		Validation validate= new Validation();
		String status = "OK";
		String thisLine = "";
		for(int j= 0 ; j<countriesListData.size() ; j++)
		{
			File inputFile = new File(mapFileName);
			BufferedReader br = new BufferedReader(new FileReader(inputFile));	
	      
			while ((thisLine = br.readLine()) != null) {
			
			if (thisLine.equalsIgnoreCase("[Territories]")) {
				while ((thisLine = br.readLine()) != null && thisLine != "") {
					String[] columns = thisLine.split(",");
					if (columns[0].equals(countriesListData.get(j))) {
						if (validate.checkAdjacentTerritoryLinkBeforeDelete(thisLine , mapFileName)) {
							continue;
						} else {
							status = "ERROR";
						}
					}
					
				}
			}
		}
			br.close();
		}
		
		return status;

		
	}
	
	
	//This method checks before deleting a territory that all its adjacent territories does not have just 1 adjacent territory i.e the one we wish to delete
	public boolean checkAdjacentTerritoryLinkBeforeDelete(String thisLine ,  String mapFileName) throws Exception {
		String line = thisLine;
		String[] columns = line.split(",");
		for (int i = 4; i < columns.length; i++) {
			File inputFile = new File(mapFileName);
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			while ((line = br.readLine()) != null) {
				if (line.equalsIgnoreCase("[Territories]")) {
					while ((line = br.readLine()) != null) {
						String[] column_adjacentTerritory = line.split(",");
						if (columns[i].equals(column_adjacentTerritory[0]) && column_adjacentTerritory.length == 5) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

}
