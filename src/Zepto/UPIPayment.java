package Zepto;

/**
 * UPIPayment.java
 * Concrete implementation of the Payment interface for UPI transactions.
 * Demonstrates: Interface implementation + Runtime Polymorphism (via Payment reference).
 */
public class UPIPayment implements Payment {

    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean validatePayment() {
        // Simple validation: UPI ID must contain '@'
        return upiId != null && upiId.contains("@");
    }

    @Override
    public void processPayment(double amount) {
        printReceiptHeader();
        if (!validatePayment()) {
            System.out.println("Payment Failed: Invalid UPI ID (must contain '@').");
            return;
        }
        System.out.println("Payment Mode : UPI");
        System.out.println("UPI ID       : " + upiId);
        System.out.printf("Amount Paid  : Rs.%.2f%n", amount);
        System.out.println("Status       : SUCCESS");
        System.out.println("---------------------------------------------");
    }
}
