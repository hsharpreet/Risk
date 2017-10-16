package game.risk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

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

	public boolean validateLinkToDelete(String neighbouringCountry , String thisLine) throws Exception {
	
		String[] column_adjacentTerritory = thisLine.split(",");
					if (neighbouringCountry.equals(column_adjacentTerritory[0]) && column_adjacentTerritory.length > 5) {
							return true;
					}
				
		
		return false;
	}

}
