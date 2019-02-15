package readstudent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner sc = null;
		ArrayList<String> data = new ArrayList<String>();
		try {
			sc = new Scanner(new File("student.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (sc.hasNext()) {
			data.add(sc.nextLine());
		}
		double[] dataScores = new double[Integer.valueOf(data.get(2))];
		for (int i = 3; i < data.size(); i++) {
			dataScores[i-3] = Double.valueOf(data.get(i));
		}
		System.out.println(new Student(data.get(0), Integer.valueOf(data.get(1)), Integer.valueOf(data.get(2)), dataScores));
	}
}

class Student {
	double[] testScores;
	String name;
	int grade;
	int nTestScores;
	double average;
	double highest;
	double scoresaboveaverage;
	
	public Student(String name, int grade, int nTestScores, double[] testScores) {
		this.name = name;
		this.grade = grade;
		this.nTestScores = nTestScores;
		this.testScores = testScores;
		
		average = Arrays.stream(testScores).average().orElse(Double.NaN);
		highest = Arrays.stream(testScores).max().orElse(Double.NaN);
		for (double i : testScores) {
			if (i > average) {
				scoresaboveaverage++;
			}
		}
	}
	
	public String toString() {
		return name + " is in grade " + grade + "\nMost recent test score is " + testScores[testScores.length-1] + "\nYour " + nTestScores + " tests had an average of " + average + "\nHighest Test Score: " + highest + "\nScores Above Average: " + scoresaboveaverage;
	}
}