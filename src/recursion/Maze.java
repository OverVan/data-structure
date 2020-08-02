package recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 迷宫。1表示墙；2表示经过；0表示未经过
 * 
 * @author Van
 */
public class Maze {
	private int row;// 迷宫行数
	private int column;// 迷宫列数
	private int[][] map;// 迷宫地图。二维数组默认填充0
	private int endX;
	private int endY;

	public Maze(int row, int column, int endX, int endY) {
		this.row = row;
		this.column = column;
		this.endX = endX;
		this.endY = endY;
		map = new int[row][column];
		// 上下两行有墙
		for (int i = 0; i < column; i++) {
			map[0][i] = 1;
			map[row - 1][i] = 1;
		}
		// 左右两列有墙
		for (int i = 0; i < row; i++) {
			map[i][0] = 1;
			map[i][column - 1] = 1;
		}
		// 内部的墙
		map[3][1] = 1;
		map[3][2] = 1;
	}

	/**
	 * 显示迷宫
	 */
	public void showMaze() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(map[i][j] + " ");
			}
			// 打印1行后换行
			System.out.println();
		}
	}

	/**
	 * 从起点到终点按下右上左的检索顺序找到通路
	 * 
	 * @param x 行坐标
	 * @param y 列坐标
	 * @return
	 */
	public void findWayByBRTL(int x, int y) {
		// 前进一步
		map[x][y] = 2;
		if (x == endX && y == endY) {
			return;
		}
		if (map[x + 1][y] == 0) {
			// 向下走一步
			findWayByBRTL(x + 1, y);
		} else if (map[x][y + 1] == 0) {
			// 向右走一步
			findWayByBRTL(x, y + 1);
		} else if (map[x - 1][y] == 0) {
			// 向上走一步
			findWayByBRTL(x - 1, y);
		} else if (map[x][y - 1] == 0) {
			// 向左走一步
			findWayByBRTL(x, y - 1);
		}
	}

	/**
	 * 从起点到终点按上右下左的检索顺序找到通路
	 * 
	 * @param x 行坐标
	 * @param y 列坐标
	 */
	public void findWayByTRBL(int x, int y) {
		// 前进一步
		map[x][y] = 2;
		if (x == endX && y == endY) {
			return;
		}
		if (map[x - 1][y] == 0) {
			// 向上走一步
			findWayByBRTL(x - 1, y);
		} else if (map[x][y + 1] == 0) {
			// 向右走一步
			findWayByBRTL(x, y + 1);
		} else if (map[x + 1][y] == 0) {
			// 向下走一步
			findWayByBRTL(x + 1, y);
		} else if (map[x][y - 1] == 0) {
			// 向左走一步
			findWayByBRTL(x, y - 1);
		}
	}

	/**
	 * 从起点到终点按右下左上的检索顺序找到通路
	 * 
	 * @param x 行坐标
	 * @param y 列坐标
	 */
	public void findWayByRBLT(int x, int y) {
		// 前进一步
		map[x][y] = 2;
		if (x == endX && y == endY) {
			return;
		}
		if (map[x][y + 1] == 0) {
			// 向右走一步
			findWayByBRTL(x, y + 1);
		} else if (map[x + 1][y] == 0) {
			// 向下走一步
			findWayByBRTL(x + 1, y);
		} else if (map[x][y - 1] == 0) {
			// 向左走一步
			findWayByBRTL(x, y - 1);
		} else if (map[x - 1][y] == 0) {
			// 向上走一步
			findWayByBRTL(x - 1, y);
		}
	}

	/**
	 * 左下右上；利用循环
	 * 
	 * @param x 行坐标
	 * @param y 列坐标
	 */
	public void findWayByLBRT(int x, int y) {
		while (true) {
			if (x == endX && y == endY) {
				return;
			}
			if (map[x][y - 1] == 0) {
				// 向左走一步
				map[x][--y] = 2;
			} else if (map[x + 1][y] == 0) {
				// 向下走一步
				map[++x][y] = 2;
			} else if (map[x][y + 1] == 0) {
				// 向右走一步
				map[x][++y] = 2;
			} else if (map[x - 1][y] == 0) {
				// 向上走一步
				map[--x][y] = 2;
			}
		}
	}

	/**
	 * 算路径长度
	 * 
	 * @return
	 */
	public int measurePath() {
		int length = 0;
		for (int[] row : map) {
			for (int item : row) {
				if (item == 2) {
					length++;
				}
			}
		}
		return length;
	}

	/**
	 * 求路径最短的所取策略
	 * 
	 * @param startX 起点行坐标
	 * @param startY 起点列坐标
	 * @return 顺序-最短路径
	 */
	public void getShortest(int startX, int startY) {
		findWayByBRTL(startX, startY);
		int brtl = measurePath();
		System.out.println("下右上左：" + brtl);
		findWayByRBLT(startX, startY);
		int rblt = measurePath();
		System.out.println("右下左上：" + rblt);
		findWayByTRBL(startX, startY);
		int trbl = measurePath();
		System.out.println("上右下左：" + trbl);
		// 用映射存放各路线名及其路径长度
		List<Integer> lengthList = new ArrayList<Integer>();
		lengthList.add(brtl);
		lengthList.add(rblt);
		lengthList.add(trbl);
		Integer min = Collections.min(lengthList);
		System.out.println("最短为：" + min);
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Maze maze = new Maze(8, 7, 6, 5);
		maze.getShortest(1, 1);
	}

}
