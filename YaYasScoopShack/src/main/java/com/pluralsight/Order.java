package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
    private final List<IceCream> iceCreams = new ArrayList<>();
    private final List<Drink> drinks = new ArrayList<>();
    private final List<Cookie> cookies = new ArrayList<>();
    private final List<Milkshake> milkshakes = new ArrayList<>();

    public static final String PINK = "\u001B[95m";
    public static final String BROWN = "\u001B[38;5;94m";
    public static final String CREAM = "\u001B[38;5;223m";
    public static final String CYAN = "\u001B[96m";
    public static final String RESET = "\u001B[0m";

    // === Getters for ReceiptFileManager ===
    public List<IceCream> getIceCreams() { return iceCreams; }
    public List<Drink> getDrinks() { return drinks; }
    public List<Cookie> getCookies() { return cookies; }
    public List<Milkshake> getMilkshakes() { return milkshakes; }

    public void addIceCream(Scanner scanner) {
        IceCream iceCream = IceCream.createFromInput(scanner);
        iceCreams.add(iceCream);
        System.out.println(PINK + "✅ Ice Cream added!" + RESET);
        displayOrder();
    }

    public void addDrink(Scanner scanner) {
        Drink drink = Drink.createFromInput(scanner);
        drinks.add(drink);
        System.out.println(CYAN + "✅ Drink added!" + RESET);
        displayOrder();
    }

    public void addCookie(Scanner scanner) {
        Cookie cookie = Cookie.createFromInput(scanner);
        cookies.add(cookie);
        System.out.println(BROWN + "✅ Cookie added!" + RESET);
        displayOrder();
    }

    public void addMilkshake(Scanner scanner) {
        Milkshake milkshake = Milkshake.createFromInput(scanner);
        milkshakes.add(milkshake);
        System.out.println(CREAM + "✅ Milkshake added!" + RESET);
        displayOrder();
    }

    public void addSignatureItem(Scanner scanner) {
        SignatureItem.showSignatureMenu();
        int choice = ConsoleHelper.readInt(scanner, "Choose a signature item: ");
        SignatureItem sig = SignatureItem.createSignature(choice);
        if (sig != null) {
            sig.customize(scanner);
            iceCreams.add(sig); // signature items stored as ice cream
            System.out.println(PINK + "✅ Signature Item added!" + RESET);
            displayOrder();
        } else System.out.println("⚠️ Invalid selection.");
    }

    public void displayOrder() {
        System.out.println(PINK + "\nYour Order So Far:" + RESET);
        for (IceCream i : iceCreams) System.out.println(CREAM + "- " + i.getDescription() + RESET);
        for (Drink d : drinks) System.out.println(CYAN + "- " + d.getDescription() + RESET);
        for (Cookie c : cookies) System.out.println(BROWN + "- " + c.getDescription() + RESET);
        for (Milkshake m : milkshakes) System.out.println(CREAM + "- " + m.getDescription() + RESET);

        System.out.printf(PINK + "Current total: $%.2f%n" + RESET, calculateTotal());
    }

    public double calculateTotal() {
        double total = 0;
        for (IceCream i : iceCreams) total += i.getPrice();
        for (Drink d : drinks) total += d.getPrice();
        for (Cookie c : cookies) total += c.getPrice();
        for (Milkshake m : milkshakes) total += m.getPrice();
        return total;
    }

    public void cancelOrder() {
        iceCreams.clear();
        drinks.clear();
        cookies.clear();
        milkshakes.clear();
        System.out.println(PINK + "⚠️ Order canceled." + RESET);
    }

    public void checkout(Scanner scanner) {
        displayOrder();
        if (ConsoleHelper.readYesNo(scanner, "Confirm order?")) {
            ReceiptFileManager.saveReceipt(this);
        } else cancelOrder();
    }
}
