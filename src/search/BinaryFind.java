package search;

public class BinaryFind {
	public int recursionFind(int[] arr, int left, int right, int target) {
		int middle = (int) (left + right) / 2;
		if (target == arr[middle]) {
			return middle;
		}
		// 左右指针相遇前要先判断是否找到
		if (left >= right) {
			return -1;
		}
		// 左半子数组递归
		if (target < arr[middle]) {
			return recursionFind(arr, left, middle - 1, target);
		}
		// 右半子数组递归
		if (target > arr[middle]) {
			return recursionFind(arr, middle + 1, right, target);
		}
		return -1;
	}

	public int iterationFind(int[] arr, int left, int right, int target) {
		int middle = 0;
		while (left <= right) {
			middle = (int) (left + right) / 2;
			if (target == arr[middle]) {
				return middle;
			}
			if (target < arr[middle]) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] arr = { -1, 0, 3, 5, 9, 12 };
//		int[] arr = { 2, 5 };
		BinaryFind binaryFind = new BinaryFind();
		System.out.println(binaryFind.recursionFind(arr, 0, arr.length - 1, 9));
		System.out.println(binaryFind.iterationFind(arr, 0, arr.length - 1, 9));
	}

}
