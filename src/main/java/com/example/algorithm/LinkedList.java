package com.example.algorithm;

/**
 * 연결 리스트
 *
 * @param <T> 연결 리스트에 저장할 객체의 타입
 */
public class LinkedList<T> {
	private Node<T> head;

	public LinkedList() {
		this.head = null;
	}

	/**
	 * 연결 리스트 중간에 노드 삽입한다.
	 *
	 * @param preNode 삽입할 노드의 이전 노드
	 * @param data 삽입할 노드의 데이터
	 */
	public void insert(Node preNode, T data) {
		Node<T> newNode = new Node<>();
		newNode.setData(data);

		newNode.setLink(preNode.getLink());

		preNode.setLink(newNode);
	}

	/**
	 * 연결 리스트 맨 뒤에 노드를 삽입한다.
	 *
	 * @param data 삽입할 노드의 데이터
	 */
	public void push(T data) {
		Node<T> newNode = new Node<>();
		newNode.setData(data);

		if (this.head == null) {
			this.head = newNode;
		} else {
			Node<T> temp = head;

			// 마지막 노드 찾기
			while (temp.getLink() != null) {
				temp = temp.getLink();
			}

			// 마지막에 삽입
			temp.setLink(newNode);
		}
	}

	/**
	 * 주어진 데이터와 일치하는 노드를 삭제한다.
	 *
	 * @param data 삭제할 노드의 데이터
	 * @throws IllegalArgumentException 데이터와 일치하는 노드가 없을 때
	 */
	public void delete(T data) throws IllegalArgumentException {
		Node<T> preNode = this.head;
		Node<T> currentNode = this.head.getLink();

		if (data.equals(preNode.getData())) {
			// 첫번째 노드가 일치할 때
			this.head = currentNode;
			preNode.setLink(null);

			return;
		} else {
			while (currentNode != null) {
				if (data.equals(currentNode.getData())) {
					// 마지막 노드인 경우
					if (currentNode.getLink() == null) {
						preNode.setLink(null);
					} else {
						preNode.setLink(currentNode.getLink());
						currentNode.setLink(null);
					}

					return;
				}

				preNode = currentNode;
				currentNode = currentNode.getLink();
			}
		}

		throw new IllegalArgumentException(String.format("The data %s does not exist in the list", data));
	}

	/**
	 * 주어진 데이터와 일치하는 노드를 찾아서 반환한다.
	 *
	 * @param data 찾을 노드의 데이터
	 * @return 주어진 데이터와 일치하는 노드
	 * @throws IllegalArgumentException 데이터와 일치하는 노드가 없을 때
	 */
	public Node<T> search(T data) throws IllegalArgumentException {
		Node<T> currentNode = this.head;

		while (currentNode != null) {
			if (data.equals(currentNode.getData())) {
				return currentNode;
			}

			currentNode = currentNode.getLink();
		}

		throw new IllegalArgumentException(String.format("The data %s does not exist in the list", data));
	}

	/**
	 * 리스트가 비어있는지 여부를 반환한다.
	 *
	 * @return 리스트가 비어있으면 true
	 */
	public boolean isEmpty() {
		return this.head == null;
	}

	/**
	 * 리스트에 존재하는 모든 노드를 출력한다.
	 */
	public void print() {
		Node<T> currentNode = this.head;

		System.out.print("Linked List: ");
		while (currentNode != null) {
			System.out.print(String.format("%s -> ", currentNode.getData()));
			currentNode = currentNode.getLink();
		}
		System.out.println();
	}

	/**
	 * 연결 리스트를 구성하는 노드 클래스
	 *
	 * @param <T> 노드에 저장할 객체의 타입
	 */
	public static class Node<T> {
		private T data;
		private Node<T> link;

		public Node() {
			this.data = null;
			this.link = null;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> getLink() {
			return link;
		}

		public void setLink(Node<T> link) {
			this.link = link;
		}
	}
}
