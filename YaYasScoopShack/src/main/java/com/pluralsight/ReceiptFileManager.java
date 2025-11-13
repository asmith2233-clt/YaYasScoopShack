package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReceiptFileManager {

    private static final double SALES_TAX_RATE = 0.07; // 7% sales tax

    // === Called from Order.checkout() ===
    public static void saveReceipt(Order order) {
        List<String> items = new ArrayList<>();
        List<Double> prices = new ArrayList<>();

        // Add all items, drinks, cookies, milkshakes
        order.getIceCreams().forEach(i -> addItem(items, prices, i.getDescription(), i.getPrice()));
        order.getDrinks().forEach(d -> addItem(items, prices, d.getDescription(), d.getPrice()));
        order.getCookies().forEach(c -> addItem(items, prices, c.getDescription(), c.getPrice()));
        order.getMilkshakes().forEach(m -> addItem(items, prices, m.getDescription(), m.getPrice()));

        double subtotal = order.calculateTotal();

        // Generate unique filename using timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = "Receipt_" + timestamp + ".txt";

        printReceipt(items, prices, subtotal, filename);

        System.out.println("🧾 Receipt saved to " + filename);
    }

    // === Add item and price after cleaning description ===
    private static void addItem(List<String> items, List<Double> prices, String description, double price) {
        items.add(stripColors(cleanDescription(description)));
        prices.add(price);
    }

    // === Remove color codes from console text ===
    private static String stripColors(String text) {
        return text.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    // === Remove any price already in the description ===
    private static String cleanDescription(String desc) {
        return desc.replaceAll("\\s*-\\s*\\$[0-9]+\\.?[0-9]*", "");
    }

    // === Print receipt to its own file ===
    private static void printReceipt(List<String> items, List<Double> prices, double subtotal, String filename) {
        File file = new File(filename);

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, false))) {

            writer.println("=======================================");
            writer.println("         🍦 YA YA’S SCOOP SHACK 🍦");
            writer.println("         Where Every Bite Feels Just Right ");
            writer.println("=======================================");
            writer.println(String.format("%-30s %s", "Item", "Price"));
            writer.println("---------------------------------------");

            for (int i = 0; i < items.size(); i++) {
                writer.printf("%-30s $%.2f%n", items.get(i), prices.get(i));
            }

            writer.println("---------------------------------------");
            writer.printf("%-30s $%.2f%n", "Subtotal:", subtotal);

            double tax = subtotal * SALES_TAX_RATE;
            writer.printf("%-30s $%.2f%n", "Sales Tax (7%):", tax);

            double total = subtotal + tax;
            writer.printf("%-30s $%.2f%n", "TOTAL:", total);

            writer.println("---------------------------------------");
            writer.println("Thank you for visiting Ya Ya’s Scoop Shack!");
            writer.println("Keep Your Receipt and you get 10% OFF on your NEXT purchase!🍨");
            writer.println("Tag Us on Instagram @YaYas_ScoopShack!!!");
            writer.println("---------------------------------------");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            writer.println("Date/Time: " + dtf.format(LocalDateTime.now()));
            writer.println("=======================================\n");

            writer.flush();

            openReceiptFile(file);

        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
 //Interesting Piece of Code
    // === Auto-open the saved receipt file ==
// This method tries to automatically open a saved receipt file
// using the system's default application for text files.
    private static void openReceiptFile(File file) {
        try {
            // Get the operating system name (e.g., "Windows 10", "Mac OS X", "Linux")
            String os = System.getProperty("os.name").toLowerCase();

            // Check if the OS is Windows
            if (os.contains("win")) {
                // On Windows, use Notepad to open the file
                // ProcessBuilder launches external processes/commands
                new ProcessBuilder("notepad.exe", file.getAbsolutePath()).start();

                // Check if the OS is macOS
            } else if (os.contains("mac")) {
                // On Mac, use the 'open' command which opens files with the default app
                new ProcessBuilder("open", file.getAbsolutePath()).start();

                // If not Windows or Mac, assume Linux/Unix
            } else {
                // On Linux, use 'xdg-open' to open the file with the default app
                new ProcessBuilder("xdg-open", file.getAbsolutePath()).start();
            }

            // Catch any IOException that may occur when trying to start the process
        } catch (IOException e) {
            // If something goes wrong (e.g., file doesn't exist, command not found), print an error
            System.out.println("Could not automatically open the receipt file.");
        }
    }
}

