package search;

import org.junit.jupiter.api.Test;

class SearchTest {

	@Test
	void testBinarySearch() {
		Integer[] array = { -1, 0, 3, 5, 9, 12 };
		BinarySearch<Integer> search = new BinarySearch<>();
		System.out.println(search.recurSearch(array, 0, array.length - 1, 4));
		System.out.println(search.loopSearch(array, 0, array.length - 1, 5));
	}

	@Test
	void testLinearSearch() {
		Integer[] array = { 7, 2, 5, 5, 3, 9, 8, 2, 2, 4 };
		LinearSearch<Integer> sequenceSearch = new LinearSearch<>();
		System.out.println(sequenceSearch.searchFirst(array, 2));
		System.out.println(sequenceSearch.searchAll(array, 2));
	}

}
