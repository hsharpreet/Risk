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

}
