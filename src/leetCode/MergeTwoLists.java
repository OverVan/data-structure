package leetCode;

/**
 * 21. 合并两个有序链表
 */
public class MergeTwoLists {
	static class ListNode {
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
	}

	/**
	 * @param list1 这两个链表非递减
	 * @param list2
	 * @return 合并链表也非递减
	 */
	public static ListNode mergeTwoList(ListNode list1, ListNode list2) {
		// 合并链表及其游标，虚拟头结点
		ListNode mergeList = new ListNode(0);
		ListNode cursor = mergeList;
		// 双游标
		ListNode cursor1 = list1;
		ListNode cursor2 = list2;
		// 任一游标到了null就退出循环
		while (cursor1 != null && cursor2 != null) {
			// 两游标比值，较小的那个被挂上合并链表，并后移
			if (cursor1.val < cursor2.val) {
				cursor.next = cursor1;
				cursor1 = cursor1.next;
			} else {
				cursor.next = cursor2;
				cursor2 = cursor2.next;
			}
			cursor = cursor.next;
		}
		// 必然有且仅有一方已经全部挂上，那么一次性挂上另一方剩余结点
		if (cursor1 == null) {
			cursor.next = cursor2;
		} else {
			cursor.next = cursor1;
		}
		// 返回合并链表
		return mergeList.next;
	}

	/**
	 * 遍历链表
	 * 
	 * @param head 头结点
	 */
	public static void traverse(ListNode head) {
		ListNode cursor = head;
		while (cursor != null) {
			System.out.println(cursor.val);
			cursor = cursor.next;
		}
	}

	public static void main(String[] args) {
//		ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
		ListNode list1 = null;
		ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
//		ListNode list2 = null;
		traverse(mergeTwoList(list1, list2));
	}
}
