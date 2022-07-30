package tree.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树的结点，需实现Comparable接口以支持sort方法
 * 
 * @author Van
 */
class HuffmanNode implements Comparable<HuffmanNode> {
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
	public int compareTo(HuffmanNode o) {
		return value - o.value;
	}
}

/**
 * 赫夫曼树
 * 
 * @author Van
 */
public class HuffmanTree {
	private int[] array;// 元素数组
	private HuffmanNode root;// 根结点

	public HuffmanTree(int[] array) {
		this.array = array;
	}

	public HuffmanNode getRoot() {
		return root;
	}

	/**
	 * 搭建赫夫曼树
	 */
	public void createHuffmanTree() {
		// 将数值数组里的元素映射到结点列表中
		List<HuffmanNode> nodes = new ArrayList<HuffmanNode>();
		for (int value : array) {
			nodes.add(new HuffmanNode(value));
		}
		// 只剩一个元素标志着所有结点安排完毕
		while (nodes.size() > 1) {
			// 将诸结点按值从小到大排序（依赖Comparable接口的实现）
			Collections.sort(nodes);
			// 取最小的两个结点搭起子树
			HuffmanNode parentNode = new HuffmanNode(nodes.get(0).getValue() + nodes.get(1).getValue(), nodes.get(0),
					nodes.get(1));
			// 删除刚刚用掉的两个结点
			nodes.remove(0);
			nodes.remove(0);
			// 将新子树（结点）加入列表
			nodes.add(parentNode);
		}
		// 确定根结点
		root = nodes.get(0);
	}

	/**
	 * 先序遍历赫夫曼树
	 * 
	 * @param node
	 */
	public void preOrderTraversal(HuffmanNode node) {
		if (node != null) {
			System.out.println(node.getValue());
			preOrderTraversal(node.getLeft());
			preOrderTraversal(node.getRight());
		}
	}

	/**
	 * 中序遍历赫夫曼树
	 * 
	 * @param node
	 */
	public void inOrderTraversal(HuffmanNode node) {
		if (node != null) {
			inOrderTraversal(node.getLeft());
			System.out.println(node.getValue());
			inOrderTraversal(node.getRight());
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 1, 4, 3, 7, 8, 0, 5 };
		HuffmanTree huffmanTree = new HuffmanTree(array);
		// 搭建赫夫曼树
		huffmanTree.createHuffmanTree();
		// 先序遍历和中序遍历确定一棵树的结构
		huffmanTree.preOrderTraversal(huffmanTree.getRoot());
		huffmanTree.inOrderTraversal(huffmanTree.getRoot());
	}

}
