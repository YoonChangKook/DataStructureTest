package com.example.datastructure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingletonTest {
	@Test
	public void getInstanceTest() {
		SingletonInterface instance1 = Singleton.getInstance();
		SingletonInterface instance2 = Singleton.getInstance();

		assertEquals(instance1, instance2);
		assertEquals(instance1.getNum(), instance2.getNum());
	}
}
