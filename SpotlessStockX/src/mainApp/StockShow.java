package mainApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;

import logger.LoggerStockX;

public class StockShow {
    private Scanner scanner;
    private SearchInventory inventoryManager;

    // Database connection parameters (update with your details)
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://your_database_url:your_port/your_database_name";
    private static final String DB_USER = "your_database_user";
    private static final String DB_PASSWORD = "your_database_password";

    public StockShow() {
        this.scanner = new Scanner(System.in);
        this.inventoryManager = new SearchInventory();
    }

    public void showStock() {
        // TODO: Implement logic to display stock information from the database
    }

    private Connection connectToDatabase() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            LoggerStockX.logger.info("Connected to DB successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            LoggerStockX.logger.log(Level.SEVERE, "Error connecting to the DB", e);
        }
        LoggerStockX.logger.info("DB Connection Started");
        return connection;
    }

    private void closeResources() {
        // Closing resources if needed
        if (scanner != null) {
            scanner.close();
        }
    }

    public static void main(String[] args) {
        StockShow stockShow = new StockShow();
        stockShow.showStock();
        stockShow.closeResources();
    }
}
