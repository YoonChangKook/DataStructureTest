package com.example.algorithm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 동기화 된 원형 큐
 *
 * @param <T> 큐에 저장할 클래스
 * @author 국윤창
 */
public class SynchronizedQueue<T> {
	private static final int MAX_SIZE = 100;

	/**
	 * enqueue, dequeue의 원자성을 보장해주는 필드
	 */
	private final Lock lock;

	/**
	 * 큐가 꽉 찼을때 삽입을 대기하도록 하는 Condition
	 */
	private final Condition notFull;

	/**
	 * 큐가 비었을 때 반환을 대기하도록 하는 Condition
	 */
	private final Condition notEmpty;

	/**
	 * 큐에 저장될 요소들이 보관될 배열
	 */
	private final Object[] arr;

	/**
	 * 원형 큐의 끝 인덱스
	 */
	private int rear;

	/**
	 * 원형 큐의 시작 인덱스 - 1
	 */
	private int front;

	/**
	 * 큐에 현재 저장된 객체의 개수를 저장
	 */
	private int size;

	public SynchronizedQueue() {
		this.lock = new ReentrantLock();
		this.notFull = this.lock.newCondition();
		this.notEmpty = this.lock.newCondition();

		this.arr = new Object[MAX_SIZE];
		this.rear = 0;
		this.front = 0;
	}

	/**
	 * 큐의 맨 뒤에 요소를 삽입한다.
	 * 큐가 꽉 찼으면 대기한 뒤 삽입한다. (Blocking)
	 *
	 * @param elem 큐에 삽입할 요소
	 */
	public void enqueue(T elem) {
		lock.lock();
		try {
			while (this.size == MAX_SIZE) {
				this.notFull.await();
			}

			this.rear = (this.rear + 1) % MAX_SIZE;
			this.size++;
			this.arr[this.rear] = elem;
			this.notEmpty.signal();
		} catch (InterruptedException ex) {
			throw new IllegalStateException("The thread is interrupted", ex);
		} finally {
			this.lock.unlock();
		}
	}

	/**
	 * 큐의 맨 앞에 있는 요소를 삭제하고 반환한다.
	 * 큐가 비어있으면 대기한 뒤 반환한다. (Blocking)
	 *
	 * @return 큐의 맨 앞 요소
	 */
	public T dequeue() {
		lock.lock();
		try {
			while (this.size == 0) {
				this.notEmpty.await();
			}

			this.front = (this.front + 1) % MAX_SIZE;
			this.size--;
			this.notFull.signal();
			return elementData(this.front);
		} catch (InterruptedException ex) {
			throw new IllegalStateException("The thread is interrupted", ex);
		} finally {
			this.lock.unlock();
		}
	}

	/**
	 * 큐가 비었는지 여부를 반환한다.
	 *
	 * @return 큐가 비었으면 true
	 */
	public boolean isEmpty() {
		return this.rear == this.front;
	}

	public int size() {
		return this.size;
	}

	/**
	 * 인덱스에 해당하는 요소를 타입 T로 변환하여 반환한다.
	 *
	 * @param index 반환할 요소의 인덱스
	 * @return 변환된 요소
	 */
	@SuppressWarnings("unchecked")
	private T elementData(int index) {
		return (T) this.arr[index];
	}
}
