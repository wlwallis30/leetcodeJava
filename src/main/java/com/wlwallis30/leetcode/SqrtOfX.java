package com.wlwallis30.leetcode;

public class SqrtOfX {
  public int mySqrt_69(int x) {
    if (x < 2) return x;

    int mid, left = 2, right = x / 2;
    while (left <= right) {
      mid = left + (right - left) / 2;
      // using dividing can avoid long integer usage or overflos for int
      if (x/mid < mid) right = mid - 1;
      else if (x/mid > mid) left = mid + 1;
      else return mid;
    }

    return right;
  }

  double powerOfN_50(double x, int n) {
    if (n == 0) return 1;
    double half = powerOfN_50(x, n / 2);
    if (n % 2 == 0) return half * half;
    // one base case is: 1 * 1 * x
    if (n > 0) return half * half * x;
    // dealing with n < 0, then you need to divide by x
    return half * half / x;
  }

  public boolean perfectSquare_367(int x) {
    if (x < 2) return true;
    // mid could be as large as ((2^31-1)/2)^2 = 1152921503533105200 which will cause wrap around, so in java, left and right also need to be long
    // in c++, left and right might be auto converted into long, while java will complain about conversion
    long left = 0, right = x/2, mid, guess;
    while (left <= right) {
      mid = (right + left) / 2;
      guess = mid * mid;
      // using dividing can avoid long integer usage or overflos for int
      if (guess == x) return true;
      else if (guess > x) right = mid - 1;
      else  left = mid + 1;
    }

    return false;
  }
}