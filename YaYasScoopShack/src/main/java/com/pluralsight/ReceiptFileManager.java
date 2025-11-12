package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFileManager {

    public static void saveReceipt(Order order) {
        LocalDateTime now = LocalDateTime.now();
        String filename = "receipt_" + now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";

        try (FileWriter writer = new FileWriter(filename)) {

            // Header
            writer.write("""
                    ╔════════════════════════════════════════════╗
                    ║         🍦  YA YA’S SCOOP SHACK  🍦        ║
                    ║        "Where Every Bite Feels Just Right" ║
                    ╚════════════════════════════════════════════╝

                    """);

            // Date & Time
            writer.write("Date: " + now.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")) + "\n");
            writer.write("Time: " + now.format(DateTimeFormatter.ofPattern("hh:mm a")) + "\n");
            writer.write("──────────────────────────────────────────\n");
            writer.write("Order Receipt\n");
            writer.write("──────────────────────────────────────────\n");

            // Items
            for (IceCream i : order.getIceCreams())
                writer.write(formatLine(stripColors(i.getDescription())));
            for (Drink d : order.getDrinks())
                writer.write(formatLine(stripColors(d.getDescription())));
            for (Cookie c : order.getCookies())
                writer.write(formatLine(stripColors(c.getDescription())));
            for (Milkshake m : order.getMilkshakes())
                writer.write(formatLine(stripColors(m.getDescription())));

            writer.write("──────────────────────────────────────────\n");
            writer.write(String.format("%-35s %8s%n", "TOTAL:", String.format("$%.2f", order.calculateTotal())));
            writer.write("──────────────────────────────────────────\n\n");

            // Footer
            writer.write("""
                    🍧 Thank you for visiting Ya Ya’s Scoop Shack! 🍧
                    Come back soon for another sweet treat! 🍓🍫🍪

                    Keep Your Receipt and you get 50% OFF your NEXT PURCHASE!!!

                    📍 123 Sundae Lane
                    ☎️  (555) SCOOP-IT
                    💕  www.YaYasScoopShack.com
                    """);

            System.out.println("Receipt saved to " + filename);

        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }

    // Format item description with aligned price
    private static String formatLine(String description) {
        int priceIndex = description.lastIndexOf("$");
        if (priceIndex > 0) {
            String item = description.substring(0, priceIndex).trim();
            String price = description.substring(priceIndex).trim();
            return String.format("%-35s %8s%n", item, price);
        } else {
            return description + "\n";
        }
    }

    // Remove all ANSI escape sequences (colors)
    private static String stripColors(String text) {
        return text.replaceAll("\u001B\\[[;\\d]*m", "");
    }
}
