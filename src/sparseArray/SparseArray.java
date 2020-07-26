package sparseArray;

/**
 * 稀疏数组
 * 
 * @author Van
 */
public class SparseArray {

	public static void main(String[] args) {
		// 原始棋盘二维数组：11行11列；0表示无子，1表示黑子，2表示白子
		int[][] chessArray = new int[11][11];
		// 放上棋子（后面的转化结果会随这里的更新而变化）
		chessArray[1][2] = 1;
		chessArray[2][3] = 2;
		chessArray[3][3] = 2;
		// 形象地把这盘棋打印出来
		System.out.println("原始的棋盘二维数组：");
		for (int[] row : chessArray) {
			for (int data : row)
				System.out.print(data + "  ");
			// 行末换行
			System.out.println();
		}
		// 计算棋子总数（非零值总数）
		int sum = 0;
		for (int[] row : chessArray) {
			for (int data : row) {
				if (data != 0)
					sum++;
			}
		}
		// 稀疏数组：行数为非零值个数加头行（记录行数、列数、棋子数）；列数固定为3（行号、列号、值）
		int[][] sparseArray = new int[sum + 1][3];
		// 根据原数组填充稀疏数组
		sparseArray[0][0] = 11;// 头行-棋子数
		sparseArray[0][1] = 11;// 头行-列数
		sparseArray[0][2] = sum;// 头行-值总数
		int count = 1;// 棋子信息从第2个子数组开始填
		for (int i = 0; i < chessArray.length; i++) {
			for (int j = 0; j < chessArray[0].length; j++) {
				if (chessArray[i][j] != 0) {
					sparseArray[count][0] = i;// 填入当前棋子行坐标
					sparseArray[count][1] = j;// 填入当前棋子列坐标
					sparseArray[count][2] = chessArray[i][j];// 填入当前棋子值
					count++;// 待填下一个子数组
				}
			}
		}
		System.out.println("转化成的稀疏数组：");
		for (int[] row : sparseArray) {
			for (int data : row)
				System.out.print(data + "  ");
			// 行末换行
			System.out.println();
		}
		// 将稀疏数组恢复成原数组
		int[][] returnArray = new int[sparseArray[0][0]][sparseArray[0][1]];
		for (int k = 1; k <= sparseArray[0][2]; k++)
			returnArray[sparseArray[k][0]][sparseArray[k][1]] = sparseArray[k][2];
		System.out.println("还原出来的数组：");
		for (int[] row : returnArray) {
			for (int data : row)
				System.out.print(data + "  ");
			// 行末换行
			System.out.println();
		}
		/**
		 * 对磁盘的稀疏数组存取待续
		 */
	}

}
