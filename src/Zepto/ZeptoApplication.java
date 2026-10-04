package Zepto;

/* 
  📌 Resume Line :- 
        Developed a Zepto-inspired console application using Core Java and OOP concepts.
        implemented Encapsulation, Inheritance, Abstraction, and Polymorphism
        with ArrayList-based cart management and interface-based payment processing.

kya kya hoga is project m :- 
          1 Abstract Class (Product)
          2 Interfaces (Payment, CartOperations)
          5 Child Classes (Fruit, Vegetable, Drink, Fashion, IceCream)
          User Class
          Cart Class
          ZeptoApplication (Main)
          ArrayList<Product>
          Runtime Polymorphism
          Constructor Chaining
          Method Overriding
          Add to Cart
          Remove from Cart
          Product Details
          Bill Generation
          Payment
*/

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ZeptoApplication.java
 * Main class - entry point of the Zepto-inspired console application.
 * Ties together Product (Abstraction/Inheritance/Polymorphism), Cart (ArrayList
 * management via CartOperations interface), User, and Payment (interface-based
 * payment processing).
 */
public class ZeptoApplication {

    // Master product catalog available in the store
    private static ArrayList<Product> catalog = new ArrayList<>();
    private static Cart cart = new Cart();
    private static Scanner sc = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {
        loadCatalog();
        System.out.println("=================================================");
        System.out.println("      WELCOME TO ZEPTO - CONSOLE APPLICATION      ");
        System.out.println("=================================================");
        registerUser();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> viewCatalog();
                case 2 -> addToCartFlow();
                case 3 -> removeFromCartFlow();
                case 4 -> cart.viewCart();
                case 5 -> {
                    cart.generateBill(currentUser);
                }
                case 6 -> paymentFlow();
                case 7 -> {
                    System.out.println("Thank you for shopping with Zepto! Goodbye, " + currentUser.getName() + "!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please select between 1-7.");
            }
        }
        sc.close();
    }

    // Load sample products into the catalog (demonstrates all 5 child classes)
    private static void loadCatalog() {
        catalog.add(new Fruit(101, "Apple (1kg)", 120.0, 1, true));
        catalog.add(new Fruit(102, "Banana Dozen", 60.0, 1));
        catalog.add(new Vegetable(201, "Tomato (1kg)", 40.0, 1, true));
        catalog.add(new Vegetable(202, "Potato (1kg)", 30.0, 1));
        catalog.add(new Drink(301, "Coca-Cola 750ml", 45.0, 1, true));
        catalog.add(new Drink(302, "Orange Juice 1L", 99.0, 1));
        catalog.add(new Fashion(401, "Cotton T-Shirt", 499.0, 1, "M"));
        catalog.add(new Fashion(402, "Denim Jeans", 1299.0, 1, "L"));
        catalog.add(new IceCream(501, "Choco Bar", 50.0, 1, "Chocolate"));
        catalog.add(new IceCream(502, "Vanilla Tub 500ml", 199.0, 1));
    }

    private static void registerUser() {
        System.out.println("\n--- Please Register to Continue ---");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        System.out.print("Enter your address: ");
        String address = sc.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = sc.nextLine();
        currentUser = new User(1, name, email, address, phone);
        System.out.println("\nRegistration successful! Welcome, " + name + ".\n");
    }

    private static void printMenu() {
        System.out.println("\n-------------- MAIN MENU --------------");
        System.out.println("1. View Product Catalog / Details");
        System.out.println("2. Add Product to Cart");
        System.out.println("3. Remove Product from Cart");
        System.out.println("4. View Cart");
        System.out.println("5. Generate Bill");
        System.out.println("6. Make Payment");
        System.out.println("7. Exit");
        System.out.println("----------------------------------------");
    }

    private static void viewCatalog() {
        System.out.println("\n============ PRODUCT CATALOG ============");
        // Runtime Polymorphism: each product prints its own overridden getDetails()
        for (Product p : catalog) {
            System.out.println(p.getDetails());
        }
        System.out.println("==========================================");
    }

    private static void addToCartFlow() {
        viewCatalog();
        int id = readInt("Enter Product ID to add to cart: ");
        int qty = readInt("Enter quantity: ");
        Product found = findProductById(id);
        if (found == null) {
            System.out.println("Product not found in catalog.");
            return;
        }
        // Create a fresh product instance of the same runtime type/category with chosen qty
        Product cartProduct = createCartCopy(found, qty);
        cart.addToCart(cartProduct);
    }

    // Uses instanceof + casting to create the right subclass copy (demonstrates polymorphism handling)
    private static Product createCartCopy(Product p, int qty) {
        if (p instanceof Fruit f) {
            return new Fruit(f.getProductId(), f.getProductName(), f.getPrice(), qty, f.isOrganic());
        } else if (p instanceof Vegetable v) {
            return new Vegetable(v.getProductId(), v.getProductName(), v.getPrice(), qty, v.isFresh());
        } else if (p instanceof Drink d) {
            return new Drink(d.getProductId(), d.getProductName(), d.getPrice(), qty, d.isChilled());
        } else if (p instanceof Fashion fa) {
            return new Fashion(fa.getProductId(), fa.getProductName(), fa.getPrice(), qty, fa.getSize());
        } else if (p instanceof IceCream ic) {
            return new IceCream(ic.getProductId(), ic.getProductName(), ic.getPrice(), qty, ic.getFlavor());
        }
        return p;
    }

    private static void removeFromCartFlow() {
        cart.viewCart();
        if (cart.isEmpty()) return;
        int id = readInt("Enter Product ID to remove from cart: ");
        cart.removeFromCart(id);
    }

    private static void paymentFlow() {
        double total = cart.calculateTotal();
        if (total <= 0) {
            System.out.println("Cart is empty. Add products before making a payment.");
            return;
        }
        System.out.printf("Amount to Pay: Rs.%.2f%n", total);
        System.out.println("\nSelect Payment Method:");
        System.out.println("1. Cash on Delivery");
        System.out.println("2. Card Payment");
        System.out.println("3. UPI Payment");
        int choice = readInt("Enter choice: ");

        // Interface-based payment processing: Payment reference, different runtime object
        Payment payment;
        switch (choice) {
            case 1 -> {
                double cash = readDouble("Enter cash amount tendered: ");
                payment = new CashPayment(cash);
            }
            case 2 -> {
                System.out.print("Enter 16-digit card number: ");
                String cardNum = sc.nextLine();
                System.out.print("Enter card holder name: ");
                String holder = sc.nextLine();
                payment = new CardPayment(cardNum, holder);
            }
            case 3 -> {
                System.out.print("Enter UPI ID (e.g. name@bank): ");
                String upi = sc.nextLine();
                payment = new UPIPayment(upi);
            }
            default -> {
                System.out.println("Invalid payment choice.");
                return;
            }
        }
        // Runtime Polymorphism: processPayment() call resolves to the correct
        // implementation (Cash/Card/UPI) based on the actual object at runtime.
        payment.processPayment(total);
    }

    private static Product findProductById(int id) {
        for (Product p : catalog) {
            if (p.getProductId() == id) return p;
        }
        return null;
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine(); // consume leftover newline
        return val;
    }

    private static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextDouble()) {
            System.out.print("Please enter a valid amount: ");
            sc.next();
        }
        double val = sc.nextDouble();
        sc.nextLine();
        return val;
    }
}
