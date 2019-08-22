package com.example.datastructure;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

import org.junit.Test;

public class QueueTest {
	private static final int TEST_SIZE = 70;

	@Test
	public void isEmptyTest() {
		Queue<Integer> queue = new Queue<>();

		assertTrue(queue.isEmpty());

		queue.enqueue(100);
		assertFalse(queue.isEmpty());

		queue.dequeue();
		assertTrue(queue.isEmpty());
	}

	@Test
	public void isFullTest() {
		Queue<Integer> queue = new Queue<>();

		assertFalse(queue.isFull());

		// enqueue
		try {
			for (int i = 0; ; i++) queue.enqueue(i);
		} catch (IllegalStateException ex) {
			assertTrue(queue.isFull());
		}

		// dequeue
		queue.dequeue();
		assertFalse(queue.isFull());
	}

	@Test
	public void peekTest() {
		Queue<Integer> queue = new Queue<>();

		// enqueue
		IntStream.range(0, TEST_SIZE).forEach(queue::enqueue);

		// peek
		assertEquals(0, (int)queue.peek());
	}

	@Test
	public void enqueueAndDequeueTest() {
		Queue<Integer> queue = new Queue<>();

		// push
		IntStream.range(0, TEST_SIZE).forEach(queue::enqueue);

		// pop
		IntStream.range(0, TEST_SIZE)
			.boxed()
			.forEach(num -> assertEquals(num, queue.dequeue()));

		// check if the stack is empty
		assertTrue(queue.isEmpty());

		// check whether circular queue operate correctly
		// push
		IntStream.range(0, TEST_SIZE).forEach(queue::enqueue);

		// pop
		IntStream.range(0, TEST_SIZE)
			.boxed()
			.forEach(num -> assertEquals(num, queue.dequeue()));

		// check if the stack is empty
		assertTrue(queue.isEmpty());
	}
}
