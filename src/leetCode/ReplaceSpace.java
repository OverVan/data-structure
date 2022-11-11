package leetCode;

/**
 * 剑指 Offer 05. 替换空格 https://leetcode.cn/problems/ti-huan-kong-ge-lcof/
 */
public class ReplaceSpace {
	/**
	 * 时间上O(n)，空间上O(n)
	 * 
	 * @param string
	 * @return
	 */
	public static String replaceSpace(String string) {
		StringBuilder stringBuilder = new StringBuilder(string.length() * 3);
		for (char c : string.toCharArray()) {
			if (c == ' ') {
				stringBuilder.append("%20");
			} else {
				stringBuilder.append(c);
			}
		}
		return stringBuilder.toString();
	}

	public static void main(String[] args) {
		System.out.println(replaceSpace("we are happy"));
	}
}
