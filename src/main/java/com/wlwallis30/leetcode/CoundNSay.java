package com.wlwallis30.leetcode;

import java.util.*;

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

  public String encode271(List<String> strs) {
    if (strs.size() == 0) return Character.toString((char)282);

    String delimiter = Character.toString((char)283);
    StringBuilder strToBuild = new StringBuilder();
    for(String str: strs) {
      strToBuild.append(str);
      strToBuild.append(delimiter);
    }
    strToBuild.deleteCharAt(strToBuild.length() - 1);
    return strToBuild.toString();
  }

  // Decodes a single string to a list of strings.
  public List<String> decode(String str) {
    String delimiter = Character.toString((char)282);
    if (str.equals(delimiter)) return new ArrayList();

    delimiter = Character.toString((char)283);
    // limit = negative number, pattern will be applied as many as possible
    return Arrays.asList(str.split(delimiter, -1));
  }
}
