package com.wlwallis30.leetcode;

public class InsertPosition {
  public int searchInsert_35(int[] nums, int target) {
    int mid, left = 0, right = nums.length - 1;
    while (left <= right) {
      // this will prevent the overflow of left + right
      mid = left + (right - left) / 2;
      if (nums[mid] == target) return mid;
      if (target < nums[mid]) right = mid - 1;
      else left = mid + 1;
      // if using left = mid, the while should use, left < right
    }
    return left;
  }

  int firstBadVersion_278(int n) {
    int left = 1, right = n;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (isBadVersion(mid)) right = mid - 1;
      else left = mid + 1;
    }
    return left;
  }

  boolean isBadVersion(int version) { return true;} //fake API
}
