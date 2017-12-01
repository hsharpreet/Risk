package game.risk.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import game.risk.model.entities.CurrentGameStatics;
import game.risk.model.entities.Player;
import game.risk.model.entities.Territory;

/**
 * Class to assign random territories to the player
 * 
 * @author team
 *
 */
public class PlayerUtil {

	Player[] players;

	public PlayerUtil(Player[] player) {
		this.players = player;
	}

	/**
	 * Method to assign Random territories
	 * 
	 * @param territories
	 *            a hashMap to store territories
	 */
	public void assignRandomTerritories(HashMap<String, Territory> territories) {
		ArrayList<String> keyList = new ArrayList<>(territories.keySet());
		Collections.shuffle(keyList);
		int p = 0;
		while (true) {
			try {
				for (int i = 0; i < players.length; i++) {
					Territory t = territories.get(keyList.get(p));
					CurrentGameStatics cgs = new CurrentGameStatics(1, t, i);
					players[i].currentGameStaticsList.add(cgs);
					p++;
				}
			} catch (Exception ex) {
				break;
			}
		}
	}

	/**
	 * Method to get players
	 * 
	 * @return the players
	 */
	public Player[] getPlayers() {
		return players;
	}
}
