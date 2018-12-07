package sqrt;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Sqrt {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int number = -1;

		// Validation
		while (number < 0) {
			System.out.print("Enter a valid number: ");
			while (!sc.hasNextInt()) {
				sc.next();
				System.out.print("Enter a valid number: ");
			}
			number = sc.nextInt();
		}

		// There are many ways to round numbers, but this is one of the easiest.
		DecimalFormat f = new DecimalFormat("##.00");
		System.out.println("The square root of " + number + " is " + f.format((double) Math.sqrt(number)));

		sc.close();
	}
}
