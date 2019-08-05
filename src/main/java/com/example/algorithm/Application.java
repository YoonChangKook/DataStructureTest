package com.example.algorithm;

import java.util.Random;

public class Application {
	public static void main(String[] args) {
		Integer[] arr = new Random().ints(1000, 1, 1000).boxed().toArray(Integer[]::new);
		Integer target = arr[500];

		// quick sort
		Sort.quickSort(arr);
		System.out.print("Sort: ");
		for (int num : arr) {
			System.out.print(String.format("%d ", num));
		}
		System.out.println();

		// binary search
		int index = BinarySearch.search(arr, target);
		System.out.println(String.format("BinarySearch target: %d, index: %d", target, index));

		// max heap
//		MaxHeap<Integer> maxHeap = new MaxHeap<>();
//		for (int num : arr) {
//			maxHeap.insert(num);
//		}
//
//		System.out.print("Heap pop: ");
//		while (!maxHeap.isEmpty()) {
//			int root = maxHeap.get();
//			maxHeap.delete();
//
//			System.out.print(String.format("%d ", root));
//		}
//		System.out.println();

		// singleton test
		SingletonInterface instance = Singleton.getInstance();
		System.out.println(String.format("num: %d", instance.getNum()));
	}
}
