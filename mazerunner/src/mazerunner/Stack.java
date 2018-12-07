package mazerunner;

public class Stack extends CBase {
	public Point get() {
		return data.get(data.size()-1);
	}
	public String toString() {
		return String.format("Stack of %d objects!!!", data.size());
	}
	public Point remove() {
		Point item = data.get(data.size()-1);
		data.remove(data.size()-1);
		return item;
	}
	

}
