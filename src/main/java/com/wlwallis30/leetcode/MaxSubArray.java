package com.wlwallis30.leetcode;

public class MaxSubArray {
  public int maxSubArray_53(int[] nums) {
    // Initialize our variables using the first element.
    int curMax = nums[0];
    int globalMax = nums[0];

    // Start with the 2nd element since we already used the first one.
    for (int i = 1; i < nums.length; i++) {
      int num = nums[i];
      // If current_subarray is negative, throw it away. Otherwise, keep adding to it.
      curMax = Math.max(num, curMax + num);
      globalMax = Math.max(globalMax, curMax);
    }

    return globalMax;
  }

  // best time to buu & sell stock
  public int maxProfit_121(int[] prices) {
    int res=0,movingMin=Integer.MAX_VALUE;
    for(int price:prices)
    {
      movingMin = Math.min(movingMin,price);
      res= Math.max(res, price-movingMin);
    }
    return res;
  }
}
