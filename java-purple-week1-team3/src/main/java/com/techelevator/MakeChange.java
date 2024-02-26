package com.techelevator;

import java.util.Scanner;

/*
 Write a command line program which prompts the user for the total bill, and the amount tendered. It should then
 display the change required.

 Please enter the amount of the bill: 23.65
 Please enter the amount tendered: 100.00
 The change required is 76.35
 */
public class MakeChange {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.printf("Enter bill here: $");
		double bill = scanner.nextDouble();

		System.out.printf("Enter tendered here: $");
		double tendered = scanner.nextDouble();

		double change = tendered - bill;
		System.out.println("Your change is: $" + change);


	}

}
