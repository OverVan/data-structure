package queue;

/**
 * 基于顺序表的循环队列
 * 
 * @param <Elem> 元素类型
 */
public class LoopQueue<Elem> implements Queue<Elem> {
	// 元素数组
	private Elem[] data;
	// 队首-队首元素的下标
	private int front;
	// 队尾-队尾元素下标+1，不加1无法描述队列空的情况
	private int rear;
	// 长度-有效元素个数，仅凭front、rear也可计算得出，size则通过自增、自减来监控
	private int size;

	/**
	 * 初始化一个指定容量的队列
	 * 
	 * @param capacity
	 */
	public LoopQueue(int capacity) {
		// 预留一个无元素空间，使得队满与队空条件不冲突
		data = (Elem[]) new Object[capacity + 1];
		front = 0;
		rear = 0;
		size = 0;
	}

	/**
	 * 初始化一个默认容量的队列
	 */
	public LoopQueue() {
		this(10 + 1);
	}

	/**
	 * 获取容量
	 * 
	 * @return
	 */
	public int getCapacity() {
		return data.length - 1;
	}

	/**
	 * 获取长度 O(1)
	 * 
	 * @return
	 */
	@Override
	public int getSize() {
		// return (rear - front + data.length) % data.length;
		return size;
	}

	/**
	 * 判断队列是否为空 O(1)
	 * 
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		// 出队才趋向空，front追rear
		return front == rear;
	}

	/**
	 * 变容
	 * 
	 * @param newCapacity 新容量
	 */
	private void resize(int newCapacity) {
		// 新数组长度至少为1
		Elem[] newData = (Elem[]) new Object[newCapacity + 1];
		for (int i = 0; i < size; i++) {
			// 缩容之后原下标可能越界，故从0开始填充，i可以叫偏移量
			newData[i] = data[(front + i) % data.length];
		}
		data = newData;
		front = 0;
		rear = size;
	}

	/**
	 * O(1) 均摊
	 */
	@Override
	public void enqueue(Elem elem) {
		// 队满则扩容
		if ((rear + 1) % data.length == front) {
			resize(getCapacity() * 2);
		}
		data[rear] = elem;
		rear = (rear + 1) % data.length;
		size++;
	}

	/**
	 * 解决普通队列出队操作复杂度成O(n)的问题，用队首元素指针的移动取代出队时元素的移动 O(1) 均摊
	 */
	@Override
	public Elem dequeue() {
		// 队列非空才能出队
		if (!isEmpty()) {
			Elem elem = data[front];
			// 置不置null无所谓 data[front] = null;
			front = (front + 1) % data.length;
			size--;
			// 出队到一定程度变容
			if (size == data.length / 4 && data.length / 2 != 0) {
				resize(getCapacity() / 2);
			}
			return elem;
		}
		throw new RuntimeException("队列为空，无法出队");
	}

	/**
	 * O(1)
	 */
	@Override
	public Elem getFront() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空，无法获取队首元素");
		}
		return data[front];
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("LoopQueue front [");
		for (int i = front; i != rear; i = (i + 1) % data.length) {
			res.append(data[i]);
			if ((i + 1) % data.length != rear) {
				res.append(", ");
			}
		}
		res.append("] rear");
		return res.toString();
	}
}
