package leetCode;

/**
 * 367. 有效的完全平方数 https://leetcode.cn/problems/valid-perfect-square/
 */
public class PerfectSquare {
	/**
	 * 若num是完全平方数，则存在正整数n使得1+2+···+(2n-1)=num 时间O(n)
	 * 
	 * @param num
	 * @return
	 */
	public static boolean sumOdd(int num) {
		int sum = 1;
		while (num > 0) {
			num -= sum;
			sum += 2;
		}
		return num == 0 ? true : false;
	}

	/**
	 * 二分法 时间O(logn)
	 * 
	 * @return
	 */
	public static boolean binary(int num) {
		int low = 1;
		int high = num >= 4 ? num / 2 : num;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			int quotient = num / mid;
			if (quotient == mid) {
				return num % quotient == 0;
			} else if (quotient < mid) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return false;
	}

	/**
	 * 逐数算二次方 时间O(n)
	 * 
	 * @param num
	 * @return
	 */
	public static boolean square(int num) {
		int n = 1;
		int limit = num >= 4 ? num / 2 : num;
		while (n <= limit) {
			if (n * n == num) {
				return true;
			}
			n++;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(sumOdd(121));
		System.out.println(binary(25));
		System.out.println(square(36));
	}

}
