package tree;

import java.util.Random;

import org.junit.jupiter.api.Test;

class TreeTest {

	@Test
	void testBinaryTree() {
		// 搭建二叉树
		BinaryTree<Integer> binaryTree = new BinaryTree<>();
		BinaryTree<Integer>.Node root = binaryTree.new Node(1);
		BinaryTree<Integer>.Node node1 = binaryTree.new Node(3);
		BinaryTree<Integer>.Node node2 = binaryTree.new Node(5);
		BinaryTree<Integer>.Node node3 = binaryTree.new Node(4);
		BinaryTree<Integer>.Node node4 = binaryTree.new Node(6);
		BinaryTree<Integer>.Node node5 = binaryTree.new Node(2);
		root.setLeft(node1);
		root.setRight(node2);
		node1.setLeft(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		binaryTree.setRoot(root);
		// 先序遍历
		binaryTree.preOrderTraverse();
		System.out.println();
		// 中序遍历
		binaryTree.inOrderTraverse();
		System.out.println();
		// 后序遍历
		binaryTree.postOrderTraverse();
		System.out.println();
		// 先序查找
		System.out.println(binaryTree.preOrderSearch(6));
		// 删除
		binaryTree.inOrderRemove(6);
//		binaryTree.inOrderRemove(1);
		binaryTree.inOrderTraverse();
	}

	@Test
	void testBinarySearchTree() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Integer[] nums = new Integer[] { 5, 3, 6, 8, 4, 2, 2, 7, 1 };
		for (Integer num : nums) {
			bst.add(num);
		}
		bst.preOrderTraverse();
		System.out.println();
		System.out.println(bst);
		bst.levelOrderTraverse();
		System.out.println();
//		System.out.println(bst.removeMin());
//		System.out.println(bst.removeMax());
//		bst.inOrderTraverse();
		bst.remove(5);
		bst.inOrderTraverse();
		System.out.println(bst.getSize());
	}

	@Test
	void testBST() {
		BST<Integer> bst = new BST<>();
		Integer[] nums = new Integer[] { 5, 3, 6, 8, 4, 2, 2, 7, 1 };
		for (Integer num : nums) {
			bst.add(num);
		}
		bst.preOrderTraverse();
	}

	@Test
	void testAVL() {
		int optCount = 1000;
		AVL<Integer, Object> avl = new AVL<>();
		Random random = new Random(10);
		// 不断添加不断保持平衡与BST特性
		for (int i = 0; i < optCount; i++) {
			int nextInt = random.nextInt(100);
			avl.add(nextInt, "avl");
			if (!avl.isBalanced()) {
				System.out.println("unbalanced");
			}
			if (!avl.isBST()) {
				System.out.println("not BST");
			}
		}
		System.out.println(avl);
		// 不断删除不断保持平衡与BST特性
		for (int i = 0; i < 100; i++) {
			avl.remove(i);
			if (!avl.isBalanced()) {
				System.out.println("unbalanced");
			}
			if (!avl.isBST()) {
				System.out.println("not BST");
			}
		}
		System.out.println(avl);
	}

	@Test
	void testRedBlackTree() {
		int optCount = 30000000;
//		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		AVL<Integer, Object> avl = new AVL<>();
		RedBlackTree<Integer, Object> rbTree = new RedBlackTree<>();
		long begin = System.currentTimeMillis();
		for (int i = 0; i < optCount; i++) {
			avl.add(i, "map");
		}
		for (int i = 0; i < optCount; i++) {
			avl.get(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("AVL: " + (end - begin) / 1E3);
		begin = System.currentTimeMillis();
		for (int i = 0; i < optCount; i++) {
			rbTree.add(i, "map");
		}
		for (int i = 0; i < optCount; i++) {
			// 相较于AVL，查询不占优势，因为深度更大，优势主要在增删
			rbTree.contains(i);
		}
		end = System.currentTimeMillis();
		System.out.println("RedBlackTree: " + (end - begin) / 1E3);
//		begin = System.currentTimeMillis();
//		for (int i = 0; i < optCount; i++) {
//			bst.add(i);
//		}
//		for (int i = 0; i < optCount; i++) {
//			bst.contains(i);
//		}
//		end = System.currentTimeMillis();
//		System.out.println("BinarySearchTree: " + (end - begin) / 1E3);
	}

}
