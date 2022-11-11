package sort;

/**
 * 非递减排序 若考虑负数，像桶排序等算法就得改，采用分正负数桶组等策略
 * 
 * @param <Elem> 元素类型，要求可比，要么传入Comparator对象，不可取null
 */
public class Sort<Elem extends Comparable<Elem>> {
	/**
	 * 交换元素
	 * 
	 * @param elems 元素数组
	 * @param i     下标
	 * @param j     下标
	 */
	private void swap(Elem[] elems, int i, int j) {
		Elem temp = elems[i];
		elems[i] = elems[j];
		elems[j] = temp;
	}

	/**
	 * 冒泡排序 最好O(n)；平均O(n2)；最坏O(n2)；空间O(1)；稳定
	 * 
	 * @param elems 元素数组
	 */
	public void bubble(Elem[] elems) {
		// 最多n-1趟
		for (int i = 0; i < elems.length - 1; i++) {
			// 每趟排序的交换标志量
			boolean swapped = false;
			// 每趟结束从后往前数至少i个元素排好序，即每一趟都会让本趟最大值上浮
			for (int j = 0; j < elems.length - i - 1; j++) {
				// 若干须交换的情况
				if (elems[j].compareTo(elems[j + 1]) > 0) {
					swap(elems, j, j + 1);
					// 标记本趟发生了交换
					swapped = true;
				}
			}
			// 若本趟没有发生交换则说明已全部排好
			if (!swapped) {
				break;
			}
		}
	}

	/**
	 * 选择排序 最好O(n2)；平均O(n2)；最坏O(n2)；空间O(1)；不稳定
	 * 
	 * @param elems 元素数组
	 */
	public void select(Elem[] elems) {
		// 每一趟最小元素的下标
		int min = 0;
		// 共n-1趟
		for (int i = 0; i < elems.length - 1; i++) {
			// 暂定首元素为最小元素
			min = i;
			if (elems[min] == null) {
				// 首元素为null直接进行下一趟
				continue;
			}
			// 选出最小元素，记录下标
			for (int j = i + 1; j < elems.length; j++) {
				if (elems[j].compareTo(elems[min]) < 0) {
					min = j;
				}
			}
			// 若最小元素不是首元素了，则交换这两个元素
			if (min != i) {
				swap(elems, min, i);
			}
		}
	}

	/**
	 * 插入排序 最好O(n)；平均O(n2)；最坏(n2)；空间O(1)；稳定
	 * 
	 * @param elems 元素数组
	 */
	public void insert(Elem[] elems) {
		// 待插入元素
		Elem insertElem = null;
		// 从第2个元素开始遍历
		for (int i = 1; i < elems.length; i++) {
			insertElem = elems[i];
			// 将待插入值与前面的元素一一比较，若后者更大则后者后移一位
			int j = i;
			while (j >= 1 && elems[j - 1].compareTo(insertElem) > 0) {
				elems[j] = elems[j - 1];
				j--;
			}
			// 要么不动：i，要么在不大于待插元素的元素之后插入或前面元素都更大则在下标0处插入
			elems[j] = insertElem;
		}
	}

	/**
	 * 快速排序 最好O(nlogn)；平均O(nlogn)；最坏(n2)；空间O(logn)-来自系统栈；不稳定
	 * 
	 * @param elems 元素数组
	 * @param left  闭区间左端下标
	 * @param right 闭区间右端下标
	 * @param index 闭区间内枢轴下标 本方案要求枢轴固定为0，至于随机枢轴有待研究
	 */
	public void quick(Elem[] elems, int left, int right, int index) {
		// 左端下标等于右端下标-仅1个元素，直接返回
		if (left == right) {
			return;
		}
		// 左游标
		int low = left;
		// 右游标
		int high = right;
		// 设当前区间起始元素为枢轴
		Elem pivot = elems[index];
		// 为了把不大元素挪到枢轴左边，不小元素挪到右边，从右往左找更小的元素，从左往右找更大的元素，覆盖空闲区，枢轴起始位置就是第一个空闲区
		while (low < high) {
			// 取不取等号都行
			while (elems[high].compareTo(pivot) >= 0 && low < high) {
				high--;
			}
			elems[low] = elems[high];
			while (elems[low].compareTo(pivot) <= 0 && low < high) {
				low++;
			}
			elems[high] = elems[low];
		}
		// 将枢轴放入游标相遇位置，如此其左边元素均不大，右边元素均不小
		int center = low; // center=low=high
		elems[center] = pivot;
		// 若枢轴左边有元素则递归调用快排
		if (left < center) {
			quick(elems, left, center - 1, left);
		}
		// 若枢轴右边有元素则递归调用快排
		if (right > center) {
			quick(elems, center + 1, right, center + 1);
		}
	}

	/**
	 * 计数排序，仅限整型元素 最好O(n+m)；平均O(n+m)；最坏O(n+m)；空间O(n+m)，不过此处没用到临时数组，故为O(n)；稳定
	 * 
	 * @param elems 元素数组
	 */
	public void count(int[] elems) {
		// 找出最值
		int min = elems[0];
		int max = elems[0];
		for (int i = 0; i < elems.length; i++) {
			if (elems[i] < min) {
				min = elems[i];
			}
			if (elems[i] > max) {
				max = elems[i];
			}
		}
		// 构造计数器，数据非整型则无法构造
		int[] counters = new int[max - min + 1];
		for (int i = 0; i < elems.length; i++) {
			counters[elems[i] - min]++;
		}
		// 遍历计数器，重填元素数组
		int j = 0;
		for (int i = 0; i < counters.length; i++) {
			while (counters[i] != 0) {
				elems[j] = i + min;
				j++;
				counters[i]--;
			}
		}
	}

	/**
	 * 希尔排序 最好O(n)；平均O(nlogn)；最坏O(n2)；空间O(1)；不稳定
	 * 
	 * @param elems
	 */
	public void shell(Elem[] elems) {
		// 待插入元素
		Elem insertValue = null;
		// 最后一次循环的step为1即仅1整组的插入排序
		for (int half = 2; elems.length / half > 0; half *= 2) { // 这里的递推式是翻倍，也有其他的如3*half+1
			// 分组个数或步长
			int step = elems.length / half;
			// 所有分组的第2个元素必在step及之后位置上
			for (int i = step; i < elems.length; i++) {
				int j = i;
				insertValue = elems[j];
				// 插入排序变体-各组内的插入排序，步长由1变成其他数
				while (j >= step && insertValue.compareTo(elems[j - step]) < 0) {
					elems[j] = elems[j - step];
					j -= step;
				}
				// 要么不动：i；要么在j（含0）处插入
				elems[j] = insertValue;
			}
		}
	}

	/**
	 * 二分归并排序 最好O(nlogn)；平均O(nlogn)；最坏O(nlogn)；空间O(n)；稳定
	 * 
	 * @param elems 元素数组
	 * @param left  闭区间左端下标
	 * @param right 闭区间右端下标
	 */
	public void merge(Elem[] elems, int left, int right) {
		/**
		 * 拆分阶段
		 */
		// 二分边界
		int mid = (left + right) / 2;
		// 分到2个元素或1个元素便不再往下分，因这两种情况的两段自然非递减
		if (right - left + 1 > 2) {
			// 递归地排左边
			merge(elems, left, mid);
			// 递归地排右边
			merge(elems, mid + 1, right);
		}
		/**
		 * 合并阶段，左右两组都是非递减的
		 */
		// 双游标
		int low = left;
		int high = mid + 1;
		// 中间数组，从0位置开始填
		Object[] tempElems = new Object[right - left + 1];
		int k = 0;
		// 任一方游标出了边界就退出
		while (low <= mid && high <= right) {
			if (elems[low].compareTo(elems[high]) < 0) {
				tempElems[k] = elems[low];
				k++;
				low++;
			} else {
				tempElems[k] = elems[high];
				k++;
				high++;
			}
		}
		// 必然有且仅有一组先行全部进中间数组，那么一次性插入另一组剩余元素
		if (low > mid) {
			for (int i = high; i <= right; i++, k++) {
				tempElems[k] = elems[i];
			}
		} else {
			for (int i = low; i <= mid; i++, k++) {
				tempElems[k] = elems[i];
			}
		}
		// 将中间列表中的元素对应位置填入原数组
		for (int i = 0, j = left; i < tempElems.length; i++, j++) {
			elems[j] = (Elem) tempElems[i];
		}
	}

	/**
	 * 插入排序，仅限整型元素
	 * 
	 * @param elems 元素数组
	 * @param size  有效元素数
	 */
	public void insert(int[] elems, int size) {
		int insertElem;
		for (int i = 1; i < size; i++) {
			insertElem = elems[i];
			int j = i;
			while (j >= 1 && elems[j - 1] > insertElem) {
				elems[j] = elems[j - 1];
				j--;
			}
			elems[j] = insertElem;
		}
	}

	/**
	 * 基于非等宽区间-位数的桶排序，故限定元素类型为整型
	 * 
	 * @param elems 元素数组
	 */
	public void bucketByIncreasingWidh(int[] elems) {
		// 求最大值以确定最高位，最低位是1
		int max = elems[0];
		for (int i = 0; i < elems.length; i++) {
			if (elems[i] > max) {
				max = elems[i];
			}
		}
		// 字符串长度即位数
		int maxRadix = String.valueOf(max).length();
		// 桶数组：第一维是桶，第二维是桶内元素
		int[][] buckets = new int[maxRadix][elems.length];
		// 桶内元素数记录
		int[] counters = new int[maxRadix];
		// 根据各元素位数将它们入桶
		for (int i = 0; i < elems.length; i++) {
			int radix = String.valueOf(elems[i]).length();
			buckets[radix - 1][counters[radix - 1]++] = elems[i];
		}
		int k = 0;
		// 如此桶与桶之间有序，再使各桶内有序，此处用插入排序
		for (int i = 0; i < buckets.length; i++) {
			insert(buckets[i], counters[i]);
			// 回填原数组
			for (int j = 0; j < counters[i]; j++, k++) {
				elems[k] = buckets[i][j];
			}
		}
	}

	/**
	 * 基于等宽区间的桶排序，此处限定元素类型为整型 最好O(n)；平均O(n)；最坏O(n)；空间O(m)；稳定
	 * 
	 * @param elems 元素数组
	 */
	public void bucketByConstantWidth(int[] elems) {
		// 自行设计区间宽度，区间宽度并不等于桶容量
		int range = 10;
		// 求最值以设计桶数
		int min = elems[0];
		int max = elems[0];
		for (int i = 0; i < elems.length; i++) {
			if (elems[i] < min) {
				min = elems[i];
			}
			if (elems[i] > max) {
				max = elems[i];
			}
		}
		int bucketNum = (int) Math.ceil((max - min) / (double) range);
		// 桶数组
		int[][] buckets = new int[bucketNum][elems.length];
		// 桶内元素数记录
		int[] counters = new int[bucketNum];
		// 根据各元素所属区间将它们入桶
		for (int i = 0; i < elems.length; i++) {
			buckets[(elems[i] - min) / range][counters[(elems[i] - min) / range]++] = elems[i];
		}
		int k = 0;
		for (int i = 0; i < buckets.length; i++) {
			insert(buckets[i], counters[i]);
			// 回填原数组
			for (int j = 0; j < counters[i]; j++, k++) {
				elems[k] = buckets[i][j];
			}
		}
	}

	/**
	 * 基数排序 只能从低位到高位排，桶与桶之间在当前位上是有序的 最好O(n)；平均O(n*m)；最坏O(n2)(m=n)；空间O(n)；稳定
	 * 
	 * @param elems 元素类型 适合整型、字符串等类型
	 */
	public void radix(int[] elems) {
		// 求最大值以得最高位
		int max = elems[0];
		for (int i = 0; i < elems.length; i++) {
			if (elems[i] > max) {
				max = elems[i];
			}
		}
		int maxDigit = String.valueOf(max).length();
		// 如对整型设10个桶，对字母字符串设26个桶
		int radixNum = 10;
		// 桶
		int[][] buckets = new int[radixNum][elems.length];
		// 各桶元素计数器
		int[] counters = new int[radixNum];
		// 从个位开始遍历到最高位
		for (int digit = 0; digit < maxDigit; digit++) {
			// 遍历数组
			for (int i = 0; i < elems.length; i++) {
				// 根据当前元素在当前位的值入桶
				int radix = elems[i] % (int) Math.pow(10, digit + 1) / (int) Math.pow(10, digit);
				buckets[radix][counters[radix]++] = elems[i];
			}
			int k = 0;
			// 将桶内元素回填到原数组
			for (int i = 0; i < buckets.length; i++) {
				for (int j = 0; j < counters[i]; j++) {
					elems[k++] = buckets[i][j];
				}
				counters[i] = 0;
			}
		}
	}

	/**
	 * 堆排序 最好O(nlogn)；平均O(nlogn)；最坏O(nlogn)；空间O(1)；不稳定
	 * 
	 * @param elems 元素数组
	 */
	public void heap(Elem[] elems) {
		int size = elems.length;
		Elem temp = null;
		int cursor = -1;
		// 对数组进行堆化，即调整为大顶堆
		for (int i = (elems.length - 1 - 1) / 2; i >= 0; i--) {
			// 从最后一个非叶子结点往前依次下沉
			cursor = i;
			while (true) {
				// 没孩子
				if (cursor * 2 + 1 >= size) {
					break;
				}
				// 有且仅有一个孩子-左孩子
				if (cursor * 2 + 2 >= size) {
					if (elems[cursor].compareTo(elems[cursor * 2 + 1]) < 0) {
						temp = elems[cursor];
						elems[cursor] = elems[cursor * 2 + 1];
						elems[cursor * 2 + 1] = temp;
					}
					break;
				}
				// 两个孩子
				if (elems[cursor].compareTo(elems[cursor * 2 + 1]) >= 0
						&& elems[cursor].compareTo(elems[cursor * 2 + 2]) >= 0) {
					break;
				}
				int maxChild = elems[cursor * 2 + 1].compareTo(elems[cursor * 2 + 2]) > 0 ? cursor * 2 + 1
						: cursor * 2 + 2;
				temp = elems[maxChild];
				elems[maxChild] = elems[cursor];
				elems[cursor] = temp;
				cursor = maxChild;
			}
		}
		int k = elems.length - 1;
		Object[] tempElems = new Object[elems.length];
		// 不断删除根结点并倒序填入临时数组，同时调整堆结构
		while (size > 0) {
			tempElems[k--] = elems[0];
			elems[0] = elems[size-- - 1];
			cursor = 0;
			while (true) {
				if (cursor * 2 + 1 >= size) {
					break;
				}
				if (cursor * 2 + 2 >= size) {
					if (elems[cursor].compareTo(elems[cursor * 2 + 1]) < 0) {
						temp = elems[cursor];
						elems[cursor] = elems[cursor * 2 + 1];
						elems[cursor * 2 + 1] = temp;
					}
					break;
				}
				if (elems[cursor].compareTo(elems[cursor * 2 + 1]) >= 0
						&& elems[cursor].compareTo(elems[cursor * 2 + 2]) >= 0) {
					break;
				}
				int maxChild = elems[cursor * 2 + 1].compareTo(elems[cursor * 2 + 2]) > 0 ? cursor * 2 + 1
						: cursor * 2 + 2;
				temp = elems[maxChild];
				elems[maxChild] = elems[cursor];
				elems[cursor] = temp;
				cursor = maxChild;
			}
		}
		for (int i = tempElems.length - 1, j = 0; i >= 0; i--, j++) {
			elems[j] = (Elem) tempElems[i];
		}
	}
}
