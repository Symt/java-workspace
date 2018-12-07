package queues;

import java.util.ArrayList;


@SuppressWarnings({"rawtypes", "unchecked"})
public class Queues {
	ArrayList queue;
	public Queues() {
		queue = new ArrayList<>();
	}
	
	public void add(Object i) {
		queue.add(i);
	}
	
	public Object remove() {
		return queue.remove(0);
	}
	
	public Object peek() {
		return queue.get(0);
	}
	
	public boolean element(Object i) { 
		return queue.contains(i);
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public String toString() {
		return queue.toString();
	}
}