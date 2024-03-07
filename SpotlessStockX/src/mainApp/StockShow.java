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
import mainApp.DatabaseConnector;

public class StockShow {
	// Scanner for user input
    private Scanner scanner;

    // Constructor
    public StockShow() {
        this.scanner = new Scanner(System.in);
        
    }

    public void showStock() {
    	// Database connection
    	DatabaseConnector dbConn = new DatabaseConnector();
    	
    	// Display the stock
		try (Connection connection = dbConn.connectToDatabase()) {
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

    private void closeResources() {
        // Closing resources if needed
        if (scanner != null) {
            scanner.close();
        }
    }


}
