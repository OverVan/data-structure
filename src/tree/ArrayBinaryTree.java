package tree;

/**
 * 基于数组的二叉树
 * 
 * @param <Elem> 元素类型
 */
public class ArrayBinaryTree<Elem> {
	// 从前往后找到空位置x填元素，那么左孩子位于2x+1，右孩子位于2x+2
	private Elem[] array;

	public ArrayBinaryTree(Elem[] array) {
		this.array = array;
	}

	/**
	 * 先序遍历
	 * 
	 * @param index 下标
	 */
	public void preOrderTraverse(int index) {
		// 树为空
		if (array.length == 0 || array == null) {
			System.out.println("二叉树为空");
			return;
		}
		System.out.print(array[index]);
		// 先序遍历左子树
		if ((index * 2 + 1) < array.length) {
			preOrderTraverse(index * 2 + 1);
		}
		// 先序遍历右子树
		if ((index * 2 + 2) < array.length) {
			preOrderTraverse(index * 2 + 2);
		}
	}

	/**
	 * 中序遍历
	 * 
	 * @param index 下标
	 */
	public void inOrderTraverse(int index) {
		if (array.length == 0 || array == null) {
			System.out.println("二叉树为空");
			return;
		}
		// 先序遍历左子树
		if ((index * 2 + 1) < array.length) {
			preOrderTraverse(index * 2 + 1);
		}
		System.out.print(array[index]);
		// 先序遍历右子树
		if ((index * 2 + 2) < array.length) {
			preOrderTraverse(index * 2 + 2);
		}
	}

	/**
	 * 后序遍历
	 * 
	 * @param index 下标
	 */
	public void postOrderTraverse(int index) {
		if (array.length == 0 || array == null) {
			System.out.println("二叉树为空");
			return;
		}
		// 先序遍历左子树
		if ((index * 2 + 1) < array.length) {
			preOrderTraverse(index * 2 + 1);
		}
		// 先序遍历右子树
		if ((index * 2 + 2) < array.length) {
			preOrderTraverse(index * 2 + 2);
		}
		System.out.print(array[index]);
	}
}
