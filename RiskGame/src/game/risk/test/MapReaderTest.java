package game.risk.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import game.risk.util.MapReader;
import game.risk.util.RiskMap;

/**
 * @author admin
 *
 */

public class MapReaderTest {

	/**
	 * @throws java.lang.Exception
	 */
	
	@Before
	public void setUp() throws Exception {
		
		
	}
	
	@Test
	public void testReadMapWithInvalidContinentInCountries() throws Exception {
   MapReader mapReader = new MapReader();
   String path = "World_WithinvalidContinentinCountry.map";
   RiskMap riskMap = mapReader.readMap(path);
   System.out.println("testReadMapWithInvalidContinentInCountries"+riskMap);
   assertNull(riskMap);
   
	}

	@Test
	public void testReadMapCountrieswithNoNeighbours() throws Exception {
   MapReader mapReader = new MapReader();
   String path = "World_CountryWithNoNeighbours.map";
   RiskMap riskMap = mapReader.readMap(path);
   System.out.println("testReadMapCountrieswithNoNeighbours"+riskMap);
   assertNull(riskMap);
   
	}
	
	@Test
	public void testReadMapCountrieswithAsymmetricNeighbours() throws Exception {
   MapReader mapReader = new MapReader();
   String path = "World_AsymmetricNeighbours.map";
   RiskMap riskMap = mapReader.readMap(path);
   System.out.println("testReadMapCountrieswithAsymmetricNeighbours"+riskMap);
   assertNull(riskMap);
   
	}
	
	@Test
	public void testGetContinentOfACountry_validCountry() {
		System.out.println("\ntestGetContinentOfACountry_validCountry.........");
   MapReader mapReader = new MapReader();
   String path = "World.map";
   String country =  "Alberta";
   String continent = mapReader.getContinentOfACountry(country, path);
   assertEquals("North America", continent);
   assertNotNull(continent);
   
	}

	@Test
	public void testGetContinentOfACountry_InvalidCountry() {
		System.out.println("\ntestGetContinentOfACountry_InvalidCountry.........");
   MapReader mapReader = new MapReader();
   String path = "World.map";
   String country =  "hello";
   String continent = mapReader.getContinentOfACountry(country, path);
   System.out.println("testGetContinentOfACountry_InvalidCountry"+continent);
   assertTrue(continent.isEmpty());
   
	}


}