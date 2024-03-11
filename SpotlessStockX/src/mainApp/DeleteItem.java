// Purpose: Delete an item from the inventory by searching for it by name and then confirming the deletion.


package mainApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;

import logger.LoggerStockX;

public class DeleteItem {
    private DatabaseConnector dbConn;
    private Scanner scanner;

    public DeleteItem(DatabaseConnector dbConn) {
        this.dbConn = dbConn;
        this.scanner = new Scanner(System.in);
    }

    public void itemDelete(String chemicalName) {
        try (Connection connection = dbConn.connectToDatabase()) {
            if (connection != null) {
                String query = "SELECT * FROM CurrentInventory WHERE ChemicalName LIKE ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, "%" + chemicalName + "%");

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            do {
                                displayItemDetails(resultSet);

                               
                                
                                
                                //Not Working ATM
                                
                                
                                System.out.print("Type '1' to confirm deletion, '2' to skip: ");
//                                try {
//                                    switch (scanner.nextLine()) {
//                                        case "1":
//                                            deleteConfirmedItem(resultSet.getString("ChemicalName"));
//                                            return;
//                                        case "2":
//                                            System.out.println("Item not deleted");
//                                            return;
//                                    }
//                                } catch (Exception e) {
//                                    System.out.println("Invalid input, item not deleted");
//                                    return;
//                                }
                                
                                deleteConfirmedItem(resultSet.getString("ChemicalName"));
                            } while (resultSet.next());
                        } else {
                            System.out.println("No matching items found");
                        }
                    }
                }
            } else {
                System.out.println("Error: Database connection is null.");
            }
        } catch (SQLException e) {
            LoggerStockX.logger.log(Level.SEVERE, "Error deleting item from inventory", e);
        } finally {
            
        }
    }

    private void displayItemDetails(ResultSet resultSet) throws SQLException {
        System.out.println("Potential match found:");
        System.out.println("ChemicalID: " + resultSet.getInt("InventoryId") +
                ", Chemical Name: " + resultSet.getString("ChemicalName") +
                ", Container Size: " + resultSet.getString("ContainerSize") +
                ", Quantity: " + resultSet.getString("CurrentQuantity"));
    }

    private void deleteConfirmedItem(String chemicalName) {
        try (Connection connection = dbConn.connectToDatabase()) {
            if (connection != null) {
                String deleteQuery = "DELETE FROM CurrentInventory WHERE ChemicalName = ?";

                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                    deleteStatement.setString(1, chemicalName);

                    int rowsAffected = deleteStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Item deleted successfully");
                    } else {
                        System.out.println("Error: Item not found or deletion unsuccessful");
                    }
                }
            } else {
                System.out.println("Error: Database connection is null.");
            }
        } catch (SQLException e) {
            LoggerStockX.logger.log(Level.SEVERE, "Error deleting item from inventory", e);
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
