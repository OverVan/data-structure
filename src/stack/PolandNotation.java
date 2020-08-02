package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 * 
 * @author Van
 */
public class PolandNotation {
	private Stack<String> operatorStack = new Stack<String>();// 运算符栈
	private String suffixExpression = ""; // 后缀表达式

	/**
	 * 将逆波兰表达式的字符依次存入ArrayList中
	 * 
	 * @param expression 逆波兰表达式
	 * @return 字符串列表
	 */
	public List<String> getListString(String expression) {
		List<String> list = new ArrayList<String>();
		String[] strings = expression.split(" ");
		for (String string : strings) {
			list.add(string);
		}
		return list;
	}

	/**
	 * 两个数的计算
	 * 
	 * @param number1  后入栈（先出栈）的数
	 * @param number2  先入栈（后出栈）的数
	 * @param operator 运算符
	 * @return 计算结果
	 */
	public int calculate(int number1, int number2, String operator) {
		// 结果
		int result = 0;
		if (operator.equals("+")) {
			result = number1 + number2;
		} else if (operator.equals("-")) {
			result = number2 - number1;// 注意顺序
		} else if (operator.equals("×")) {
			result = number1 * number2;
		} else if (operator.equals("/")) {
			result = number2 / number1;// 注意顺序
		} else {
			throw new RuntimeException("运算符有误");
		}
		return result;
	}

	/**
	 * 运算逆波兰表达式
	 * 
	 * @param list 承载表达式的字符串列表
	 * @return 运算结果
	 */
	public int operateExpression(List<String> list) {
		// 只需一个栈
		Stack<String> stack = new Stack<String>();
		// 遍历list
		for (String item : list) {
			// 当前字符串若是数字，则直接入栈，否则弹出俩数，运算之并将结果入栈
			if (item.matches("\\d+")) {// 利用正则表达式，匹配至少一位数字
				stack.push(item);
			} else {
				String result = String
						.valueOf(calculate(Integer.parseInt(stack.pop()), Integer.parseInt(stack.pop()), item));
				// 或不转成String，直接用stack.push("" + result);
				stack.push(result);
			}
		}
		// 返回最终结果
		return Integer.parseInt(stack.pop());
	}

	/**
	 * 返回运算符的优先级，用数字表示
	 * 
	 * @param operator 运算符
	 * @return 优先级数值
	 */
	public int priority(String operator) {
		// 基于ASCⅡ码整型与字符相映射
		if ("(".equals(operator)) {
			return 2;
		} else if ("×".equals(operator) || "/".equals(operator)) {
			return 1;
		} else if ("+".equals(operator) || "-".equals(operator) || ")".equals(operator)) {
			return 0;
		} else {
			// 暂定加减乘除和小括号
			return -1;
		}
	}

	/**
	 * 判断当前符号是否为运算符
	 * 
	 * @param symbol 符号
	 * @return true：是运算符；false：不是运算符
	 */
	public boolean isOperator(String symbol) {
		return "+".equals(symbol) || "-".equals(symbol) || "×".equals(symbol) || "/".equals(symbol)
				|| "(".equals(symbol) || ")".equals(symbol);
	}

	/**
	 * 运算符压栈的递归操作
	 * 
	 * @param string 运算符
	 */
	public void pushOperator(String string) {
		// 计算若干次后可能栈空，此时直接入运算符栈
		if (operatorStack.isEmpty()) {
			operatorStack.push(string);
		} else {
			// 将当前运算符与栈顶运算符进行优先级比较
			if (priority(string) <= priority(operatorStack.peek())) {// 当前运算符的优先级小于等于栈顶运算符
				if (")".equals(string)) {// 当前为右括号，那么须进行若干次出栈，然后让左括号也出栈
					while (!"(".equals(operatorStack.peek())) {
						suffixExpression += operatorStack.pop() + " ";
					}
					// 弹出左括号
					operatorStack.pop();
				} else {// 非右括号运算符遇左括号须直接入栈，而不是让它去参与计算
					if ("(".equals(operatorStack.peek())) {
						operatorStack.push(string);
					} else {// 非右括号运算符没遇到左括号则进行正常操作
						suffixExpression += operatorStack.pop() + " ";
						// 递归进行后续操作
						pushOperator(string);
					}
				}

			} else {// 当前运算符的优先级大于等于栈顶元素，直接入运算符栈
				operatorStack.push(string);
			}
		}
	}

	/**
	 * 将中缀表达式转为后缀表达式
	 * 
	 * @param infixExpression 中缀表达式。简便起见，输入的数字或运算符用空格分隔
	 * @return 对应的后缀表达式
	 */
	public String infixToSuffix(String infixExpression) {
		String[] strings = infixExpression.split(" ");
		// 遍历字符串数组
		for (String string : strings) {
			if (isOperator(string)) {// 当前字符串是运算符
				if (operatorStack.isEmpty()) {// 运算符栈为空则直接压栈
					operatorStack.push(string);
				} else {// 运算符栈不空，做进一步操作
					pushOperator(string);
				}
			} else {
				suffixExpression += string + " ";
			}
		}
		// 运算符栈不空则弹出所有
		while (!operatorStack.isEmpty()) {
			suffixExpression += operatorStack.pop() + " ";
		}
		// 删除最后一个空格
		suffixExpression.substring(0, suffixExpression.length() - 1);
		return suffixExpression;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PolandNotation polandNotation = new PolandNotation();
		// 定义一个中缀表达式
		String infixExpression = "1 + ( ( 2 + 3 ) × 4 ) - 5";
		String suffixExpresion = polandNotation.infixToSuffix(infixExpression);
		// 载入ArrayList
		List<String> list = polandNotation.getListString(suffixExpresion);
		System.out.println(list);
		// 运算
		int result = polandNotation.operateExpression(list);
		System.out.println("运算结果为：" + result);
	}

}
