package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class IceCream extends Item {

    private String size;
    private boolean specialized;
    private final List<String> premiumToppings;
    private final List<String> regularToppings;

    public IceCream(String name, String size, double price) {
        super(name, price);
        this.size = size;
        this.specialized = false;
        this.premiumToppings = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
    }

    // === Add toppings ===
    public void addPremiumTopping(String topping, double price) {
        premiumToppings.add(topping);
        this.price += price; // increase price for premium topping
    }

    public void addRegularTopping(String topping) {
        regularToppings.add(topping);
    }

    // === Set specialized (+$1) ===
    public void setSpecialized(boolean specialized) {
        this.specialized = specialized;
        if (specialized) this.price += 1.00;
    }

    // === Getters for UserInterface ===
    public List<String> getPremiumToppings() {
        return premiumToppings;
    }

    public List<String> getRegularToppings() {
        return regularToppings;
    }

    public String getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        StringBuilder desc = new StringBuilder(size + " " + name);
        if (!premiumToppings.isEmpty() || !regularToppings.isEmpty()) {
            desc.append(" with ");
            if (!premiumToppings.isEmpty()) {
                desc.append(String.join(", ", premiumToppings));
            }
            if (!premiumToppings.isEmpty() && !regularToppings.isEmpty()) {
                desc.append(", ");
            }
            if (!regularToppings.isEmpty()) {
                desc.append(String.join(", ", regularToppings));
            }
        }
        return desc + " - $" + String.format("%.2f", price);
    }
}
