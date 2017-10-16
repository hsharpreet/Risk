package game.risk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Validation

{
public boolean validateAddLink(String countryName , String linkName , String mapFileName)throws Exception
{
    String country = countryName;
    String link = linkName;
    String mapfile = mapFileName;
	File inputFile = new File(mapfile);
	BufferedReader br = new BufferedReader(new FileReader(inputFile));
	String thisLine = "";
	while ((thisLine = br.readLine()) != null) {
		if (thisLine.equalsIgnoreCase("[Territories]")) 
		{
			while ((thisLine = br.readLine()) != null && thisLine != "") {
				String[] columns = thisLine.split(",");
				if (columns[0].equalsIgnoreCase(country)) 
				{
					for(int j=4 ; j<columns.length ; j++)
					{
						if(columns[j].equals(link))
						{
							return false;
						}
					}
                 }
	
}
		}
	}
	return true;
}
}
	
