package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 * 
 * @author Van
 */
public class BubbleSort {
	// 存放元素的数组
	private int[] array;

	public BubbleSort(int[] array) {
		this.array = array;
	}

	public int[] getArray() {
		return array;
	}

	/**
	 * 排序
	 */
	public void sort() {
		// 交换辅助变量
		int temp = 0;
		// 最多进行array.length-1趟排序
		for (int i = 0; i < array.length - 1; i++) {
			System.out.printf("第%d趟排序：", i + 1);
			// 每一趟排序设1个标志量
			boolean flag = true;// 也可用计数器取代布尔值
			// 每一趟作array.length-i-1次交换
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j] > array[j + 1]) {
					// 前后交换
					temp = array[j + 1];
					array[j + 1] = array[j];
					array[j] = temp;
					// 只要交换就将标志量置为false
					flag = false;
				}
			}
			// 打印本趟排序
			System.out.println(Arrays.toString(array));
			// 若本趟一次交换都没有发生则说明全部排好了，直接返回
			if (flag) {
				return;
			}
		}
	}

	/**
	 * 定量直观测时间
	 */
	public void calculateTime() {
		Date beforeDate = new Date();
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
		String before = simpleDateFormat1.format(beforeDate);
		System.out.println("排序之前的时间：" + before);
		sort();
		Date afterDate = new Date();
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
		String after = simpleDateFormat2.format(afterDate);
		System.out.println("排序之后的时间：" + after);
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 2, 1, 3, 7, 6, 5, 4, 8 };
		BubbleSort bubbleSort = new BubbleSort(array);
//		bubbleSort.sort();
		bubbleSort.calculateTime();
	}

}
