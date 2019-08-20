package com.example.algorithm;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class MaxHeapTest {
	private static final int TEST_SIZE = 10;

	@Test
	public void insertAndGetTest() {
		MaxHeap<Integer> maxHeap = new MaxHeap<>();

		// insert
		new Random().ints(1000, 1, 2000).boxed().forEach(maxHeap::insert);

		// get and delete
		int previous = Integer.MAX_VALUE;
		while (!maxHeap.isEmpty()) {
			int current = maxHeap.get();
			assertTrue(previous >= current);
			System.out.print(String.format("%d ", current));

			previous = current;
			maxHeap.delete();
		}
	}
}
