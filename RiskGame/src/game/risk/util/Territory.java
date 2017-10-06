package game.risk.util;

import java.util.List;

public class Territory {

	private String name;
	private String coordinateX;
	private String coordinateY;
	private String continent;
	private List<String> neighbouringTerritories;

	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCoordinateX() {
		return coordinateX;
	}



	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}



	public String getCoordinateY() {
		return coordinateY;
	}



	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}



	public String getContinent() {
		return continent;
	}



	public void setContinent(String continent) {
		this.continent = continent;
	}



	public List<String> getNeighbouringTerritories() {
		return neighbouringTerritories;
	}



	public void setNeighbouringTerritories(List<String> neighbouringTerritories) {
		this.neighbouringTerritories = neighbouringTerritories;
	}



	@Override
	public String toString() {
		StringBuffer territoryInformation = new StringBuffer();
		territoryInformation.append(getName()).append(",").
		append(getCoordinateX()).append(",").append(getCoordinateY()).append(",")
				.append(getContinent()).append(",").append(getNeighbouringTerritories().toString());
		return territoryInformation.toString();
	}
}
