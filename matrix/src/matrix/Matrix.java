package matrix;
import java.util.ArrayList;
import java.util.Collections;

class Matrix {

								
	private ArrayList<ArrayList<Integer>> list;
	private int listSize;
	private int numRows;
	private int numCols;
	
	public Matrix() {
		list = new ArrayList<>();
	}
	public Matrix(int r, int c) {
		list = new ArrayList<ArrayList<Integer>>();
		while (r > 0) {
			list.add(new ArrayList<Integer>(Collections.nCopies(c, 0)));
			r--;
		}
	}
	public Matrix(int r, int c, int value) {
		list = new ArrayList<ArrayList<Integer>>();
		while (r > 0) {
			list.add(new ArrayList<Integer>(Collections.nCopies(c, value)));
			r--;
		}
		
	}
	public int getRows() {
		numRows = list.size();
		return numRows;
	}
	public int getCols() {
		numCols = list.get(0).size();
		return numCols;
	}
	public int getSize() {
		listSize = getRows() * getCols();
		return listSize;
	}
	public int getValue(int r, int c) {
		return list.get(r).get(c);
	}
	public void setValue(int r, int c, int value) {
		list.get(r).set(c, value);
	}
	public void displayMatrix(String str) {
		System.out.println(str);
		int rowCount = getRows();
		int count = 0;
		while (rowCount > 0) {
			System.out.println(displayRow(count));
			rowCount--;
			count++;
		}
	}
	public ArrayList<Integer> displayRow(int count) {
		ArrayList<Integer> row = new ArrayList<Integer>(list.get(count));
		return row;
	}
	public void resize(int rows, int cols) {
		ArrayList<ArrayList<Integer>> resized = new ArrayList<ArrayList<Integer>>();
		int value;
		int colsCount = 0;
		int rowsCount = 0;
		int tempCols;
		while (rows > 0) {
			resized.add(new ArrayList<Integer>(Collections.nCopies(cols, 0)));
			tempCols = cols;
			colsCount = 0;
			while (tempCols > 0) {
				try {
					value = list.get(rowsCount).get(colsCount);
				} catch (Exception e) {
					value = 0;
				}
				resized.get(rowsCount).set(colsCount, value);
				tempCols--;
				colsCount++;
			}
			rows--;
			rowsCount++;
		}
		list = resized;
	}
}