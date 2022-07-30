package sequenceList;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class ArrayTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	void testArray() {
		Array array = new Array(20);
		array.addLast(1);
		array.addLast(2);
		array.addLast(3);
		System.out.println(array.toString());
		array.add(8, 1);
		System.out.println(array.toString());
	}

	@Test
	void testGenericArray() {
		GenericArray<Student> genericArray = new GenericArray<>();
		genericArray.addLast(new Student("bob", 80));
		genericArray.addLast(new Student("tom", 90));
		genericArray.addFirst(new Student("john", 88));
		System.out.println(genericArray.toString());
		System.out.println(genericArray.find(new Student("bob", 80)));
	}

	@Test
	void testDynamicArray() {
		DynamicArray<Student> dynamicArray = new DynamicArray<Student>(2);
		dynamicArray.addFirst(new Student("bob", 90));
		dynamicArray.addFirst(new Student("tom", 80));
		System.out.println(dynamicArray.toString());
		dynamicArray.addFirst(new Student("joe", 86));
		System.out.println(dynamicArray.toString());
		dynamicArray.removeLast();
		System.out.println(dynamicArray.toString());
	}

}
