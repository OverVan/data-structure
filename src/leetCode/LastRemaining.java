package leetCode;

import java.util.LinkedList;
import java.util.List;

import linkedList.DummyCircularLinkedList;

/**
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 * https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 */
public class LastRemaining {
	/**
	 * 使用内置链表列表模拟过程 时间O(n)，空间O(n)
	 * 
	 * @param n
	 * @param m
	 * @return
	 */
	public static int useLinkedList(int n, int m) {
		List<Integer> linkedList = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			linkedList.add(i);
		}
		int index = 0;
		while (linkedList.size() != 1) {
			index = (index + m - 1) % linkedList.size();
			System.out.println(linkedList.remove(index));
			// n=linkedList.size
			index = index % n;
		}
		return linkedList.remove(0);
	}

	/**
	 * 使用自制循环链表模拟过程
	 * 
	 * @param n
	 * @param m
	 * @return
	 */
	public static int useDummyCircularLinkedList(int n, int m) {
		DummyCircularLinkedList<Integer> linkedList = new DummyCircularLinkedList<>();
		for (int i = 0; i < n; i++) {
			linkedList.addLast(i);
		}
		int index = 0;
		while (linkedList.getSize() != 1) {
			// 每一轮报数
			index = (index + m - 1) % linkedList.getSize();
			System.out.println(linkedList.remove(index));
			index = index % linkedList.getSize();
		}
		return linkedList.removeFirst();
	}

	/**
	 * 基于数学推导时间O(n)，空间O(1)
	 * 
	 * @param n
	 * @param m
	 * @return
	 */
	public static int infer(int n, int m) {
		int index = 0;
		for (int i = 2; i <= n; i++) {
			index = (index + m) % i;
		}
		return index;
	}

	public static void main(String[] args) {
		System.out.println(useDummyCircularLinkedList(5, 3));
		System.out.println(useLinkedList(5, 3));
		System.out.println(infer(5, 3));
	}
}
