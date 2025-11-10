package com.pluralsight;

import java.io.File;
import java.io.FileWriter;

public class Main {
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

    // === Start a new order ===
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
        String flavor = ConsoleHelper.readString(
                "Enter flavor (Vanilla Bean, Chocolate Fudge, Strawberry Swirl, Mint Chocolate Chip): ");
        String size = ConsoleHelper.readString("Enter size (Cup, Pint, Quart): ");

        double basePrice = switch (size.toLowerCase()) {
            case "cup" -> 3.50;
            case "pint" -> 6.50;
            case "quart" -> 8.50;
            default -> 3.50;
        };

        IceCream iceCream = new IceCream(flavor, size, basePrice);

        // Premium toppings
        if (ConsoleHelper.readYesNo("Would you like to add premium toppings?")) {
            while (true) {
                String topping = ConsoleHelper.readString(
                        "Enter topping (Brownie Bites, Cookie Dough Chunks, Cheesecake Pieces, Caramel Drizzle or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addPremiumTopping(topping, 0.75);
            }
        }

        // Regular toppings
        if (ConsoleHelper.readYesNo("Would you like to add regular toppings?")) {
            while (true) {
                String topping = ConsoleHelper.readString(
                        "Enter topping (Sprinkles, Whipped Cream, Cherries, Crushed Oreos, Peanuts, Marshmallows or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addRegularTopping(topping);
            }
        }

        // Specialized
        if (ConsoleHelper.readYesNo("Would you like the ice cream specialized?")) {
            iceCream.setSpecialized(true);
        }

        order.addIceCream(iceCream);
        System.out.println("✅ Ice Cream added to your order!");
    }

    // === Add Drink ===
    public static void addDrink(Order order) {
        System.out.println("\n=== Add Drink ===");
        Drink.showDrinkMenu();

        String drinkName = ConsoleHelper.readString("Enter drink (Bottled Water, Soda, Iced Coffee, Lemonade): ");
        String size = ConsoleHelper.readString("Enter size (Small, Medium, Large): ");

        double price = Drink.getPriceFor(drinkName, size);
        order.addDrink(new Drink(drinkName, size, price));
        System.out.println("✅ Drink added to your order!");
    }

    // === Add Cookie ===
    public static void addCookie(Order order) {
        System.out.println("\n=== Add Cookie ===");
        Cookie.showCookieMenu();

        String type = ConsoleHelper.readString("Enter cookie type (Chocolate Chip, Sugar Cookie, Peanut Butter, Snickerdoodle): ");
        String quantity = ConsoleHelper.readString("Enter quantity (Each, Half Dozen, Dozen): ");

        double price = Cookie.getPriceFor(type, quantity);
        order.addCookie(new Cookie(type, quantity, price));
        System.out.println("✅ Cookie added to your order!");
    }

    // === Add Milkshake ===
    public static void addMilkshake(Order order) {
        System.out.println("\n=== Add Milkshake ===");
        Milkshake.showMilkshakeMenu();

        String flavor = ConsoleHelper.readString(
                "Enter milkshake flavor (Classic Vanilla, Chocolate Delight, Strawberry Dream, Cookies & Cream): ");
        String size = ConsoleHelper.readString("Enter size (Small, Medium, Large): ");

        double price = Milkshake.getPriceFor(flavor, size);
        order.addMilkshake(new Milkshake(flavor, size, price));
        System.out.println("✅ Milkshake added to your order!");
    }

    // === Checkout ===
    public static void checkout(Order order) {
        System.out.println("\n=== CHECKOUT ===");
        order.displayOrder();

        if (ConsoleHelper.readYesNo("Confirm order?")) {
            try {
                File folder = new File("receipts");
                if (!folder.exists()) folder.mkdir();

                String fileName = "receipts/receipt_" + System.currentTimeMillis() + ".txt";
                FileWriter writer = new FileWriter(fileName);

                writer.write("=========================================\n");
                writer.write("        Ya Ya's Scoop Shack Receipt      \n");
                writer.write("=========================================\n\n");

                order.writeReceipt(writer);

                writer.write("=========================================\n");
                writer.write(String.format("TOTAL PRICE: $%.2f\n", order.calculateTotal()));
                writer.write("=========================================\n");
                writer.write("Thank you for visiting Ya Ya’s Scoop Shack!\n");

                writer.close();

                System.out.println("✅ Order confirmed! Receipt saved to: " + fileName);
            } catch (Exception e) {
                System.out.println("⚠️ Error saving receipt: " + e.getMessage());
            }
        } else {
            order.cancelOrder();
        }
    }
}
