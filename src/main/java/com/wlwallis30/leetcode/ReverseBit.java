package com.wlwallis30.leetcode;

import java.util.Arrays;

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

  // count 1 bits, you need to treat n as an unsigned value in java, in java there is no unsigned int
  public int hammingWeight_191(int n) {
    int count = 0;

    //number is positive, 0 filled, negative then fill 1
    //Signed right shift operator >>, unsigned shift >>>, unsigned will always fill 0
    //https://www.tutorialspoint.com/Bitwise-right-shift-operator-in-Java
    //while(n!=0) can not be used since right shift might fill 1, so infinite loop
    for (int i = 0; i < 32; i++) {
      count += (n&1);
      System.out.println(n);
      n=n>>1;
      // n = n>>>1 also works since we are limiting by 32 ops
    }
    return count;
  }
  /*
  1     2   3      4         8         16 　　....
  1    10   011   100      1000      10000　....
   */
  // 4&3 == 0, 2&1 == 0
  public boolean isPowerOfTwo_231(int n) {
    return n>0 && (n & (n-1)) == 0;
  }

  /*
    0    0000    0
    -------------
    1    0001    1
    -------------
    2    0010    1
    3    0011    2
    -------------
    4    0100    1
    5    0101    2
    6    0110    2
    7    0111    3
    -------------
    8    1000    1
    9    1001    2
    10   1010    2
    11   1011    3
    12   1100    2
    13   1101    3
    14   1110    3
    15   1111    4

   */

  // you can use hammingWeight_191 to count bits and form an array. but check the above table to find DP way
  public int[] countBits_338(int n) {
    int[] res = new int[n + 1];
    Arrays.fill(res, 0);
    for (int x = 0; x <= n; ++x) {
      if(x%2 == 0) res[x] = res[x/2];
      else res[x] = res[x/2] + 1;
    }

    return  res;
  }

  // easier to think of it, but exceed time limit
  public int[] countBits338Straight(int n) {
   int[] res = new int[n+1];
   for(int i=0; i<=n;++i) {
     int count = 0;
     for (int j = 0; j < 32; j++) {
       count += (n&1);
       System.out.println(n);
       i=i>>1;
     }
     res[i] = count;
   }
    return res;
  }
}