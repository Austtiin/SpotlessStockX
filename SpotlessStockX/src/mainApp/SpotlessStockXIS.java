// Purpose: Main application class for SpotlessStockXIS.

package mainApp;

import java.util.Scanner;
import java.util.logging.Level;
import logger.loggerStockX;

public class SpotlessStockXIS {
    private Scanner INP;
    private searchINV SI;
    private stockShow SS;

    public SpotlessStockXIS() {
        this.INP = new Scanner(System.in);
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
}