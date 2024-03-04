package mainApp;

import java.util.Date;

public class SalesTransaction { // SalesTransaction class
    private String customerName; // Customer name
    private String itemName; // Item name
    private int quantity; // Quantity of items
    private double totalPrice; // Total price of the transaction
    private Date transactionDate; // Date of the transaction

    public SalesTransaction(String customerName, String itemName, int quantity, double totalPrice) {
        this.customerName = customerName;
        this.itemName = itemName; // Item name
        this.quantity = quantity; // Quantity of items
        this.totalPrice = totalPrice; // Total price of the transaction
        this.transactionDate = new Date(); // Date of the transaction
    }

   
    public String getCustomerName() {
        return customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
}