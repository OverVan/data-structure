package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 * https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/
 */
public class Fibonacci {
	private static Map<Integer, Integer> memory = new HashMap<>();

	/**
	 * n过大导致栈溢出 时间O(n!)，空间O(1)
	 * 
	 * @param n
	 * @return
	 */
	public static int fib(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return (int) ((fib(n - 1) + fib(n - 2)) % (1E9 + 7));
	}

	/**
	 * 缓存已算过的斐波那契数 时间O(n)，空间O(n)
	 * 
	 * @param n
	 * @return
	 */
	public static int remember(int n) {
		if (n == 0) {
			memory.put(0, 0);
			return 0;
		}
		if (n == 1) {
			memory.put(1, 1);
			return 1;
		}
		Integer first = memory.get(n - 1);
		Integer second = memory.get(n - 2);
		if (first == null) {
			first = remember(n - 1);
			memory.put(n - 1, first);
		}
		if (second == null) {
			second = remember(n - 2);
			memory.put(n - 2, second);
		}
		return (int) ((first + second) % (1e9 + 7));
	}

	public static void main(String[] args) {
		System.out.println(fib(5));
		System.out.println(remember(100));
	}
}
