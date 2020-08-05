package recursion;

/**
 * 八皇后
 * 
 * @author Van
 */
public class EightQueen {
	private int[] array = new int[8];// 各皇后所占列
	private int count = 0;// 解的个数

	public int getCount() {
		return count;
	}

	/**
	 * 打印各行皇后所占列的情况
	 */
	public void print() {
		count++;// 解数加1
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	/**
	 * 检查第n个皇后目前摆放的位置对不对
	 * 
	 * @param n
	 * @return true：正确；false：错误
	 */
	public boolean judge(int n) {
		// 跟前面的比
		for (int i = 0; i < n; i++) {
			// 同列或行差等于列差（斜率为1，同一斜行）
			if (array[i] == array[n] || (n - i) == Math.abs(array[n] - array[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 摆放第n个皇后。循环加递归瞩目
	 * 
	 * @param n
	 */
	public void putN(int n) {
		// 若最后一个皇后已摆放完毕则返回
		if (n == 8) {
			print();
			return;
		}
		// 最后一个皇后尚未摆放完毕即八个皇后没全部放好
		// 对当前行的皇后试每一列
		for (int i = 0; i < 8; i++) {
			array[n] = i;
			// 当前试的这一列可行则摆下一个
			if (judge(n)) {
				putN(n + 1);
			}
			// 不可行就试下一列（进入下一次循环）
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EightQueen eightQueen = new EightQueen();
		// 从第1个皇后（第一行）开始放起
		eightQueen.putN(0);
		System.out.println("解的个数：" + eightQueen.getCount());
		// 分析结果：八皇后各不相同的解个数为92，但也可设定八皇后都一模一样，那么就有重复的（中心对称摆放）
	}

}
