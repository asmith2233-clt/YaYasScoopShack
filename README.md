Ya Ya’s Scoop Shack – Order & Receipt Application
Project Description

The Ya Ya’s Scoop Shack app is a program that helps users manage and track orders for an ice cream shop. It allows users to:

Add ice cream, drinks, cookies, and milkshakes to an order

Customize items with flavors, sizes, and toppings

View the current order in a clear, color-coded format in the terminal

Automatically generate a formatted receipt for each order

Automatically open the receipt file after checkout

This project simulates how a real ice cream shop could operate, giving owners a fun and interactive way to manage orders and receipts directly from the terminal.

Application Screens
Home Screen

The main menu that appears when the application starts. Users can start a new order, view signature items, or exit the app.

Example Output:

🍦 Welcome to Ya Ya’s Scoop Shack 🍦
Where Every Bite Feels Just Right.

╔════════════════════════╗
║      MAIN MENU         ║
╚════════════════════════╝
1) Start a New Order
2) View Signature Items
0) Exit
Enter your choice: 

Order Menu

Users can add ice cream, drinks, cookies, milkshakes, or signature items to their order, then checkout or cancel.

Example Output:

🍧 ORDER MENU 🍧
1) Add Ice Cream
2) Add Drink
3) Add Cookie
4) Add Milkshake
5) Add Signature Item
6) Checkout
0) Cancel Order

Receipt Generation

After checkout, a receipt is saved to a .txt file and automatically opened, showing all items with their prices, subtotal, tax, and total.

Example Receipt Output:

=======================================
         🍦 YA YA’S SCOOP SHACK 🍦
         Where Every Bite Feels Just Right
=======================================
Item                          Price
---------------------------------------
Small Vanilla Bean Cup         $3.50
Medium Iced Coffee (Mocha)    $3.00
Half Dozen Chocolate Chip      $7.50
---------------------------------------
Subtotal:                     $14.00
Sales Tax (7%):                $0.98
TOTAL:                        $14.98
---------------------------------------
Thank you for visiting Ya Ya’s Scoop Shack!
Keep Your Receipt and you get 10% OFF on your NEXT purchase!🍨
Tag Us on Instagram @YaYas_ScoopShack!!!
---------------------------------------
Date/Time: 2025-11-13 12:26:06
=======================================

Interesting Piece of Code

One of the most exciting features is how the app automatically opens the saved receipt file after checkout. This gives users a seamless, real-world experience as if a physical receipt had printed.

// === Auto-open the saved receipt file ===
private static void openReceiptFile(File file) {
    try {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            new ProcessBuilder("notepad.exe", file.getAbsolutePath()).start();
        } else if (os.contains("mac")) {
            new ProcessBuilder("open", file.getAbsolutePath()).start();
        } else {
            new ProcessBuilder("xdg-open", file.getAbsolutePath()).start();
        }
    } catch (IOException e) {
        System.out.println("Could not automatically open the receipt file.");
    }
}


Why it’s interesting:

Detects the operating system and opens the receipt file with the correct application

Works on Windows, macOS, and Linux automatically

Enhances user experience by showing the receipt immediately after checkout

Demonstrates practical use of ProcessBuilder to interact with the operating system

Technologies Used

Language: Java

Packages: java.io, java.util, java.time

File Type: .txt receipts

Tools: IntelliJ IDEA / VS Code

Future Improvements

One improvement would be to implement an automatic running total and order summary in the terminal while building the order. This would allow the shop owner to see totals in real-time before generating a receipt.

Author

Ayanna Smith — Student Project

"I created this ice cream order and receipt app as a step toward running the kind of shop I hope to own one day."
