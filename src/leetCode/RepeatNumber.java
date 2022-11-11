package leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 */
public class RepeatNumber {
	/**
	 * 快速排序，然后两两比较，只要有相等的就返回 时间上O(nlogn)，空间上O(1)
	 * 
	 * @param nums
	 * @return
	 */
	public static int sortAndCompare(int[] nums) {
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == nums[i + 1]) {
				return nums[i];
			}
		}
		return nums.length;
	}

	/**
	 * 借助映射 时间上O(n)，空间上O(n)
	 * 
	 * @param nums
	 */
	public static int useMap(int[] nums) {
		Map<Integer, Object> map = new HashMap<>(nums.length);
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				return nums[i];
			} else {
				map.put(nums[i], null);
			}
		}
		return nums.length;
	}

	/**
	 * 抽屉（鸽巢）原理、原位哈希 时间上O(n)，空间上O(1)
	 */
	public static int drawer(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			// 若当前元素若与下标不相等，则与元素下标上的元素交换，直到相等
			while (nums[i] != i) {
				// 交换时发现元素重复
				if (nums[i] == nums[nums[i]]) {
					return nums[i];
				} else {
					int temp = nums[i];
					nums[i] = nums[nums[i]];
					nums[temp] = temp;
				}
			}
		}
		return nums.length;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 2, 3, 1, 0, 2, 5, 3 };
		System.out.println(sortAndCompare(nums));
		System.out.println(useMap(nums));
		System.out.println(drawer(nums));
	}
}
