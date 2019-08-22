package com.example.datastructure;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.stream.IntStream;

import org.junit.Test;

public class StackTest {
	private static final int TEST_SIZE = 10;

	@Test
	public void isEmptyTest() {
		Stack<Integer> stack = new Stack<>();

		assertTrue(stack.isEmpty());

		stack.push(100);
		assertFalse(stack.isEmpty());

		stack.pop();
		assertTrue(stack.isEmpty());
	}

	@Test
	public void sizeTest() {
		Stack<Integer> stack = new Stack<>();

		// push
		IntStream.range(0, TEST_SIZE).forEach(stack::push);

		// size
		assertEquals(TEST_SIZE, stack.size());

		// pop
		IntStream.range(0, TEST_SIZE).forEach(num -> stack.pop());

		// size
		assertEquals(0, stack.size());
	}

	@Test
	public void peekTest() {
		Stack<Integer> stack = new Stack<>();

		// push
		IntStream.range(0, TEST_SIZE).forEach(stack::push);

		assertEquals(TEST_SIZE - 1, (int)stack.peek());
	}

	@Test
	public void pushAndPopTest() {
		Stack<Integer> stack = new Stack<>();

		// push
		IntStream.range(0, TEST_SIZE).forEach(stack::push);

		// pop
		IntStream.range(0, TEST_SIZE)
			.boxed()
			.sorted(Collections.reverseOrder())
			.forEach(num -> assertEquals(num, stack.pop()));

		// check if the stack is empty
		assertTrue(stack.isEmpty());
	}
}
