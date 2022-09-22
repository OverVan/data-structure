package seqList;

import java.util.Objects;

/**
 * 带泛型的变容顺序表
 * 
 * @param <Elem> 元素类型
 */
public class DynamicSeqList<Elem> {
	// 顺序存储数据的数组，动态指定元素类型
	private Elem[] data;
	private int size;

	/**
	 * 实例化指定初始容量的顺序表
	 * 
	 * @param capacity 容量-数组长度
	 */
	public DynamicSeqList(int capacity) {
		this.data = (Elem[]) new Object[capacity];
		this.size = 0;
	}

	/**
	 * 实例化默认初始长度的顺序表
	 */
	public DynamicSeqList() {
		this(10);
	}

	public DynamicSeqList(Elem[] elems) {
		data = (Elem[]) new Object[elems.length];
		for (int i = 0; i < elems.length; i++) {
			data[i] = elems[i];
		}
		size = elems.length;
	}

	/**
	 * 获取长度
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 获取容量
	 * 
	 * @return
	 */
	public int getCapacity() {
		return data.length;
	}

	/**
	 * 判断表是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 判断表是否为满
	 * 
	 * @return
	 */
	public boolean isFull() {
		return size == data.length;
	}

	/**
	 * 校验删除、修改或获取位置是否合法
	 * 
	 * @param index 位置
	 * @return
	 */
	private boolean validateIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("指定位置非法");
		}
		return true;
	}

	/**
	 * 向指定位置插入一个元素
	 * 
	 * @param elem  元素
	 * @param index 位置
	 */
	public void add(Elem elem, int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("插入位置非法");
		}
		if (isFull()) {
			// 表满则扩容到原来的两倍
			resize(2 * data.length);
		}
		for (int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		data[index] = elem;
		size++;
	}

	/**
	 * 向表尾插入一个元素
	 * 
	 * @param elem 元素
	 */
	public void addLast(Elem elem) {
		add(elem, size);
	}

	/**
	 * 向表头插入一个元素
	 * 
	 * @param elem 元素
	 */
	public void addFirst(Elem elem) {
		add(elem, 0);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(String.format("DynamicSeqList capacity: %d, size: %d [", data.length, size));
		for (int i = 0; i < size; i++) {
			res.append(data[i]);
			if (i != size - 1) {
				res.append(", ");
			}
		}
		res.append("]");
		return res.toString();
	}

	/**
	 * 获取指定位置上的元素
	 * 
	 * @param index 位置
	 * @return
	 */
	public Elem get(int index) {
		validateIndex(index);
		return data[index];
	}

	/**
	 * 获取尾元素
	 * 
	 * @return
	 */
	public Elem getLast() {
		return get(size - 1);
	}

	/**
	 * 获取首元素
	 * 
	 * @return
	 */
	public Elem getFirst() {
		return get(0);
	}

	/**
	 * 修改指定位置上的元素
	 * 
	 * @param elem  元素
	 * @param index 位置
	 */
	public void set(Elem elem, int index) {
		validateIndex(index);
		data[index] = elem;
	}

	/**
	 * 判断表是否包含指定元素
	 * 
	 * @param elem 元素
	 * @return
	 */
	public boolean contains(Elem elem) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(elem)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取指定元素首次出现的位置
	 * 
	 * @param elem 元素
	 * @return
	 */
	public int find(Elem elem) {
		for (int i = 0; i < size; i++) {
			if (Objects.equals(data[i], elem)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 删除并获取指定位置上的元素
	 * 
	 * @param index 位置
	 * @return
	 */
	public Elem remove(int index) {
		validateIndex(index);
		Elem res = data[index];
		for (int i = index + 1; i < size; i++) {
			data[i - 1] = data[i];
		}
		size--;
		// 置不置null无所谓 data[size] = null;
		// 删除到一定程度，缩容，避免空间浪费
//		if (size == data.length / 2) {
//			resize(data.length / 2);
//		}
		// 懒处理：规避振荡复杂度，总是预留一半空间 数组长度至少为1，空数组无法扩容
		if (size == data.length / 4 && data.length / 2 != 0) {
			resize(data.length / 2);
		}
		return res;
	}

	/**
	 * 删除首元素
	 * 
	 * @return
	 */
	public Elem removeFirst() {
		return remove(0);
	}

	/**
	 * 删除尾元素
	 * 
	 * @return
	 */
	public Elem removeLast() {
		return remove(size - 1);
	}

	/**
	 * 删除所有指定元素 O(n)
	 * 
	 * @param elem 元素
	 */
	public void removeElem(Elem elem) {
		// 边找边移，找到一个那么它到下一个目标元素之间的元素的移动步数加1
		int step = 0;
		for (int i = 0; i < size; i++) {
			if (Objects.equals(data[i], elem)) {
				step++;
				continue;
			}
			// 有关元素只需移一次
			data[i - step] = data[i];
		}
		// 找到了step个
		size -= step;
		// 视情况缩容
		if (size == data.length / 4 && data.length / 2 != 0) {
			resize(data.length / 2);
		}
	}

	/**
	 * 变容
	 * 
	 * @param newCapacity 新容量
	 */
	private void resize(int newCapacity) {
		Elem[] newData = (Elem[]) new Object[newCapacity];
		// 元素迁移
		for (int i = 0; i < size; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}
}
