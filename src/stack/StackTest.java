package stack;

import java.util.Random;

import org.junit.jupiter.api.Test;

class StackTest {

	@Test
	void testSeqListStack() {
		Stack<Integer> stack = new SeqListStack<>(8);
		for (int i = 0; i < 3; i++) {
			stack.push(i);
			System.out.println(stack);
		}
		stack.pop();
		System.out.println(stack);
		stack.pop();
		stack.pop();
		System.out.println(stack);
	}

	@Test
	void testDummyLinkedListStack() {
		Stack<Integer> stack = new LinkedListStack<>();
		for (int i = 0; i < 3; i++) {
			stack.push(i);
			System.out.println(stack);
		}
		stack.pop();
		System.out.println(stack);
	}

	/**
	 * 出栈入栈算法计时
	 * 
	 * @param stack    栈
	 * @param optCount 出栈或入栈次数
	 */
	private double time(Stack<Integer> stack, int optCount) {
		Random random = new Random();
		// 纳秒
		long begin = System.nanoTime();
		for (int i = 0; i < optCount; i++) {
			stack.push(random.nextInt(20));
		}
		for (int i = 0; i < optCount; i++) {
			stack.pop();
		}
		long end = System.nanoTime();
		return (end - begin) / 1E9;
	}

	/**
	 * 对比顺序表栈与单链表栈的性能
	 */
	@Test
	void testCompare() {
		// 它们出栈入栈复杂度都是O(1)，实际性能不相上下，上者受变容影响较大，下者受new结点影响较大，但终究达不到数量级上的差距
		System.out.printf("SeqListStack, time: %f\n", time(new SeqListStack<>(), (int) 1E3));
		System.out.printf("LinkedListStack, time: %f", time(new LinkedListStack<>(), (int) 1E3));
	}

}
