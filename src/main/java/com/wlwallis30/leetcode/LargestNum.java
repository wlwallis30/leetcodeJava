package com.wlwallis30.leetcode;

import java.util.*;

public class LargestNum {
  private class LargerNumberComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
      String order1 = a + b;
      String order2 = b + a;
      // if b+a < a+b, e.g a=9, b=3, 39<93, order2 < order1 true => a is first in ordering, b follows a
      // for example, 1 < 3, the comparator return a negative--ascending order, but above is reversed since a(9) will be like integer 1, b(3) will be like integer 3
      return order2.compareTo(order1);
    }
  }

  public String largestNumber179(int[] nums) {
    // Get input integers as strings.
    String[] asStrs = new String[nums.length];
    for (int i = 0; i < nums.length; i++) { asStrs[i] = String.valueOf(nums[i]); }
    // Sort strings according to custom comparator.
    Arrays.sort(asStrs, new LargerNumberComparator());

    // Build largest number from sorted array.
    String largestNumberStr = new String();
    for (String numAsStr : asStrs) { largestNumberStr += numAsStr; }

    //array consists of only zeroes
    return asStrs[0].equals("0") ? "0": largestNumberStr;
  }
}
