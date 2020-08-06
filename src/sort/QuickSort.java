package sort;

import java.util.Arrays;

/**
 * 快速排序
 * 
 * @author Van
 */
public class QuickSort {
	// 存放元素的数组
	private int[] array;

	public QuickSort(int[] array) {
		this.array = array;
	}

	/**
	 * 排序
	 * 
	 * @param left  数组某段的左端下标
	 * @param right 数组某段的右端下标
	 * @param index 数组某段的枢轴下标
	 */
	public void sort(int left, int right, int index) {
		// 若左端下标等于右端下标，说明当前段仅一个元素，则不必排序，直接返回
		if (left == right) {
			return;
		}
		// 左游标
		int low = left;
		// 右游标
		int high = right;
		// 枢轴元素（这里设当前段起始元素为枢轴）
		int pivot = array[index];
		// 关键循环
		while (low < high) {
			while (array[high] >= pivot && low < high) {
				high--;
			}
			array[low] = array[high];
			while (array[low] <= pivot && low < high) {
				low++;
			}
			array[high] = array[low];
		}
		// center=low=high
		// 将枢轴放入中间位置，然后其左边元素均小，右边元素均大
		int center = low;
		array[center] = pivot;
		System.out.println(Arrays.toString(array));
		// 枢轴左边有元素的话，递归调用快排
		if (left < low) {
			sort(left, center - 1, left);
		}
		// 枢轴右边有元素的话，递归调用快排
		if (right > high) {
			sort(center + 1, right, center + 1);
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 2, 0, 8, 5, 4, 7, 3, 6, 9, 1 };
		QuickSort quickSort = new QuickSort(array);
		quickSort.sort(0, array.length - 1, 0);
		System.out.println(Arrays.toString(array));
	}
}
