package com.pluralsight;

import java.util.Scanner;

public class SignatureItem extends IceCream {

    // Constructor for a signature ice cream
    public SignatureItem(String name, String size, double price) {
        super(name, size, price);
    }

    // Show the list of signature items available
    public static void showSignatureMenu() {
        System.out.println("\n⭐ Signature Items:");
        System.out.println("1) Banana Split Boat - Cup - $7.50");
        System.out.println("2) Strawberry Crunch Milkshake - Small - $6.75");
        System.out.println("3) Mint Chocolate Magic - Pint - $8.00");
    }

    // Create a signature item based on user choice
    public static SignatureItem createSignature(int choice) {
        return switch (choice) {
            case 1 -> { // Banana Split Boat
                SignatureItem item = new SignatureItem("Banana Split Boat", "Cup", 7.50);
                item.addRegularTopping("Whipped Cream");
                item.addRegularTopping("Peanuts");
                yield item;
            }
            case 2 -> { // Strawberry Crunch Milkshake
                SignatureItem item = new SignatureItem("Strawberry Crunch Milkshake", "Large", 6.75);
                item.addRegularTopping("Sprinkles");
                item.addPremiumTopping("Cookie Dough Chunks", 0.75);
                yield item;
            }
            case 3 -> { // Mint Chocolate Magic
                SignatureItem item = new SignatureItem("Mint Chocolate Magic", "Pint", 8.00);
                item.addPremiumTopping("Brownie Bites", 0.75);
                item.addRegularTopping("Marshmallows");
                yield item;
            }
            default -> null; // invalid choice
        };
    }

    // Customize toppings for a signature item
    public void customize(Scanner scanner) {
        System.out.println("Customize your signature item! Add or remove toppings (type 'done' when finished):");

        while (true) {
            String topping = ConsoleHelper.readString(scanner, "Enter topping (or 'done'): ");
            if (topping.equalsIgnoreCase("done")) break;

            // Ask if premium or regular
            if (ConsoleHelper.readYesNo(scanner, "Is this a premium topping?")) {
                addPremiumTopping(topping, 0.75);
            } else {
                addRegularTopping(topping);
            }
        }

        // Optionally make it specialized (+$1)
        if (ConsoleHelper.readYesNo(scanner, "Would you like to make it specialized? (+$1.00)")) {
            setSpecialized(true);
        }
    }
}


