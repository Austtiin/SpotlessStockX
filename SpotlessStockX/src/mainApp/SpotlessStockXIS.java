package mainApp;

import java.util.Scanner;

public class SpotlessStockXIS {
    private Scanner INP; // Scanner Class
    private searchINV SI; // Search Class
    private stockShow SS; //Show Stock
    
    public SpotlessStockXIS() { //Start Scanner - IN
        this.INP = new Scanner(System.in);
        
    }

    public void run() { // Start System
        System.out.println("Welcome to SpotlessStockX - Your go-to chemical inventory system!");
        Inventory(); //Call System Start
        INP.close();//Close Scanner Class
    }

    private void  Inventory() { //Main Menu
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
            	// Option 5 - View Delivery Sites - Menu TODO
            	break;
            case 6:
            	//Option 6 - Export BOL PDF - TODO Implement PDF option - Possibly move to submenu 
            	break;
            default:
                System.out.println("Lets try that again. ");
        }
    }

    private void stockDelete() {
		// TODO
    	System.out.println("==== Delete Stock ====");
	}

	private void stockUpdate() {
		// TODO
		System.out.println("==== Update Stock ====");
	}

	private void itemAdd() {
		System.out.println("==== Add Stock Item ====");
        // TODO grab  from the user and update inventory data structure.
		
		//grab quant - any info aout chems
        System.out.println("Enter details to add a new item:");
        System.out.println("Item added successfully!");
    }

    private void stockCheck() {
    	System.out.println("==== Check Stock levels ====");
        //TODO  Implement the logic to check the stock of an item
        // This could include displaying the current quantity of a specific item.
        System.out.println("Enter the item name to check stock:");
        String itemName = INP.nextLine();
        // Retrieve and display the stock of the specified item
        System.out.println("Stock of " + itemName + ": 10 units");
    }

	public searchINV getSI() {
		return SI;
	}

	public void setSI(searchINV sI) {
		SI = sI;
	}

    //Addi. Information
}
