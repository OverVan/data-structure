package queue;

import heap.SeqListMaxHeap;

/**
 * 基于顺序表最大堆的优先队列
 * 
 * @param <Elem> 元素类型，不可取null
 */
public class SeqListMaxHeapPriorityQueue<Elem extends Comparable<Elem>> implements Queue<Elem> {
	private SeqListMaxHeap<Elem> heap;

	/**
	 * 实例化默认初始容量的优先队列
	 */
	public SeqListMaxHeapPriorityQueue() {
		heap = new SeqListMaxHeap<>();
	}

	@Override
	public void enqueue(Elem elem) {
		heap.add(elem);
	}

	@Override
	public Elem dequeue() {
		// 出队元素一定是最大的
		return heap.extractMax();
	}

	@Override
	public Elem getFront() {
		// 获取堆顶元素
		return heap.findMax();
	}

	@Override
	public int getSize() {
		return heap.getSize();
	}

	@Override
	public boolean isEmpty() {
		return heap.isEmpty();
	}

}
