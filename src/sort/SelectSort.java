package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序
 * 
 * @author Van
 */
public class SelectSort {
	// 存放元素的数组
	private int[] array;

	public SelectSort(int[] array) {
		this.array = array;
	}

	public int[] getArray() {
		return array;
	}

	/**
	 * 排序
	 */
	public void sort() {
		// 每一轮最小元素的下标
		int min = 0;
		// 交换辅助变量
		int temp = 0;
		// 总共进行array.length-1次排序
		for (int i = 0; i < array.length - 1; i++) {
			System.out.print("第" + (i + 1) + "轮排序：");
			// 当前轮暂定首元素为最小元素
			min = i;
			// 找出当前轮最小元素
			for (int j = i + 1; j < array.length; j++) {
				// 只把下面这个<改成>就变成由高到低排
				if (array[j] < array[min]) {
					min = j;
				}
			}
			// 若最小元素不是首元素了，则交换这两个元素
			if (min != i) {
				temp = array[i];
				array[i] = array[min];
				array[min] = temp;
			}
			System.out.println(Arrays.toString(array));
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
		int[] array = { 2, 5, 1, 7, 4, 6, 8, 3 };
//		int[] array = new int[80000];
//		for (int i = 0; i < array.length; i++) {
//			array[i] = (int) (Math.random() * 8000000);
//		}
		SelectSort selectSort = new SelectSort(array);
//		selectSort.calculateTime();
		selectSort.sort();
//		System.out.println(Arrays.toString(selectSort.getArray()));
	}

}
