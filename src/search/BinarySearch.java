package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 
 * @author Van
 */
public class BinarySearch {
	private int[] array;// 有序的元素数组
	private List<Integer> indexList;// 存放找到的元素的下标的列表

	public BinarySearch(int[] array) {
		this.array = array;
		this.indexList = new ArrayList<Integer>();
	}

	public List<Integer> getIndexList() {
		return indexList;
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
		if (left <= right) {
			// 中间元素下标
			int mid = (left + right) / 2;
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
	 * 查找全部目标元素
	 * 
	 * @param target 目标元素
	 * @param left   当前分段的左端下标
	 * @param right  当前分段的右端下标
	 */
	public void searchAll(int target, int left, int right) {
		if (left <= right) {// 一旦left>right，就表明目标元素不在此序列中
			// 中间元素下标
			int mid = (left + right) / 2;
			if (target > array[mid]) {// 往右查找
				searchAll(target, mid + 1, right);
			} else if (target < array[mid]) {// 往左查找
				searchAll(target, left, mid - 1);
			} else {
				// 将找到的元素的下标存入列表
				indexList.add(mid);
				// 目标元素附近可能有相同元素，应左往右查找
				searchAll(target, mid + 1, right);
				searchAll(target, left, mid - 1);
			}
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 1, 1, 3, 5, 7, 9, 9, 9, 11 };
		BinarySearch binarySearch = new BinarySearch(array);
		System.out.println(binarySearch.searchOne(11, 0, 8));
		binarySearch.searchAll(1, 0, 8);
		System.out.println(binarySearch.getIndexList());
	}
}
