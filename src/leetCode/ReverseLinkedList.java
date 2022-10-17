package leetCode;

import java.util.Stack;

/**
 * 206. 反转链表
 */
public class ReverseLinkedList {
	/**
	 * 中间栈
	 * 
	 * @param head 头结点
	 * @return 反转后的头结点
	 */
	public static ListNode tempStack(ListNode head) {
		Stack<ListNode> stack = new Stack<>();
		ListNode cursor = head;
		while (cursor != null) {
			stack.push(cursor);
			cursor = cursor.next;
		}
		// 虚拟头结点
		ListNode newHead = new ListNode(0);
		cursor = newHead;
		while (!stack.isEmpty()) {
			ListNode pop = stack.pop();
			// 头结点后继为null
			if (stack.isEmpty()) {
				pop.next = null;
			}
			cursor.next = pop;
			cursor = cursor.next;
		}
		return newHead.next;
	}

	/**
	 * 中间数组
	 * 
	 * @param head 头结点
	 * @return 反转后的头结点
	 */
	public static ListNode tempArray(ListNode head) {
		ListNode cursor = head;
		int length = 0;
		while (cursor != null) {
			length++;
			cursor = cursor.next;
		}
		ListNode[] nodes = new ListNode[length];
		cursor = head;
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = cursor;
			cursor = cursor.next;
		}
		// 虚拟头结点
		ListNode newHead = new ListNode(0);
		cursor = newHead;
		// 倒序成链
		for (int i = nodes.length - 1; i >= 0; i--) {
			cursor.next = nodes[i];
			cursor = cursor.next;
			// 头结点后继为null
			if (i == 0) {
				cursor.next = null;
			}
		}
		return newHead.next;
	}

	/**
	 * 头插
	 * 
	 * @param head 头结点
	 * @return 反转后的头结点
	 */
	public static ListNode headInsert(ListNode head) {
		ListNode cursor = head;
		ListNode newHead = null;
		ListNode after = cursor;
		while (cursor != null) {
			after = cursor.next;
			if (newHead == null) {
				// 头插头结点，其后继为null
				cursor.next = null;
				newHead = cursor;
			} else {
				// 头插剩余结点
				cursor.next = newHead;
				newHead = cursor;
			}
			cursor = after;
		}
		return newHead;
	}

	/**
	 * 多指针-后继反转
	 * 
	 * @param head 头结点
	 * @return 反转后的头结点
	 */
	public static ListNode multiPointer(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode before = head;
		ListNode current = head.next;
		ListNode after = null;
		// 头结点后继为null
		before.next = null;
		while (current != null) {
			// 预留反转前的后继
			after = current.next;
			// 后继反转
			current.next = before;
			// 继续遍历
			before = current;
			current = after;
		}
		// 更新头结点
		head = before;
		return head;
	}

	/**
	 * 递归：反转头结点；递归反转子链表；反转后的子链表挂上反转后的头结点
	 * 
	 * @param head 头结点
	 * @return 反转后的头结点
	 */
	public static ListNode recur(ListNode head) {
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return head;
		}
		// 子链表反转前的头结点即反转后的尾结点
		ListNode subTail = head.next;
		head.next = null;
		// 返回子链表反转前的尾结点即反转后的头结点
		ListNode subHead = recur(subTail);
		subTail.next = head;
		return subHead;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		ListNode.traverse(recur(head));
	}
}
