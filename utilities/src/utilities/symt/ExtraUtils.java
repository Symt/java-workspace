/**
 * Project EXU
 * 
 * @author symt
 * @version 1.0, 08/26/18
 *   
 * This project is based around making manipulation of arrays, integers, and strings easier. 
 * 
 * Info and Usage: https://www.github.com/symt/extrautils
 */

package utilities.symt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;
import java.util.Arrays;

class IntegersUtilities {
	/**
	 * Method to reverse a given number
	 * It drops all decimal values.
	 * 
	 * @param n Number to be reversed
	 * @return Reversed form of n
	 */
	public int reverse(Number n) {
		int reversed = 0;
		int digit;
		while (n != (Number) 0) {
			digit = n.intValue() % 10;
			reversed = reversed * 10 + digit;
			n = n.intValue() / 10;
		}
		return reversed;
	}
}

class StringUtilities {
	/**
	 * Method to reverse a given string
	 * 
	 * @param str String to be reversed
	 * @return Reversed version of the string
	 */
	public String reverse(String str) {
		return new StringBuilder(str).reverse().toString();
	}
}

@SuppressWarnings({"unchecked", "rawtypes"})
class ArrayUtilities {
	/**
	 * Method to combine two arrays (Applies only to String[], Integer[], Double[], and Number[])
	 * 
	 * @param arr1 First part of array to be combined
	 * @param arr2 Second part of array to be combined
	 * @param cls Class of the array that is being combined
	 * @return combined array made of arr1 and arr2
	 * @throws IllegalArgumentException
	 */
	public <T> T[] concat(T[] arr1, T[] arr2, Class<?> cls) {
		if (cls.getSimpleName().equals("String[]")) {
			return (T[]) Stream.of(arr1, arr2).flatMap(Stream::of).toArray(String[]::new);
		} else if (cls.getSimpleName().equals("Integer[]")) {
			return (T[]) Stream.of(arr1, arr2).flatMap(Stream::of).toArray(Integer[]::new);
		} else if (cls.getSimpleName().equals("Double[]")) {
			return (T[]) Stream.of(arr1, arr2).flatMap(Stream::of).toArray(Double[]::new);
		} else if (cls.getSimpleName().equals("Number[]")) {
			return (T[]) Stream.of(arr1, arr2).flatMap(Stream::of).toArray(Number[]::new);
		} else {
			throw new IllegalArgumentException("Invalid array type.");
		}
	}

	/**
	 * Reverse method for regular arrays
	 * 
	 * @param arr Array to be reversed
	 * @return Reversed version of arr
	 */
	public <T> T[] reverse(T[] arr) {
		for (int i = 0; i < arr.length / 2; i++) {
			T temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp;
		}
		return arr;
	}
	
	
	/**
	 * ArrayList version of reverse method
	 * 
	 * @param arr Array to be reversed
	 * @return Reversed version of arr
	 */
	public ArrayList reverse(ArrayList arr) {
		Collections.reverse(arr);
		return arr;
	} 
	
	
	/**
	 * Adds a value to the end of an array
	 * 
	 * @param arr Array to be manipulated
	 * @param value Item to be added
	 * @return Changed array with item added to end
	 */
	public <T> T[] add(T[] arr, T value) {
		arr = Arrays.copyOf(arr, arr.length + 1);
		arr[arr.length - 1] = value;
		return arr;
	}
	
	
	/**
	 * Adds multiple of the same value to the end of an array
	 * 
	 * @param arr Array to be manipulated
	 * @param value Item to be added
	 * @param count Number of times the item should be added
	 * @return Changed array with item added to end
	 */
	public <T> T[] add(T[] arr, T value, int count) {
		arr = Arrays.copyOf(arr, arr.length + count);
		while (count > 0) {
			arr[arr.length - count] = value;
			count--;
		}
		return arr;
	}
	
	
	/**
	 * Adds an item to a specific location in an array
	 * 
	 * @param arr Array to be manipulated
	 * @param value Item to be added
	 * @param index Location where the item should be added
	 * @return Changed array with item added (No items are lost)
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public <T> T[] addIndex(T[] arr, T value, int index) {
		Class<?> type = arr.getClass();
		
		if (arr.length < index) {
			throw new ArrayIndexOutOfBoundsException("\nInvalid Index (Max: " + (arr.length-1) + ", Recieved: " + index + ")");
		}
		T[] part = Arrays.copyOfRange(arr, 0, index+1);
		part[part.length-1] = value;
		T[] end = Arrays.copyOfRange(arr, index, arr.length);
		T[] result = concat(part, end, type);
		return result;
	}
	
	/**
	 * Adds multiple of the same item to a specific location in an array
	 * 
	 * @param arr Array to be manipulated
	 * @param value Item to be added
	 * @param index Location where the item should be added
	 * @param count Number of times the item should be added
	 * @return Changed array with items added (No items are lost)
	 */
	public <T> T[] addIndex(T[] arr, T value, int index, int count) {
		Class<?> type = arr.getClass();
		
		if (arr.length < index) {
			throw new ArrayIndexOutOfBoundsException("\nInvalid Index (Max: " + (arr.length-1) + ", Recieved: " + index + ")");
		}
		T[] part = Arrays.copyOfRange(arr, 0, index+count);
		while (count > 0) {
			part[part.length - count] = value;
			count--;
		}
		part[part.length-1] = value;
		T[] end = Arrays.copyOfRange(arr, index, arr.length);
		T[] result = concat(part, end, type);
		return result;
	}
	
	/**
	 * Removes a single item from a specific location in an array
	 * 
	 * @param arr Array to be manipulated
	 * @param index Location where the item should be removed
	 * @return Changed array with items removed
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public <T> T[] remove(T[] arr, int index) {
		Class<?> type = arr.getClass();
		T[] part,
			end,
			result;
		if (index >= 0 && index <= arr.length-1) {
			part = Arrays.copyOfRange(arr, 0, index);
			end = Arrays.copyOfRange(arr, index+1, arr.length);
			result = concat(part, end, type);
		} else if (index == arr.length-1) {
			result = Arrays.copyOfRange(arr, 0, arr.length-1);
		} else {
			throw new ArrayIndexOutOfBoundsException("Index given is out of bounds (Rules: 0 <= index <= arr.length-1)");
		}
		return result;
	}
	
	/**
	 * Removes all instances of a given delimiter from an array
	 * 
	 * @param arr Array to be manipulated
	 * @param delimiter Item to be found and removed
	 * @param instances Number of times to remove item from array
	 * @return Changed array with items removed
	 */
	public <T> T[] remove(T[] arr, T delimiter, int instances) {
		int temp_instances = instances;
		T[] result = Arrays.copyOf(arr, 1);
		for (int i = 0; i < arr.length; i++) {
			if (instances == 0) {
				result = Arrays.copyOf(result, result.length + 1);
				result[result.length - 1] = arr[i];
				continue;
			}
			if (!arr[i].equals(delimiter)) {
				if (temp_instances == instances) {
					result[0] = arr[i];
					temp_instances = -1;
				} else {
					result = Arrays.copyOf(result, result.length + 1);
					result[result.length - 1] = arr[i];
				}
			} else {
				instances--;
			}
		}
		
		return result;
	}
}

@SuppressWarnings("rawtypes")
/**
 * 
 * Main class for Project EXU, used for converting array types to be compatible with the other classes' methods.
 * @author symt
 *
 */
public class ExtraUtils {
	private IntegersUtilities ints = new IntegersUtilities();
	private StringUtilities strs = new StringUtilities();
	private ArrayUtilities arrs = new ArrayUtilities();

	/**
	 * Method that reverses any whole number
	 * <br/> <br/>
	 * If the number isn't a whole number, the decimal will be dropped
	 * 
	 * @param n Number to be reversed
	 * @return Reversed form of number
	 */
	public Number reverse(Number n) {
		return ints.reverse(n);
	}

	/**
	 * Method that reverses any string
	 * 
	 * @param str String to be reversed
	 * @return Reversed form of string
	 */
	public String reverse(String str) {
		return strs.reverse(str);
	}

	/*
	 * Reverses for arrays
	 */

	public int[] reverse(int[] arr) {
		Integer[] arrUpdated = Arrays.stream( arr ).boxed().toArray( Integer[]::new );
		int count = 0;
		for (Integer i : arrs.reverse(arrUpdated)) {
			arr[count] = i;
			count++;
		}
		return arr;
	}

	public double[] reverse(double[] arr) {
		Double[] arrUpdated = Arrays.stream( arr ).boxed().toArray( Double[]::new );
		int count = 0;
		for (Double i : arrs.reverse(arrUpdated)) {
			arr[count] = i;
			count++;
		}
		return arr;
	}

	public String[] reverse(String[] arr) {
		return arrs.reverse(arr);
	}

	public ArrayList reverse(ArrayList arr) {
		return arrs.reverse(arr);
	}

	/*
	 * Addition/Removal of items to arrays
	 */
	
	// Set of (arr, value)

	public int[] add(int[] arr, int value) {
		int count = 0;
		int[] newArray = new int[arr.length+1];
		for (Integer i : arrs.add(Arrays.stream( arr ).boxed().toArray( Integer[]::new ), value)) {
			newArray[count] = i;
			count++;
		}
		return newArray;
	}

	public double[] add(double[] arr, double value) {
		int count = 0;
		double[] newArray = new double[arr.length+1];
		for (Double i : arrs.add(Arrays.stream( arr ).boxed().toArray( Double[]::new ), value)) {
			newArray[count] = i;
			count++;
		}
		return newArray;
	}

	public String[] add(String[] arr, String value) {
		return arrs.add(arr, value);
	}
	// Set of (arr, value, count)
	
	public int[] add(int[] arr, int value, int count) {
		int spot = 0;
		int[] newArray = new int[arr.length+count]; 
		for (Integer i : arrs.add(Arrays.stream( arr ).boxed().toArray( Integer[]::new ), value, count)) {
			newArray[spot] = i;
			spot++;
		}
		return newArray;
	}

	public double[] add(double[] arr, double value, int count) {
		int spot = 0;
		double[] newArray = new double[arr.length+count]; 
		for (Double i : arrs.add(Arrays.stream( arr ).boxed().toArray( Double[]::new ), value, count)) {
			newArray[spot] = i;
			spot++;
		}
		return newArray;
	}

	public String[] add(String[] arr, String value, int count) {
		return arrs.add(arr, value, count);
	}
	
	// Set of (arr, value, index)
	public String[] addIndex(String[] arr, String value, int index) {
		return arrs.addIndex(arr, value, index);
	}
	public int[] addIndex(int[] arr, int value, int index) {
		int count = 0;
		int[] newArray = new int[arr.length+1];
		for (Integer i : arrs.addIndex(Arrays.stream( arr ).boxed().toArray( Integer[]::new ), value, index)) {
			newArray[count] = i;
			count++;
		}
		return newArray;
	}
	public double[] addIndex(double[] arr, double value, int index) {
		int count = 0;
		double[] newArray = new double[arr.length+1];
		for (Double i : arrs.addIndex(Arrays.stream( arr ).boxed().toArray( Double[]::new ), value, index)) {
			newArray[count] = i;
			count++;
		}
		return newArray;
	}
	
	// Set of (arr, value, index, count)
	public int[] addIndex(int[] arr, int value, int index, int count) {
		int spot = 0;
		int[] newArray = new int[arr.length+count];
		for (Integer i : arrs.addIndex(Arrays.stream( arr ).boxed().toArray( Integer[]::new ), value, index, count)) {
			newArray[spot] = i;
			spot++;
		}
		return newArray;
	}
	public double[] addIndex(double[] arr, double value, int index, int count) {
		int spot = 0;
		double[] newArray = new double[arr.length+count];
		for (Double i : arrs.addIndex(Arrays.stream( arr ).boxed().toArray( Double[]::new ), value, index, count)) {
			newArray[spot] = i;
			spot++;
		}
		return newArray;
	}
	public String[] addIndex(String[] arr, String value, int index, int count) {
		return arrs.addIndex(arr, value, index, count);
	}
	// Removal from index
	public int[] remove(int[] arr, int index) {
		int spot = 0;
		int[] newArray = new int[arr.length-1];
		for (Integer i : arrs.remove(Arrays.stream( arr ).boxed().toArray( Integer[]::new ), index)) {
			newArray[spot] = i;
			spot++;
		}
		return newArray;
	}
	public double[] remove(double[] arr, int index) {
		int spot = 0;
		double[] newArray = new double[arr.length-1];
		for (Double i : arrs.remove(Arrays.stream( arr ).boxed().toArray( Double[]::new ), index)) {
			newArray[spot] = i;
			spot++;
		}
		return newArray;
	}
	public String[] remove(String[] arr, int index) {
		return arrs.remove(arr, index);
	}
	// Delimiter removal
	public int[] remove(int[] arr, int delimiter, int instances) {
		int spot = 0;
		Integer[] result = arrs.remove(Arrays.stream( arr ).boxed().toArray( Integer[]::new ), delimiter, instances);
		int[] newArray = new int[result.length];
		for (Integer i : result) {
			newArray[spot] = i;
			spot++;
		}
		return newArray;
	}
	public double[] remove(double[] arr, double delimiter, int instances) {
		int spot = 0;
		Double[] result = arrs.remove(Arrays.stream( arr ).boxed().toArray( Double[]::new ), delimiter, instances);
		double[] newArray = new double[result.length];
		for (Double i : result) {
			newArray[spot] = i;
			spot++;
		}
		return newArray;
	}
	public String[] remove(String[] arr, String delimiter, int instances) {
		return arrs.remove(arr, delimiter, instances);
	}
	// Empty
	public int[] empty(int[] arr) { return new int[arr.length]; }
	public double[] empty(double[] arr) { return new double[arr.length]; }
	public String[] empty(String[] arr) { return new String[arr.length]; }
	
	public String getType(Object obj) {
		return obj.getClass().getSimpleName();
	}
}
