package com.wlwallis30.leetcode;

import java.util.*;

public class SurroundedRegions {
  //start from the boundary 0, go deeper with connected 0, mark all of them to $, other 0 in the middle will die
  void solve130(char[][] board) {
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[i].length; ++j) {
        if ((i == 0 || i == board.length - 1 || j == 0 || j == board[i].length - 1) && board[i][j] == 'O')
          solveDFS(board, i, j);
      }
    }
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[i].length; ++j) {
        if (board[i][j] == 'O') board[i][j] = 'X'; // die!
        if (board[i][j] == '$') board[i][j] = 'O'; // base only connected to 延安 can survive
      }
    }
  }
  void solveDFS(char[][]board, int i, int j) {
    if (board[i][j] == 'O') {
      board[i][j] = '$';
      if (i > 0 && board[i - 1][j] == 'O') solveDFS(board, i - 1, j);
      if (i < board.length - 1 && board[i + 1][j] == 'O') solveDFS(board, i + 1, j);
      if (j > 0 && board[i][j - 1] == 'O') solveDFS(board, i, j - 1);
      if (j < board[i].length - 1 && board[i][j + 1] == 'O') solveDFS(board, i, j + 1);
    }
  }

  // you can use int[][] visited, but more space. just mark the vistied 1s to 0s, will be easier
  public int numIslands200(char[][] grid) {
    if (grid == null || grid.length == 0) { return 0; }

    int m = grid.length;
    int n = grid[0].length;
    int res = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == '1') {
          ++res;
          islandDFS(grid, r, c);
        }
      }
    }

    return res;
  }

  void islandDFS(char[][] grid, int r, int c) {
    int m = grid.length;
    int n = grid[0].length;
    if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == '0') { return; }

    grid[r][c] = '0'; // now this cell is visited
    islandDFS(grid, r - 1, c);
    islandDFS(grid, r + 1, c);
    islandDFS(grid, r, c - 1);
    islandDFS(grid, r, c + 1);
  }

  // O(m*n * m*n)
  void wallsAndGates286(int[][]rooms) {
    for (int i = 0; i < rooms.length; ++i) {
      for (int j = 0; j < rooms[i].length; ++j) {
        if (rooms[i][j] == 0) gateDFS(rooms, i, j, 0);
      }
    }
  }
  void gateDFS(int[][]rooms, int i, int j, int val) {
    // < val, means it already visited
    if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[i].length || rooms[i][j] < val) return;
    rooms[i][j] = val;
    gateDFS(rooms, i + 1, j, val + 1);
    gateDFS(rooms, i - 1, j, val + 1);
    gateDFS(rooms, i, j + 1, val + 1);
    gateDFS(rooms, i, j - 1, val + 1);
  }

  // with Q, O(m*n), space O(m*n)
  public void wallsAndGates286ByQ(int[][] rooms) {
    int m = rooms.length;
    if (m == 0) return;
    int n = rooms[0].length;
    Queue<int[]> q = new LinkedList<>();
    for (int row = 0; row < m; row++) {
      for (int col = 0; col < n; col++) {
        if (rooms[row][col] == 0) { q.add(new int[] { row, col }); }
      }
    }
    int[] deltaRow = {0, 0, 1, -1};
    int[] deltaCol = {1, -1, 0, 0};
    while (!q.isEmpty()) {
      int[] point = q.poll();
      int row = point[0];
      int col = point[1];
      for (int i = 0; i <  4; ++ i) {
        int r = row + deltaRow[i];
        int c = col + deltaCol[i];
        if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != Integer.MAX_VALUE) { continue; }
        rooms[r][c] = rooms[row][col] + 1;
        q.add(new int[] { r, c });
      }
    }
  }
}
