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
}
