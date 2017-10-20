package game.risk.model;

import java.util.List;

/**
 * A java class to store information about territories
 * 
 * @author Team
 *
 */
public class Territory {

	private String name;
	private String coordinateX;
	private String coordinateY;
	private String continent;
	private List<String> neighbouringTerritories;

	/**
	 * Method to get name of the territory
	 * 
	 * @return name the name of the territory
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Method to set name of the territory in the class attribute
	 * 
	 * @param name
	 *            the name of the territory
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get the x coordinate of the territory
	 * 
	 * @return coordinateX the x coordinate of the territory
	 */
	public String getCoordinateX() {
		return coordinateX;
	}

	/**
	 * Method to set the x coordinate in the class attribute
	 * 
	 * @param coordinateX
	 *            the x coordinate of the territory
	 */
	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}

	/**
	 * Method to get the y coordinate of the territory
	 * 
	 * @return coordinateY the y coordinate
	 */
	public String getCoordinateY() {
		return coordinateY;
	}

	/**
	 * Method to set the y coordinate in the class attribute
	 * 
	 * @param coordinateY
	 *            the y coordinate
	 */
	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}

	/**
	 * Method to get the y coordinate of the territory
	 * 
	 * @return continent the continent of the territory
	 */
	public String getContinent() {
		return continent;
	}

	/**
	 * Method to set the continent in the class attribute
	 * 
	 * @param continent
	 *            the continent
	 */
	public void setContinent(String continent) {
		this.continent = continent;
	}

	/**
	 * Method to get the neighboring territories
	 * 
	 * @return neighboringTerritories the neighboring territories
	 */
	public List<String> getNeighbouringTerritories() {
		return neighbouringTerritories;
	}

	/**
	 * Method to set the neighboring territories in the class attribute
	 * 
	 * @param neighbouringTerritories
	 */
	public void setNeighbouringTerritories(List<String> neighbouringTerritories) {
		this.neighbouringTerritories = neighbouringTerritories;
	}

	/**
	 * Method to append the territory information in the file
	 */
	@Override
	public String toString() {
		StringBuffer territoryInformation = new StringBuffer();
		territoryInformation.append(getName()).append(",").append(getCoordinateX()).append(",").append(getCoordinateY())
				.append(",").append(getContinent()).append(",").append(getNeighbouringTerritories().toString());
		return territoryInformation.toString();
	}
}
