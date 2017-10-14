package game.risk.model;

import java.util.List;
/**
 * A java class to store information about territories
 * @author Simran
 *
 */
public class Territory {

	private String name;
	private String coordinateX;
	private String coordinateY;
	private String continent;
	private List<String> neighbouringTerritories;

	
	// Method to get name of the territory
	public String getName() {
		return name;
	}


	// Method to set name of the territory
	public void setName(String name) {
		this.name = name;
	}


	// Method to get x coordinates of the territory
	public String getCoordinateX() {
		return coordinateX;
	}  


	// Method to set x coordinates of the territory
	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}


	// Method to get y coordinates of the territory
	public String getCoordinateY() {
		return coordinateY;
	}


	// Method to set y coordinates of the territory
	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}


	// Method to get the continent
	public String getContinent() {
		return continent;
	}


	// Method to set the continent
	public void setContinent(String continent) {
		this.continent = continent;
	}


	// Method to get neighboring territories
	public List<String> getNeighbouringTerritories() {
		return neighbouringTerritories;
	}


	// Method to set the neighboring territories
	public void setNeighbouringTerritories(List<String> neighbouringTerritories) {
		this.neighbouringTerritories = neighbouringTerritories;
	}


	//Method to append information to the file.
	@Override
	public String toString() {
		StringBuffer territoryInformation = new StringBuffer();
		territoryInformation.append(getName()).append(",").
		append(getCoordinateX()).append(",").append(getCoordinateY()).append(",")
				.append(getContinent()).append(",").append(getNeighbouringTerritories().toString());
		return territoryInformation.toString();
	}
}
