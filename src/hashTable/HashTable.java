package hashTable;

import java.util.TreeMap;

/**
 * 键值对版的哈希表
 * 
 * @param <Key>   键类型，这里没要求可比，但TreeMap隐性地要求可比
 * @param <Value> 值类型
 */
public class HashTable<Key, Value> {
	// 自己的红黑树没实现删除，故暂用Java内置的基于红黑树的TreeMap
	private TreeMap<Key, Value>[] table;
	// 压缩因子或映射因子，即数组长度
	private int M;
	// 键值对总数
	private int size;
	// 共数组变容取长度的素数表，后一个数略小于前一个数的2倍
	private static final int[] CAPACITIES = { 53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317,
			196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917, 25165843, 50331653, 100663319, 201326611,
			402653189, 805306457, 1610612741 };
	// 取长度游标
	private int capacityIndex;
	// 上界容忍度或扩容因子
	private static final int UPPER_TOL = 10;
	// 下界容忍度或缩容因子
	private static final int LOWER_TOL = 2;

	public HashTable() {
		M = CAPACITIES[capacityIndex];
		table = new TreeMap[M];
		for (int i = 0; i < M; i++) {
			table[i] = new TreeMap<>();
		}
	}

	/**
	 * 获取键值对总数
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 哈希函数：将键对象映射为哈希表上的一个位置
	 * 
	 * @param key 键
	 * @return 位置
	 */
	private int hash(Key key) {
		// 前一步是为了保持哈希值非负，0x7fffffff对应的二进制数为31个1并符号位0，表示int型的最大值
		return (key.hashCode() & 0x7fffffff) % M;
	}

	/**
	 * 对table变容
	 * 
	 * @param M 新容量
	 */
	private void resize(int newM) {
		TreeMap<Key, Value>[] newTalbe = new TreeMap[newM];
		for (int i = 0; i < newM; i++) {
			newTalbe[i] = new TreeMap<>();
		}
		for (int i = 0; i < M; i++) {
			for (Key key : table[i].keySet()) {
				newTalbe[hash(key)].put(key, table[i].get(key));
			}
		}
		M = newM;
		table = newTalbe;
	}

	/**
	 * 添加或修改键值对 O(1)，下同
	 * 
	 * @param key   键
	 * @param value 值
	 */
	public void add(Key key, Value value) {
		// 先用哈希函数求出位置，再在该位置的红黑树中用compareTo方法继续查找
		TreeMap<Key, Value> map = table[hash(key)];
		if (map.containsKey(key)) {
			map.put(key, value);
		} else {
			map.put(key, value);
			size++;
			// 扩容，但由于素数表有限，不能无限扩
			if (size >= M * UPPER_TOL && capacityIndex + 1 < CAPACITIES.length) {
				resize(CAPACITIES[++capacityIndex]);
			}
		}
	}

	/**
	 * 以键删除值
	 * 
	 * @param key 键
	 * @return 值
	 */
	public Value remove(Key key) {
		// 对M取模的结果属于[0, M-1]，不会产生越界异常
		TreeMap<Key, Value> map = table[hash(key)];
		if (map.containsKey(key)) {
			size--;
			// 缩容
			if (size <= M * LOWER_TOL && capacityIndex - 1 >= 0) {
				resize(CAPACITIES[--capacityIndex]);
			}
			return map.remove(key);
		}
		return null;
	}

	/**
	 * 以键修改值
	 * 
	 * @param key   键
	 * @param value 新值
	 */
	public void set(Key key, Value value) {
		TreeMap<Key, Value> map = table[hash(key)];
		if (map.containsKey(key)) {
			map.put(key, value);
		} else {
			throw new RuntimeException("键不存在");
		}
	}

	/**
	 * 判断是否含有指定键值对
	 * 
	 * @param key 键
	 * @return
	 */
	public boolean contains(Key key) {
		return table[hash(key)].containsKey(key);
	}

	/**
	 * 以键取值
	 * 
	 * @param key 键
	 * @return 值
	 */
	public Value get(Key key) {
		return table[hash(key)].get(key);
	}
}
