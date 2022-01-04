package com.wlwallis30.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;

public class InsertPosition {
  public int searchInsert_35(int[] nums, int target) {
    // we are searching for the lower left bound/minimum of larger value
    // so the max index can be out of index, e.g. [1,3,5,6], target = 7, ans should be 4, so right is the length
    int mid, left = 0, right = nums.length;
    while (left < right) {
      // this will prevent the overflow of left + right
      mid = left + (right - left) / 2;
      if (target > nums[mid]) left = mid + 1;
      else right = mid; // including == condition
    }
    return left;
  }

  // looking for minimum of the largest value / left bound type
  int firstBadVersion_278(int n) {
    int left = 1, right = n;
    while (left < right) {
      int mid = left + (right - left) / 2;
      //searching for bad, so invalid case condition is !isBad
      if (!isBadVersion(mid)) left = mid + 1;
      else right = mid;
    }
    return left;
  }

  boolean isBadVersion(int version) { return true;} //fake API

  // better use right=mid + (lef<right) in while since you are finding the boundaries
  //also refer to 33
  int[] searchRange34(int[] nums, int target) {
    int[] res = new int[2];
    Arrays.fill(res, -1);
    if(nums == null || nums.length == 0)return res;
    int left=0,right=nums.length-1, mid;

    while(left<right)
    {
      mid=(left+right)/2;
      //不断搜索 找到左 界 注意只有<  样就是在步步用left紧逼 右边right则放宽条件可以等于target
      if(nums[mid]<target) left=mid+1;
      else right=mid;
    }
    if(nums[left]!=target) return res;

    res[0]=left;
    right=nums.length-1;

    while(left<right)
    {
      mid=(left+right+1)/2;
      //不断搜索 找到右 界 注意只有>
      if(nums[mid]>target) right=mid-1;
      else left=mid;
    }

    res[1]=left; //after changing right =mid-1, this is enough.

    //now left == right, System.out.println("left:" + left + "   right:" + right);
    //if(nums[left] == target)res[1]=left;
      //[5,7,7,8,8,10], 8, if just using left, will give [3,5] as result, wrong!
      // reason: since (nums[mid]>target) right=mid, then mid num might be larger than target then jump out while loop
    //else res[1]=left - 1;
    return res;
  }
}
