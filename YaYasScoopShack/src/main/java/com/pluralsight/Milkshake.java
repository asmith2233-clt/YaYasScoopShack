package com.pluralsight;

public class Milkshake {
    private final String flavor;
    private final String size;
    private final double price;

    public Milkshake(String flavor, String size, double price) {
        this.flavor = flavor;
        this.size = size;
        this.price = price;
    }

    public String getFlavor() { return flavor; }
    public String getSize() { return size; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return size + " " + flavor + " Milkshake - $" + String.format("%.2f", price);
    }

    public static void showMilkshakeMenu() {
        System.out.println("=== Milkshake Menu ===");
        System.out.println("Classic Vanilla, Chocolate Delight, Strawberry Dream, Cookies & Cream");
        System.out.println("Sizes: Small $4.50, Medium $5.50, Large $6.50");
    }

    public static double getPriceFor(String flavor, String size) {
        return switch (size.toLowerCase()) {
            case "small" -> 4.50;
            case "medium" -> 5.50;
            case "large" -> 6.50;
            default -> 0.0;
        };
    }
}

