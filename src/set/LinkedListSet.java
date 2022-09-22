package set;

import linkedList.DummyLinkedList;

/**
 * 基于单链表的集合
 * 
 * @param <Elem> 元素类型
 */
public class LinkedListSet<Elem> implements Set<Elem> {
	private DummyLinkedList<Elem> linkedList;

	public LinkedListSet() {
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

	/**
	 * 受contains的影响，O(n)
	 */
	@Override
	public void add(Elem elem) {
		// 保证不重复，先查有没有
		if (!linkedList.contains(elem)) {
			linkedList.addFirst(elem);
		}
		// 重复则忽略
	}

	/**
	 * O(n)
	 */
	@Override
	public void remove(Elem elem) {
		linkedList.removeElem(elem);
	}

	/**
	 * O(n)
	 */
	@Override
	public boolean contains(Elem elem) {
		return linkedList.contains(elem);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("LinkedListSet [").append(linkedList.toString().replace("LinkedList [", ""));
		return res.toString();
	}

}
