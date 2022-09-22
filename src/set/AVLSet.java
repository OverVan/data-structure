package set;

import tree.AVL;

/**
 * 基于平衡二叉树的集合，基于映射构造集合
 * 
 * @param <Elem> 元素类型，即映射中的键类型，须可比较，不可取null
 */
public class AVLSet<Elem extends Comparable<Elem>> implements Set<Elem> {
	private AVL<Elem, Object> avl;

	public AVLSet() {
		avl = new AVL<>();
	}

	@Override
	public int getSize() {
		return avl.getSize();
	}

	@Override
	public boolean isEmpty() {
		return avl.isEmpty();
	}

	@Override
	public void add(Elem elem) {
		avl.add(elem, new Object());
	}

	@Override
	public void remove(Elem elem) {
		avl.remove(elem);
	}

	@Override
	public boolean contains(Elem elem) {
		return avl.contains(elem);
	}

}
