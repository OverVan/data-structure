package leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和 https://leetcode.cn/problems/two-sum/
 */
public class TwoSum {
	/**
	 * O(n2)
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
		int[] indices = new int[2];
		indices[0] = -1;
		indices[1] = -1;
		// 避免重复匹配
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					indices[0] = i;
					indices[1] = j;
				}
			}
		}
		return indices;
	}

	/**
	 * O(logn!)
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSumWithMap(int[] nums, int target) {
		int[] indices = new int[2];
		indices[0] = -1;
		indices[1] = -1;
		// 引入映射，元素作键，下标作值
		Map<Integer, Integer> map = new HashMap<>();
		// 同样应避免重复匹配
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				indices[0] = i;
				indices[1] = map.get(target - nums[i]);
			} else {
				map.put(nums[i], i);
			}
		}
		return indices;
	}

	public static void main(String[] args) {
		int[] elems = { 2, 2, 11, 15 };
		System.out.println(Arrays.toString(twoSum(elems, 4)));
		System.out.println(Arrays.toString(twoSumWithMap(elems, 4)));
	}
}
