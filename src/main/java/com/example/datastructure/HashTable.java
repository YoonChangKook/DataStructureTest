package com.example.datastructure;

/**
 * 해시 테이블
 *
 * @param <K> 해시테이블에 저장할 키의 클래스
 * @param <V> 해시테이블에 저장할 값의 클래스
 * @author 국윤창
 */
// TODO: remove, containsKey 등의 함수 구현하기
public class HashTable<K, V> {
	// 100에 최대한 가까운 소수
	private static final int MAX_HASH_TABLE_SIZE = 101;

	private Node<K, V>[] table;

	public HashTable() {
		@SuppressWarnings("unchecked")
		Node<K, V>[] newTable = (Node<K, V>[]) new Node[MAX_HASH_TABLE_SIZE];
		this.table = newTable;
	}

	/**
	 * 키, 값 쌍을 해시테이블에 저장한다.
	 * 만약 키가 이미 존재하면, 값을 덮어씌운다.
	 *
	 * @param key 저장할 키
	 * @param value 키에 대한 값
	 * @return 덮어씌워지기 전 해시테이블에 존재하던 값. 덮어씌워지지 않았다면 null을 반환한다.
	 * @throws IllegalArgumentException 키가 비었을 때
	 */
	public V put(K key, V value) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException("The key is empty");
		}

		int hash;
		Node<K, V> node;
		// 버킷이 비었을 때, 노드를 바로 생성한다.
		if ((node = this.table[getBucketIndex(hash = key.hashCode())]) == null) {
			this.table[getBucketIndex(hash)] = new Node<>(hash, key, value, null);
		} else {
			Node<K, V> previousNode;
			K nodeKey;
			// chaining으로 key에 해당하는 value가 존재하는지 확인
			do {
				previousNode = node;
				if (hash == node.hash && ((nodeKey = node.key) == key || key.equals(nodeKey))) {
					break;
				}
			} while ((node = node.next) != null);

			// key에 해당하는 value가 있으면 덮어씌우고 이전 값 반환. 그렇지 않으면 새로운 노드 생성
			if (node != null) {
				V oldValue = node.value;
				node.value = value;
				return oldValue;
			} else {
				previousNode.next = new Node<>(hash, key, value, null);
			}
		}
		// key에 해당하는 value가 존재하지 않으면 null 반환
		return null;
	}

	/**
	 * 해시테이블에서 키에 해당하는 값을 반환한다.
	 *
	 * @param key 키
	 * @return 키에 해당하는 값
	 */
	public V get(K key) {
		int hash;
		Node<K, V> node;
		// key가 비어있거나 key hash에 해당하는 버킷이 비었으면 바로 null 반환
		if (key == null || (node = this.table[getBucketIndex((hash = key.hashCode()))]) == null) {
			return null;
		}

		// chaining으로 key에 해당하는 value 탐색 후 반환
		K nodeKey;
		do {
			if (hash == node.hash && ((nodeKey = node.key) == key || key.equals(nodeKey))) {
				return node.value;
			}
		} while ((node = node.next) != null);
		// key에 해당하는 value가 존재하지 않으면 null 반환
		return null;
	}

	/**
	 * 해시값에 해당하는 인덱스를 반환한다.
	 *
	 * @param hash 해시값
	 * @return 해시값에 해당하는 인덱스
	 */
	private int getBucketIndex(int hash) {
		int index = hash % MAX_HASH_TABLE_SIZE;
		return index > 0 ? index : -index;
	}

	/**
	 * 해시테이블의 키, 값 쌍을 나타내는 클래스
	 *
	 * @param <K> 저장할 키의 클래스
	 * @param <V> 저장할 값의 클래스
	 */
	public static class Node<K, V> {
		/**
		 * 키의 해시 값
		 */
		private final int hash;

		/**
		 * 해시 테이블 키
		 */
		private final K key;

		/**
		 * 해시 테이블 값
		 */
		private V value;

		/**
		 * Chaining을 위한 필드
		 */
		Node<K, V> next;

		public Node(int hash, K key, V value, Node<K, V> next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public int getHash() {
			return hash;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}
}
