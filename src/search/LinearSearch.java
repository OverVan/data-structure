package search;

import java.util.Objects;

import seqList.DynamicSeqList;

/**
 * 线性查找
 * 
 * @param <Elem> 元素类型
 */
public class LinearSearch<Elem> {

	/**
	 * 从指定数组中查找指定元素的第一个下标
	 * 
	 * @param elems 数组
	 * @param elem  元素
	 * @return 下标
	 */
	public int searchFirst(Elem[] elems, Elem elem) {
		for (int i = 0; i < elems.length; i++) {
			if (Objects.equals(elem, elems[i])) {
				return i;
			}
		}
		// 找不到返回-1
		return -1;
	}

	/**
	 * 从指定数组中查找指定元素的所有下标
	 * 
	 * @param elems 数组
	 * @param elem  元素
	 * @return 下标顺序表
	 */
	public DynamicSeqList<Integer> searchAll(Elem[] elems, int elem) {
		DynamicSeqList<Integer> indices = new DynamicSeqList<>(elems.length);
		for (int i = 0; i < elems.length; i++) {
			if (Objects.equals(elem, elems[i])) {
				indices.addLast(i);
			}
		}
		return indices;
	}
}
