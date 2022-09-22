package heap;

import java.util.Random;

import org.junit.jupiter.api.Test;

class HeapTest {

	@Test
	void testSeqListMaxHeap() {
		SeqListMaxHeap<Integer> heap = new SeqListMaxHeap<>();
		Integer[] nums = new Integer[] { 52, 41, 62, 30, 15, 13, 22, 19, 28, 17, 16 };
		for (Integer num : nums) {
			heap.add(num);
		}
		System.out.println(heap);
		System.out.println(heap.extractMax());
		System.out.println(heap);
	}

	/**
	 * 用堆排序，同时检验堆的有效性
	 */
	@Test
	void testHeapSort() {
		SeqListMaxHeap<Integer> heap = new SeqListMaxHeap<>();
		Random random = new Random(10);
		int optCount = 100000;
		for (int i = 0; i < optCount; i++) {
			heap.add(random.nextInt(Integer.MAX_VALUE));
		}
		int[] arr = new int[optCount];
		// 不断取出根结点，即取出最大元素、次大元素，以此类推
		for (int i = 0; i < optCount; i++) {
			arr[i] = heap.extractMax();
		}
		for (int i = 1; i < optCount; i++) {
			if (arr[i - 1] < arr[i]) {
				throw new RuntimeException("堆结构有误");
			}
		}
		System.out.println("堆结构无误");
		System.out.println(heap);
	}

	@Test
	void testHeapify() {
		SeqListMaxHeap<Integer> heap = new SeqListMaxHeap<>(
				new Integer[] { 52, 41, 72, 30, 15, 13, 22, 19, 28, 17, 16 });
		System.out.println(heap);
	}

}
