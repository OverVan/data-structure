package set;

import org.junit.jupiter.api.Test;

class SetTest {

	@Test
	void testBSTSet() {
		Set<String> set = new BSTSet<>();
		set.add("van");
		set.add("li");
		set.add("van");
		set.remove("li");
		System.out.println(set.getSize());
//		System.out.println(set);
	}

	@Test
	void testLinkedListSet() {
		Set<String> set = new LinkedListSet<>();
		set.add("van");
		set.add("li");
		set.add("van");
		set.remove("li");
		System.out.println(set.getSize());
		System.out.println(set);
	}

	/**
	 * 计时
	 * 
	 * @param set      集合
	 * @param optCount 添加或查找的次数
	 * @return 时长
	 */
	private double time(Set<Integer> set, int optCount) {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < optCount; i++) {
			set.add(i);
		}
		for (int i = 0; i < optCount; i++) {
			set.contains(i);
		}
		long end = System.currentTimeMillis();
		return (end - begin) / 1E3;
	}

	@Test
	void testCompare() {
		System.out.println("BSTSet: " + time(new BSTSet<Integer>(), 10000));
//		System.out.println("LinkedListSet: " + time(new LinkedListSet<Integer>(), 10000));
		System.out.println("AVLSet: " + time(new AVLSet<>(), 10000));
	}

}
