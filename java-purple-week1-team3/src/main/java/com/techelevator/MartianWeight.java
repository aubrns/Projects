package com.techelevator;

import java.util.Scanner;

/*
In case you've ever pondered how much you weigh on Mars, here's the calculation:
 	Wm = We * 0.378
 	where 'Wm' is the weight on Mars, and 'We' is the weight on Earth
 
Write a command line program which accepts a series of Earth weights from the user  
and displays each Earth weight as itself, and its Martian equivalent.

Enter a series of Earth weights (space-separated): 98 235 185
 
 98 lbs. on Earth is 37 lbs. on Mars.
 235 lbs. on Earth is 88 lbs. on Mars.
 185 lbs. on Earth is 69 lbs. on Mars. 
 */
public class MartianWeight {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.printf("Enter your weight: ", "space-separated");


		String multipleWeightsStrings = scanner.nextLine();
		String[] weightArray = multipleWeightsStrings.split(" ");

		// System.out.printf(weightArray);

		double[] multipleWeightsDouble = new double[weightArray.length];
		for (int i = 0; i < multipleWeightsDouble.length; i++) {
			multipleWeightsDouble[i] = Double.parseDouble(weightArray[i]);
			System.out.println(weightArray[i] + " lbs. on Earth is " + convert(weightArray[i]) + " lbs. on Mars. ");
		}
	}
	public static String convert(String input){
		double earthW = Double.parseDouble(input);
		double marsW = earthW * 0.378;

		return String.valueOf(marsW);
	}
}

