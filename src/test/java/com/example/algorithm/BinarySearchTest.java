package com.example.algorithm;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTest {
	private Integer[] arr;

	@Before
	public void setup() {
		this.arr = new Random().ints(1000, 1, 1000).boxed().distinct().toArray(Integer[]::new);
	}

	@Test
	public void binarySearchTest() {
		// select a random element
		int target = this.arr[this.arr.length / 2];

		// sort
		Sort.quickSort(this.arr);
		printArr(this.arr);

		// search
		int searchIndex = BinarySearch.search(this.arr, target);
		assertEquals(target, (int)this.arr[searchIndex]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void binarySearchFailTest() {
		// select a random element
		int target_index = this.arr.length / 2;
		int target = this.arr[target_index];

		// remove a element
		Integer[] newArr = new Integer[this.arr.length - 1];
		System.arraycopy(this.arr, 0, newArr, 0, target_index);
		System.arraycopy(this.arr, target_index + 1, newArr, target_index, this.arr.length - target_index - 1);

		// sort
		Sort.quickSort(newArr);
		printArr(newArr);

		// search
		System.out.println(String.format("target: %d", target));
		BinarySearch.search(newArr, target);
	}

	private void printArr(Integer[] arr) {
		System.out.print("Array: ");
		for (int num : arr) {
			System.out.print(String.format("%d ", num));
		}
		System.out.println();
	}
}
