package game.risk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;;

/**
 * A class to create a Map from scratch
 * 
 * @author Team
 *
 */
public class MapFromScratch {

	private String mapName, authorVal, warnVal, imageVal, wrapVal, scrollVal, contName, contVal, territoryName,
			territoryCordX, territoryCordY, territoryContinent;

	/**
	 * A Method to get the name of the map
	 * 
	 * @return MapName A string Variable containing the name of the map
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * A method to set the name of the map in the class variable
	 * 
	 * @param mapName
	 *            the name of the map
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	/**
	 * A method to get the name of the author
	 * 
	 * @return authorVal A String variable containing the name of the author
	 */
	public String getAuthorVal() {
		return authorVal;
	}

	/**
	 * A method to set the name of the author in the local variable
	 * 
	 * @param authorVal
	 *            the name of the author
	 */
	public void setAuthorVal(String authorVal) {
		this.authorVal = authorVal;
	}

	/**
	 * A method to get the Warning value
	 * 
	 * @return warnVal A String variable containing the warning value
	 */
	public String getWarnVal() {
		return warnVal;
	}

	/**
	 * A method to set the warning value in the class variable
	 * 
	 * @param warnVal
	 *            the warning value
	 */
	public void setWarnVal(String warnVal) {
		this.warnVal = warnVal;
	}

	/**
	 * A method to get the image value
	 * 
	 * @return imageVal A String variable containing the image value
	 */
	public String getImageVal() {
		return imageVal;
	}

	/**
	 * A method to set the image value in the class variable
	 * 
	 * @param imageVal
	 *            the image value
	 */

	public void setImageVal(String imageVal) {
		this.imageVal = imageVal;
	}

	/**
	 * A method to get the Wrap value
	 * 
	 * @return wrapVal A String variable containing the wrap value
	 */
	public String getWrapVal() {
		return wrapVal;
	}

	/**
	 * A method to set the wrap value in the class variable
	 * 
	 * @param wrapVal
	 *            the wrap value
	 */

	public void setWrapVal(String wrapVal) {
		this.wrapVal = wrapVal;
	}

	/**
	 * A method to get the scroll value
	 * 
	 * @return scrollVal A String variable containing the scroll value
	 */
	public String getScrollVal() {
		return scrollVal;
	}

	/**
	 * A method to set the scroll value in the class variable
	 * 
	 * @param scrollVal
	 *            the scroll value
	 */

	public void setScrollVal(String scrollVal) {
		this.scrollVal = scrollVal;
	}

	/**
	 * A method to get the Continent Name
	 * 
	 * @return contName A String variable containing the continent name
	 */
	public String getContName() {
		return contName;
	}

	/**
	 * A method to set the Continent name in the class variable
	 * 
	 * @param contName
	 *            the name of the continent
	 */

	public void setContName(String contName) {
		this.contName = contName;
	}

	/**
	 * A method to get the continent value
	 * 
	 * @return contVal A String variable containing the continent value
	 */
	public String getContVal() {
		return contVal;
	}

	/**
	 * A method to set the continent value in the class variable
	 * 
	 * @param contVal
	 *            the value of the continent
	 */

	public void setContVal(String contVal) {
		this.contVal = contVal;
	}

	/**
	 * A method to get the Territory name
	 * 
	 * @return territoryName A String variable containing the territory name
	 */
	public String getTerritoryName() {
		return territoryName;
	}

	/**
	 * A method to set the territory name in the class variable
	 * 
	 * @param territoryName
	 *            the territory name
	 */

	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	/**
	 * A method to get the x coordinate of territory
	 * 
	 * @return territoryCordx A String variable containing the x coordinate value of
	 *         territory
	 */
	public String getTerritoryCordX() {
		return territoryCordX;
	}

	/**
	 * A method to set the x coordinate of teh territory in the class variable
	 * 
	 * @param territoryCordX
	 *            the x coordinate value
	 */

	public void setTerritoryCordX(String territoryCordX) {
		this.territoryCordX = territoryCordX;
	}

	/**
	 * A method to get the Y coordinate of territory
	 * 
	 * @return territoryCordY A String variable containing the Y coordinate value of
	 *         the territory
	 */
	public String getTerritoryCordY() {
		return territoryCordY;
	}

	/**
	 * A method to set the Y coordinate of territory in the class variable
	 * 
	 * @param territoryCordY
	 *            the y coordinate value
	 */

	public void setTerritoryCordY(String territoryCordY) {
		this.territoryCordY = territoryCordY;
	}

	/**
	 * A method to get the territories of the continent
	 * 
	 * @return territoryContinent A String variable containing the territories of
	 *         the continent
	 */
	public String getTerritoryContinent() {
		return territoryContinent;
	}

	/**
	 * A method to set the territories of the continent in the class variable
	 * 
	 * @param territoryContinent
	 *            the territory names
	 */

	public void setTerritoryContinent(String territoryContinent) {
		this.territoryContinent = territoryContinent;
	}

}