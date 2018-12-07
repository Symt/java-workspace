package vocab;

import java.util.Scanner;
import static java.lang.System.out;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		out.println(recursion("xxhixx"));

		out.print("Enter a string to be reversed: ");
		String toReverse = sc.nextLine();
		out.println("Reversed String: " + reverseString(toReverse));
		sc.close();
	}

	public static int recursion(String str) {
		if (str.length() == 0) {
			return 0;
		}
		int count = 0;
		if (str.charAt(0) == 'x') {
			count++;
		}
		return count + recursion(str.substring(1));
	}

	private int var = 666;

	public int getVar() {
		return var;
	}

	public static String reverseString(String str) {
		return new StringBuilder(str).reverse().toString();
	}

	public int countHi(String str) {
		if (str.indexOf("hi") == -1)
			return 0;
		else if (str.indexOf("hi") == 0)
			return 1 + countHi(str.substring(2));
		else
			return countHi(str.substring(1));
	}
}
