package leetCode;

/**
 * 19. 删除链表的倒数第 N 个结点
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEndOfList {
	/**
	 * 朴素：两次循环，不过总访问结点次数与双指针法是一样的
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode twoLoop(ListNode head, int n) {
		// 第一次循环得到链表长度
		int length = 0;
		ListNode cursor = head;
		while (cursor != null) {
			cursor = cursor.next;
			length++;
		}
		// 算出正数第几
		int forward = length - n + 1;
		ListNode dummyHead = new ListNode(-1, head);
		// 第二次循环，找到目标结点的前驱
		cursor = dummyHead;
		for (int i = 0; i < forward - 1; i++) {
			cursor = cursor.next;
		}
		ListNode temp = cursor.next;
		cursor.next = temp.next;
		temp.next = null;
		return dummyHead.next;
	}

	public static ListNode doublePointer(ListNode head, int n) {
		ListNode dummyHead = new ListNode(-1, head);
		ListNode front = dummyHead;
		ListNode rear = dummyHead;
		int i = 0;
		while (front.next != null) {
			front = front.next;
			// 前指针走到第n步，后指针才开始走
			if (i < n) {
				i++;
			} else {
				rear = rear.next;
			}
		}
		// 当前指针走到尾结点，后指针即走到目标结点的前驱
		ListNode temp = rear.next;
		rear.next = temp.next;
		temp.next = null;
		return dummyHead.next;
	}

	public static void main(String[] args) {
		ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//		ListNode.traverse(twoLoop(list, 2));
		ListNode.traverse(doublePointer(list, 2));
	}
}
