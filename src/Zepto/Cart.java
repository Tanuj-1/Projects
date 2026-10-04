package Zepto;

import java.util.ArrayList;

/**
 * Cart.java
 * Manages the shopping cart using an ArrayList of Product references.
 * Demonstrates: ArrayList-based Cart Management, Interface Implementation,
 * Runtime Polymorphism (calculateTotalPrice()/getDetails() resolved at runtime
 * based on the actual object type stored in the ArrayList<Product>).
 */
public class Cart implements CartOperations {

    // Polymorphic collection: holds Fruit, Vegetable, Drink, Fashion, IceCream objects
    private ArrayList<Product> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    @Override
    public void addToCart(Product product) {
        cartItems.add(product);
        System.out.println("[Added] " + product.getProductName() + " -> added to cart successfully.");
    }

    @Override
    public boolean removeFromCart(int productId) {
        for (Product p : cartItems) {
            if (p.getProductId() == productId) {
                cartItems.remove(p);
                System.out.println("[Removed] " + p.getProductName() + " -> removed from cart.");
                return true;
            }
        }
        System.out.println("[Error] Product with ID " + productId + " not found in cart.");
        return false;
    }

    @Override
    public void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("\n================= YOUR CART =================");
        // Runtime Polymorphism: p.getDetails() calls the OVERRIDDEN version
        // in Fruit/Vegetable/Drink/Fashion/IceCream depending on actual object type
        for (Product p : cartItems) {
            System.out.println(p.getDetails());
        }
        System.out.println("===============================================");
    }

    @Override
    public double calculateTotal() {
        double total = 0.0;
        for (Product p : cartItems) {
            // Runtime Polymorphism: calculateTotalPrice() resolved per actual object
            total += p.calculateTotalPrice();
        }
        return total;
    }

    @Override
    public void generateBill(User user) {
        if (cartItems.isEmpty()) {
            System.out.println("Cannot generate bill: Cart is empty.");
            return;
        }

        double subTotal = calculateTotal();
        double deliveryCharge = subTotal >= 199 ? 0 : 25;
        double tax = subTotal * 0.02; // 2% platform tax
        double grandTotal = subTotal + deliveryCharge + tax;

        System.out.println("\n=========== ZEPTO - FINAL BILL ===========");
        System.out.println(user.toString());
        System.out.println("-------------------------------------------");
        for (Product p : cartItems) {
            System.out.println(p.getDetails());
        }
        System.out.println("-------------------------------------------");
        System.out.printf("Sub Total       : Rs.%.2f%n", subTotal);
        System.out.printf("Delivery Charge : Rs.%.2f%n", deliveryCharge);
        System.out.printf("Platform Tax(2%%): Rs.%.2f%n", tax);
        System.out.println("-------------------------------------------");
        System.out.printf("GRAND TOTAL     : Rs.%.2f%n", grandTotal);
        System.out.println("=============================================\n");
    }

    public ArrayList<Product> getCartItems() {
        return cartItems;
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}
