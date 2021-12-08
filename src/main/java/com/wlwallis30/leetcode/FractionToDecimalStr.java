package com.wlwallis30.leetcode;

import java.util.*;

public class FractionToDecimalStr {
  public String fractionToDecimal166(int numerator, int denominator) {
    if (numerator == 0) { return "0"; }
    StringBuilder fraction = new StringBuilder();
    // If either one is negative (not both)
    if (numerator < 0 ^ denominator < 0) { fraction.append("-"); }
    // Convert to Long or else abs(-2147483648) overflows
    long dividend = Math.abs(Long.valueOf(numerator));
    long divisor = Math.abs(Long.valueOf(denominator));

    fraction.append(String.valueOf(dividend / divisor));
    long remainder = dividend % divisor;
    if (remainder == 0) { return fraction.toString(); }

    fraction.append(".");
    //map:  remainder, the idx of repeated remainder
    Map<Long, Integer> map = new HashMap<>();
    while (remainder != 0) {
      if (map.containsKey(remainder)) {
        fraction.insert(map.get(remainder), "(");
        fraction.append(")");
        break;
      }
      //trick: e.g. 2/3, reminder is 2, "0.", put (2: length of 2), length of 2 will be idx to insert
      map.put(remainder, fraction.length());
      remainder *= 10;
      fraction.append(String.valueOf(remainder / divisor));
      remainder %= divisor;
    }
    return fraction.toString();
  }

}
