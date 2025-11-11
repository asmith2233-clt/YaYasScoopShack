package com.pluralsight;

import java.util.Scanner;

public class Cookie extends Item {
    private String quantity;

    public Cookie(String type, String quantity, double price) {
        super(type, price);
        this.quantity = quantity;
    }

    public static Cookie createFromInput(Scanner scanner) {
        System.out.println(Order.BROWN + "=== Add Cookie ===" + Order.RESET);
        System.out.print(Order.BROWN + "Cookie type (Chocolate Chip, Sugar Cookie, Peanut Butter, Snickerdoodle): " + Order.RESET);
        String type = scanner.nextLine();

        System.out.print(Order.BROWN + "Quantity (Each, Half Dozen, Dozen): " + Order.RESET);
        String qty = scanner.nextLine();

        double price = switch (qty.toLowerCase()) {
            case "each" -> 1.50;
            case "half dozen" -> 7.50;
            case "dozen" -> 14.00;
            default -> 0.0;
        };

        return new Cookie(type, qty, price);
    }

    @Override
    public String getDescription() {
        return Order.BROWN + quantity + " " + name + Order.RESET + String.format(" - $%.2f", price);
    }
}


