package Zepto;

/**
 * CardPayment.java
 * Concrete implementation of the Payment interface for Debit/Credit Card.
 * Demonstrates: Interface implementation + Runtime Polymorphism (via Payment reference).
 */
public class CardPayment implements Payment {

    private String cardNumber;
    private String cardHolderName;

    public CardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public boolean validatePayment() {
        // Simple validation: card number should be 16 digits
        return cardNumber != null && cardNumber.replaceAll("\\s", "").length() == 16;
    }

    @Override
    public void processPayment(double amount) {
        printReceiptHeader();
        if (!validatePayment()) {
            System.out.println("Payment Failed: Invalid card number (must be 16 digits).");
            return;
        }
        String masked = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        System.out.println("Payment Mode : Debit/Credit Card");
        System.out.println("Card Holder  : " + cardHolderName);
        System.out.println("Card Number  : " + masked);
        System.out.printf("Amount Paid  : Rs.%.2f%n", amount);
        System.out.println("Status       : SUCCESS");
        System.out.println("---------------------------------------------");
    }
}
