Ya Ya’s Scoop Shack – Order & Receipt Application
Project Description
![IceCreamGIF](https://github.com/user-attachments/assets/8b3f1d63-13b3-41e6-963d-9427954f77b3)

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

<img width="313" height="327" alt="image" src="https://github.com/user-attachments/assets/b67e1b18-524d-40a3-b984-246644779945" />


Order Menu

<img width="243" height="260" alt="image" src="https://github.com/user-attachments/assets/252c8b91-f8c3-4b72-aa29-d259e3ad924a" />


Receipt Generation

After checkout, a receipt is saved to a .txt file and automatically opened, showing all items with their prices, subtotal, tax, and total.

Example Receipt Output:

<img width="973" height="557" alt="image" src="https://github.com/user-attachments/assets/3a38a5c6-ac16-476e-b868-4634513835c0" />


Interesting Piece of Code

One of the most exciting features is how the app automatically opens the saved receipt file after checkout. This gives users a seamless, real-world experience as if a physical receipt had printed.

<img width="783" height="388" alt="image" src="https://github.com/user-attachments/assets/d06e8f0c-cacc-4980-9d41-7da33f7490e3" />


Why it’s interesting:

Detects the operating system and opens the receipt file with the correct application

Works on Windows, macOS, and Linux automatically

Enhances user experience by showing the receipt immediately after checkout

Demonstrates practical use of ProcessBuilder to interact with the operating system

Technologies Used

Problem: Reciept Printing this is what it looked like before I changed it 
<img width="456" height="230" alt="image" src="https://github.com/user-attachments/assets/24220397-5daf-473f-bde1-1ddc8aab3163" /> 

Code that I loved would be adding color to my project giving real ice cream vibes:
<img width="542" height="171" alt="image" src="https://github.com/user-attachments/assets/40041423-4e5e-434f-bb4e-525a2df03c42" />



Language: Java

Packages: java.io, java.util, java.time

File Type: .txt receipts

Tools: IntelliJ IDEA / VS Code

Future Improvements

One improvement would be to implement an automatic running total and order summary in the terminal while building the order. This would allow the shop owner to see totals in real-time before generating a receipt.

Author

Ayanna Smith — Student Project

"I created this ice cream order and receipt app as a step toward running the kind of shop I hope to own one day."
