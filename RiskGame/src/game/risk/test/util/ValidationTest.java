package game.risk.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

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
	
	
}
