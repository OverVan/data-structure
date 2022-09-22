package queue;

import seqList.DynamicSeqList;

/**
 * 基于顺序表的队列
 * 
 * @param <Elem> 元素类型
 */
public class SeqListQueue<Elem> implements Queue<Elem> {
	private DynamicSeqList<Elem> seqList;

	/**
	 * 实例化一个指定长度的队列
	 * 
	 * @param maxSize 长度
	 */
	public SeqListQueue(int capacity) {
		this.seqList = new DynamicSeqList<>(capacity);
	}

	/**
	 * 实例化一个默认长度的队列
	 */
	public SeqListQueue() {
		this.seqList = new DynamicSeqList<>();
	}

	/**
	 * O(1)
	 */
	@Override
	public boolean isEmpty() {
		return seqList.isEmpty();
	}

	/**
	 * O(1) 均摊
	 */
	@Override
	public void enqueue(Elem elem) {
		// 从表尾入队
		seqList.addLast(elem);
	}

	/**
	 * O(n)
	 */
	@Override
	public Elem dequeue() {
		// 从表头出队
		return seqList.removeFirst();
	}

	/**
	 * O(1)
	 */
	@Override
	public Elem getFront() {
		return seqList.get(0);
	}

	/**
	 * O(1)
	 */
	@Override
	public int getSize() {
		return seqList.getSize();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder(seqList.getSize());
		res.append("SeqListQueue front [");
		for (int i = 0; i < seqList.getSize(); i++) {
			res.append(seqList.get(i));
			if (i != seqList.getSize() - 1) {
				res.append(", ");
			}
		}
		res.append("] rear");
		return res.toString();
	}
}
