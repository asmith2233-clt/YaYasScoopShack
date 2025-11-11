package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFileManager {

    private static final String RECEIPTS_FOLDER = "receipts";

    /**
     * Saves a formatted receipt for a given order
     *
     * @param order the completed order
     * @return the filename where the receipt was saved, or null if it failed
     */
    public static String saveReceipt(Order order) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String filename = RECEIPTS_FOLDER + "/receipt-" + now.format(formatter) + ".txt";

        try {
            // Create receipts folder if it doesn't exist
            File directory = new File(RECEIPTS_FOLDER);
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Write to file
            writeReceiptToFile(filename, order, now);
            return filename;

        } catch (IOException e) {
            System.out.println("⚠️ Error saving receipt: " + e.getMessage());
            return null;
        }
    }

    /**
     * Handles all receipt formatting and writing to file
     */
    private static void writeReceiptToFile(String filename, Order order, LocalDateTime orderTime) throws IOException {
        try (FileWriter fw = new FileWriter(filename);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println("=========================================");
            pw.println("        Ya Ya’s Scoop Shack Receipt      ");
            pw.println("=========================================");
            pw.println("Date: " + orderTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")));
            pw.println("=========================================\n");

            // Ice Creams
            if (!order.getIceCreams().isEmpty()) {
                pw.println("🍨 Ice Creams:");
                for (var i : order.getIceCreams()) pw.println("- " + i);
                pw.println();
            }

            // Drinks
            if (!order.getDrinks().isEmpty()) {
                pw.println("🥤 Drinks:");
                for (var d : order.getDrinks()) pw.println("- " + d);
                pw.println();
            }

            // Cookies
            if (!order.getCookies().isEmpty()) {
                pw.println("🍪 Cookies:");
                for (var c : order.getCookies()) pw.println("- " + c);
                pw.println();
            }

            // Milkshakes
            if (!order.getMilkshakes().isEmpty()) {
                pw.println("🥛 Milkshakes:");
                for (var m : order.getMilkshakes()) pw.println("- " + m);
                pw.println();
            }

            pw.println("=========================================");
            pw.printf("%-30s $%6.2f%n", "TOTAL:", order.calculateTotal());
            pw.println("=========================================");
            pw.println("Thank you for visiting Ya Ya’s Scoop Shack! 🍦");
            pw.println("=========================================");
        }
    }

    public static String saveReceipt(Order order, double amountPaid) {
        return "";
    }
}
