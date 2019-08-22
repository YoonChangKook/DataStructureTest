package com.example.algorithm;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

import org.junit.Test;

public class SynchronizedQueueTest {
	private static final int TEST_SIZE = 50;
	private static final int SYNCHRONIZED_TEST_SIZE = 1000000;

	@Test
	public void enqueueAndDequeueTest() {
		SynchronizedQueue<Integer> queue = new SynchronizedQueue<>();

		// push
		IntStream.range(0, TEST_SIZE).forEach(queue::enqueue);

		// pop
		IntStream.range(0, TEST_SIZE)
			.boxed()
			.forEach(num -> assertEquals(num, queue.dequeue()));

		// check if the stack is empty
		assertTrue(queue.isEmpty());
	}

	@Test
	public void synchronizedTest() {
		SynchronizedQueue<Integer> queue = new SynchronizedQueue<>();

		Thread producer = new Thread(() -> {
			// push
			IntStream.range(0, SYNCHRONIZED_TEST_SIZE).forEach(queue::enqueue);
		});

		Thread consumer = new Thread(() -> {
			//pop
			IntStream.range(0, SYNCHRONIZED_TEST_SIZE)
				.boxed()
				.forEach(num -> assertEquals(num, queue.dequeue()));
		});

		producer.start();
		consumer.start();

		try {
			producer.join();
			consumer.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		// check if the stack is empty
		assertTrue(queue.isEmpty());
	}
}
