package tree;

/**
 * 结点
 * 
 * @author Van
 */
class Node {
	private int data;// 数据（这里就用整数）
	private Node left;// 左孩子结点
	private Node right;// 右孩子结点

	public Node(int data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}
}

/**
 * 二叉树
 * 
 * @author Van
 */
public class BinaryTree {
	// 根结点
	private Node root;

	public BinaryTree(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * 先序遍历
	 * 
	 * @param binTree 当前树的根结点
	 */
	public void preOrderTraversal(Node binTree) {
		// 打印数据
		System.out.print(binTree.getData());
		// 先序遍历左子树
		if (binTree.getLeft() != null) {
			preOrderTraversal(binTree.getLeft());
		}
		// 先序遍历右子树
		if (binTree.getRight() != null) {
			preOrderTraversal(binTree.getRight());
		}
	}

	/**
	 * 中序遍历
	 * 
	 * @param binTree 当前树的根结点
	 */
	public void inOrderTraversal(Node binTree) {
		// 中序遍历左子树
		if (binTree.getLeft() != null) {
			inOrderTraversal(binTree.getLeft());
		}
		// 打印数据
		System.out.print(binTree.getData());
		// 中序遍历右子树
		if (binTree.getRight() != null) {
			inOrderTraversal(binTree.getRight());
		}
	}

	/**
	 * 后序遍历
	 * 
	 * @param binTree 当前树的根结点
	 */
	public void postOrderTraversal(Node binTree) {
		// 后序遍历左子树
		if (binTree.getLeft() != null) {
			postOrderTraversal(binTree.getLeft());
		}
		// 后序遍历右子树
		if (binTree.getRight() != null) {
			postOrderTraversal(binTree.getRight());
		}
		// 打印数据
		System.out.print(binTree.getData());
	}

	/**
	 * 先序查找
	 * 
	 * @param binTree 当前树的根结点
	 * @param target  目标数据
	 * @return
	 */
	public Node preOrderSearch(Node binTree, int target) {
		// 默认返回null
		Node result = null;
		// 比对本结点数据
		if (binTree.getData() == target) {
			return binTree;
		}
		// 先序查找左子树
		if (binTree.getLeft() != null) {
			result = preOrderSearch(binTree.getLeft(), target);
			if (result != null) {
				return result;
			}
		}
		// 先序查找右子树
		if (binTree.getRight() != null) {
			result = preOrderSearch(binTree.getRight(), target);
			if (result != null) {
				return result;
			}
		}
		return result;
	}

	/**
	 * 中序查找
	 * 
	 * @param binTree 当前树的根结点
	 * @param target  目标数据
	 * @return
	 */
	public Node inOrderSearch(Node binTree, int target) {
		// 默认返回null
		Node result = null;
		// 中序查找左子树
		if (binTree.getLeft() != null) {
			result = preOrderSearch(binTree.getLeft(), target);
			if (result != null) {
				return result;
			}
		}
		// 比对本结点数据
		if (binTree.getData() == target) {
			return binTree;
		}
		// 中序查找右子树
		if (binTree.getRight() != null) {
			result = preOrderSearch(binTree.getRight(), target);
			if (result != null) {
				return result;
			}
		}
		return result;
	}

	/**
	 * 后序查找
	 * 
	 * @param binTree 当前树的根结点
	 * @param target  目标数据
	 * @return
	 */
	public Node postOrderSearch(Node binTree, int target) {
		// 默认返回null
		Node result = null;
		// 后序查找左子树
		if (binTree.getLeft() != null) {
			result = preOrderSearch(binTree.getLeft(), target);
			if (result != null) {
				return result;
			}
		}
		// 后序查找右子树
		if (binTree.getRight() != null) {
			result = preOrderSearch(binTree.getRight(), target);
			if (result != null) {
				return result;
			}
		}
		// 比对本结点数据
		if (binTree.getData() == target) {
			return binTree;
		}
		return result;
	}

	/**
	 * 删除结点。对于非叶子结点，删除以之为根的树
	 * 
	 * @param target 目标数据
	 */
	public void deleteNode(Node binTree, int target) {
		// 目标结点为根结点的情况
		if (root.getData() == target) {
			setRoot(null);
			return;
		}
		// 目标结点非根结点的情况
		if (binTree.getLeft() != null && binTree.getLeft().getData() == target) {
			binTree.setLeft(null);
		} else if (binTree.getRight() != null && binTree.getRight().getData() == target) {
			binTree.setRight(null);
		} else if (binTree.getLeft() != null) {// 向左递归删除结点
			deleteNode(binTree.getLeft(), target);
		} else if (binTree.getRight() != null) {// 向右递归删除结点
			deleteNode(binTree.getRight(), target);
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 手动搭二叉树
		Node root = new Node(1);
		Node node1 = new Node(3);
		Node node2 = new Node(5);
		Node node3 = new Node(4);
		Node node4 = new Node(6);
		Node node5 = new Node(2);
		root.setLeft(node1);
		root.setRight(node2);
		node1.setLeft(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		BinaryTree binaryTree = new BinaryTree(root);
		// 先序遍历
		binaryTree.preOrderTraversal(binaryTree.getRoot());
		System.out.println();
		// 中序遍历
		binaryTree.inOrderTraversal(binaryTree.getRoot());
		System.out.println();
		// 后序遍历
		binaryTree.postOrderTraversal(binaryTree.getRoot());
		System.out.println();
		// 先序查找
		System.out.println(binaryTree.preOrderSearch(binaryTree.getRoot(), 1));
		// 中序查找
		System.out.println(binaryTree.inOrderSearch(binaryTree.getRoot(), 5));
		// 后序查找
		System.out.println(binaryTree.postOrderSearch(binaryTree.getRoot(), 2));
		// 删除结点
		binaryTree.deleteNode(binaryTree.getRoot(), 5);
		binaryTree.preOrderTraversal(binaryTree.getRoot());
	}
}
