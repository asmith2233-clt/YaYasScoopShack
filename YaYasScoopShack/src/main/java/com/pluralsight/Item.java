package com.pluralsight;

public class Item {
    protected String name;
    protected double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getDescription() {
        return name + String.format(" - $%.2f", price);
    }

    public double getPrice() { return price; }
}


