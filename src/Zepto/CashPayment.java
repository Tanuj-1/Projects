package Zepto;

/**
 * CashPayment.java
 * Concrete implementation of the Payment interface for Cash on Delivery.
 * Demonstrates: Interface implementation + Runtime Polymorphism (via Payment reference).
 */
public class CashPayment implements Payment {

    private double cashTendered;

    public CashPayment(double cashTendered) {
        this.cashTendered = cashTendered;
    }

    @Override
    public boolean validatePayment() {
        return cashTendered > 0;
    }

    @Override
    public void processPayment(double amount) {
        printReceiptHeader();
        if (!validatePayment() || cashTendered < amount) {
            System.out.println("Payment Failed: Insufficient cash tendered.");
            return;
        }
        double change = cashTendered - amount;
        System.out.println("Payment Mode : Cash on Delivery");
        System.out.printf("Amount Paid  : Rs.%.2f%n", cashTendered);
        System.out.printf("Bill Amount  : Rs.%.2f%n", amount);
        System.out.printf("Change Due   : Rs.%.2f%n", change);
        System.out.println("Status       : SUCCESS");
        System.out.println("---------------------------------------------");
    }
}

