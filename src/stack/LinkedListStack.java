package stack;

import linkedList.DummyLinkedList;

/**
 * 基于单链表的栈
 * 
 * @param <Elem> 元素类型
 */
public class LinkedListStack<Elem> implements Stack<Elem> {
	private DummyLinkedList<Elem> linkedList;

	public LinkedListStack() {
		linkedList = new DummyLinkedList<>();
	}

	@Override
	public int getSize() {
		return linkedList.getSize();
	}

	@Override
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	@Override
	public void push(Elem elem) {
		// 头插
		linkedList.addFirst(elem);
	}

	@Override
	public Elem pop() {
		// 头删
		return linkedList.removeFirst();
	}

	@Override
	public Elem peek() {
		// 头查
		return linkedList.getFirst();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("LinkedListStack top").append(linkedList.toString().substring(10));
		return res.toString();
	}
}
