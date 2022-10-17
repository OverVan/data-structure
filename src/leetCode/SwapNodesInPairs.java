package leetCode;

/**
 * 24. 两两交换链表中的节点 https://leetcode.cn/problems/swap-nodes-in-pairs/
 */
public class SwapNodesInPairs {
	public static ListNode swapPairs(ListNode head) {
		// 至少有两个结点
		if (head != null && head.next != null) {
			ListNode dummyHead = new ListNode(-1, head);
			// 三指针
			ListNode before = dummyHead;
			ListNode current = before.next;
			ListNode after = current.next;
			while (true) {
				current.next = after.next;
				after.next = current;
				before.next = after;
				// 前面的交换完毕，剩余0个或1个结点就退出
				if (current.next != null && current.next.next != null) {
					before = current;
					current = current.next;
					after = current.next;
				} else {
					break;
				}
			}
			return dummyHead.next;
		}
		return head;
	}

	public static void main(String[] args) {
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
//		ListNode head = new ListNode(1, null);
//		ListNode head = null;
		ListNode head = new ListNode(1, new ListNode(2, null));
		ListNode.traverse(swapPairs(head));
	}
}
