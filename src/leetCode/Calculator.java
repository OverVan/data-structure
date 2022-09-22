package leetCode;

import java.util.Scanner;

import stack.SeqListStack;

/**
 * 中缀表达式计算器。重点：高优先级的运算符要先算；左后括号不是纯运算符，需成对消失；多位数的拼接
 * 
 * @author Van
 */
public class Calculator {
	private SeqListStack<Integer> numberStack;// 数栈
	private SeqListStack<Character> operatorStack;// 运算符栈
	private int size;// 表达式字符串长度

	/**
	 * 初始化两个栈，容量尽量大
	 * 
	 * @param size
	 */
	public Calculator(int size) {
		this.size = size;
		this.numberStack = new SeqListStack<>(this.size);
		this.operatorStack = new SeqListStack<>(this.size);
	}

	/**
	 * 返回运算符的优先级，用数字表示
	 * 
	 * @param operator 运算符
	 * @return 优先级数值
	 */
	public int priority(int operator) {
		// 基于ASCⅡ码整型与字符相映射
		if (operator == '(') {
			return 2;
		} else if (operator == '*' || operator == '/') {
			return 1;
		} else if (operator == '+' || operator == '-' || operator == ')') {
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
	public boolean isOperator(char symbol) {
		return symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/' || symbol == '(' || symbol == ')';
	}

	/**
	 * 两个数的计算
	 * 
	 * @param number1  后入栈（先出栈）的数
	 * @param number2  先入栈（后出栈）的数
	 * @param operator 运算符
	 * @return 计算结果
	 */
	public int calculate(int number1, int number2, int operator) {
		// 结果
		int result = 0;
		switch (operator) {
		case '+':
			result = number1 + number2;
			break;
		case '-':
			result = number2 - number1;// 注意顺序
			break;
		case '*':
			result = number1 * number2;
			break;
		case '/':
			result = number2 / number1;// 注意顺序
			break;
		default:
			break;
		}
		return result;
	}

	/**
	 * 运算符压栈的递归操作
	 * 
	 * @param ch 运算符
	 */
	public void pushOperator(char ch) {
		// 计算若干次后可能栈空，此时直接入运算符栈
		if (operatorStack.isEmpty()) {
			operatorStack.push(ch);
		} else {
			// 将当前运算符与栈顶运算符进行优先级比较
			if (priority(ch) <= priority(operatorStack.peek())) {// 当前运算符的优先级小于等于栈顶运算符
				if (ch == ')') {// 当前为右括号，那么须进行若干次计算，然后跟左括号一起消失
					while (operatorStack.peek() != '(') {
						numberStack.push(calculate(numberStack.pop(), numberStack.pop(), operatorStack.pop()));
					}
					// 弹出左括号
					operatorStack.pop();
				} else {// 非右括号运算符遇左括号须直接入栈，而不是让它去参与计算
					if (operatorStack.peek() == '(') {
						operatorStack.push(ch);
					} else {// 非右括号运算符没遇到左括号则进行正常操作
						int result = calculate(numberStack.pop(), numberStack.pop(), operatorStack.pop());
						numberStack.push(result);
						// 递归进行后续操作
						pushOperator(ch);
					}
				}

			} else {// 当前运算符的优先级大于等于栈顶元素，直接入运算符栈
				operatorStack.push(ch);
			}
		}
	}

	/**
	 * 运算整个表达式
	 * 
	 * @param expression 表达式字符串
	 * @return 运算结果
	 */
	public int operateExpression(String expression) {
		// 拼位数的字符串及转化成的数值
		String numberString = "";
		int number = 0;
		// 对表达式字符串进行逐字符操作
		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			if (isOperator(ch)) {// 当前字符是运算符
				if (operatorStack.isEmpty()) {// 运算符栈为空则直接压栈
					operatorStack.push(ch);
				} else {// 运算符栈不空，做进一步操作
					pushOperator(ch);
				}
			} else {// 当前字符是1位数字
				numberString += ch;
				// 判断i+1和最后一个下标的关系
				if (i + 1 < expression.length() - 1) {
					// 遇到运算符字符就不拼了，转成数字并入数栈
					if (isOperator(expression.substring(i + 1, i + 2).charAt(0))) {
						number = Integer.parseInt(numberString);
						numberStack.push(number);
						// 清空拼子准备拼下一个多位数
						numberString = "";
					} // 此if条件不满足则保持现状，准备拼入下一个数字字符
				} else if (i + 1 == expression.length() - 1) {
					if (isOperator(expression.substring(i + 1).charAt(0))) {
						number = Integer.parseInt(numberString);
						numberStack.push(number);
						numberString = "";
					}
				} else {// 确定最后一位是数字，后面没字符了，则直接入栈
					number = Integer.parseInt(numberString);
					numberStack.push(number);
					numberString = "";
				}
			}
		}
		// 随后需检查运算符栈空否，不空则算完，直至运算符栈空
		while (!operatorStack.isEmpty()) {
			numberStack.push(calculate(numberStack.pop(), numberStack.pop(), operatorStack.pop()));
		}
		// 最后数栈仅剩一个元素，即最终结果
		return numberStack.pop();
	}

	public static void main(String[] args) {
		// 可人为地将数字或运算符用空格隔开，这样就不用麻烦地拼数字了
		System.out.println("输入一行四则运算式：");
		// 输入流
		Scanner scanner = new Scanner(System.in);
		// 用户输入
		String expression = scanner.next();
		// 计算器
		Calculator calculator = new Calculator(expression.length());
		System.out.println("计算结果为：" + calculator.operateExpression(expression));
		// 关闭输入流
		scanner.close();
	}

}
