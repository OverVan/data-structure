package map;

import tree.AVL;

/**
 * 基于平衡二叉树的映射
 * 
 * @param <Key>   键类型，不可取null
 * @param <Value> 值类型
 */
public class AVLMap<Key extends Comparable<Key>, Value> implements Map<Key, Value> {
	private AVL<Key, Value> avl;

	public AVLMap() {
		avl = new AVL<>();
	}

	@Override
	public void add(Key key, Value value) {
		avl.add(key, value);
	}

	@Override
	public Value remove(Key key) {
		return avl.remove(key);
	}

	@Override
	public boolean contains(Key key) {
		return avl.contains(key);
	}

	@Override
	public Value get(Key key) {
		return avl.get(key);
	}

	@Override
	public void set(Key key, Value value) {
		avl.set(key, value);
	}

	@Override
	public int getSize() {
		return avl.getSize();
	}

	@Override
	public boolean isEmpty() {
		return avl.isEmpty();
	}

}
