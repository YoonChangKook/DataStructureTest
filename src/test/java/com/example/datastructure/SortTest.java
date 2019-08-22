package com.example.datastructure;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class SortTest {
	private Integer[] arr;

	@Before
	public void setup() {
		this.arr = new Random().ints(1000, 1, 1000).boxed().toArray(Integer[]::new);
	}

	@Test
	public void quickSortTest() {
		Sort.quickSort(arr);

		printArr();
		for (int i = 1; i < arr.length; i++) {
			assertTrue(arr[i - 1] <= arr[i]);
		}
	}

	@Test
	public void mergeSortTest() {
		Sort.mergeSort(arr);

		printArr();
		for (int i = 1; i < arr.length; i++) {
			assertTrue(arr[i - 1] <= arr[i]);
		}
	}

	private void printArr() {
		System.out.print("Array: ");
		for (int num : this.arr) {
			System.out.print(String.format("%d ", num));
		}
	}
}
