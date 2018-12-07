package mazerunner;

public class Queue extends CBase {
	
	public Point get() {
		return data.get(0);
	}
	
	public String toString() {
		return String.format("Queue of %d objects!!!", data.size());
	}
	
	public Point remove() {
		Point item = data.get(0);
		data.remove(0);
		return item;
	}

}
