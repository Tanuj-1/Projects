package Zepto;

/**
 * CartOperations.java
 * Interface defining the contract for cart management.
 * Implemented by the Cart class.
 */
public interface CartOperations {

    void addToCart(Product product);

    boolean removeFromCart(int productId);

    void viewCart();

    double calculateTotal();

    void generateBill(User user);
}

