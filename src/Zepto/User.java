package Zepto;

/**
 * User.java
 * Represents a customer using the Zepto application.
 * Demonstrates: Encapsulation.
 */
public class User {

    private int userId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public User(int userId, String name, String email, String address, String phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters (Encapsulation)
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer: " + name + " | Phone: " + phoneNumber + " | Address: " + address;
    }
}
