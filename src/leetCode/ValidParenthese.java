package leetCode;

import java.util.Stack;

/**
 * 20. 有效的括号
 */
public class ValidParenthese {
	public static boolean isValid(String string) {
		// 引入Java API中的栈结构
		Stack<Character> stack = new Stack<>();
		// 遍历字符串
		for (int i = 0; i < string.length(); i++) {
			char bracket = string.charAt(i);
			// 当前字符为左括号则入栈
			if (bracket == '(' || bracket == '[' || bracket == '{') {
				stack.push(bracket);
			} else {
				// 当前字符为右括号则出栈并匹配
				if (stack.isEmpty()) {
					// 无栈可出，对应右括号多了或右括号起始
					return false;
				}
				char pop = stack.pop().charValue();
				if (bracket == ')' && pop != '(' || bracket == ']' && pop != '[' || bracket == '}' && pop != '{') {
					return false;
				}
			}
		}
		// 检查有无剩余的左括号
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		String string = "{[{}]}";
		System.out.println(isValid(string));
	}
}
