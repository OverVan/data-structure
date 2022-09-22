package map;

/**
 * 基于二叉搜索树的键存储有序映射
 * 
 * @param <Key>   键类型，不可取null
 * @param <Value> 值类型
 */
public class BinarySearchTreeMap<Key extends Comparable<Key>, Value> implements Map<Key, Value> {
//相当于重复造了一个BST，简便起见，可把<Key, Value>封装成Elem，参见BSTMap
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

		public Node(Key key, Value value, Node left, Node right) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
		}

		public Node(Key key, Value value) {
			this(key, value, null, null);
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
	 * 向当前BST中添加结点
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
			// 键相等则修改值
			node.value = value;
		}
		return node;
	}

	/**
	 * 平均O(logn)；最坏O(n)；最准确O(depth)；最好O(1) 下同
	 */
	@Override
	public void add(Key key, Value value) {
		root = add(root, key, value);
	}

	/**
	 * 获取当前BST中最大键所属结点
	 * 
	 * @param node 根结点
	 * @return
	 */
	private Node findMax(Node node) {
		if (node.right == null) {
			return node;
		}
		return findMax(node.right);
	}

	/**
	 * 删除当前BST中最大键所属结点并调整树结构
	 * 
	 * @param node 根结点
	 * @return 调整之后的根结点
	 */
	private Node removeMax(Node node) {
		if (node.right == null) {
			if (node.left != null) {
				return node.left;
			} else {
				return null;
			}
		}
		node.right = removeMax(node.right);
		return node;
	}

	/**
	 * 从当前BST中删除指定键所属结点并调整树结构
	 * 
	 * @param node 根结点
	 * @param key  键
	 * @return 调整之后的根结点
	 */
	private Node remove(Node node, Key key) {
		if (node != null) {
			if (node.key.compareTo(key) > 0) {
				node.left = remove(node.left, key);
			} else if (node.key.compareTo(key) < 0) {
				node.right = remove(node.right, key);
			} else {
				size--;
				if (node.left != null && node.right == null) {
					node = node.left;
				} else if (node.left == null && node.right != null) {
					node = node.right;
				} else if (node.left == null && node.right == null) {
					node = null;
				} else {
					node.key = findMax(node.left).key;
					node.left = removeMax(node.left);
				}
			}
			return node;
		}
		return null;
	}

	@Override
	public Value remove(Key key) {
		Node target = getNode(root, key);
		if (target == null) {
			return null;
		}
		Value value = target.value;
		root = remove(root, key);
		return value;
	}

	/**
	 * 从当前BST中以键获取结点
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

	@Override
	public boolean contains(Key key) {
		return getNode(root, key) != null;
	}

	@Override
	public Value get(Key key) {
		Node target = getNode(root, key);
		// 找没找到要靠返回值来反馈，这里向Java API看齐，没找到用null反馈，会与值也为null的情况冲突，只能自己控制不要添加值为null的键值对
		return target == null ? null : target.value;
	}

	@Override
	public void set(Key key, Value value) {
		Node target = getNode(root, key);
		if (target == null) {
			throw new RuntimeException("键不存在");
		}
		target.value = value;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 中序遍历当前BST
	 * 
	 * @param node 根结点
	 * @param res
	 */
	private void inOrderTraverse(Node node, StringBuilder res) {
		if (node != null) {
			// 中序遍历左子树
			inOrderTraverse(node.left, res);
			res.append(node).append(", ");
			// 中序遍历右子树
			inOrderTraverse(node.right, res);
		}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("BSTMap [");
		if (!isEmpty()) {
			// 利用中序遍历
			inOrderTraverse(root, res);
		}
		String substring = res.substring(0, res.length() - 2);
		res = new StringBuilder();
		res.append(substring).append("]");
		return res.toString();
	}
}
