package com.pluralsight;

import java.util.Scanner;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(Order.PINK + "🍦 Welcome to Ya Ya’s Scoop Shack! 🍦" + Order.RESET);

        boolean running = true;
        while (running) {
            System.out.println("\n" + Order.CREAM + "=== HOME SCREEN ===" + Order.RESET);
            System.out.println(Order.PINK + "1) New Order" + Order.RESET);
            System.out.println(Order.BROWN + "0) Exit" + Order.RESET);

            int choice = ConsoleHelper.readInt(scanner, "Enter your choice: ");
            switch (choice) {
                case 1 -> startNewOrder();
                case 0 -> {
                    System.out.println(Order.PINK + "Thank you for visiting Ya Ya’s Scoop Shack! Goodbye! 👋" + Order.RESET);
                    running = false;
                }
                default -> System.out.println("⚠️ Invalid choice. Please enter 1 or 0.");
            }
        }
        scanner.close();
    }

    private static void startNewOrder() {
        Order order = new Order();
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n" + Order.CREAM + "=== ORDER MENU ===" + Order.RESET);
            System.out.println(Order.PINK + "1) Add Ice Cream" + Order.RESET);
            System.out.println(Order.CYAN + "2) Add Drink" + Order.RESET);
            System.out.println(Order.BROWN + "3) Add Cookie" + Order.RESET);
            System.out.println(Order.CREAM + "4) Add Milkshake" + Order.RESET);
            System.out.println(Order.PINK + "5) Add Signature Item" + Order.RESET);
            System.out.println(Order.CYAN + "6) Checkout" + Order.RESET);
            System.out.println(Order.BROWN + "0) Cancel Order" + Order.RESET);

            int choice = ConsoleHelper.readInt(scanner, "Enter your choice: ");
            switch (choice) {
                case 1 -> order.addIceCream(scanner);
                case 2 -> order.addDrink(scanner);
                case 3 -> order.addCookie(scanner);
                case 4 -> order.addMilkshake(scanner);
                case 5 -> order.addSignatureItem(scanner);
                case 6 -> {
                    order.checkout(scanner);
                    ordering = false;
                }
                case 0 -> {
                    order.cancelOrder();
                    ordering = false;
                }
                default -> System.out.println("⚠️ Invalid choice, please try again.");
            }
        }
    }
}


