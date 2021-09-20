package com.wlwallis30.leetcode;

import java.util.*;

public class ReverseWords {
  public String reverseWords151ByLib(String s) {
    // remove leading spaces
    s = s.trim();
    // split by multiple spaces, first \ is escape since \s is space
    // regex in short: https://stackoverflow.com/questions/15625629/regex-expressions-in-java-s-vs-s
    List<String> wordList = Arrays.asList(s.split("\\s+"));
    Collections.reverse(wordList);
    return String.join(" ", wordList);
  }

  public StringBuilder trimSpaces(String s) {
    int left = 0, right = s.length() - 1;
    while (left <= right && s.charAt(left) == ' ') ++left;
    while (left <= right && s.charAt(right) == ' ') --right;
    StringBuilder sb = new StringBuilder();
    boolean needAspace = false;
    while (left <= right) {
      while (left <= right && s.charAt(left) == ' ') { ++left; needAspace = true; }
      if(needAspace) sb.append(" ");
      sb.append(s.charAt(left));
      needAspace =  false;
      ++left;
    }
    return sb;
  }

  public void reverse(StringBuilder sb, int left, int right) {
    while (left < right) {
      char tmp = sb.charAt(left);
      sb.setCharAt(left++, sb.charAt(right));
      sb.setCharAt(right--, tmp);
    }
  }

  public void reverseEachWord(StringBuilder sb) {
    int n = sb.length();
    int start = 0, end = 0;
    while (start < n) {
      // go to the end of the word
      while (end < n && sb.charAt(end) != ' ') ++end;
      // reverse the word
      reverse(sb, start, end - 1);
      // move to the next word
      start = end + 1;
      ++end;
    }
  }

  //with string builder, space O(N)
  public String reverseWords151BySB(String s) {
    StringBuilder sb = trimSpaces(s);
    reverse(sb, 0, sb.length() - 1);
    // reverse each word
    reverseEachWord(sb);
    return sb.toString();
  }
//------------------------186---much easier due to char[]-------
  public void reverse(char[] s, int left, int right) {
    while (left < right) { char tmp = s[left]; s[left++] = s[right]; s[right--] = tmp; }
  }

  public void reverseEachWord(char[] s) {
    int n = s.length;
    int start = 0, end = 0;

    while (start < n) {
      while (end < n && s[end] != ' ') ++end; // go to the end of the word
      reverse(s, start, end - 1); // now end points to space
      start = end + 1; // move to the next word
      ++end;
    }
  }

  public void reverseWords186(char[] s) {
    reverse(s, 0, s.length - 1);
    reverseEachWord(s);
  }
}
