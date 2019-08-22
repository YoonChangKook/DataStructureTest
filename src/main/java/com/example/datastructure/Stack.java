package com.example.datastructure;

/**
 * 스택
 *
 * @param <T> 스택에 저장할 클래스
 * @author 국윤창
 */
public class Stack<T> {
	private static final int MAX_STACK_SIZE = 100;
	private static final int EMPTY_TOP_INDEX = -1;

	/**
	 * 스택의 요소들이 저장되는 공간
	 */
	private final Object[] arr;

	/**
	 * 스택의 맨 위 요소의 index
	 * 스택이 비었으면 -1 이다.
	 */
	private int topIndex;

	public Stack() {
		this.arr = new Object[MAX_STACK_SIZE];
		this.topIndex = EMPTY_TOP_INDEX;
	}

	/**
	 * 스택 최상단에 요소를 삽입한다.
	 *
	 * @param elem 삽입할 요소
	 * @throws IllegalStateException 스택이 꽉 찼을 때
	 */
	public void push(T elem) throws IllegalStateException {
		if (this.topIndex == MAX_STACK_SIZE) {
			throw new IllegalStateException("The stack is full");
		}

		this.arr[++topIndex] = elem;
	}

	/**
	 * 스택 최상단의 데이터를 반환한다.
	 *
	 * @return 스택 최상단의 데이터
	 * @throws IllegalStateException 스택이 비어있을 때
	 */
	public T peek() throws IllegalStateException {
		if (isEmpty()) {
			throw new IllegalStateException("The stack is empty");
		}

		return elementData(this.topIndex);
	}

	/**
	 * 스택 최상단의 데이터를 제거하고 반환한다.
	 *
	 * @return 스택 최상단의 데이터
	 * @throws IllegalStateException 스택이 비어있을 때
	 */
	public T pop() throws IllegalStateException {
		T element = peek();
		this.arr[topIndex--] = null;

		return element;
	}

	/**
	 * 스택이 비어있는지 여부를 반환한다.
	 *
	 * @return 스택이 비어있으면 true
	 */
	public boolean isEmpty() {
		return this.topIndex == EMPTY_TOP_INDEX;
	}

	/**
	 * @return 스택에 저장된 요소 개수
	 */
	public int size() {
		return this.topIndex + 1;
	}

	/**
	 * @return 스택이 저장할 수 있는 최대 요소 개수
	 */
	public int capacity() {
		return MAX_STACK_SIZE;
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
