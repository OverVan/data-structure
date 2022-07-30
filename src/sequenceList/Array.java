package sequenceList;

/**
 * 用二次封装的数组实现的顺序表
 * 
 * @author Van
 *
 */
public class Array {
	// 数据
	private int[] data;
	// 实际数据量
	private int size;

	public Array(int capacity) {
		this.data = new int[capacity];
		this.size = 0;
	}

	public Array() {
		// 设定默认最大数据量
		this(10);
	}

	/**
	 * 获得实际数据量
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 获得最大数据量
	 * 
	 * @return
	 */
	public int getCapacity() {
		return data.length;
	}

	/**
	 * 判断顺序表是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 判断顺序表是否为满
	 * 
	 * @return
	 */
	public boolean isFull() {
		return size == data.length;
	}

	/**
	 * 向表尾插入一个元素
	 * 
	 * @param elem
	 */
	public void addLast(int elem) {
		if (isFull()) {
			throw new IllegalArgumentException("顺序表已满，不能插入");
		} else {
			data[size] = elem;
			size++;
		}
		// 复用add方法：add(elem, size);
	}

	/**
	 * 向指定位置插入一个元素
	 * 
	 * @param elem
	 * @param index
	 */
	public void add(int elem, int index) {
		if (isFull()) {
			throw new IllegalArgumentException("顺序表已满，不能插入");
		}
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("索引值非法");
		}
		for (int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		data[index] = elem;
		size++;
	}

	/**
	 * 向表头插入一个元素
	 * 
	 * @param elem
	 */
	public void addFirst(int elem, int index) {
		add(elem, 0);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(String.format("A sequence list. capacity: %d, size: %d\n", data.length, size));
		res.append("data: ");
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
	 * 查询某位置元素
	 * 
	 * @param index
	 * @return
	 */
	public int get(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("索引值非法");
		} else {
			return data[index];
		}
	}

	/**
	 * 修改某位置元素
	 * 
	 * @param elem
	 * @param index
	 */
	public void set(int elem, int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("索引值非法");
		} else {
			data[index] = elem;
		}
	}

	/**
	 * 表是否包含某元素
	 * 
	 * @param elem
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
	 * 获得某元素首次出现的位置
	 * 
	 * @param elem
	 * @return
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
	 * 删除指定位置的元素
	 * 
	 * @param index
	 * @return
	 */
	public int remove(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("索引值非法");
		}
		int res = data[index];
		for (int i = index + 1; i < size; i++) {
			data[i - 1] = data[i];
		}
		size--;
		return res;
	}

	/**
	 * 删除表头元素
	 * 
	 * @return
	 */
	public int removeFirst() {
		return remove(0);
	}

	/**
	 * 删除表尾元素
	 * 
	 * @return
	 */
	public int removeLast() {
		return remove(size - 1);
	}

	/**
	 * 删除指定值的元素
	 * 
	 * @return
	 */
	public void removeElem(int elem) {
		int index = find(elem);
		if (index != -1) {
			remove(index);
		} else {
			throw new IllegalArgumentException("此元素不存在");
		}
	}
}
