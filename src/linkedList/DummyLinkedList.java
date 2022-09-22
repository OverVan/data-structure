package linkedList;

import java.util.Objects;

/**
 * 单向链表，带虚拟头结点
 * 
 * @param <Elem> 元素类型
 */
public class DummyLinkedList<Elem> {
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
		 * @param next 后继结点
		 */
		public Node(Elem elem, Node next) {
			this.elem = elem;
			this.next = next;
		}

		/**
		 * 实例化一个指定元素、无后继的结点
		 * 
		 * @param elem 元素
		 */
		public Node(Elem elem) {
			this(elem, null);
		}

		@Override
		public String toString() {
			// 只描述元素
			return elem.toString();
		}
	}

	// 头结点
	private Node dummyHead;
	// 长度-元素个数
	private int size;

	/**
	 * 实例化一个空链表
	 */
	public DummyLinkedList() {
		dummyHead = new Node(null);
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
	 * 在指定位置添加元素 O(n)
	 * 
	 * @param index 位置
	 * @param elem  元素
	 */
	public void add(int index, Elem elem) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("添加位置非法");
		}
		// 找到目标位置的前驱，统一头结点与其他结点
		Node cursor = dummyHead;
		for (int i = 0; i < index; i++) {
			cursor = cursor.next;
		}
		cursor.next = new Node(elem, cursor.next);
		size++;
	}

	/**
	 * 向表头添加一个元素 O(1)
	 * 
	 * @param elem 元素
	 */
	public void addFirst(Elem elem) {
		// 复用add
		add(0, elem);
	}

	/**
	 * 向表尾添加一个元素 O(n)
	 * 
	 * @param elem 元素
	 */
	public void addLast(Elem elem) {
		// 复用add
		add(size, elem);
	}

	/**
	 * 校验删除、修改或获取位置
	 * 
	 * @param index
	 * @return
	 */
	private boolean validateIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("指定位置非法");
		}
		return true;
	}

	/**
	 * 获取指定位置上的元素 O(n)
	 * 
	 * @param index 位置
	 * @return
	 */
	public Elem get(int index) {
		validateIndex(index);
		Node cursor = dummyHead.next;
		for (int i = 0; i < index; i++) {
			cursor = cursor.next;
		}
		return cursor.elem;
	}

	/**
	 * 获取首元素 O(1)
	 * 
	 * @return
	 */
	public Elem getFirst() {
		// 复用get
		return get(0);
	}

	/**
	 * 获取尾元素 O(n)
	 * 
	 * @return
	 */
	public Elem getLast() {
		return get(size - 1);
	}

	/**
	 * 修改指定位置上的元素 O(n)
	 * 
	 * @param index 位置
	 * @param elem  新元素
	 */
	public void set(int index, Elem elem) {
		validateIndex(index);
		Node cursor = dummyHead.next;
		for (int i = 0; i < index; i++) {
			cursor = cursor.next;
		}
		cursor.elem = elem;
	}

	/**
	 * 判断是否含有指定元素 O(n)
	 * 
	 * @param elem 元素
	 * @return
	 */
	public boolean contains(Elem elem) {
		Node cursor = dummyHead.next;
		while (cursor != null) {
			if (Objects.equals(cursor.elem, elem)) {
				return true;
			}
			cursor = cursor.next;
		}
		return false;
	}

	/**
	 * 删除并获取指定位置上的元素 O(n)
	 * 
	 * @param index 位置
	 * @return 元素
	 */
	public Elem remove(int index) {
		validateIndex(index);
		Node cursor = dummyHead;
		// 找到目标位置的前驱
		for (int i = 0; i < index; i++) {
			cursor = cursor.next;
		}
		Node del = cursor.next;
		cursor.next = del.next;
		// 让被删结点不再接入链表
		del.next = null;
		size--;
		return del.elem;
	}

	/**
	 * 删除首元素 O(1)
	 * 
	 * @return
	 */
	public Elem removeFirst() {
		// 复用remove
		return remove(0);
	}

	/**
	 * 删除尾元素 O(n)
	 * 
	 * @return
	 */
	public Elem removeLast() {
		return remove(size - 1);
	}

	/**
	 * 删除第一个指定元素
	 * 
	 * @param elem 元素
	 */
	public void removeElem(Elem elem) {
		Node cursor = dummyHead;
		Node del = null;
		while (cursor.next != null) {
			if (Objects.equals(cursor.next.elem, elem)) {
				del = cursor.next;
				cursor.next = del.next;
				del.next = null;
				size--;
				// 找到一个马上退出
				return;
			} else {
				cursor = cursor.next;
			}
		}
	}

	/**
	 * 删除所有指定元素 循环 O(n)
	 * 
	 * @param elem 元素
	 */
	public void removeAll(Elem elem) {
		Node cursor = dummyHead;
		Node del = null;
		while (cursor.next != null) {
			if (Objects.equals(cursor.next.elem, elem)) {
				del = cursor.next;
				cursor.next = del.next;
				del.next = null;
				size--;
			} else {
				cursor = cursor.next;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("LinkedList [");
		Node cursor = dummyHead.next;
		// 可等价替换为for，还是两种for，一种根据size以i循环，一种以cursor循环
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
}
