package com.pluralsight;

public class Drink {
    private String name;    // Example: Soda, Lemonade, Iced Coffee, Water
    private String size;    // Example: Small, Medium, Large
    private double price;   // Example: 2.00, 2.50, 3.00

    // Constructor
    public Drink(String name, String size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    // For easy printing
    @Override
    public String toString() {
        return size + " " + name + " - $" + String.format("%.2f", price);
    }

    // Static method: shows menu of drink options
    public static void showDrinkMenu() {
        System.out.println("=== Ya Ya’s Scoop Shack Drink Menu ===");
        System.out.println("Drink           Small   Medium   Large");
        System.out.println("--------------------------------------");
        System.out.println("Bottled Water   $2.00   $2.50    $3.00");
        System.out.println("Soda            $2.00   $2.50    $3.00");
        System.out.println("Iced Coffee     $2.50   $3.00    $3.50");
        System.out.println("Lemonade        $2.00   $2.50    $3.00");
        System.out.println();
    }

    // Example method for getting price based on size (if you want to calculate dynamically)
    public static double getPriceFor(String drinkName, String size) {
        double price = 0.0;

        switch (drinkName.toLowerCase()) {
            case "bottled water":
            case "soda":
            case "lemonade":
                if (size.equalsIgnoreCase("Small")) price = 2.00;
                else if (size.equalsIgnoreCase("Medium")) price = 2.50;
                else if (size.equalsIgnoreCase("Large")) price = 3.00;
                break;

            case "iced coffee":
                if (size.equalsIgnoreCase("Small")) price = 2.50;
                else if (size.equalsIgnoreCase("Medium")) price = 3.00;
                else if (size.equalsIgnoreCase("Large")) price = 3.50;
                break;
        }

        return price;
    }
}
