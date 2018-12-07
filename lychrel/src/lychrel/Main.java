package lychrel;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter how many numbers you want to check: ");
		int check = sc.nextInt();
		System.out.println(checkAll(check));
		
		sc.close();
	}

	public static long checkAll(int end) {
		int totalCount = 0;
		long placeholder = 1;
		while (end > 1) {
			if (isLychrel(placeholder)) {
				totalCount++;
			}
			placeholder++;
			end--;
		}
		return totalCount;
	}
	
	public static boolean isLychrel(long n) {
		for (int i = 0; i < 50; i++) {
			n = n + reverse(n);
			if (isPalindrome(n)) {
				return false;
			}
		}
		System.out.println(n);
		return true;
	}

	public static boolean isPalindrome(long n) {
		return n == reverse(n);
	}

	public static long reverse(long n) {
		long reversed = 0;
		while (n > 0) {

			long temp = n % 10;
			reversed = reversed * 10 + temp;
			n /= 10;

		}

		return reversed;
	}
}
