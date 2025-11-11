package com.pluralsight;

import java.util.Scanner;

public class ConsoleHelper {

    // Reads an integer safely
    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❗ Invalid number, try again.");
            }
        }
    }

    // Reads a double safely (handles "$6" or "6.00")
    public static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().replace("$", "").trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("❗ Please enter a valid amount (like 3.50).");
            }
        }
    }

    // Reads a yes/no answer
    public static boolean readYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " (yes/no): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals("yes") || answer.equals("y")) return true;
            if (answer.equals("no") || answer.equals("n")) return false;
            System.out.println("❗ Please answer yes or no.");
        }
    }
}
