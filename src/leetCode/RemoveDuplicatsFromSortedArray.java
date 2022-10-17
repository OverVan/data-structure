package leetCode;

import java.util.Arrays;

/**
 * 26. 删除有序数组中的重复项
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicatsFromSortedArray {
	/**
	 * 保留下来的元素移动相应步数
	 * 
	 * @param nums
	 * @return 新长度
	 */
	public static int remove(int[] nums) {
		int step = 0;
		int j = 0;
		// 当i指向最后一个位置，说明最后一个元素是个新元素
		out: for (int i = 0; i < nums.length - 1; i = j) {
			j = i + 1;
			while (nums[j] == nums[i]) {
				step++;
				j++;
				// 最后一个元素是重复元素，j才越界，直接退出最外层循环
				if (j == nums.length) {
					break out;
				}
			}
			nums[j - step] = nums[j];
		}
		return nums.length - step;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		System.out.println(remove(nums));
		System.out.println(Arrays.toString(nums));
	}
}
