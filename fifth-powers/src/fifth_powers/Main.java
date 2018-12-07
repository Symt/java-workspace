package fifth_powers;

public class Main {

	public static void main(String[] args) {
		int start = 2;
		int end = 1000000;
		int temp_n;
		int digit;
		int total = 0;
		int sum = 0;
		
		for (int i = start; i < end; i++) {
			temp_n = i;
			while (temp_n > 0) {
				digit = temp_n % 10;
				temp_n /= 10;
				
				total += Math.pow(digit, 5);
			}
			if (total == i) {
				sum += total;
			}
			total = 0;
		}
		System.out.println(sum);
	}
	
}
