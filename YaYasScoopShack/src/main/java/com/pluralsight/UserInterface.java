package com.pluralsight;

import java.util.Scanner;

/**
 * Animated & color-coded User Interface for Ya Ya’s Scoop Shack.
 * “Where Every Bite Feels Just Right.”
 */
public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);
    private final Order order = new Order();

    // === ANSI Colors for retro pastel theme ===
    public static final String RESET = "\u001B[0m";
    public static final String PINK = "\u001B[38;5;218m";
    public static final String MINT = "\u001B[38;5;121m";
    public static final String CREAM = "\u001B[38;5;230m";
    public static final String BROWN = "\u001B[38;5;130m";
    public static final String BLUE = "\u001B[38;5;117m";

    // === Entry point ===
    public void display() {
        printSlow(PINK + "\n🍦 Welcome to Ya Ya’s Scoop Shack 🍦\n" + RESET, 35);
        printSlow(MINT + "Where Every Bite Feels Just Right.\n" + RESET, 25);

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = ConsoleHelper.readInt(scanner, MINT + "Enter your choice: " + RESET);

            switch (choice) {
                case 1 -> startNewOrder();
                case 2 -> viewSignatureMenu();
                case 0 -> {
                    printSlow(PINK + "\n👋 Thanks for visiting Ya Ya’s Scoop Shack!" + RESET, 25);
                    printSlow(CREAM + "Hope your day is sprinkled with sweetness!\n" + RESET, 20);
                    running = false;
                }
                default -> System.out.println(BROWN + "⚠️ Invalid choice, please try again.\n" + RESET);
            }
        }
    }

    private void showMainMenu() {
        System.out.println(BLUE + "\n╔════════════════════════╗" + RESET);
        System.out.println(BLUE + "║      MAIN MENU         ║" + RESET);
        System.out.println(BLUE + "╚════════════════════════╝" + RESET);
        System.out.println(PINK + "1)" + RESET + " Start a New Order");
        System.out.println(PINK + "2)" + RESET + " View Signature Items");
        System.out.println(PINK + "0)" + RESET + " Exit");
    }

    private void startNewOrder() {
        boolean ordering = true;
        printSlow(MINT + "\nStarting a new order..." + RESET, 30);

        while (ordering) {
            showOrderMenu();
            int choice = ConsoleHelper.readInt(scanner, MINT + "Enter your choice: " + RESET);

            switch (choice) {
                case 1 -> addIceCream();
                case 2 -> addDrink();
                case 3 -> addCookie();
                case 4 -> addMilkshake();
                case 5 -> addSignatureItem();
                case 6 -> checkout();
                case 0 -> {
                    order.cancelOrder();
                    ordering = false;
                }
                default -> System.out.println(BROWN + "⚠️ Invalid option. Try again." + RESET);
            }
        }
    }

    private void showOrderMenu() {
        System.out.println(PINK + "\n🍧 ORDER MENU 🍧" + RESET);
        System.out.println("1) Add Ice Cream");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Cookie");
        System.out.println("4) Add Milkshake");
        System.out.println("5) Add Signature Item");
        System.out.println("6) Checkout");
        System.out.println("0) Cancel Order");
    }

    private void addIceCream() {
        printSlow(PINK + "\n🍨 Add Ice Cream" + RESET, 20);
        // Show available flavors
        System.out.println("Available flavors: Vanilla Bean, Chocolate Fudge, Strawberry Swirl, Mint Chocolate Chip");
        String flavor = ConsoleHelper.readString(scanner, "Enter flavor: ");
        String size = ConsoleHelper.readString(scanner, "Enter size (Cup, Pint, Quart): ");

        double price = switch (size.toLowerCase()) {
            case "pint" -> 6.50;
            case "quart" -> 8.50;
            default -> 3.50;
        };

        IceCream iceCream = new IceCream(flavor, size, price);

        if (ConsoleHelper.readYesNo(scanner, "Add premium toppings?")) {
            while (true) {
                String topping = ConsoleHelper.readString(scanner, "Enter topping (or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addPremiumTopping(topping, 0.75);
            }
        }

        if (ConsoleHelper.readYesNo(scanner, "Add regular toppings?")) {
            while (true) {
                String topping = ConsoleHelper.readString(scanner, "Enter topping (or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addRegularTopping(topping);
            }
        }

        if (ConsoleHelper.readYesNo(scanner, "Make it specialized (+$1.00)?"))
            iceCream.setSpecialized(true);

        order.addIceCream(iceCream);
        printSlow(MINT + "✅ Ice cream added to your order!" + RESET, 20);
        order.displayOrder();
    }

    private void addDrink() {
        printSlow(BLUE + "\n🥤 Add Drink" + RESET, 20);
        Drink.showDrinkMenu();
        String drink = ConsoleHelper.readString(scanner, "Enter drink name: ");
        String size = ConsoleHelper.readString(scanner, "Enter size (Small, Medium, Large): ");
        double price = Drink.getPriceFor(drink, size);
        order.addDrink(new Drink(drink, size, price));
        printSlow(MINT + "✅ Drink added!" + RESET, 20);
        order.displayOrder();
    }

    private void addCookie() {
        printSlow(BROWN + "\n🍪 Add Cookie" + RESET, 20);
        Cookie.showCookieMenu(); // Show available cookie types
        String type = ConsoleHelper.readString(scanner, "Enter cookie type: ");
        String quantity = ConsoleHelper.readString(scanner, "Enter quantity (Each, Half Dozen, Dozen): ");
        double price = Cookie.getPriceFor(type, quantity);
        order.addCookie(new Cookie(type, quantity, price));
        printSlow(MINT + "✅ Cookie added!" + RESET, 20);
        order.displayOrder();
    }

    private void addMilkshake() {
        printSlow(CREAM + "\n🥤 Add Milkshake" + RESET, 20);
        Milkshake.showMilkshakeMenu(); // Show available milkshake flavors
        String flavor = ConsoleHelper.readString(scanner, "Enter flavor: ");
        String size = ConsoleHelper.readString(scanner, "Enter size (Small, Medium, Large): ");
        double price = Milkshake.getPriceFor(flavor, size);
        order.addMilkshake(new Milkshake(flavor, size, price));
        printSlow(MINT + "✅ Milkshake added!" + RESET, 20);
        order.displayOrder();
    }

    private void addSignatureItem() {
        SignatureItem.showSignatureMenu();
        int choice = ConsoleHelper.readInt(scanner, "Enter your choice: ");
        SignatureItem item = SignatureItem.createSignature(choice);
        if (item == null) {
            System.out.println(BROWN + "⚠️ Invalid choice." + RESET);
            return;
        }

        if (ConsoleHelper.readYesNo(scanner, "Would you like to customize it?"))
            item.customize(scanner);

        order.addIceCream(item);
        printSlow(MINT + "✅ Signature item added!" + RESET, 20);
        order.displayOrder();
    }

    private void viewSignatureMenu() {
        System.out.println(PINK + "\n⭐ Signature Creations ⭐" + RESET);
        SignatureItem.showSignatureMenu();
        System.out.println("Select them from the Order Menu to purchase.\n");
    }

    private void checkout() {
        printSlow(CREAM + "\n🧾 Checking out..." + RESET, 25);
        order.checkout(scanner);
    }

    // === Animation helper ===
    private void printSlow(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
}

