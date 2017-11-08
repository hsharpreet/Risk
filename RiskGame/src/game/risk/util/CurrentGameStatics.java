
package game.risk.util;

/**
 * 
 * @author Team
 * 
 * CurrentGameStatics class Represents the current status of game
 *
 */
public class CurrentGameStatics {

	public int infantries; // available infantries
	public Territory territory;
	public int player;

	/**
	 * 
	 * @param infantries
	 * @param territory
	 */

	public CurrentGameStatics(int infantries, Territory territory) {
		this.infantries = infantries;
		this.territory = territory;
	}

	/**
	 * 
	 * @param infantries
	 * @param territory
	 * @param player
	 */
	public CurrentGameStatics(int infantries, Territory territory, int player) {
		this.infantries = infantries;
		this.territory = territory;
		this.player = player;
	}
}
