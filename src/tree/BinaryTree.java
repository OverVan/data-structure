package tree;

import java.util.Objects;

/**
 * 二叉树 方法统一基于递归
 * 
 * @param <Elem> 元素类型
 */
public class BinaryTree<Elem> {
	/**
	 * 结点
	 */
	public class Node {
		// 元素
		private Elem elem;
		// 左孩子
		private Node left;
		// 右孩子
		private Node right;

		public Node(Elem elem, Node left, Node right) {
			this.elem = elem;
			this.left = left;
			this.right = right;
		}

		public Node(Elem elem) {
			this(elem, null, null);
		}

		/**
		 * 填充元素
		 * 
		 * @param elem 元素
		 */
		public void setElem(Elem elem) {
			this.elem = elem;
		}

		/**
		 * 添加左孩子
		 * 
		 * @param left 左孩子
		 */
		public void setLeft(Node left) {
			this.left = left;
		}

		/**
		 * 添加右孩子
		 * 
		 * @param right 右孩子
		 */
		public void setRight(Node right) {
			this.right = right;
		}

		@Override
		public String toString() {
			return elem.toString();
		}
	}

	// 根结点
	private Node root;

	public BinaryTree(Node root) {
		this.root = root;
	}

	public BinaryTree() {
		super();
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * 先序遍历当前二叉树
	 * 
	 * @param node 根结点
	 */
	private void preOrderTraverse(Node node) {
		if (node != null) {
			System.out.print(node.elem);
			// 先序遍历左子树
			preOrderTraverse(node.left);
			// 先序遍历右子树
			preOrderTraverse(node.right);
		}
	}

	/**
	 * 先序遍历
	 */
	public void preOrderTraverse() {
		preOrderTraverse(root);
	}

	/**
	 * 中序遍历当前二叉树
	 * 
	 * @param node 根结点
	 */
	private void inOrderTraverse(Node node) {
		if (node != null) {
			// 中序遍历左子树
			inOrderTraverse(node.left);
			System.out.print(node.elem);
			// 中序遍历右子树
			inOrderTraverse(node.right);
		}
	}

	/**
	 * 中序遍历
	 */
	public void inOrderTraverse() {
		inOrderTraverse(root);
	}

	/**
	 * 后序遍历当前二叉树
	 * 
	 * @param node 根结点
	 */
	private void postOrderTraverse(Node node) {
		if (node != null) {
			// 后序遍历左子树
			postOrderTraverse(node.left);
			// 后序遍历右子树
			postOrderTraverse(node.right);
			System.out.print(node.elem);
		}
	}

	/**
	 * 后序遍历
	 */
	public void postOrderTraverse() {
		postOrderTraverse(root);
	}

	/**
	 * 基于先序查找判断当前二叉树是否包含指定元素
	 * 
	 * @param node 根结点
	 * @param elem 元素
	 * @return
	 */
	private boolean preOrderSearch(Node node, Elem elem) {
		boolean exist = false;
		if (node != null) {
			if (Objects.equals(node.elem, elem)) {
				exist = true;
			}
			// 先序查找左子树
			if (exist == false) {
				exist = preOrderSearch(node.left, elem);
			}
			// 先序查找右子树
			if (exist == false) {
				exist = preOrderSearch(node.right, elem);
			}
		}
		return exist;
	}

	/**
	 * 基于先序查找判断是否包含指定元素
	 * 
	 * @param elem 元素
	 * @return
	 */
	public boolean preOrderSearch(Elem elem) {
		return preOrderSearch(root, elem);
	}

	/**
	 * 基于中序查找在当前二叉树中删除指定元素
	 * 
	 * @param node 根结点
	 * @param elem 元素
	 * @return
	 */
	private boolean inOrderRemove(Node node, Elem elem) {
		boolean exist = false;
		if (node != null) {
			// 中序查找左子树
			exist = inOrderRemove(node.left, elem);
			// 凭父结点解挂目标结点
			if (exist == false && node.left != null && Objects.equals(node.left.elem, elem)) {
				node.left = null;
				exist = true;
			}
			if (exist == false && node.right != null && Objects.equals(node.right.elem, elem)) {
				node.right = null;
				exist = true;
			}
			// 中序查找右子树
			if (exist == false) {
				exist = inOrderRemove(node.right, elem);
			}
		}
		return exist;
	}

	/**
	 * 中序查找并删除指定元素，连带删除子树
	 * 
	 * @param elem 元素
	 */
	public void inOrderRemove(Elem elem) {
		if (Objects.equals(root.elem, elem)) {
			// root没有父结点
			root = null;
		} else {
			inOrderRemove(root, elem);
		}
	}
}
