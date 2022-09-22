package tree;

/**
 * 键值对版的红黑树
 * 
 * @param <Key>   键类型，必须可比，不可取null
 * @param <Value> 值类型
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {
	// 红结点标识
	private static final boolean RED = true;
	// 黑结点标识
	private static final boolean BLACK = false;

	/**
	 * 结点
	 */
	private class Node {
		// 键
		private Key key;
		// 值
		private Value value;
		// 左孩子
		private Node left;
		// 右孩子
		private Node right;
		// 颜色
		private boolean color;

		public Node(Key key, Value value, Node left, Node right, boolean color) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			this.color = color;
		}

		public Node(Key key, Value value) {
			// 默认为红结点，在后续操作中视情况变成黑的
			this(key, value, null, null, RED);
		}

		@Override
		public String toString() {
			return key + ": " + value;
		}
	}

	// 根结点
	private Node root;
	// 键值对总数
	private int size;

	/**
	 * 判断指定结点是红结点还是黑结点
	 * 
	 * @param node 结点
	 * @return
	 */
	public boolean isRed(Node node) {
		return node == null ? false : node.color;
	}

	/**
	 * 向当前红黑树中添加结点
	 * 
	 * @param node  根结点
	 * @param key   键
	 * @param value 值
	 * @return 新根结点
	 */
	private Node add(Node node, Key key, Value value) {
		if (node == null) {
			node = new Node(key, value);
			size++;
			return node;
		}
		if (node.key.compareTo(key) > 0) {
			node.left = add(node.left, key, value);
		} else if (node.key.compareTo(key) < 0) {
			node.right = add(node.right, key, value);
		} else {
			node.value = value;
		}
		if (isRed(node.right) && !isRed(node.left)) {
			node = leftRotate(node);
		}
		if (isRed(node.left) && isRed(node.left.left)) {
			node = rightRotate(node);
		}
		if (isRed(node.left) && isRed(node.right)) {
			flipColor(node);
		}
		return node;
	}

	/**
	 * 对当前红黑树进行左旋
	 * 
	 * @param node 根结点
	 * @return
	 */
	private Node leftRotate(Node node) {
		Node x = node.right;
		node.right = x.left;
		x.left = node;
		x.color = node.color;
		node.color = RED;
		return x;
	}

	/**
	 * 对当前红黑树进行右旋
	 * 
	 * @param node 根结点
	 * @return
	 */
	private Node rightRotate(Node node) {
		Node x = node.left;
		node.left = x.right;
		x.right = node;
		x.color = node.color;
		node.color = RED;
		return x;
	}

	/**
	 * 对结点进行颜色翻转
	 * 
	 * @param node
	 */
	private void flipColor(Node node) {
		node.color = RED;
		node.left.color = BLACK;
		node.right.color = BLACK;
	}

	/**
	 * 添加键值对
	 */
	public void add(Key key, Value value) {
		root = add(root, key, value);
		// 保持根结点黑色
		root.color = BLACK;
	}

	/**
	 * 从当前红黑树中以键获取结点
	 * 
	 * @param node 根结点
	 * @param key  键
	 * @return 结点
	 */
	private Node getNode(Node node, Key key) {
		if (node == null) {
			return null;
		}
		if (node.key.compareTo(key) > 0) {
			return getNode(node.left, key);
		}
		if (node.key.compareTo(key) < 0) {
			return getNode(node.right, key);
		}
		return node;
	}

	/**
	 * 判断是否含有指定键值对
	 * 
	 * @param key 键
	 * @return
	 */
	public boolean contains(Key key) {
		return getNode(root, key) != null;
	}
}
