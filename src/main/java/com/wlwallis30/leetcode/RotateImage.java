package com.wlwallis30.leetcode;

public class RotateImage {
  void rotate48(int[][] matrix) {
    if(matrix.length == 0 ||matrix[0].length == 0) return;
    int m=matrix.length, n=matrix[0].length;

    for(int row=0;row<m;++row)
    {
      //以\ 方向为对 线 交换 然后每行 倒顺序, 按对 线交换时候注意col=row+1, 不然会交换两次 又换回来
      for(int col=row+1;col<n;++col)  {
        int tmp = matrix[row][col];
        matrix[row][col] =  matrix[col][row];
        matrix[col][row] = tmp;
      }

      int left=0, right=n-1;
      while(left<right){
        int tmp  = matrix[row][left];
        matrix[row][left] = matrix[row][right];
        matrix[row][right] = tmp;
        ++left;--right;
      }
    }

    return;
  }
}
