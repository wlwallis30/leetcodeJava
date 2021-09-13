package com.wlwallis30.leetcode;

import java.util.*;

public class RestoreIPaddress {
  List<String> restoreIpAddresses93(String s) {
    List<String> res = new ArrayList<>();
    String out ="";
    int k =4;
    restore(res, out, s, k);
    return res;
  }

  void restore(List<String> res, String out, String strLeft, int k)
  {
    if(k==0) {
      if(strLeft.length() == 0) res.add(out);
      // else if not empty, do nothing, coz meaningless
    }
    else {
      for(int i=1; i<=3; ++i) {
        // substring right boundary is exclusive, so 0 ~ i-1, length is i
        if(strLeft.length()>=i && isValidPortion(strLeft.substring(0,i))) {
          //int nextK = k-1;
          if(k==1) restore(res, out+strLeft.substring(0,i), strLeft.substring(i), k-1);
          else restore(res, out+ strLeft.substring(0,i) +".", strLeft.substring(i), k-1);
        }
      }
    }
  }

  boolean isValidPortion(String str) {
    //if((str.length()>1 && str.charAt(0) == '0')|| str.length() == 0 || str.length()>3) return false; >3 or ==0 not necessary
    if(str.length()>1 && str.charAt(0) == '0') return false; // 01, 025 etc, not valid
    int digit = Integer.parseInt(str);
    return digit <= 255 && digit >= 0;
  }
}
