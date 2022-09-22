package linkedList;

import java.util.Objects;

/**
 * 单向链表
 * 
 * @param <Elem> 元素类型
 */
public class LinkedList<Elem> {
	/**
	 * 结点
	 */
	private class Node {
		// 元素
		private Elem elem;
		// 后继
		private Node next;

		/**
		 * 实例化一个指定元素与后继的结点
		 * 
		 * @param elem 元素
		 * @param next 后继
		 */
		public Node(Elem elem, Node next) {
			this.elem = elem;
			this.next = next;
		}

		@Override
		public String toString() {
			// 只描述元素
			return elem.toString();
		}
	}

	// 头结点
	private Node head;
	// 长度-元素个数
	private int size;

	/**
	 * 实例化一个空链表
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * 获取长度
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 在指定位置添加元素
	 * 
	 * @param index 位置
	 * @param elem  元素
	 */
	public void add(int index, Elem elem) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("添加位置非法");
		}
		// 头结点没有前驱，复用addFirst
		if (index == 0) {
			// 跟顺序表刚好相反，顺序表中addFirst复用add，此处add复用addFirst
			addFirst(elem);
		} else {
			// 找到目标位置的前驱
			Node cursor = head;
			for (int i = 0; i < index - 1; i++) {
				cursor = cursor.next;
			}
			cursor.next = new Node(elem, cursor.next);
			size++;
		}
	}

	/**
	 * 向表头添加一个元素
	 * 
	 * @param elem 元素
	 */
	public void addFirst(Elem elem) {
		head = new Node(elem, head);
		size++;
	}

	/**
	 * 向表尾添加一个元素
	 * 
	 * @param elem 元素
	 */
	public void addLast(Elem elem) {
		// 复用add
		add(size, elem);
	}

	/**
	 * 从当前链表删除所有指定元素所属结点 递归
	 * 
	 * @param elem 元素
	 * @param head 头结点
	 * @return 新的头结点
	 */
	private Node removeAll(Elem elem, Node head) {
		if (head == null) {
			// 递归终止条件
			return null;
		}
		Node del = head;
		// 把一段链表分成头结点与其他结点组成的子链表，若头结点匹配指定元素
		if (Objects.equals(head.elem, elem)) {
			head = head.next;
			// 解挂
			del.next = null;
			// 继续处理子链表
			head = removeAll(elem, head);
		} else {
			// 直接处理子链表
			head.next = removeAll(elem, head.next);
		}
		return head;
	}

	/**
	 * 删除所有指定元素
	 * 
	 * @param elem 元素
	 */
	public void removeAll(Elem elem) {
		head = removeAll(elem, head);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("LinkedList [");
		for (Node cursor = head; cursor != null; cursor = cursor.next) {
			res.append(cursor.elem);
			if (cursor.next != null) {
				res.append("->");
			}
		}
		res.append("]");
		return res.toString();
	}
}
