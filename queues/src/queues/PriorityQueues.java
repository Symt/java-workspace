package queues;

import java.util.PriorityQueue;

public class PriorityQueues {
	public static void main(String[] args) {
		PriorityQueue<Student> queue = new PriorityQueue<Student>();

		queue.add(new Student("John", 3.83));
		queue.add(new Student("Steve", 1.23));
		queue.add(new Student("Bob", 2.93));
		queue.add(new Student("Parker", 4.00));
		queue.add(new Student("Loser", 0.69));
		
		System.out.println(queue);
	}
	
}


class Student implements Comparable<Student> {

	double gpa;
	String name;
	
	public Student(String name, double gpa) {
		this.name = name;
		this.gpa = gpa;
	}
	
	public int compareTo(Student s) {
		return (this.gpa == s.gpa) ? 0 : (this.gpa < s.gpa) ? 1 : -1;
	}
	
	public String toString() {
		return "[" + name + ", " + gpa  + "]";
	}
}