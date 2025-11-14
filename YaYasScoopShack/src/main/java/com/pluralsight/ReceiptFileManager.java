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

        printReceipt(items, prices, subtotal, filename, order);

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
    private static void printReceipt(List<String> items, List<Double> prices, double subtotal,
                                     String filename, Order order) {

        File file = new File(filename);

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, false))) {

            writer.println("=======================================");
            writer.println("         🍦 YA YA’S SCOOP SHACK 🍦");
            writer.println("         Where Every Bite Feels Just Right ");
            writer.println("=======================================");
            writer.println(String.format("%-30s %s", "Item", "Price"));
            writer.println("---------------------------------------");

            int iceCreamIndex = 0; // NEW counter added for topping tracking

            for (int i = 0; i < items.size(); i++) {

                writer.printf("%-30s $%.2f%n", items.get(i), prices.get(i));

                // ===== TOPPING DETAILS (Added Section) =====
                if (items.get(i).toLowerCase().contains("ice cream")) {

                    var iceCream = order.getIceCreams().get(iceCreamIndex);
                    iceCreamIndex++;

                    // Regular toppings
                    if (!iceCream.getRegularToppings().isEmpty()) {
                        writer.printf("   - Regular Toppings (%d × $0.30)%n",
                                iceCream.getRegularToppings().size());
                        for (String t : iceCream.getRegularToppings()) {
                            writer.println("      • " + t);
                        }
                    }

                    // Premium toppings
                    if (!iceCream.getPremiumToppings().isEmpty()) {
                        writer.printf("   - Premium Toppings (%d × $0.75)%n",
                                iceCream.getPremiumToppings().size());
                        for (String t : iceCream.getPremiumToppings()) {
                            writer.println("      • " + t);
                        }
                    }

                    // Sauces
                    if (!iceCream.getCondimentSauces().isEmpty()) {
                        writer.printf("   - Sauces (%d × $0.50)%n",
                                iceCream.getCondimentSauces().size());
                        for (String s : iceCream.getCondimentSauces()) {
                            writer.println("      • " + s);
                        }
                    }
                }
                // ===== END OF ADDED SECTION =====
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
    // Automatically opens the saved receipt file using the system's default text editor
    private static void openReceiptFile(File file) {
        try {
            // Get the name of the operating system (ex: "Windows 10", "Mac OS X", "Linux")
            String os = System.getProperty("os.name").toLowerCase();

            // --- WINDOWS ---
            // If the OS name contains "win", we assume it is a Windows system
            if (os.contains("win")) {
                // Use Notepad to open the file
                new ProcessBuilder("notepad.exe", file.getAbsolutePath()).start();

                // --- MACOS ---
            } else if (os.contains("mac")) {
                // "open" launches files using the default Mac application
                new ProcessBuilder("open", file.getAbsolutePath()).start();

                // --- LINUX / UNIX ---
            } else {
                // "xdg-open" opens files using the default Linux text editor
                new ProcessBuilder("xdg-open", file.getAbsolutePath()).start();
            }

        } catch (IOException e) {
            // If anything goes wrong (file missing, OS command unavailable, etc.)
            System.out.println("Could not automatically open the receipt file.");
        }
    }

}
