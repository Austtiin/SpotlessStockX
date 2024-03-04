package mainApp;

import java.sql.Connection;

// Purpose: Main application class for SpotlessStockXIS.

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import logger.loggerStockX;

public class SpotlessStockXIS {
    private Scanner INP;
    private searchINV SI;
    private stockShow SS;
    private List<SalesTransaction> salesTransactions;

    // Database connection parameters for MySQL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://myservice-stockx-rasmussen-stockx-ajs.a.aivencloud.com:3306/your_database_name?useSSL=true&requireSSL=true";
    private static final String DB_USER = "avnadmin";
    private static final String DB_PASSWORD = "AVNS_uYYq-8I32N-sLAwgIO0";
    private static final String SSL_CA_PATH = "C:\\Users\\Austin\\Downloads\\ca.pem";
    
    // Constructor
    public SpotlessStockXIS() {
        this.INP = new Scanner(System.in);
        this.salesTransactions = new ArrayList<>();
    }

    public void run() {
        try {
            loggerStockX.logger.info("SpotlessStockXIS Application Started.");
            System.out.println("Welcome to SpotlessStockX - Your go-to chemical inventory system!");
            Inventory();
        } catch (Exception e) {
            loggerStockX.logger.log(Level.SEVERE, "Error in SpotlessStockXIS application", e);
        } finally {
            INP.close();
        }
    }

    // Inventory method
    private void Inventory() {
        SI = new searchINV();
        while (true) {
            loggerStockX.logger.info("SpotlessStockXIS Menu Accessed.");
            System.out.println("==== Main Menu ====");
            System.out.println("Select an option:");
            System.out.println("1. Add Item");
            System.out.println("2. Check Stock");
            System.out.println("3. Update Stock Information");
            System.out.println("4. Delete Stock Item");
            System.out.println("5. View Delivery Sites");
            System.out.println("6. Export BOL Report");

            int choice = INP.nextInt();
            INP.nextLine();

            switch (choice) {
                case 1:
                    itemAdd();
                    break;
                case 2:
                    stockCheck();
                    break;
                case 3:
                    stockUpdate();
                    break;
                case 4:
                    stockDelete();
                    break;
                case 5:
                    sitesView();
                    break;
                case 6:
                    exportBOL();
                    break;
                case 7:
                    // Option 7 - Search Inventory
                    break;
                default:
                    System.out.println("Try Again ");
            }
        }
    }

    private void exportBOL() {
        // TODO Implement Export BOL
        System.out.println("==== Export BOL Report ====");
    }

    private void sitesView() {
        // TODO Implement Site View - Waiting for DB
        System.out.println("==== View Delivery Sites ====");
    }

    private void stockDelete() {
        System.out.println("==== Delete Stock ====");
        try {
            boolean valid = false;

            System.out.println("Enter the item name to delete:");
            String item = INP.nextLine();

            if (SI.removeInventory(item)) {
                valid = true;
                System.out.println("Item deleted successfully!");
            } else {
                valid = false;
                System.out.println("Try Again, Item NOT deleted successfully!");
            }

            valid = false;
        } catch (Exception e) {
            loggerStockX.logger.log(Level.SEVERE, "Error in stockDelete method", e);
        }
    }

    private void stockUpdate() {
        // TODO implement Update Stock
        System.out.println("==== Update Stock ====");
    }

    private void itemAdd() {
        System.out.println("==== Add Stock Item ====");
        try {
            System.out.println("Enter details to add a new item:");
            System.out.println("Enter the item name:");
            String item = INP.nextLine();
            System.out.println("Enter the quantity:");
            int quantity = INP.nextInt();
            INP.nextLine();

            // Get a database connection
            Connection connection = connectToDatabase();

            // Check if the connection is successful
            if (connection != null) {
                try {
                   // Create a SQL statement
                    String sql = "INSERT INTO your_table_name (item_name, quantity) VALUES (?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setString(1, item);
                        preparedStatement.setInt(2, quantity);

                        // Execute the SQL statement
                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Item added successfully!");
                        } else {
                            System.out.println("Item NOT added successfully!");
                        }
                    }
                } catch (SQLException e) {
                    loggerStockX.logger.log(Level.SEVERE, "Error executing SQL statement", e);
                } finally {
                    // Close the database connection
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        loggerStockX.logger.log(Level.SEVERE, "Error closing database connection", e);
                    }
                }
            } else {
                System.out.println("Failed Connection.");
            }

        } catch (Exception e) {
            loggerStockX.logger.log(Level.SEVERE, "itemAdd Error", e);
        }
    }

 // Stock Check method
    private void stockCheck() {
        System.out.println("==== Check Stock levels ====");
        try {
            System.out.println("Enter the item name to search:");
            String item = INP.nextLine();
            SI.search(item);
        } catch (Exception e) {
            loggerStockX.logger.log(Level.SEVERE, "Error in stockCheck", e);
        }
    }

    // Main method
    public searchINV getSI() {
        return SI;
    }

    	// Add Sales Transaction method
    public void addSalesTransaction(String customerName, String itemName, int quantity, double totalPrice) {
        SalesTransaction transaction = new SalesTransaction(customerName, itemName, quantity, totalPrice);
        salesTransactions.add(transaction);
        System.out.println("Sale added!");
    }

    	// View Sales Transactions method
    public void viewSalesTransactions() {
        for (SalesTransaction transaction : salesTransactions) {
            System.out.println("Transaction Date: " + transaction.getTransactionDate());
            System.out.println("Customer: " + transaction.getCustomerName());
            System.out.println("Item: " + transaction.getItemName());
            System.out.println("Quantity: " + transaction.getQuantity());
            System.out.println("Total Price: $" + transaction.getTotalPrice());
            System.out.println("----------------------------");
        }
    }

    // Database connection method
    private Connection connectToDatabase() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            loggerStockX.logger.log(Level.SEVERE, "Connected to DB successfully.");
        } catch (ClassNotFoundException | SQLException e) {
        	loggerStockX.logger.log(Level.SEVERE, "Error connecting to the DB", e);
        }
        System.out.println("DB Conntection Started");
        return connection;
    }

    	// Main method
    public static void main(String[] args) {
        SpotlessStockXIS stockXIS = new SpotlessStockXIS();
        Connection connection = stockXIS.connectToDatabase();
        

        // Close the database connection
        try {
            if (connection != null) {
                connection.close();
                System.out.println("DB Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
