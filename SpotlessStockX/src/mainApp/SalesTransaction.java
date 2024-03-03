package mainApp;

import java.util.Date;

public class SalesTransaction {
    private String customerName;
    private String itemName;
    private int quantity;
    private double totalPrice;
    private Date transactionDate;

    public SalesTransaction(String customerName, String itemName, int quantity, double totalPrice) {
        this.customerName = customerName;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.transactionDate = new Date(); // Capture the current date and time
    }

    // Getters for transaction details
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