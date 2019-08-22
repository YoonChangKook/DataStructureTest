package com.example.datastructure;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.stream.IntStream;

import org.junit.Test;

public class LinkedListTest {
	private static final int TEST_SIZE = 10;
	private static final int SEARCH_DATA = 5;
	private static final int INSERT_DATA = 100;

	@Test
	public void pushAndDeleteTest() {
		LinkedList<Integer> linkedList = new LinkedList<>();

		// push
		IntStream.range(0, TEST_SIZE).forEach(linkedList::push);
		assertFalse(linkedList.isEmpty());

		linkedList.print();

		// delete
		IntStream.range(0, TEST_SIZE)
			.boxed()
			.sorted(Collections.reverseOrder())
			.forEach(linkedList::delete);
		assertTrue(linkedList.isEmpty());
	}

	@Test
	public void searchTest() {
		LinkedList<Integer> linkedList = new LinkedList<>();

		IntStream.range(0, TEST_SIZE).forEach(linkedList::push);

		// search
		LinkedList.Node node = linkedList.search(SEARCH_DATA);
		assertEquals(SEARCH_DATA, node.getData());
	}

	@Test
	public void insertTest() {
		LinkedList<Integer> linkedList = new LinkedList<>();

		IntStream.range(0, TEST_SIZE).forEach(linkedList::push);

		// search
		LinkedList.Node node = linkedList.search(SEARCH_DATA);
		assertEquals(SEARCH_DATA, node.getData());
		linkedList.print();

		// insert
		linkedList.insert(node, INSERT_DATA);
		linkedList.print();

		// search
		node = linkedList.search(INSERT_DATA);
		assertEquals(SEARCH_DATA + 1, node.getLink().getData());
	}
}
