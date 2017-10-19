package game.risk.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import game.risk.gui.MapEditor;
import game.risk.util.MapReader;
import game.risk.util.MapWriter;
import game.risk.util.RiskMap;
import game.risk.util.Validation;

public class ValidationTest {

	@Test
	public void testValidateAddLink_ForNeighbouringCountryAlreadyExist() throws Exception {
		String thisLine ="Alaska,30,46,North America,Northwest Territory,Alberta,Kamchatka";
		String country= "Alaska";
		String neighbouringCountry="Alberta";
		
		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.validateAddLink(country, neighbouringCountry, thisLine);
		
		assertFalse(result);
	}
	
	@Test
	public void testValidateAddLink_ForNeighbouringCountryNotExist() throws Exception {
		String thisLine ="Alaska,30,46,North America,Northwest Territory,Alberta,Kamchatka";
		String country= "Alaska";
		String neighbouringCountry="India";
		
		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.validateAddLink(country, neighbouringCountry, thisLine);
		
		assertTrue(result);
	}
	
	@Test
	public void testvalidateAddLinkAddingItselfFalse() throws Exception {
		String thisLine ="Alaska,30,46,North America,Northwest Territory,Alberta,Kamchatka";
		String country= "Alaska";
		String neighbouringCountry="Alaska";
		
		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.validateAddLinkAddingItself(country, neighbouringCountry, thisLine);
		
		assertFalse(result);
	}
	
	@Test
	public void testvalidateAddLinkAddingItselfTrue() throws Exception {
		String thisLine ="Alaska,30,46,North America,Northwest Territory,Alberta,Kamchatka";
		String country= "Alaska";
		String neighbouringCountry="India";
		
		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.validateAddLinkAddingItself(country, neighbouringCountry, thisLine);
		
		assertTrue(result);
	}
	
	@Test
	public void testvalidateLinkToDeleteTrue() throws Exception {
		
		String countryLink= "Argentina";
		String mapFile="World_TerritoryWithOneNeighbour.map";
		
		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.validateLinkToDelete(countryLink, mapFile);
		
		assertFalse(result);
	}
	
	@Test
	public void testCheckTerritoriesBeforeDeletingContinentFalse() throws Exception {
		
		String mapFile="WorldDeleteInvalid.map";
		
		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.checkTerritoriesBeforeDeletingContinent("hello", mapFile);
		
		assertFalse(result);
	}

	@Test
	public void testCheckAdjacentTerritoryLinkBeforeDeleteFalse() throws Exception {
		
		String mapFile="WorldDeleteInvalid2.map";
		
		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.checkAdjacentTerritoryLinkBeforeDelete("test1", mapFile);
		
		assertFalse(result);
	}
	
	
	
}
