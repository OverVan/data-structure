package leetCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 14. 最长公共前缀 https://leetcode.cn/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {
	/**
	 * O(n*m)
	 * 
	 * @param strings
	 * @return
	 */
	public static String everyString(String... strings) {
		List<String> strs = Arrays.asList(strings);
		// 理想条件下最长前缀即数组内最短字符串
		int min = strs.stream().map(String::length).min(Integer::compareTo).orElse(-1);
		// 随便取一个长度等于最小长度的字符串
		String shortest = strs.stream().filter(string -> string.length() == min).findFirst().orElse("");
		StringBuilder res = new StringBuilder();
		// 遍历该字符串的每一个字符，用当前字符匹配所有元素对应位置上的字符
		int[] pesudoI = new int[1]; // 局部内部类的特性，在这倒成了麻烦了
		for (int i = 0; i < shortest.length(); i++) {
			char c = shortest.charAt(i);
			pesudoI[0] = i;
			if (strs.stream().allMatch(string -> string.charAt(pesudoI[0]) == c)) {
				res.append(c);
			} else {
				break;
			}
		}
		return res.toString();
	}

	/**
	 * 这个原理还没想明白 O(nlogn+k)?
	 * 
	 * @param strings
	 * @return
	 */
	public static String maxAndMin(String... strings) {
		List<String> strs = Arrays.asList(strings);
		// 排序，耗时
		List<String> sorted = strs.stream().sorted(String::compareTo).collect(Collectors.toList());
		String minString = sorted.get(0);
		String maxString = sorted.get(sorted.size() - 1);
		StringBuilder res = new StringBuilder();
		// 只需比较两个最值，最值跟长短无关
		for (int i = 0; i < minString.length(); i++) {
			if (minString.charAt(i) == maxString.charAt(i)) {
				res.append(minString.charAt(i));
			} else {
				break;
			}
		}
		return res.toString();
	}

	public static void main(String[] args) {
		System.out.println(everyString("flower", "flight", "flow"));
		System.out.println(maxAndMin("dog", "docar", "dorace", "doeverything"));
		System.out.println(maxAndMin("shit"));
	}
}
