package com.pluralsight;

import java.util.Scanner;

/**
 * Animated & color-coded User Interface for Ya Ya’s Scoop Shack
 * “Where Every Bite Feels Just Right.”
 *
 * Responsibilities:
 * - Display menus to the user
 * - Handle user input
 * - Add items (Ice Cream, Drinks, Cookies, Milkshakes, Signature Items) to orders
 * - Checkout and cancel orders
 * - Use colored output and slow-printing animation
 */
public class UserInterface {

    // Scanner object to read user input from console
    private final Scanner scanner = new Scanner(System.in);

    // Current order being built
    private final Order order = new Order();

    // ANSI escape codes for colored terminal text
    public static final String RESET = "\u001B[0m";
    public static final String PINK = "\u001B[38;5;218m";
    public static final String MINT = "\u001B[38;5;121m";
    public static final String CREAM = "\u001B[38;5;230m";
    public static final String BROWN = "\u001B[38;5;130m";
    public static final String BLUE = "\u001B[38;5;117m";

    /**
     * Display the main user interface
     * - Animated welcome messages
     * - Main loop until user exits
     */
    public void display() {
        // Slow-print welcome messages with color
        printSlow(PINK + "\n🍦 Welcome to Ya Ya’s Scoop Shack 🍦\n" + RESET, 35);
        printSlow(MINT + "Where Every Bite Feels Just Right.\n" + RESET, 25);

        boolean running = true;

        while (running) {
            showMainMenu(); // Show the main menu options
            int choice = ConsoleHelper.readInt(scanner, MINT + "Enter your choice: " + RESET);

            // Handle user's main menu choice
            switch (choice) {
                case 1 -> startNewOrder(); // Start a new order
                case 2 -> viewSignatureMenu(); // View signature items
                case 0 -> { // Exit the application
                    printSlow(PINK + "\n👋 Thanks for visiting Ya Ya’s Scoop Shack!" + RESET, 25);
                    printSlow(CREAM + "Hope your day is sprinkled with sweetness!\n" + RESET, 20);
                    running = false; // Stops the main loop
                }
                default -> System.out.println(BROWN + "⚠️ Invalid choice, please try again.\n" + RESET);
            }
        }
    }

    /**
     * Display the main menu options to the user
     */
    private void showMainMenu() {
        System.out.println(BLUE + "\n╔════════════════════════╗" + RESET);
        System.out.println(BLUE + "║      MAIN MENU         ║" + RESET);
        System.out.println(BLUE + "╚════════════════════════╝" + RESET);
        System.out.println(PINK + "1)" + RESET + " Start a New Order");
        System.out.println(PINK + "2)" + RESET + " View Signature Items");
        System.out.println(PINK + "0)" + RESET + " Exit");
    }

    /**
     * Start a new order
     * - Displays order menu
     * - Handles adding items or checkout
     * - Allows cancellation
     */
    private void startNewOrder() {
        boolean ordering = true;
        printSlow(MINT + "\nStarting a new order..." + RESET, 30);

        while (ordering) {
            showOrderMenu(); // Show menu specific to the order
            int choice = ConsoleHelper.readInt(scanner, MINT + "Enter your choice: " + RESET);

            switch (choice) {
                case 1 -> addIceCream(); // Add ice cream
                case 2 -> addDrink(); // Add drink
                case 3 -> addCookie(); // Add Side
                case 4 -> addMilkshake(); // Add Side
                case 5 -> addSignatureItem(); // Add signature item
                case 6 -> checkout(); // Checkout current order
                case 0 -> { // Cancel current order
                    order.cancelOrder();
                    ordering = false;
                }
                default -> System.out.println(BROWN + "⚠️ Invalid option. Try again." + RESET);
            }
        }
    }

    /**
     * Show order menu options to user
     */
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

    /**
     * Add an ice cream to the current order
     * Includes handling:
     * - Flavors
     * - Sizes
     * - Premium toppings (+$0.75 each)
     * - Regular toppings (+$0.30 each)
     * - Condiment sauces (+$0.50 each)
     */
    private void addIceCream() {
        printSlow(PINK + "\n🍨 Add Ice Cream" + RESET, 20);
        System.out.println("Available flavors: Vanilla Bean, Chocolate Fudge, Strawberry Swirl, Mint Chocolate Chip");

        String flavor = ConsoleHelper.readString(scanner, "Enter flavor: ");
        String size = ConsoleHelper.readString(scanner, "Enter size (Cup, Pint, Quart): ");

        // Determine base price based on size
        double price = switch (size.toLowerCase()) {
            case "pint" -> 6.50;
            case "quart" -> 8.50;
            default -> 3.50;
        };

        IceCream iceCream = new IceCream(flavor, size, price);

        // PREMIUM TOPPINGS section
        System.out.println("\n✨ Premium Toppings ($0.75 each): Cookie Dough Chunks, Brownie Bites, Cheesecake Bits, Caramel Swirl");
        if (ConsoleHelper.readYesNo(scanner, "Add premium toppings?")) {
            while (true) {
                String topping = ConsoleHelper.readString(scanner, "Enter topping (or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addPremiumTopping(topping, 0.75); // Add topping and price
            }
        }

        // REGULAR TOPPINGS section
        System.out.println("\n🍒 Regular Toppings ($0.30 each): Rainbow Sprinkles, Chocolate Sprinkles, Shredded Coconut, Crushed Oreos, Sugared Strawberries, Whipped Cream, Cherries, Peanuts, Marshmallows");
        if (ConsoleHelper.readYesNo(scanner, "Add regular toppings?")) {
            while (true) {
                String topping = ConsoleHelper.readString(scanner, "Enter topping (or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addRegularTopping(topping, .30); // Add topping and price
            }
        }

        // CONDIMENT SAUCES section
        System.out.println("\n🍯 Condiment Sauces ($0.50 each): Hot Fudge, Caramel Sauce, Strawberry Sauce, Butterscotch Sauce, Peanut Butter Sauce, Marshmallow Cream Sauce");
        if (ConsoleHelper.readYesNo(scanner, "Add condiment sauces?")) {
            while (true) {
                String sauce = ConsoleHelper.readString(scanner, "Enter sauce (or 'done'): ");
                if (sauce.equalsIgnoreCase("done")) break;
                iceCream.addCondimentSauce(sauce, 0.50); // Add sauce and price
            }
        }

        order.addIceCream(iceCream); // Add ice cream to order
        printSlow(MINT + "✅ Ice cream added to your order!" + RESET, 20);
        order.displayOrder(); // Show updated order
    }

    /**
     * Add a drink to the current order
     * - Handles sodas, iced coffee, and other drinks
     * - Adjusts drink name and flavor based on selection
     */
    private void addDrink() {
        printSlow(BLUE + "\n🥤 Add Drink" + RESET, 20);
        Drink.showDrinkMenu();

        String drink = ConsoleHelper.readString(scanner, "Enter drink name: ");

        // Special handling for soda
        if (drink.equalsIgnoreCase("soda")) {
            System.out.println("Available soda flavors: Coke, Sprite, Diet Coke, Fanta");
            String sodaType = ConsoleHelper.readString(scanner, "Select soda type: ");
            drink = sodaType + " Soda";
        }
        // Special handling for iced coffee
        else if (drink.toLowerCase().contains("iced")) {
            System.out.println("Available iced coffee flavors: Regular, Caramel, Mocha, Vanilla");
            String coffeeType = ConsoleHelper.readString(scanner, "Select coffee flavor: ");
            drink = coffeeType + " Iced Coffee";
        }

        String size = ConsoleHelper.readString(scanner, "Enter size (Small, Medium, Large): ");
        double price = Drink.getPriceFor(drink, size);

        order.addDrink(new Drink(drink, size, price));
        printSlow(MINT + "✅ Drink added!" + RESET, 20);
        order.displayOrder();
    }

    /**
     * Add cookies to the current order
     * - Handles type and quantity
     */
    private void addCookie() {
        printSlow(BROWN + "\n🍪 Add Cookie" + RESET, 20);
        Cookie.showCookieMenu();

        String type = ConsoleHelper.readString(scanner, "Enter cookie type: ");
        String quantity = ConsoleHelper.readString(scanner, "Enter quantity (Each, Half Dozen, Dozen): ");
        double price = Cookie.getPriceFor(type, quantity);

        order.addCookie(new Cookie(type, quantity, price));
        printSlow(MINT + "✅ Cookie added!" + RESET, 20);
        order.displayOrder();
    }

    /**
     * Add milkshake to the current order
     */
    private void addMilkshake() {
        printSlow(CREAM + "\n🥤 Add Milkshake" + RESET, 20);
        Milkshake.showMilkshakeMenu();

        String flavor = ConsoleHelper.readString(scanner, "Enter flavor: ");
        String size = ConsoleHelper.readString(scanner, "Enter size (Small, Medium, Large): ");
        double price = Milkshake.getPriceFor(flavor, size);

        order.addMilkshake(new Milkshake(flavor, size, price));
        printSlow(MINT + "✅ Milkshake added!" + RESET, 20);
        order.displayOrder();
    }

    /**
     * Add a signature item to the order
     * - Optionally allows customization
     */
    private void addSignatureItem() {
        SignatureItem.showSignatureMenu();
        int choice = ConsoleHelper.readInt(scanner, "Enter your choice: ");
        SignatureItem item = SignatureItem.createSignature(choice);

        if (item == null) {
            System.out.println(BROWN + "⚠️ Invalid choice." + RESET);
            return;
        }

        // Allow user to customize toppings or make it specialized
        if (ConsoleHelper.readYesNo(scanner, "Would you like to customize it?"))
            item.customize(scanner);

        order.addIceCream(item);
        printSlow(MINT + "✅ Signature item added!" + RESET, 20);
        order.displayOrder();
    }

    /**
     * View signature items (read-only)
     */
    private void viewSignatureMenu() {
        System.out.println(PINK + "\n⭐ Signature Creations ⭐" + RESET);
        SignatureItem.showSignatureMenu();
        System.out.println("Select them from the Order Menu to purchase.\n");
    }

    /**
     * Checkout the current order
     */
    private void checkout() {
        printSlow(CREAM + "\n Checking out..." + RESET, 25);
        order.checkout(scanner);
    }

// Prints text one character at a time, creating a "typing" effect
    private void printSlow(String text, int delay) {

        // Loop through each character in the string
        for (char c : text.toCharArray()) {

            // Print the character without moving to a new line
            System.out.print(c);

            try {
                // Pause (sleep) for the specified number of milliseconds
                // This controls how slow or fast the text prints
                Thread.sleep(delay);

            } catch (InterruptedException e) {
                // If another thread interrupts the sleep, restore interruption state
                Thread.currentThread().interrupt();
            }
        }

        System.out.println();
    }
}
