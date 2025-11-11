package com.pluralsight;

import java.util.Scanner;

public class SignatureItem extends IceCream {

    public SignatureItem(String name, String size, double price) {
        super(name, size, price);
    }

    public static void showSignatureMenu() {
        System.out.println("\n⭐ Signature Items:");
        System.out.println("1) Banana Split Boat - Cup - $7.50");
        System.out.println("2) Strawberry Crunch Milkshake - Pint - $6.75");
    }

    public static SignatureItem createSignature(int choice) {
        return switch (choice) {
            case 1 -> new SignatureItem("Banana Split Boat", "Cup", 7.50);
            case 2 -> new SignatureItem("Strawberry Crunch Milkshake", "Pint", 6.75);
            default -> null;
        };
    }

    // Allows free customization for SignatureItem
    public void customize(Scanner scanner) {
        System.out.println(Order.BROWN + "Customize toppings (type 'done' when finished):" + Order.RESET);
        while (true) {
            System.out.print(Order.CREAM + "Add topping: " + Order.RESET);
            String t = scanner.nextLine();
            if (t.equalsIgnoreCase("done")) break;
            addCustomTopping(t); // <- now works perfectly
        }
    }
}



