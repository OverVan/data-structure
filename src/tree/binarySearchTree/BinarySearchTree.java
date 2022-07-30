package tree.binarySearchTree;

/**
 * 结点
 * 
 * @author Van
 */
class Node {
	// 值
	private int value;
	// 左子结点
	private Node left;
	// 右子结点
	private Node right;

	/**
	 * 构造器：左右结点为空
	 * 
	 * @param value 数据
	 */
	public Node(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public Node getRight() {
		return right;
	}

	public Node getLeft() {
		return left;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	/**
	 * 添加结点
	 * 
	 * @param node 结点
	 */
	public void add(Node node) {
		if (node == null) {
			return;
		}
		// 往左子树上挂
		if (node.getValue() < value) {
			if (left == null) {
				left = node;
			} else {
				left.add(node);
			}
		}
		// 往右子树上挂
		else {
			if (right == null) {
				right = node;
			} else {
				right.add(node);
			}
		}
	}

	/**
	 * 查找要删除的结点
	 * 
	 * @param value 数据
	 * @return
	 */
	public Node searchNode(int value) {
		if (this.value == value) { // 数据相等，直接返回
			return this;
		} else if (value < this.value) { // 向左子树查找
			if (left != null) {
				return left.searchNode(value);
			} else {
				return null;
			}
		} else { // 向右子树查找
			if (right != null) {
				return right.searchNode(value);
			} else {
				return null;
			}
		}
	}

	/**
	 * 查找目标结点的父结点
	 * 
	 * @param value 数据
	 * @return
	 */
	public Node searchParentNode(int value) {
		if ((left != null && left.getValue() == value) || (right != null && right.getValue() == value)) { // 直接返回
			return this;
		} else {
			if (left != null && value < this.value) { // 向左子树查找
				return left.searchParentNode(value);
			} else if (right != null && value > this.value) { // 向右子树查找
				return right.searchParentNode(value);
			} else { // 其他情况都返回空
				return null;
			}
		}
	}
}

/**
 * 二叉搜索树（二叉排序树）
 * 
 * @author Van
 */
public class BinarySearchTree {
	// 根结点
	private Node root;

	public Node getRoot() {
		return root;
	}

	/**
	 * 中序遍历
	 * 
	 * @param binTree 当前树的根结点
	 */
	public void inOrderTraversal(Node bst) {
		// 中序遍历左子树
		if (bst.getLeft() != null) {
			inOrderTraversal(bst.getLeft());
		}
		// 打印数据
		System.out.print(bst.getValue() + " ");
		// 中序遍历右子树
		if (bst.getRight() != null) {
			inOrderTraversal(bst.getRight());
		}
	}

	/**
	 * 添加结点
	 * 
	 * @param node 结点
	 */
	public void addNode(Node node) {
		// 若树为空则直接将传入结点作根结点
		if (root == null) {
			root = node;
		} else {
			root.add(node);
		}
	}

	/**
	 * 查找目标结点
	 * 
	 * @param value 数据
	 * @return
	 */
	public Node searchNode(int value) {
		if (root == null) {
			return null;
		} else {
			return root.searchNode(value);
		}
	}

	/**
	 * 查找目标结点的父结点
	 * 
	 * @param value 数据
	 * @return
	 */
	public Node searchParentNode(int value) {
		if (root == null) {
			return null;
		} else {
			return root.searchParentNode(value);
		}
	}

	/**
	 * 删除结点
	 * 
	 * @param node
	 */
	public void deleteNode(Node node) {
		// 叶子结点
		// 仅有一个叶子结点的非叶子结点
		// 有两个子结点的非叶子结点
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 7, 3, 10, 12, 5, 1, 9 };
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		// 将数组里的元素依次挂到二叉排序树上
		for (int item : array) {
			binarySearchTree.addNode(new Node(item));
		}
		// 中序遍历：由BST的特点可知其中序遍历的结果一定是从小到大的序列
		System.out.print("中序遍历：");
		binarySearchTree.inOrderTraversal(binarySearchTree.getRoot());
	}
}
