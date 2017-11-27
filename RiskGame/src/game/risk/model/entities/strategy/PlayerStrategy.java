package game.risk.model.entities.strategy;

import java.util.logging.Level;

import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;

public interface PlayerStrategy {

	public int placeInfantoryStrategy(int i, Player player, int army);
	
	public int reinforcementStrategy(int i, Player player, int army);

	public int attackStrategy(Player player[],int i, Player player2, RiskMap mapDetails);

	public int fortificationStrategy();
	
	public default int randomNumber(int size) {
		double d = Math.random();
		int random = (int) Math.round(d * (size - 1));
		CustomLogRecord logRecord = new CustomLogRecord(Level.INFO,
				"Random number generated: ");
		LoggerUtility.consoleHandler.publish(logRecord);
		return random;
	}
}