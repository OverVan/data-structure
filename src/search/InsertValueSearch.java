package search;

/**
 * 插值查找（二分查找的优化）
 * 
 * @author Van
 */
public class InsertValueSearch {
	private int[] array;// 有序且元素尽量连续的元素数组

	public InsertValueSearch(int[] array) {
		this.array = array;
	}

	/**
	 * 查找一个目标元素
	 * 
	 * @param target 目标元素
	 * @param left   当前分段的左端下标
	 * @param right  当前分段的右端下标
	 * @return 目标元素的下标
	 */
	public int searchOne(int target, int left, int right) {
		// 注意加范围约束，防止下标越界
		if (left <= right && target >= array[0] && target <= array[array.length - 1]) {
			// 目标元素的理想下标。除数为array[right]-array[left]，注意元素全部相等的情况
			int mid = left + (right - left) * (target - array[left]) / (array[right] - array[left]);
			if (target > array[mid]) {// 往右查找
				return searchOne(target, mid + 1, right);
			} else if (target < array[mid]) {// 往左查找
				return searchOne(target, left, mid - 1);
			} else {
				return mid;
			}
		} else {// 一旦left>right，就表明目标元素不在此序列中
			return -1;
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 就用连续数组
		int[] array = new int[100];
		for (int i = 0; i < 100; i++) {
			array[i] = i + 1;
		}
		InsertValueSearch insertValueSearch = new InsertValueSearch(array);
		System.out.println(insertValueSearch.searchOne(70, 0, 99));
	}
}
