package com.wlwallis30.leetcode;

public class UniqueBSt {
  /*
  total number of unique BST G(n), is the sum of BST F(i,n) enumerating each number i (1 <= i <= n) as a root.
  G(n)=∑F(i,n)    ----------------------------(1)
  F(3,7)=G(2)⋅G(4). To generalise from the example, we could derive the following formula:
  F(i,n)=G(i−1)⋅G(n−i)  ---------------------(2)
  By combining the formulas (1), (2), we obtain a recursive formula for G(n)G(n), i.e.
  G(n)=∑G(i−1)⋅G(n−i) ------------------------(3)
   */
  public int numTrees96(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1; // empty tree can be also consider as one case
    dp[1] = 1;

    for (int i = 2; i <= n; ++i) {
      for (int j = 1; j <= i; ++j) {
        dp[i] += dp[j - 1] * dp[i - j];
      }
    }
    return dp[n];
  }
}
