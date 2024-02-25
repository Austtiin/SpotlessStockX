// Description: Main Application for SpotlessStockXIS
// Purpose: Main Application for SpotlessStockXIS
// This class runs the instance of the SpotlessStockXIS class.
// Austin Stephens
// 02/17/2024
// Professor Kumar
// Advanced Java Programming
// COP3805C


package mainApp;

import java.lang.System.Logger.Level;
import java.util.Scanner;
import logger.loggerStockX;


public class SpotlessStockXIS {
	private Scanner INP; // Scanner Class
	private searchINV SI; // Search Class
	private stockShow SS; //Show Stock


	public SpotlessStockXIS() { //Start Scanner - 'INP'
		this.INP = new Scanner(System.in);
	}

	public void run() {
		try {
			loggerStockX.logger.info("SpotlessStockXIS Application Started.");
			System.out.println("Welcome to SpotlessStockX - Your go-to chemical inventory system!");
			Inventory(); // Call System Start

		} catch (Exception e) {
			loggerStockX.loggerBAD(Level.ERROR, "Exception: ", e);
		} finally {
			INP.close(); // Close Scanner Class
		}
	}

	
	private void Inventory() { // Main Menu
		SI = new searchINV(); // Start Inventory


		// Main Menu

		while (true) {
			loggerStockX.logger.info("SpotlessStockXIS Menu Acessed.");
			System.out.println("==== Main Menu ====");
			System.out.println("Select an option:");
			System.out.println("1. Add Item");
			System.out.println("2. Check Stock");
			System.out.println("3. Update Stock Information");
			System.out.println("4. Delete Stock Item");
			System.out.println("5. View Delivery Sites");
			System.out.println("6. Export BOL Report");

			int choice = INP.nextInt();//Grab IN
			INP.nextLine();

			switch (choice) {
			case 1:
				//Option 1 - Add Item TO INV
				itemAdd();
				break;
			case 2:
				//Option 2 - Check Stock Levels
				stockCheck();
				break;
			case 3:
				//Option 3 - Update Stock Item
				stockUpdate();
				break;
			case 4:
				//Option 4 - Delete Stock Item
				stockDelete();
				break;
			case 5:
				// Option 5 - View Delivery Sites
				sitesView();
				break;
			case 6:
				exportBOL();
				//Option 6 - Export BOL PDF 
				//TODO Implement PDF option - Possibly move to submenu 
				break;
			case 7:
				// Option 7 - Search Inventory

				break;
			default:
				System.out.println("Lets try that again. ");
			}
		}
	}

	private void exportBOL() {
		// TODO Implement Export BOL
		// Waiting for DB - Would like to make this have site names
		System.out.println("==== Export BOL Report ====");

	}

	private void sitesView() {
		// TODO Implement Site View - Waiting for DB
		System.out.println("==== View Delivery Sites ====");



	}


	private void stockDelete() {
		System.out.println("==== Delete Stock ====");
		try {
			boolean valid = false; // Set to false initially / default

			System.out.println("Enter the item name to delete:");
			String item = INP.nextLine(); // Get Item Name

			if (SI.removeInventory(item)) { // Remove Item from Inventory
				valid = true; // Set to true if successful
				System.out.println("Item deleted successfully!");
			} else {
				valid = false; // Set to false if not successful
				System.out.println("Try Again, Item NOT deleted successfully!");
			}

			valid = false; // Set to false for next use
		} catch (Exception e) {
			loggerStockX.loggerBAD(Level.ERROR, "Exception: ", e);
		}
	}


	private void stockUpdate() {
		// TODO implement Update Stock
		System.out.println("==== Update Stock ====");
	}

	private void itemAdd() {
		System.out.println("==== Add Stock Item ====");
		boolean valid = false; // Set to false initially / default

		System.out.println("Enter details to add a new item:");
		System.out.println("Enter the item name:"); // Get Item Name
		String item = INP.nextLine();
		System.out.println("Enter the quantity:"); // Get Quantity
		int quantity = INP.nextInt();
		INP.nextLine();

		if (SI.addInventory(item, quantity)) { // Add Item to Inventory
			valid = true; // Set to true if successful
		}else {
			valid = false; // Set to false if not successful
		}

		if (!valid){ // If the item is not added
			System.out.println("Try Again, Item NOT added successfully!"); 
			loggerStockX.logger.info("Item NOT added successfully!");
		}else { // If the item is added
			System.out.println("Item added successfully!");
			loggerStockX.logger.info("Item added successfully!");

		}
		valid = false; // Set to false for next use
	}

	private void stockCheck() {
		System.out.println("==== Check Stock levels ====");
		System.out.println("Enter the item name to search:");
		String item = INP.nextLine();
		SI.search(item);
	}

	public searchINV getSI() {
		return SI;
	}



	//
}
