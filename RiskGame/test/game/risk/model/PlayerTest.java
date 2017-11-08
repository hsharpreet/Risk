package game.risk.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import game.risk.util.CurrentGameStatics;
import game.risk.util.MapReader;
import game.risk.util.Territory;

public class PlayerTest {

	@Test
	public void testReinforcementArmies() {
		
		RiskMap mapDetails = MapReader.readMapFile("World_testingReinforcement.map");
		Territory t1= new Territory();
		t1.setName("test1");
		t1.setContinent("hello");
		
		Territory t2= new Territory();
		t2.setName("test2");
		t2.setContinent("hello");
		
		Territory t3= new Territory();
		t3.setName("Peru");
		t3.setContinent("South America");
		
		Territory t4= new Territory();
		t4.setName("Brazil");
		t4.setContinent("South America");
		
		CurrentGameStatics gamestat1 = new CurrentGameStatics(1, t1);
		CurrentGameStatics gamestat2 = new CurrentGameStatics(2, t2);
		CurrentGameStatics gamestat3 = new CurrentGameStatics(2, t3);
		CurrentGameStatics gamestat4 = new CurrentGameStatics(2, t4);
		Player players[] = new Player[1];
		players[0]= new Player(1, players, mapDetails);
		players[0].currentGameStaticsList= new ArrayList<>();
		players[0].currentGameStaticsList.add(gamestat1);
		players[0].currentGameStaticsList.add(gamestat2);
		players[0].currentGameStaticsList.add(gamestat3);
		players[0].currentGameStaticsList.add(gamestat4);

		assertEquals(3, players[0].calculateReinformentArmies(0));
	}

}
