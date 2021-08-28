package com.wlwallis30.leetcode;

public class CommonPrefixStr {
  public String longestCommonPrefix_14(String[] strs) {
    if (strs.length == 0) return "";
    for(int col =0; col < strs[0].length(); ++col)
    {
      for(int row = 0; row < strs.length-1; ++ row)
      {
        //System.out.println("col:" + col + "   row len:" + strs[row+1].length());
        if(col >= strs[row].length()
            || col >= strs[row+1].length()
            || strs[row].charAt(col) != strs[row+1].charAt(col))
          return strs[row].substring(0,col);
      }
    }

    return strs[0];
  }
}
