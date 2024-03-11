// Purpose: Main application class for the SpotlessStockXIS application.
// Description: This class is the main application class for the SpotlessStockXIS application. 
//It contains the main menu and the logic for each menu option.

package mainApp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;

import logger.LoggerStockX;

public class SpotlessStockXIS {
    private DatabaseConnector databaseConnector;
    private StockShow stockShow;
    private AddItem addItem;
    private Scanner scanner;
    private DeleteItem deleteItem;
    private List<SalesTransaction> salesTransactions;

    public SpotlessStockXIS() {
        this.databaseConnector = new DatabaseConnector();
        this.stockShow = new StockShow(databaseConnector);
        this.addItem = new AddItem(databaseConnector);
        this.scanner = new Scanner(System.in); // Use a single scanner
        this.deleteItem = new DeleteItem(databaseConnector);
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
            // Close the scanner in the finally block
            if (scanner != null) {
                scanner.close();
            }
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
                    scanner.nextLine(); // Consume the newline character after reading the integer

                    if (choice >= 1 && choice <= 7) {
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
                                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                                break;
                        }
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            LoggerStockX.logger.log(Level.SEVERE, "Error reading input: " + e.getMessage(), e);
        }
    }




    private void itemAdd() {
        LoggerStockX.logger.info("==== Add Stock Item ====");
        try {
            boolean exit = false;

            while (!exit) {
                System.out.println("==== Adding Item menu ====");
                System.out.println("Select an option:");
                System.out.println("1. Show Current Inventory");
                System.out.println("2. Add Item to Inventory");
                System.out.println("3. Exit");

                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.println("Checking Current Inventory...");
                            LoggerStockX.logger.info("Checking Current Inventory...");
                            addItem.ShowCurrent();
                            break;
                        case 2:
                            System.out.println("Please Type the Chemical Name:");
                            String chemicalName = scanner.nextLine();
                            System.out.println("Please Type the Container Size: (5, 15, 30, 55)");
                            String containerSize = scanner.nextLine();

                            if (containerSize.equals("5") || containerSize.equals("15")
                                    || containerSize.equals("30") || containerSize.equals("55")) {
                                System.out.println("Please Type the Current Inventory:");
                                String currentInventory = scanner.nextLine();
                                addItem.itemAdd(chemicalName, containerSize, currentInventory);
                            } else {
                                System.out.println("Invalid Container Size. Please try again.");
                                break;
                            }

                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }

                    if (choice == 3) {
                        exit = true; // Exit the loop if the user chooses option 3
                    }
                } else {
                    // Consume the invalid input
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine();
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            LoggerStockX.logger.log(Level.SEVERE, "Error reading input: " + e.getMessage(), e);
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
                            stockShow.showStock();
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
                        exit = true;
                    }
                } else {
                    // Consume the invalid input
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine();
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            LoggerStockX.logger.log(Level.SEVERE, "Error reading input: " + e.getMessage(), e);
        }
    }

    private void stockUpdate() {
        LoggerStockX.logger.info("==== Update Stock ====");
        // TODO: Implement Update Stock
        
        
    }

    public void stockDelete() {
        LoggerStockX.logger.info("==== Delete Stock ====");
        System.out.println("Enter the item name to delete:");

        try {
            String chemicalName = scanner.nextLine().trim();

            if (!chemicalName.isEmpty()) {
                DeleteItem deleteItem = new DeleteItem(databaseConnector);
                deleteItem.itemDelete(chemicalName);
            } else {
                System.out.println("Item name cannot be empty, Try again.");
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            LoggerStockX.logger.log(Level.SEVERE, "Error reading input:: " + e.getMessage(), e);
        }
    }

        
   

    private void sitesView() {
        LoggerStockX.logger.info("==== View Delivery Sites ====");
        // TODO: Implement Site View - Waiting for DB
    }

    private void exportBOL() {
        LoggerStockX.logger.info("==== Export BOL Report ====");
        // TODO: Implement Export BOL
    }

    private void searchInventory() {
        LoggerStockX.logger.info("==== Search Inventory ====");
        // TODO: Implement Search Inventory logic
    }

    private void viewSalesTransactions() {
        System.out.println("==== View Sales Transactions ====");
        // TODO: Implement View Sales Transactions
    }

    private void exitApplication() {
        System.out.println("Exiting SpotlessStockXIS Application. Goodbye!");
        System.exit(0);
    }

    public static void main(String[] args) {
        SpotlessStockXIS stockXIS = new SpotlessStockXIS();
        stockXIS.run();
    }
}
