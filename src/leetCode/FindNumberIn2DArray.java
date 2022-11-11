package leetCode;

/**
 * 剑指 Offer 04. 二维数组中的查找
 * https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 */
public class FindNumberIn2DArray {
	/**
	 * 将右上角视作BST的根结点 O(n+m)
	 * 
	 * @param matrix n行m列
	 * @param target
	 * @return
	 */
	public static boolean find(int[][] matrix, int target) {
		if (matrix.length == 0) {
			return false;
		}
		// 行号
		int row = 0;
		if (matrix[0].length == 0) {
			return false;
		}
		// 列号
		int col = matrix[0].length - 1;
		while (row < matrix.length && col >= 0) {
			int root = matrix[row][col];
			if (target < root) {
				col--;
			} else if (target > root) {
				row++;
			} else {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 },
				{ 10, 13, 14, 17, 24 }, { 18, 21, 23, 26, 30 } };
		int[][] blank = new int[][] {};
		int[][] blankCol = new int[][] { {}, {} };
		System.out.println(find(matrix, 5));
		System.out.println(find(blank, 5));
		System.out.println(find(blankCol, 5));
	}
}
