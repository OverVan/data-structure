package linkedList;

import java.util.Stack;

/**
 * 好汉结点
 * 
 * @author Van
 */
class HeroNode {
	private int no;// 编号
	private String name;// 姓名
	private String nickName;// 绰号
	private HeroNode next;// 后继节点

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public HeroNode getNext() {
		return next;
	}

	public void setNext(HeroNode next) {
		this.next = next;
	}

	/**
	 * 仅初始化数据域，next默认为null
	 * 
	 * @param no
	 * @param name
	 * @param nickName
	 */
	public HeroNode(int no, String name, String nickName) {
		this.no = no;
		this.name = name;
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		// 换个好看的样子
		return "HeroNode [no=" + no + ", name=" + name + ", nickName=" + nickName + "]";
	}
}

/**
 * 单链表
 * 
 * @author Van
 */
public class SingleLinkedList {
	// 头结点（不存放数据）
	private HeroNode head = new HeroNode(0, "", "");

	public HeroNode getHead() {
		return head;
	}

	/**
	 * 显示链表
	 */
	public void showList() {
		// 空链表显示个毛
		if (head.getNext() == null) {
			System.out.println("单链表为空");
		} else {
			// 打印每一个结点信息
			HeroNode cursor = head.getNext();
			while (cursor != null) {
				System.out.println(cursor + " ——> ");
				cursor = cursor.getNext();
			}
		}
	}

	/**
	 * 添加一个结点（尾插）
	 * 
	 * @param heroNode 待添结点
	 */
	public void addNode(HeroNode heroNode) {
		// 游标结点
		HeroNode cursor = head;
		// 只要游标的后继非空，游标就后移
		while (cursor.getNext() != null) {
			cursor = cursor.getNext();
		}
		// 找到尾结点，接上要添加的结点
		cursor.setNext(heroNode);
		// 令更新的尾结点的后继为null
		heroNode.setNext(null);
	}

	/**
	 * 添加一个单链表（尾插）
	 * 
	 * @param heroNode 待接的单链表的头结点
	 */
	public void addList(HeroNode heroNode) {
		// 游标结点
		HeroNode cursor = head;
		// 只要游标的后继非空，游标就后移
		while (cursor.getNext() != null) {
			cursor = cursor.getNext();
		}
		// 找到尾结点，接上要添加单链表
		cursor.setNext(heroNode);
	}

	/**
	 * 根据姓名将新结点插入到指定好汉后面
	 * 
	 * @param heroNode 待插结点
	 * @param name     指定的好汉名
	 * @return true：插入成功；false：插入失败
	 */
	public boolean insertByName(HeroNode heroNode, String name) {
//		// 可附带条件：不能重复插入
//		HeroNode pointer = head;
//		while (pointer != null) {
//			if (pointer.getName().equals(heroNode.getName())) {
//				System.out.println("此好汉已存在，不可重复插入");
//				return false;
//			}
//		}
		// 游标结点
		HeroNode cursor = head;
		// 若游标非空且非给定好汉名的结点，则游标后移
		while (cursor != null && !cursor.getName().equals(name)) {
			cursor = cursor.getNext();
		}
		// 最终要么停到null，要么停到待插结点的前驱
		if (cursor == null) {
			System.out.println("指定的好汉" + name + "没找到，无法插入");
			return false;
		} else {
			// 先出后入
			heroNode.setNext(cursor.getNext());
			cursor.setNext(heroNode);
			return true;
		}
	}

	/**
	 * 根据编号将新结点插入到指定好汉后面
	 * 
	 * @param heroNode 待插结点
	 * @param no       编号
	 * @return true：插入成功；false：插入失败
	 */
	public boolean insertByNo(HeroNode heroNode, int no) {
		// 游标结点
		HeroNode cursor = head;
		// 若游标非空且非给定好汉名的结点，则游标后移
		while (cursor != null && cursor.getNo() != no) {
			cursor = cursor.getNext();
		}
		// 最终要么停到null，要么停到待插结点的前驱
		if (cursor == null) {
			System.out.println("指定的好汉" + no + "没找到，无法插入");
			return false;
		} else {
			// 先出后入
			heroNode.setNext(cursor.getNext());
			cursor.setNext(heroNode);
			return true;
		}
	}

	/**
	 * 根据编号修改结点数据
	 * 
	 * @param heroNode 携带新数据的结点
	 */
	public boolean update(HeroNode heroNode) {
		// 空链表改个毛
		if (head.getNext() == null) {
			System.out.println("单链表为空");
			return false;
		}
		// 游标结点
		HeroNode cursor = head;
		// 若游标非空且其编号不符，则游标后移
		while (cursor != null && cursor.getNo() != heroNode.getNo()) {
			cursor = cursor.getNext();
		}
		// 最终要么停到null，要么停到待修改结点
		if (cursor == null) {
			System.out.println("指定的好汉" + heroNode.getNo() + "没找到，无法修改");
			return false;
		} else {
			// 更新数据
			if (heroNode.getName() != null) {
				cursor.setName(heroNode.getName());
			}
			if (heroNode.getNickName() != null) {
				cursor.setNickName(heroNode.getNickName());
			}
			return true;
		}
	}

	/**
	 * 删除指定编号的结点
	 * 
	 * @param no
	 * @return true：删除成功；false：删除失败
	 */
	public boolean delete(int no) {
		// 空链表删个毛
		if (head.getNext() == null) {
			System.out.println("单链表为空");
			return false;
		}
		// 游标结点
		HeroNode cursor = head;
		// 若游标的后继非空且后继的编号不符，则游标后移
		while (cursor.getNext() != null && cursor.getNext().getNo() != no) {
			cursor = cursor.getNext();
		}
		// 最终要么停到最后一个结点，要么停到待删结点的前驱
		if (cursor.getNext() == null) {
			System.out.println("指定的好汉" + no + "没找到，无法删除");
			return false;
		} else {
			// 删除结点
			HeroNode deleted = cursor.getNext();
			cursor.setNext(deleted.getNext());
//			deleted.setNext(null);
			return true;
		}
	}

	/**
	 * 统计有效结点（刨开头结点）个数
	 * 
	 * @return
	 */
	public int getLength() {
		// 空链表计个毛
		if (head.getNext() == null) {
			return 0;
		}
		// 计数君
		int length = 0;
		// 游标结点（从第一个好汉结点开始）
		HeroNode cursor = head.getNext();
		// 若游标非空则游标后移
		while (cursor != null) {
			length++;
			cursor = cursor.getNext();
		}
		return length;
	}

	/**
	 * 查找倒数第k个结点
	 * 
	 * @return 找到的结点
	 */
	public HeroNode findKReverse(int k) {
		// 空链表查个毛
		if (head.getNext() == null) {
			System.out.println("单链表为空");
			return null;
		}
		// 正数第index个
		int index = getLength() + 1 - k;
		// 游标结点
		HeroNode cursor = head;
		// 若游标非空且其编号不符，则游标后移
		while (cursor != null && cursor.getNo() != index) {
			cursor = cursor.getNext();
		}
		// 最终要么停到null，要么停到待取结点
		if (cursor == null) {
			System.out.println("倒数第" + k + "没找到，无法修改");
			return null;
		} else {
			return cursor;
		}
	}

	/**
	 * 将单链表反转（有诸多方法，如中间数组、中间栈、头插法，这里用头插法）
	 * 
	 * @return 反转后的链表头结点
	 */
	public HeroNode reverseByHeadInsert() {
		// 空链表反转个毛
		if (head.getNext() == null) {
			System.out.println("单链表为空");
			return null;
		}
		// 游标结点
		HeroNode cursor = head.getNext();
		// 若游标的后继非空则游标后移
		while (cursor != null) {
			// 预先拿出游标的后继，不然变死循环
			HeroNode temp = cursor.getNext();
			// 对当前结点进行头插
			insertByName(cursor, "");
			// 游标后移
			cursor = temp;
		}
		// 令反转链表的尾结点的后继为null
		HeroNode pointer = head;
		while (pointer.getNext() != pointer) {// 由于insertByName的作用，仅尾结点指向自己
			pointer = pointer.getNext();
		}
		pointer.setNext(null);
		// 返回反转链表头结点
		return head;
	}

	/**
	 * 将单链表反转（借助中间数组）
	 * 
	 * @return 反转得到的链表
	 */
	public SingleLinkedList reverseByArray() {
		// 空链表反转个毛
		if (head.getNext() == null) {
			System.out.println("单链表为空");
			return null;
		}
		// 将排除头结点的原始链表存入中间数组
		HeroNode cursor = head.getNext();
		HeroNode[] heroNodes = new HeroNode[getLength()];
		int i = 0;
		while (cursor != null) {
			heroNodes[i] = cursor;
			cursor = cursor.getNext();
			heroNodes[i].setNext(null); // 游标后移之后再将刚进数组的结点的后继置空
			i++;
		}
		// 逆序地将数组中的结点挂进一个新的链表
		SingleLinkedList newLinkedList = new SingleLinkedList();
		for (int j = heroNodes.length - 1; j >= 0; j--) {
			newLinkedList.addNode(heroNodes[j]);
		}
//		// 令反转链表的尾结点的后继为null
//		HeroNode pointer = newLinkedList.getHead();
//		while (pointer.getNext() != head.getNext().getNext()) {// 反转链表尾结点为原始链表第一个有效结点的后继
//			pointer = pointer.getNext();
//		}
//		pointer.setNext(null);
		// 返回新的反转链表
		return newLinkedList;
	}

	/**
	 * 不改变原有结构，利用栈逆序打印单链表
	 */
	public void reversePrint() {
		// 空链表打印个毛
		if (head.getNext() == null) {
			System.out.println("单链表为空");
			return;
		}
		// 好汉结点栈
		Stack<HeroNode> stack = new Stack<HeroNode>();
		// 正序将结点压栈
		HeroNode cursor = head.getNext();
		while (cursor != null) {
			stack.push(cursor);
			cursor = cursor.getNext();
		}
		// 出栈并打印
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

	/**
	 * 利用归并排序合并两个有序单链表，合并之后依然有序
	 * 
	 * @param list1
	 * @param list2
	 * @return 合并所得的单链表
	 */
	public static SingleLinkedList mergeTwoList(SingleLinkedList list1, SingleLinkedList list2) {
		// 待挂的合并链表
		SingleLinkedList mergedList = new SingleLinkedList();
		// 双游标
		HeroNode cursor1 = list1.getHead().getNext();
		HeroNode cursor2 = list2.getHead().getNext();
		// 归并排序循环
		while (cursor1 != null || cursor2 != null) {
			// 只要有一方全部挂上去那么就直接挂上另一方剩余的结点并终止循环
			if (cursor1 == null) {
				mergedList.addList(cursor2);
				break;
			}
			if (cursor2 == null) {
				mergedList.addList(cursor1);
				break;
			}
			// 两方链表挨个儿比编号，必有且仅有一方挂上一个
			if (cursor1.getNo() < cursor2.getNo()) {
				// 预先拿出游标的后继
				HeroNode temp = cursor1.getNext();
				// 挂上去
				mergedList.addNode(cursor1);
				// 游标后移一位
				cursor1 = temp;
			} else {
				HeroNode temp = cursor2.getNext();
				mergedList.addNode(cursor2);
				cursor2 = temp;
			}
		}
		// 返回合并链表
		return mergedList;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 初始化单链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		// 创建一系列结点
		HeroNode heroOne = new HeroNode(1, "宋江", "呼保义");
		HeroNode heroTwo = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode heroThree = new HeroNode(3, "吴用", "智多星");
		HeroNode heroFour = new HeroNode(4, "公孙胜", "入云龙");
		HeroNode heroFive = new HeroNode(5, "关胜", "大刀");
		// 将他们链起来（setter、insertXXX、addNode多选一）
		singleLinkedList.getHead().setNext(heroOne);
		heroOne.setNext(heroTwo);
		heroTwo.setNext(heroThree);
		singleLinkedList.addNode(heroFour);
		singleLinkedList.addNode(heroFive);
		// 展示单链表
		singleLinkedList.showList();

		// 逆序打印单链表
		System.out.println("逆序打印单链表：");
		singleLinkedList.reversePrint();

		// 在某好汉后面插入一张鬼牌
		HeroNode goastNode = new HeroNode(250, "goast", "goast");
		if (singleLinkedList.insertByName(goastNode, "林冲")) {
			// 再展示单链表
			singleLinkedList.showList();
		}

		// 合并两个有序链表
		SingleLinkedList listOne = new SingleLinkedList();
		listOne.addNode(new HeroNode(1, "", ""));
		listOne.addNode(new HeroNode(3, "", ""));
		listOne.addNode(new HeroNode(7, "", ""));
		listOne.showList();
		SingleLinkedList listTwo = new SingleLinkedList();
		listTwo.addNode(new HeroNode(2, "", ""));
		listTwo.addNode(new HeroNode(4, "", ""));
		listTwo.addNode(new HeroNode(5, "", ""));
		listTwo.addNode(new HeroNode(6, "", ""));
		listTwo.showList();
		System.out.println("合并俩单链表得：");
		mergeTwoList(listOne, listTwo).showList();

		// 修改好汉的信息
		singleLinkedList.update(new HeroNode(2, null, "卢比奥"));
		singleLinkedList.update(new HeroNode(4, null, "公孙乌龙"));
		// 再展示单链表
		singleLinkedList.showList();

		// 查询倒数第某个结点
		System.out.println("从倒数第4个开始打印：" + singleLinkedList.findKReverse(4));

		// 反转单链表
//		System.out.println("反转后得到：" + singleLinkedList.reverseByHeadInsert());
		System.out.println("反转后得到：");
		singleLinkedList.reverseByArray().showList();

		// 删除某个结点
		singleLinkedList.delete(1);
		// 再展示单链表
		singleLinkedList.showList();

		// 计结点数
		System.out.println("有效结点个数：" + singleLinkedList.getLength());
	}
}
