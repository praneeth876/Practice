package com.HomePage;

public class PatternModule {

	
	public void pattern() {
		
		 
		        int size = 6; // Adjust the size of the heart by changing this value

		        for (int i = size / 2; i <= size; i += 2) {
		            for (int j = 1; j < size - i; j += 2) {
		                System.out.print(" ");
		            }

		            for (int j = 1; j <= i; j++) {
		                System.out.print("*");
		            }

		            for (int j = 1; j <= size - i; j++) {
		                System.out.print(" ");
		            }

		            for (int j = 1; j <= i; j++) {
		                System.out.print("*");
		            }

		            System.out.println();
		        }

		        for (int i = size; i >= 1; i--) {
		            for (int j = i; j < size; j++) {
		                System.out.print(" ");
		            }

		            for (int j = 1; j <= (i * 2) - 1; j++) {
		                System.out.print("*");
		            }

		            System.out.println();
		        }
		    }
		}

	
	
	

