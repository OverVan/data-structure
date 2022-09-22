package tree;

import seqList.DynamicSeqList;

/**
 * 键值对版的平衡二叉（搜索）树
 * 
 * @param <Key>   键类型，须可比较，不可取null
 * @param <Value> 值类型
 */
public class AVL<Key extends Comparable<Key>, Value> {
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
		private int height;

		public Node(Key key, Value value, Node left, Node right) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			// 只要构造一个结点，高度至少为1
			this.height = 1;
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
	 * 判断是否为AVL，检验平衡化有效
	 * 
	 * @return
	 */
	public boolean isBalanced() {
		return isBalanced(root);
	}

	/**
	 * 判断当前树是否为AVL
	 * 
	 * @param node 根结点
	 * @return
	 */
	private boolean isBalanced(Node node) {
		// 0-0
		if (node == null) {
			return true;
		}
		if (Math.abs(getBalanceFactory(node)) > 1) {
			return false;
		}
		// 依次向左右孩子递归
		return isBalanced(node.left) && isBalanced(node.right);
	}

	/**
	 * 判断是否为BST，检验平衡化后仍为BST
	 * 
	 * @return
	 */
	public boolean isBST() {
		DynamicSeqList<Key> keys = new DynamicSeqList<>(getSize());
		inOrderTraverse(root, keys);
		for (int i = 1; i < keys.getSize(); i++) {
			if (keys.get(i - 1).compareTo(keys.get(i)) >= 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 中序遍历当前AVL
	 * 
	 * @param node 根结点
	 * @param keys
	 */
	private void inOrderTraverse(Node node, DynamicSeqList<Key> keys) {
		if (node != null) {
			inOrderTraverse(node.left, keys);
			keys.addLast(node.key);
			inOrderTraverse(node.right, keys);
		}
	}

	/**
	 * 获取指定结点的高度
	 * 
	 * @param node 结点
	 * @return 高度
	 */
	public int getHeight(Node node) {
		return node == null ? 0 : node.height;
	}

	/**
	 * 获取指定结点的平衡因子
	 * 
	 * @param node 结点
	 * @return 平衡因子
	 */
	public int getBalanceFactory(Node node) {
		return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
	}

	/**
	 * 对当前左偏的树进行右旋以达到平衡
	 * 
	 * @param y 根结点
	 * @return 平衡后的根结点
	 */
	private Node rightRotate(Node y) {
		Node x = y.left;
		Node T3 = x.right;
		// 右旋
		x.right = y;
		y.left = T3;
		// 仅需从下到上更新y与x的高度
		y.height = Integer.max(getHeight(y.left), getHeight(y.right)) + 1;
		x.height = Integer.max(getHeight(x.left), getHeight(x.right)) + 1;
		return x;
	}

	/**
	 * 对当前右偏的树进行左旋以达到平衡
	 * 
	 * @param y 根结点
	 * @return 平衡后的根结点
	 */
	private Node leftRotate(Node y) {
		Node x = y.right;
		Node T2 = x.left;
		// 左旋
		x.left = y;
		y.right = T2;
		// 仅需从下到上更新y与x的高度
		y.height = Integer.max(getHeight(y.left), getHeight(y.right)) + 1;
		x.height = Integer.max(getHeight(x.left), getHeight(x.right)) + 1;
		return x;
	}

	/**
	 * 向当前AVL中添加结点
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
		// 维护高度：左右孩子高度中的最大值+1
		node.height = Integer.max(getHeight(node.left), getHeight(node.right)) + 1;
		// 回溯到第一个不平衡结点，只会是以下四种情况之一
		if (getBalanceFactory(node) == 2 && getBalanceFactory(node.left) >= 0) {
			// LL：对本结点进行右旋
			node = rightRotate(node);
		} else if (getBalanceFactory(node) == -2 && getBalanceFactory(node.right) <= 0) {
			// RR：对本结点进行左旋
			node = leftRotate(node);
		} else if (getBalanceFactory(node) == 2 && getBalanceFactory(node.left) <= 0) {
			// LR：先对左孩子进行左旋，再对本结点进行右旋
			node.left = leftRotate(node.left);
			node = rightRotate(node);
		} else if (getBalanceFactory(node) == -2 && getBalanceFactory(node.right) >= 0) {
			// RL：先对右孩子进行右旋，再对本结点进行左旋
			node.right = rightRotate(node.right);
			node = leftRotate(node);
		}
		return node;
	}

	/**
	 * 添加键值对
	 * 
	 * @param key   键
	 * @param value 值
	 */
	public void add(Key key, Value value) {
		root = add(root, key, value);
	}

	/**
	 * 获取当前AVL中最大键所属结点
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
	 * 删除当前BST中最大键所属结点并调整树结构，未维护平衡性
	 * 
	 * @param node 根结点
	 * @return 调整之后的根结点
	 */
	@Deprecated
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
	 * 从当前AVL中删除指定键所属结点并调整树结构
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
					return null;
				} else {
					node.key = findMax(node.left).key;
					// 这里也有删除操作，那么也要维护平衡性，要么修改removeMax的逻辑，要么复用remove，因最大元素已找到
					// node.left = removeMax(node.left);
					node.left = remove(node.left, node.key);
				}
			}
			// 与插入时的回溯同理
			// 维护高度：左右孩子高度中的最大值+1
			node.height = Integer.max(getHeight(node.left), getHeight(node.right)) + 1;
			// 回溯到第一个不平衡结点，只会是以下四种情况之一
			if (getBalanceFactory(node) == 2 && getBalanceFactory(node.left) >= 0) {
				// LL：对本结点进行右旋
				node = rightRotate(node);
			} else if (getBalanceFactory(node) == -2 && getBalanceFactory(node.right) <= 0) {
				// RR：对本结点进行左旋
				node = leftRotate(node);
			} else if (getBalanceFactory(node) == 2 && getBalanceFactory(node.left) <= 0) {
				// LR：先对左孩子进行左旋，再对本结点进行右旋
				node.left = leftRotate(node.left);
				node = rightRotate(node);
			} else if (getBalanceFactory(node) == -2 && getBalanceFactory(node.right) >= 0) {
				// RL：先对右孩子进行右旋，再对本结点进行左旋
				node.right = rightRotate(node.right);
				node = leftRotate(node);
			}
			return node;
		}
		return null;
	}

	public Value remove(Key key) {
		Node target = getNode(root, key);
		if (target == null) {
			// remove找不到靠null、false、抛异常等来反馈，不过找到情况下就得不到值了
			// throw new RuntimeException("键不存在");
			return null;
		}
		Value value = target.value;
		root = remove(root, key);
		return value;
	}

	/**
	 * 从当前AVL中以键获取结点
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

	public boolean contains(Key key) {
		return getNode(root, key) != null;
	}

	public Value get(Key key) {
		Node target = getNode(root, key);
		return target == null ? null : target.value;
	}

	public void set(Key key, Value value) {
		Node target = getNode(root, key);
		if (target == null) {
			throw new RuntimeException("键不存在");
		}
		target.value = value;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 中序遍历当前AVL
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
		res.append("MapAVL [");
		if (!isEmpty()) {
			// 利用中序遍历
			inOrderTraverse(root, res);
			String substring = res.substring(0, res.length() - 2);
			res.append(substring);
		}
		res.append("]");
		return res.toString();
	}
}
