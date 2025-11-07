package com.pluralsight;

public class Milkshake {
    private String flavor;   // Example: Chocolate Delight, Strawberry Dream
    private String size;     // Example: Small, Medium, Large
    private double price;    // Example: 4.50, 5.50, 6.50

    // Constructor
    public Milkshake(String flavor, String size, double price) {
        this.flavor = flavor;
        this.size = size;
        this.price = price;
    }

    // Getters
    public String getFlavor() {
        return flavor;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    // For displaying milkshake details in the order summary
    @Override
    public String toString() {
        return size + " " + flavor + " Milkshake - $" + String.format("%.2f", price);
    }

    // ---- Optional helper methods ----

    // Show the milkshake menu
    public static void showMilkshakeMenu() {
        System.out.println("=== Ya Ya’s Scoop Shack Milkshake Menu ===");
        System.out.println("Type               Small   Medium   Large");
        System.out.println("------------------------------------------");
        System.out.println("Classic Vanilla     $4.50   $5.50    $6.50");
        System.out.println("Chocolate Delight   $4.50   $5.50    $6.50");
        System.out.println("Strawberry Dream    $4.50   $5.50    $6.50");
        System.out.println("Cookies & Cream     $4.50   $5.50    $6.50");
        System.out.println();
    }

    // Get price automatically by flavor and size
    public static double getPriceFor(String flavor, String size) {
        double price = 0.0;

        if (size.equalsIgnoreCase("Small")) price = 4.50;
        else if (size.equalsIgnoreCase("Medium")) price = 5.50;
        else if (size.equalsIgnoreCase("Large")) price = 6.50;

        return price;
    }
}

