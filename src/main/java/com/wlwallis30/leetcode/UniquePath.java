package com.wlwallis30.leetcode;

import java.util.Arrays;

public class UniquePath {
  //状态转移方程为:  dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
  int uniquePaths62(int m, int n) {
    int[][] dp = new int[m][n];

    //if 2 dimensional, only 0 row and 0 col need to be filled with 1, others can be 0, but keep like this
    for(int i = 0; i<m; ++i){
      Arrays.fill(dp[i], 1);
    }

    // 0 row and 0 col should always be 1 possible, so start with 1
    //or you can make dp[0][0]=1, fill all other with 0, then start with row=0 col=0, just skip [0][0], then upper and left bound will also be filled with 1
    for(int row = 1; row<m; ++row) {
      for(int col =1; col < n; ++col) {
        dp[row][col] = dp[row-1][col] + dp[row][col-1];
      }
    }
    return dp[m-1][n-1];
  }

  //the above algo can be opted to 1 dimensional array
  //just like update along col, then down one row, then along col, etc
  int uniquePaths62JustArray(int m, int n) {
    int[] dp = new int[n];
    Arrays.fill(dp, 1);

    // 0 row and 0 col should always be 1 possible, so start with 1
    //if 1 dimensional, 0 row and 0 col need to be filled with 1, others should be filled with 1 as well
    for(int row = 1; row<m; ++row) {
      for(int col =1; col < n; ++col) {
        dp[col] = dp[col] + dp[col-1];
      }
    }
    return dp[n-1];
  }

  // since we dont know where is the rock, so we need to start from row=0 and col=0
  int uniquePathsWithObstacles63(int[][] obstacleGrid) {
    if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
    if(obstacleGrid[0][0]==1) return 0;

    int m = obstacleGrid.length, n = obstacleGrid[0].length;
    int[] dpEachRowSteps = new int[n];
    dpEachRowSteps[0]=1;

    for(int row =0; row<m;++row)
    {
      for(int col =0; col<n;++col)
      {
        if(obstacleGrid[row][col]==1) dpEachRowSteps[col] = 0;
        else if(col>0) dpEachRowSteps[col]+=dpEachRowSteps[col-1];
        //then now: col = 0 && not a rock, we do nothing, dp[0] was initialized to 1 already.
      }
    }

    return dpEachRowSteps[n-1];
  }

  public int minPathSum64JustArray(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[] dpEachRowSteps = new int[n];
    Arrays.fill(dpEachRowSteps, Integer.MAX_VALUE);
    dpEachRowSteps[0] = 0;

    for(int row =0; row<m;++row) {
      for(int col =0; col<n;++col) {
        if(col == 0) dpEachRowSteps[col] += grid[row][col];
        // col actually represent previous row's result, while col-1 is from current row updating
        else dpEachRowSteps[col] = grid[row][col] + Math.min(dpEachRowSteps[col-1], dpEachRowSteps[col]);
      }
    }

    return dpEachRowSteps[n-1];
  }
}
