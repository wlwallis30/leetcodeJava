package com.wlwallis30.leetcode;

public class SubStringPattern {

  int strStr_28(String haystack, String needle) {
    if(needle.isEmpty()) return 0;
    int m = haystack.length(), n = needle.length();
    if(m <n) return -1;
    for(int i=0; i<=m-n;++i)
    {
      // you will still need j outside of for loop.
      int j =0;
      for(j=0; j<n; ++j)
      {
        if(haystack.charAt(i+j)!= needle.charAt(j)) break;
      }
      if(j==n) return i;
    }

    return -1;
  }
}
