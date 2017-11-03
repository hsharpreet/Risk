package game.risk.model;

public class TempGameStatics {

	public int infantries;
	public Territory territory;
	public int player;
	public boolean isOwn;
	public boolean isNeighbour;

	public TempGameStatics(Territory territory) {
		this.territory = territory;
	}
}
