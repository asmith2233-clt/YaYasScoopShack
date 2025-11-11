package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFileManager {

    public static void saveReceipt(Order order) {
        String filename = "receipt_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("🍦 YA YA’S SCOOP SHACK RECEIPT 🍦\n");
            writer.write("--------------------------------------\n");

            for (IceCream i : order.getIceCreams())
                writer.write("- " + i.getDescription() + "\n");
            for (Drink d : order.getDrinks())
                writer.write("- " + d.getDescription() + "\n");
            for (Cookie c : order.getCookies())
                writer.write("- " + c.getDescription() + "\n");
            for (Milkshake m : order.getMilkshakes())
                writer.write("- " + m.getDescription() + "\n");

            writer.write("--------------------------------------\n");
            writer.write(String.format("Total: $%.2f%n", order.calculateTotal()));
            writer.write("Thank you for visiting Ya Ya’s Scoop Shack!\n");

            System.out.println("🧾 Receipt saved to " + filename);
        } catch (IOException e) {
            System.out.println("❗ Error saving receipt: " + e.getMessage());
        }
    }
}
