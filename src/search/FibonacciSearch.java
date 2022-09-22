package search;

import java.util.Arrays;

/**
 * 斐波那契查找
 * 
 * @author Van
 */
public class FibonacciSearch {
	private int[] array;// 元素数组
	private int maxSize;// 数组最大长度

	public FibonacciSearch(int[] array, int maxSize) {
		this.array = array;
		this.maxSize = maxSize;
	}

	/**
	 * 构造一个斐波那契数列
	 * 
	 * @return
	 */
	public int[] getFibonacci() {
		int[] fibonacci = new int[maxSize];
		fibonacci[0] = 1;
		fibonacci[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			// 递推式
			fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
		}
		return fibonacci;
	}

	/**
	 * 查找一个指定元素
	 * 
	 * @param target 元素
	 * @return 下标
	 */
	public int searchOne(int target) {
		int left = 0;
		int right = array.length - 1;
		int k = 0;
		int mid = 0;
		int[] fibonacci = getFibonacci();
		while (right > fibonacci[k] - 1) {
			k++;
		}
		int[] tempArray = Arrays.copyOf(array, fibonacci[k]);
		for (int i = right + 1; i < tempArray.length; i++) {
			tempArray[i] = array[right];
		}
		while (left <= right) {
			mid = left + fibonacci[k - 1] - 1;
			if (target < tempArray[mid]) {
				right = mid - 1;
				k--;
			} else if (target > tempArray[mid]) {
				left = mid + 1;
				k -= 2;
			} else {
				if (mid <= right) {
					return mid;
				} else {
					return right;
				}
			}
		}
		return -1;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 1, 8, 10, 89, 1000, 1234 };
		FibonacciSearch fibonacciSearch = new FibonacciSearch(array, 20);
		System.out.println(fibonacciSearch.searchOne(1000));
	}
}
