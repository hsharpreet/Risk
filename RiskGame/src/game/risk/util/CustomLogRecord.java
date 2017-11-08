package game.risk.util;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * 
 * @author Team
 *
 */
public class CustomLogRecord extends LogRecord {
	/**
	 * 
	 * @param level
	 * @param msg
	 */
	public CustomLogRecord(Level level, String msg) {
		super(level, msg);
		this.setLoggerName("Risk Game");
		this.setMessage(this.getSequenceNumber() + 1 + " - " + msg);
	}

}
