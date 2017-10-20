package game.risk.model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * A class for storing information about players
 * 
 * @author Team
 *
 */
public class Player {
	// Declaration
	private String name;
	private int totalNoOfArmies;
	private int currentNoOfArmies;
	private Map<Territory, Integer> territorAndArmies;// = new HashMap<String, String>();
	private boolean turn;
	private boolean isComputer;
	private Color territorAndArmiesColor;

	/**
	 * Method to get the color of territory and army
	 * 
	 * @return color of the territory and army
	 */
	public Color getTerritorAndArmiesColor() {
		return territorAndArmiesColor;
	}

	/**
	 * Method to set the color of territories and army in the class attribute
	 * 
	 * @param territorAndArmiesColor
	 */
	public void setTerritorAndArmiesColor(Color territorAndArmiesColor) {
		this.territorAndArmiesColor = territorAndArmiesColor;
	}

	/**
	 * Method to get the name of the player 
	 * 
	 * @return String name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the name of the player in the class attribute.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get the total number of armies of player
	 * 
	 * @return totalNoofArmies the total number of armies of the player
	 */
	public int getTotalNoOfArmies() {
		return totalNoOfArmies;
	}

	/**
	 * Method to set the total number of armies of the player in the class attribute
	 * 
	 * @param totalNoOfArmies
	 *            the total number of armies of the player
	 */
	public void setTotalNoOfArmies(int totalNoOfArmies) {
		this.totalNoOfArmies = totalNoOfArmies;
	}

	/**
	 * Method to get the current number of armies of the player.
	 * 
	 * @return currentNoofrmies
	 */
	public int getCurrentNoOfArmies() {
		return currentNoOfArmies;
	}

	/**
	 * Method to set the current number of armies of the player in the class attribute.
	 * 
	 * @param currentNoOfArmies
	 */
	public void setCurrentNoOfArmies(int currentNoOfArmies) {
		this.currentNoOfArmies = currentNoOfArmies;
	}

	/**
	 * Method to get territories and armies
	 * 
	 * @return territorAndArmies a map containing the territory and armies
	 */
	public Map<Territory, Integer> getTerritorAndArmies() {
		return territorAndArmies;
	}

	/**
	 * Method to set territory and the armies in the class attribute
	 * 
	 * @param territorAndArmies
	 *            a map containing the territory and armies
	 */
	public void setTerritorAndArmies(Map<Territory, Integer> territorAndArmies) {
		this.territorAndArmies = territorAndArmies;
	}

	/**
	 * Method to find the turn of the player
	 * 
	 * @return turn
	 */
	public boolean isTurn() {
		return turn;
	}

	/**
	 * Method to set the turn of the player in the class attribute
	 * 
	 * @param turn
	 */
	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	/**
	 * Method to check if the player is a computer
	 * 
	 * @return isComputer
	 */
	public boolean isComputer() {
		return isComputer;
	}

	/**
	 * Method to set the player as computer in the class attribute
	 * 
	 * @param isComputer
	 */
	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}

}
