package leetCode;

/**
 * 7. 整数反转 https://leetcode.cn/problems/reverse-integer/
 */
public class ReverseInteger {
	/**
	 * 转字符串，反转，再转回去
	 * 
	 * @param x
	 * @return
	 */
	public static int reverseString(int x) {
		String xString;
		StringBuilder stringBuilder;
		int reversed;
		if (x < 0) {
			// 取绝对值
			x = Math.abs(x);
			xString = String.valueOf(x);
			stringBuilder = new StringBuilder(xString.length());
			// 可能溢出
			try {
				// 添回负号
				reversed = Integer.parseInt("-" + stringBuilder.append(xString).reverse().toString());
			} catch (NumberFormatException e) {
				reversed = 0;
			}
		} else {
			xString = String.valueOf(x);
			stringBuilder = new StringBuilder(xString.length());
			try {
				reversed = Integer.parseInt(stringBuilder.append(xString).reverse().toString());
			} catch (NumberFormatException e) {
				reversed = 0;
			}
		}
		return reversed;
	}

	/**
	 * 从低位到高位遍历，把反转数算出来
	 * 
	 * @param x
	 * @return
	 */
	public static int calculate(int x) {
		// 此前用字符串求位数，这里用算的
		int digit = 0;
		// 这里也可能溢出，后续同理
		while (x / (long) Math.pow(10, digit) != 0) {
			digit++;
		}
		// 防溢出
		long reversed = 0;
		if (x < 0) {
			if (x == Integer.MIN_VALUE) {
				return 0;
			}
			// 取绝对值
			x = Math.abs(x);
			for (int i = 0; i < digit; i++) {
				int radix = (int) (x % (long) Math.pow(10, i + 1) / (long) Math.pow(10, i));
				// 一溢出就返回0
				if (-(reversed * 10 + radix) < Integer.MIN_VALUE) {
					return 0;
				}
				// 算还是按正数算，不怕正数溢出
				reversed = reversed * 10 + radix;
			}
			// 添回负号
			reversed = -reversed;
		} else {
			for (int i = 0; i < digit; i++) {
				int radix = (int) (x % (long) Math.pow(10, i + 1) / (long) Math.pow(10, i));
				if (reversed * 10 + radix > Integer.MAX_VALUE) {
					return 0;
				}
				reversed = reversed * 10 + radix;
			}
		}
		return (int) reversed;
	}

	public static void main(String[] args) {
		System.out.println(reverseString(120));
		System.out.println(calculate(-123));
	}
}
