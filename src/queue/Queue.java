package queue;

/**
 * 队列
 * 
 * @param elem 元素类型
 */
public interface Queue<Elem> {
	/**
	 * 入队
	 * 
	 * @param elem 入队元素
	 */
	void enqueue(Elem elem);

	/**
	 * 出队
	 * 
	 * @return 出队元素
	 */
	Elem dequeue();

	/**
	 * 获取队首元素但不出队
	 * 
	 * @return
	 */
	Elem getFront();

	/**
	 * 获取长度
	 * 
	 * @return
	 */
	int getSize();

	/**
	 * 判断队列是否为空
	 * 
	 * @return
	 */
	boolean isEmpty();
}
