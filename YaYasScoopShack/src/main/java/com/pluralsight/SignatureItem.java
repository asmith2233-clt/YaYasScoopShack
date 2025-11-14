package com.pluralsight;

import java.util.Scanner;

public class SignatureItem extends IceCream {

    public SignatureItem(String name, String size, double price) {
        super(name, size, price);
    }

    public static void showSignatureMenu() {
        System.out.println("\n⭐ Signature Items:");
        System.out.println("1) Banana Split Boat - Cup - $7.50");
        System.out.println("2) Strawberry Crunch Milkshake - Small - $6.75");
        System.out.println("3) Mint Chocolate Magic - Pint - $8.00");
    }

    public static SignatureItem createSignature(int choice) {
        return switch (choice) {
            case 1 -> {
                SignatureItem item = new SignatureItem("Banana Split Boat", "Cup", 7.50);
                item.addRegularTopping("Whipped Cream", 0.30);
                item.addRegularTopping("Peanuts", 0.30);
                yield item;
            }
            case 2 -> {
                SignatureItem item = new SignatureItem("Strawberry Crunch Milkshake", "Small", 6.75);
                item.addRegularTopping("Sprinkles", 0.30);
                item.addPremiumTopping("Cookie Dough Chunks", 0.75);
                yield item;
            }
            case 3 -> {
                SignatureItem item = new SignatureItem("Mint Chocolate Magic", "Pint", 8.00);
                item.addPremiumTopping("Brownie Bites", 0.75);
                item.addRegularTopping("Marshmallows", 0.30);
                yield item;
            }
            default -> null;
        };
    }

    public void customize(Scanner scanner) {
        System.out.println("Customize your signature item! Add or remove toppings (type 'done' when finished):");

        while (true) {
            String topping = ConsoleHelper.readString(scanner, "Enter topping (or 'done'): ");
            if (topping.equalsIgnoreCase("done")) break;

            if (ConsoleHelper.readYesNo(scanner, "Is this a premium topping?")) {
                addPremiumTopping(topping, 0.75);
            } else {
                addRegularTopping(topping, 0.30);
            }
        }

        if (ConsoleHelper.readYesNo(scanner, "Would you like to make it specialized? (+$1.00)")) {
            setSpecialized(true);
        }
    }
}
