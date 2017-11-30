package game.risk.model.entities;

public class TournamentResult {
	private String mapName;
    private int gameIndex;
    private String winnerName;
    
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public int getGameIndex() {
		return gameIndex;
	}
	public void setGameIndex(int gameIndex) {
		this.gameIndex = gameIndex;
	}
	public String getWinnerName() {
		return winnerName;
	}
	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}    

}
