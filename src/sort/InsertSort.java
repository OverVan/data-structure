package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序
 * 
 * @author Van
 */
public class InsertSort {
	// 存放元素的数组（也可用单链表做头插）
	private int[] array;

	public InsertSort(int[] array) {
		this.array = array;
	}

	/**
	 * 排序
	 */
	public void sort() {
		// 待插入值
		int insertValue = 0;
		for (int i = 1; i < array.length; i++) {
			System.out.printf("第%d轮排序：", i);
			// 指定待插入值
			insertValue = array[i];
			// 将待插入值与前面的元素一一比较，若后者更大则后者后移一位
			int j = i - 1;
			while (j >= 0 && insertValue < array[j]) {
				array[j + 1] = array[j];
				j--;
			}
			// 找到了比待插值小的元素就在该元素后插入，或前面元素都更大就在下标0处插入
			array[j + 1] = insertValue;
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
		int[] array = { 2, 6, 8, 3, 7, 5, 4, 1 };
		InsertSort insertSort = new InsertSort(array);
		insertSort.sort();
	}
}
