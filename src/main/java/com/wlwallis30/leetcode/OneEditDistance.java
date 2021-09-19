package com.wlwallis30.leetcode;

public class OneEditDistance {
  boolean isOneEditDistance161(String s, String t) {
    if(s.length()<t.length()) {
      String tmp = s;
      s = t;
      t = tmp;
    }
    int m=s.length(), n=t.length(), diff=m-n;
    if(diff>1) return false;
    else if(diff==1) {
      // again, you can not use s.substring == t.substring coz they are diff object, use equal!!!
      for(int i=0;i<n;++i) { if(s.charAt(i)!=t.charAt(i)) return s.substring(i+1).equals(t.substring(i)); }
      // this is the case like abcd and abc
      return true;
    }
    else {
      int count =0;
      for(int i=0; i<m;++i) { if(s.charAt(i)!=t.charAt(i)) ++count; }

      return count==1;
    }
  }
}
