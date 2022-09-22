package queue;

import org.junit.jupiter.api.Test;

class QueueTest {

	@Test
	void testSeqListQueue() {
		Queue<Integer> queue = new SeqListQueue<>();
		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
			if (i % 3 == 2) {
				queue.dequeue();
			}
			System.out.println(queue);
		}
	}

	@Test
	void testLoopQueue() {
		Queue<Integer> queue = new LoopQueue<>();
		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
			if (i % 3 == 2) {
				queue.dequeue();
			}
			System.out.println(queue);
		}
	}

	/**
	 * 出入队算法计时
	 * 
	 * @param queue    队列
	 * @param optCount 入队或出队次数
	 * @return
	 */
	private static double time(Queue<Integer> queue, int optCount) {
		// 纳秒
		long begin = System.nanoTime();
		for (int i = 0; i < optCount; i++) {
			queue.enqueue(i);
		}
		for (int i = 0; i < optCount; i++) {
			queue.dequeue();
		}
		long end = System.nanoTime();
		return (end - begin) / 1E9;
	}

	/**
	 * 对比顺序表队列、循环队列、单链表队列的性能
	 */
	@Test
	void testCompare() {
		// 差距主要体现在出队操作，总时间差3个数量级
		System.out.printf("SeqListQueue, time: %f\n", time(new SeqListQueue<>(), (int) 1E5));
		System.out.printf("LoopQueue, time: %f\n", time(new LoopQueue<>(), (int) 1E5));
		// 为了严谨，消除计算机系统、JVM状态起伏的偶然性，应当做多组实验然后取平均值
		System.out.printf("LinkedListQueue, time: %f", time(new LinkedListQueue<>(), (int) 1E5));
		// 下两个复杂度都是O(1)，达不到数量级上的差距
	}

	@Test
	void testLinkedListQueue() {
		Queue<Integer> queue = new LinkedListQueue<>();
		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
			if (i % 3 == 2) {
				queue.dequeue();
			}
			System.out.println(queue);
		}
	}

	@Test
	void testSeqListMaxHeapPriorityQueue() {
		SeqListMaxHeapPriorityQueue<Integer> queue = new SeqListMaxHeapPriorityQueue<>();
		Integer[] nums = new Integer[] { 52, 41, 62, 30, 15, 13, 22, 19, 28, 17, 16 };
		for (Integer num : nums) {
			queue.enqueue(num);
		}
		while (!queue.isEmpty()) {
			System.out.println(queue.dequeue());
		}
	}

}
