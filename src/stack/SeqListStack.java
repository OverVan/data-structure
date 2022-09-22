package stack;

import seqList.DynamicSeqList;

/**
 * 基于顺序表的栈
 * 
 * @param <Elem> 元素类型
 */
public class SeqListStack<Elem> implements Stack<Elem> {
	private DynamicSeqList<Elem> seqList;

	/**
	 * 实例化指定容量的栈
	 * 
	 * @param capacity
	 */
	public SeqListStack(int capacity) {
		this.seqList = new DynamicSeqList<>(capacity);
	}

	/**
	 * 实例化默认容量的栈
	 */
	public SeqListStack() {
		this.seqList = new DynamicSeqList<>();
	}

	/**
	 * O(1) 均摊
	 */
	@Override
	public void push(Elem elem) {
		// 从表尾入栈
		seqList.addLast(elem);
	}

	/**
	 * O(1) 均摊
	 */
	@Override
	public Elem pop() {
		// 从表尾出栈
		return seqList.removeLast();
	}

	/**
	 * O(1)
	 */
	@Override
	public Elem peek() {
		return seqList.getLast();
	}

	/**
	 * O(1)
	 */
	@Override
	public int getSize() {
		return seqList.getSize();
	}

	/**
	 * O(1)
	 */
	@Override
	public boolean isEmpty() {
		return seqList.isEmpty();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder(seqList.getSize());
		res.append("SeqListStack [");
		for (int i = 0; i < seqList.getSize(); i++) {
			res.append(seqList.get(i));
			if (i != seqList.getSize() - 1) {
				// 栈顶标识置于右侧
				res.append(", ");
			}
		}
		res.append("] top");
		return res.toString();
	}
}
