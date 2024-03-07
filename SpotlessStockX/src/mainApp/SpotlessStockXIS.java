package mainApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import logger.LoggerStockX;

public class SpotlessStockXIS {
    private Scanner scanner;
    private SearchInventory inventoryManager;
    private StockShow stockShow;
    private DatabaseConnector databaseConnector;
    private List<SalesTransaction> salesTransactions;

    public SpotlessStockXIS() {
        this.scanner = new Scanner(System.in);
        this.inventoryManager = new SearchInventory();
        this.stockShow = new StockShow();
        this.databaseConnector = new DatabaseConnector();
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

            int choice = scanner.nextInt();
            scanner.nextLine();

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
                    System.out.println("Try Again ");
            }
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
    	System.out.println("==== Check Stock levels ====");
        LoggerStockX.logger.info("Checking Stock Levels...");
        stockShow.showStock();
        
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

        if (databaseConnector.removeInventory(item)) {
            valid = true;
            System.out.println("Item deleted successfully!");
        } else {
            valid = false;
            System.out.println("Try Again, Item NOT deleted successfully!");
        }

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
