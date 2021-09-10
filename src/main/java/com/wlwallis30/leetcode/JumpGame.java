package com.wlwallis30.leetcode;

import java.util.Arrays;

public class JumpGame {
  boolean canJump55(int[] nums) {
    int n = nums.length;
    int[] powerLeft = new int[n];
    Arrays.fill(powerLeft,0);
    for (int i = 1; i < n; ++i) {
      powerLeft[i] = Math.max(powerLeft[i - 1], nums[i - 1]) - 1;
      if (powerLeft[i] < 0) return false;
    }
    return powerLeft[n - 1] >= 0;
  }
}
