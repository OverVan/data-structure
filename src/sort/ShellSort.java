package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 希尔排序（插入排序的改进版）
 * 
 * @author Van
 */
public class ShellSort {
	// 存放元素的数组
	private int[] array;

	public ShellSort(int[] array) {
		this.array = array;
	}

	public int[] getArray() {
		return array;
	}

	/**
	 * 排序（自己写的效率不高，还借了空间）
	 */
	public void sort() {
		int half = 2;
		Map<Integer, Integer> map = new HashMap<>();
		// 最后一次循环执行于step为1时，再往后就是0（0.5下取整）了
		while (array.length / half > 0) {
			// 分组个数或步长
			int step = array.length / half;
			// 每组元素个数
			int count = half;
			// 以步长为数目的若干个子数组
			int[] group = new int[count];
			InsertSort insertSort = new InsertSort(group);
			// 填充各个子数组
			for (int i = 0; i < step; i++) {
				// 逐步长将元素填入当前子数组
				for (int j = i, k = 0; k < count; j += step, k++) {
					group[k] = array[j];
				}
				// 对当前子数组进行插入排序
				insertSort.sort();
				// 借助map将排好序的子数组按原始下标回填到原数组中
				for (int j = i, k = 0; k < count; j += step, k++) {
					map.put(j, group[k]);
				}
				for (int key : map.keySet()) {
					array[key] = map.get(key);
				}

			}
			// 缩减步长，准备下一次分组
			half *= 2;
		}
	}

	/**
	 * 改进版排序
	 */
	public void enhancedSort() {
		// 待插值
		int insertValue = 0;
		// 轮数
		int circle = 0;
		// 最后一次循环执行于step为1时，再往后就是0（0.5下取整）了
		for (int half = 2; array.length / half > 0; half *= 2) {
			// 分组个数或步长
			int step = array.length / half;
			// 下面这坨循环及其巧妙，隐性地填充并插排子数组
			for (int i = step; i < array.length; i++) {
				int j = i;
				insertValue = array[j];
				// 插排变式-步长由1变成其他数
				while (j - step >= 0 && insertValue < array[j - step]) {
					array[j] = array[j - step];
					j -= step;
				}
				// 当insertValue>array[j-step]或j-step为负，就在j-step+step下标处插入
				array[j] = insertValue;
			}
			circle++;
			System.out.println("第" + circle + "轮排序：" + Arrays.toString(array));
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 8, 7, 6, 5, 4, 3, 2, 1 };
		ShellSort shellSort = new ShellSort(array);
		shellSort.enhancedSort();
	}
}
