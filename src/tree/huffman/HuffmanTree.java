package tree.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 哈夫曼树
 */
public class HuffmanTree {
	/**
	 * 哈夫曼结点，须实现Comparable接口以支持sort方法
	 */
	private class HuffmanNode implements Comparable<HuffmanNode> {
		private int value;
		private HuffmanNode left;
		private HuffmanNode right;

		public HuffmanNode(int value) {
			this.value = value;
		}

		public HuffmanNode(int value, HuffmanNode left, HuffmanNode right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}

		public int getValue() {
			return value;
		}

		public HuffmanNode getLeft() {
			return left;
		}

		public HuffmanNode getRight() {
			return right;
		}

		@Override
		public String toString() {
			return "HuffmanNode [value=" + value + "]";
		}

		@Override
		public int compareTo(HuffmanNode other) {
			return value - other.value;
		}
	}

	// 数据-一堆整数
	private int[] array;
	// 根结点
	private HuffmanNode root;

	public HuffmanTree(int[] array) {
		this.array = array;
	}

	public HuffmanNode getRoot() {
		return root;
	}

	/**
	 * 搭建哈夫曼树
	 */
	public void createHuffmanTree() {
		// 将数值数组里的元素映射到结点列表中
		List<HuffmanNode> nodes = new ArrayList<HuffmanNode>();
		for (int value : array) {
			nodes.add(new HuffmanNode(value));
		}
		// 只剩一个元素标志着所有结点安排完毕
		while (nodes.size() > 1) {
			// 将诸结点按value从小到大排序
			Collections.sort(nodes);
			// 取最小的两个结点搭起父（新）结点
			HuffmanNode parentNode = new HuffmanNode(nodes.get(0).getValue() + nodes.get(1).getValue(), nodes.get(0),
					nodes.get(1));
			// 删除刚刚用掉的两个结点
			nodes.remove(0);
			nodes.remove(0);
			// 将新子树（结点）加入列表
			nodes.add(parentNode);
		}
		// 重新确定根结点
		root = nodes.get(0);
	}

	/**
	 * 先序遍历当前哈夫曼树
	 * 
	 * @param node 根结点
	 */
	public void preOrderTraversal(HuffmanNode node) {
		if (node != null) {
			System.out.print(node.getValue() + " ");
			preOrderTraversal(node.getLeft());
			preOrderTraversal(node.getRight());
		}
	}

	/**
	 * 中序遍历当前哈夫曼树
	 * 
	 * @param node 根结点
	 */
	public void inOrderTraversal(HuffmanNode node) {
		if (node != null) {
			inOrderTraversal(node.getLeft());
			System.out.print(node.getValue() + " ");
			inOrderTraversal(node.getRight());
		}
	}
}
