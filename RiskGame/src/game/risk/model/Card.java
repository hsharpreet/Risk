package game.risk.model;

/**
 * Class to create Cards
 * 
 * @author Team
 *
 */
public class Card {
	private Territory territory;
	private String cardDesign;
	private int player;

	/**
	 * A constructor
	 * 
	 * @param territory
	 *            An object of territory class
	 * @param cardDesign
	 *            the design of the card
	 */
	public Card(Territory territory, String cardDesign) {
		this.territory = territory;
		this.cardDesign = cardDesign;
		this.player = -1;
	}

	/**
	 * Method to get the territory details
	 * 
	 * @return territory an object of territory class
	 */
	public Territory getTerritory() {
		return territory;
	}

	/**
	 * Method to set the territory details in the class attribute
	 * 
	 * @param territory
	 *            the territory details
	 */
	public void setTerritory(Territory territory) {
		this.territory = territory;
	}

	/**
	 * Method to get the card design
	 * 
	 * @return cardDesign a String variable
	 */
	public String getCardDesign() {
		return cardDesign;
	}

	/**
	 * Method to set the card Design details in the class attribute
	 * 
	 * @param cardDesign
	 *            A string variable
	 */
	public void setCardDesign(String cardDesign) {
		this.cardDesign = cardDesign;
	}

	/**
	 * Method to get the number of players
	 * 
	 * @return Player an integer variable
	 */
	public int getPlayer() {
		return player;
	}

	/**
	 * Method to set the Number of players in the class variable
	 * 
	 * @param player
	 *            the number of players
	 */
	public void setPlayer(int player) {
		this.player = player;
	}

}
