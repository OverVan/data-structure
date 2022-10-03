package leetCode;

import java.util.Arrays;

/**
 * 977. 有序数组的平方 https://leetcode.cn/problems/squares-of-a-sorted-array/
 */
public class SquaresOfSortedArray {
	/**
	 * 遍历求二次幂，再用快速排序 O(nlogn)
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] squareAndSort(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			nums[i] = (int) Math.pow(nums[i], 2);
		}
		Arrays.sort(nums);
		return nums;
	}

	/**
	 * 双指针：若左元素二次幂大，倒序插入，指针右移，否则右元素倒序插入，指针左移，重复直到相遇 O(n)
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] doublePointer(int[] nums) {
		int[] temp = new int[nums.length];
		int cursor = temp.length - 1;
		int low = 0;
		int high = nums.length - 1;
		while (low < high) {
			if (Math.pow(nums[low], 2) >= Math.pow(nums[high], 2)) {
				temp[cursor--] = (int) Math.pow(nums[low++], 2);
			} else {
				temp[cursor--] = (int) Math.pow(nums[high--], 2);
			}
		}
		// 相遇把当前元素插入首位
		temp[cursor] = (int) Math.pow(nums[low], 2);
		return temp;
	}

	public static void main(String[] args) {
		int[] nums = { -4, -1, 0, 3, 10 };
//		System.out.println(Arrays.toString(squareAndSort(nums)));
		System.out.println(Arrays.toString(doublePointer(nums)));
	}
}
