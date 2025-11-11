package com.pluralsight;

import java.util.Scanner;

public class Main {

    private static final double SALES_TAX_RATE = 0.07;

    public static void main(String[] args) {
        System.out.println("🍦 Welcome to Ya Ya’s Scoop Shack! 🍦");
        boolean running = true;

        while (running) {
            System.out.println("\n=== HOME SCREEN ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            int choice = ConsoleHelper.readInt("Enter your choice: ");

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

    // === Start New Order ===
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
            System.out.println("0) Cancel Order");

            int choice = ConsoleHelper.readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addIceCream(order);
                case 2 -> addDrink(order);
                case 3 -> addCookie(order);
                case 4 -> addMilkshake(order);
                case 5 -> {
                    checkout(order);
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

    // === Add Ice Cream ===
    public static void addIceCream(Order order) {
        System.out.println("\n=== Add Ice Cream ===");
        String flavor = ConsoleHelper.readString("Enter flavor (Vanilla Bean, Chocolate Fudge, Strawberry Swirl, Mint Chocolate Chip): ");
        String size = ConsoleHelper.readString("Enter size (Cup, Pint, Quart): ");

        double basePrice = switch (size.toLowerCase()) {
            case "cup" -> 3.50;
            case "pint" -> 6.50;
            case "quart" -> 8.50;
            default -> 3.50;
        };

        IceCream iceCream = new IceCream(flavor, size, basePrice);

        if (ConsoleHelper.readYesNo("Add premium toppings?")) {
            while (true) {
                String topping = ConsoleHelper.readString("Enter premium topping (or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addPremiumTopping(topping, 0.75);
            }
        }

        if (ConsoleHelper.readYesNo("Add regular toppings?")) {
            while (true) {
                String topping = ConsoleHelper.readString("Enter regular topping (or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addRegularTopping(topping);
            }
        }

        if (ConsoleHelper.readYesNo("Make it specialized (+ $1.00)?")) {
            iceCream.setSpecialized(true);
        }

        order.addIceCream(iceCream);
        System.out.println("✅ Ice Cream added to your order!");
    }

    // === Add Drink ===
    public static void addDrink(Order order) {
        System.out.println("\n=== Add Drink ===");
        Drink.showDrinkMenu();

        String drinkName = ConsoleHelper.readString("Enter drink: ");
        String size = ConsoleHelper.readString("Enter size (Small, Medium, Large): ");

        double price = Drink.getPriceFor(drinkName, size);
        order.addDrink(new Drink(drinkName, size, price));
        System.out.println("✅ Drink added to your order!");
    }

    // === Add Cookie ===
    public static void addCookie(Order order) {
        System.out.println("\n=== Add Cookie ===");
        Cookie.showCookieMenu();

        String type = ConsoleHelper.readString("Enter cookie type: ");
        String quantity = ConsoleHelper.readString("Enter quantity (Each, Half Dozen, Dozen): ");

        double price = Cookie.getPriceFor(type, quantity);
        order.addCookie(new Cookie(type, quantity, price));
        System.out.println("✅ Cookie added to your order!");
    }

    // === Add Milkshake ===
    public static void addMilkshake(Order order) {
        System.out.println("\n=== Add Milkshake ===");
        Milkshake.showMilkshakeMenu();

        String flavor = ConsoleHelper.readString("Enter flavor: ");
        String size = ConsoleHelper.readString("Enter size (Small, Medium, Large): ");

        double price = Milkshake.getPriceFor(flavor, size);
        order.addMilkshake(new Milkshake(flavor, size, price));
        System.out.println("✅ Milkshake added to your order!");
    }

    // === Checkout ===
    public static void checkout(Order order) {
        System.out.println("\n=== CHECKOUT ===");
        order.displayOrder();

        double subtotal = order.calculateTotal();
        double tax = subtotal * SALES_TAX_RATE;
        double totalWithTax = subtotal + tax;

        System.out.printf("%nSubtotal: $%.2f%nTax (7%%): $%.2f%nTotal: $%.2f%n", subtotal, tax, totalWithTax);

        if (ConsoleHelper.readYesNo("Confirm order?")) {
            double amountPaid = 0;
            do {
                amountPaid = ConsoleHelper.readInt("Enter amount paid (e.g. 10 for $10.00): ");
                if (amountPaid < totalWithTax)
                    System.out.println("⚠️ That’s not enough to cover the total.");
            } while (amountPaid < totalWithTax);

            String fileName = ReceiptFileManager.saveReceipt(order, amountPaid);
            if (fileName != null)
                System.out.println("✅ Order confirmed! Receipt saved to: " + fileName);
            else
                System.out.println("⚠️ Error saving receipt.");
        } else {
            order.cancelOrder();
        }
    }
}
