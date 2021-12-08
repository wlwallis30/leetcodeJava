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
      //also works strToBuild.append((char)283);
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

  // O(N), inplace, O(1), newIdx and oldIdx are in the same original array.
  public int compress443(char[] chars) {
    int newIdx = 0, count = 0;
    // We traverse the entire array with this iteration.
    for (int oldIdx = 0; oldIdx < chars.length; oldIdx++) {
      count++; // We keep note of number of characters in sequence.
      /* When the next character is not as same as the previous one,
       * we modify the array from the beginning with the current character.
       * Note that the array will only become shorter as we keep updating the data.
       * So there is no need to create another array.
       */
      if (oldIdx+1 == chars.length || chars[oldIdx] != chars[oldIdx+1]) {
        chars[newIdx++] = chars[oldIdx];
        // If there are multiple characters, we add the number to the array.
        if (count != 1) {
          for (char c : String.valueOf(count).toCharArray()) { //Integer.toString(count).toCharArray()
            chars[newIdx++] = c;
          }
        }
        // We initialize count to zero for the next character check.
        count = 0;
      }
    }
    return newIdx;
  }
}
