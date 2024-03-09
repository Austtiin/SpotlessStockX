package mainApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;

import logger.LoggerStockX;

public class AddItem {
	private static Scanner scanner;
    private static DatabaseConnector dbConn;
    
	public AddItem(DatabaseConnector dbConn) {
		AddItem.dbConn = dbConn;
	}

	
	public static void ShowCurrent (DatabaseConnector dbConn) {
		try (final Connection connection = dbConn.connectToDatabase()) {
        	// Check if the connection is not null
            if (connection != null) {
            	// Query to show the stock of chemicals per gallon
            	String query = "SELECT * FROM CurrentInventory;";
            	// Prepare the statement
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                	

                    // Execute the query
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                        	// Print the results
                            System.out.println("InventoryID: " + resultSet.getInt("InventoryID") +
                                    ", Chemical Name: " + resultSet.getString("ChemicalID") +
                                    	", Container Size: " + resultSet.getString("Quantity") +
                                    		", Last Update: " + resultSet.getString("LastUpdate"));
                        }
                    }
                }
                
            } else {
                System.out.println("Error: Database connection is null.");
            }
        } catch (SQLException e) {
            LoggerStockX.logger.log(Level.SEVERE, "ERROR ADDING TO INVENTORY", e);
        } finally {
            closeResources();
        }

	}

	
	public static void itemAdd (String chemicalName, String containerSize, String currentInventory) {
    	// Database connection
        try (final Connection connection = dbConn.connectToDatabase()) {
        	// Check if the connection is not null
            if (connection != null) {
            	// Query to insert a new item into the inventory
            	String query = "INSERT INTO "
            			+ "CurrentInventory "
            			+ "(ChemicalName, ContainerSize, CurrentQuantity) "
            			+ "VALUES "
            			+ chemicalName + ", " + containerSize + ", " + currentInventory;
            	
            	// Prepare the statement
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                	

                    // Execute the query
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                        	// Print the results
                            System.out.println(
                            		"InventoryID: " + resultSet.getInt("InventoryID: ") 
                            		+ ", Chemical Name: " + resultSet.getString("ChemicalName")
                            		+ ", Container Size: " + resultSet.getString("ContainerSize")
                            		+ ", Quantity: " + resultSet.getString("CurrentQuantity"));
                        }
                    }
                    System.out.println("Item added successfully");
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
	
	private static void closeResources() {
        // Closing resources if needed
        if (scanner != null) {
            scanner.close();
        }
    }
}
