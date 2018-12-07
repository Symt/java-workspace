package self_powers;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		BigInteger total = new BigInteger("0");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the end number: ");
		int end = sc.nextInt();
		
		for (int i = 1; i <= end; i++) {
			BigInteger base = new BigInteger(Integer.toString(i));
			total = total.add(base.pow(i));
			
			if (i%10000 == 0) {
				System.out.println(i);
			}
		}
		
		System.out.print("Last 10 digits: " + total.mod(new BigInteger("10").pow(10)));
		
		sc.close();
	}
}
