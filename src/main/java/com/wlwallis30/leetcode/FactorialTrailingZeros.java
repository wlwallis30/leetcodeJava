package com.wlwallis30.leetcode;

public class FactorialTrailingZeros {
  // 8! = 8*7*6*5*4*3*2*1,  there are only one five and many twos, twos always more than fives
  // so just need to find how many fives
  public int trailingZeroes172(int n) {
    int res = 0;
    while (n > 0) {
      res += n/5;
      // this is for 25, 125, 625, otherwise the above will be enough. 10/5=2, there are 2 fives
      n /= 5;
    }

    return res;
  }
}
