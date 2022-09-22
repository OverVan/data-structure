package sort;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class SortTest {

	@Test
	void testSort() {
		Integer[] elems = { 2, 1, 3, 7, null, 3, 6, 5, 5, 4, 8, null };
		new Sort<Integer>().bubble(elems);
		System.out.println(Arrays.toString(elems));
	}

	@Test
	void testSelect() {
		Integer[] elems = { 2, 1, 3, 7, null, 3, 6, 5, 5, 4, 8, null };
		new Sort<Integer>().select(elems);
		System.out.println(Arrays.toString(elems));
	}

	@Test
	void testInsert() {
		Integer[] elems = { 2, 1, 3, 7, null, 3, 6, 5, 5, 4, 8, null };
		new Sort<Integer>().insert(elems);
		System.out.println(Arrays.toString(elems));
	}

	@Test
	void testQuick() {
		Integer[] elems = { 2, 1, 3, 7, null, 3, 6, 5, 5, 4, 8, null };
		new Sort<Integer>().quick(elems, 0, elems.length - 1, 0);
		System.out.println(Arrays.toString(elems));
	}

	@Test
	void testCount() {
		int[] elems = { 2, 1, 3, 7, 3, 6, 5, 5, 4, 8 };
		new Sort<Integer>().count(elems);
		System.out.println(Arrays.toString(elems));
	}

	@Test
	void testShell() {
		Integer[] elems = { 2, 1, 3, 7, 3, 6, 5, 5, 4, 8 };
		new Sort<Integer>().shell(elems);
		System.out.println(Arrays.toString(elems));
	}

	@Test
	void testMerge() {
		Integer[] elems = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		new Sort<Integer>().merge(elems, 0, elems.length - 1);
		System.out.println(Arrays.toString(elems));
	}

	@Test
	void testBucketByIncreasingWidh() {
		int[] elems = { 10, 22, 38, 217, 652, 52, 4, 8, 3, 1 };
		new Sort<Integer>().bucketByIncreasingWidh(elems);
		System.out.println(Arrays.toString(elems));
	}

	@Test
	void testBucketByConstantWidth() {
		int[] elems = { 10, 33, 25, 14, 87, 52, 24, 58, 38, 87, 98, 90 };
		new Sort<Integer>().bucketByConstantWidth(elems);
		System.out.println(Arrays.toString(elems));
	}

	@Test
	void testRadix() {
		int[] elems = { 21, 7, 310, 14, 88, 105, 2, 216, 1024, 421, 2877, 3, 66, 361 };
		new Sort<Integer>().radix(elems);
		System.out.println(Arrays.toString(elems));
	}

	@Test
	void testHeap() {
		Integer[] elems = { 52, 41, 62, 30, 15, 13, 22, 19, 28, 17, 16 };
		new Sort<Integer>().heap(elems);
		System.out.println(Arrays.toString(elems));
	}
}
