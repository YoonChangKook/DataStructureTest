package com.example.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 최대 힙
 *
 * @param <T> Comparable 상속 클래스
 * @author 국윤창
 */
public class MaxHeap<T extends Comparable<? super T>> {
	private static final int ROOT_INDEX = 1;
	private static final int MAX_HEAP_SIZE = 2000;

	private List<T> arr;

	public MaxHeap() {
		this.arr = new ArrayList<>();
		this.arr.add(null);
	}

	/**
	 * 최대 힙의 루트 요소를 반환한다.
	 * @return 최대 힙의 루트 요소
	 * @throws IllegalStateException 힙이 비었을 때
	 */
	public T get() throws IllegalStateException {
		if (isEmpty()) {
			throw new IllegalStateException("The max heap is empty.");
		}

		return this.arr.get(ROOT_INDEX);
	}

	/**
	 * 요소를 최대 힙에 삽입한다.
	 * @param element 삽입할 요소
	 */
	public void insert(T element) {
		this.arr.add(element);
		for (int index = this.arr.size() - 1; index > ROOT_INDEX; index /= 2) {
			if (this.arr.get(index / 2).compareTo(this.arr.get(index)) < 0) {
				Collections.swap(this.arr, index, index / 2);
			} else {
				break;
			}
		}
	}

	/**
	 * 루트 요소를 삭제한다.
	 * @throws IllegalStateException 힙이 비었을 때
	 */
	public void delete() throws IllegalStateException {
		if (isEmpty()) {
			throw new IllegalStateException("The max heap is empty.");
		}

		this.arr.set(ROOT_INDEX, this.arr.get(this.arr.size() - 1));
		this.arr.remove(this.arr.size() - 1);

		for (int index = ROOT_INDEX; index * 2 <= this.arr.size() - 1;) {
			if (this.arr.get(index).compareTo(this.arr.get(index * 2)) > 0 && index * 2 + 1 <= this.arr.size() - 1 && this.arr.get(index).compareTo(this.arr.get(index * 2 + 1)) > 0) {
				break;
			} else if (this.arr.get(index * 2).compareTo(this.arr.get(index * 2 + 1)) > 0) {
				Collections.swap(this.arr, index, index * 2);
				index = index * 2;
			} else {
				Collections.swap(this.arr, index, index * 2 + 1);
				index = index * 2 + 1;
			}
		}
	}

	/**
	 * @return 힙이 비었는 지 여부
	 */
	public boolean isEmpty() {
		return this.arr.size() == ROOT_INDEX;
	}
}
