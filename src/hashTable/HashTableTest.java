package hashTable;

import org.junit.jupiter.api.Test;

class HashTableTest {

	@Test
	void testHashCode() {
		// 像数学函数那样，不能一对多，允许多对一-哈希冲突
		Student thomas = new Student("thomas", "yang", 2, 6);
		Student yang = new Student("thomas", "yang", 2, 6);
		System.out.println(thomas.hashCode() == yang.hashCode());
		System.out.println(thomas.equals(yang));
	}

	@Test
	void testHashTable() {
		HashTable<String, Object> nations = new HashTable<>();
		nations.add("china", 1);
		nations.add("russia", 2);
		nations.add("japan", 10);
		System.out.println(nations.getSize());
		System.out.println(nations.get("china"));

		HashTable<Integer, Object> table = new HashTable<>();
		int optCount = 1000;
		long begin = System.currentTimeMillis();
		for (int i = 0; i < optCount; i++) {
			table.add(i, "hashTable");
		}
		for (int i = 0; i < optCount; i++) {
			table.get(i);
		}
		long end = System.currentTimeMillis();
		System.out.println((end - begin) / 1E3);
	}

}
