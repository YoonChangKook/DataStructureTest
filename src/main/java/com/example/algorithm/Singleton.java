package com.example.algorithm;

/**
 * lazy-loading 싱글톤 테스트 클래스
 *
 * @author 국윤창
 */
public class Singleton implements SingletonInterface {
	private Singleton() {
		System.out.println("CREATED.");
	}

	@Override
	public int getNum() {
		return 100;
	}

	public static SingletonInterface getInstance() {
		return SingletonInstance.instance;
	}

	private static class SingletonInstance {
		private static Singleton instance = new Singleton();
	}
}
