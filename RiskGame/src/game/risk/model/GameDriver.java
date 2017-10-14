package game.risk.model;

import java.util.ArrayList;
/**
 * A java class which drives the game.
 * @author Simran
 *
 */

public class GameDriver {

	int totalPlayer;
	int totalTerritories;
	int territoriesPerPlayer;
	
	Player player;
	ArrayList<Player> listPlayer;
	// Method to get player
	public Player getPlayer() {
		return player;
	}
	// Method to set player
	public void setPlayer(Player player) {
		this.player = player;
	}
	// Method to list all the players
	public ArrayList<Player> getListPlayer() {
		return listPlayer;
	}
	// Method to save the list of players
	public void setListPlayer(ArrayList<Player> listPlayer) {
		this.listPlayer = listPlayer;
	}
	//  Method to get the total players
	public int getTotalPlayer() {
		return totalPlayer;
	}
	//Method to set the total number of players
	public void setTotalPlayer(int totalPlayer) {
		this.totalPlayer = totalPlayer;
	}
	// Method to get the total territories
	public int getTotalTerritories() {
		return totalTerritories;
	}
	public void setTotalTerritories(int totalTerritories) {
		this.totalTerritories = totalTerritories;
	}
	// Method to  get the number of territories per player.
	public int getTerritoriesPerPlayer() {
		return territoriesPerPlayer;
	}
	public void setTerritoriesPerPlayer(int territoriesPerPlayer) {
		this.territoriesPerPlayer = territoriesPerPlayer;
	}
	
	
	
}
