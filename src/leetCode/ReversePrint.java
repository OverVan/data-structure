package leetCode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 */
public class ReversePrint {
	/**
	 * 应当是不能破坏原链表结构，即不反转链表
	 * 
	 * @param head
	 * @return
	 */
	public static int[] useList(ListNode head) {
		// 双向循环链表，故descendingIterator方法为其独有，List接口下没有
		LinkedList<Integer> list = new LinkedList<>();
		ListNode cursor = head;
		while (cursor != null) {
			list.add(cursor.val);
			cursor = cursor.next;
		}
		int[] array = new int[list.size()];
		// 反向遍历
		Iterator<Integer> iterator = list.descendingIterator();
		int i = 0;
		while (iterator.hasNext()) {
			array[i++] = iterator.next();
		}
		return array;
	}

	public static int[] useArray(ListNode head) {
		int length = 0;
		ListNode cursor = head;
		while (cursor != null) {
			length++;
			cursor = cursor.next;
		}
		cursor = head;
		int[] array = new int[length];
		for (int i = array.length - 1; i >= 0; i--) {
			array[i] = cursor.val;
			cursor = cursor.next;
		}
		return array;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(2, new ListNode(1, new ListNode(3)));
		System.out.println(Arrays.toString(useList(head)));
		System.out.println(Arrays.toString(useArray(head)));
	}
}
