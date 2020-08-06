package sort;

import java.util.Arrays;

/**
 * 归并排序
 * 
 * @author Van
 */
public class MergeSort {
	// 存放元素的数组
	private int[] array;

	public MergeSort(int[] array) {
		this.array = array;
	}

	/**
	 * 合并排序两个分段
	 * 
	 * @param leftBorder  左段左端下标
	 * @param rightBorder 右段右端下标
	 */
	public void merge(int leftBorder, int rightBorder) {
		// 中部边界
		int mid = (leftBorder + rightBorder) / 2;
		// 双游标
		int left = leftBorder;
		int right = mid + 1;
		// 中间数组
		int[] tempArray = new int[rightBorder - leftBorder + 1];
		int k = 0;
		// 归并的关键循环，任一方游标出了边界就退出
		while (left <= mid && right <= rightBorder) {
			if (array[left] < array[right]) {
				tempArray[k] = array[left];
				k++;
				left++;
			} else {
				tempArray[k] = array[right];
				k++;
				right++;
			}
		}
		// 必然有一分段先行全部进列表，那么就直接加入另一分段剩余元素
		if (left == mid + 1) {
			for (int i = right; i < rightBorder + 1; i++, k++) {
				tempArray[k] = array[i];
			}
		} else {
			for (int i = left; i < mid + 1; i++, k++) {
				tempArray[k] = array[i];
			}
		}
		// 将中间列表中的元素依次原位返回到数组
		for (int i = 0, j = leftBorder; i < tempArray.length; i++, j++) {
			array[j] = tempArray[i];
		}
	}

	/**
	 * 排序
	 * 
	 * @param leftBorder  左段左端下标
	 * @param rightBorder 右段右端下标
	 */
	public void sort(int leftBorder, int rightBorder) {
		// 只有分到2个元素或1个元素才不再往下分
		if (rightBorder - leftBorder > 1) {
			// 中间边界
			int mid = (leftBorder + rightBorder) / 2;
			// 排左边
			sort(leftBorder, mid);
			// 排右边
			sort(mid + 1, rightBorder);
		}
		// 合并排序本层的两段
		merge(leftBorder, rightBorder);
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 2, 7, 4, 1, 5, 3, 8, 6, 0, 10, 88, 14, 77, 65, 22, 0 };
		MergeSort mergeSort = new MergeSort(array);
		mergeSort.sort(0, 15);
		System.out.println(Arrays.toString(array));
	}
}
