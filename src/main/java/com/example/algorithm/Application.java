package com.example.algorithm;

public class Application {
	public static void main(String[] args) {
		// singleton test
		SingletonInterface instance = Singleton.getInstance();
		System.out.println(String.format("num: %d", instance.getNum()));
	}
}
