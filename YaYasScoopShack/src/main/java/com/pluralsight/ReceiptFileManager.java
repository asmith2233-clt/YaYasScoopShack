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

            // 🎀 Header
            writer.write("""
                    ╔════════════════════════════════════════════╗
                    ║         🍦  YA YA’S SCOOP SHACK  🍦        ║
                    ║        "Where Every Bite Feels Just Right" ║
                    ╚════════════════════════════════════════════╝
                    
                    """);

            // 🕒 Date and Time of Purchase
            writer.write("Date: " + now.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")) + "\n");
            writer.write("Time: " + now.format(DateTimeFormatter.ofPattern("hh:mm a")) + "\n");
            writer.write("──────────────────────────────────────────\n");
            writer.write("Order Receipt\n");
            writer.write("──────────────────────────────────────────\n");

            // 🧁 Ordered Items
            for (IceCream i : order.getIceCreams())
                writer.write(formatLine(i.getDescription()));
            for (Drink d : order.getDrinks())
                writer.write(formatLine(d.getDescription()));
            for (Cookie c : order.getCookies())
                writer.write(formatLine(c.getDescription()));
            for (Milkshake m : order.getMilkshakes())
                writer.write(formatLine(m.getDescription()));

            writer.write("──────────────────────────────────────────\n");
            writer.write(String.format("%-30s %10s%n", "TOTAL:", String.format("$%.2f", order.calculateTotal())));
            writer.write("──────────────────────────────────────────\n\n");

            // Footer
            writer.write("""
                    🍧 Thank you for visiting Ya Ya’s Scoop Shack! 🍧
                    Come back soon for another sweet treat! 🍓🍫🍪
                    
                    Keep Your Receipt and you get 50% OFF yur NEXT PURCHASE!!!
                    
                    📍 123 Sundae Lane
                    ☎️  (555) SCOOP-IT
                    💕  www.YaYasScoopShack.com
                    """);

            System.out.println("Receipt saved to " + filename);

        } catch (IOException e) {
            System.out.println(" Error saving receipt: " + e.getMessage());
        }
    }

    // Helper method to format each line neatly
    private static String formatLine(String description) {
        int priceIndex = description.lastIndexOf("$");
        if (priceIndex > 0) {
            String item = description.substring(0, priceIndex).trim();
            String price = description.substring(priceIndex).trim();
            return String.format("%-30s %10s%n", item, price);
        } else {
            return description + "\n";
        }
    }
}
