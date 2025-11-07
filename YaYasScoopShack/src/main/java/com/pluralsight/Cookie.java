package com.pluralsight;

public class Cookie {
    private String type;     // Example: Chocolate Chip, Sugar Cookie, etc.
    private String quantity; // Example: "Each", "Half Dozen", or "Dozen"
    private double price;    // Example: 1.50, 7.50, or 14.00

    // Constructor
    public Cookie(String type, String quantity, double price) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public String getType() {
        return type;
    }

    public String getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // For displaying cookie details in the order
    @Override
    public String toString() {
        return quantity + " " + type + " Cookie(s) - $" + String.format("%.2f", price);
    }

    // ---- Optional helper methods ----

    // Show cookie menu for the user
    public static void showCookieMenu() {
        System.out.println("=== Ya Ya’s Scoop Shack Cookie Menu ===");
        System.out.println("Type              Each    Half Dozen    Dozen");
        System.out.println("----------------------------------------------");
        System.out.println("Chocolate Chip    $1.50   $7.50         $14.00");
        System.out.println("Sugar Cookie      $1.50   $7.50         $14.00");
        System.out.println("Peanut Butter     $1.50   $7.50         $14.00");
        System.out.println("Snickerdoodle     $1.50   $7.50         $14.00");
        System.out.println();
    }

    // Get price automatically based on type and quantity
    public static double getPriceFor(String type, String quantity) {
        double price = 0.0;

        if (quantity.equalsIgnoreCase("Each")) price = 1.50;
        else if (quantity.equalsIgnoreCase("Half Dozen")) price = 7.50;
        else if (quantity.equalsIgnoreCase("Dozen")) price = 14.00;

        return price;
    }
}

