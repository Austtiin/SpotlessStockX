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
	// Initialize the scanner and database connection
    private Scanner scanner;
    private DatabaseConnector dbConn;

    // Constructor
    public StockShow(DatabaseConnector dbConn) {
        // Reminder to not initialize / start a new scanner here
        this.dbConn = dbConn;
    }

    public void showStock(DatabaseConnector dbConn) {
        // Database connection
        try (final Connection connection = dbConn.connectToDatabase()) {
            if (connection != null) {
            	PreparedStatement statement = connection.prepareStatement("SELECT * FROM Chemicals");
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    System.out.println("ChemicalID: " + resultSet.getInt("ChemicalId") +
                            ", Chemical Name: " + resultSet.getString("ChemicalName") +
                            ", Container Size: " + resultSet.getString("ContainerSize") +
                            ", Quantity: " + resultSet.getString("CurrentInventory"));
                }
            } else {
                System.out.println("Error Show All Stock");
            }
        } catch (SQLException e) {
            LoggerStockX.logger.log(Level.SEVERE, "Error in Showing The Stock", e);
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        // Closing resources if needed
        if (scanner != null) {
            scanner.close();
        }
        
    }
    
    
    // Method to show stock per gallon
    public void perGallonShow(int gallon) {
    	// Database connection
        try (final Connection connection = dbConn.connectToDatabase()) {
        	// Check if the connection is not null
            if (connection != null) {
            	// Query to show the stock of chemicals per gallon
            	String query = "SELECT * FROM Chemicals WHERE ContainerSize = " + gallon;
            	// Prepare the statement
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                	

                    // Execute the query
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                        	// Print the results
                            System.out.println("ChemicalID: " + resultSet.getInt("ChemicalId") +
                                    ", Chemical Name: " + resultSet.getString("ChemicalName") +
                                    	", Container Size: " + resultSet.getString("ContainerSize") +
                                    		", Quantity: " + resultSet.getString("CurrentInventory"));
                        }
                    }
                }
                
            } else {
                System.out.println("Error: Database connection is null.");
            }
        } catch (SQLException e) {
            LoggerStockX.logger.log(Level.SEVERE, "ERROR SHOWING CHEMICALS", e);
        } finally {
            closeResources();
        }
    }

    }
