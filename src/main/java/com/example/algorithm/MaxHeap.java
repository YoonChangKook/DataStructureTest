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

	private Object[] arr;
	private int size;

	public MaxHeap() {
		this.arr = new Object[MAX_HEAP_SIZE + 1];
		this.size = 0;
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

		return elementData(ROOT_INDEX);
	}

	/**
	 * 요소를 최대 힙에 삽입한다.
	 * @param element 삽입할 요소
	 * @throws IllegalStateException 힙이 꽉 찼을 때
	 */
	public void insert(T element) throws IllegalStateException {
		if (isFull()) {
			throw new IllegalStateException("The max heap is full.");
		}

		this.arr[++size] = element;

		for (int index = this.size; index > ROOT_INDEX; index /= 2) {
			if (elementData(index / 2).compareTo(elementData(index)) < 0) {
				swap(index, index / 2);
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

		this.arr[ROOT_INDEX] = this.arr[this.size];
		this.size--;

		for (int parent = ROOT_INDEX, child = ROOT_INDEX * 2; child <= this.size;) {
			// 두 자식 노드 중 큰 것 선택
			if (elementData(child).compareTo(elementData(child + 1)) < 0) {
				child++;
			}

			// 더 이상 부모 객체가 작지 않으면 종료
			if (elementData(parent).compareTo(elementData(child)) >= 0) {
				break;
			}

			// 부모 노드가 더 작으면 교환
			swap(parent, child);

			// 다음으로 이동
			parent = child;
			child *= 2;
		}
	}

	/**
	 * @return 힙이 비었는지 여부
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * @return 힙이 꽉 찼는지 여부
	 */
	public boolean isFull() { return this.size == MAX_HEAP_SIZE; }

	@SuppressWarnings("unchecked")
	private T elementData(int index) {
		return (T) this.arr[index];
	}

	private void swap(int index1, int index2) {
		Object temp = this.arr[index1];
		this.arr[index1] = this.arr[index2];
		this.arr[index2] = temp;
	}
}
