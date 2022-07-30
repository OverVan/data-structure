package tree.huffman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 赫夫曼（编码）树的结点
 * 
 * @author Van
 */
class HuffmanCodeNode implements Comparable<HuffmanCodeNode> {
	private Byte data;// 数据
	private int weight;// 权值（频次）
	private HuffmanCodeNode left;// 左子结点
	private HuffmanCodeNode right;// 右子结点

	public HuffmanCodeNode(byte data, int weight) {
		// 仅叶子结点的data非null
		this.data = data;
		this.weight = weight;
	}

	public HuffmanCodeNode(int weight, HuffmanCodeNode left, HuffmanCodeNode right) {
		// data为null的结点是非叶子结点
		this.data = null;
		this.weight = weight;
		this.left = left;
		this.right = right;
	}

	public Byte getData() {
		return data;
	}

	public int getWeight() {
		return weight;
	}

	public HuffmanCodeNode getLeft() {
		return left;
	}

	public HuffmanCodeNode getRight() {
		return right;
	}

	@Override
	public String toString() {
		if (data == null) {
			return "HuffmanCodeNode [data=" + data + ", weight=" + weight + "]";
		} else {
			return "HuffmanCodeNode [data=" + data + "/" + (char) data.byteValue() + ", weight=" + weight + "]";
		}
	}

	@Override
	public int compareTo(HuffmanCodeNode o) {
		// 减号表按权值从小到大
		return weight - o.weight;
	}

}

/**
 * 哈夫曼（编码）树
 * 
 * @author Van
 */
public class HuffmanCode {
	private String array;// 原字符串
	private List<HuffmanCodeNode> nodeList;// 结点列表
	private HuffmanCodeNode root;// 根结点
	// 赫夫曼码表。一个字符（字节）映射为一个0-1字符串
	private Map<Byte, String> huffmanCodes;

	public HuffmanCode(String array) {
		this.array = array;
		this.huffmanCodes = new HashMap<Byte, String>();
	}

	public HuffmanCodeNode getRoot() {
		return root;
	}

	/**
	 * 填充结点列表
	 */
	public void fillNodeList() {
		// 将字符串转为ASCⅡ码数组
		byte[] bytes = array.getBytes();
		// 用map记录每个字符（一个字符对应一个字节，一个整数）及其频次
		Map<Byte, Integer> nodeMap = new HashMap<Byte, Integer>();
		for (byte data : bytes) {
			Integer weight = nodeMap.get(data);
			if (weight == null) {// 添加
				nodeMap.put(data, 1);
			} else {// 覆盖
				nodeMap.put(data, weight + 1);
			}
		}
		// 将每个字符及其权值封装成结点并存入列表
		List<HuffmanCodeNode> nodeList = new ArrayList<HuffmanCodeNode>();
		for (Map.Entry<Byte, Integer> entry : nodeMap.entrySet()) {
			nodeList.add(new HuffmanCodeNode(entry.getKey(), entry.getValue()));
		}
		this.nodeList = nodeList;
	}

	/**
	 * 搭赫夫曼树
	 */
	public void createHuffmanTree() {
		// 只剩一个元素标志着所有结点安排完毕
		while (nodeList.size() > 1) {
			// 将诸结点按值从小到大排序（依赖Comparable接口的实现）
			Collections.sort(nodeList);
			// 取最小的两个结点搭起子树
			HuffmanCodeNode left = nodeList.get(0);
			HuffmanCodeNode right = nodeList.get(1);
			HuffmanCodeNode parentNode = new HuffmanCodeNode(left.getWeight() + right.getWeight(), left, right);
			// 删除刚刚用掉的两个结点
			nodeList.remove(left);
			nodeList.remove(right);
			// 将新子树（结点）加入列表
			nodeList.add(parentNode);
		}
		// 确定根结点
		root = nodeList.get(0);
	}

	/**
	 * 先序遍历赫夫曼树
	 * 
	 * @param node
	 */
	public void preOrderTraversal(HuffmanCodeNode node) {
		if (node != null) {
			System.out.println(node);
			preOrderTraversal(node.getLeft());
			preOrderTraversal(node.getRight());
		}
	}

	/**
	 * 中序遍历赫夫曼树
	 * 
	 * @param node
	 */
	public void inOrderTraversal(HuffmanCodeNode node) {
		if (node != null) {
			inOrderTraversal(node.getLeft());
			System.out.println(node);
			inOrderTraversal(node.getRight());
		}
	}

	/**
	 * 创建赫夫曼码表（有问题）
	 * 
	 * @param node     拼接0-1码串
	 * @param pathCode 路径标号：0或1
	 */
	public void createHuffmanCodes(HuffmanCodeNode node, String pathCode, StringBuilder stringBuilder) {
		// 接续前面的路径拼接
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		stringBuilder2.append(pathCode);
		// 先检查当前结点是否为null
		if (node != null) {
			// 再检查当前结点是否为叶子结点，是则将字节值及其编码存入码表
			if (node.getData() == null) {
				// 向左递归
				createHuffmanCodes(node.getLeft(), "0", stringBuilder2);
				// 向右递归
				createHuffmanCodes(node.getRight(), "1", stringBuilder2);
			} else {
				huffmanCodes.put(node.getData(), stringBuilder2.toString());
			}
		}
	}

	/**
	 * 根据赫夫曼码表，返回各元素对应的码串排列成字符串后再拆成的字节数组
	 * 
	 * @return
	 */
	public byte[] zip() {
		for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
			System.out.println(entry.getKey() + "/" + (char) entry.getKey().byteValue() + " " + entry.getValue());
		}
		// 原字符串转字节数组
		byte[] bytes = array.getBytes();
		// 根据赫夫曼码表将原字节（字符）对应的编码拼成长字符串
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : bytes) {
			// get方法返回的是该字节（符）经赫夫曼编码得到的的0-1码串（字符串）
			stringBuilder.append(huffmanCodes.get(b));
		}
		// 目标字节数组的长度
		int length = (stringBuilder.length() + 7) / 8;
		byte[] huffmanBytes = new byte[length];
		for (int i = 0, index = 0; i < stringBuilder.length(); i += 8, index++) {
			// 当前所截字节的字符串形式（8位0-1串）
			String huffmanByteStr = null;
			if (i + 8 > stringBuilder.length()) {
				// end默认为末尾下标
				huffmanByteStr = stringBuilder.substring(i);
			} else {
				huffmanByteStr = stringBuilder.substring(i, i + 8);
			}
			// 将字符串形式的8位0-1串转为字节存入目标数组
			huffmanBytes[index] = (byte) Integer.parseInt(huffmanByteStr, 2);
		}
		return huffmanBytes;
	}

	/**
	 * 赫夫曼编码
	 * 
	 * @return 经赫夫曼编码后得到的字节数组
	 */
	public byte[] huffmanZip() {
		// 赫夫曼编码
		fillNodeList();
		createHuffmanTree();
		createHuffmanCodes(root, "", new StringBuilder());// 根结点前无标号
		return zip();
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String content = "i like like like java do you like a java";
		System.out.println("原始长度：" + content.length());
		HuffmanCode huffmanCode = new HuffmanCode(content);
		byte[] zip = huffmanCode.huffmanZip();
		huffmanCode.preOrderTraversal(huffmanCode.getRoot());
		System.out.println("赫夫曼编码表：" + Arrays.toString(zip) + " 长度：" + zip.length);
		System.out.println("压缩率：" + (double) (content.length() - zip.length) / content.length());
	}

}
