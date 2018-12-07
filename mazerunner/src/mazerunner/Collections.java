package mazerunner;

import java.util.ArrayList;

public interface Collections {
	ArrayList<Point> data = new ArrayList<Point>();
	public abstract boolean isEmpty();
	public abstract int size();
	public abstract Point get();
}
