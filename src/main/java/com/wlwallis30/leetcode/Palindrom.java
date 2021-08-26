package com.wlwallis30.leetcode;

public class Palindrom {
  public String palindrom_5(String str) {
    if(str.isEmpty()) return "".toString();
    String res = "";
    int leftPalin = 0, rightPalin = 0;
    int lenPalin = 0;
    int size = str.length();
    int[][] dp = new int[size][size];
    for(int end = 0; end < size; ++end) {
      dp[end][end] = 1;
      for(int start = 0; start < end; ++start) {
        if(str.charAt(start) == str.charAt(end)
            && (dp[start+1][end-1] == 1 || start == end-1))
        { dp[start][end] = 1; }
        else dp[start][end] = 0;

        if(dp[start][end] ==1 && lenPalin < end-start+1) {
          lenPalin = end-start+1;
          leftPalin = start;
          rightPalin = end;
        }
      }
    }

    lenPalin =  Math.max(lenPalin, rightPalin-leftPalin+1);
    // java's sub string: extend to char whose index = endIndex-1
    res = str.substring(leftPalin+0, rightPalin+1);
    return res;
  }
}
