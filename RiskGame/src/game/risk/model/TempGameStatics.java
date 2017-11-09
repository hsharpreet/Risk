package game.risk.model;

/**
 * A class which maintains the temporary status of the game
 * 
 * @author Team
 *
 */
public class TempGameStatics {

	public int infantries;
	public Territory territory;
	public int player;
	public boolean isOwn;
	public boolean isNeighbour;

	/**
	 * A constructor
	 * 
	 * @param territory
	 *            the name of the territory
	 */
	public TempGameStatics(Territory territory) {
		this.territory = territory;
	}
}
