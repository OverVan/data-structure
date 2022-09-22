package tree;

import stack.SeqListStack;
import stack.Stack;

/**
 * 二叉搜索树，统一用循环实现
 * 
 * @param <Elem> 元素类型，必须可比，不可取null
 */
public class BST<Elem extends Comparable<Elem>> {
	/**
	 * 结点
	 */
	private class Node {
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

		@Override
		public String toString() {
			return elem.toString();
		}
	}

	// 根结点
	private Node root;
	// 元素总数
	private int size;

	public boolean isEmpty() {
		// root == null
		return size == 0;
	}

	public int getSize() {
		return size;
	}

	/**
	 * 添加元素
	 * 
	 * @param elem 元素
	 */
	public void add(Elem elem) {
		// 根结点无父结点
		if (isEmpty()) {
			root = new Node(elem);
			size++;
			return;
		}
		Node curosr = root;
		Node parent = null;
		while (curosr != null) {
			// 凭父结点挂上目标结点
			parent = curosr;
			if (curosr.elem.compareTo(elem) > 0) {
				curosr = curosr.left;
			} else if (curosr.elem.compareTo(elem) < 0) {
				curosr = curosr.right;
			} else {
				return;
			}
		}
		// 确定最终落到哪个孩子
		if (parent.elem.compareTo(elem) > 0) {
			parent.left = new Node(elem);
		} else {
			parent.right = new Node(elem);
		}
		size++;
	}

	/**
	 * 先序遍历，借助栈，深度优先遍历
	 */
	public void preOrderTraverse() {
		// 中序、后序遍历愈加复杂
		Stack<Node> stack = new SeqListStack<>(size);
		Node cursor = root;
		// 根结点入栈
		stack.push(cursor);
		while (!stack.isEmpty()) {
			// 根结点出栈
			System.out.print(stack.pop().elem);
			// 右孩子入栈
			if (cursor.right != null) {
				stack.push(cursor.right);
			}
			// 左孩子入栈
			if (cursor.left != null) {
				stack.push(cursor.left);
			}
			// 取栈顶元素作根结点
			if (!stack.isEmpty()) {
				cursor = stack.peek();
			}
		}
	}
}
