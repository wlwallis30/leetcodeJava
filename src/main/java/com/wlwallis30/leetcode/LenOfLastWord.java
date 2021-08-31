package com.wlwallis30.leetcode;

public class LenOfLastWord {
  int lengthOfLastWord_58(String s) {
    if(s.length()==0) return 0;
    int probe = s.length()-1;
    while(probe>=0 && s.charAt(probe)==' ') --probe;
    int mostRight = probe;
    while(probe>=0 && s.charAt(probe)!=' ') --probe;
    return mostRight-probe;
  }
}
