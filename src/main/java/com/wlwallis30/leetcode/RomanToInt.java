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
    //Map<Character, Integer> xyz = Map.of('M', 100,  'D', 500); also works
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

  //题目中限定了输入数字的范围 (1 - 3999)
  private static final int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
  private static final String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
  public String intToRoman12(int num) {
    StringBuilder sb = new StringBuilder();
    // Loop through each symbol, stopping if num becomes 0.
    for (int i = 0; i < values.length && num > 0; i++) {
      // Repeat while the current symbol still fits into num.
      while (values[i] <= num) {
        num -= values[i];
        sb.append(symbols[i]);
      }
    }
    return sb.toString();
  }

  // 1, 111, 111, 111
  // B   M    K   hundred
  String numberToWords273(int num) {
    String res = convertHundred(num % 1000); // do hundred first, then we iterate for K, M and B range
    String [] v = {"Thousand", "Million", "Billion"};
    for (int i = 0; i < 3; ++i) {
      num /= 1000;
      res = (num % 1000) != 0 ? convertHundred(num % 1000) + " " + v[i] + " " + res : res;
    }
    res = res.trim();
    return res.isEmpty() ? "Zero" : res;
  }
  String convertHundred(int num) {
    String [] below20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String [] above20 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String res;
    int hundred = num / 100, tensOrSingles = num % 100, single = num % 10;
    res = tensOrSingles < 20 ? below20[tensOrSingles] : above20[tensOrSingles / 10] + (single!=0 ? " " + below20[single] : "");
    if (hundred > 0) res = below20[hundred] + " Hundred" + (tensOrSingles!=0 ? " " + res : "");
    return res;
  }
}
