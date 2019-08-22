package com.example.datastructure;

import com.example.algorithm.Problem9935;

import java.util.*;
import java.util.Queue;

public class Application {
	public static void main(String[] args) {
		// singleton test
		SingletonInterface instance = Singleton.getInstance();
		System.out.println(String.format("num: %d", instance.getNum()));

		//Problem9935.run();

		Map<Character, Integer> map = new TreeMap<>();
		String testStr = "asdfkjhuhzlkxcvjjsgASDFCXVASDFEFiuohsd987gy340985u394uhtgui834hg87her4ghauiefsdf";
		for (char c : testStr.toCharArray()) {
			int count = map.getOrDefault(c, 0);
			map.put(c, count + 1);
		}
		System.out.println(map);
	}
}
