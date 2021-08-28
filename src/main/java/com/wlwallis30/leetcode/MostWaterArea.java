package com.wlwallis30.leetcode;

public class MostWaterArea {
  public int maxArea_11(int[] height) {
    int left = 0, right = height.length-1;
    int res = 0;
    int minHeight = Integer.MAX_VALUE;
    while(left < right) {
      minHeight = Math.min(height[left], height[right]);
      res = Math.max(res, (right - left)*minHeight);
      if(minHeight == height[left]) --right;
      else ++left;
    }
    return res;
  }
}
