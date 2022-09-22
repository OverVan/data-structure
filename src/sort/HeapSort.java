package sort;

import java.util.Arrays;

/**
 * 堆排序即heapify-将数组转化为堆
 * 
 * @author Van
 */
public class HeapSort {
	// 元素数组
	private int[] array;

	public HeapSort(int[] array) {
		this.array = array;
	}

	/**
	 * 排序
	 */
	public void sort() {
		int temp = 0;
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			adjustHeap(i, array.length);
		}
		for (int j = array.length - 1; j > 0; j--) {
			temp = array[j];
			array[j] = array[0];
			array[0] = temp;
			adjustHeap(0, j);
		}
	}

	/**
	 * 调整为一个大顶堆
	 * 
	 * @param i      当前结点的下标
	 * @param length 长度
	 */
	public void adjustHeap(int i, int length) {

		int temp = array[i];
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			if (k + 1 < length && array[k] < array[k + 1]) {
				k++;
			}
			if (array[k] > temp) {
				array[i] = array[k];
				i = k;
			} else {
				break;
			}
		}
		array[i] = temp;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 4, 6, 8, 5, 9, -1, 56, 89, 10 };
		HeapSort heapSort = new HeapSort(array);
		heapSort.sort();
		System.out.println(Arrays.toString(array));
	}

}
