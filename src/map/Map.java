package map;

/**
 * 映射
 * 
 * @param <Key>   键类型，可不可取null看底层结构
 * @param <Value> 值类型
 */
public interface Map<Key, Value> {
	/**
	 * 添加键值对或以键改值
	 * 
	 * @param key   键
	 * @param value 值
	 */
	void add(Key key, Value value);

	/**
	 * 以键删除键值对并获取值
	 * 
	 * @param key 键
	 * @return
	 */
	Value remove(Key key);

	/**
	 * 以键判断是否含有指定键值对
	 * 
	 * @param key 键
	 * @return
	 */
	boolean contains(Key key);

	/**
	 * 以键取值
	 * 
	 * @param key 键
	 * @return
	 */
	Value get(Key key);

	/**
	 * 以键修改值
	 * 
	 * @param key
	 * @param value
	 */
	void set(Key key, Value value);

	/**
	 * 获取键值对总数
	 * 
	 * @return
	 */
	int getSize();

	/**
	 * 判断有无键值对
	 * 
	 * @return
	 */
	boolean isEmpty();
}
