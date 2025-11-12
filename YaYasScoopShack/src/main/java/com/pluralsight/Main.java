package com.pluralsight;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(Order.PINK + "🍦 Welcome to Ya Ya’s Scoop Shack! 🍦" + Order.RESET);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println(Order.CYAN + "\n=== HOME SCREEN ===" + Order.RESET);
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            int choice = ConsoleHelper.readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1 -> startNewOrder(scanner);
                case 0 -> {
                    System.out.println(Order.PINK + "Thank you for visiting Ya Ya’s Scoop Shack! Goodbye! 👋" + Order.RESET);
                    running = false;
                }
                default -> System.out.println("⚠️ Invalid choice. Please enter 1 or 0.");
            }
        }

        scanner.close();
    }

    // === Start a new order ===
    public static void startNewOrder(Scanner scanner) {
        Order order = new Order();
        boolean ordering = true;

        while (ordering) {
            System.out.println(Order.CYAN + "\n=== ORDER SCREEN ===" + Order.RESET);
            System.out.println("1) Add Item (Ice Cream)");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Main Side");
            System.out.println("4) Checkout");
            System.out.println("5) Signature Item");
            System.out.println("0) Cancel Order");

            int choice = ConsoleHelper.readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1 -> addItem(order, scanner);
                case 2 -> addDrink(order, scanner);
                case 3 -> addMainSide(order, scanner);
                case 4 -> {
                    order.checkout(scanner);
                    ordering = false;
                }
                case 5 -> addSignatureItem(order, scanner);
                case 0 -> {
                    order.cancelOrder();
                    ordering = false;
                }
                default -> System.out.println("⚠️ Invalid choice, please try again.");
            }
        }
    }

    // === Add Ice Cream Item ===
    public static void addItem(Order order, Scanner scanner) {
        System.out.println(Order.PINK + "\n=== Add Ice Cream ===" + Order.RESET);

        String flavor = ConsoleHelper.readString(scanner,
                "Enter flavor (Vanilla Bean, Chocolate Fudge, Strawberry Swirl, Mint Chocolate Chip): ");
        String size = ConsoleHelper.readString(scanner, "Enter size (Cup, Pint, Quart): ");

        double basePrice = switch (size.toLowerCase()) {
            case "pint" -> 6.50;
            case "quart" -> 8.50;
            default -> 3.50;
        };

        IceCream iceCream = new IceCream(flavor, size, basePrice);

        // Premium toppings
        if (ConsoleHelper.readYesNo(scanner, "Would you like to add premium toppings?")) {
            System.out.println("Premium toppings: Brownie Bites, Cookie Dough Chunks, Cheesecake Pieces, Caramel Drizzle");
            while (true) {
                String topping = ConsoleHelper.readString(scanner, "Enter premium topping (or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addPremiumTopping(topping, 0.75);
            }
        }

        // Regular toppings
        if (ConsoleHelper.readYesNo(scanner, "Would you like to add regular toppings?")) {
            System.out.println("Regular toppings: Sprinkles, Whipped Cream, Cherries, Crushed Oreos, Peanuts, Marshmallows");
            while (true) {
                String topping = ConsoleHelper.readString(scanner, "Enter regular topping (or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addRegularTopping(topping);
            }
        }

        // Specialized option
        if (ConsoleHelper.readYesNo(scanner, "Would you like the item specialized? (+$1.00)")) {
            iceCream.setSpecialized(true);
        }

        order.addIceCream(iceCream);
        System.out.println(Order.PINK + "✅ Item added to your order!" + Order.RESET);
        order.displayOrder();
    }

    // === Add Drink ===
    public static void addDrink(Order order, Scanner scanner) {
        System.out.println(Order.CYAN + "\n=== Add Drink ===" + Order.RESET);
        Drink.showDrinkMenu();
        String drinkName = ConsoleHelper.readString(scanner, "Enter drink: ");
        String size = ConsoleHelper.readString(scanner, "Enter size (Small, Medium, Large): ");
        double price = Drink.getPriceFor(drinkName, size);
        order.addDrink(new Drink(drinkName, size, price));
        System.out.println(Order.CYAN + "✅ Drink added to your order!" + Order.RESET);
        order.displayOrder();
    }

    // === Add Main Side (Cookie or Milkshake) ===
    public static void addMainSide(Order order, Scanner scanner) {
        System.out.println(Order.CREAM + "\n=== Add Main Side ===" + Order.RESET);
        System.out.println("Available sides: Cookie, Milkshake");
        String sideType = ConsoleHelper.readString(scanner, "Enter main side type: ");

        switch (sideType.toLowerCase()) {
            case "cookie" -> addCookie(order, scanner);
            case "milkshake" -> addMilkshake(order, scanner);
            default -> System.out.println("⚠️ Unknown side type.");
        }
    }

    public static void addCookie(Order order, Scanner scanner) {
        Cookie.showCookieMenu();
        String type = ConsoleHelper.readString(scanner, "Enter cookie type: ");
        String quantity = ConsoleHelper.readString(scanner, "Enter quantity (Single, Half Dozen, Dozen): ");
        double price = Cookie.getPriceFor(type, quantity);
        order.addCookie(new Cookie(type, quantity, price));
        System.out.println(Order.BROWN + "✅ Cookie added to your order!" + Order.RESET);
        order.displayOrder();
    }

    public static void addMilkshake(Order order, Scanner scanner) {
        Milkshake.showMilkshakeMenu();
        String flavor = ConsoleHelper.readString(scanner, "Enter milkshake flavor: ");
        String size = ConsoleHelper.readString(scanner, "Enter size (Small, Medium, Large): ");
        double price = Milkshake.getPriceFor(flavor, size);
        order.addMilkshake(new Milkshake(flavor, size, price));
        System.out.println(Order.CREAM + "✅ Milkshake added to your order!" + Order.RESET);
        order.displayOrder();
    }

    public static void addSignatureItem(Order order, Scanner scanner) {
        SignatureItem.showSignatureMenu();
        int choice = ConsoleHelper.readInt(scanner, "Enter signature item number: ");
        SignatureItem item = SignatureItem.createSignature(choice);

        if (item != null) {
            System.out.println("You chose: " + item.getDescription());
            if (ConsoleHelper.readYesNo(scanner, "Would you like to customize this signature item?")) {
                item.customize(scanner);
            }
            order.addIceCream(item);
            System.out.println(Order.PINK + "✅ Signature item added!" + Order.RESET);
            order.displayOrder();
        } else {
            System.out.println("⚠️ Invalid choice for signature item.");
        }
    }
}





