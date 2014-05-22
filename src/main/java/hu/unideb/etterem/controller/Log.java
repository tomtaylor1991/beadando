package hu.unideb.etterem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Az osztály biztosít egy Logolás kezelőhöz hozzáférést.
 * 
 * @author Szabo Tamas
 *
 */
public class Log {
	
	/**
	 * Példák:
	 * 	
	    Log.logger.error("This is an ERROR message");
		Log.logger.warn("This is a WARNING message");
		Log.logger.info("This is an INFO message");
		Log.logger.debug("This is a DEBUG message");
		Log.logger.trace("This is a TRACE message");
	 *	
	 * */
	
	
	/**
	 * Logger referencia.
	 */
	public static Logger logger;
	
	static {
		logger = LoggerFactory.getLogger(Main.class);
	}
}
