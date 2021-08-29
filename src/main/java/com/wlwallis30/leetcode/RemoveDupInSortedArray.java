package com.wlwallis30.leetcode;

public class RemoveDupInSortedArray {
  public int removeDuplicates_26(int[] nums) {
    if (nums.length == 0) return 0;
    int keepPos = 0;
    for (int probe = 1; probe < nums.length; probe++) {
      if (nums[probe] != nums[keepPos]) {
        keepPos++;
        nums[keepPos] = nums[probe];
      }
    }
    return keepPos + 1;
  }
}
