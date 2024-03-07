// Purpose: This class is responsible for searching the inventory.
package mainApp;

import java.util.HashMap;
import java.util.Map;

public class SearchInventory {
    private Map<String, Integer> inventory;

    public SearchInventory() {
        this.inventory = new HashMap<>();

        inventory.put("GOLDEN KNIGHT", 20);
        inventory.put("SURFACE ARMOUR", 6);
        inventory.put("HELLCAT", 30);
        inventory.put("BALSAM FROST BURST", 42);
    }

    public void search(String item) {
        // Implement search logic here
    }

    public boolean removeInventory(String item) {
        // Implement removeInventory logic here (possibly move to DatabaseConnector)
        return false;
    }

    public void search1(String item) {
        // Implement search1 logic here
    }

    public boolean addInventory(String item, int quantity) {
        // Implement addInventory logic here (possibly move to DatabaseConnector)
        return false;
    }
}
