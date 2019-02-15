package eulers_method_automation;

import java.util.Scanner;

public class Main {

	private float func(float x, float y) {
		return (y-x);
	}
	
	public void euler(float x0, float y, float h, float x) {
		float oldy;
		while (x0 < x) {
			oldy = y;
			y = y + h * func(x0, y);
			System.out.println(y + " = " + oldy + " + " + h + " * f(" + x0 + ", " + oldy + ") | @ x=" + x + ", y=" + y);
			x0 += h;
		}
		System.out.println("Approximate solution at x = " + x + " is " + y);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a valid x0 (initial x): ");
		while (!sc.hasNextFloat()) {
			System.out.print("Enter a valid x0 (initial x): ");
			sc.next();
		}
		float x0 = sc.nextFloat();
		System.out.print("Enter a valid y value: ");
		while (!sc.hasNextFloat()) {
			System.out.print("Enter a valid y value: ");
			sc.next();
		}
		float y = sc.nextFloat();
		System.out.print("Enter the value you want to approximate: ");
		while (!sc.hasNextFloat()) {
			System.out.print("Enter the value you want to approximate: ");
			sc.next();
		}
		float x = sc.nextFloat();
		System.out.print("Enter a step (h): ");
		while (!sc.hasNextFloat() ) {
			System.out.print("Enter a step (h): ");
			sc.next();
		}
		float h = sc.nextFloat();
		sc.close();
		
		new Main().euler(x0, y, h, x);
	}

	
}