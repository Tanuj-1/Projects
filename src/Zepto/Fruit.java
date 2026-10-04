package Zepto;
/**
 * Fruit.java
 * Concrete child class of Product.
 * Demonstrates: Inheritance, Constructor Chaining (super), Method Overriding.
 */
public class Fruit extends Product {

    private boolean isOrganic;

    // Constructor chaining via super() -> calls Product's full constructor
    public Fruit(int productId, String productName, double price, int quantity, boolean isOrganic) {
        super(productId, productName, price, quantity);
        this.isOrganic = isOrganic;
    }

    // Overloaded constructor - chains to the constructor above using this()
    public Fruit(int productId, String productName, double price, int quantity) {
        this(productId, productName, price, quantity, false); // default: not organic
    }

    @Override
    public double calculateTotalPrice() {
        double total = getPrice() * getQuantity();
        // Organic fruits get a 5% premium
        if (isOrganic) {
            total += total * 0.05;
        }
        return total;
    }

    @Override
    public String getCategory() {
        return "Fruit";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + (isOrganic ? " | Organic: Yes" : " | Organic: No");
    }

    public boolean isOrganic() {
        return isOrganic;
    }
}
