package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class IceCream extends Item {
    private String size;
    private boolean specialized;
    private final List<String> premiumToppings = new ArrayList<>();
    private final List<String> regularToppings = new ArrayList<>();

    public IceCream(String name, String size, double price) {
        super(name, price);
        this.size = size;
        this.specialized = false;
    }

    public void addPremiumTopping(String topping, double price) {
        premiumToppings.add(topping);
        this.price += price;
    }

    public void addRegularTopping(String topping) {
        regularToppings.add(topping);
    }

    public void setSpecialized(boolean specialized) {
        this.specialized = specialized;
        if (specialized) this.price += 1.0;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder(size + " " + name);
        List<String> allToppings = new ArrayList<>();
        allToppings.addAll(premiumToppings);
        allToppings.addAll(regularToppings);

        if (!allToppings.isEmpty()) {
            sb.append(" with ").append(String.join(", ", allToppings));
        } else {
            sb.append(" with no toppings");
        }

        if (specialized) sb.append(" [Specialized]");
        sb.append(String.format(" - $%.2f", price));

        return sb.toString();
    }
}





