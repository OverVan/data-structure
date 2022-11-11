package leetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

import heap.SeqListMaxHeap;

/**
 * 215. 数组中的第K个最大元素
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/
 */
public class FindKthLargest {
	/**
	 * 基于内置优先队列的堆排序 时间O(n+klogn)即O(n)，空间O(n)
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int heapSort(int[] nums, int k) {
		// 大值优先队列
		PriorityQueue<Integer> queue = new PriorityQueue<>(nums.length, (num1, num2) -> {
			return num2 - num1;
		});
		for (int i = 0; i < nums.length; i++) {
			queue.add(nums[i]);
		}
		for (int i = 0; i < k - 1; i++) {
			queue.remove();
		}
		return queue.remove();
	}

	/**
	 * 使用自制的大顶堆 时间O(n+klogn)，空间O(n)
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int useSeqListMaxHeap(int[] nums, int k) {
		SeqListMaxHeap<Integer> heap = new SeqListMaxHeap<>(nums.length);
		// 堆化
		for (int i = 0; i < nums.length; i++) {
			heap.add(nums[i]);
		}
		for (int i = 0; i < k - 1; i++) {
			heap.extractMax();
		}
		return heap.extractMax();
	}

	/**
	 * 普通快排 时间O(nlogn)，空间O(1)
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int quickSort(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[nums.length - k];
	}

	/**
	 * 优化的快速排序，只用递归到当前区间长度为k（k随递归可能变小） 时间O(n)，空间O(1)
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int selectiveQuickSort(int[] nums, int k) {
		return recur(nums, k, 0, nums.length - 1, 0);
	}

	private static int recur(int[] nums, int k, int left, int right, int index) {
		int low = left;
		int high = right;
		int pivot = nums[index];
		while (low < high) {
			while (nums[high] >= pivot && low < high) {
				high--;
			}
			nums[low] = nums[high];
			while (nums[low] <= pivot && low < high) {
				low++;
			}
			nums[high] = nums[low];
		}
		nums[low] = pivot;
		int length = right - low + 1;
		if (length == k) {
			return pivot;
		}
		if (length > k) {
			return recur(nums, k, low + 1, right, index);
		}
		return recur(nums, k - length, left, low - 1, index);
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
//		int[] nums = new int[] { 2, 1 };
//		System.out.println(heapSort(nums, 4));
//		System.out.println(useSeqListMaxHeap(nums, 4));
//		System.out.println(quickSort(nums, 4));
		System.out.println(selectiveQuickSort(nums, 4));
	}
}
