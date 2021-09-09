package com.wlwallis30.leetcode;

public class SearchInRotatedSortArray {
  public int search33(int[] nums, int target) {
    int n = nums.length;
    if (n == 0)
      return -1;
    int left = 0, right = n - 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (nums[mid] == target)
        return mid;
      else if (nums[mid] < nums[right]) //the right part is in order and ascending
      {
        if (nums[mid] < target && target <= nums[right])
          left = mid + 1; //if this condition not fit, means target could be in left part
        else
          right = mid - 1;
      } else {
        if (nums[mid] > target && target >= nums[left])
          right = mid - 1; //if this condition not fit, means target could be in right part
        else
          left = mid + 1;
      }
    }
    return -1;
  }

  public boolean searchWithRepeat81(int[] nums, int target) {
    int n = nums.length;
    if (n == 0)
      return true;
    int left = 0, right = n - 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (nums[mid] == target)
        return true;
      else if (nums[mid] < nums[right]) //the right part is in order and ascending
      {
        if (nums[mid] < target && target <= nums[right])
          left = mid + 1; //if this condition not fit, means target could be in left part
        else
          right = mid - 1;
      } else if(nums[mid] > nums[right]) {
        if (nums[mid] > target && target >= nums[left])
          right = mid - 1; //if this condition not fit, means target could be in right part
        else
          left = mid + 1;
        //[3 1 1] 和 [1 1 3 1]，对于这两种情况中间值等于最右值时，目标值3既可以在左边又可以在右边, just move
      } else --right;
    }
    return false;
  }

  public int findMin153(int[] nums) {
    int left = 0, right = (int)nums.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      //不用管左半段 是右半段是有序的 A[mid]>A[right]左半段升序,最小在右边 
      if (nums[mid] > nums[right]) left = mid + 1;
      // right = mid - 1 with left <= right of while loop, can not be used in this case due to  [4 5 6 0 1 2 3], 0 is mid now
      else right = mid;
    }
    return nums[right];
  }
}

