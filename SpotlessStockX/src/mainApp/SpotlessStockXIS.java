package mainApp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import logger.LoggerStockX;

public class SpotlessStockXIS {
    private Scanner scanner;
    private StockShow stockShow;
    private DatabaseConnector databaseConnector;
    private List<SalesTransaction> salesTransactions;

    public SpotlessStockXIS() {
        this.scanner = new Scanner(System.in);
        this.databaseConnector = new DatabaseConnector();
        this.stockShow = new StockShow(databaseConnector);
        this.salesTransactions = new ArrayList<>();
    }

    public void run() {
        try {
            LoggerStockX.logger.info("SpotlessStockXIS Application Started.");
            System.out.println("Welcome to SpotlessStockX - Your go-to chemical inventory system!");
            inventoryManager();
        } catch (Exception e) {
            LoggerStockX.logger.log(Level.SEVERE, "Error in SpotlessStockXIS application", e);
        } finally {
            scanner.close();
        }
    }

    private void inventoryManager() {
        try {
            while (true) {
                LoggerStockX.logger.info("SpotlessStockXIS Menu Accessed.");
                System.out.println("==== Main Menu ====");
                System.out.println("Select an option:");
                System.out.println("1. Add Item");
                System.out.println("2. Check Stock");
                System.out.println("3. Update Stock Information");
                System.out.println("4. Delete Stock Item");
                System.out.println("5. View Delivery Sites");
                System.out.println("6. Export BOL Report");
                System.out.println("7. Search Inventory");

                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

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
                            searchInventory();
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("Error reading input: " + e.getMessage());
		} finally {
			//scanner.close();
		}
    }




    private void itemAdd() {
        LoggerStockX.logger.info("==== Add Stock Item ====");
        try {
            System.out.println("Enter details to add a new item:");
            System.out.println("Enter the item name:");
            String item = scanner.nextLine();
            System.out.println("Enter the quantity:");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            databaseConnector.addItem(item, quantity);
        } catch (Exception e) {
            LoggerStockX.logger.log(Level.SEVERE, "itemAdd Error", e);
        }
    }

    private void stockCheck() {
        try {
            boolean exit = false;

            while (!exit) {
                System.out.println("==== Check Stock levels ====");
                System.out.println("Select an option:");
                System.out.println("1. Show All Stock Levels");
                System.out.println("2. Show 5 Gallon Stock Levels");
                System.out.println("3. Show 15 Gallon Stock Levels");
                System.out.println("4. Show 30 Gallon Stock Levels");
                System.out.println("5. Show 55 Gallon Stock Levels");
                System.out.println("6. Exit");

                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                        	System.out.println("Checking Stock Levels...");
                        	LoggerStockX.logger.info("Checking Stock Levels...");
                            stockShow.showStock(databaseConnector);
                            break;
                        case 2:
                        	System.out.println("Checking 5 Gallon Stock Levels...");
                        	LoggerStockX.logger.info("Checking 5 Gallon Stock Levels...");
                        	stockShow.perGallonShow(5);
                            break;
                        case 3:
                        	System.out.println("Checking 15 Gallon Stock Levels...");
                        	LoggerStockX.logger.info("Checking 15 Gallon Stock Levels...");
                        	stockShow.perGallonShow(15);
                            break;
                        case 4:
                        	System.out.println("Checking 30 Gallon Stock Levels...");
                        	LoggerStockX.logger.info("Checking 30 Gallon Stock Levels...");
                        	stockShow.perGallonShow(30);
                            break;
                        case 5:
                        	System.out.println("Checking 55 Gallon Stock Levels...");
                        	LoggerStockX.logger.info("Checking 55 Gallon Stock Levels...");
                        	stockShow.perGallonShow(55);
                            break;
                        case 6:
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }

                    if (choice == 6) {
                        exit = true; // Exit the loop if the user chooses option 6
                    }
                } else {
                	// Consume the invalid input
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine();
                }
            }
            //scanner.close();
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("Error reading input: " + e.getMessage());
        } finally {
            //scanner.close();  // <-- This line is causing the issue
        }
    }



    private void stockUpdate() {
        LoggerStockX.logger.info("==== Update Stock ====");
        // TODO implement Update Stock
    }

    private void stockDelete() {
        LoggerStockX.logger.info("==== Delete Stock ====");
        boolean valid = false;

        System.out.println("Enter the item name to delete:");
        String item = scanner.nextLine();

        //if (databaseConnector.removeInventory(item)) {
        //    valid = true;
       //     System.out.println("Item deleted successfully!");
       // } else {
       //     valid = false;
      //      System.out.println("Try Again, Item NOT deleted successfully!");
      //  }

        valid = false;
    }


    private void sitesView() {
        LoggerStockX.logger.info("==== View Delivery Sites ====");
        // TODO Implement Site View - Waiting for DB

    }




    private void exportBOL() {
        LoggerStockX.logger.info("==== Export BOL Report ====");
        // TODO Implement Export BOL
    }




    private void searchInventory() {
        LoggerStockX.logger.info("==== Search Inventory ====");
        // TODO: Implement Search Inventory logic
    }





    public void addSalesTransaction(String customerName, String itemName, int quantity, double totalPrice) {
        SalesTransaction transaction = new SalesTransaction(customerName, itemName, quantity, totalPrice);
        salesTransactions.add(transaction);
        System.out.println("Sale added!");
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

    public static void main(String[] args) {
        SpotlessStockXIS stockXIS = new SpotlessStockXIS();
        stockXIS.run();
    }
}
