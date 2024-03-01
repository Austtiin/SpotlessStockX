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
            fh = new FileHandler("StockXLog.log");
            logger.addHandler(fh);

            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to load Filehandler", e);
        }
    }

    public static void main(String[] args) {
        loggerStockX log = new loggerStockX();
        logger.info("Logger Created");
        try {
            
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception: ", e);
        }
    }
}