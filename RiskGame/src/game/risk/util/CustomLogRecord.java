package game.risk.util;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Class to keep the custom record
 * 
 * @author Team
 *
 */
public class CustomLogRecord extends LogRecord {
	/**
	 * a constructor
	 * 
	 * @param level
	 *            attribute of logRecord class
	 * @param msg
	 *            attribute of logRecord class
	 */
	public CustomLogRecord(Level level, String msg) {
		super(level, msg);
		this.setLoggerName("Risk Game");
		this.setMessage(this.getSequenceNumber() + 1 + " - " + msg);
	}

}
