/*
 * 
 * Total List of Methods
 * 		reverse x 1
 * 		add x 4
 * 		remove x 2
 * 		empty x 1
 * 		getType x 1
 */

package using_api;
import utilities.symt.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		
		
		ExtraUtils exu = new ExtraUtils(); // 
		/*
		 * Usage of ExtraUtils' reverse feature
		 * 
		 * Works for: Strings, Integers, Arrays, ArrayLists
		 *
		 */
		System.out.println("Reversing\n---");
		System.out.println("abc to " + exu.reverse("abc"));
		System.out.println("123 to " + exu.reverse(123));
		/*
		 * For arrays, you still need to change it to a string yourself
		 */
		double[] swapper = {1, 3, 5};
		System.out.println(Arrays.toString(exu.reverse(swapper)));
		ArrayList<String> arrlist = new ArrayList<String>();
		arrlist.add("A");
		arrlist.add("B");
		arrlist.add("C");
		System.out.println(exu.reverse(arrlist));
		System.out.println();
		
		/*
		 * Usage of ExtraUtils' Resizing features for Arrays
		 * 
		 * add(array, value) -- adds to end of array (DONE)
		 * add(array, value, count) -- adds count # of value to end of array (DONE)
		 * addIndex(array, value, index) -- adds value to array at index and pushes all else (DONE)
		 * addIndex(array, value, index, count) -- adds count # of value at index and pushes all else (DONE)
		 * remove(array, index) -- remove item index from array (DONE)
		 * remove(array, delimiter, instances) -- remove instances number of delimiter from array (DONE)
		 * empty(array) -- removes all items from array (DONE)
		 * 
		 * Note: All variations return void, methods just manipulate array.
		 */
		System.out.println("Resizing\n---");
		int[] boringArray = {1, 2, 3, 4};
		System.out.println("Start: " + Arrays.toString(boringArray) + " (" + exu.getType(boringArray) + ")");
		boringArray = exu.add(boringArray, 5);
		System.out.println("1 - Add an item to end: " + Arrays.toString(boringArray));
		boringArray = exu.addIndex(boringArray, 0, 0);
		System.out.println("2 - Add an item to the beginning: " + Arrays.toString(boringArray));
		boringArray = exu.addIndex(boringArray, -1, 3, 5);
		System.out.println("3 - Add multiple items to end: " + Arrays.toString(boringArray));
		boringArray = exu.remove(boringArray, 2);
		System.out.println("4 - Remove one item at index (2): " + Arrays.toString(boringArray));
		boringArray = exu.remove(boringArray, -1, 2);
		System.out.println("5 - Remove 2 instances of -1: " + Arrays.toString(boringArray));
		boringArray = exu.empty(boringArray);
		System.out.println("6 - Don't like it? Empty it - " + Arrays.toString(boringArray));
	}
}
