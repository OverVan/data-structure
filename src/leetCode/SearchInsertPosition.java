package leetCode;

/**
 * 35. 搜索插入位置 https://leetcode.cn/problems/search-insert-position/
 */
public class SearchInsertPosition {
	/**
	 * 二分查找的变体
	 * 
	 * @param nums   非递减数组
	 * @param target 目标元素
	 * @return 匹配位置或插入位置
	 */
	public static int loop(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		int mid = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			if (nums[mid] == target) {
				return mid;
			}
			if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		if (target < nums[mid]) {
			return mid;
		}
		return mid + 1;
	}

	private static int recur(int[] nums, int left, int right, int target) {
		int mid = (left + right) / 2;
		if (left > right) {
			if (target < nums[mid]) {
				return mid;
			}
			return mid + 1;
		}
		if (target == nums[mid]) {
			return mid;
		}
		if (target < nums[mid]) {
			return recur(nums, left, mid - 1, target);
		} else {
			return recur(nums, mid + 1, right, target);
		}
	}

	public static int searchByRecur(int[] nums, int target) {
		return recur(nums, 0, nums.length - 1, target);
	}

	public static void main(String[] args) {
		System.out.println(loop(new int[] { 1, 3, 5, 6 }, 7));
		System.out.println(searchByRecur(new int[] { 1, 3, 5, 6 }, 1));
	}
}
