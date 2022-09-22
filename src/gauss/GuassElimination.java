package gauss;

import java.util.Arrays;

/**
 * 高斯消元法
 * 
 * @author Van
 */
public class GuassElimination {
	// 元的个数
	private int n;
	// 系数矩阵
	private double[][] A;
	// 等号右边的值向量
	private double[] b;
	// 解向量
	private double[] x;

	public GuassElimination(int n, double[][] A, double[] b) {
		this.n = n;
		this.A = A;
		this.b = b;
		x = new double[n];
	}

	/**
	 * 打印系数矩阵和值向量
	 */
	public void printMatrix() {
		for (double[] row : A) {
			for (double elem : row) {
				System.out.print(elem + " ");
			}
			System.out.println();
		}
		System.out.println(Arrays.toString(b));
	}

	/**
	 * 解线性方程组
	 */
	public void solveLinearEquations() {
		printMatrix();
		// 化上三角 中括号内注意减1，其他地方不用改
		for (int k = 1; k <= n - 1; k++) {
			for (int i = k + 1; i <= n; i++) {
				// 将归零项原值存起来
				double former = A[i - 1][k - 1];
				for (int j = k; j <= n; j++) {
					A[i - 1][j - 1] -= A[k - 1][j - 1] * former / A[k - 1][k - 1];
				}
				// 与j无关
				b[i - 1] -= b[k - 1] * former / A[k - 1][k - 1];
			}
			printMatrix();
		}
		// 回代 中括号内注意减1，其他地方不用改
		x[n - 1] = b[n - 1] / A[n - 1][n - 1];
		for (int p = n - 1; p >= 1; p--) {
			for (int q = p + 1; q <= n; q++) {
				b[p - 1] = b[p - 1] - x[q - 1] * A[p - 1][q - 1];
			}
			x[p - 1] = b[p - 1] / A[p - 1][p - 1];
		}
		// 打印解向量
		for (int m = 0; m < x.length; m++) {
			System.out.println("x_" + (m + 1) + " = " + x[m]);
		}
	}

	public static void main(String[] args) {
		double[][] A = { { 1, 1, 1 }, { 1, 2, 1 }, { 1, 3, 3 } };
		double[] b = { 3, 4, 7 };
		GuassElimination guassElimination = new GuassElimination(3, A, b);
		guassElimination.solveLinearEquations();
	}

}
