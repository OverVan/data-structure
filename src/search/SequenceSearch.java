package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 线性查找（从前往后一个个找）
 * 
 * @author Van
 */
public class SequenceSearch {
	// 元素数组
	private int[] array;

	public SequenceSearch(int[] array) {
		this.array = array;
	}

	/**
	 * 找第一个元素目标元素
	 * 
	 * @param target 目标元素
	 * @return 目标元素的下标
	 */
	public int searchFirst(int target) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == target) {
				return i;
			}
		}
		// 找不到返回-1
		return -1;
	}

	/**
	 * 找出所有的目标元素
	 * 
	 * @param target 目标元素
	 * @return 目标元素下标组成的列表
	 */
	public List<Integer> searchAll(int target) {
		List<Integer> indexList = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			if (array[i] == target) {
				indexList.add(i);
			}
		}
		// 找不到返回的就是一个空列表
		return indexList;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 7, 2, 5, 5, 3, 9, 8, 2, 2, 4 };
		SequenceSearch sequenceSearch = new SequenceSearch(array);
		System.out.println(sequenceSearch.searchFirst(2));
		System.out.println(sequenceSearch.searchAll(2));
	}
}
