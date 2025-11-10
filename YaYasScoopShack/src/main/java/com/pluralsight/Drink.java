package com.pluralsight;

public class Drink {
    private final String name;
    private final String size;
    private final double price;

    public Drink(String name, String size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public String getName() { return name; }
    public String getSize() { return size; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return size + " " + name + " - $" + String.format("%.2f", price);
    }

    public static void showDrinkMenu() {
        System.out.println("=== Drink Menu ===");
        System.out.println("Bottled Water $2.00 $2.50 $3.00");
        System.out.println("Soda $2.00 $2.50 $3.00");
        System.out.println("Iced Coffee $2.50 $3.00 $3.50");
        System.out.println("Lemonade $2.00 $2.50 $3.00");
    }

    public static double getPriceFor(String drinkName, String size) {
        return switch (drinkName.toLowerCase()) {
            case "bottled water", "soda", "lemonade" -> switch (size.toLowerCase()) {
                case "small" -> 2.00;
                case "medium" -> 2.50;
                case "large" -> 3.00;
                default -> 0.0;
            };
            case "iced coffee" -> switch (size.toLowerCase()) {
                case "small" -> 2.50;
                case "medium" -> 3.00;
                case "large" -> 3.50;
                default -> 0.0;
            };
            default -> 0.0;
        };
    }
}
