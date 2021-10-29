package com.wlwallis30.leetcode;

import java.util.*;

public class SearchIn2DMatrix {
  // O(log(m*n))
  public boolean searchMatrix74MimicArray(int[][] matrix, int target) {
    int m = matrix.length;
    if (m == 0)
      return false;
    int n = matrix[0].length;

    // binary search
    int left = 0, right = m * n - 1;
    int pivotIdx, pivotElement;
    while (left <= right) {
      pivotIdx = (left + right) / 2;
      pivotElement = matrix[pivotIdx / n][pivotIdx % n];
      if (target == pivotElement)
        return true;
      else {
        if (target < pivotElement)
          right = pivotIdx - 1;
        else
          left = pivotIdx + 1;
      }
    }
    return false;
  }

  // O(log(m) + log(n))
  public boolean searchMatrix74(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
    int m = matrix.length, n = matrix[0].length;

    // binary search #1: looking for the right row to search for the value
    int start = 0, end = m;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (matrix[mid][n - 1] < target) start = mid + 1;
      else end = mid;
    }

    if (start == m) return false;
    if (matrix[start][n - 1] == target) return true;

    int theRow = start;

    // binary search #2: looking for the target in that row
    start = 0;
    end = n;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (matrix[theRow][mid] < target) start = mid + 1;
      else end = mid;
    }
    return start != n && matrix[theRow][start] == target;
  }

  //左下角和右上角的数。左下角的 18，往上所有的数变小，往右所有数增加,
  //当然也可以把起始数放在右上角
  // O(m+n) coz row can be increased m times, col for n times in while loop
  public boolean searchMatrix240(int[][] matrix, int target) {
    // start our "pointer" in the bottom-left
    int row = matrix.length-1;
    int col = 0;

    while (row >= 0 && col < matrix[0].length) {
      if (matrix[row][col] > target) {
        row--;
      } else if (matrix[row][col] < target) {
        col++;
      } else { // found it
        return true;
      }
    }

    return false;
  }

  interface BinaryMatrix {
    int get(int row, int col);
    List<Integer> dimensions();
  }

  public int leftMostColumnWithOne1428(BinaryMatrix binaryMatrix) {
    int rows = binaryMatrix.dimensions().get(0);
    int cols = binaryMatrix.dimensions().get(1);
    int smallestIndex = cols;
    for (int row = 0; row < rows; row++) {
      // Binary Search for the first 1 in the row.
      int left = 0;
      int right = cols - 1;
      while (left < right) {
        int mid = (left + right) / 2;
        if (binaryMatrix.get(row, mid) == 0) { left = mid + 1; }
        else { right = mid; }
      }
      // If the last element in the search space is a 1, then this row contained a 1.
      if (binaryMatrix.get(row, left) == 1) { smallestIndex = Math.min(smallestIndex, left); }
    }
    // If smallest_index is still set to cols, then there were no 1's in the grid.
    return smallestIndex == cols ? -1 : smallestIndex;
  }

  private static final int[][] directions =
      new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

  //changing the original matrix
  public int shortestPathBinaryMatrix1091(int[][] grid) {
    if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
      return -1;
    }

    // Set up the BFS.
    Queue<int[]> idxQ = new ArrayDeque<>();
    grid[0][0] = 1;
    idxQ.add(new int[]{0, 0});

    // Carry out the BFS
    while (!idxQ.isEmpty()) {
      int[] cell = idxQ.remove();
      int row = cell[0];
      int col = cell[1];
      int distance = grid[row][col];
      if (row == grid.length - 1 && col == grid[0].length - 1) {
        return distance;
      }
      for (int[] neighbour : getNeighbours(row, col, grid)) {
        int neighbourRow = neighbour[0];
        int neighbourCol = neighbour[1];
        idxQ.add(new int[]{neighbourRow, neighbourCol});
        grid[neighbourRow][neighbourCol] = distance + 1;
      }
    }

    // The target was unreachable.
    return -1;
  }

  private List<int[]> getNeighbours(int row, int col, int[][] grid) {
    List<int[]> neighbours = new ArrayList<>();
    for (int i = 0; i < directions.length; i++) {
      int newRow = row + directions[i][0];
      int newCol = col + directions[i][1];
      if (newRow < 0 || newCol < 0 || newRow >= grid.length
          || newCol >= grid[0].length
          || grid[newRow][newCol] != 0) {
        continue;
      }
      neighbours.add(new int[]{newRow, newCol});
    }
    return neighbours;
  }
}
