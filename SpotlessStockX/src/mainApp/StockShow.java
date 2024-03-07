// Purpose: This class is used to show the stock of the chemicals in the inventory.

package mainApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;

import logger.LoggerStockX;

public class StockShow {
	// Scanner for user input
    private Scanner scanner;
    
    // Database credentials
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://your_database_url:your_port/your_database_name";
    private static final String DB_USER = "your_database_user";
    private static final String DB_PASSWORD = "your_database_password";

    // Constructor
    public StockShow() {
        this.scanner = new Scanner(System.in);
    }

    public void showStock() {
    	
		try (Connection connection = connectToDatabase()) {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Chemcials");
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				System.out.println("ChemicalID: " + 
						resultSet.getInt("id") + 
							", Chemical Name: " + 
							resultSet.getString("ChemicalName") + 
								", Container Size: " + 
								resultSet.getString("ContainerSize") +
										", Quantity: " + 
										resultSet.getString("CurrentInventory"));
			}
			
		} catch (SQLException e) {
			LoggerStockX.logger.log(Level.SEVERE, "Error in Showing The Stock", e);
		}
		
		closeResources();
    }

    private Connection connectToDatabase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            LoggerStockX.logger.info("Connected to DB successfully.");
        } catch (SQLException e) {
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
