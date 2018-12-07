package mazerunner;

import java.util.ArrayList;

public abstract class CBase implements Collections {
	ArrayList<Point> data = new ArrayList<Point>();
	public boolean isEmpty() {
		return data.isEmpty();
	}
	public abstract Point get();
	public int size() {
		return data.size();
	}
	public void add(Point item) {
		data.add(item);
	}
}