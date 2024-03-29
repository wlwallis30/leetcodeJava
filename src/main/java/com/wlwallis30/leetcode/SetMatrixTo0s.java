package com.wlwallis30.leetcode;

public class SetMatrixTo0s {
  //space O(m+n)的方法是，用一个长度为m的一维数组记录各行中是否有0(in place: 用第一列)，
  // 用一个长度为n的一维数组记录各列中是否有0(in place: 用第一行), 所以在存储前，需要先记录第一行和列是否要置0
  //but need O(1)!! so we can use the first row and first col as these 2 arrays, but b4 doing so, we need to record if they have 0 originally.
  void setZeroes73(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0 || matrix == null) return;
    int m = matrix.length, n = matrix[0].length;
    boolean firstRowZeroFlag = false, firstColZeroFlag = false;

    for(int i =0; i<m;++i) if(matrix[i][0]==0) firstColZeroFlag = true;
    for(int j=0; j<n; ++j) if(matrix[0][j]==0) firstRowZeroFlag =true;

    for(int i=1; i<m;++i)
      for(int j=1; j<n;++j)
        if(matrix[i][j]==0) { matrix[i][0]=0; matrix[0][j]=0; }
        // you are touching the first row and col!!!

    for(int i=1; i<m;++i)
      for(int j=1; j<n;++j)
        if(matrix[i][0]==0 || matrix[0][j]==0) matrix[i][j]=0;

    //need to handle special case where flag are true previously since you touched the first row and col!!!
    if(firstColZeroFlag) for(int i =0; i<m;++i) matrix[i][0]=0;
    if(firstRowZeroFlag) for(int j =0; j<n;++j) matrix[0][j]=0;
  }

  // case 1: live to live:  live neighbors == 2 or 3  ------> implied with case 3
  // case 2: death to death: live neighbors != 3 ---------> implied with case 4
  // case 3: live to death: live neighbors <2 or >3 -----> need to mark as special to remember previous live state
  // case 4: death to live: live neighbors == 3  --------> need to mark as special to remember previous dead state
  void gameOfLife289(int[][] board) {
    if(board == null || board.length == 0 || board[0].length == 0) return;
    int m=board.length, n=board[0].length;
    int deltaX[]={-1,-1,-1, 0,0,1,1,1};
    int deltaY[]={-1,0,1,-1,1,-1,0,1};
    //int x, y;
    for(int i=0;i<m;++i) {
      for(int j=0;j<n;++j) {
        int count=0;
        for(int k=0;k<8;++k) {
          int x = i+deltaX[k], y = j+deltaY[k];
          // should also consider case 3(live neighbors has <2 or >3, live to dead) as live now!!
          if(x>=0 && x <m && y>=0 &&y<n && (board[x][y]==1 || board[x][y]==2)) ++count;
        }

        // reset the board
        if(board[i][j]==1 && (count<2 || count >3))board[i][j]=2; //case 3,
          //else if(count==2|| count==3) board[i][j] =1;  case1, this condition is implied
        else if(board[i][j]==0 && count==3) board[i][j] =3; // case 4, also implying case 2
      }
    }

    for(int i=0;i<m;++i) for(int j=0;j<n;++j) board[i][j]=board[i][j]%2;
  }
}