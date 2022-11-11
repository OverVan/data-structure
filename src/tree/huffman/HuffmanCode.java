package tree.huffman;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 哈夫曼树
 */
public class HuffmanCode {
	/**
	 * 哈夫曼结点
	 */
	private class HuffmanNode implements Comparable<HuffmanNode> {
		// 数据
		private Character data;
		// 权值-频次
		private int weight;
		// 左孩子
		private HuffmanNode left;
		// 右孩子
		private HuffmanNode right;

		public HuffmanNode(Character data, int weight) {
			this.data = data;
			this.weight = weight;
		}

		public HuffmanNode(int weight, HuffmanNode left, HuffmanNode right) {
			// 仅叶子结点的data非null
			this.data = null;
			this.weight = weight;
			this.left = left;
			this.right = right;
		}

		public Character getData() {
			return data;
		}

		public int getWeight() {
			return weight;
		}

		public HuffmanNode getLeft() {
			return left;
		}

		public HuffmanNode getRight() {
			return right;
		}

		@Override
		public String toString() {
			return "HuffmanNode [data=" + data + "]";
		}

		@Override
		public int compareTo(HuffmanNode other) {
			// 结点按权值从小到大排列
			return weight - other.weight;
		}

	}

	// 原字符串
	private String string;
	// 结点列表
	private List<HuffmanNode> nodeList;
	// 根结点
	private HuffmanNode root;
	// 赫夫曼码表 一个字符映射为一个0/1序列
	private Map<Character, String> huffmanCodes;

	public HuffmanCode(String array) {
		this.string = array;
		this.huffmanCodes = new HashMap<Character, String>();
	}

	public HuffmanNode getRoot() {
		return root;
	}

	/**
	 * 填充结点列表
	 * 
	 * @throws UnsupportedEncodingException
	 */
	private void fillNodeList() throws UnsupportedEncodingException {
		// 将字符串转为字符数组
		char[] chars = new char[string.length()];
		string.getChars(0, string.length(), chars, 0);
		// 用映射记录每个字符（一个字符对应一个字节）及其频次
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char data : chars) {
			Integer weight = map.get(data);
			// 添加或覆盖
			if (weight == null) {
				map.put(data, 1);
			} else {
				map.put(data, weight + 1);
			}
		}
		// 将每个字符及其权值封装成结点并存入列表
		nodeList = new ArrayList<HuffmanNode>();
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			nodeList.add(new HuffmanNode(entry.getKey(), entry.getValue()));
		}
	}

	/**
	 * 搭建哈夫曼树
	 */
	private void createHuffmanTree() {
		// 只剩一个元素标志着所有结点安排完毕
		while (nodeList.size() > 1) {
			// 将诸结点按data从小到大排序
			Collections.sort(nodeList);
			// 取最小的两个结点搭建父（新）结点
			HuffmanNode left = nodeList.get(0);
			HuffmanNode right = nodeList.get(1);
			HuffmanNode parentNode = new HuffmanNode(left.getWeight() + right.getWeight(), left, right);
			// 删除刚刚用掉的两个结点
			nodeList.remove(left);
			nodeList.remove(right);
			// 将新子树（结点）加入列表
			nodeList.add(parentNode);
		}
		// 重新确定根结点
		root = nodeList.get(0);
	}

	/**
	 * 先序遍历当前哈夫曼树
	 * 
	 * @param node 根结点
	 */
	public void preOrderTraversal(HuffmanNode node) {
		if (node != null) {
			System.out.print(node + " ");
			preOrderTraversal(node.getLeft());
			preOrderTraversal(node.getRight());
		}
	}

	/**
	 * 中序遍历当前赫夫曼树
	 * 
	 * @param node 根结点
	 */
	public void inOrderTraversal(HuffmanNode node) {
		if (node != null) {
			inOrderTraversal(node.getLeft());
			System.out.print(node + " ");
			inOrderTraversal(node.getRight());
		}
	}

	/**
	 * 创建哈夫曼码表
	 * 
	 * @param node     哈夫曼树（根结点）
	 * @param pathCode 0或1
	 * @param sb
	 */
	private void createHuffmanCodes(HuffmanNode node, char pathCode, StringBuilder sb) {
		StringBuilder newSb = null;
		if (node == root) {
			newSb = new StringBuilder();
		} else {
			// 接续前驱序列拼接，注意stringBuilder.append(pathCode)是错的
			newSb = new StringBuilder(sb);
			newSb.append(pathCode);
		}
		// 当前结点是否为null
		if (node != null) {
			// 当前结点是否为叶子结点
			if (node.getData() == null) {
				// 向左递归
				createHuffmanCodes(node.getLeft(), '0', newSb);
				// 向右递归
				createHuffmanCodes(node.getRight(), '1', newSb);
			} else {
				// 是则将其数据及编码存入码表
				huffmanCodes.put(node.getData(), newSb.toString());
			}
		}
	}

	/**
	 * 根据原字符串及码表得到映射的序列
	 * 
	 * @return
	 */
	private String unzip() {
		StringBuilder stringBuilder = new StringBuilder(string.length());
		for (int i = 0; i < string.length(); i++) {
			stringBuilder.append(huffmanCodes.get(string.charAt(i)));
		}
		return stringBuilder.toString();
	}

	/**
	 * 编码得到0/1序列
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String encode() throws UnsupportedEncodingException {
		fillNodeList();
		createHuffmanTree();
		createHuffmanCodes(root, 'x', null);
		return unzip();
	}
}
