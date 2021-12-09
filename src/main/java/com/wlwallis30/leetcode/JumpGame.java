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

  boolean canJump55Greedy(int[] nums) {
    int n = nums.length, reach = 0;
    for (int i = 0; i < n; ++i) {
      // i>reach means can not reach. either case of following should break
      if (i > reach || reach >= n - 1) break;
      reach = Math.max(reach, i + nums[i]);
    }
    return reach >= n - 1;
  }

  // the farthest is as same as above reach logic: greedy
  public int jump45(int[] nums) {
    //curJumpEnd to mark the end of the range that we can jump to
    int minJumps = 0, curJumpEnd = 0, farthest = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      // we continuously find the how far we can reach in the current jump
      farthest = Math.max(farthest, i + nums[i]);
      // if we have come to the max of the current jump range/subarray, it might jump from this end or some other idx b4 this end
      // we need to make another jump
      if (i == curJumpEnd) {
        minJumps++;
        curJumpEnd = farthest;
      }
    }
    return minJumps;
  }
}
