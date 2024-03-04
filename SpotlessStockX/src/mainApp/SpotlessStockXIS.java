package mainApp;

import java.sql.Connection;

// Purpose: Main application class for SpotlessStockXIS.

import java.sql.DriverManager;
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
                    System.out.println("Let's try that again. ");
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
            boolean valid = false;

            System.out.println("Enter details to add a new item:");
            System.out.println("Enter the item name:");
            String item = INP.nextLine();
            System.out.println("Enter the quantity:");
            int quantity = INP.nextInt();
            INP.nextLine();

            if (SI.addInventory(item, quantity)) {
                valid = true;
                System.out.println("Item added successfully!");
            } else {
                valid = false;
                System.out.println("Try Again, Item NOT added successfully!");
            }

            valid = false;
        } catch (Exception e) {
            loggerStockX.logger.log(Level.SEVERE, "Error in itemAdd method", e);
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
            loggerStockX.logger.log(Level.SEVERE, "Error in stockCheck method", e);
        }
    }

    // Main method
    public searchINV getSI() {
        return SI;
    }

    public void addSalesTransaction(String customerName, String itemName, int quantity, double totalPrice) {
        SalesTransaction transaction = new SalesTransaction(customerName, itemName, quantity, totalPrice);
        salesTransactions.add(transaction);
        System.out.println("Sales transaction added successfully!");
    }

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

    // Method to establish a database connection
    private Connection connectToDatabase() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Connected to the database!");
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, "Error connecting to the database", e);
        }
        return connection;
    }

    public static void main(String[] args) {
        SpotlessStockXIS stockXIS = new SpotlessStockXIS();
        Connection connection = stockXIS.connectToDatabase();

        // Perform database operations using the 'connection' object

        // Close the connection when done
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
