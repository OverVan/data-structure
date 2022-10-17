package search;

/**
 * 插值查找-二分查找的优化
 */
public class InsertValueSearch {
	/**
	 * 查找一个指定元素
	 * 
	 * @param elems 单调递增数组
	 * @param elem  元素
	 * @param left  闭区间左下标
	 * @param right 闭区间右下标
	 * @return 下标
	 */
	public int search(int[] array, int elem, int left, int right) {
		// 防止下标越界，仅第一次调用有意义
		if (left <= right && elem >= array[0] && elem <= array[array.length - 1]) {
			int mid = left + (right - left) * (elem - array[left]) / (array[right] - array[left]);
			if (elem > array[mid]) {
				return search(array, elem, mid + 1, right);
			} else if (elem < array[mid]) {
				return search(array, elem, left, mid - 1);
			} else {
				return mid;
			}
		} else {
			return -1;
		}
	}
}
