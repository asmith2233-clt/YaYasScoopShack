package com.pluralsight;

import java.util.Scanner;

/**
 * Animated & color-coded User Interface for Ya Ya’s Scoop Shack
 * “Where Every Bite Feels Just Right.”
 */
public class UserInterface {

    private final Scanner scanner = new Scanner(System.in); // Scanner for user input
    private final Order order = new Order(); // Single order instance

    // === ANSI Colors for console styling ===
    public static final String RESET = "\u001B[0m";
    public static final String PINK = "\u001B[38;5;218m";
    public static final String MINT = "\u001B[38;5;121m";
    public static final String CREAM = "\u001B[38;5;230m";
    public static final String BROWN = "\u001B[38;5;130m";
    public static final String BLUE = "\u001B[38;5;117m";

    /**
     * Starts the user interface.
     * Displays welcome message and main menu.
     */
    public void display() {
        printSlow(PINK + "\n🍦 Welcome to Ya Ya’s Scoop Shack 🍦\n" + RESET, 35);
        printSlow(MINT + "Where Every Bite Feels Just Right.\n" + RESET, 25);

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = ConsoleHelper.readInt(scanner, MINT + "Enter your choice: " + RESET);

            switch (choice) {
                case 1 -> startNewOrder(); // Start a new order
                case 2 -> viewSignatureMenu(); // Show signature items
                case 0 -> { // Exit the program
                    printSlow(PINK + "\n👋 Thanks for visiting Ya Ya’s Scoop Shack!" + RESET, 25);
                    printSlow(CREAM + "Hope your day is sprinkled with sweetness!\n" + RESET, 20);
                    running = false;
                }
                default -> System.out.println(BROWN + "⚠️ Invalid choice, please try again.\n" + RESET);
            }
        }
    }

    // Displays the main menu
    private void showMainMenu() {
        System.out.println(BLUE + "\n╔════════════════════════╗" + RESET);
        System.out.println(BLUE + "║      MAIN MENU         ║" + RESET);
        System.out.println(BLUE + "╚════════════════════════╝" + RESET);
        System.out.println(PINK + "1)" + RESET + " Start a New Order");
        System.out.println(PINK + "2)" + RESET + " View Signature Items");
        System.out.println(PINK + "0)" + RESET + " Exit");
    }

    // Starts a new order process
    private void startNewOrder() {
        boolean ordering = true;
        printSlow(MINT + "\nStarting a new order..." + RESET, 30);

        while (ordering) {
            showOrderMenu();
            int choice = ConsoleHelper.readInt(scanner, MINT + "Enter your choice: " + RESET);

            switch (choice) {
                case 1 -> addIceCream();       // Add ice cream
                case 2 -> addDrink();          // Add drink
                case 3 -> addCookie();         // Add cookie
                case 4 -> addMilkshake();      // Add milkshake
                case 5 -> addSignatureItem();  // Add signature item
                case 6 -> checkout();          // Checkout
                case 0 -> { // Cancel current order
                    order.cancelOrder();
                    ordering = false;
                }
                default -> System.out.println(BROWN + "⚠️ Invalid option. Try again." + RESET);
            }
        }
    }

    // Shows the order menu
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

    // Add ice cream to the order
    private void addIceCream() {
        printSlow(PINK + "\n🍨 Add Ice Cream" + RESET, 20);
        System.out.println("Available flavors: Vanilla Bean, Chocolate Fudge, Strawberry Swirl, Mint Chocolate Chip");
        String flavor = ConsoleHelper.readString(scanner, "Enter flavor: ");
        String size = ConsoleHelper.readString(scanner, "Enter size (Cup, Pint, Quart): ");

        // Determine price based on size
        double price = switch (size.toLowerCase()) {
            case "pint" -> 6.50;
            case "quart" -> 8.50;
            default -> 3.50;
        };

        IceCream iceCream = new IceCream(flavor, size, price);

        // Add premium toppings if customer wants
        System.out.println("\n✨ Premium Toppings ($0.75 each): Cookie Dough Chunks, Brownie Bites, Cheesecake Bits, Caramel Swirl");
        if (ConsoleHelper.readYesNo(scanner, "Add premium toppings?")) {
            while (true) {
                String topping = ConsoleHelper.readString(scanner, "Enter topping (or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addPremiumTopping(topping, 0.75);
            }
        }

        // Add regular toppings
        System.out.println("\n🍒 Regular Toppings (Free): Sprinkles, Whipped Cream, Cherries, Peanuts, Marshmallows");
        if (ConsoleHelper.readYesNo(scanner, "Add regular toppings?")) {
            while (true) {
                String topping = ConsoleHelper.readString(scanner, "Enter topping (or 'done'): ");
                if (topping.equalsIgnoreCase("done")) break;
                iceCream.addRegularTopping(topping);
            }
        }

        order.addIceCream(iceCream);
        printSlow(MINT + "✅ Ice cream added to your order!" + RESET, 20);
        order.displayOrder();
    }

    // Method to add a drink to the current order
    private void addDrink() {
        // Print a header for the drink section with blue color and a slight delay for animation
        printSlow(BLUE + "\n🥤 Add Drink" + RESET, 20);

        // Display the drink menu (static method in Drink class)
        Drink.showDrinkMenu();

        // Prompt the user to enter the name of the drink
        // ConsoleHelper.readString(scanner, prompt) reads user input as a String
        String drink = ConsoleHelper.readString(scanner, "Enter drink name: ");

        // Check if the user selected a soda
        if (drink.equalsIgnoreCase("soda")) {
            // If the user typed "soda", show available soda flavors
            System.out.println("Available soda flavors: Coke, Sprite, Diet Coke, Fanta");

            // Prompt the user to select a soda flavor
            String sodaType = ConsoleHelper.readString(scanner, "Select soda type: ");

            // Combine the chosen flavor with "Soda" to create a full drink name
            // Example: "Coke" -> "Coke Soda"
            drink = sodaType + " Soda";

            // Check if the drink is some type of iced beverage (like iced coffee)
        } else if (drink.toLowerCase().contains("iced")) {
            // This handles various inputs like "Iced Coffee", "iced coffee", etc.

            // Show available iced coffee flavors
            System.out.println("Available iced coffee flavors: Regular, Caramel, Mocha, Vanilla");

            // Prompt the user to select one flavor
            String coffeeType = ConsoleHelper.readString(scanner, "Select coffee flavor: ");

            // Combine the flavor with "Iced Coffee" to form the full drink name
            // Example: "Caramel" -> "Caramel Iced Coffee"
            drink = coffeeType + " Iced Coffee";
        }

        // At this point, 'drink' variable now contains the exact name of the drink
        // e.g., "Coke Soda" or "Caramel Iced Coffee"



    String size = ConsoleHelper.readString(scanner, "Enter size (Small, Medium, Large): ");
        double price = Drink.getPriceFor(drink, size);
        order.addDrink(new Drink(drink, size, price));
        printSlow(MINT + "✅ Drink added!" + RESET, 20);
        order.displayOrder();
    }

    // Add a cookie
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

    // Add a milkshake
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

    // Add a signature item
    private void addSignatureItem() {
        SignatureItem.showSignatureMenu();
        int choice = ConsoleHelper.readInt(scanner, "Enter your choice: ");
        SignatureItem item = SignatureItem.createSignature(choice);
        if (item == null) {
            System.out.println(BROWN + "⚠️ Invalid choice." + RESET);
            return;
        }

        // Optional customization
        if (ConsoleHelper.readYesNo(scanner, "Would you like to customize it?"))
            item.customize(scanner);

        order.addIceCream(item);
        printSlow(MINT + "✅ Signature item added!" + RESET, 20);
        order.displayOrder();
    }

    // View the signature item menu
    private void viewSignatureMenu() {
        System.out.println(PINK + "\n⭐ Signature Creations ⭐" + RESET);
        SignatureItem.showSignatureMenu();
        System.out.println("Select them from the Order Menu to purchase.\n");
    }

    // Checkout process
    private void checkout() {
        printSlow(CREAM + "\n Checking out..." + RESET, 25);
        order.checkout(scanner); // calls Order.checkout()
    }

    // Helper for animated text output
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


