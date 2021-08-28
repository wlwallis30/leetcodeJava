package com.wlwallis30.leetcode;

public class ReverInteger {
  int reverse_7(int x) {
    int res=0;
    while(x!=0)
    {
      // no need to check = Int.MAX:
      /*
      x的第一位只能是1或者2，翻转之后 res 的最后一位只能是1或2，所以 res 只能是 2147483641 或 2147483642 都在 int 的范围内。
      但是它们对应的x为 1463847412 和 2463847412，后者超出了数值范围, 不可能作为x出现。所以不用检查。
       */
      if(Math.abs(res)>Integer.MAX_VALUE/10) return 0;
      res=res*10+ x%10;
      x=x/10;
    }

    return res;
  }

  int atoi_8(String str) {
    if(str.isEmpty()) return 0;
    int pos=0, res=0;

    // you can use for loop with break to avoid the condition of pos < length since charAt will throw if out of range
    while(pos<str.length() && str.charAt(pos)==' ') ++pos;
    boolean isPositive= true;
    if(pos<str.length() && str.charAt(pos)== '+') ++pos;
    else if(pos<str.length() && str.charAt(pos)=='-') {isPositive=false; ++pos;}

//    if(str.charAt(pos)>'9' || str.charAt(pos)<'0') return 0;
    if(pos<str.length() && !Character.isDigit(str.charAt(pos))) return 0;

//    while(str.charAt(pos)<='9' && str.charAt(pos)>='0' )

    while(pos<str.length() && Character.isDigit(str.charAt(pos)))
    {
      if(res>Integer.MAX_VALUE/10 || (res==Integer.MAX_VALUE/10 && str.charAt(pos)-'0'>=8))
      {
        // -2147483648～2147483647. so if last digit is >=8, then either max or min
        if(isPositive) return Integer.MAX_VALUE;
        else return Integer.MIN_VALUE;
      }
      res = res*10 + str.charAt(pos)-'0';
      ++pos;
    }

    return isPositive? res:-res;
  }
}
