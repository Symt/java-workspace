package stack;

import java.util.ArrayList;

public class runThatDangStack {
	public static void main(String... args) {
		Stack gradeStack = new Stack();
		
		gradeStack.push(50);
		gradeStack.push(100);
		gradeStack.push(65);
		gradeStack.push(43);
		gradeStack.push(78);
		gradeStack.push(99);
		gradeStack.push(67);
		gradeStack.push(83);
		int size = gradeStack.size();
		for(int i = 0; i < size; i++) {
			String grade;
			int temp = (int) gradeStack.pop();
			if (temp >= 90) {
				grade = "A";
			} else if (temp >= 80) {
				grade = "B";
			} else if (temp >= 70) {
				grade = "C";
			} else if (temp >= 60) {
				grade = "D";
			} else {
				grade = "F";
			}
			System.out.println(temp + "\t\t|\t\t" + grade);
			
		}
	}
}

@SuppressWarnings({"unchecked", "rawtypes"})
class Stack {
	ArrayList stack;
	
	public Stack() {
		stack = new ArrayList();
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	public void clear() {
		stack.clear();
	}
	public Object peek() {
		return stack.get(stack.size()-1);
	}
	public String toString() {
		String contents = "";
		for (int i = stack.size()-1; i >= 0; i--) {
			contents += stack.get(i) + ", ";
		}
		return contents.replaceAll(", $", "");
	}
	public void push(Object item) {
		stack.add(item);
	}
	public Object pop() {
		Object temp = peek();
		stack.remove(stack.size()-1);
		return temp;
	}
	public int size() {
		return stack.size();
	}
}
