package game.risk.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import game.risk.gui.RiskGame;
import game.risk.model.entities.CurrentGameStatics;
import game.risk.model.entities.CurrentGameStaticsTableModel;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.model.entities.Territory;
import game.risk.model.entities.strategy.AggressivePlayerStrategy;
import game.risk.model.entities.strategy.HumanStrategy;

/**
 * Class to create test cases for Game Writer
 * 
 * @author author
 *
 */
public class GameWriterTest {
	/**
	 * Method to test the save option
	 */
	@Test
	public void testSave() {
		Player players[];
		GameWriter gameWriter = new GameWriter("World_testingReinforcement");
		RiskMap mapDetails = MapReader.readMapFile("World_testingReinforcement.map");
		Territory t1 = new Territory();
		t1.setName("test1");
		t1.setContinent("hello");

		Territory t2 = new Territory();
		t2.setName("test2");
		t2.setContinent("hello");

		Territory t3 = new Territory();
		t3.setName("Peru");
		t3.setContinent("South America");

		Territory t4 = new Territory();
		t4.setName("Brazil");
		t4.setContinent("South America");

		Territory t5 = new Territory();
		t5.setName("Argentina");
		t5.setContinent("South America");

		CurrentGameStatics gamestat1 = new CurrentGameStatics(1, t1);
		CurrentGameStatics gamestat2 = new CurrentGameStatics(2, t2);
		CurrentGameStatics gamestat3 = new CurrentGameStatics(3, t3);
		CurrentGameStatics gamestat4 = new CurrentGameStatics(2, t4);
		CurrentGameStatics gamestat5 = new CurrentGameStatics(2, t5);

		players = new Player[2];
		players[0] = new Player(new RiskGame(), 0, players, mapDetails);
		players[0].setComputer(false);
		players[0].setStrategy(new HumanStrategy());
		players[0].setName("Human");
		players[1] = new Player(new RiskGame(), 1, players, mapDetails);
		players[1].setComputer(true);
		players[1].setStrategy(new AggressivePlayerStrategy());
		players[1].setName("Aggressive");
		players[0].currentGameStaticsList = new ArrayList<>();
		players[1].currentGameStaticsList = new ArrayList<>();
		players[0].currentGameStaticsList.add(gamestat1);
		players[0].currentGameStaticsList.add(gamestat2);
		players[0].currentGameStaticsList.add(gamestat3);
		players[0].currentGameStaticsList.add(gamestat4);
		players[1].currentGameStaticsList.add(gamestat5);
		players[1].currentGameStaticsTableModel = new CurrentGameStaticsTableModel(players[1].currentGameStaticsList);
		gameWriter.saveGame(players);

		Player[] playerLoaded = gameWriter.readGame();
		assertNotNull(playerLoaded);
		assertEquals("Human", playerLoaded[0].getName());
		assertEquals(false, playerLoaded[0].isComputer());
		assertEquals("Aggressive", playerLoaded[1].getName());
		assertEquals(true, playerLoaded[1].isComputer());
		assertEquals("Territory", playerLoaded[1].currentGameStaticsTableModel.getColumnName(0));
		assertEquals(1, playerLoaded[1].currentGameStaticsTableModel.list.size());
	}

}
