
package game.risk.util;

/**
 * CurrentGameStatics class Represents the current status of game
 * 
 * @author Team
 * 
 * 
 */
public class CurrentGameStatics {

	public int infantries; // available infantries
	public Territory territory;
	public int player;

	/**
	 * a constructor to initialize the class attributes
	 * 
	 * @param infantries
	 *            the number of infantries
	 * @param territory
	 *            an object of territory class
	 */

	public CurrentGameStatics(int infantries, Territory territory) {
		this.infantries = infantries;
		this.territory = territory;
	}

	/**
	 * a constructor
	 * 
	 * @param infantries
	 *            the number of infantries
	 * @param territory
	 *            an object of territory class
	 * @param player
	 *            the number of players
	 */
	public CurrentGameStatics(int infantries, Territory territory, int player) {
		this.infantries = infantries;
		this.territory = territory;
		this.player = player;
	}
}
