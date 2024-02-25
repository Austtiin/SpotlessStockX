package mainApp;

import java.util.HashMap;
import java.util.Map;

public class searchINV {

    private Map<String, Integer> inventory;

    public searchINV() {
    		//implement DB connection / pass in input from user
        this.inventory = new HashMap<>();
        // Populate inventory for testing
        inventory.put("ItemA", 20);
        inventory.put("ItemB", 15);
        inventory.put("ItemC", 30);
    }

    public void search() {
        // Implement the logic for searching the inventory
        // Display the results to the user.
        System.out.println("Enter the item name to search:");
        String itemName = //  get user input or any other way you want to get the item name
        if (inventory.containsKey(itemName)) {
            System.out.println("Stock of " + itemName + ": " + inventory.get(itemName) + " units");
        } else {
            System.out.println("Item not found in the inventory.");
        }
    }
}