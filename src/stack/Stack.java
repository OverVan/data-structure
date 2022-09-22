package stack;

/**
 * 栈
 * 
 * @param <Elem> 元素类型
 */
public interface Stack<Elem> {
	/**
	 * 获取长度
	 * 
	 * @return
	 */
	int getSize();

	/**
	 * 判断栈是否为空
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 入栈
	 * 
	 * @param elem 入栈元素
	 */
	void push(Elem elem);

	/**
	 * 出栈
	 * 
	 * @return 出栈元素
	 */
	Elem pop();

	/**
	 * 获取栈顶元素但不出栈
	 * 
	 * @return
	 */
	Elem peek();
}
