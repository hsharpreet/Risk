package game.risk.model;

import java.util.ArrayList;

public class GameDriver {

	int totalPlayer;
	int totalTerritories;
	int territoriesPerPlayer;
	
	Player player;
	ArrayList<Player> listPlayer;
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public ArrayList<Player> getListPlayer() {
		return listPlayer;
	}
	public void setListPlayer(ArrayList<Player> listPlayer) {
		this.listPlayer = listPlayer;
	}
	public int getTotalPlayer() {
		return totalPlayer;
	}
	public void setTotalPlayer(int totalPlayer) {
		this.totalPlayer = totalPlayer;
	}
	public int getTotalTerritories() {
		return totalTerritories;
	}
	public void setTotalTerritories(int totalTerritories) {
		this.totalTerritories = totalTerritories;
	}
	public int getTerritoriesPerPlayer() {
		return territoriesPerPlayer;
	}
	public void setTerritoriesPerPlayer(int territoriesPerPlayer) {
		this.territoriesPerPlayer = territoriesPerPlayer;
	}
	
	
	
}
