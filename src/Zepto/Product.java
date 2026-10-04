package Zepto;
/**
 * Product.java
 * Abstract class representing a generic product in the Zepto-inspired store.
 * Demonstrates: Abstraction, Encapsulation, Constructor Chaining, base for Runtime Polymorphism.
 */
public abstract class Product {

    // Encapsulation: private fields, accessed only via getters/setters
    private int productId;
    private String productName;
    private double price;
    private int quantity;

    // Constructor 1 - full constructor
    public Product(int productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    // Constructor 2 - overloaded, chains to Constructor 1 using this()
    // Demonstrates Constructor Chaining within the same class
    public Product(int productId, String productName, double price) {
        this(productId, productName, price, 1); // default quantity = 1
    }

    // Abstract methods - MUST be implemented by every child class
    // This enforces Abstraction and enables Runtime Polymorphism
    public abstract double calculateTotalPrice();

    public abstract String getCategory();

    // Concrete method - can be overridden by children (Method Overriding)
    public String getDetails() {
        return String.format(
                "ID: %-4d | Name: %-15s | Category: %-10s | Price: Rs.%-8.2f | Qty: %-3d | Total: Rs.%.2f",
                productId, productName, getCategory(), price, quantity, calculateTotalPrice()
        );
    }

    // Encapsulation: Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return getDetails();
    }
}
