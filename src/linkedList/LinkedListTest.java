package linkedList;

import org.junit.jupiter.api.Test;

public class LinkedListTest {
	@Test
	void testLinkedList() {
		LinkedList<Integer> linkedList = new LinkedList<>();
		for (int i = 0; i < 3; i++) {
			linkedList.addFirst(2);
		}
		linkedList.addLast(3);
		linkedList.addLast(2);
		linkedList.addLast(4);
		System.out.println(linkedList);
		linkedList.removeAll(2);
		System.out.println(linkedList);
	}

	@Test
	void testDummyLinkedList() {
		DummyLinkedList<Integer> linkedList = new DummyLinkedList<>();
		for (int i = 0; i < 3; i++) {
			linkedList.addFirst(2);
		}
		linkedList.addLast(3);
		linkedList.addLast(2);
		linkedList.addLast(4);
		System.out.println(linkedList);
		linkedList.removeAll(2);
		System.out.println(linkedList);
	}

	@Test
	void testDoubleLinkedList() {
		DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();
		for (int i = 0; i < 3; i++) {
			linkedList.addFirst(i);
		}
		linkedList.addFirst(7);
		System.out.println(linkedList.toNormalString());
		linkedList.addLast(8);
		System.out.println(linkedList.toReverseString());
		System.out.println(linkedList.removeLast());
		System.out.println(linkedList.removeLast());
		System.out.println(linkedList.removeFirst());
		System.out.println(linkedList.toNormalString());
		System.out.println(linkedList.toReverseString());
	}

	@Test
	void testDummyCircularLinkedList() {
		DummyCircularLinkedList<Integer> linkedList = new DummyCircularLinkedList<>();
		for (int i = 0; i < 5; i++) {
			linkedList.addLast(i);
		}
		System.out.println(linkedList);
		System.out.println(linkedList.removeFirst());
		System.out.println(linkedList.removeLast());
		System.out.println(linkedList.removeFirst());
		System.out.println(linkedList.removeLast());
		System.out.println(linkedList.removeFirst());
		System.out.println(linkedList);
	}
}
