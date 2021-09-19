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

  //constrains: nums[-1] = nums[n] = -∞, you can assume this. nums[i] != nums[i + 1]. Peak must exist
  //如果第二个数字比第一个数字小，说明此时第一个数字就是一个局部峰值；否则就往后继续遍历，现在是个递增趋势，如果此时某个数字小于前面那个数字，说明前面数字就是一个局部峰值
  public int findPeakElement162Linear(int[] nums) {
    for (int i = 1; i < nums.length; ++i) {
      if (nums[i] < nums[i - 1]) return i - 1;
    }
    return nums.length - 1;
  }
  int findPeakElement162BinarySearch(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    //since you will be using mid+1, better use left<right in while
    while (left < right) {
      int mid = (left + right) / 2;
      if (nums[mid] < nums[mid + 1]) left = mid + 1;
      else right = mid;
    }
    return right;
  }
}

