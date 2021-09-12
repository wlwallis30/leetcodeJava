package com.wlwallis30.leetcode;

public class WordSearch {
  // O(m * n * 3^L) is the length of word.
  // cell has only 3 directions to be potentially explored because one direction has been already visited by its parent
  boolean exist79(char[][] board, String word) {
    if(word.length()==0) return true;
    int m = board.length, n = board[0].length;
    if(m == 0 || n == 0) return false;
    boolean[][] visited = new boolean[m][n];
    for(int i=0; i<m ;++i) {
      for(int j=0; j<n;++j) { if(searchDFS(board, word, visited, 0, i, j)) return true; }
    }

    return false;
  }

  boolean searchDFS(char[][] board, String word, boolean[][] visited, int idx, int i, int j) {
    if(word.length() == idx) return true;

    if(i<0 || j <0 || i >= board.length || j>=board[0].length
        || visited[i][j] || word.charAt(idx)!=board[i][j] ) return false;

    // int[] rowOffsets = {0, 1, 0, -1};
    visited[i][j] = true;
    boolean res = searchDFS(board, word, visited, idx + 1, i - 1, j)
        || searchDFS(board, word, visited, idx + 1, i+1, j )
        || searchDFS(board, word, visited, idx + 1, i, j-1 )
        || searchDFS(board, word, visited, idx + 1, i, j+1 );
    visited[i][j]=false;
    return res;
  }
}
