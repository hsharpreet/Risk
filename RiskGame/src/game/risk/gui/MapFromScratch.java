package game.risk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;;

public class MapFromScratch {

	private String mapName, authorVal, warnVal, imageVal, wrapVal, scrollVal, contName, contVal, territoryName,
			territoryCordX, territoryCordY, territoryContinent;

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getAuthorVal() {
		return authorVal;
	}

	public void setAuthorVal(String authorVal) {
		this.authorVal = authorVal;
	}

	public String getWarnVal() {
		return warnVal;
	}

	public void setWarnVal(String warnVal) {
		this.warnVal = warnVal;
	}

	public String getImageVal() {
		return imageVal;
	}

	public void setImageVal(String imageVal) {
		this.imageVal = imageVal;
	}

	public String getWrapVal() {
		return wrapVal;
	}

	public void setWrapVal(String wrapVal) {
		this.wrapVal = wrapVal;
	}

	public String getScrollVal() {
		return scrollVal;
	}

	public void setScrollVal(String scrollVal) {
		this.scrollVal = scrollVal;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getContVal() {
		return contVal;
	}

	public void setContVal(String contVal) {
		this.contVal = contVal;
	}

	public String getTerritoryName() {
		return territoryName;
	}

	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	public String getTerritoryCordX() {
		return territoryCordX;
	}

	public void setTerritoryCordX(String territoryCordX) {
		this.territoryCordX = territoryCordX;
	}

	public String getTerritoryCordY() {
		return territoryCordY;
	}

	public void setTerritoryCordY(String territoryCordY) {
		this.territoryCordY = territoryCordY;
	}

	public String getTerritoryContinent() {
		return territoryContinent;
	}

	public void setTerritoryContinent(String territoryContinent) {
		this.territoryContinent = territoryContinent;
	}

}