package game.risk.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import game.risk.model.TournamentModel;
import game.risk.model.entities.TournamentResult;;
public class TournamentModelTest {

	@Before
	public void setUp() throws Exception {
	
		
	}

	@Test
	public void testValidResultCount() {
		String [] player = {"Aggressive","Benevolent","Select","Select"};
		String [] maps= {"World.map","World.map" };
		int count=3;
		int turn=10;
		
		TournamentModel t= new TournamentModel(player,maps,count,turn);
		t.startTournament();
		
		List<TournamentResult> bb = t.getTournamentResult();
		
		assertEquals(6,bb.size());
	}
	@Test
	public void testFalseValidResultCount() {
		String [] player = {"Aggressive","Benevolent","Select","Select"};
		String [] maps= {"World.map","World.map" };
		int count=3;
		int turn=10;
		
		TournamentModel t= new TournamentModel(player,maps,count,turn);
		t.startTournament();
		
		List<TournamentResult> bb = t.getTournamentResult();
		
		assertNotEquals(2,bb.size());
	}
	
}
