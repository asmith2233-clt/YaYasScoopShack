package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class IceCream {
    private String flavor;
    private String size;
    private double basePrice;
    private boolean specialized;
    private List<String> toppings = new ArrayList<>();

    public IceCream(String flavor, String size, double basePrice) {
        this.flavor = flavor;
        this.size = size;
        this.basePrice = basePrice;
    }

    public void addTopping(String topping, double price) {
        toppings.add(topping);
        basePrice += price;
    }

    public void setSpecialized(boolean specialized) {
        this.specialized = specialized;
        if (specialized) {
            basePrice += 1.00; // add extra for customization
        }
    }

    public String getFlavor() {
        return flavor;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return basePrice;
    }

    public boolean isSpecialized() {
        return specialized;
    }
}

