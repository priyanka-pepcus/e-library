package com.pepcus.PerformanceOptimizedCode;

import java.util.ArrayList;

public class MissingNumber {
	public static void main(String[] args) {

		ArrayList<Integer> numberList = new ArrayList<>();

		ArrayList<Integer> missingNumbers = new ArrayList<>();

		for (int j = 1; j <= 100; j++) {

			numberList.add(j);

		}
		numberList.remove(10);

		System.out.println("Contents of myArraylist: " + numberList);

		// Search missing number
		for (int j = 1; j <= 100; j++)
			if (!numberList.contains(j)) {
				// Put missing numbers into missingNumbers
				missingNumbers.add(j);
			}
		// Print missingNumbers
		System.out.println("Missing numbers is : " + missingNumbers);

	}

}
