package com.wlwallis30.leetcode;

public class ClimbChairs {
  /*
                            3
                       +2 /  \  +1
                        /     \
                      dp[2]   dp[1]
                     / \       /
                   2 or 1+1   1
   */
  public int climbStairs_70(int n) {
    if (n <= 1) return 1;
    int[] dp = new int[n];
    dp[0] = 1; dp[1] = 2;
    for (int i = 2; i < n; ++i) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n - 1];
  }

}
