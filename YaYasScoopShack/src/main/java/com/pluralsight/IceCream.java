package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IceCream extends Item {
    private String size;
    private boolean specialized;

    // Toppings
    private final List<String> premiumToppings = new ArrayList<>();
    private final List<String> regularToppings = new ArrayList<>();
    private final List<String> customToppings = new ArrayList<>(); // for SignatureItem customization

    public IceCream(String name, String size, double price) {
        super(name, price);
        this.size = size;
    }

    public static IceCream createFromInput(Scanner scanner) {
        return null;
    }

    // Add toppings
    public void addPremiumTopping(String topping, double price) {
        premiumToppings.add(topping);
        this.price += price;
    }

    public void addRegularTopping(String topping) {
        regularToppings.add(topping);
    }

    public void addCustomTopping(String topping) {
        customToppings.add(topping);
    }

    public void setSpecialized(boolean specialized) {
        this.specialized = specialized;
        if (specialized) this.price += 1.00;
    }

    @Override
    public String getDescription() {
        StringBuilder desc = new StringBuilder(size + " " + name + " with ");

        List<String> allToppings = new ArrayList<>();
        allToppings.addAll(premiumToppings);
        allToppings.addAll(regularToppings);
        allToppings.addAll(customToppings);

        if (allToppings.isEmpty()) {
            desc.append("no toppings");
        } else {
            desc.append(String.join(", ", allToppings));
        }

        if (specialized) desc.append(" [Specialized]");

        desc.append(String.format(" - $%.2f", price));
        return desc.toString();
    }

    // Getters for Order display
    public List<String> getPremiumToppings() { return premiumToppings; }
    public List<String> getRegularToppings() { return regularToppings; }
    public List<String> getCustomToppings() { return customToppings; }
    public String getSize() { return size; }
    public boolean isSpecialized() { return specialized; }
}




