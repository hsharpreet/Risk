package game.risk.model.entities;

/**
 * A class to present the results of tournament mode
 * 
 * @author Team
 *
 */

public class TournamentResult {
	private String mapName;
	private int gameIndex;
	private String winnerName;

	/**
	 * a method to get the name of map file
	 * 
	 * @return mapName name of map file
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * a method to set the name of map file
	 * 
	 * @param mapName
	 *            name of map file
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	/**
	 * a method to tell about no. of game
	 * 
	 * @return gameIndex which game no.
	 */
	public int getGameIndex() {
		return gameIndex;
	}

	/***
	 * a method to set the no. of game
	 * 
	 * @param gameIndex
	 *            no. of game
	 */
	public void setGameIndex(int gameIndex) {
		this.gameIndex = gameIndex;
	}

	/**
	 * a method to get the name of winner
	 * 
	 * @return winnerName name of the winner of the game
	 */
	public String getWinnerName() {
		return winnerName;
	}

	/**
	 * a method to set the name of winner
	 * 
	 * @param winnerName
	 *            name of winner of game
	 */
	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}

}
