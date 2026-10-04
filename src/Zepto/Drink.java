package Zepto;
/**
 * Drink.java
 * Concrete child class of Product.
 * Demonstrates: Inheritance, Constructor Chaining (super), Method Overriding.
 */
public class Drink extends Product {

    private boolean isChilled;

    public Drink(int productId, String productName, double price, int quantity, boolean isChilled) {
        super(productId, productName, price, quantity);
        this.isChilled = isChilled;
    }

    // Constructor chaining using this()
    public Drink(int productId, String productName, double price, int quantity) {
        this(productId, productName, price, quantity, true); // default: chilled
    }

    @Override
    public double calculateTotalPrice() {
        double total = getPrice() * getQuantity();
        // Chilled drinks cost a small extra Rs.2 per unit (cooling charge)
        if (isChilled) {
            total += 2 * getQuantity();
        }
        return total;
    }

    @Override
    public String getCategory() {
        return "Drink";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + (isChilled ? " | Chilled: Yes" : " | Chilled: No");
    }

    public boolean isChilled() {
        return isChilled;
    }
}
