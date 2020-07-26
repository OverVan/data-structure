package queue;

import java.util.Scanner;

/**
 * 循环队列（环形队列）
 * 
 * @author Van
 */
public class CircleQueue {
	private int maxSize;// 队列最大容量
	private int front;// 队头-队首元素的下标
	private int rear;// 队尾-队尾元素下标+1
	private int[] arrayQueue;// 队列数组

	/**
	 * 初始化循环队列
	 * 
	 * @param maxSize
	 */
	public CircleQueue(int maxSize) {
		this.maxSize = maxSize;
		arrayQueue = new int[this.maxSize];
		front = 0;
		rear = 0;
	}

	/**
	 * 判断队列是否满
	 * 
	 * @return
	 */
	public boolean isFull() {
		// 因预留一个位置，元素数达到maxSize-1便表示满
		return (rear + 1) % maxSize == front;
	}

	/**
	 * 判断队列是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		// front追rear
		return front == rear;
	}

	/**
	 * 入队
	 * 
	 * @param element
	 */
	public void addQueue(int element) {
		// 队列不满才能入队
		if (!isFull()) {
			// 插入元素
			arrayQueue[rear] = element;
			// 队尾后移一位
			rear = (rear + 1) % maxSize;
		} else {
			System.out.println("队列已满，不能插入");
		}
	}

	/**
	 * 出队
	 * 
	 * @return
	 */
	public int getQueue() {
		// 队列非空才能出队
		if (!isEmpty()) {
			// 取队头元素
			int value = arrayQueue[front];
			// 队头后移一位
			front = (front + 1) % maxSize;
			return value;
		} else {
			throw new RuntimeException("队列为空，无法取数据");
		}
	}

	/**
	 * 展示队列的所有元素
	 */
	public void showQueueAll() {
		if (isEmpty()) {
			System.out.println("队列为空");
		} else {
			for (int i = 0; i < maxSize; i++) {
				System.out.printf("queue[ %d ] = %d\n", i, arrayQueue[i]);
			}
		}
	}

	/**
	 * 展示逻辑上的队列元素
	 */
	public void showQueueReal() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空");
		} else {
			for (int i = front; i < front + getSize(); i++) {
				System.out.printf("queue[ %d ] = %d\n", i % maxSize, arrayQueue[i % maxSize]);
			}
		}
	}

	/**
	 * 队列长度（逻辑上元素个数）
	 * 
	 * @return
	 */
	public int getSize() {
		return (rear - front + maxSize) % maxSize;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 初始化队列数组
		CircleQueue queue = new CircleQueue(5);// 逻辑上最多存4个
		// 用户输入的选项
		Scanner scanner = new Scanner(System.in);
		char key;
		// 循环参数
		boolean flag = true;
		while (flag) {
			System.out.println("\n操作菜单：");
			System.out.println("s(show)：展示物理上完整的队列");
			System.out.println("r(real)：展示逻辑上的队列");
			System.out.println("a(add)：元素入队");
			System.out.println("g(get)：元素出队");
			System.out.println("e(exit)：退出程序");
			System.out.println("选择：");
			// 输入选项
			key = scanner.next().charAt(0);
			// 根据选项做出下一步队列操作
			switch (key) {
			// 展示完整队列
			case 's':
				queue.showQueueAll();
				break;
			// 展示逻辑队列
			case 'r':
				try {
					queue.showQueueReal();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			// 入队
			case 'a':
				System.out.println("输入待入队的元素：");
				int element = scanner.nextInt();
				queue.addQueue(element);
				break;
			// 出队
			case 'g':
				try {
					System.out.println("出队元素为：" + queue.getQueue());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			// 退出
			case 'e':
				// 关闭输入流
				scanner.close();
				// 退出循环
				flag = false;
				break;
			// 默认退出
			default:
				flag = false;
				break;
			}
		}
		System.out.println("程序正常退出");
	}
}
