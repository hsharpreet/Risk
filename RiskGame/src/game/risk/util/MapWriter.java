package game.risk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Class to write in the World.map file
 * @author Team
 *
 */
public class MapWriter {

	String mapFileName = "World.map";//the map file

	public MapWriter(String mapFileName) {
		this.mapFileName = mapFileName;
	}
/**
 * The main method
 * @param args
 * @throws IOException
 */
	public static void main(String[] args) throws IOException {
		MapWriter writer = new MapWriter("World.map");
	}
/**
 * Method to add continent in the file with its name and the number of armies
 * @param name the name of the continent
 * @param value the value of the armies which a player receives during reenforcement if he owns all the countries of the continent
 * @throws IOException
 */
	public void addContinent(String name, String value) throws IOException {
		String newContinent = name + "=" + value;
		File inputFile = new File(mapFileName);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		File outFile = new File("temp.map");// the file path
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) {//writing the continents
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Continents]")) {
				printWriter.println(newContinent);
			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);
	}


/**
 * Method to delete continent by writing it into temp.map from world.map
 * @param name the name of the continents
 * @throws IOException
 */
	public void deleteContinent(String name) throws IOException {
		File inputFile = new File(mapFileName);// file path to read from
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		File outFile = new File("temp.map");//file path to write
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) {
			if (!thisLine.toLowerCase().contains(name.toLowerCase())) {
				printWriter.println(thisLine);
			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);
	}
/**
 * Method to add territory
 * @param name the name of the territory
 * @throws IOException
 */
	public void addTerritory(String name) throws IOException {
		String newTerritory = name;
		File inputFile = new File(mapFileName);// file path to read from
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		File outFile = new File("temp.map");//file path to write
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null)
		{
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Territories]"))
			{
				printWriter.println(newTerritory);
				System.out.println("hiiii testing me");
			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);
	}
  /**
   * Method to add new country neighbors to territories
   * @param newCountryEntry the entry of new country 
   * @param adjacentCountriesToNewCountry the adjacent countries of the new country
   * @throws Exception 
   */
	public void addNewCountryLinkToTerritories(String newCountryEntry,
			LinkedHashSet<String> adjacentCountriesToNewCountry) throws Exception{
		String newTerritory = newCountryEntry;
		File inputFile = new File(mapFileName);// file path to read from
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		File outFile = new File("temp.map");//file path to write
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null){
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Territories]"))
			{
				while ((thisLine = br.readLine()) != null && thisLine != "")
				{
					for (String element : adjacentCountriesToNewCountry)
					{
						String[] columns = thisLine.split(",");
						if (columns[0].equals(element))
						{
							thisLine = thisLine + "," + newCountryEntry;
						}
					}
					printWriter.println(thisLine);
				}
			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);
	}
/**
 * Method to delete territory from world.map
 * @param territoryToDelete the territory to be deleted
 * @return status status of the process
 * @throws Exception
 */
	public String deleteTerritory(String territoryToDelete) throws Exception {
		Validation validate= new Validation();
		String status = "OK";
		if(!validate.checkAdjacentTerritoryLinkBeforeDelete(territoryToDelete, mapFileName)){
			return "ERROR";
		}
		File inputFile = new File(mapFileName);// file path to read from
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		ArrayList<String> territoryList = new ArrayList<>();
		File outFile = new File("temp.map");//file path to write
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) {
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Territories]")) {//reading the file to delete the territory
				while ((thisLine = br.readLine()) != null && thisLine != "") {
					String[] columns = thisLine.split(",");
					if (columns[0].equals(territoryToDelete)) {
						for (int i = 4; i < columns.length; i++) {
							territoryList.add(columns[i]);
							System.out.println("list --" + columns[i]);
						}
						continue;
					}
					printWriter.println(thisLine);
				}
			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);
		if (status.equals("OK")) {
			deleteLinksOfDeletedTerritoryFromOthers(territoryList, territoryToDelete);
		}
		return status;

	}
/**
 * Method to delete neighbors of the deleted territory from the file 
 * @param territoryList the list of the territories
 * @param territoryToDelete the territory to be deleted
 * @throws Exception
 */
	private void deleteLinksOfDeletedTerritoryFromOthers(ArrayList<String> territoryList, String territoryToDelete)
			throws Exception {
		ArrayList<String> listOfTerritories = territoryList;
		String territory = territoryToDelete;
		for (int i = 0; i < listOfTerritories.size(); i++) {
			File inputFile = new File(mapFileName);//file path to read 
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			File outFile = new File("temp.map");//file path to write
			FileOutputStream outStream = new FileOutputStream(outFile);
			PrintWriter printWriter = new PrintWriter(outStream);
			String thisLine = "";
			while ((thisLine = br.readLine()) != null) {
				printWriter.println(thisLine);
				if (thisLine.equalsIgnoreCase("[Territories]")) {//reading the file to delete the link
					String modifiedLink = "";
					while ((thisLine = br.readLine()) != null && thisLine != "") {
						String[] columns = thisLine.split(",");
						if (columns[0].equalsIgnoreCase(listOfTerritories.get(i))) {
							for (int j = 0; j < columns.length; j++) {
								if (j < 4) {
									modifiedLink = modifiedLink.concat(columns[j] + ",");
								}
								if (j >= 4) {
									if (columns[j].equalsIgnoreCase(territory)) {

									} else {
										modifiedLink = modifiedLink.concat(columns[j] + ",");
									}
								}
							}
							printWriter.println(modifiedLink.substring(0, modifiedLink.length() - 1));
						} else {
							printWriter.println(thisLine);
						}
					}
				}
			}
			printWriter.flush();
			printWriter.close();
			br.close();
			inputFile.delete();
			outFile.renameTo(inputFile);
		}
	}

/**
 * Method to assign new continent to the territory
 * @param selectedItem the selected continent
 * @param territory the territory to which the continent has to be assigned
 */
	public void assignNewContinent(String selectedItem, String territory) {
		try {
			String newContinent = selectedItem;
			String territorySelected = territory;
			File inputFile = new File(mapFileName);
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			File outFile = new File("temp.map");
			FileOutputStream outStream = new FileOutputStream(outFile);
			PrintWriter printWriter = new PrintWriter(outStream);
			String thisLine = "";
			while ((thisLine = br.readLine()) != null) {
				printWriter.println(thisLine);
				if (thisLine.equalsIgnoreCase("[Territories]")) {
					String newLine = "";
					while ((thisLine = br.readLine()) != null && thisLine != "") {
						String[] columns = thisLine.split(",");
						if (columns.length >= 5) {
							if (columns[0].equals(territorySelected)) {
								columns[3] = newContinent;
								for (int i = 0; i < columns.length; i++) {
									newLine = newLine.concat(columns[i] + ",");
								}
								newLine = newLine.substring(0, newLine.length() - 1);
								printWriter.println(newLine);
								System.out.println(newContinent + "------" + newLine);
							} else {
								printWriter.println(thisLine);
							}
						} else {
							printWriter.println(thisLine);
						}
					}
				}
			}
			printWriter.flush();
			printWriter.close();
			br.close();
			inputFile.delete();
			outFile.renameTo(inputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Method to delete the neighbor 
	 * @param link the link to be deleted
	 * @param country the country whose link has to be deleted
	 * @return status
	 */
	  
	 
	public String deleteLink(String link , String country)
	{
		Validation validate = new Validation();// object of validation class
		String status = "OK";
		try
		{
			ArrayList<String> al = new ArrayList<>();
			al.add(link);
			
		String countrySelected = country;
		File inputFile = new File(mapFileName);// file path to read
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		File outFile = new File("temp.map");// file path to write
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);

		String thisLine = "";
		while ((thisLine = br.readLine()) != null) 
		{
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Territories]")) {
				
				while ((thisLine = br.readLine()) != null && thisLine != " ") {
					String[] columns = thisLine.split(",");
					String newLine = "";
					int a = 1;
					if (columns[0].equals(country)) 
					
					{
						a=0 ;
						if(columns.length<6)
						{
							status = "ERROR";
							return status;
						}
						for(int i = 4; i<columns.length ; i++)
						{
						if(validate.validateLinkToDelete(link , mapFileName) == true)
						{
							//System.out.println("hiii");
						}
						else
						{
							status = "ERROR";
						}
						}
						
						
					}
					
					 printWriter.println(thisLine);
					
				}
				//printWriter.println(thisLine);
			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);
		
		if (status.equals("OK")) {
			deleteLinksOfmodifiedTerritory(al, country);
		}
		return status;
	} catch (Exception e) {
		e.printStackTrace();
	}
		return status;
	
	}
/**
 * Method to delete neighbors of modified territory
 * @param link an array list of links
 * @param country the country whose link will be deleted
 * @throws Exception
 */
	private void deleteLinksOfmodifiedTerritory(ArrayList<String> link, String country)throws Exception
	
	{
		ArrayList<String> link_name = link;
		String country_name = country;
		
			File inputFile = new File(mapFileName);//file path to read from
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			File outFile = new File("temp.map");//file path to write to
			FileOutputStream outStream = new FileOutputStream(outFile);
			PrintWriter printWriter = new PrintWriter(outStream);
			String thisLine = "";
			while ((thisLine = br.readLine()) != null) {
				printWriter.println(thisLine);
				if (thisLine.equalsIgnoreCase("[Territories]")) 
				{
					String modifiedLink = "";
					while ((thisLine = br.readLine()) != null && thisLine != "") {
						String[] columns = thisLine.split(",");//splitting the element of the array
						
						if (columns[0].equalsIgnoreCase(link_name.get(0))) {
							for (int j = 0; j < columns.length; j++) {
								if (j < 4) {
									modifiedLink = modifiedLink.concat(columns[j] + ",");
								}
								if (j >= 4) {
									if (columns[j].equalsIgnoreCase(country_name)) {

									} else {
										modifiedLink = modifiedLink.concat(columns[j] + ",");
									}
								}
							}
							printWriter.println(modifiedLink.substring(0, modifiedLink.length() - 1));
						} 
						
						else if (columns[0].equalsIgnoreCase(country_name)) {
							for (int j = 0; j < columns.length; j++) {
								if (j < 4) {
									modifiedLink = modifiedLink.concat(columns[j] + ",");
								}
								if (j >= 4) {
									if (columns[j].equalsIgnoreCase(link_name.get(0))) {

									} else {
										modifiedLink = modifiedLink.concat(columns[j] + ",");
									}
								}
							}
							printWriter.println(modifiedLink.substring(0, modifiedLink.length() - 1));
						} 
						
						
						
						else {
							printWriter.println(thisLine);
						}
						modifiedLink = "";
					}
				}
			}
			printWriter.flush();
			printWriter.close();
			br.close();
			inputFile.delete();
			outFile.renameTo(inputFile);
	
	}
/**
 * Method to add neighbors if the neighbor does not exist already
 * @param selectedItem the selected link 
 * @param text the country to which the link will be added
 * @return the status of the process
 * @throws Exception
 */
	public String addLink(String selectedItem, String text)throws Exception
	
	{
		Validation validate = new Validation();
		// TODO Auto-generated method stub
		String link = selectedItem;
		String country = text;
		File inputFile = new File(mapFileName);//file to read from
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		File outFile = new File("temp.map");//file to write to
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) {
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Territories]")) 
			{
				String modifiedLink = "";
				while ((thisLine = br.readLine()) != null && thisLine != "") {
					String[] columns = thisLine.split(",");
					if (columns[0].equalsIgnoreCase(country)) {
						if(validate.validateAddLink(country , link  ,thisLine)==false )
						{
						 return "ERROR_LinkAlreadyExists";
							}
						else if(validate.validateAddLinkAddingItself(country, link, thisLine)==false)
						{
							return "ERROR_AddingItselfInLink";
						}
					else
					{
					     	modifiedLink = modifiedLink.concat(thisLine + ","+link);
						 printWriter.println(modifiedLink);
						
					}
					}
					else
					{
						 printWriter.println(thisLine);
					}
						}
						
					} 
				}
		
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);
			
	return "OK";	
}
/**
 * Method to delete territories of the deleted continent
 * @param terittoryToDelete the territory to be deleted
 * @throws Exception
 */
	public void deleteTerritoriesOfContinentDeleted(String terittoryToDelete) throws Exception {
		Validation validate= new Validation();
		String status = "OK";
		String territoryToDelete = terittoryToDelete;
		File inputFile = new File(mapFileName);// file path to read from
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		ArrayList<String> territoryList = new ArrayList<>();
		File outFile = new File("temp.map");// file path to write to
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) {
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Territories]")) {
				while ((thisLine = br.readLine()) != null && thisLine != "") {
					String[] columns = thisLine.split(",");//splitting the element
					if (columns[0].equals(territoryToDelete)) {//deleting the territory
						
							for (int i = 4; i < columns.length; i++) {
								territoryList.add(columns[i]);
							}
							continue;
					}
					printWriter.println(thisLine);
				}
			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);
		
		deleteLinksOfDeletedTerritoryFromOthers(territoryList, territoryToDelete);
		
		

	}

	}
