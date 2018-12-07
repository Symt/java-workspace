package matrix;

public class Main {
	public static void main(String args[])
	{
		System.out.println("\nLAB24A 100-POINT VERSION\n");
		
		Matrix m1 = new Matrix();
		m1.displayMatrix("Matrix m1 Default Display");
		System.out.println();
		
		Matrix m2 = new Matrix(3,5);
		int count = 100;
		for (int r = 0; r < m2.getRows(); r++)
		{
			for (int c = 0; c < m2.getCols(); c++)
			{
				m2.setValue(r,c,count);
				count++;
			}
		}		
		m2.displayMatrix("Matrix m2 3 X 5 Consecutive Integers Display");
		System.out.println();
		
		m2.resize(4,4);
		m2.displayMatrix("Matrix m2 After 4 X 4 Resizing Display");
		System.out.println();
		
		Matrix m3 = new Matrix(3,3,100);				
		m3.displayMatrix("Matrix m3 3 X 3 Initialized to 100 Display");
		System.out.println();
	}
	
	
}