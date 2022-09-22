package queue;

/**
 * 基于单链表的队列
 * 
 * @param <Elem> 元素类型
 */
public class LinkedListQueue<Elem> implements Queue<Elem> {
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

	// 因为只涉及到两端的入队、出队，没有中间的插入、删除，不用统一头结点与非头结点的插入、删除，故无需虚拟头结点
	private Node front;
	// 设定从表头出队，从表尾入队，为了让后者的复杂度也为O(1)，即避免为了获取最后一个结点的遍历，引入尾指针
	private Node rear;
	private int size;

	public LinkedListQueue() {
		super();
	}

	/**
	 * O(1)
	 */
	@Override
	public void enqueue(Elem elem) {
		// 第一次入队后，front不能继续指null
		if (isEmpty()) {
			rear = new Node(elem);
			front = rear;
		} else {
			rear.next = new Node(elem);
			// 尾指针后移
			rear = rear.next;
		}
		size++;
	}

	/**
	 * O(1)
	 */
	@Override
	public Elem dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空，无法出队");
		}
		Node del = front;
		// 头指针后移
		front = del.next;
		// 最后一次出队后，尾指针置null
		if (front == null) {
			rear = null;
		}
		// 解挂出队结点
		del.next = null;
		size--;
		return del.elem;
	}

	@Override
	public Elem getFront() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空，无头结点");
		}
		return front.elem;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("LinkedListQueue front [");
		for (Node cursor = front; cursor != rear.next; cursor = cursor.next) {
			res.append(cursor.elem);
			if (cursor != rear) {
				res.append("->");
			}
		}
		res.append("] rear");
		return res.toString();
	}
}
