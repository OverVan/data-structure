package leetCode;

/**
 * 387. 字符串中的第一个唯一字符
 */
public class FirstUniqueCharacter {
//不难想到用红黑树、AVL、映射等记录字符并频率，这里用最简便的数组

	public static int firstUniqChar(String string) {
		int[] freq = new int[26];
		// 利用字母的数值性，将a-z映射为0-25
		for (int i = 0; i < string.length(); i++) {
			// 当前字符对应位置上频率自增
			freq[string.charAt(i) - 'a']++;
		}
		for (int i = 0; i < string.length(); i++) {
			if (freq[string.charAt(i) - 'a'] == 1) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(firstUniqChar("loveleetcode"));
		System.out.println(firstUniqChar("leetcode"));
		System.out.println(firstUniqChar("aabb"));
	}
}
