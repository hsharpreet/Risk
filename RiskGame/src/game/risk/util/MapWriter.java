package game.risk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;

public class MapWriter {

	public static void main(String[] args) throws IOException {
		MapWriter writer = new MapWriter();
		writer.addContinent("aman", "7");
		System.out.println("updated new file");
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
	}
