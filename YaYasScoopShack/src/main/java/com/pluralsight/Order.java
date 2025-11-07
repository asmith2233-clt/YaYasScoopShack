package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<IceCream> iceCreams = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Cookie> cookies = new ArrayList<>();
    private List<Milkshake> milkshakes = new ArrayList<>();

    public void addIceCream(IceCream iceCream) {
        iceCreams.add(iceCream);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addCookie(Cookie cookie) {
        cookies.add(cookie);
    }

    public void addMilkshake(Milkshake milkshake) {
        milkshakes.add(milkshake);
    }

    public List<IceCream> getIceCreams() {
        return iceCreams;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public List<Milkshake> getMilkshakes() {
        return milkshakes;
    }

    public double calculateTotal() {
        double total = 0;
        for (IceCream i : iceCreams) total += i.getPrice();
        for (Drink d : drinks) total += d.getPrice();
        for (Cookie c : cookies) total += c.getPrice();
        for (Milkshake m : milkshakes) total += m.getPrice();
        return total;
    }

    public void displayOrder() {
        System.out.println("Your order includes:");
        for (IceCream i : iceCreams) {
            System.out.println("- Ice Cream: " + i.getFlavor() + " (" + i.getSize() + ") $" + i.getPrice());
        }
        for (Drink d : drinks) {
            System.out.println("- Drink: " + d.getName() + " (" + d.getSize() + ") $" + d.getPrice());
        }
        for (Cookie c : cookies) {
            System.out.println("- Cookie: " + c.getType() + " (" + c.getQuantity() + ") $" + c.getPrice());
        }
        for (Milkshake m : milkshakes) {
            System.out.println("- Milkshake: " + m.getFlavor() + " (" + m.getSize() + ") $" + m.getPrice());
        }
        System.out.printf("TOTAL: $%.2f\n", calculateTotal());
    }

    public void cancelOrder() {
        iceCreams.clear();
        drinks.clear();
        cookies.clear();
        milkshakes.clear();
        System.out.println("Order canceled.");
    }

    }


