package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/*
 * This class is provided to you as a *suggested* class to start
 * your project. Feel free to refactor this code as you see fit.
 */
public class VendingMachineCLI {

	Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}

	public void run() {
		// Read CSV file
		// For each line in the file:
		// Split into an array, create new variables for vending machine item properties using array indexes.
		// Create new VendingItem using it's constructor with the properties.
		// Add item to map using the key (index 0) and the VendingItem
		// Open file and check if it exists/is a file
		// Create new VendingMachine using file path
		VendingMachine vendingMachine = new VendingMachine("main.csv");
		// Display main menu in a while loop - only exiting loop when someone selects EXIT
		File salesLog = vendingMachine.turnOn("log.txt");


		boolean backToMenu = true;
		while (backToMenu == true) {
			displayMainMenu();
			System.out.println("\n\n");
			//(1) Display Vending Machine Items
			//(2) Purchase
			//(3) Exit

			// Ask customer to enter number
			System.out.print("Enter menu option: ");
			String input = userInput.nextLine();
			input = input.trim();

			// Make sure choice is valid
			if (input.equals("1")) {
				displayAllItems(vendingMachine.getDisplayList());
				System.out.println("\n\n");
			} else if (input.equals("2")) {
				while (backToMenu == true) {
					displayPurchaseMenu(vendingMachine);
					System.out.println("\n\n");
					System.out.print("Enter menu option: ");
					input = userInput.nextLine().toUpperCase();
					input = input.trim();
					if (input.equals("1")) {
						// Feed money
						try {
							System.out.print("\nInsert your cash: ");
							String moneyString = userInput.nextLine();
							moneyString = moneyString.trim();
							double doubleMoney = Double.parseDouble(moneyString);
							BigDecimal money = new BigDecimal(doubleMoney);
							if (doubleMoney % 1.00 == 0 && doubleMoney >= 1) {
								vendingMachine.feedMoney(money, salesLog);
								System.out.println("\n");
							} else if (doubleMoney % 1.00 != 0) {
								System.out.println("Please enter money in whole dollar amounts.");
							} else {
								throw new NumberFormatException();
							}
						} catch (NumberFormatException e) {
							System.out.println("Money only!");
						}
					} else if (input.equals("2")) {
						// Select Product
						displayInStockItems(vendingMachine.getDisplayList());
						System.out.println("\n\n");
						vendingMachine.selectProduct(salesLog);

					} else if (input.equals("3")) {
						// Finish transaction
						vendingMachine.finishTransaction(salesLog);
						break;
					} else {
						// Invalid input
					}
				}

			} else if (input.equals("3")) {
				vendingMachine.turnOff(salesLog);
				System.out.println("Goodbye!");
				System.exit(2);
			} else {
				// Starts loop again
			}
		}

	}

	public File openFile(String filePath) {
		File inventoryCSV = new File(filePath);

		if (inventoryCSV.exists() == false) {
			System.out.println(filePath + " does not exist");
			System.exit(1);
		} else {
			// File exists and is file. Returns usable file.
		}
		return inventoryCSV;
	}


	// Methods:
	// displayAllItems
	public void displayAllItems(List<String> displayList) {
		for (String currentItem : displayList) {
			System.out.println(currentItem);
		}
	}

	// display displayInstockItems
	public void displayInStockItems(List<String> displayList) {
		for (String currentItem : displayList) {
			if (currentItem.contains("OUT OF STOCK")) {
				// Nothing prints to console
			} else {
				System.out.println(currentItem);
			}
		}
	}

	public void displayMainMenu() {
		System.out.println("(1) Display Vending Machine Items\n(2) Purchase\n(3) Exit");
	}

	public void displayPurchaseMenu(VendingMachine vendingMachine) {
		System.out.println("Current Money Provided: $" + vendingMachine.getCurrentMoney() + "\n(1) Feed Money\n(2) Select Product\n(3) Finish Transaction");
	}


}
