package com.wlwallis30.leetcode;

public class AddDigits {
  // 202 is also similar problems, check LinkedListCycle
  int addDigits_258(int num) {
    while (num / 10 > 0) {
      int sum = 0;
      // adding all digits to the sum
      while (num > 0) {
        sum += num % 10;
        num /= 10;
      }
      num = sum;
      // now go to next round of operation.
    }
    return num;
  }

  public int addDigits_258Math(int num) {
    if (num == 0) return 0;
    if (num % 9 == 0) return 9;
    return num % 9;
  }
}
