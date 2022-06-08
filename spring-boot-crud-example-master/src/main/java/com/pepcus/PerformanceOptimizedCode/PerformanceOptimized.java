package com.pepcus.PerformanceOptimizedCode;

import java.time.Duration;
import java.time.LocalDateTime;

public class PerformanceOptimized {
	public static void main(String[] args) {

		long startTime = System.nanoTime();
		factorialTernaryOperator(15);
		System.out
				.println("========execTime factorialTernaryOperator=========" + (System.nanoTime() - startTime) + "ms");

		factorialLoop(25);

		multiplyArrayValue();

		andOperatior(12, 12);

		withoutAndOperatior(12, 13);

		stringConcatenate();

		stringBuilder();

	}

	public static long factorialTernaryOperator(int number) {

		return number == 1 ? 1 : number * factorialTernaryOperator(number - 1);
	}

	public static long factorialLoop(long num) {
		long startTime = System.nanoTime();
		long result = 1;

		int number = 0;
		for (; number > 0; number--) {
			result *= number;
		}
		System.out.println("========execTime factorialLoop=========" + (System.nanoTime() - startTime) + "ms");
		return result;
	}

	public static void multiplyArrayValue() {
		long startTime = System.nanoTime();
		// Program to Multiply array value by constant
		int constant = 10; // Constant
		int arr[] = { 1, 3, 5, 7, 9 }; // Array
		int size = arr.length;
		int newarr[] = new int[size];
		int i = 0;

		for (i = 0; i < size; i++) {
			// int const=10; //BAD
			newarr[i] = arr[i] * constant;
		}
		System.out.println("========execTime multiplyArrayValue=========" + (System.nanoTime() - startTime) + "ms");

	}

	// Bad ptactise
	public static void andOperatior(int y, int z) {
		long startTime = System.nanoTime();
		int x = 12;
		if (x == y && x == z) {
			// System.out.println("andOperatior condition true");
		} else {
			System.out.println("andOperatior condition false");
		}
		System.out.println("========execTime andOperatior=========" + (System.nanoTime() - startTime) + "ms");
	}

	// BETTER CODE... This will execute faster
	public static void withoutAndOperatior(int y, int z) {
		long startTime = System.nanoTime();
		int x = 12;
		if (x == y) {
			if (x == z) {
				System.out.println("withoutAndOperatior condition true");
			}

		} else {
			System.out.println("withoutAndOperatior condition false");
		}
		System.out.println("========execTime withoutAndOperatior=========" + (System.nanoTime() - startTime) + "ms");
	}

	public static void stringConcatenate() {
		long startTime = System.nanoTime();
		// Program to concatenate
		String constant = "gupta";
		String arr[] = { "rinky", "pinky", "chinky", "tinky", "kinky" }; // Array
		int size = arr.length;
		String newarr[] = new String[size];
		int i = 0;

		for (i = 0; i < size; i++) {
			// int const=10; //BAD
			newarr[i] = arr[i] + constant;

		}
		System.out.println("========execTime string Concatenate=========" + (System.nanoTime() - startTime) + "ms");

	}

	public static void stringBuilder() {
		long startTime = System.nanoTime();
		// Program to concatenate
		StringBuilder sb = new StringBuilder("gupta");
		String arr[] = { "rinky", "pinky", "chinky", "tinky", "kinky" }; // Array
		int size = arr.length;
		StringBuilder newarr[] = new StringBuilder[size];
		int i = 0;

		for (i = 0; i < size; i++) {
			// int const=10; //BAD
			newarr[i] = sb.append(arr[i]);

		}
		System.out.println(
				"========execTime stringBuilder Concatenate=========" + (System.nanoTime() - startTime) + "ms");

	}

}
