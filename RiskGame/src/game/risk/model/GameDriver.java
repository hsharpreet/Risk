package game.risk.model;

import java.util.ArrayList;
/**
 * A java class which drives the game.
 * @author Team
 *
 */

public class GameDriver {

	int totalPlayer;
	int totalTerritories;
	int territoriesPerPlayer;
	
	Player player;
	ArrayList<Player> listPlayer;
	/**
	 * Method to get player
	 * @return Player 
	 */
	public Player getPlayer() {
		return player;
	}
	/**
	 * Method to set Players
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	/**
	 * Method to get the list of players
	 * @return ListPlayers The list of players
	 */
	public ArrayList<Player> getListPlayer() {
		return listPlayer;
	}
	/**
	 * Method to set the list of players
	 * @param listPlayer The list of players
	 */
	public void setListPlayer(ArrayList<Player> listPlayer) {
		this.listPlayer = listPlayer;
	}
	/**
	 * Method to get the total number of players
	 * @return totalPlayer The total  number players
	 */
	public int getTotalPlayer() {
		return totalPlayer;
	}
	/**
	 * Method to set the total number of players
	 * @return totalPlayer The total  number players
	 */
	public void setTotalPlayer(int totalPlayer) {
		this.totalPlayer = totalPlayer;
	}
	/**
	 * Method to get the total territories
	 * @return totalTerritories
	 */
	public int getTotalTerritories() {
		return totalTerritories;
	}
	/**
	 * Method to set total territoriess
	 * @param totalTerritories
	 */
	public void setTotalTerritories(int totalTerritories) {
		this.totalTerritories = totalTerritories;
	}
	/**
	 * Method to get the territories per player
	 * @return territoriesPerPlayer.
	 */
	public int getTerritoriesPerPlayer() {
		return territoriesPerPlayer;
	}
	/**
	 * Method to set the territories per player.
	 * @param territoriesPerPlayer
	 */
	public void setTerritoriesPerPlayer(int territoriesPerPlayer) {
		this.territoriesPerPlayer = territoriesPerPlayer;
	}
	
	
	
}
