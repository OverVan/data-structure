package leetCode;

/**
 * 2. 两数相加 https://leetcode.cn/problems/add-two-numbers/
 */
public class AddTwoNumbers {
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
	 * 用数列模拟：a1, a2, ..., an; b1, b2, ..., bn; cm = (am + bm + dm-1) % 10, m >= 1,
	 * d0 = 0. e.g. c1 = (a1 + b1 + d0) % 10, d1 = (a1 + b1 + d0) > 10 ? 1 : 0; c2 =
	 * (a2 + b2 + d1) % 10，以此类推，dm取0或1
	 * 
	 * @param list1
	 * @param list2
	 * @return 和链表
	 */
	public static ListNode addTwoNumbers(ListNode list1, ListNode list2) {
		// 求加数链表的位数
		int radix1 = 0;
		int radix2 = 0;
		ListNode cursor1 = list1;
		ListNode cursor2 = list2;
		while (cursor1 != null) {
			cursor1 = cursor1.next;
			radix1++;
		}
		while (cursor2 != null) {
			cursor2 = cursor2.next;
			radix2++;
		}
		int maxRadix = radix1 > radix2 ? radix1 : radix2;
		// m及n(m<=n)位数的和的位数只能是n或n+1，于是和链表初始化位数为n
		ListNode sumList = new ListNode(-1);
		ListNode cursor = sumList;
		for (int i = 0; i < maxRadix + 1; i++, cursor = cursor.next) {
			cursor.next = new ListNode(-1);
		}
		// 逐位求和再加上一位的进位，填和链表
		cursor1 = list1;
		cursor2 = list2;
		cursor = sumList.next;
		int val1 = 0;
		int val2 = 0;
		// d0=0
		int carray = 0;
		while (cursor != null) {
			// 任一加数链表到头了，后续位均取0
			if (cursor1 == null) {
				val1 = 0;
			} else {
				val1 = cursor1.val;
			}
			if (cursor2 == null) {
				val2 = 0;
			} else {
				val2 = cursor2.val;
			}
			int sum = val1 + val2 + carray;
			cursor.val = sum % 10;
			if (sum >= 10) {
				carray = 1;
			} else {
				carray = 0;
			}
			cursor = cursor.next;
			if (cursor1 != null) {
				cursor1 = cursor1.next;
			}
			if (cursor2 != null) {
				cursor2 = cursor2.next;
			}
		}
		// 由于加数链表非null，和链表至少有两个结点，看最后一位是否多余
		cursor = sumList.next;
		while (cursor.next != null) {
			if (cursor.next.next == null && cursor.next.val == 0) {
				cursor.next = null;
				break;
			}
			cursor=cursor.next;
		}
		return sumList.next;
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
		ListNode list1 = new ListNode(2, new ListNode(4, new ListNode(3)));
		ListNode list2 = new ListNode(5, new ListNode(6, new ListNode(4)));
//		list1 = new ListNode(0);
//		list2 = new ListNode(0);
//		list1 = new ListNode(9,
//				new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
//		list2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
		traverse(addTwoNumbers(list1, list2));
	}
}
