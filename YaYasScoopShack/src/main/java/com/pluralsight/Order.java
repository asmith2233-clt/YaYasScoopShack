package com.pluralsight;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<IceCream> iceCreams = new ArrayList<>();
    private final List<Drink> drinks = new ArrayList<>();
    private final List<Cookie> cookies = new ArrayList<>();
    private final List<Milkshake> milkshakes = new ArrayList<>();

    public void addIceCream(IceCream iceCream) { iceCreams.add(iceCream); }
    public void addDrink(Drink drink) { drinks.add(drink); }
    public void addCookie(Cookie cookie) { cookies.add(cookie); }
    public void addMilkshake(Milkshake milkshake) { milkshakes.add(milkshake); }

    public List<IceCream> getIceCreams() { return iceCreams; }
    public List<Drink> getDrinks() { return drinks; }
    public List<Cookie> getCookies() { return cookies; }
    public List<Milkshake> getMilkshakes() { return milkshakes; }

    public double calculateTotal() {
        double total = 0;
        for (IceCream i : iceCreams) total += i.getPrice();
        for (Drink d : drinks) total += d.getPrice();
        for (Cookie c : cookies) total += c.getPrice();
        for (Milkshake m : milkshakes) total += m.getPrice();
        return total;
    }

    public void displayOrder() {
        System.out.println("\nYour order includes:");

        for (int i = iceCreams.size() - 1; i >= 0; i--) System.out.println("- " + iceCreams.get(i));
        for (int i = drinks.size() - 1; i >= 0; i--) System.out.println("- " + drinks.get(i));
        for (int i = cookies.size() - 1; i >= 0; i--) System.out.println("- " + cookies.get(i));
        for (int i = milkshakes.size() - 1; i >= 0; i--) System.out.println("- " + milkshakes.get(i));

        System.out.println("TOTAL: $" + String.format("%.2f", calculateTotal()));
    }

    public void cancelOrder() {
        iceCreams.clear();
        drinks.clear();
        cookies.clear();
        milkshakes.clear();
        System.out.println("⚠️ Order canceled.");
    }

    public void writeReceipt(FileWriter writer) throws Exception {
        if (!iceCreams.isEmpty()) {
            writer.write("🍨 Ice Creams:\n");
            for (int i = iceCreams.size() - 1; i >= 0; i--) writer.write("- " + iceCreams.get(i) + "\n");
            writer.write("\n");
        }

        if (!drinks.isEmpty()) {
            writer.write("🥤 Drinks:\n");
            for (int i = drinks.size() - 1; i >= 0; i--) writer.write("- " + drinks.get(i) + "\n");
            writer.write("\n");
        }

        if (!cookies.isEmpty()) {
            writer.write("🍪 Cookies:\n");
            for (int i = cookies.size() - 1; i >= 0; i--) writer.write("- " + cookies.get(i) + "\n");
            writer.write("\n");
        }

        if (!milkshakes.isEmpty()) {
            writer.write("🥛 Milkshakes:\n");
            for (int i = milkshakes.size() - 1; i >= 0; i--) writer.write("- " + milkshakes.get(i) + "\n");
            writer.write("\n");
        }
    }
}
