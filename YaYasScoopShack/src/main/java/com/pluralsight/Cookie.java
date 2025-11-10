package com.pluralsight;

public class Cookie {
    private final String type;
    private final String quantity;
    private final double price;

    public Cookie(String type, String quantity, double price) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public String getType() { return type; }
    public String getQuantity() { return quantity; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return quantity + " " + type + " Cookie(s) - $" + String.format("%.2f", price);
    }

    public static void showCookieMenu() {
        System.out.println("=== Cookie Menu ===");
        System.out.println("Chocolate Chip, Sugar Cookie, Peanut Butter, Snickerdoodle");
        System.out.println("Quantity: Each, Half Dozen, Dozen");
    }

    public static double getPriceFor(String type, String quantity) {
        return switch (quantity.toLowerCase()) {
            case "each" -> 1.50;
            case "half dozen" -> 7.50;
            case "dozen" -> 14.00;
            default -> 0.0;
        };
    }
}
