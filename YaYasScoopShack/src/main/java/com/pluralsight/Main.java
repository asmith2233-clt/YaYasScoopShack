package com.pluralsight;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🍦 Welcome to Ya Ya’s Scoop Shack! 🍦");
        boolean running = true;

        while (running) {
            System.out.println("\n=== HOME SCREEN ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            int choice = ConsoleHelper.readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1 -> startNewOrder();
                case 0 -> {
                    System.out.println("Thank you for visiting Ya Ya’s Scoop Shack! Goodbye! 👋");
                    running = false;
                }
                default -> System.out.println("⚠️ Invalid choice. Please enter 1 or 0.");
            }
        }
    }

    public static void startNewOrder() {
        Order order = new Order();
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n=== ORDER MENU ===");
            System.out.println("1) Add Ice Cream");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Cookie");
            System.out.println("4) Add Milkshake");
            System.out.println("5) Checkout");
            System.out.println("6) Signature Item");
            System.out.println("0) Cancel Order");

            int choice = ConsoleHelper.readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1 -> order.addIceCream(createIceCream());
                case 2 -> order.addDrink(Drink.createFromInput(scanner));
                case 3 -> order.addCookie(Cookie.createFromInput(scanner));
                case 4 -> order.addMilkshake(Milkshake.createFromInput(scanner));
                case 5 -> {
                    order.checkout();
                    ordering = false;
                }
                case 6 -> {
                    SignatureItem.showSignatureMenu();
                    int sigChoice = ConsoleHelper.readInt(scanner, "Choose signature item: ");
                    SignatureItem sigItem = SignatureItem.createSignature(sigChoice);
                    if (sigItem != null) {
                        sigItem.customize(scanner);
                        order.addIceCream(sigItem);
                    }
                }
                case 0 -> {
                    order.cancelOrder();
                    ordering = false;
                }
                default -> System.out.println("⚠️ Invalid choice, please try again.");
            }
        }
    }

    private static IceCream createIceCream() {
        System.out.println("\n=== Add Ice Cream ===");
        String flavor = ConsoleHelper.readString(scanner, "Enter flavor (Vanilla Bean, Chocolate Fudge, Strawberry Swirl, Mint Chocolate Chip): ");
        String size = ConsoleHelper.readString(scanner, "Enter size (Cup, Pint, Quart): ");

        double basePrice = switch (size.toLowerCase()) {
            case "pint" -> 6.50;
            case "quart" -> 8.50;
            default -> 3.50;
        };

        IceCream iceCream = new IceCream(flavor, size, basePrice);

        if (ConsoleHelper.readYesNo(scanner, "Would you like to add premium toppings?")) {
            while (true) {
                String topping = ConsoleHelper.readString(scanner, "Enter premium topping (or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addPremiumTopping(topping, 0.75);
            }
        }

        if (ConsoleHelper.readYesNo(scanner, "Would you like to add regular toppings?")) {
            while (true) {
                String topping = ConsoleHelper.readString(scanner, "Enter regular topping (or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addRegularTopping(topping);
            }
        }

        if (ConsoleHelper.readYesNo(scanner, "Would you like the ice cream specialized?")) {
            iceCream.setSpecialized(true);
        }

        return iceCream;
    }
}
