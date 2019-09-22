package com.example.datastructure;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HashTableTest {
	private static final int MIN_LENGTH = 10;
	private static final int MAX_LENGTH = 30;
	private static final int TEST_SIZE = 10000;

	@Test
	public void putTest() {
		HashTable<String, String> hashTable = new HashTable<>();
		String testKey = getRandomString(), testValue1 = getRandomString(), testValue2 = getRandomString();

		String previousValue = hashTable.put(testKey, testValue1);
		assertNull(previousValue);
		System.out.println(String.format("key: %s, value: %s", testKey, testValue1));

		// 이전 값이 덮어 씌워지는지 확인
		previousValue = hashTable.put(testKey, testValue2);
		assertEquals(testValue1, previousValue);
		System.out.println(String.format("key: %s, value: %s", testKey, testValue2));
	}

	@Test
	public void putAndGetTest() {
		HashTable<String, String> hashTable = new HashTable<>();
		// 테스트 데이터 생성
		String[] keys = new String[TEST_SIZE];
		String[] values = new String[TEST_SIZE];
		for (int i = 0; i < TEST_SIZE; i++) {
			keys[i] = getRandomString();
			values[i] = getRandomString();
			hashTable.put(keys[i], values[i]);
		}

		// 모든 키, 값 쌍이 제대로 존재하는지 확인
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(values[i], hashTable.get(keys[i]));
		}
	}

	/**
	 * 무작위 길이({@link #MIN_LENGTH} ~ {@link #MAX_LENGTH})의 무작위 알파벳, 숫자 문자열을 반환한다.
	 *
	 * @return 무작위 문자열
	 */
	private String getRandomString() {
		int strLen = new Random().nextInt() % (MAX_LENGTH - MIN_LENGTH);
		strLen = strLen > 0 ? MIN_LENGTH + strLen : MIN_LENGTH - strLen;
		return RandomStringUtils.randomAlphanumeric(strLen);
	}
}
