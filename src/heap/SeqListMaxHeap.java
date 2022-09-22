package heap;

import seqList.DynamicSeqList;

/**
 * 基于变容顺序表的大顶堆
 * 
 * @param <Elem> 元素类型，必须可比，不可取null
 */
public class SeqListMaxHeap<Elem extends Comparable<Elem>> {
	// 变容顺序表
	private DynamicSeqList<Elem> seqList;

	/**
	 * 实例化指定初始容量的堆
	 * 
	 * @param capacity 初始容量
	 */
	public SeqListMaxHeap(int capacity) {
		seqList = new DynamicSeqList<>(capacity);
	}

	/**
	 * 实例化默认初始容量的堆
	 */
	public SeqListMaxHeap() {
		seqList = new DynamicSeqList<>();
	}

	/**
	 * 堆化（heapify）：将数组转化成堆
	 * 
	 * @param elems 数组
	 */
	public SeqListMaxHeap(Elem[] elems) {
		// 朴素的想法是依次取元素做下沉，复杂度是O(nlogn)
		// 更好的做法是从最后一个非叶子结点下标开始往前遍历，依次做下沉，复杂度难算，为O(n)
		seqList = new DynamicSeqList<>(elems);
		if (elems.length > 1) {
			// 最后一个非叶子结点即最大编号结点的父结点
			for (int i = parent(getSize() - 1); i >= 0; i--) {
				siftDown(i);
			}
		}
	}

	public int getSize() {
		return seqList.getSize();
	}

	public boolean isEmpty() {
		return seqList.isEmpty();
	}

	/**
	 * 获取指定下标结点的父结点的下标
	 * 
	 * @param index 子结点下标
	 * @return 父结点下标
	 */
	private int parent(int index) {
		return (index - 1) / 2;
	}

	/**
	 * 获取指定下标结点的左孩子的下标
	 * 
	 * @param index
	 * @return
	 */
	private int leftChild(int index) {
		return index * 2 + 1;
	}

	/**
	 * 获取指定下标结点的左孩子的下标
	 * 
	 * @param index
	 * @return
	 */
	private int rightChild(int index) {
		return index * 2 + 2;
	}

	/**
	 * 交换两个下标上的元素
	 * 
	 * @param i
	 * @param j
	 */
	private void swap(int i, int j) {
		Elem temp = seqList.get(i);
		seqList.set(seqList.get(j), i);
		seqList.set(temp, j);
	}

	/**
	 * 上浮：当前结点不断跟父结点交换元素，直到不大于父结点元素或到达根结点
	 * 
	 * @param cursor 编号游标
	 */
	private void siftUp(int cursor) {
		// 根结点无父结点，下标为零就交换到根结点了
		while (cursor > 0 && seqList.get(cursor).compareTo(seqList.get(parent(cursor))) > 0) {
			swap(parent(cursor), cursor);
			cursor = parent(cursor);
		}
	}

	/**
	 * 添加元素
	 * 
	 * @param elem
	 */
	public void add(Elem elem) {
		// 先加到表尾，即堆的最大编号+1处
		seqList.addLast(elem);
		// 再上浮
		siftUp(seqList.getSize() - 1);
	}

	/**
	 * 查看最大元素即根结点元素
	 * 
	 * @return
	 */
	public Elem findMax() {
		if (isEmpty()) {
			throw new RuntimeException("堆为空");
		}
		return seqList.get(0);
	}

	/**
	 * 下沉：当前结点不断跟最大孩子交换元素，直到元素不小于任何孩子元素或无孩子
	 * 
	 * @param cursor 编号游标
	 */
	private void siftDown(int cursor) {
		while (true) {
			if (isEmpty()) {
				break;
			}
			Elem elem = seqList.get(cursor);
			// 没孩子，即左孩子下标越界，如此则右孩子下标一定也越界
			if (leftChild(cursor) >= getSize()) {
				break;
			}
			Elem leftElem = seqList.get(leftChild(cursor));
			// 一个孩子，那么必定是左孩子，且为叶子结点
			if (rightChild(cursor) >= getSize()) {
				if (elem.compareTo(leftElem) < 0) {
					swap(cursor, leftChild(cursor));
				}
				break;
			}
			Elem rightElem = seqList.get(rightChild(cursor));
			// 两个孩子，那么继续比较元素跟两个孩子的元素
			if (elem.compareTo(leftElem) >= 0 && elem.compareTo(rightElem) >= 0) {
				break;
			}
			int maxChild = leftElem.compareTo(rightElem) >= 0 ? leftChild(cursor) : rightChild(cursor);
			swap(cursor, maxChild);
			cursor = maxChild;
		}
	}

	/**
	 * 获取最大元素并调整堆结构
	 * 
	 * @return
	 */
	public Elem extractMax() {
		Elem max = findMax();
		// 用最大编号元素填充根结点，再删除该元素
		seqList.set(seqList.get(getSize() - 1), 0);
		seqList.removeLast();
		// 下沉
		siftDown(0);
		return max;
	}

	/**
	 * 获取最大元素，并替换以新元素，并调整堆结构
	 * 
	 * @param elem
	 * @return
	 */
	public Elem replace(Elem elem) {
		Elem max = findMax();
		seqList.set(elem, 0);
		siftDown(0);
		return max;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		// 用正则表达式替换
		res.append("SeqListMaxHeap [")
				.append(seqList.toString().replaceAll("DynamicSeqList capacity: \\d*, size: \\d* \\[", ""));
		return res.toString();
	}
}
