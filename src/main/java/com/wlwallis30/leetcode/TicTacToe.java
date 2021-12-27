package com.wlwallis30.leetcode;

//348, O(1), space O(n) better than brute force of O(n), space O(nxn)
// by voting force
public class TicTacToe {
  int[] rows;
  int[] cols;
  int diagonal;
  int antiDiagonal;

  public TicTacToe(int n) {
    rows = new int[n];
    cols = new int[n];
  }

  public int move(int row, int col, int player) {
    int currentPlayer = (player == 1) ? 1 : -1;
    // update currentPlayer in rows and cols arrays
    rows[row] += currentPlayer;
    cols[col] += currentPlayer;
    // update diagonal
    if (row == col) { diagonal += currentPlayer; }
    //update anti diagonal
    if (col + row == (cols.length - 1)) {
      antiDiagonal += currentPlayer;
    }
    int n = rows.length;
    // check if the current player wins
    if (Math.abs(rows[row]) == n ||
        Math.abs(cols[col]) == n ||
        Math.abs(diagonal) == n ||
        Math.abs(antiDiagonal) == n) {
      return player;
    }
    // No one wins
    return 0;
  }

  // in total, 6 invalid states
  public boolean validTicTacToe794(String[] board) {
    int player1 = 0;
    int player2 = 0;
    boolean player1Win = false;
    boolean player2Win = false;

    int[] rows = new int[3];
    int[] cols = new int[3];
    int[] diag = new int[2];

    for (int i = 0; i < 3; i++) {
      String s = board[i];

      for (int j = 0; j < 3; j++) {

        char c = s.charAt(j);
        if (c == 'X') {
          rows[i]++;
          cols[j]++;
          player1++;
          if (i == j) diag[0]++;
          if (i + j == 2) diag[1]++;
          if (rows[i] == 3 || cols[j] == 3 || diag[0] == 3 || diag[1] == 3) {
            //need to intercept here, both win, so return false
            if (player2Win) {
              return false;
            }
            player1Win = true;
          }
        } else if (c =='O') {
          rows[i]--;
          cols[j]--;
          player2++;
          if (i == j) diag[0]--;
          if (i + j == 2) diag[1]--;
          if (rows[i] == -3 || cols[j] == -3 || diag[0] == -3 || diag[1] == -3) {
            // both win, so return false
            if (player1Win) {
              return false;
            }
            player2Win = true;
          }
        }
      }
    }
    // all invalid states inside the ().
    return !(player1 < player2 || Math.abs(player1-player2) > 1
        || (player1Win && player1 == player2) || (player2Win && player1 > player2));
    // player1 win, player2 still play;    or player2 win, player1 still play
  }
}
