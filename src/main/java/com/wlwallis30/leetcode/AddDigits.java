package com.wlwallis30.leetcode;

import java.util.Arrays;

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

  public boolean isUgly_263(int num) {
    if(num<=0) return false;

    while(num>=2)
    {
      if(num%2==0) num/=2;
      else if(num%3==0) num/=3;
      else if(num%5==0) num/=5;
      else return false;
    }

    return true;
  }

  //outer loop: start at 2 and go up to \sqrt{n}
  //This is because by that point we will have considered all of the possible multiples of all the prime numbers below n
  // e.g. n is 30, when outer loop reaching 5, it already covered .. (6 which is 2x3) * 4, 7*4
  //innner loop: start at num*num. Why not start at 2*num?
  // The reason is that all of the previous multiples would already have been covered by previous primes.
  /*
  Let's assume that n is 50 (a value greater than 7*7) to demonstrate this claim.
  7 * 2 = 14 = 2 * 7
  7 * 3 = 21 = 3 * 7
  7 * 4 = 28 = 2 * 2 * 7 = 2 * 14
  7 * 5 = 35 = 5 * 7
  7 * 6 = 42 = 2 * 3 * 7 = 2 * 21
   */
  public int countPrimes_204(int n) {
    if(n<=1) return 0;
    //num: 1~n-1, num = idx+1, boolean array default is false?
    boolean[] isPrime = new boolean[n-1];
    Arrays.fill(isPrime, true);
    isPrime[0]=false; // 1 is not a prime

    // (int) will floor the sqrt, e.g. sqrt of 30 will have 5 by flooring
    int limit = (int)Math.sqrt(n), res=0;
    for(int num=2;num<=limit;++num)
    {
      int idx = num-1;
      if(isPrime[idx]==true)
      {
        for(int curNum=num*num;curNum<n; curNum+=num) isPrime[curNum-1]=false;
      }
    }

    for(boolean each:isPrime) if(each) ++res;
    return res;
  }

  // int range is: -2,147,483,648 ~ 2,147,483,647
  public int divide29Substraction(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
    // Count the number of negatives + convert parameters to positives.
    //If dividend = -2147483648, then converting it to a positive number will behave differently depending on the language/compiler/interpreter you're using.
    // This is because the positive form (2147483648) is outside of the 32-bit signed integer range
    // luckily java does not have this problem, it will auto convert  -2^32 to 2^32-1
    int negatives = 0;
    if (dividend < 0) {
      negatives++;
      dividend = -dividend;
    }
    if (divisor < 0) {
      negatives++;
      divisor = -divisor;
    }

    // Count the number of subtractions.
    int subtractions = 0;
    while (dividend - divisor >= 0) {
      subtractions++;
      dividend -= divisor;
    }

    return negatives == 1? -subtractions: subtractions;
  }

  //divisor != 0
  int divide29BitShift(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
    // Math.abs(Integer.Min) will still give Min since Max will be out of int range, so need a long first.
    long m = (long)dividend, n = (long)divisor, res = 0;
    m = Math.abs(m);
    n = Math.abs(n);
    int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
    if (n == 1) return sign == 1 ? (int)m : -(int)m;
    while (m >= n) {
      long t = n, cnt = 1;
      //如下循环，t扩大一倍, cnt扩大一倍
      // e.g. 28/3.  t: 3->6->12->24, cnt of t: 1->2->4->8;
      while (m >= (t << 1)) {
        t <<= 1;
        cnt <<= 1;
      }
      res += cnt;
      m -= t;
    }
    return (int)res*sign;
  }
}
