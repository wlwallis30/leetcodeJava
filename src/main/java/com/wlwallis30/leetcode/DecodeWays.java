package com.wlwallis30.leetcode;

import java.util.*;

public class DecodeWays {
  Map<Integer, Integer> memo = new HashMap<>();

  // 08, 010, 60, 90, 909 are not valid numbers.
  //O(N), where NN is length of the string. Memoization helps in pruning the recursion tree and hence decoding for an index only once.
  public int numDecodings91Recur(String s) {
    //for(Integer key: memo.keySet()){ System.out.println(key);}
    return recursiveWithMemo(0, s);
  }

  private int recursiveWithMemo(int index, String str) {
    // Have we already seen this substring?
    // for this recursive approach,  you will not memo the last index
    //this is like DP, each index will only be memoed if it tried current digit then the two digits
    if (memo.containsKey(index)) return memo.get(index);

    // If you reach the end of the string, the case for two chars at the end, such as 1 26
    // Return 1 for success.
    if (index == str.length()) return 1;

    // If the string starts with a zero, it can't be decoded
    if (str.charAt(index) == '0') return 0;

    // that is the case for 1 char at the end, such as 12 6
    if (index == str.length() - 1) return 1;

    int ans = recursiveWithMemo(index + 1, str);
    if (Integer.parseInt(str.substring(index, index + 2)) <= 26) {
      ans += recursiveWithMemo(index + 2, str);
    }
    // Save for memoization
    memo.put(index, ans);

    return ans;
  }

  public int numDecodings91DP(String s) {
    // DP array to store the subproblem results
    int[] dp = new int[s.length() + 1];
    dp[0] = 1;

    // Ways to decode a string of size 1 is 1. Unless the string is '0'.
    // '0' doesn't have a single digit decode.
    dp[1] = s.charAt(0) == '0' ? 0 : 1;

    for(int i = 2; i < dp.length; i++) {
      // Check if successful single digit decode is possible.
      if (s.charAt(i - 1) != '0') {
        dp[i] = dp[i - 1];
      }

      // Check if successful two digit decode is possible.
      int twoDigit = Integer.valueOf(s.substring(i - 2, i));
      if (twoDigit >= 10 && twoDigit <= 26) {
        dp[i] += dp[i - 2];
      }
    }

    return dp[s.length()];
  }
}
