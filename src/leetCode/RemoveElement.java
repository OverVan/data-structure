package leetCode;

import java.util.Arrays;

/**
 * 27. 移除元素 https://leetcode.cn/problems/remove-element/
 */
public class RemoveElement {
	public static int remove(int[] nums, int val) {
		int step = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == val) {
				step++;
				continue;
			}
			nums[i - step] = nums[i];
		}
		return nums.length - step;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 3, 2, 2, 3 };
		System.out.println(remove(nums, 3));
		System.out.println(Arrays.toString(nums));
	}
}
