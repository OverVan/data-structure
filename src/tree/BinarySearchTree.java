package tree;

import queue.Queue;
import queue.SeqListQueue;
import seqList.DynamicSeqList;

/**
 * 二叉搜索（排序）树 无重复元素，方法统一基于递归
 * 
 * @param <Elem> 元素类型，须可比较，暂不允许元素为null
 */
public class BinarySearchTree<Elem extends Comparable<Elem>> {
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
			if (elem == null) {
				throw new IllegalArgumentException("元素不能为null");
			}
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
	 * 验证BST有效性
	 * 
	 * @return
	 */
	public boolean isBST() {
		DynamicSeqList<Elem> keys = new DynamicSeqList<>(getSize());
		inOrderTraverse(root, keys);
		for (int i = 1; i < keys.getSize(); i++) {
			if (keys.get(i - 1).compareTo(keys.get(i)) >= 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 中序遍历当前BST
	 * 
	 * @param node 根结点
	 * @param keys
	 */
	private void inOrderTraverse(Node node, DynamicSeqList<Elem> keys) {
		if (node != null) {
			inOrderTraverse(node.left, keys);
			keys.addLast(node.elem);
			inOrderTraverse(node.right, keys);
		}
	}

	/**
	 * 向当前BST中添加结点
	 * 
	 * @param node 根结点
	 * @param elem 元素
	 * @return 新根结点
	 */
	private Node add(Node node, Elem elem) {
		if (node == null) {
			// 挂上
			node = new Node(elem);
			size++;
			return node;
		}
		if (node.elem.compareTo(elem) > 0) {
			// 往左子树递归
			node.left = add(node.left, elem);
		} else if (node.elem.compareTo(elem) < 0) {
			// 往右子树递归
			node.right = add(node.right, elem);
		}
		// 本BST定义不含元素相等的情况，即单调递增，相等则返回原结点
		return node;
	}

	/**
	 * 添加元素
	 * 
	 * @param elem 元素
	 */
	public void add(Elem elem) {
		root = add(root, elem);
	}

	/**
	 * 判断当前BST是否包含指定元素
	 * 
	 * @param node 根结点
	 * @param elem 元素
	 * @return
	 */
	private boolean contains(Node node, Elem elem) {
		if (node == null) {
			return false;
		}
		if (node.elem.compareTo(elem) > 0) {
			return contains(node.left, elem);
		} else if (node.elem.compareTo(elem) < 0) {
			return contains(node.right, elem);
		} else {
			// 相等即包含
			return true;
		}
	}

	/**
	 * 判断是否包含指定元素
	 * 
	 * @param elem 元素
	 * @return
	 */
	public boolean contains(Elem elem) {
		return contains(root, elem);
	}

	/**
	 * 专门服务于映射，从当前BST中获取指定元素
	 * 
	 * @param node 根结点
	 * @param elem 元素
	 * @return
	 */
	private Elem get(Node node, Elem elem) {
		if (node == null) {
			return null;
		}
		if (node.elem.compareTo(elem) > 0) {
			return get(node.left, elem);
		} else if (node.elem.compareTo(elem) < 0) {
			return get(node.right, elem);
		} else {
			return node.elem;
		}
	}

	/**
	 * 专门服务于映射，获取指定元素
	 * 
	 * @param elem
	 * @return
	 */
	public Elem get(Elem elem) {
		return get(root, elem);
	}

	/**
	 * 先序遍历当前BST
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
	 * 中序遍历当前BST
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
	 * 中序遍历，递增顺序遍历
	 */
	public void inOrderTraverse() {
		inOrderTraverse(root);
	}

	/**
	 * 中序遍历当前BST
	 * 
	 * @param node 根结点
	 * @param res
	 */
	private void inOrderTraverse(Node node, StringBuilder res) {
		if (node != null) {
			inOrderTraverse(node.left, res);
			res.append(node).append(", ");
			inOrderTraverse(node.right, res);
		}
	}

	/**
	 * 后序遍历当前BST
	 * 
	 * @param node 根结点
	 */
	private void postOrderTraverse(Node node) {
		if (node != null) {
			// 中序遍历左子树
			postOrderTraverse(node.left);
			// 中序遍历右子树
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
	 * 层序遍历，借助队列，广度优先遍历
	 */
	public void levelOrderTraverse() {
		Queue<Node> queue = new SeqListQueue<>(size);
		Node cursor = root;
		// 根结点入队
		queue.enqueue(cursor);
		while (!queue.isEmpty()) {
			// 根结点出队
			System.out.print(queue.dequeue());
			// 左孩子入队
			if (cursor.left != null) {
				queue.enqueue(cursor.left);
			}
			// 右孩子入队
			if (cursor.right != null) {
				queue.enqueue(cursor.right);
			}
			// 取队首元素作根结点
			if (!queue.isEmpty()) {
				cursor = queue.getFront();
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("BinarySearchTree:\n");
		generateBSTString(root, 0, res);
		return res.toString();
	}

	private void generateBSTString(Node node, int depth, StringBuilder res) {
		// null也打出来，便于区分左右子树
		if (node == null) {
			res.append(generateDepthString(depth)).append("null\n");
		} else {
			// 先序遍历
			res.append(generateDepthString(depth)).append(node.elem).append("\n");
			generateBSTString(node.left, depth + 1, res);
			generateBSTString(node.right, depth + 1, res);
		}
	}

	private String generateDepthString(int depth) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			res.append("-");
		}
		return res.toString();
	}

	/**
	 * 基于中序遍历的toString
	 * 
	 * @return
	 */
	public String toInOrderTraverseString() {
		StringBuilder res = new StringBuilder();
		res.append("BinarySearchTree [");
		if (!isEmpty()) {
			inOrderTraverse(root, res);
		}
		String substring = res.substring(0, res.length() - 2);
		res = new StringBuilder();
		res.append(substring).append("]");
		return res.toString();
	}

	/**
	 * 获取最小元素所属结点
	 * 
	 * @param node
	 * @return
	 */
	private Node findMin(Node node) {
		// 一直向左走直到左孩子为null
		if (node.left == null) {
			return node;
		}
		return findMin(node.left);
	}

	/**
	 * 获取最小元素
	 * 
	 * @return 元素
	 */
	public Elem findMin() {
		if (isEmpty()) {
			throw new RuntimeException("BST为空");
		}
		return findMin(root).elem;
	}

	/**
	 * 获取当前BST最大元素所属结点
	 * 
	 * @param node 根结点
	 * @return
	 */
	private Node findMax(Node node) {
		// 一直向右走直到右孩子为null
		if (node.right == null) {
			return node;
		}
		return findMax(node.right);
	}

	/**
	 * 获取最大元素
	 * 
	 * @return 元素
	 */
	public Elem findMax() {
		if (isEmpty()) {
			throw new RuntimeException("BST为空");
		}
		return findMax(root).elem;
	}

	/**
	 * 删除当前BST中最小元素所属结点并调整树结构
	 * 
	 * @param node 根结点
	 * @return 调整之后的根结点
	 */
	private Node removeMin(Node node) {
		if (node.left == null) {
			// 若有右孩子，则替换自己，否则置null
			if (node.right != null) {
				return node.right;
			} else {
				return null;
			}
		}
		// 仅左孩子改变
		node.left = removeMin(node.left);
		// 返回变化之后的根结点
		return node;
	}

	/**
	 * 删除并获取最小元素
	 * 
	 * @return
	 */
	public Elem removeMin() {
		Elem min = findMin();
		root = removeMin(root);
		size--;
		return min;
	}

	/**
	 * 删除当前BST中最大元素所属结点并调整树结构
	 * 
	 * @param node 根结点
	 * @return 调整之后的根结点
	 */
	private Node removeMax(Node node) {
		if (node.right == null) {
			// 若有左孩子，则替换自己，否则置null
			if (node.left != null) {
				return node.left;
			} else {
				return null;
			}
		}
		// 仅右孩子改变
		node.right = removeMax(node.right);
		return node;
	}

	/**
	 * 删除并获取最大元素
	 * 
	 * @return
	 */
	public Elem removeMax() {
		Elem max = findMax();
		root = removeMax(root);
		size--;
		return max;
	}

	/**
	 * 删除当前BST中指定元素所属结点并调整树结构
	 * 
	 * @param node 根结点
	 * @param elem 元素
	 * @return 调整之后的根结点
	 */
	private Node remove(Node node, Elem elem) {
		if (node != null) {
			// 递归查找
			if (node.elem.compareTo(elem) > 0) {
				node.left = remove(node.left, elem);
			} else if (node.elem.compareTo(elem) < 0) {
				node.right = remove(node.right, elem);
			} else {
				size--;
				if (node.left != null && node.right == null) {
					// 仅有左孩子，则左孩子替换自己
					node = node.left;
				} else if (node.left == null && node.right != null) {
					// 仅有右孩子，则右孩子替换自己
					node = node.right;
				} else if (node.left == null && node.right == null) {
					// 没孩子，则置null
					node = null;
				} else {
					// 左右孩子都有，则元素替换成右子树中的最小元素或左子树中的最大元素
					node.elem = findMin(node.right).elem;
					// 并删除对应结点、调整子树结构
					node.right = removeMin(node.right);
				}
			}
			return node;
		}
		return null;
	}

	/**
	 * 删除指定元素
	 * 
	 * @param elem 元素
	 */
	public void remove(Elem elem) {
		root = remove(root, elem);
	}
}
