package Zepto;

/**
 * Vegetable.java
 * Concrete child class of Product.
 * Demonstrates: Inheritance, Constructor Chaining (super), Method Overriding.
 */
public class Vegetable extends Product {

    private boolean isFresh;

    public Vegetable(int productId, String productName, double price, int quantity, boolean isFresh) {
        super(productId, productName, price, quantity);
        this.isFresh = isFresh;
    }

    // Constructor chaining using this()
    public Vegetable(int productId, String productName, double price, int quantity) {
        this(productId, productName, price, quantity, true); // default: fresh
    }

    @Override
    public double calculateTotalPrice() {
        double total = getPrice() * getQuantity();
        // Non-fresh vegetables get a 10% discount
        if (!isFresh) {
            total -= total * 0.10;
        }
        return total;
    }

    @Override
    public String getCategory() {
        return "Vegetable";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + (isFresh ? " | Fresh: Yes" : " | Fresh: No");
    }

    public boolean isFresh() {
        return isFresh;
    }
}
