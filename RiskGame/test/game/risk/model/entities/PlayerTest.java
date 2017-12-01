package game.risk.model.entities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import game.risk.gui.PlayerPanel;
import game.risk.gui.RiskGame;
import game.risk.model.MapReader;
import game.risk.model.entities.CurrentGameStatics;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.model.entities.Territory;
import game.risk.model.entities.strategy.CheaterPlayerStrategy;

/**
 * Class to Test reinforcement
 * 
 * @author team
 *
 */
public class PlayerTest {

	Player players[];

	/**
	 * Method to setup variables for each test
	 */
	@Before
	public void setUpData() {
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

		CurrentGameStatics gamestat1 = new CurrentGameStatics(1, t1);
		CurrentGameStatics gamestat2 = new CurrentGameStatics(2, t2);
		CurrentGameStatics gamestat3 = new CurrentGameStatics(3, t3);
		CurrentGameStatics gamestat4 = new CurrentGameStatics(2, t4);
		players = new Player[3];
		players[0] = new Player(new RiskGame(), 0, players, mapDetails);
		players[1] = new Player(new RiskGame(), 1, players, mapDetails);
		players[2] = new Player(new RiskGame(), 2, players, mapDetails);
		players[2].setStrategy(new CheaterPlayerStrategy());
		players[2].currentGameStaticsList= new ArrayList<>();
		players[2].setPlayerPanel(new PlayerPanel());
		players[2].currentGameStaticsTableModel = new CurrentGameStaticsTableModel(
				players[2].currentGameStaticsList);
		players[0].currentGameStaticsList = new ArrayList<>();
		players[0].currentGameStaticsList.add(gamestat1);
		players[0].currentGameStaticsList.add(gamestat2);
		players[0].currentGameStaticsList.add(gamestat3);
		players[0].currentGameStaticsList.add(gamestat4);
		players[2].currentGameStaticsList.add(gamestat1);
		players[2].currentGameStaticsList.add(gamestat2);
	}

	/**
	 * Method to calculate reinforcement armies by no. of the territories occupied
	 */
	@Test
	public void testReinforcementArmies_occupiedTerritories() {
		assertEquals(3, players[0].calculateReinformentArmiesInitially(0));
	}

	/***
	 * Method to calculate reinforcement armies by no. of the territories and
	 * continents occupied
	 */
	@Test
	public void testReinforcementArmies_OccupiedContinent() {
		Territory t5 = new Territory();
		t5.setName("test3");
		t5.setContinent("hello");
		CurrentGameStatics gamestat5 = new CurrentGameStatics(2, t5);
		players[0].currentGameStaticsList.add(gamestat5);
		assertEquals(4, players[0].calculateReinformentArmiesInitially(0));
	}
	
	/***
	 * Method to test a valid fortification 
	 */
	@Test
	public void testFortification_true() {
		players[0].fortification("test1", 2, 0);
		int armiesInSourceCountry = players[0].currentGameStaticsList.get(2).infantries;
		int armiesInDestinationCountry = players[0].currentGameStaticsList.get(0).infantries;
		assertEquals(2, armiesInSourceCountry);
		assertEquals(2, armiesInDestinationCountry);
	}
	
	/***
	 * Method to test a invalid fortification in case destination country is not
	 * the one player occupies
	 */
	@Test
	public void testFortification_destinationCountryInvalid() {
		players[0].fortification("test3", 2, 0);
		int armiesInSourceCountry = players[0].currentGameStaticsList.get(2).infantries;
		int armiesInDestinationCountry = players[0].currentGameStaticsList.get(0).infantries;
		assertEquals(3, armiesInSourceCountry);
		assertEquals(1, armiesInDestinationCountry);
	}

	/***
	 * Method to test a invalid fortification in case source country is having 1 army on it
	 */
	@Test
	public void testFortification_sourceCountryInsufficient() {
		players[0].fortification("Peru", 0, 0);
		int armiesInSourceCountry = players[0].currentGameStaticsList.get(0).infantries;
		int armiesInDestinationCountry = players[0].currentGameStaticsList.get(2).infantries;
		assertEquals(1, armiesInSourceCountry);
		assertEquals(3, armiesInDestinationCountry);
	}

	@Test
	public void testReinforcementArmies_CheaterStrategy() {
		 players[2].reinforcementStrategy(2, players[2], players[2].infantriesAvailable);
		 assertEquals(2, players[2].currentGameStaticsList.get(0).infantries);
		 assertEquals(4, players[2].currentGameStaticsList.get(1).infantries);
	}
	
}
