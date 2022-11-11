package tree.huffman;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;

class HuffmanTest {

	@Test
	void testHuffmanTree() {
		int[] array = { 1, 4, 3, 7, 8, 0, 5 };
		HuffmanTree huffmanTree = new HuffmanTree(array);
		huffmanTree.createHuffmanTree();
		// 先序遍历和中序遍历确定一棵树的结构
		huffmanTree.preOrderTraversal(huffmanTree.getRoot());
		huffmanTree.inOrderTraversal(huffmanTree.getRoot());
	}

	@Test
	void testHuffmanCode() {
		String content = "i like like like java do you like a java";
//		String content = "aabc";
		System.out.println("字符串长度：" + content.length());
		HuffmanCode huffmanCode = new HuffmanCode(content);
		String codes = null;
		try {
			codes = huffmanCode.encode();
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
//		huffmanCode.preOrderTraversal(huffmanCode.getRoot());
		System.out.println("编码长度：" + codes.length());
//		System.out.println("编码表：" + codes);
	}

}
