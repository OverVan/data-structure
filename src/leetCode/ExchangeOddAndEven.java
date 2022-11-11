package leetCode;

import java.util.Arrays;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 */
public class ExchangeOddAndEven {
	/**
	 * 使用新数组 时间O(n)，空间O(n)
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] useArray(int[] nums) {
		int[] exchange = new int[nums.length];
		// 奇数从左边插，偶数从右边插
		for (int i = 0, low = 0, high = nums.length - 1; i < nums.length; i++) {
			if (nums[i] % 2 == 0) {
				exchange[high--] = nums[i];
			} else {
				exchange[low++] = nums[i];
			}
		}
		return exchange;
	}

	/**
	 * 同向双指针，元素交换 时间O(n2)，空间O(1)
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] concurrentPointer(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] % 2 == 0) {
				for (int j = i + 1; j < nums.length; j++) {
					if (nums[j] % 2 != 0) {
						int temp = nums[i];
						nums[i] = nums[j];
						nums[j] = temp;
					}
				}
			}
		}
		return nums;
	}

	/**
	 * 对向双指针，元素交换 时间O(n)，空间O(1)
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] oppositePointer(int[] nums) {
		if (nums.length == 0) {
			return nums;
		}
		int low = 0;
		int high = nums.length - 1;
		int pivot = nums[low];
		while (low < high) {
			while (nums[high] % 2 == 0 && low < high) {
				high--;
			}
			nums[low] = nums[high];
			while (nums[low] % 2 != 0 && low < high) {
				low++;
			}
			nums[high] = nums[low];
		}
		nums[low] = pivot;
		return nums;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 2, 4, 5, 3, 1 };
		System.out.println(Arrays.toString(useArray(nums)));
		System.out.println(Arrays.toString(concurrentPointer(nums)));
		System.out.println(Arrays.toString(oppositePointer(nums)));
	}
}
