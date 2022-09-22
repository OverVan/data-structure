package map;

import org.junit.jupiter.api.Test;

class MapTest {

	@Test
	void testLinkedListMap() {
		Map<String, Integer> map = new LinkedListMap<>();
		map.add("china", 1);
		map.add("russia", 2);
		map.add("japan", 10);
		System.out.println(map);
		System.out.println(map.remove("japan"));
		map.add("china", 3);
		System.out.println(map);
		System.out.println(map.getSize());
	}

	@Test
	void testBinarySearchTreeMap() {
		Map<String, Integer> map = new BinarySearchTreeMap<>();
		map.add("china", 1);
		map.add("russia", 2);
		map.add("japan", 10);
		System.out.println(map);
		System.out.println(map.remove("japan"));
		map.add("china", 3);
		System.out.println(map);
		System.out.println(map.getSize());
	}

	@Test
	void testBSTMap() {
		Map<String, Integer> map = new BSTMap<>();
		map.add("china", 1);
		map.add("russia", 2);
		map.add("japan", 10);
		System.out.println(map);
		System.out.println(map.remove("javan"));
	}

	/**
	 * 计时
	 * 
	 * @param map      映射
	 * @param optCount 添加或查找的次数
	 * @return 时长
	 */
	private double time(Map<Integer, String> map, int optCount) {
		// 极端：BST退化为单链表
		long begin = System.currentTimeMillis();
		for (int i = 0; i < optCount; i++) {
			map.add(i, "map");
		}
		for (int i = 0; i < optCount; i++) {
			map.get(i);
		}
		long end = System.currentTimeMillis();
		return (end - begin) / 1E3;
	}

	@Test
	void testCompare() {
		// 归根结底还是比较AVL、BST、单链表
		System.out.println("BSTMap: " + time(new BinarySearchTreeMap<Integer, String>(), 1000));
//		System.out.println("LinkedListMap: " + time(new LinkedListMap<Integer, String>(), 1000));
		System.out.println("AVLMap: " + time(new AVLMap<>(), 1000));
	}

}
