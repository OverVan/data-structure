package leetCode;

/**
 * 9. 回文数 https://leetcode.cn/problems/palindrome-number/
 */
public class Palindrome {
	/**
	 * 成对比较低位数与高位数
	 * 
	 * @param x
	 * @return
	 */
	public static boolean matchRadix(int x) {
		// 负数一定非回文数
		if (x < 0) {
			return false;
		}
		// 位数
		int digit = String.valueOf(x).length();
		int highRadix = 0;
		int lowRadix = 0;
		// 单数位数与双数位数的退出条件统一
		for (int i = 0; i < digit / 2; i++) {
			highRadix = x % (int) Math.pow(10, digit - i) / (int) Math.pow(10, digit - i - 1);
			lowRadix = x % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
			if (highRadix != lowRadix) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 成对比较低位字符与高位字符
	 * 
	 * @param x
	 * @return
	 */
	public static boolean matchRadixChar(int x) {
		String xString = String.valueOf(x);
		// 负数一定非回文数
		if (xString.startsWith("-")) {
			return false;
		}
		// 双指针
		int low = 0;
		int high = xString.length() - 1;
		// 单数位数与双数位数的退出条件统一
		while (low < xString.length() / 2) {
			if (xString.charAt(low) != xString.charAt(high)) {
				return false;
			}
			low++;
			high--;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(matchRadix(10101));
		System.out.println(matchRadixChar(1221));
	}
}
