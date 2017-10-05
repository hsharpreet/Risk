package game.risk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import javax.swing.text.AbstractDocument.BranchElement;

public class MapWriter {

	public static void main(String[] args) throws IOException {
		MapWriter writer = new MapWriter();
		/*writer.addContinent("aman", "7");
		System.out.println("updated new file");*/
		/*writer.deleteContinent("gurpreet");
		System.out.println("updated new file");*/
	}

	public void addContinent(String name, String value) throws IOException {

		String newContinent = name + "=" + value;
		File inputFile = new File("World.map");
		BufferedReader br = new BufferedReader(new FileReader(inputFile));

		File outFile = new File("temp.map");
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) 
		
		{
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Continents]")) 
			
			{
				printWriter.println(newContinent);

			}
			
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);

	}
	
	/*
	 * Method to delete continent from the map
	 */
	public void deleteContinent(String name) throws IOException {
		File inputFile = new File("World.map");
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		File outFile = new File("temp.map");
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		
		while ((thisLine = br.readLine()) != null) 
		{
			if (!thisLine.toLowerCase().contains(name.toLowerCase())) 
			{
				printWriter.println(thisLine);

			}
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);
	}
	
	public void addTerritory(String name) throws IOException {

		String newTerritory = name;
		File inputFile = new File("World.map");
		BufferedReader br = new BufferedReader(new FileReader(inputFile));

		File outFile = new File("temp.map");
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) 
		
		{
			printWriter.println(thisLine);
			if (thisLine.equalsIgnoreCase("[Territories]")) 
			
			{
				printWriter.println(newTerritory);
			}
			
		}
		printWriter.flush();
		printWriter.close();
		br.close();
		inputFile.delete();
		outFile.renameTo(inputFile);

	}

	public void addNewCountryLinkToTerritories(String newCountryEntry,LinkedHashSet<String> adjacentCountriesToNewCountry) throws Exception
	
	{
		
		
		  
		
		String newTerritory = newCountryEntry;
		File inputFile = new File("World.map");
		BufferedReader br = new BufferedReader(new FileReader(inputFile));

		File outFile = new File("temp.map");
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null && thisLine != "")
			
		
		{
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
						printWriter.println(thisLine+","+newCountryEntry);
					}
					else
					{
						printWriter.println(thisLine);

					}
				//printWriter.println(newTerritory);
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
	
public String deleteTerritory(String terittoryToDelete) throws Exception
	
	{
	 String status = "OK";
		String territoryToDelete = terittoryToDelete;
		File inputFile = new File("World.map");
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		ArrayList<String> territoryList = new ArrayList<>();
		File outFile = new File("temp.map");
		FileOutputStream outStream = new FileOutputStream(outFile);
		PrintWriter printWriter = new PrintWriter(outStream);
		String thisLine = "";
		while ((thisLine = br.readLine()) != null) 
		
		{
			printWriter.println(thisLine);
			
			if (thisLine.equalsIgnoreCase("[Territories]")) 
			
			{
				while ((thisLine = br.readLine()) != null && thisLine != "")
			
				{
				String[] columns = thisLine.split(",");
				
				if(columns[0].equals(territoryToDelete))
				{
					
					if(checkAdjacentTerritoryLinkBeforeDelete(thisLine))
					{
						for(int i=4 ; i<columns.length ; i++)
						{
							territoryList.add(columns[i]);	
							System.out.println("list --"+columns[i]);
						}
					continue;
					}
					else
					{
				   status="ERROR";
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
		if(status.equals("OK"))
		{
			deleteLinksOfDeletedTerritoryFromOthers(territoryList , territoryToDelete);
		}
		return status;

	}

private void deleteLinksOfDeletedTerritoryFromOthers(ArrayList<String> territoryList , String territoryToDelete)throws Exception

{
	ArrayList<String>listOfTerritories = territoryList;
	String territory = territoryToDelete;
	
	for(int i=0 ; i< listOfTerritories.size() ; i++)
		
	{
		
	File inputFile = new File("World.map");
	BufferedReader br = new BufferedReader(new FileReader(inputFile));
    File outFile = new File("temp.map");
	FileOutputStream outStream = new FileOutputStream(outFile);
	PrintWriter printWriter = new PrintWriter(outStream);
	String thisLine = "";
	while ((thisLine = br.readLine()) != null) 
	
	{
		printWriter.println(thisLine);
		
		if (thisLine.equalsIgnoreCase("[Territories]")) 
		
		{
			String modifiedLink ="";
			while ((thisLine = br.readLine()) != null && thisLine != "")
		
			{
				String[] columns = thisLine.split(",");
				if(columns[0].equalsIgnoreCase(listOfTerritories.get(i)))
				{
				
		         	for(int j = 0 ; j<columns.length ; j++)
		         	{
		         		if(j<4)
		         		{
		         			modifiedLink = modifiedLink.concat(columns[j]+",");
		         			
		         		}
		         		if(j>=4)
		         		{
		         			if(columns[j].equalsIgnoreCase(territory))
		         			{
		         				
		         			}
		         			else
		         			{
		         				modifiedLink = modifiedLink.concat(columns[j]+",");
		         			}
		         		}
		         		
		         	}
		         	printWriter.println(modifiedLink.substring(0, modifiedLink.length()-1));
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
	}
}

public boolean checkAdjacentTerritoryLinkBeforeDelete(String thisLine) throws Exception
{
	String line = thisLine;
	
	
	String[] columns = line.split(",");
	
	for(int i=4 ; i<columns.length ; i++)
	{
		
	File inputFile = new File("World.map");
	BufferedReader br = new BufferedReader(new FileReader(inputFile));

	File outFile = new File("temp.map");
	FileOutputStream outStream = new FileOutputStream(outFile);
	PrintWriter printWriter = new PrintWriter(outStream);
	while ((line = br.readLine()) != null) 
	
	{
		if (line.equalsIgnoreCase("[Territories]")) 
			
		{
			while ((line = br.readLine()) != null) 
			{
				
			String[] column_adjacentTerritory = line.split(",");
				if(columns[i].equals(column_adjacentTerritory[0]) && column_adjacentTerritory.length==5)
			{
					return false;
			}
		}
		
		}
	
     }

	}
	return true;
	}

public void assignNewContinent(String selectedItem , String territory)

{
	try
	{
	String newContinent = selectedItem;
	String territorySelected = territory;
	File inputFile = new File("World.map");
	BufferedReader br = new BufferedReader(new FileReader(inputFile));

	File outFile = new File("temp.map");
	FileOutputStream outStream = new FileOutputStream(outFile);
	PrintWriter printWriter = new PrintWriter(outStream);
	String thisLine = "";
	while ((thisLine = br.readLine() )!=null )
		
	
	{
		printWriter.println(thisLine);
		if (thisLine.equalsIgnoreCase("[Territories]")) 
		
		{
		String newLine = "";
			while ((thisLine = br.readLine()) != null && thisLine != "")
			{
			
				String[] columns = thisLine.split(",");
				
				if(columns.length>=5)
				{
				if (columns[0].equals(territorySelected))
				{
					
					columns[3] = newContinent;
					
					for(int i = 0 ;i <columns.length ; i++)
					{
						
						newLine=newLine.concat(columns[i]+",");
						
					}
					newLine = newLine.substring(0,newLine.length()-1);
					
					printWriter.println(newLine);
					System.out.println("hiiiii "+newContinent+"------"+newLine);
				}
				else
				{
					printWriter.println(thisLine);

				}
				}
				else
				{
					printWriter.println(thisLine);
				}
			//printWriter.println(newTerritory);
			}
			
		}
		
	}
	printWriter.flush();
	printWriter.close();
	br.close();
	inputFile.delete();
	outFile.renameTo(inputFile);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}

public String getPresentContinent(String text)

{
	try
	{
	String presentContinent = text;
	File inputFile = new File("World.map");
	BufferedReader br = new BufferedReader(new FileReader(inputFile));

	File outFile = new File("temp.map");
	FileOutputStream outStream = new FileOutputStream(outFile);
	String thisLine = "";
	while ((thisLine = br.readLine() )!=null )
		
	
	{
		
		if (thisLine.equalsIgnoreCase("[Territories]")) 
		
		{
		String newLine = "";
			while ((thisLine = br.readLine()) != null && thisLine != " ")
			{
			
				String[] columns = thisLine.split(",");
				
				
				if (columns[0].equals(text))
				{
					return columns[3];
					
				}
				
			//printWriter.println(newTerritory);
			}
			
		}
		
	}
	br.close();
	inputFile.delete();
	outFile.renameTo(inputFile);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return null;
}

public ArrayList getCountriesOfContinent(String selectedContinent) 

{
	String continent = selectedContinent;
	ArrayList<String> countriesList = new ArrayList<String>();
	try
	{
	File inputFile = new File("World.map");
	BufferedReader br = new BufferedReader(new FileReader(inputFile));

	File outFile = new File("temp.map");
	FileOutputStream outStream = new FileOutputStream(outFile);
	String thisLine = "";
	while ((thisLine = br.readLine() )!=null )
		
	
	{
		
		if (thisLine.equalsIgnoreCase("[Territories]")) 
		
		{
			
			
			while ((thisLine = br.readLine()) != null && thisLine != " ")
			{
			
				String[] columns = thisLine.split(",");
				
				if(columns.length >=5)
				{
				if (columns[3].equals(continent))
				{
					countriesList.add(columns[0]);
				
				}//printWriter.println(newTerritory);
			}
			}
		}
		
	}
	br.close();
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return countriesList;
	
}
}