## Module 1 Capstone - Vending Machine Software

You're developing an application for the newest vending machine distributor,
Umbrella Corp. They've released a new vending machine, Vendo-Matic 800, that's integrated
with everyone's bank accounts, allowing customers to purchase products from their computers for their convenience.

### Application requirements

1. The vending machine dispenses beverages, candy, munchies, and gum.
2. A main menu must display when the software runs, presenting the following options:
    > ```
    > (1) Display Vending Machine Items
    > (2) Purchase
    > (3) Exit
    > ```
3. The vending machine reads its inventory from an input file when the vending machine
starts. 
4. The vending machine is automatically restocked each time the application runs.
5. When the customer selects "(1) Display Vending Machine Items", they're presented
with a list of all items in the vending machine with its quantity remaining:
    - Each vending machine product has a slot identifier and a purchase price.
    - Each slot in the vending machine has enough room for 5 of that product.
    - Every product is initially stocked to the maximum amount.
    - A product that has run out must indicate that it's SOLD OUT.
6. When the customer selects "(2) Purchase", they start with a current money provided of 0.00.
They're guided through the purchasing process menu:
    ```
    Current Money Provided: $2.00
    
    (1) Feed Money
    (2) Select Product
    (3) Finish Transaction
    
    ```
7. The purchase process flow is as follows:
    - Selecting "(1) Feed Money" allows the customer to repeatedly feed money into the
    machine in whole dollar amounts.
        - The "Current Money Provided" indicates how much money the customer
        has fed into the machine.
    - Selecting "(2) Select Product" allows the customer to select a product to
    purchase.
        - Show the list of products available and allow the customer to enter
        a code to select an item.
        - If the product code doesn't exist, the vending machine informs the customer and returns them
        to the Purchase menu.
        - If a product is currently sold out, the vending machine informs the customer and returns them to the
        Purchase menu.
        - If a customer selects a valid product, it's dispensed to the customer.
        - Dispensing an item prints the item name, cost, and the money
        remaining. Dispensing also returns a message:
          - All Munchy items print "Crunch Crunch, Yum!"
          - All Candy items print "Yummy Yummy, So Sweet!"
          - All Drink items print "Glug Glug, Yum!"
          - All Gum items print "Chew Chew, Yum!"
        - After the machine dispenses the product, the machine must update the customer's balance
        accordingly and return the customer to the Purchase menu.
        - To celebrate the end of year, this machine is running a BOGODO sale, buy one
        item, get one dollar off any second item.
            - After an item is purchased with the discount the discount resets. For example, if a third item is purchased a discount IS NOT applied. If a fourth item is purchased the discount DOES apply, and so on.
    - Selecting "(3) Finish Transaction" allows the customer to complete the
    transaction and receive any remaining change.
        - The machine returns the customer's money using nickels, dimes, and quarters
        (using the smallest amount of coins possible).
        - The machine's current balance updates to $0 remaining.
    - After completing their purchase, the user returns to the "Main" menu to
    continue using the vending machine.
8. The vending machine logs all transactions to prevent theft from the vending machine.
   - Each purchase must generate a line in a file called `Log.txt`.
   - The lines must follow the format shown in the following example.
       - The first dollar amount is the amount deposited, spent, or given as change.
       - The second dollar amount is the new balance.
        ```
        01/01/2019 12:00:00 PM FEED MONEY: $5.00 $5.00 
        01/01/2019 12:00:15 PM FEED MONEY: $5.00 $10.00 
        01/01/2019 12:00:20 PM Crunchie B4 $1.75 $8.25 
        01/01/2019 12:01:25 PM Cowtales B2 $1.50 $6.75 
        01/01/2019 12:01:35 PM GIVE CHANGE: $6.75 $0.00
        ```
9. Create as many of your classes as possible to be "testable" classes. Limit console
input and output to as few classes as possible.
10. Optional - Sales Report
    - Provide a "Hidden" menu option on the main menu ("4") that writes to a sales
    report that shows the total sales since the machine started. The name of the
    file must include the date and time so each sales report is uniquely named.
    - An example of the output format appears at the end of this file.
11. Provide unit tests for the following:
    - Objects for your vending machine items are instantiated correctly.
    - Change functionality works as expected.
___
### Vending machine data file
The input file that stocks the vending machine products is a comma-separated values (CSV) file. Each line is a separate product in the file and follows this format:

| Column Name   | Description |
----------------|-------------|
| Slot Location | The slot location in the vending machine containing the product.   |
| Product Name  | The display name of the vending machine product.                   |
| Price         | The purchase price for the product.                                |
| Type          | The product type for this row.                                     |

For example:
```
A1,U-Chews,1.65,Gum
A2,Ginger Ayle,1.85,Drink
A3,Snykkers,4.25,Candy
A4,Chippos,3.85,Munchy
```

**An input file is in your repository: `main.csv`.**
An alternate input file, `alternate.csv` is provided for testing purposes.
#### Challenge
To harden your program against errors, a third file, `problem.csv`, is provided. This file has a number of errors in it. Make sure it does not cause your program to crash. This will require changes to the file reading processing. 

 ---
### Sales report
The output sales report file is pipe-delimited for consistency. Each line is a separate product with the number of sales for the applicable product. At the end of the report is a blank line followed by the **TOTAL SALES** dollar amount indicating the gross sales from the vending machine.

Items purchased at a discount through the BOGODO sale are the last number in the row for each item in the following format:

Taste Elevator Sales Report
item|amount sold full price|amount sold with BOGODO discount

For example:
>```
>Potato Crisps|0|0
>Stackers|1|0
>Grain Waves|0|0
>Cloud Popcorn|0|0
>Moonpie|3|1
>Cowtales|0|0
>Wonka Bar|0|0
>Crunchie|0|0
>Cola|2|1
>Dr. Salt|0|0
>Mountain Melter|0|0
>Heavy|0|0
>U-Chews|0|0
>Little League Chew|1|0
>Chiclets|1|0
>Triplemint|0|0
>
>**TOTAL SALES** $31.05
>```
