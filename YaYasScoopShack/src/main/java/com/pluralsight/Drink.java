package com.pluralsight;

public class Drink extends Item {
    private String size;

    public Drink(String name, String size, double price) {
        super(name, price);
        this.size = size;
    }

    public static void showDrinkMenu() {
        System.out.println("🥤 Drinks:");
        System.out.println("Bottled Water, Soda, Iced Coffee, Lemonade");
    }

    // Fixed switch expression to remove IDE warning
    public static double getPriceFor(String drinkName, String size) {
        drinkName = drinkName.toLowerCase();
        size = size.toLowerCase();

        switch (drinkName) {
            case "bottled water":
            case "soda":
            case "lemonade":
                return switch (size) {
                    case "small" -> 2.00;
                    case "medium" -> 2.50;
                    case "large" -> 3.00;
                    default -> 2.00;
                };
            case "iced coffee":
                return switch (size) {
                    case "small" -> 2.50;
                    case "medium" -> 3.00;
                    case "large" -> 3.50;
                    default -> 2.50;
                };
            default:
                return 2.00; // fallback price
        }
    }

    @Override
    public String getDescription() {
        return Order.CYAN + size + " " + name + Order.RESET + String.format(" - $%.2f", price);
    }
}
