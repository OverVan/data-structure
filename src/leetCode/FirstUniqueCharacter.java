package leetCode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 387. 字符串中的第一个唯一字符；剑指 Offer 50. 第一个只出现一次的字符
 * https://leetcode.cn/problems/first-unique-character-in-a-string/
 * https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 */
public class FirstUniqueCharacter {
	/**
	 * 甚至用数组记录字符并频率
	 * 
	 * @param string
	 * @return
	 */
	public static int useArray(String string) {
		int[] freqs = new int[26];
		// 利用字母的数值性，将a-z映射为0-25
		for (int i = 0; i < string.length(); i++) {
			// 当前字符对应位置上频率自增
			freqs[string.charAt(i) - 'a']++;
		}
		for (int i = 0; i < string.length(); i++) {
			if (freqs[string.charAt(i) - 'a'] == 1) {
				return i;
			}
		}
		return -1;
	}

	public static int useMap(String string) {
		// 使用LinkedHashMap，便于遍历，由于强盗第一个，BST、AVL、红黑树是不合适的
		Map<Character, Integer> freqs = new LinkedHashMap<>(26);
		for (int i = 0; i < string.length(); i++) {
			Character ch = string.charAt(i);
			Integer freq = freqs.get(string.charAt(i));
			if (freq == null) {
				freqs.put(ch, 1);
			} else {
				freqs.put(ch, freq + 1);
			}
		}
		for (Entry<Character, Integer> entry : freqs.entrySet()) {
			if (entry.getValue() == 1) {
				return string.indexOf(entry.getKey());
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(useArray("loveleetcode"));
		System.out.println(useMap("loveleetcode"));
	}
}
