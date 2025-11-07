package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("🍦 Welcome to Ya Ya’s Scoop Shack! 🍦");

        while (running) {
            System.out.println("\n=== HOME SCREEN ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear input

            switch (choice) {
                case 1:
                    startNewOrder(scanner);
                    break;
                case 0:
                    System.out.println("Thank you for visiting Ya Ya’s Scoop Shack! Goodbye! 👋");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 0.");
                    break;
            }
        }

        scanner.close();
    }

    // === Start a new order ===
    public static void startNewOrder(Scanner scanner) {
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
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addIceCream(scanner, order);
                    break;
                case 2:
                    addDrink(scanner, order);
                    break;
                case 3:
                    addCookie(scanner, order);
                    break;
                case 4:
                    addMilkshake(scanner, order);
                    break;
                case 5:
                    checkout(scanner, order);
                    ordering = false;
                    break;
                case 0:
                    order.cancelOrder();
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    // === Add Ice Cream ===
    public static void addIceCream(Scanner scanner, Order order) {
        System.out.println("\n=== Add Ice Cream ===");
        System.out.print("Enter flavor (Vanilla Bean, Chocolate Fudge, Strawberry Swirl, Mint Chocolate Chip): ");
        String flavor = scanner.nextLine();

        System.out.print("Enter size (Cup, Pint, Quart): ");
        String size = scanner.nextLine();

        double basePrice = 0.0;
        if (size.equalsIgnoreCase("Cup")) basePrice = 3.50;
        else if (size.equalsIgnoreCase("Pint")) basePrice = 6.50;
        else if (size.equalsIgnoreCase("Quart")) basePrice = 8.50;

        IceCream iceCream = new IceCream(flavor, size, basePrice);

        // Add toppings
        System.out.print("Would you like to add premium toppings? (yes/no): ");
        String addToppings = scanner.nextLine();
        if (addToppings.equalsIgnoreCase("yes")) {
            while (true) {
                System.out.print("Enter topping (Brownie Bites, Cookie Dough Chunks, Cheesecake Pieces, Caramel Drizzle or 'done'): ");
                String topping = scanner.nextLine();
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addTopping(topping, 0.75);
            }
        }

        // Specialized option
        System.out.print("Would you like the ice cream specialized? (yes/no): ");
        String spec = scanner.nextLine();
        if (spec.equalsIgnoreCase("yes")) {
            iceCream.setSpecialized(true);
        }

        order.addIceCream(iceCream);
        System.out.println("✅ Ice Cream added to your order!");
    }

    // === Add Drink ===
    public static void addDrink(Scanner scanner, Order order) {
        System.out.println("\n=== Add Drink ===");
        Drink.showDrinkMenu();

        System.out.print("Enter drink (Bottled Water, Soda, Iced Coffee, Lemonade): ");
        String drinkName = scanner.nextLine();

        System.out.print("Enter size (Small, Medium, Large): ");
        String size = scanner.nextLine();

        double price = Drink.getPriceFor(drinkName, size);
        Drink drink = new Drink(drinkName, size, price);

        order.addDrink(drink);
        System.out.println("✅ Drink added to your order!");
    }

    // === Add Cookie ===
    public static void addCookie(Scanner scanner, Order order) {
        System.out.println("\n=== Add Cookie ===");
        Cookie.showCookieMenu();

        System.out.print("Enter cookie type (Chocolate Chip, Sugar Cookie, Peanut Butter, Snickerdoodle): ");
        String type = scanner.nextLine();

        System.out.print("Enter quantity (Each, Half Dozen, Dozen): ");
        String quantity = scanner.nextLine();

        double price = Cookie.getPriceFor(type, quantity);
        Cookie cookie = new Cookie(type, quantity, price);

        order.addCookie(cookie);
        System.out.println("✅ Cookie added to your order!");
    }

    // === Add Milkshake ===
    public static void addMilkshake(Scanner scanner, Order order) {
        System.out.println("\n=== Add Milkshake ===");
        Milkshake.showMilkshakeMenu();

        System.out.print("Enter milkshake flavor (Classic Vanilla, Chocolate Delight, Strawberry Dream, Cookies & Cream): ");
        String flavor = scanner.nextLine();

        System.out.print("Enter size (Small, Medium, Large): ");
        String size = scanner.nextLine();

        double price = Milkshake.getPriceFor(flavor, size);
        Milkshake milkshake = new Milkshake(flavor, size, price);

        order.addMilkshake(milkshake);
        System.out.println("✅ Milkshake added to your order!");
    }

    // === Checkout (with FileWriter for receipts) ===
    public static void checkout(Scanner scanner, Order order) {
        System.out.println("\n=== CHECKOUT ===");
        order.displayOrder();

        System.out.print("Would you like to confirm your order? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            try {
                // ✅ Create receipts folder if it doesn’t exist
                File folder = new File("receipts");
                if (!folder.exists()) {
                    folder.mkdir();
                }

                // Create unique file name using current time
                String fileName = "receipts/receipt_" + System.currentTimeMillis() + ".txt";

                FileWriter writer = new FileWriter(fileName);

                writer.write("=========================================\n");
                writer.write("        Ya Ya's Scoop Shack Receipt      \n");
                writer.write("=========================================\n\n");

                // Write Ice Creams
                if (!order.getIceCream().isEmpty()) {
                    writer.write("🍨 Ice Cream:\n");
                    for (IceCream i : order.getIceCream()) {
                        writer.write(" - " + i.getSize() + " " + i.getFlavor() + " - $" + String.format("%.2f", i.getPrice()) + "\n");
                    }
                    writer.write("\n");
                }

                // Write Drinks
                if (!order.getDrinks().isEmpty()) {
                    writer.write("🥤 Drinks:\n");
                    for (Drink d : order.getDrinks()) {
                        writer.write(" - " + d.getSize() + " " + d.getName() + " - $" + String.format("%.2f", d.getPrice()) + "\n");
                    }
                    writer.write("\n");
                }

                // Write Cookies
                if (!order.getCookies().isEmpty()) {
                    writer.write("🍪 Cookies:\n");
                    for (Cookie c : order.getCookies()) {
                        writer.write(" - " + c.getQuantity() + " " + c.getType() + " - $" + String.format("%.2f", c.getPrice()) + "\n");
                    }
                    writer.write("\n");
                }

                // Write Milkshakes
                if (!order.getMilkshakes().isEmpty()) {
                    writer.write("🥛 Milkshakes:\n");
                    for (Milkshake m : order.getMilkshakes()) {
                        writer.write(" - " + m.getSize() + " " + m.getFlavor() + " - $" + String.format("%.2f", m.getPrice()) + "\n");
                    }
                    writer.write("\n");
                }

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


