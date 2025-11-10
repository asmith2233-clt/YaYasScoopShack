package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class IceCream {
    private final String flavor;
    private final String size;
    private double basePrice;
    private boolean specialized;
    private final List<String> premiumToppings = new ArrayList<>();
    private final List<String> regularToppings = new ArrayList<>();

    public IceCream(String flavor, String size, double basePrice) {
        this.flavor = flavor;
        this.size = size;
        this.basePrice = basePrice;
    }

    // Add premium topping (increases price)
    public void addPremiumTopping(String topping, double price) {
        premiumToppings.add(topping);
        basePrice += price;
    }

    // Add regular topping (included in base price)
    public void addRegularTopping(String topping) {
        regularToppings.add(topping);
    }

    // Specialized option adds $1
    public void setSpecialized(boolean specialized) {
        this.specialized = specialized;
        if (specialized) basePrice += 1.00;
    }

    // Getters
    public String getFlavor() { return flavor; }
    public String getSize() { return size; }
    public double getPrice() { return basePrice; }
    public boolean isSpecialized() { return specialized; }
    public List<String> getPremiumToppings() { return premiumToppings; }
    public List<String> getRegularToppings() { return regularToppings; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append(" ").append(flavor).append(" Ice Cream - $")
                .append(String.format("%.2f", basePrice));
        if (specialized) sb.append(" *Specialized*");
        if (!premiumToppings.isEmpty()) sb.append(" | Premium Toppings: ").append(String.join(", ", premiumToppings));
        if (!regularToppings.isEmpty()) sb.append(" | Regular Toppings: ").append(String.join(", ", regularToppings));
        return sb.toString();
    }
}

