package leetCode;

import java.util.LinkedHashSet;

/**
 * 3. 无重复字符的最长子串
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubstring {
	public static int slideWindow(String string) {
		LinkedHashSet<Character> set = new LinkedHashSet<>(string.length());
		int right = 0;
		int left = 0;
		int max = 0;
		while (right < string.length()) {
			if (!set.contains(string.charAt(right))) {
				set.add(string.charAt(right));
				right++;
			} else {
				set.remove(string.charAt(left));
				left++;
			}
			max = Math.max(max, set.size());
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(slideWindow("abcabcbb"));
	}
}
