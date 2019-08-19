package com.example.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 정렬 메서드를 제공하는 클래스
 *
 * @author 국윤창
 */
public final class Sort {
	private Sort() {}

	public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	public static <T extends Comparable<? super T>> void quickSort(T[] arr, int start, int end) {
		int left = start, right = end;
		T pivot = arr[start];

		while (left < right) {
			while (left < right && pivot.compareTo(arr[right]) <= 0) right--;
			while (left < right && arr[left].compareTo(pivot) <= 0) left++;
			if (left < right) swapInArr(arr, left, right);
		}
		swapInArr(arr, left, start);

		if (start < left) {
			quickSort(arr, start, left - 1);
		}
		if (right < end) {
			quickSort(arr, left + 1, end);
		}
	}

	public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}

	public static <T extends Comparable<? super T>> void mergeSort(T[] arr, int start, int end) {
		if (start < end) {
			int middle = (start + end) / 2;
			mergeSort(arr, start, middle);
			mergeSort(arr, middle + 1, end);
			merge(arr, start, end, middle);
		}
	}

	private static <T extends Comparable<? super T>> void merge(T[] arr, int start, int end, int middle) {
		List<T> result = new ArrayList<>();
		int left = start, right = middle + 1;

		while (left <= middle && right <= end) {
			if (arr[left].compareTo(arr[right]) < 0) result.add(arr[left++]);
			else result.add(arr[right++]);
		}

		while (left <= middle) result.add(arr[left++]);
		while (right <= end) result.add(arr[right++]);

		for (int i = start, j = 0; i <= end; i++, j++) {
			arr[i] = result.get(j);
		}
	}

	/**
	 * 배열 arr의 두 원소를 서로 교환한다.
	 *
	 * @param arr 타겟 배열
	 * @param index1 바꿀 인덱스 1
	 * @param index2 바꿀 인덱스 2
	 */
	private static <T> void swapInArr(T[] arr, int index1, int index2) {
		T temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
}
