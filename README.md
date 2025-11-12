Ya Ya’s Scoop Shack

Slogan: “Where Every Bite Feels Just Right.”
Language: Java
Type: Console-based Interactive Ice Cream Ordering System

Project Overview

This is a console-based Java application simulating an ice cream shop called Ya Ya’s Scoop Shack. The program allows users to:

Start a new order

Add ice cream, drinks, cookies, milkshakes, or signature items

Customize toppings and sizes

Checkout and generate a formatted receipt

View signature items without ordering

The application uses ANSI color codes to create a visually appealing, retro-style console interface.

Features
1. Interactive Menu

Color-coded and animated menus

Users can navigate main menu and order menu

Options include starting a new order, viewing signature items, and exiting

2. Order Management

Add multiple items (ice cream, drinks, cookies, milkshakes)

Customize toppings (regular & premium) and sizes

Specialized items add $1.00 to the total

Cancel an order anytime before checkout

3. Signature Items

Pre-defined popular items (e.g., Banana Split Boat, Strawberry Crunch Milkshake, Mint Chocolate Magic)

Can be further customized with toppings

Price automatically adjusted for premium toppings

4. Receipt Generation

Saves a formatted receipt to a .txt file

Includes date, time, itemized order, total price, and shop branding

ANSI colors removed for clarity in file output

Classes
Class	Description
Main	Launches the program and starts the user interface
UserInterface	Handles all console interactions, menus, and input
Order	Manages all items in an order and calculates totals
IceCream	Represents ice cream items, sizes, toppings, and specialized status
SignatureItem	Extends IceCream for pre-defined signature creations
Drink	Represents drinks with size-based pricing
Cookie	Represents cookies and pricing by quantity (not shown in snippet)
Milkshake	Represents milkshakes with size-based pricing (not shown in snippet)
ConsoleHelper	Utility class for safe user input (integers, doubles, yes/no, strings)
ReceiptFileManager	Handles saving order receipts to files with proper formatting
Getting Started
Requirements

Java 17+

Console/Terminal that supports ANSI colors (optional for color-coded output)

Running the Program

Compile the project:

javac com/pluralsight/*.java


Run the program:

java com.pluralsight.Main

Usage Example
🍦 Welcome to Ya Ya’s Scoop Shack 🍦
Where Every Bite Feels Just Right.

MAIN MENU
1) Start a New Order
2) View Signature Items
0) Exit
Enter your choice: 1


Users can then add ice cream, drinks, cookies, milkshakes, or signature items.

Customization prompts appear for toppings and specializations.

Checkout generates a receipt in the project folder.

Notes

Premium toppings add $0.75 by default.

Specialized items add $1.00 to the total.

Receipts are saved in the format receipt_YYYYMMDD_HHMMSS.txt.

Console uses colors for fun retro style; receipts are plain text.

Author

Ayanna Smith
Ya Ya’s Scoop Shack Project – Java Capstone
