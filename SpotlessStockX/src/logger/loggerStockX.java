
// Purpose: Create a logger for the StockX application. This logger will log all events that occur in the application.


package logger;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class loggerStockX {

    public static final Logger logger = Logger.getLogger("StockXLogger");
    private static FileHandler fh;

    static {
        try {
        	// Create log file
            fh = new FileHandler("StockXLog.log");
            logger.addHandler(fh);

            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            
            // Set default level
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
        	// Log error if filehandler fails to load
            logger.log(Level.SEVERE, "Failed to load Filehandler", e);
            
        }
    }

    public static void main(String[] args) {
        loggerStockX log = new loggerStockX();
        logger.info("Logger Created");
        try {
            // log.logger.log(Level.INFO, "This is a test message");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception: ", e);
        }
    }
}