package linkedList;

/**
 * 诗人结点
 * 
 * @author Van
 */
class PoetNode {
	private int no;// 编号
	private String name;// 姓名
	private PoetNode next;// 后继节点
	private PoetNode prior;// 前驱结点

	/**
	 * 仅初始化数据域，next和prior默认为null
	 * 
	 * @param no
	 * @param name
	 * @param nickName
	 */
	public PoetNode(int no, String name) {
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public PoetNode getNext() {
		return next;
	}

	public void setNext(PoetNode next) {
		this.next = next;
	}

	public PoetNode getPrior() {
		return prior;
	}

	public void setPrior(PoetNode prior) {
		this.prior = prior;
	}

	@Override
	public String toString() {
		return "PoetNode [no=" + no + ", name=" + name + "]";
	}
}

/**
 * 双向链表
 * 
 * @author Van
 */
public class DoubleLinkedList {
	// 头结点，不存放任何数据
	private PoetNode head = new PoetNode(0, "");

	/**
	 * 显示双向链表
	 */
	public void showList() {
		// 空链表显示个毛
		if (head.getNext() == null) {
			System.out.println("双向链表为空");
		} else {
			// 打印每一个结点的信息
			PoetNode cursor = head.getNext();
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
	public void addNode(PoetNode poetNode) {
		// 游标结点
		PoetNode cursor = head;
		// 只要游标的后继非空，游标就后移
		while (cursor.getNext() != null) {
			cursor = cursor.getNext();
		}
		// 找到尾结点，接上要添加的结点
		cursor.setNext(poetNode);
		// 令更新的尾结点的后继为null
		poetNode.setNext(null);
		// 链上更新的尾结点的前驱
		poetNode.setPrior(cursor);
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
			System.out.println("双向链表为空");
			return false;
		}
		// 游标结点
		PoetNode cursor = head;
		// 若游标的后继非空且后继的编号不符，则游标后移
		while (cursor.getNext() != null && cursor.getNext().getNo() != no) {
			cursor = cursor.getNext();
		}
		// 最终要么停到尾结点，要么停到待删结点的前驱
		if (cursor.getNext() == null) {
			System.out.println("要删的诗人" + no + "没找到，无法删除");
			return false;
		} else {
			// 删除最后一个结点与删除普通结点分开看待
			if (cursor.getNext().getNext() != null) {
				cursor.getNext().getNext().setPrior(cursor);
			}
			cursor.setNext(cursor.getNext().getNext());
			return true;
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 初始化双向链表
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		// 一系列诗人结点
		PoetNode poet1 = new PoetNode(1, "李白");
		PoetNode poet2 = new PoetNode(2, "杜甫");
		PoetNode poet3 = new PoetNode(3, "韩愈");
		PoetNode poet4 = new PoetNode(4, "白居易");
		PoetNode poet5 = new PoetNode(5, "苏轼");
		// 链起来
		doubleLinkedList.addNode(poet1);
		doubleLinkedList.addNode(poet2);
		doubleLinkedList.addNode(poet3);
		doubleLinkedList.addNode(poet4);
		doubleLinkedList.addNode(poet5);
		// 展示双链表
		doubleLinkedList.showList();

		// 删除最后一个结点
		doubleLinkedList.delete(5);
		// 展示双链表
		System.out.println("删除最后一个结点得到：");
		doubleLinkedList.showList();
	}

}
