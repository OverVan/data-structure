package hashTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 员工结点
 * 
 * @author Van
 */
class EmployeeNode {
	private int id;// id
	private String name;// 姓名
	private EmployeeNode next;// 后继结点

	public EmployeeNode(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public EmployeeNode getNext() {
		return next;
	}

	public void setNext(EmployeeNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "员工 [id=" + id + ", name=" + name + "]";
	}
}

/**
 * 哈希表数组的元素-员工单链表
 * 
 * @author Van
 */
class EmployeeLinkedList {
	// 首结点（带数据）
	private EmployeeNode head;

	/**
	 * 添加一个员工结点（尾插）
	 * 
	 * @param employeeNode 员工结点
	 */
	public void addNode(EmployeeNode employeeNode) {
		// 若首结点为空则直接将待插结点赋给首结点
		if (head == null) {
			head = employeeNode;
		} else {// 否则接到尾结点后
			EmployeeNode cursor = head;
			while (cursor.getNext() != null) {
				cursor = cursor.getNext();
			}
			cursor.setNext(employeeNode);
		}
	}

	/**
	 * 展示哈希表数组当前下标对应的链表
	 */
	public void showList() {
		// 空链表的情况没有单独拎出来
		EmployeeNode cursor = head;
		while (cursor != null) {
			System.out.print(cursor.toString() + " -> ");
			cursor = cursor.getNext();
		}
		System.out.println();
	}

	/**
	 * 根据id查找员工
	 * 
	 * @param id 员工id
	 * @return 员工结点
	 */
	public EmployeeNode findById(int id) {
		EmployeeNode cursor = head;
		while (cursor != null) {
			if (cursor.getId() == id) {
				return cursor;
			}
			cursor = cursor.getNext();
		}
		return null;
	}
}

/**
 * 哈希表（散列表）
 * 
 * @author Van
 */
public class HashTable {
	private EmployeeLinkedList[] linkedListArray;// 员工链表数组
	private int size; // 数组长度

	public HashTable(int size) {
		this.size = size;
		this.linkedListArray = new EmployeeLinkedList[this.size];
		// 初始化数组的每个元素即所有链表
		for (int i = 0; i < linkedListArray.length; i++) {
			linkedListArray[i] = new EmployeeLinkedList();
		}
	}

	/**
	 * 散列函数
	 * 
	 * @param id 员工id
	 * @return 应挂置的链表对应的下标
	 */
	public int hashFunction(int id) {
		// 链地址法
		return id % size;
	}

	/**
	 * 添加员工
	 * 
	 * @param employeeNode 员工结点
	 */
	public void addEmployee(EmployeeNode employeeNode) {
		// 用散列函数求目标链表
		int index = hashFunction(employeeNode.getId());
		// 在该链表尾插
		linkedListArray[index].addNode(employeeNode);
	}

	/**
	 * 展示哈希表即遍历所有员工链表
	 */
	public void showTable() {
		// 对每个链表调用展示方法
		for (int i = 0; i < linkedListArray.length; i++) {
			System.out.printf("[%d] => ", i);
			linkedListArray[i].showList();
		}
	}

	/**
	 * 查找员工
	 * 
	 * @param id 员工id
	 * @return 员工结点
	 */
	public EmployeeNode search(int id) {
		// 用散列函数求目标链表
		int index = hashFunction(id);
		// 在该链表上调用查找方法
		EmployeeNode target = linkedListArray[index].findById(id);
		// 分别处理查到与否的情况
		if (target == null) {
			System.out.println("该员工不在哈希表中");
			return target;
		} else {
			return target;
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 构造id较为分散且互异的员工结点数组
		Random random = new Random();
		List<Integer> integers = new ArrayList<Integer>();
		while (integers.size() < 20) {
			int id = random.nextInt(100);
			if (!integers.contains(id)) {
				integers.add(id);
			}
		}
		EmployeeNode[] employeeNodes = new EmployeeNode[20];
		System.out.println(integers.size());
		for (int i = 0; i < 20; i++) {
			employeeNodes[i] = new EmployeeNode(integers.get(i), i + "ForText");
		}
		// 构造哈希表
		HashTable hashTable = new HashTable(15);
		// 添加所有员工到指定位置
		for (int i = 0; i < employeeNodes.length; i++) {
			hashTable.addEmployee(employeeNodes[i]);
		}
		// 打印哈希表
		hashTable.showTable();
		// 查找员工
		System.out.println(hashTable.search(55));
		System.out.println(hashTable.search(17));
		System.out.println(hashTable.search(80));
	}
}
