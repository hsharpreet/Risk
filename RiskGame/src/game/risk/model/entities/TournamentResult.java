package game.risk.model.entities;

/**
 * Class to generate Tournament result
 * 
 * @author Team
 *
 */
public class TournamentResult {
	private String mapName;
	private int gameIndex;
	private String winnerName;

	/**
	 * Method to get Map Name
	 * 
	 * @return the name of map
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * Method to set the attribute value
	 * 
	 * @param mapName
	 *            the value
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	/**
	 * Method to get Game index
	 * 
	 * @return the index number
	 */
	public int getGameIndex() {
		return gameIndex;
	}

	/**
	 * Method to set the index value in class attribute
	 * 
	 * @param gameIndex
	 *            the index number
	 */
	public void setGameIndex(int gameIndex) {
		this.gameIndex = gameIndex;
	}

	/**
	 * Method to get the name of the winner
	 * 
	 * @return the winner name
	 */
	public String getWinnerName() {
		return winnerName;
	}

	/**
	 * Method to set the name of the winner in class attribute
	 * 
	 * @param winnerName
	 *            the name of the winner
	 */
	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}

}
