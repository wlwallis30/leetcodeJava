package com.wlwallis30.leetcode;

import java.util.*;

public class SpiralMatrix {
  List<Integer> spiralOrder54(int[][] matrix) {
    List<Integer> res = new ArrayList<>();
    if (matrix.length == 0 || matrix[0].length == 0) return res;
    int m = matrix.length, n = matrix[0].length;

    int ring = (Math.min(m, n) + 1) / 2;
    int height = m, width = n;

    // ringstep controls where the first row moves, height and width controls the moving boundary
    //since each ringstep/ring, we reduce the boundary by 2, so -2.
    for (int ringStep = 0; ringStep < ring; ++ringStep, height -= 2, width -= 2)
    {
      int row = ringStep, col;
      for (col = ringStep; col<ringStep + width; ++col)
      {
        res.add(matrix[row][col]);
      }
      --col;
      for (row = row + 1; row<ringStep + height; ++row)
      {
        res.add(matrix[row][col]);
      }
      --row;
      // please observe the last ring behavior, if height or width ==1, just move the above, and skip the following
      if (height == 1 || width == 1) break;
      for (col = col - 1; col >= ringStep; --col)
      {
        res.add(matrix[row][col]);
      }
      ++col;
      for (row = row - 1; row>ringStep; --row)
      {
        res.add(matrix[row][col]);
      }
    }

    return res;
  }

  // no need to pre calc the ringStep
  List<Integer> spiralOrder54Better(int[][] matrix) {
    List<Integer> res = new ArrayList<>();
    if (matrix.length == 0 || matrix[0].length == 0) return res;
    int m = matrix.length, n = matrix[0].length;

    // the boundaries
    int up = 0, down = m - 1, left = 0, right = n - 1;
    while (true) {
      for (int j = left; j <= right; ++j) res.add(matrix[up][j]);
      if (++up > down) break;
      for (int i = up; i <= down; ++i) res.add(matrix[i][right]);
      if (--right < left) break;
      for (int j = right; j >= left; --j) res.add(matrix[down][j]);
      if (--down < up) break;
      for (int i = down; i >= up; --i) res.add(matrix[i][left]);
      if (++left > right) break;
    }
    return res;
  }

  int[][] spiralOrder59Better(int n) {
    int[][] res = new int[n][n];

    // the boundaries
    int up = 0, down = n - 1, left = 0, right = n - 1, val = 1;
    while (true) {
      for (int j = left; j <= right; ++j) res[up][j] = val++;
      if (++up > down) break;
      for (int i = up; i <= down; ++i) res[i][right] = val++;
      if (--right < left) break;
      for (int j = right; j >= left; --j) res[down][j] = val++;
      if (--down < up) break;
      for (int i = down; i >= up; --i) res[i][left] = val++;
      if (++left > right) break;
    }
    return res;
  }

  public int[][] generateMatrix59(int n) {
    int ring = (n + 1) / 2;
    int height = n;// width = n;
    int[][] res = new int[n][n];
    int val = 1;

    // ringstep controls where the first row moves, height and width controls the moving boundary
    //since each ringstep/ring, we reduce the boundary by 2, so -2.
    for (int ringStep = 0; ringStep < ring; ++ringStep, height -= 2)
    {
      int row = ringStep, col;
      for (col = ringStep; col<ringStep + height; ++col) res[row][col] = val++;
      --col;
      for (row = row + 1; row<ringStep + height; ++row) res[row][col] = val++;
      --row;
      // please observe the last ring behavior, if height or width ==1, just move the above, and skip the following
      if (height == 1) break;
      for (col = col - 1; col >= ringStep; --col) res[row][col] = val++;
      ++col;
      for (row = row - 1; row>ringStep; --row) res[row][col] = val++;
    }

    return res;
  }
}
