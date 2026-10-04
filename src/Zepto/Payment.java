package Zepto;

/**
 * Payment.java
 * Interface for all payment methods.
 * Demonstrates: Abstraction + Interface-based Payment Processing + Runtime Polymorphism
 * (different payment classes implement this differently, chosen at runtime).
 */
public interface Payment {

    // Every payment method must validate before processing
    boolean validatePayment();

    // Every payment method must implement its own processing logic
    void processPayment(double amount);

    // Common default method - all payment types share a receipt-style confirmation
    default void printReceiptHeader() {
        System.out.println("---------------------------------------------");
        System.out.println("           ZEPTO - PAYMENT RECEIPT           ");
        System.out.println("---------------------------------------------");
    }
}
