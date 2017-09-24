package test;

import java.util.List;

public class Territories {

	private String territoryName;
	private String territoryCoordinateX;
	private String territoryCoordinateY;
	
	private List<String> territoryNeighbours;

	public String getTerritoryName() {
		return territoryName;
	}

	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	public String getTerritoryCoordinateX() {
		return territoryCoordinateX;
	}

	public void setTerritoryCoordinateX(String territoryCoordinateX) {
		this.territoryCoordinateX = territoryCoordinateX;
	}

	public String getTerritoryCoordinateY() {
		return territoryCoordinateY;
	}

	public void setTerritoryCoordinateY(String territoryCoordinateY) {
		this.territoryCoordinateY = territoryCoordinateY;
	}

	public List<String> getTerritoryNeighbours() {
		return territoryNeighbours;
	}

	public void setTerritoryNeighbours(List<String> territoryNeighbours) {
		this.territoryNeighbours = territoryNeighbours;
	}
	
	
}
