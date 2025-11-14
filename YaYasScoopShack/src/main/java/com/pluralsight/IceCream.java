package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class IceCream extends Item {

    private String size;
    private boolean specialized;
    private final List<String> premiumToppings;
    private final List<String> regularToppings;
    private final List<String> condimentSauces;

    public IceCream(String name, String size, double price) {
        super(name, price);
        this.size = size;
        this.specialized = false;
        this.premiumToppings = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.condimentSauces = new ArrayList<>();
    }

    // === Add premium topping ===
    public void addPremiumTopping(String topping, double price) {
        premiumToppings.add(topping);
        this.price += price;
    }

    // === Add regular topping (FIXED) ===
    public void addRegularTopping(String topping, double price) {
        regularToppings.add(topping);
        this.price += price;
    }

    // === Add Condiment Sauce ===
    public void addCondimentSauce(String sauce, double price) {
        condimentSauces.add(sauce);
        this.price += price;
    }

    // === Specialized ===
    public void setSpecialized(boolean specialized) {
        this.specialized = specialized;
        if (specialized) this.price += 1.00;
    }

    public List<String> getPremiumToppings() {
        return premiumToppings;
    }

    public List<String> getRegularToppings() {
        return regularToppings;
    }

    public List<String> getCondimentSauces() {
        return condimentSauces;
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

        boolean hasAny = !premiumToppings.isEmpty() ||
                !regularToppings.isEmpty() ||
                !condimentSauces.isEmpty();

        if (hasAny) {
            desc.append(" with ");

            if (!premiumToppings.isEmpty()) {
                desc.append(String.join(", ", premiumToppings));
            }

            if (!premiumToppings.isEmpty() && (!regularToppings.isEmpty() || !condimentSauces.isEmpty())) {
                desc.append(", ");
            }

            if (!regularToppings.isEmpty()) {
                desc.append(String.join(", ", regularToppings));
            }

            if (!regularToppings.isEmpty() && !condimentSauces.isEmpty()) {
                desc.append(", ");
            }

            if (!condimentSauces.isEmpty()) {
                desc.append(String.join(", ", condimentSauces));
            }
        }

        return desc + " - $" + String.format("%.2f", price);
    }
}
