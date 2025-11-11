package com.pluralsight;

public class Cookie extends Item {
    private String quantity;

    public Cookie(String type, String quantity, double price) {
        super(type, price);
        this.quantity = quantity;
    }

    public static void showCookieMenu() {
        System.out.println("🍪 Cookies:");
        System.out.println("Chocolate Chip, Sugar Cookie, Peanut Butter, Snickerdoodle");
        System.out.println("Quantities: Each, Half Dozen, Dozen");
    }

    public static double getPriceFor(String type, String quantity) {
        quantity = quantity.toLowerCase();

        return switch (quantity) {
            case "each" -> 1.50;
            case "half dozen" -> 7.50;
            case "dozen" -> 14.00;
            default -> 1.50; // default to single cookie
        };
    }

    @Override
    public String getDescription() {
        return Order.BROWN + quantity + " " + name + Order.RESET + String.format(" - $%.2f", price);
    }
}

