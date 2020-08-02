package recursion;

/**
 * 递归的小例子
 * 
 * @author Van
 */
public class Introduction {
	/**
	 * 依次打印2到n之间的整数
	 * 
	 * @param n
	 */
	public static void test(int n) {
		if (n > 2) {
			test(n - 1);
		}
		System.out.println("n=" + n);
	}

	/**
	 * 求n的阶乘
	 * 
	 * @param n
	 * @return
	 */
	public static int factorial(int n) {
		if (n == 1) {
			return n;
		} else {
			return factorial(n - 1) * n;
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		test(6);
		System.out.println("5的阶乘为：" + factorial(5));
	}

}
