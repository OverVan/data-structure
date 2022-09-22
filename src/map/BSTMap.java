package map;

import tree.BinarySearchTree;

/**
 * 基于二叉搜索树的键存储有序映射
 * 
 * @param <Key>   键类型，不可取null
 * @param <Value> 值类型
 */
public class BSTMap<Key extends Comparable<Key>, Value> implements Map<Key, Value> {
	/**
	 * BST结点元素类型-键值对
	 */
	private class Entry implements Comparable<Entry> {
		private Key key;
		private Value value;

		public Entry(Key key, Value value) {
			this.key = key;
			this.value = value;
		}

		public Entry(Key key) {
			this(key, null);
		}

		@Override
		public String toString() {
			return key + ": " + value;
		}

		@Override
		public int compareTo(Entry entry) {
			return key.compareTo(entry.key);
		}
	}

	// 二叉搜索树
	private BinarySearchTree<Entry> bst;

	/**
	 * 实例化一个空映射
	 */
	public BSTMap() {
		bst = new BinarySearchTree<>();
	}

	@Override
	public void add(Key key, Value value) {
		Entry node = bst.get(new Entry(key));
		if (node == null) {
			bst.add(new Entry(key, value));
		} else {
			node.value = value;
		}
	}

	@Override
	public Value remove(Key key) {
		Entry target = bst.get(new Entry(key));
		if (target == null) {
			return null;
		}
		bst.remove(new Entry(key));
		return target.value;
	}

	@Override
	public boolean contains(Key key) {
		return bst.contains(new Entry(key));
	}

	@Override
	public Value get(Key key) {
		Entry target = bst.get(new Entry(key));
		return target == null ? null : target.value;
	}

	@Override
	public void set(Key key, Value value) {
		Entry target = bst.get(new Entry(key));
		if (target == null) {
			throw new RuntimeException("键不存在");
		}
		target.value = value;
	}

	@Override
	public int getSize() {
		return bst.getSize();
	}

	@Override
	public boolean isEmpty() {
		return bst.isEmpty();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("BSTMap [").append(bst.toInOrderTraverseString().replace("BinarySearchTree [", ""));
		return res.toString();
	}
}
