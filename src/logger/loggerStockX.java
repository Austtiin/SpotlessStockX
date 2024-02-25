// Last Update: 02/22/2024
// Description: This class is used to log the information of the StockX
// Purpose: This class is used to log the information of the StockX API.
// Austin Stephens
// 02/19/2024
// Professor Kumar
// Advanced Java Programming
// COP3805C


package logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// This class is used to log the information of the StockX API.
public class loggerStockX{
    // Create a Logger
    public static final Logger logger = Logger.getLogger("StockXLog");
    private FileHandler fh;

   // Constructor
    public loggerStockX() {
        try {
            // Initialize the FileHandler
            fh = new FileHandler("StockXLog.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            
            // Set the logger level to INFO
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            // Log a severe message if the FileHandler fails
            logger.log(Level.SEVERE, "FileHandler failed", e);
        }
    }

   // Main method
    public static void main(String[] args) {
        // Initialize the logger
        logger.info("Logger Initialized.");
        try {
            // Log a message
        } catch (Exception e) {
            // Log a severe message if an exception occurs
            logger.log(Level.WARNING, "Exception: ", e);
        }
    }

    // Method to log a message
    public static void loggerBAD(java.lang.System.Logger.Level error, String string, Exception e) {
        logger.log(Level.SEVERE, string, e);
    }
}