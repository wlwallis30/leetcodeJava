package com.wlwallis30.leetcode;

import java.util.*;

public class MissingRange {
  public List<String> findMissingRanges163(int[] nums, int lower, int upper) {
    List<String> result = new ArrayList<>();
    int prevNum = lower - 1;

    // prevNum and curNum are like two pointers. curNum - prevNum >= 1
    // then we need to insert string "prevNum+1 -> curNum-1", condition rewritten to curNum-1 >= prevNum+1
    //so, in order to apply these to two edge cases, lower-1, upper+1, and let last i = nums.length to handle possible edge case for upper.
    for (int i = 0; i <= nums.length; i++) {
      int curNum = (i < nums.length) ? nums[i] : upper + 1;
      if (prevNum + 1 <= curNum - 1) {
        result.add(formatRange(prevNum + 1, curNum - 1));
      }
      prevNum = curNum;
    }
    return result;
  }

  // formats range in the requested format
  private String formatRange(int lower, int upper) {
    if (lower == upper) {
      return String.valueOf(lower);
    }
    return lower + "->" + upper;
  }

  public List<String> summaryRanges228(int[] nums) {
    List<String> summary = new ArrayList<>();
    int start;
    for (int end = 0; end < nums.length; ++end){
      start = end;
      // moving when continuous nums.
      while (end + 1 < nums.length && nums[end + 1] == nums[end] + 1) ++end;
      // gen string
      if (start == end)
        summary.add(nums[start] + "");
      else
        summary.add(nums[start] + "->" + nums[end]);
    }
    return summary;
  }

  // missing kth positive number
  public int findKthPositive1539(int[] arr, int k) {
    // if the kth missing is less than arr[0],  [3, 4, 6, 7...] k=2
    if (k <= arr[0] - 1) {
      return k;
    }
    k = k- (arr[0] - 1);

    int n = arr.length;
    for (int i = 0; i < n - 1; ++i) {
      // missing between arr[i] and arr[i + 1], [...3, 6, ....]
      int curMissCnt = arr[i + 1] - arr[i] - 1;
      // if the kth missing is between arr[i] and arr[i + 1]
      if (k <= curMissCnt) {
        return arr[i] + k;
      }
      // otherwise, proceed further
      k -= curMissCnt;
    }

    // if the missing number if greater than arr[n - 1]
    return arr[n - 1] + k;
  }

  //[1, 2, 3, 4, 5], no missing integer. k=5
  //[2, 3, 4, 7, 11], so total missing counts: arr[idx] - (idx+1). select pivot index in the middle of the array
  public int findKthPositive1539BinarySearch(int[] arr, int k) {
    int left = 0, right = arr.length - 1;
    //[1,2,3,4] k=2 you wanna move left further beyond the num 4 when done, which is num 5 with idx of 4, so 6=4+2, so <= and right=pivot-1
    while (left <= right) {
      int pivot = left + (right - left) / 2;
      if (arr[pivot] - (pivot+1) < k) {
        left = pivot + 1;
      } else {
        right = pivot-1;
      }
    }

    // At the end of the loop, left = right + 1,
    // and the kth missing is in-between arr[right] and arr[left], should return right+1+k
    // e.g. [2,3,4,7,11] k=5, left=4, right=3, return  3+1+5
    return right+1+k;
  }

}
