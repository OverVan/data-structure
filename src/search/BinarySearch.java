package search;

/**
 * 二份查找或折半查找
 * 
 * @param <Elem> 元素类型，必须可比，不可取null
 */
public class BinarySearch<Elem extends Comparable<Elem>> {
	/**
	 * 查找一个指定元素，递归
	 * 
	 * @param array 单调递增数组
	 * @param left  闭区间左下标
	 * @param right 闭区间右下标
	 * @param elem  元素
	 * @return 下标
	 */
	public int recurSearch(Elem[] array, int left, int right, Elem elem) {
		if (left > right) {
			return -1;
		}
		// 一个不太重要的点：避免整型溢出 int middle = (left + right) / 2
		int middle = left + (right - left) / 2;
		// 左子数组递归
		if (elem.compareTo(array[middle]) < 0) {
			return recurSearch(array, left, middle - 1, elem);
		}
		// 右子数组递归
		if (elem.compareTo(array[middle]) > 0) {
			return recurSearch(array, middle + 1, right, elem);
		}
		return middle;
	}

	/**
	 * 查找一个指定元素，循环
	 * 
	 * @param array 单调递增数组
	 * @param left  闭区间左下标
	 * @param right 闭区间右下标
	 * @param elem  元素
	 * @return 下标
	 */
	public int loopSearch(Elem[] array, int left, int right, Elem elem) {
		int middle = 0;
		// 一旦left>right，就说明目标元素不存在
		while (left <= right) {
			middle = (left + right) / 2;
			if (elem.compareTo(array[middle]) < 0) {
				right = middle - 1;
				continue;
			}
			if (elem.compareTo(array[middle]) > 0) {
				left = middle + 1;
				continue;
			}
			return middle;
		}
		return -1;
	}
}
