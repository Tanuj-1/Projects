package Zepto;

/**
 * Fashion.java
 * Concrete child class of Product.
 * Demonstrates: Inheritance, Constructor Chaining (super), Method Overriding.
 */
public class Fashion extends Product {

    private String size;

    public Fashion(int productId, String productName, double price, int quantity, String size) {
        super(productId, productName, price, quantity);
        this.size = size;
    }

    // Constructor chaining using this()
    public Fashion(int productId, String productName, double price, int quantity) {
        this(productId, productName, price, quantity, "Free Size");
    }

    @Override
    public double calculateTotalPrice() {
        // Fashion items have a flat 12% GST added
        double total = getPrice() * getQuantity();
        total += total * 0.12;
        return total;
    }

    @Override
    public String getCategory() {
        return "Fashion";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Size: " + size;
    }

    public String getSize() {
        return size;
    }
}
