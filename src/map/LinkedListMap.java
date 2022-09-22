package map;

import java.util.Objects;

/**
 * 基于单链表的建存储无序映射
 * 
 * @param <Key>   键类型
 * @param <Value> 值类型
 */
public class LinkedListMap<Key, Value> implements Map<Key, Value> {
	/**
	 * 结点
	 */
	private class Node {
		// 键
		private Key key;
		// 值
		private Value value;
		// 后继
		private Node next;

		public Node(Key key, Value value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public Node() {
			this(null, null, null);
		}

		@Override
		public String toString() {
			return key + ": " + value;
		}
	}

	// 虚拟头结点
	private Node dummyHead;
	// 键值对总数
	private int size;

	/**
	 * 实例化一个空映射
	 */
	public LinkedListMap() {
		this.dummyHead = new Node();
		this.size = 0;
	}

	/**
	 * 以键获取结点
	 * 
	 * @param key 键
	 * @return 结点
	 */
	private Node getNode(Key key) {
		Node cursor = dummyHead.next;
		while (cursor != null) {
			if (Objects.equals(cursor.key, key)) {
				return cursor;
			}
			cursor = cursor.next;
		}
		return null;
	}

	/**
	 * O(n)
	 */
	@Override
	public void add(Key key, Value value) {
		Node node = getNode(key);
		if (node == null) {
			// 头插
			dummyHead.next = new Node(key, value, dummyHead.next);
			size++;
		} else {
			// 键重复则更新（或抛异常）
			node.value = value;
		}
	}

	/**
	 * O(n)
	 */
	@Override
	public Value remove(Key key) {
		Node cursor = dummyHead;
		Node del = null;
		while (cursor.next != null) {
			if (Objects.equals(cursor.next.key, key)) {
				del = cursor.next;
				cursor.next = del.next;
				del.next = null;
				size--;
				// 键不重复，找到一个就返回
				return del.value;
			} else {
				cursor = cursor.next;
			}
		}
		return null;
	}

	/**
	 * O(n)
	 */
	@Override
	public boolean contains(Key key) {
		return getNode(key) != null;
	}

	/**
	 * O(n)
	 */
	@Override
	public Value get(Key key) {
		Node target = getNode(key);
		return target == null ? null : target.value;
	}

	/**
	 * O(n)
	 */
	@Override
	public void set(Key key, Value value) {
		Node node = getNode(key);
		if (node == null) {
			throw new RuntimeException("键不存在");
		} else {
			node.value = value;
		}
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("LinkedListMap [");
		Node cursor = dummyHead.next;
		while (cursor != null) {
			res.append(cursor);
			if (cursor.next != null) {
				res.append(", ");
			}
			cursor = cursor.next;
		}
		res.append("]");
		return res.toString();
	}
}
