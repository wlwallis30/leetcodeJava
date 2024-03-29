package com.wlwallis30.leetcode;
import java.util.*;

public class Strobogrammatic {
  public boolean isStrobogrammatic(String num) {
    Map<Character, Character> rotatedDigits = new HashMap<> (
        Map.of('0', '0', '1', '1', '6', '9', '8', '8', '9', '6'));

    // Java allows us to have more than one iteration variable.
    for (int left = 0, right = num.length() - 1; left <= right; left++, right--) {
      char leftChar = num.charAt(left);
      char rightChar = num.charAt(right);
      if (!rotatedDigits.containsKey(leftChar) || rotatedDigits.get(leftChar) != rightChar) {
        return false;
      }
    }
    return true;
  }
}
