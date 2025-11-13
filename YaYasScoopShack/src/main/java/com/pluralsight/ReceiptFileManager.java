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


// INTERESTING PIECE OF CODE
// I remembered seeing this in somebody else's code last capstone and applied it to mine
// This section generates a nicely formatted receipt for an order.
// It writes the receipt to a writer (likely a PrintWriter or BufferedWriter), including
// a header, date & time, items ordered, and the total cost.
// -----------------------------

// Header
            writer.write("""
        ╔════════════════════════════════════════════╗
        ║         🍦  YA YA’S SCOOP SHACK  🍦        ║
        ║        "Where Every Bite Feels Just Right" ║
        ╚════════════════════════════════════════════╝

        """); // Using triple quotes """ to write multiple lines at once
// -----------------------------
// Date & Time Section
// Writing the current date and time on the receipt.
// 'now' is assumed to be a LocalDateTime object representing current time.
            writer.write("Date: " + now.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")) + "\n"); // Example: "November 12, 2025"
            writer.write("Time: " + now.format(DateTimeFormatter.ofPattern("hh:mm a")) + "\n"); // Example: "01:45 PM"
            writer.write("──────────────────────────────────────────\n"); // Decorative separator
            writer.write("Order Receipt\n"); // Title for the order section
            writer.write("──────────────────────────────────────────\n");

// -----------------------------
// Items Section
// Looping through all items in the order and writing them to the receipt.
// Each item is formatted and stripped of any color codes ** Not paying for printed colors on the receipts !!
            for (IceCream i : order.getIceCreams())
                writer.write(formatLine(stripColors(i.getDescription()))); // formatLine aligns text nicely
            for (Drink d : order.getDrinks())
                writer.write(formatLine(stripColors(d.getDescription())));
            for (Cookie c : order.getCookies())
                writer.write(formatLine(stripColors(c.getDescription())));
            for (Milkshake m : order.getMilkshakes())
                writer.write(formatLine(stripColors(m.getDescription())));

// -----------------------------
// Total Section
// Writes the total cost of the order, formatted to 2 decimal places with a dollar sign.
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
