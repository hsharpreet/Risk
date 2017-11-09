package game.risk.util;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import game.risk.model.Player;
import game.risk.model.RiskMap;

/**
 * Class to test startup phase
 * 
 * @author team
 *
 */
public class PlayerUtilTest {

	/**
	 * Method to check at starting territories are being assigned properly
	 * 
	 * @throws Exception
	 *             unchecked
	 */
	@Test
	public void testAssignRandomTerritories() throws Exception {
		MapReader reader = new MapReader();
		RiskMap riskMap = reader.readMap("World_testingReinforcement.map");
		Player[] players = new Player[2];
		players[0] = new Player(null, 0, players, riskMap);
		players[1] = new Player(null, 1, players, riskMap);
		players[0].currentGameStaticsList = new ArrayList<>();
		players[1].currentGameStaticsList = new ArrayList<>();
        PlayerUtil playerUtil = new PlayerUtil(players);
		playerUtil.assignRandomTerritories(riskMap.getTerritories());
		players = playerUtil.getPlayers();

		int territorydistribution = riskMap.getTerritories().size() / players.length;
		assertTrue(players[0].currentGameStaticsList.size() >= territorydistribution);
		assertTrue(players[1].currentGameStaticsList.size() >= territorydistribution);
	}

}
