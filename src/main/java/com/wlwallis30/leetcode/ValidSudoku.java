package com.wlwallis30.leetcode;

public class ValidSudoku {
  public boolean isValidSudoku36(char[][] board) {
    int N = 9;

    // Use an array to record the status
    int[][] rows = new int[N][N];
    int[][] cols = new int[N][N];
    int[][] boxes = new int[N][N];

    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        // Check if the position is filled with number
        if (board[row][col] == '.') {
          continue;
        }
        int num = board[row][col] - '1';

        // Check the row
        if (rows[row][num] == 1) {
          return false;
        }
        rows[row][num] = 1;

        // Check the column
        if (cols[col][num] == 1) {
          return false;
        }
        cols[col][num] = 1;

        // Check the box
        int idx = (row / 3) * 3 + col / 3;
        if (boxes[idx][num] == 1) {
          return false;
        }
        boxes[idx][num] = 1;
      }
    }
    return true;
  }
}
