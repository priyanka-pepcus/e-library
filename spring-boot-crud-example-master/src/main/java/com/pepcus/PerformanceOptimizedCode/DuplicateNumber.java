package com.pepcus.PerformanceOptimizedCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DuplicateNumber {
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<>();
		for(int i = 0; i<1000; i++){
			int number = (int) (i * Math.random());
			al.add(number);
			}
		
		Set<Integer> set = new HashSet<>();
		for(Integer number : al) {
		
			if(set.add(number)) {
				System.out.println("=======Number is ==========="+number);
			}else {
				System.out.println("=======Duplicate Number is ==========="+number);
			}
		}
		
	}
}
