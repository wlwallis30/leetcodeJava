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

  //buy sell stock II
  int maxProfit122(int[] prices) {
    if(prices.length == 0) return 0;
    int res=0;
    for(int i=1; i<prices.length;++i) {
      if(prices[i]>prices[i-1]) res += prices[i]-prices[i-1];
    }
    return res;
  }

  // buy sell stock with cool down, check the markdown file to see the diagram
  // sold[i]=hold[i−1]+price[i]
  // held[i]=max(held[i−1],reset[i−1]−price[i])
  // reset[i]=max(reset[i−1],sold[i−1])
  public int maxProfit309(int[] prices) {
    int sold = Integer.MIN_VALUE, held = Integer.MIN_VALUE, reset = 0;
    for (int price : prices) {
      int preSold = sold;

      sold = held + price;
      held = Math.max(held, reset - price);
      reset = Math.max(reset, preSold);
    }

    return Math.max(sold, reset);
  }
}
