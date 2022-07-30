package tree;

/**
 * 结点
 * 
 * @author Van
 */
class ThreadedNode {
	private int data;// 数据
	private ThreadedNode left;// 左孩子结点
	private ThreadedNode right;// 右孩子结点
	// left和right的标记：0表左子树或右子树；1表前驱或后继结点
	private int leftType;
	private int rightType;

	public ThreadedNode(int data) {
		this.data = data;
	}

	public ThreadedNode getLeft() {
		return left;
	}

	public void setLeft(ThreadedNode left) {
		this.left = left;
	}

	public ThreadedNode getRight() {
		return right;
	}

	public void setRight(ThreadedNode right) {
		this.right = right;
	}

	public int getData() {
		return data;
	}

	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
	}

	public int getLeftType() {
		return leftType;
	}

	public int getRightType() {
		return rightType;
	}

	@Override
	public String toString() {
		return "ThreadedNode [data=" + data + "]";
	}
}

/**
 * 线索二叉树
 * 
 * @author Van
 */
public class ThreadedBinaryTree {
	private ThreadedNode root;// 根结点
	private ThreadedNode previous;// 当前结点的前驱结点

	public ThreadedBinaryTree(ThreadedNode root) {
		this.root = root;
	}

	public ThreadedNode getRoot() {
		return root;
	}

	public void setRoot(ThreadedNode root) {
		this.root = root;
	}

	/**
	 * 中序线索化
	 * 
	 * @param node 当前被线索化的结点
	 */
	public void threadNodes(ThreadedNode node) {
		if (node == null) {
			return;
		}
		// 中序线索化左子树
		threadNodes(node.getLeft());
		// 中序线索化当前结点
		if (node.getLeft() == null) {
			node.setLeft(previous);
			node.setLeftType(1);
		}
		if (previous != null && previous.getRight() == null) {
			previous.setRight(node);
			previous.setRightType(1);
		}
		previous = node;
		// 中序线索化右子树
		threadNodes(node.getRight());
	}

	/**
	 * 以线索化的方式遍历二叉树
	 */
	public void traversal() {
		ThreadedNode node = root;
		while (node != null) {
			while (node.getLeftType() == 0) {
				node = node.getLeft();
			}
			System.out.println(node);
			while (node.getRightType() == 1) {
				node = node.getRight();
				System.out.println(node);
			}
			node = node.getRight();
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 手动搭二叉树
		ThreadedNode root = new ThreadedNode(1);
		ThreadedNode node1 = new ThreadedNode(3);
		ThreadedNode node2 = new ThreadedNode(5);
		ThreadedNode node3 = new ThreadedNode(4);
		ThreadedNode node4 = new ThreadedNode(6);
		ThreadedNode node5 = new ThreadedNode(2);
		root.setLeft(node1);
		root.setRight(node2);
		node1.setLeft(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
		threadedBinaryTree.threadNodes(threadedBinaryTree.getRoot());
//		System.out.println(node3.getLeft());
//		System.out.println(node3.getRight());
		// 线索化之后才能用线索化的方式遍历
		threadedBinaryTree.traversal();
	}
}
