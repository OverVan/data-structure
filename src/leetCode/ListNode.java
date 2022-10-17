package leetCode;

public class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	/**
	 * 遍历链表
	 * 
	 * @param head 头结点
	 */
	public static void traverse(ListNode head) {
		StringBuilder res = new StringBuilder();
		res.append("[");
		ListNode cursor = head;
		while (cursor != null) {
			res.append(cursor.val);
			if (cursor.next != null) {
				res.append(", ");
			}
			cursor = cursor.next;
		}
		res.append("]");
		System.out.println(res.toString());
	}
}
