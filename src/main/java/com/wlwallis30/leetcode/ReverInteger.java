package com.wlwallis30.leetcode;

import java.util.*;

public class ReverInteger {
  int reverse_7(int x) {
    int res=0;
    while(x!=0)
    {
      //  we do not need to check if res == 214748364, why? （214748364 即为 INT_MAX / 10）
      /*
      x的范围也应该在 -2147483648～2147483647 之间，那么x的第一位只能是1或者2，翻转之后 res 的最后一位只能是1或2，
      所以 res 只能是 2147483641 或 2147483642 都在 int 的范围内。但是它们对应的x为 1463847412 和 2463847412，
      后者超出了数值范围。所以当过程中 res 等于 214748364 时， 输入的x只能为 1463847412， 翻转后的结果为 2147483641
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

  public boolean isNumber65(String s) {
    if(s.trim().isEmpty()) return false;
    // \d* is for \\d+\\.?([eE]) to avoid wrong result for 0.8, 0345.6, 0.183(these are valid num).
    // 0. and 1. are valid, but 1.e 1e is not valid
    // | has the scope extended to ( and ), so it is OR for \d+\.?  and  \.\d+, not just \. and \.
    String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*([eE][+-]?\\d+)?";
    return s.trim().matches(regex);
  }

  //http://n00tc0d3r.blogspot.com/2013/06/valid-number.html Deterministic Finite state machine
  private static final List<Map<String, Integer>> dfa = List.of(
      Map.of("digit", 1, "sign", 2, "dot", 3),
      Map.of("digit", 1, "dot", 4, "exponent", 5),
      Map.of("digit", 1, "dot", 3),
      Map.of("digit", 4),
      Map.of("digit", 4, "exponent", 5),
      Map.of("sign", 6, "digit", 7),
      Map.of("digit", 7),
      Map.of("digit", 7)
  );

  // These are all of the valid finishing states for our DFA.
  private static final Set<Integer> validFinalStates = Set.of(1, 4, 7);
  //s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.', no space need to be considered(if need, just use str.trim())
  public boolean isNumber65FiniteState(String s) {
    int currentState = 0;
    String action = "";

    for (int i = 0; i < s.length(); i++) {
      char curr = s.charAt(i);
      if (Character.isDigit(curr)) {
        action = "digit";
      } else if (curr == '+' || curr == '-') {
        action = "sign";
      } else if (curr == 'e' || curr == 'E') {
        action = "exponent";
      } else if (curr == '.') {
        action = "dot";
      } else {
        return false;
      }

      if (!dfa.get(currentState).containsKey(action)) { return false; }

      currentState = dfa.get(currentState).get(action);
    }

    return validFinalStates.contains(currentState);
  }
}
