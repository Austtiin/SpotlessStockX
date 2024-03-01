// Purpose: This class is responsible for searching the inventory.

package mainApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// This class is responsible for searching the inventory
public class searchINV {
    private Map<String, Integer> inventory;

    protected searchINV() {
        this.inventory = new HashMap<>();

        inventory.put("GOLDEN KNIGHT", 20);
        inventory.put("SURFACE ARMOUR", 6);
        inventory.put("HELLCAT", 30);
        inventory.put("BALSAM FROST BURST", 42);
    }

    protected void search(String searchQuery) {
        // Implement search logic here
    }

    public boolean removeInventory(String item) {
        // Implement removeInventory logic here
        return false;
    }

    public void search1(String item) {
        // Implement search1 logic here
    }

    public boolean addInventory(String item, int quantity) {
        // Implement addInventory logic here
        return false;
    }
}