package game.risk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

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

}