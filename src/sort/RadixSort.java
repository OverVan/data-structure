package sort;

import java.util.Arrays;

/**
 * 基数排序
 * 
 * @author Van
 */
public class RadixSort {
	private int[] array;// 存放元素的数组
	private int[][] buckets;// 桶
	private int[] elementCount;// 每个桶的已存元素个数

	public RadixSort(int[] array) {
		this.array = array;
		// 10个桶
		this.buckets = new int[10][array.length];
		this.elementCount = new int[10];
	}

	/**
	 * 得到数组元素的最大位数
	 * 
	 * @return
	 */
	public int getMaxDigit() {
		// 找出最大元素
		int maxElement = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > maxElement) {
				maxElement = array[i];
			}
		}
		// 最大元素转字符串后得其长度即最大位数
		return String.valueOf(maxElement).length();
	}

	/**
	 * 排序
	 */
	public void sort() {
		// 从个位到最高位，针对每一位作排序
		for (int digit = 0; digit < getMaxDigit(); digit++) {
			// 针对全体元素的当前位作排序
			for (int i = 0; i < array.length; i++) {
				// 当前元素在当前位的数值
				int number = (array[i] / (int) Math.pow(10, digit)) % 10;
				// 将数值入桶
				buckets[number][elementCount[number]] = array[i];
				// 将该桶元素数加1
				elementCount[number]++;
			}
			// 从各桶中取元素，重新装填数组
			int index = 0;
			// 迭代所有的桶
			for (int i = 0; i < elementCount.length; i++) {
				// 当前桶非空才取元素
				if (elementCount[i] != 0) {
					for (int j = 0; j < elementCount[i]; j++) {
						array[index] = buckets[i][j];
						index++;
					}
					// 当前桶元素取完了要逻辑上清零
					elementCount[i] = 0;
				}
			}
			System.out.print("第" + (digit + 1) + "轮排序：" + Arrays.toString(array) + "\n");
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 11, 2232, 24, 1, 55, 674, 29, 33, 45, 486 };
//		int[] array = { 8, 7, 6, 5, 4, 3, 2, 1 };
		RadixSort radixSort = new RadixSort(array);
		radixSort.sort();
	}
}
