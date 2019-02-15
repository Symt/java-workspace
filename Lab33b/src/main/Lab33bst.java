package main;

//Lab33bst.java

//The student version of the Lab33b assignment.
import java.util.Scanner;

public class Lab33bst {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the degree of the polynomial: ");
		int deg = sc.nextInt();
		int i = 1;
		PolyNode node = null;
		System.out.print("Enter the coefficient for X^" + deg + " If no term exists, enter 0: ");
		int coeff = sc.nextInt();
		node = new PolyNode(coeff, deg, new PolyNode(0, 0, null));
		while (i <= deg) {
			System.out.print("Enter the coefficient for X^" + (deg - i) + " If no term exists, enter 0: ");
			coeff = sc.nextInt();
			node = new PolyNode(coeff, deg - i, node);
			i++;
		}
		System.out.print("Enter the x value of the polynomial: ");
		int x = sc.nextInt();
		System.out.print("Y = ");
		String plus = " + ";
		int sum = 0;
		while (node.getNext() != null) {
			sum += (node.getCoeff() * Math.pow(x, node.getDegree()));
			if (node.getNext().getNext() == null) {
				plus = "";
			}
			if (node.getDegree() == 0 && node.getCoeff() != 0) {
				System.out.print(node.getCoeff() + plus);
			} else if (node.getDegree() == 1 && node.getCoeff() != 0) {
				System.out.print(node.getCoeff() + "X" + plus);
			} else if (node.getCoeff() == 1) {
				System.out.print("X^" + node.getDegree() + plus);
			} else if (node.getCoeff() != 0) {
				System.out.print(node.getCoeff() + "X^" + node.getDegree() + plus);
			}
			node = node.getNext();
		}
		System.out.println("\nY(" + x + ") = " + sum);
	}
}

class PolyNode {

	private int coeff; // coefficient of each term
	private int degree; // degree of each term
	private PolyNode next; // link to the next term node

	public PolyNode(int c, int d, PolyNode initNext) {
		coeff = c;
		degree = d;
		next = initNext;
	}

	public int getCoeff() {
		return coeff;
	}

	public int getDegree() {
		return degree;
	}

	public PolyNode getNext() {
		return next;
	}

	public void setCoeff(int newCoeff) {
		coeff = newCoeff;
	}

	public void setDegree(int newDegree) {
		degree = newDegree;
	}

	public void setNext(PolyNode newNext) {
		next = newNext;
	}
}