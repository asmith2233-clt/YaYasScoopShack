package com.pluralsight;

import java.util.Scanner;

public class Drink extends Item {
    private String size;

    public Drink(String name, String size, double price) {
        super(name, price);
        this.size = size;
    }

    public static Drink createFromInput(Scanner scanner) {
        System.out.println(Order.CYAN + "=== Add Drink ===" + Order.RESET);
        System.out.print(Order.CYAN + "Drink flavor (Bottled Water, Soda, Iced Coffee, Lemonade): " + Order.RESET);
        String name = scanner.nextLine();

        System.out.print(Order.CYAN + "Size (Small, Medium, Large): " + Order.RESET);
        String size = scanner.nextLine();

        double price = switch (size.toLowerCase()) {
            case "small" -> 1.50;
            case "medium" -> 2.00;
            case "large" -> 2.50;
            default -> 0.0;
        };

        return new Drink(name, size, price);
    }

    @Override
    public String getDescription() {
        return Order.CYAN + size + " " + name + Order.RESET + String.format(" - $%.2f", price);
    }
}

