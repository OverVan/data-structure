package set;

/**
 * 集合
 * 
 * @param <Elem> 元素类型，可不可取null看底层结构
 */
public interface Set<Elem> {
//修改是不合理的，一旦重复修改就成了删除
	/**
	 * 获取元素总数
	 */
	int getSize();

	/**
	 * 是否有元素
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 添加元素，不可重复
	 * 
	 * @param elem
	 */
	void add(Elem elem);

	/**
	 * 删除指定元素
	 * 
	 * @param elem
	 */
	void remove(Elem elem);

	/**
	 * 是否含有指定元素
	 * 
	 * @param elem 元素
	 * @return
	 */
	boolean contains(Elem elem);
}
