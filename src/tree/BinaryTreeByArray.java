package tree;

/**
 * 基于顺序存储的二叉树
 * 
 * @author Van
 */
public class BinaryTreeByArray {
	private int[] array;

	public BinaryTreeByArray(int[] array) {
		this.array = array;
	}

	/**
	 * 先序遍历
	 * 
	 * @param index 数组下标
	 */
	public void preOrderTraversal(int index) {
		// 树为空的情况
		if (array.length == 0 || array == null) {
			System.out.println("二叉树为空");
			return;
		}
		// 访问数据
		System.out.print(array[index]);
		// 先序遍历左子树
		if ((index * 2 + 1) < array.length) {
			preOrderTraversal(index * 2 + 1);
		}
		// 先序遍历右子树
		if ((index * 2 + 2) < array.length) {
			preOrderTraversal(index * 2 + 2);
		}
	}

	/**
	 * 中序遍历
	 * 
	 * @param index 数组下标
	 */
	public void inOrderTraversal(int index) {
		if (array.length == 0 || array == null) {
			System.out.println("二叉树为空");
			return;
		}
		// 先序遍历左子树
		if ((index * 2 + 1) < array.length) {
			preOrderTraversal(index * 2 + 1);
		}
		// 访问数据
		System.out.print(array[index]);
		// 先序遍历右子树
		if ((index * 2 + 2) < array.length) {
			preOrderTraversal(index * 2 + 2);
		}
	}

	/**
	 * 后序遍历
	 * 
	 * @param index 数组下标
	 */
	public void postOrderTraversal(int index) {
		if (array.length == 0 || array == null) {
			System.out.println("二叉树为空");
			return;
		}
		// 先序遍历左子树
		if ((index * 2 + 1) < array.length) {
			preOrderTraversal(index * 2 + 1);
		}
		// 先序遍历右子树
		if ((index * 2 + 2) < array.length) {
			preOrderTraversal(index * 2 + 2);
		}
		// 访问数据
		System.out.print(array[index]);
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 手动搭二叉树
		int[] array = { 1, 2, 3, 4, 5, 6, 7 };
		BinaryTreeByArray binaryTreeByArray = new BinaryTreeByArray(array);
		// 先序遍历
		binaryTreeByArray.preOrderTraversal(0);
		System.out.println();
		// 中序遍历
		binaryTreeByArray.inOrderTraversal(0);
		System.out.println();
		// 后序遍历
		binaryTreeByArray.postOrderTraversal(0);
		System.out.println();
	}
}
