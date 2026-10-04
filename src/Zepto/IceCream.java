package Zepto;
/**
 * IceCream.java
 * Concrete child class of Product.
 * Demonstrates: Inheritance, Constructor Chaining (super), Method Overriding.
 */
public class IceCream extends Product {

    private String flavor;

    public IceCream(int productId, String productName, double price, int quantity, String flavor) {
        super(productId, productName, price, quantity);
        this.flavor = flavor;
    }

    // Constructor chaining using this()
    public IceCream(int productId, String productName, double price, int quantity) {
        this(productId, productName, price, quantity, "Vanilla");
    }

    @Override
    public double calculateTotalPrice() {
        // Ice cream has a mandatory Rs.5 cold-storage handling fee per unit
        double total = getPrice() * getQuantity();
        total += 5 * getQuantity();
        return total;
    }

    @Override
    public String getCategory() {
        return "IceCream";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Flavor: " + flavor;
    }

    public String getFlavor() {
        return flavor;
    }
}
