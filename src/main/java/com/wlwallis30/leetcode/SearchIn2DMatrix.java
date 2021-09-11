package com.wlwallis30.leetcode;

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
}
