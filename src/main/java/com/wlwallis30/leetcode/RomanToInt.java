package com.wlwallis30.leetcode;
import java.util.*;

public class RomanToInt {
  /*
  Input: s = "LVIII"
  Output: 58
  Explanation: L = 50, V= 5, III = 3.
  Input: s = "M CM XC IV"
  Output: 1994
  Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
   */
  public int romanToInt_13(String str) {
    Map<Character, Integer> romanMap = new HashMap<>();
    romanMap.put('M', 1000);
    romanMap.put('D', 500);
    romanMap.put('C', 100);
    romanMap.put('L', 50);
    romanMap.put('X', 10);
    romanMap.put('V', 5);
    romanMap.put('I', 1);
    int res = 0;
    for (int i = 0; i < str.length(); ++i) {
      int val = romanMap.get(str.charAt(i));
      if (i<str.length()-1
          && romanMap.get(str.charAt(i+1)) > romanMap.get(str.charAt(i))) res -= val;
      else res += val;
    }

    return res;
  }
}
