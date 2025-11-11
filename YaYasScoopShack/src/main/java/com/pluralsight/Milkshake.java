package com.pluralsight;

public class Milkshake extends Item {
    private String size;

    public Milkshake(String flavor, String size, double price) {
        super(flavor, price);
        this.size = size;
    }

    public static void showMilkshakeMenu() {
        System.out.println("🥤 Milkshakes:");
        System.out.println("Classic Vanilla, Chocolate Delight, Strawberry Dream, Cookies & Cream");
    }

    public static double getPriceFor(String flavor, String size) {
        size = size.toLowerCase();

        return switch (size) {
            case "small" -> 4.50;
            case "medium" -> 5.50;
            case "large" -> 6.50;
            default -> 4.50; // default to small
        };
    }

    @Override
    public String getDescription() {
        return Order.CREAM + size + " " + name + Order.RESET + String.format(" - $%.2f", price);
    }
}
