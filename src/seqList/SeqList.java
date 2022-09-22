package seqList;

/**
 * 顺序存储的线性表-顺序表 主要优点是随机访问
 */
public class SeqList {
	// 顺序存储数据的数组，固定元素类型为int
	private int[] data;
	// 长度-有效元素个数
	private int size;

	/**
	 * 实例化指定容量的顺序表
	 * 
	 * @param capacity 容量
	 */
	public SeqList(int capacity) {
		// 容量即数组长度
		this.data = new int[capacity];
		this.size = 0;
	}

	/**
	 * 实例化默认长度为10的顺序表
	 */
	public SeqList() {
		this(10);
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
	 * 校验插入位置是否合法
	 * 
	 * @param index 位置
	 * @return
	 */
	private boolean validateInsert(int index) {
		if (isFull()) {
			throw new IllegalArgumentException("表已满，不能插入");
		}
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("插入位置非法");
		}
		return true;
	}

	/**
	 * 校验删除、修改或获取位置是否合法
	 * 
	 * @param index 位置
	 * @return
	 */
	private boolean validateOther(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("删除、修改或查询位置非法");
		}
		return true;
	}

	/**
	 * 向指定位置插入一个元素
	 * 
	 * @param elem  元素
	 * @param index 位置
	 */
	public void add(int elem, int index) {
		validateInsert(index);
		// 从后往前，元素逐一后移一位
		for (int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		// 先移后插
		data[index] = elem;
		size++;
	}

	/**
	 * 向表尾插入一个元素
	 * 
	 * @param elem 元素
	 */
	public void addLast(int elem) {
		// 复用add方法
		add(elem, size);
	}

	/**
	 * 向表头插入一个元素
	 * 
	 * @param elem 元素
	 */
	public void addFirst(int elem) {
		add(elem, 0);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(String.format("sequence list, capacity: %d, size: %d\n", data.length, size));
		res.append("SeqList: ");
		res.append("[");
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
	 * 查询指定位置上的元素
	 * 
	 * @param index 位置
	 * @return 元素
	 */
	public int get(int index) {
		validateOther(index);
		return data[index];
	}

	/**
	 * 修改指定位置上的元素
	 * 
	 * @param elem  元素
	 * @param index 位置
	 */
	public void set(int elem, int index) {
		validateOther(index);
		data[index] = elem;
	}

	/**
	 * 判断表是否包含指定元素
	 * 
	 * @param elem 元素
	 * @return
	 */
	public boolean contains(int elem) {
		for (int i = 0; i < size; i++) {
			if (data[i] == elem) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获得指定元素首次出现的位置
	 * 
	 * @param elem 元素
	 * @return 位置
	 */
	public int find(int elem) {
		for (int i = 0; i < size; i++) {
			if (data[i] == elem) {
				return i;
			}
		}
		// 没找到返回-1
		return -1;
	}

	/**
	 * 删除并获取指定位置上的元素
	 * 
	 * @param index 位置
	 * @return 元素
	 */
	public int remove(int index) {
		validateOther(index);
		// 先取后移
		int res = data[index];
		// 从前往后，元素逐一前移一位
		for (int i = index + 1; i < size; i++) {
			data[i - 1] = data[i];
		}
		size--;
		return res;
	}

	/**
	 * 删除首元素
	 * 
	 * @return
	 */
	public int removeFirst() {
		return remove(0);
	}

	/**
	 * 删除尾元素
	 * 
	 * @return
	 */
	public int removeLast() {
		return remove(size - 1);
	}

	/**
	 * 删除所有指定元素 循环 O(n^2)
	 * 
	 * @param elem 元素
	 */
	@Deprecated
	public void removeEvery(int elem) {
		int i = 0;
		while (i < size) {
			if (data[i] == elem) {
				for (int j = i + 1; j < size; j++) {
					data[j - 1] = data[j];
				}
				size--;
			} else {
				i++;
			}
		}
	}

	/**
	 * 删除所有指定元素 O(n)
	 * 
	 * @param elem 元素
	 */
	public void removeAll(int elem) {
		int step = 0;
		for (int i = 0; i < size; i++) {
			if (data[i] == elem) {
				step++;
				continue;
			}
			data[i - step] = data[i];
		}
		size -= step;
	}
}
