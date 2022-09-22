package set;

import tree.BinarySearchTree;

/**
 * 基于二叉搜索树的集合
 * 
 * @param <Elem> 元素类型，须可比较，不可取null
 */
public class BSTSet<Elem extends Comparable<Elem>> implements Set<Elem> {
	private BinarySearchTree<Elem> bst;

	public BSTSet() {
		this.bst = new BinarySearchTree<>();
	}

	@Override
	public int getSize() {
		return bst.getSize();
	}

	@Override
	public boolean isEmpty() {
		return bst.isEmpty();
	}

	/**
	 * 平均O(logn)；最坏O(n)；最准确O(depth)；最好O(1) 下同
	 */
	@Override
	public void add(Elem elem) {
		bst.add(elem);
	}

	@Override
	public void remove(Elem elem) {
		bst.remove(elem);
	}

	@Override
	public boolean contains(Elem elem) {
		return bst.contains(elem);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("BSTSet [").append(bst.toInOrderTraverseString().replace("BinarySearchTree [", ""));
		return res.toString();
	}
}
