package leetCode;

/**
 * 剑指 Offer 15. 二进制中1的个数
 * https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 */
public class HammingWeight {
	/**
	 * 遍历字符串，朴素想法，效率较低
	 * 
	 * @param n
	 * @return
	 */
	public static int iterateString(int n) {
		int count = 0;
		String binString = Integer.toBinaryString(n);
		for (int i = 0; i < binString.length(); i++) {
			if (binString.charAt(i) == '1') {
				count++;
			}
		}
		return count;
	}

	/**
	 * 逐位与“1”相与
	 * 
	 * @param n
	 * @return
	 */
	public static int shiftAndAnd(int n) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if ((n & 1 << i) != 0) {
				count++;
			}
		}
		return count;
	}

	/**
	 * API
	 * 
	 * @param n
	 * @return
	 */
	public static int api(int n) {
		return Integer.bitCount(n);
	}

	public static void main(String[] args) {
		System.out.println(iterateString(128));
		System.out.println(shiftAndAnd(11));
		System.out.println(api(11));
	}
}
