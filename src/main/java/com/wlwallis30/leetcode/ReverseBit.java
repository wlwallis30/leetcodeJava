package com.wlwallis30.leetcode;

public class ReverseBit {
  public int reverseBits_190(int n) {
    int res = 0;
    for (int i = 0; i < 32; ++i) {
      if ((n & 1) == 1) {
        res = (res << 1) + 1;
      } else {
        res = res << 1;
      }
      n = n >> 1;
    }
    return res;
  }

  // count 1 bits
  int hammingWeight_191(int n) {
    int count = 0;

    //number is positive, 0 filled, negative then fill 1
    //Signed right shift operator >>, unsigned shift >>>, unsigned will always fill 0
    //https://www.tutorialspoint.com/Bitwise-right-shift-operator-in-Java
    //while(n!=0) can not be used since right shift might fill 1, so infinite loop
    for (int i = 0; i < 32; i++) {
      count += (n&1);
      System.out.println(n);
      n=n>>1;
    }
    return count;
  }
}