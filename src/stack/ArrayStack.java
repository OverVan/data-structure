package stack;

import java.util.Scanner;

/**
 * 用数组表示的栈
 * 
 * @author Van
 */
public class ArrayStack {
	private int maxSize;// 栈的容量
	private int[] stack;// 承载栈的数组
	private int top;// 栈顶下标

	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		this.stack = new int[maxSize];
		this.top = -1;
	}

	/**
	 * 判断栈是否满
	 * 
	 * @return
	 */
	public boolean isFull() {
		return top == maxSize - 1;
	}

	/**
	 * 判断栈是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return top == -1;
	}

	/**
	 * 入栈
	 * 
	 * @param value 压栈的数值
	 */
	public void push(int value) {
		if (isFull()) {
			System.out.println("栈已满，不可再进行入栈操作");
			return;
		}
		stack[++top] = value;
	}

	/**
	 * 出栈
	 * 
	 * @return 出栈的数值
	 */
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("栈为空，无元素出栈");
		}
		return stack[top--];
	}

	/**
	 * 只取栈顶元素而不出栈
	 * 
	 * @return
	 */
	public int peek() {
		return stack[top];
	}

	/**
	 * 从栈顶往下显示栈
	 */
	public void showStack() {
		if (isEmpty()) {
			System.out.println("栈为空");
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d] = %d\n", i, stack[i]);
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 初始化栈
		ArrayStack arrayStack = new ArrayStack(2);
		// 输入流
		Scanner scanner = new Scanner(System.in);
		// 用户输入的选项
		String key = "";
		// 循环控制变量
		boolean flag = true;
		// 操作循环
		while (flag) {
			System.out.println("\n操作菜单");
			System.out.println("s(show)：打印栈");
			System.out.println("push：入栈");
			System.out.println("pop：出栈");
			System.out.println("e(exit)：退出");
			System.out.println("输入选项：");
			// 获取输入的选项
			key = scanner.next();
			switch (key) {
			case "s":
				arrayStack.showStack();
				break;
			case "push":
				System.out.println("输入一个整数：");
				arrayStack.push(scanner.nextInt());
				break;
			case "pop":
				try {
					System.out.println("出栈元素为：" + arrayStack.pop());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "e":
				// 关闭输入流
				scanner.close();
				// 退出循环
				flag = false;
				// 退出分支
				break;
			default:
				scanner.close();
				// 退出循环
				flag = false;
				// 退出分支
				break;
			}
		}
		System.out.println("程序正常结束");
	}
}
