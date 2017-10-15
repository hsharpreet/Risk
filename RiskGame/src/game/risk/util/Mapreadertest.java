package game.risk.util;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

/**
 * @author admin
 *
 */

public class Mapreadertest {

	/**
	 * @throws java.lang.Exception
	 */
	
	@Before
	public void setUp() throws Exception {
		
		
	}

	@Test
	public void test() {
RiskMap map= new RiskMap();
		
		HashMap<String,String> cont=new HashMap<String,String>();
		cont.put("North America", "5");
		cont.put("South America", "2");
		cont.put("Africa", "3");
		cont.put("Europe", "5");
		cont.put("Asia", "7");
		cont.put("Australia", "2");
		
		HashMap<String,String> result=new HashMap<String,String>();
		result=map.continents;
		asseertEquals(result,cont);
		
	}

	private void asseertEquals(HashMap<String, String> result, HashMap<String, String> cont) {
		// TODO Auto-generated method stub
		
	}

}