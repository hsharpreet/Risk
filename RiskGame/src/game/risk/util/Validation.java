package game.risk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * Class to validate MapWriter
 * @author Team
 *
 */
public class Validation

{
	/**
	 * Method to validate add link method
	 * @param countryName the name of the country
	 * @param linkName the name of the link
	 * @param thisLine 
	 * @return true if the link can be added
	 * @return false if the link cannot be added
	 * @throws Exception
	 */
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
/**
 * Method to validate that a country cannot select itself to add as link
 * @param countryName the name of the country
 * @param linkName the name of the link
 * @param thisLine
 * @return false if country can add itself as link
 * @return true if country cannot add itself as link
 * @throws Exception
 */
	public boolean validateAddLinkAddingItself(String countryName, String linkName, String thisLine) throws Exception {
		if (countryName.equals(linkName)) {
			return false;
		}
		return true;

	}
/**
 * Method to validate that the links delete which are selected to delete
 * @param link the link to be deleted
 * @param mapFileName the name of the map file
 * @return true if the link delete
 * @return false if the link do not delete
 * @throws Exception
 */
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
	
	
	
	/**
	 * Method to check delete continent option to check that countries to be deleted after deleting continent will not leave map as invalid
	 * @param countriesListData the list of countries
	 * @param mapFileName the file name
	 * @return status the status of the process
	 * @throws Exception
	 */
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
	
	
	/** Method to check before deleting a territory that all its adjacent territories does not have just 1 adjacent territory i.e the one we wish to delete
	 * @param thisLine 
	 * @param mapFileName
	 * @return false if adjacent territories have more than 1 adjacent territory
	 * @return true if adjacent territories have just 1 adjacent territory
	 * @throws Exception
	 */
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
				//this check is to allow to delete territory even if length is 5 because the only adjacent country of this country is also to be deleted since they are of same continent
									if(!(columns[3].equals(column_adjacentTerritory[3])))
									return false;
								}
							}
						}
					}
				}
				return true;
			}
		

}
