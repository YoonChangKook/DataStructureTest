package com.example.datastructure;

/**
 * 이진 탐색을 도와주는 유틸 클래스
 *
 * @author 국윤창
 */
public final class BinarySearch {
	private BinarySearch() {}

	/**
	 * 배열 arr에서 target에 해당하는 index를 반환한다.
	 * @param arr 요소를 찾을 배열
	 * @param target 찾을 요소
	 * @return 요소가 존재하는 인덱스
	 * @throws IllegalArgumentException 요소가 배열에 존재하지 않을 때
	 */
	public static <T extends Comparable<? super T>> int search(T[] arr, T target) throws IllegalArgumentException {
		int left = 0, right = arr.length - 1;

		while (left <= right) {
			int mid = (right + left) / 2;

			if (arr[mid].compareTo(target) == 0) {
				return mid;
			} else if (arr[mid].compareTo(target) < 0) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		throw new IllegalArgumentException(String.format("There is no index for the target: %s", target));
	}
}
