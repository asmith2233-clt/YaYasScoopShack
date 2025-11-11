package com.pluralsight;

import java.util.Scanner;

public class Milkshake extends Item {
    private String size;

    public Milkshake(String flavor, String size, double price) {
        super(flavor, price);
        this.size = size;
    }

    public static Milkshake createFromInput(Scanner scanner) {
        System.out.println(Order.CREAM + "=== Add Milkshake ===" + Order.RESET);
        System.out.print(Order.CREAM + "Flavor (Classic Vanilla, Chocolate Delight, Strawberry Dream, Cookies & Cream): " + Order.RESET);
        String flavor = scanner.nextLine();

        System.out.print(Order.CREAM + "Size (Small, Medium, Large): " + Order.RESET);
        String size = scanner.nextLine();

        double price = switch (size.toLowerCase()) {
            case "small" -> 3.50;
            case "medium" -> 5.50;
            case "large" -> 7.50;
            default -> 0.0;
        };

        return new Milkshake(flavor, size, price);
    }

    @Override
    public String getDescription() {
        return Order.CREAM + size + " " + name + Order.RESET + String.format(" - $%.2f", price);
    }
}




