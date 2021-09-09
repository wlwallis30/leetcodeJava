package com.wlwallis30.leetcode;

public class CoundNSay {
  String countAndSay38(int n) {
    if (n <= 0) return "";
    String toSay = "1";
    for (int i = 1; i < n; ++i) {
      char[] prevChars = toSay.toCharArray();
      StringBuilder curStr = new StringBuilder();

      for (int charIdx = 0; charIdx < prevChars.length; ++charIdx) {
        int count = 1;
        while(charIdx<prevChars.length-1
            && prevChars[charIdx] == prevChars[charIdx+1]) {
          ++charIdx;
          ++count;
        }
        //now charIdx is at the end of repeated char
        curStr.append(count).append(prevChars[charIdx]);
      }
      toSay = curStr.toString();
    }

    return toSay;
  }
}
