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

  int maxProduct152DP(int[] nums) {
    if(nums.length == 0) return 0;

    int curLocalMax=1, curLocalMin=1;
    int globalMax = Integer.MIN_VALUE;
    int tmpMx, tmpMn;
    for(int num:nums)
    {
      tmpMx=curLocalMax;
      tmpMn=curLocalMin;
      curLocalMax = Math.max(num,Math.max(num*tmpMx,num*tmpMn));
      curLocalMin = Math.min(num,Math.min(num*tmpMx,num*tmpMn));
      globalMax = Math.max(globalMax,Math.max(curLocalMax,curLocalMin));
    }

    return globalMax;
  }

  //转移方程 dp[i] = max(num[i] + dp[i - 2], dp[i - 1]), but you dont need array, just two variables enough
  int rob198(int[] nums) {
    if(nums.length == 0) return 0;
    if(nums.length == 1) return nums[0];
    int n = nums.length;
    int[] maxRob = new int[n];
    maxRob[0] = nums[0];
    maxRob[1] = Math.max(nums[0], nums[1]);

    for(int i = 2;i < n; ++i) maxRob[i] = Math.max(maxRob[i-1], maxRob[i-2]+nums[i]);
    return maxRob[n-1];
  }

  int rob198Optimal(int[] nums) {
    if(nums.length == 0) return 0;
    if(nums.length == 1) return nums[0];
    int n = nums.length;
    // max for i-2 and i-1
    int max_2 = nums[0];
    int max_1 = Math.max(nums[0], nums[1]);
    for(int i = 2; i < n; ++i) {
      int tmpMax = Math.max(max_2 + nums[i], max_1);
      max_2 = max_1;
      max_1 = tmpMax;
    }
    return max_1;
  }

  //actually it is pretty much as same as max_2 and max_1
  int rob198EvenOdd(int[] nums) {
    if(nums.length == 0) return 0;
    if(nums.length == 1) return nums[0];
    int n = nums.length;
    int robOdd = 0, robEven = 0;
    for(int i = 0;i < n; ++i) {
      // this algo will not work, coz you dont have to rob one after another, e.g. [2,1,1,100], you can rob 2, relax and 100
      //if(i%2 == 0) robEven += nums[i];
      //else robOdd += nums[i];
      if (i % 2 == 0) robEven = Math.max(robEven + nums[i], robOdd);
      else  robOdd = Math.max(robEven, robOdd + nums[i]);
    }

    return Math.max(robEven, robOdd);
  }

  // [7,4,1,9,3,8,6,5], --> [7,4,1,9,3,8,6] and [4,1,9,3,8,6,5]
  public int robCircle213(int[] nums) {
    if (nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];
    int max1 = rob_simple(nums, 0, nums.length - 2);
    int max2 = rob_simple(nums, 1, nums.length - 1);

    return Math.max(max1, max2);
  }

  public int rob_simple(int[] nums, int start, int end) {
    int max_1 = 0;
    int max_2 = 0;

    for (int i = start; i <= end; i++) {
      int current = nums[i];
      int tmpMax = Math.max(current + max_2, max_1);
      max_2 = max_1;
      max_1 = tmpMax;
    }

    return max_1;
  }
}
