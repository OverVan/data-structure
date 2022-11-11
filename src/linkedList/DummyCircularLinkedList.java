package linkedList;

import java.util.Objects;

/**
 * 带虚拟头结点的单向循环链表
 * 
 * @param <Elem> 元素类型
 */
public class DummyCircularLinkedList<Elem> {
	/**
	 * 结点
	 */
	private class Node {
		// 元素
		private Elem elem;
		// 后继
		private Node next;

		public Node(Elem elem, Node next) {
			super();
			this.elem = elem;
			this.next = next;
		}

		public Node(Elem elem) {
			this(elem, null);
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
	// 长度
	private int size;

	public DummyCircularLinkedList() {
		dummyHead = new Node(null);
		tail = dummyHead;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getSize() {
		return size;
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
		// 头插，尾结点后继变化
		if (index == 0) {
			Node add = new Node(elem, dummyHead.next);
			dummyHead.next = add;
			// 第一次头插，头结点自反
			if (isEmpty()) {
				add.next = add;
				tail = add;
			} else {
				add = tail.next;
			}
		} else {
			Node cursor = dummyHead;
			for (int i = 0; i < index; i++) {
				cursor = cursor.next;
			}
			Node add = new Node(elem, cursor.next);
			cursor.next = add;
			// 尾插，尾结点变化
			if (index == size) {
				tail = add;
			}
		}
		size++;
	}

	/**
	 * 向表头添加一个元素 O(1)
	 * 
	 * @param elem 元素
	 */
	public void addFirst(Elem elem) {
		add(0, elem);
	}

	/**
	 * 向表尾添加一个元素 O(n)
	 * 
	 * @param elem 元素
	 */
	public void addLast(Elem elem) {
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
		// 由于循环，不能用cursor==null作退出条件
		for (int i = 0; i < size; i++) {
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
		size--;
		Node del;
		// 头删，尾结点后继变化
		if (index == 0) {
			del = dummyHead.next;
			// 删除仅有的一个结点
			if (isEmpty()) {
				dummyHead.next = null;
				tail = dummyHead;
			} else {
				dummyHead.next = del.next;
				tail = del.next;
			}
			del.next = null;
			return del.elem;
		}
		Node cursor = dummyHead;
		for (int i = 0; i < index; i++) {
			cursor = cursor.next;
		}
		del = cursor.next;
		// 尾删，尾结点变化
		if (index == size - 1) {
			tail = cursor;
		}
		cursor.next = del.next;
		del.next = null;
		return del.elem;
	}

	/**
	 * 删除首元素 O(1)
	 * 
	 * @return
	 */
	public Elem removeFirst() {
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

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("CircularLinkedList [");
		Node cursor = dummyHead.next;
		for (int i = 0; i < size; i++) {
			res.append(cursor.elem);
			if (i != size - 1) {
				res.append("->");
			}
			cursor = cursor.next;
		}
		res.append("]");
		return res.toString();
	}
}
