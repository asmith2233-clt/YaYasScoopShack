package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {

    private final List<IceCream> iceCreams = new ArrayList<>();
    private final List<Drink> drinks = new ArrayList<>();
    private final List<Cookie> cookies = new ArrayList<>();
    private final List<Milkshake> milkshakes = new ArrayList<>();

    // ANSI color codes
    public static final String PINK = "\u001B[95m";
    public static final String CREAM = "\u001B[38;5;223m";
    public static final String BROWN = "\u001B[38;5;94m";
    public static final String CYAN = "\u001B[96m";
    public static final String RESET = "\u001B[0m";

    // === Add items to order ===
    public void addIceCream(IceCream iceCream) { if (iceCream != null) iceCreams.add(iceCream); }
    public void addDrink(Drink drink) { if (drink != null) drinks.add(drink); }
    public void addCookie(Cookie cookie) { if (cookie != null) cookies.add(cookie); }
    public void addMilkshake(Milkshake milkshake) { if (milkshake != null) milkshakes.add(milkshake); }

    // === Calculate total ===
    public double calculateTotal() {
        double total = 0;
        for (IceCream i : iceCreams) total += i.getPrice();
        for (Drink d : drinks) total += d.getPrice();
        for (Cookie c : cookies) total += c.getPrice();
        for (Milkshake m : milkshakes) total += m.getPrice();
        return total;
    }

    // === Display order with colors ===
    public void displayOrder() {
        System.out.println(PINK + "\nYour Order So Far:" + RESET);

        for (IceCream i : iceCreams)
            System.out.println(CREAM + "- " + i.getDescription() + RESET);

        for (Drink d : drinks)
            System.out.println(CYAN + "- " + d.getDescription() + RESET);

        for (Cookie c : cookies)
            System.out.println(BROWN + "- " + c.getDescription() + RESET);

        for (Milkshake m : milkshakes)
            System.out.println(CREAM + "- " + m.getDescription() + RESET);

        System.out.printf(PINK + "Current total: $%.2f%n" + RESET, calculateTotal());
    }

    // === Cancel order ===
    public void cancelOrder() {
        iceCreams.clear();
        drinks.clear();
        cookies.clear();
        milkshakes.clear();
        System.out.println(PINK + "⚠️ Order canceled." + RESET);
    }

    // === Checkout ===
    public void checkout(Scanner scanner) {
        displayOrder(); // keeps colored console output
        if (ConsoleHelper.readYesNo(scanner, "Confirm order?")) {
            try {
                File folder = new File("receipts");
                if (!folder.exists()) folder.mkdir();

                String filename = "receipts/receipt_" + System.currentTimeMillis() + ".txt";
                FileWriter writer = new FileWriter(filename);

                writer.write("🍦 YA YA’S SCOOP SHACK RECEIPT 🍦\n");
                writer.write("--------------------------------------\n");

                for (IceCream i : iceCreams)
                    writer.write("- " + stripColors(i.getDescription()) + "\n");
                for (Drink d : drinks)
                    writer.write("- " + stripColors(d.getDescription()) + "\n");
                for (Cookie c : cookies)
                    writer.write("- " + stripColors(c.getDescription()) + "\n");
                for (Milkshake m : milkshakes)
                    writer.write("- " + stripColors(m.getDescription()) + "\n");

                writer.write("--------------------------------------\n");
                writer.write(String.format("TOTAL: $%.2f%n", calculateTotal()));
                writer.write("Thank you for visiting Ya Ya’s Scoop Shack!\n");

                writer.close();

                System.out.println(PINK + "✅ Order confirmed! Receipt saved to: " + filename + RESET);
            } catch (IOException e) {
                System.out.println("❗ Error saving receipt: " + e.getMessage());
            }
        } else {
            cancelOrder();
        }
    }

    // === Strip ANSI codes for receipts ===
    private String stripColors(String text) {
        return text.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    // === Getters for future use ===
    public List<IceCream> getIceCreams() { return iceCreams; }
    public List<Drink> getDrinks() { return drinks; }
    public List<Cookie> getCookies() { return cookies; }
    public List<Milkshake> getMilkshakes() { return milkshakes; }
}
