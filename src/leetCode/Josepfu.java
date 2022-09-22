package leetCode;

/**
 * 参与者结点
 * 
 * @author Van
 */
class PeopleNode {
	private int no;// 编号
	private PeopleNode next;// 后继节点

	/**
	 * 仅初始化编号，后继节点默认为null
	 * 
	 * @param no
	 */
	public PeopleNode(int no) {
		this.no = no;
	}

	public PeopleNode() {
		this.no = 0;
	}

	public int getNo() {
		return no;
	}

	public PeopleNode getNext() {
		return next;
	}

	public void setNext(PeopleNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "People [no=" + no + "]";
	}
}

/**
 * 约瑟夫环（无头结点的单向环形链表）
 * 
 * @author Van
 */
public class Josepfu {
	private PeopleNode first = new PeopleNode(-1);// 第一个结点
	private int count;// 结点总数

	/**
	 * 添加若干个参与者
	 * 
	 * @param count 人数
	 */
	public void addPeople(int count) {
		// 校验人数
		if (count < 1) {
			System.out.println("count至少为1");
			return;
		}
		this.count = count;
		// 游标结点
		PeopleNode cursor = new PeopleNode();
		// 依次添加结点
		for (int i = 1; i <= count; i++) {
			// 初始化一个结点
			PeopleNode people = new PeopleNode(i);
			if (i == 1) {
				first = people;
				// 自反（自闭合）
				first.setNext(first);
				// 游标自此始
				cursor = first;
			} else {
				// 跟上一个结点接上
				cursor.setNext(people);
				// 尾链上首以闭合
				people.setNext(first);
			}
			// 游标后移一位
			cursor = cursor.getNext();
		}
	}

	/**
	 * 显示环形链表
	 */
	public void showPeople() {
		// 连第一个都没有，更别说后面的
		if (first == null) {
			System.out.println("环状链表为空");
			return;
		}
		// 游标结点
		PeopleNode cursor = first;
		// 只有最后一个结点的后继是first，打印完最后一个跳出循环
		while (true) {
			System.out.println(cursor + "——>");
			cursor = cursor.getNext();
			if (cursor == first) {
				break;
			}
		}
	}

	/**
	 * 在完整链表条件下，从1号结点开始寻找k号结点。count号和0号是同一个，设0是为了便于1号出圈
	 * 
	 * @param k 目标号码（0<=k<=count)
	 * @return 目标结点
	 */
	public PeopleNode findKNode(int k) {
		// 号码应处合理区间
		if (k > count || k < 0) {
			System.out.println("号码不合理");
			return null;
		}
		// 游标结点
		PeopleNode cursor = first;
		if (k == 0) {
			// 找0号结点即最后一个结点
			// 最后一个结点的后继是first，也可用while(cursor.getNo() != count)
			while (cursor.getNext() != first) {
				cursor = cursor.getNext();
			}
			return cursor;
		} else {
			// 找普通结点（包括最后一个）
			while (cursor.getNo() != k) {
				cursor = cursor.getNext();
			}
			return cursor;
		}
	}

	/**
	 * 玩游戏，得出出圈的序列。用双层循环：外层负责出圈，内层负责报数
	 * 
	 * @param startNo 开始号码
	 * @param number  报数上限
	 */
	public void play(int startNo, int number) {
		// 开始号码不能大于链表结点编号最大值也不能小于最小值
		if (startNo > count || startNo < 1) {
			System.out.println("报数的号码不合理");
			return;
		}
		// 游标结点，从startNo号结点开始
		PeopleNode cursor = findKNode(startNo);
		// 删除操作的辅助结点(0<=startNo-1<=count-1)
		PeopleNode pointer = findKNode(startNo - 1);
		// 只有剩一个结点时，它才是自反的，它既是最后一个出圈的
		while (cursor.getNext() != cursor) {
			// 依次报数，出圈
			int i = 1;// 每一轮都从1开始报
			while (i != number) {
				i++;
				cursor = cursor.getNext();
				pointer = pointer.getNext();// pointer始终跟随cursor，作其前驱
			}
			System.out.println(cursor.getNo() + " 号出圈儿");
			// 删除结点，重新闭环
			pointer.setNext(cursor.getNext());
			// 从出圈结点的下一结点开始下一轮
			cursor = cursor.getNext();
		}
		// 幸存者出圈
		System.out.println(cursor.getNo() + " 号出圈儿");
		System.out.println("所有小伙伴出圈完毕");
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 10个小伙伴
		Josepfu josepfu = new Josepfu();
		josepfu.addPeople(10);
		josepfu.showPeople();
		// 玩游戏
		josepfu.play(1, 2);
	}

}
