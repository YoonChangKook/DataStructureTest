package com.example.datastructure;

/**
 * 원형 큐
 *
 * @param <T> 큐에 저장할 클래스
 * @author 국윤창
 */
public class Queue<T> {
	private static final int MAX_QUEUE_SIZE = 100;

	/**
	 * 큐의 요소들이 저장되는 공간
	 */
	private final Object[] arr;

	/**
	 * 원형 큐의 시작 인덱스 - 1
	 */
	private int front;

	/**
	 * 원형 큐의 끝 인덱스
	 */
	private int rear;

	public Queue() {
		this.arr = new Object[MAX_QUEUE_SIZE];
		this.front = 0;
		this.rear = 0;
	}

	/**
	 * 큐에 새로운 요소를 삽입한다.
	 *
	 * @param elem 큐에 삽입할 요소
	 * @throws IllegalStateException 큐가 꽉 찼을 때
	 */
	public void enqueue(T elem) throws IllegalStateException {
		if (isFull()) {
			throw new IllegalStateException("The queue is full");
		}

		this.rear = (this.rear + 1) % MAX_QUEUE_SIZE;
		this.arr[this.rear] = elem;
	}

	/**
	 * 큐의 맨 앞 요소를 제거하고 반환한다.
	 *
	 * @throws IllegalStateException 큐가 비어있을 때
	 */
	public T dequeue() throws IllegalStateException {
		T elem = peek();
		this.front = (this.front + 1) % MAX_QUEUE_SIZE;

		return elem;
	}

	/**
	 * 큐의 맨 앞 요소를 반환한다.
	 *
	 * @throws IllegalStateException 큐가 비어있을 때
	 */
	public T peek() throws IllegalStateException {
		if (isEmpty()) {
			throw new IllegalStateException("The queue is empty");
		}

		return elementData((this.front + 1) % MAX_QUEUE_SIZE);
	}

	/**
	 * 큐가 비어있는지 검사
	 *
	 * @return 큐가 비어있으면 true
	 */
	public boolean isEmpty() {
		return this.rear == this.front;
	}

	/**
	 * 큐가 꽉 찼는지 검사
	 *
	 * @return 큐가 꽉 찼으면 true
	 */
	public boolean isFull() {
		return (this.rear + 1) % MAX_QUEUE_SIZE == this.front;
	}

	/**
	 * Object로 저장된 요소를 적절한 타입으로 변환하여 반환한다.
	 *
	 * @param index 요소가 저장된 index
	 * @return 타입이 변환된 요소
	 */
	@SuppressWarnings("unchecked")
	private T elementData(int index) {
		return (T) this.arr[index];
	}
}
