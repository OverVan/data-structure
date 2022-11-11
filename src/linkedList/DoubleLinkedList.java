package linkedList;

/**
 * 双向链表
 * 
 * @param <Elem> 元素类型
 */
public class DoubleLinkedList<Elem> {
	/**
	 * 结点
	 */
	private class Node {
		// 元素
		private Elem elem;
		// 后继
		private Node next;
		// 前驱
		private Node prev;

		public Node(Elem elem, Node next, Node prev) {
			this.elem = elem;
			this.next = next;
			this.prev = prev;
		}

		public Node(Elem elem) {
			this(elem, null, null);
		}

		@Override
		public String toString() {
			return elem.toString();
		}
	}

	// 虚拟头结点
	private Node dummyHead;
	// 尾结点
	private Node tail;
	private int size;

	public DoubleLinkedList() {
		dummyHead = new Node(null);
		tail = dummyHead;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 头插 O(1)
	 * 
	 * @param elem
	 */
	public void addFirst(Elem elem) {
		Node add = new Node(elem, dummyHead.next, dummyHead);
		if (isEmpty()) {
			dummyHead.next = add;
			// tail指向变化
			tail = dummyHead.next;
		} else {
			dummyHead.next.prev = add;
			dummyHead.next = add;
		}
		size++;
	}

	/**
	 * 尾插 O(1)
	 * 
	 * @param elem
	 */
	public void addLast(Elem elem) {
		tail.next = new Node(elem, null, tail);
		tail = tail.next;
		size++;
	}

	/**
	 * 头删 O(1)
	 * 
	 * @return
	 */
	public Elem removeFirst() {
		if (isEmpty()) {
			throw new RuntimeException("链表为空，无法删除");
		}
		Node del = dummyHead.next;
		dummyHead.next = del.next;
		size--;
		if (isEmpty()) {
			// tail指向变化
			tail = dummyHead;
		} else {
			del.next.prev = dummyHead;
		}
		del.next = null;
		del.prev = null;
		return del.elem;
	}

	/**
	 * 尾删 O(1)
	 * 
	 * @return
	 */
	public Elem removeLast() {
		if (isEmpty()) {
			throw new RuntimeException("链表为空，无法删除");
		}
		Node del = tail;
		del.prev.next = null;
		tail = tail.prev;
		del.prev = null;
		size--;
		return del.elem;
	}

	public String toNormalString() {
		StringBuilder res = new StringBuilder();
		res.append("DoubleLinkedList normal [");
		Node cursor = dummyHead.next;
		while (cursor != null) {
			res.append(cursor.elem);
			if (cursor.next != null) {
				res.append("->");
			}
			cursor = cursor.next;
		}
		res.append("]");
		return res.toString();
	}

	public String toReverseString() {
		StringBuilder res = new StringBuilder();
		res.append("DoubleLinkedList reverse [");
		for (Node cursor = tail; cursor != dummyHead; cursor = cursor.prev) {
			res.append(cursor.elem);
			if (cursor.prev != dummyHead) {
				res.append("->");
			}
		}
		res.append("]");
		return res.toString();
	}
}
