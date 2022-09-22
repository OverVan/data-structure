package seqList;

import java.util.Random;

import org.junit.jupiter.api.Test;

class SeqListTest {
	@Test
	void testSeqList() {
		SeqList seqList = new SeqList();
		int[] arr = new int[] { 3, 3, 3, 3, 2, 3, 3, 6 };
		for (int i : arr) {
			seqList.addLast(i);
		}
		seqList.removeEvery(3);
		System.out.println(seqList);
	}

	@Test
	void testDynamicSeqList() {
		DynamicSeqList<Student> students = new DynamicSeqList<>(2);
		students.addFirst(new Student("bob", 90));
		students.addFirst(new Student("tom", 80));
		System.out.println(students.toString());
		students.addFirst(new Student("joe", 86));
		System.out.println(students.toString());
		students.removeLast();
		students.removeLast();
		System.out.println(students.toString());
//		DynamicSeqList<Integer> seqList = new DynamicSeqList<>();
//		for (int i = 0; i < 1000; i++) {
//			seqList.addFirst(i);
//		}
//		System.out.println(seqList);
	}

	@Test
	void testRemoveAll() {
		DynamicSeqList<Integer> dynamicSeqList = new DynamicSeqList<>();
		Integer[] arr = new Integer[] { 3, 3, 3, 3, 2, 3, 3, 6, null };
		for (Integer i : arr) {
			dynamicSeqList.addLast(i);
		}
		dynamicSeqList.removeElem(3);
		System.out.println(dynamicSeqList);
	}

	/**
	 * 对比两种removeAll算法的性能
	 */
	@Test
	void testCompare() {
		Random random = new Random();
		SeqList seqList = new SeqList(100000);
		// 50000个2，50000个其他值
		for (int i = 0; i < 100000; i++) {
			if (i % 2 == 0) {
				seqList.addLast(2);
			} else {
				seqList.addLast(random.nextInt(10));
			}
		}
		long begin = System.currentTimeMillis();
		seqList.removeEvery(2);
		long end = System.currentTimeMillis();
		System.out.println("removeEvery: " + (end - begin));
		begin = System.currentTimeMillis();
		seqList.removeAll(2);
		end = System.currentTimeMillis();
		System.out.println("removeAll: " + (end - begin));
		// 差两个数量级
	}
}
