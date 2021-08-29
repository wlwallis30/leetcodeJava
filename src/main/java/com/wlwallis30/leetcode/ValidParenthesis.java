package com.wlwallis30.leetcode;

import java.util.*;

public class ValidParenthesis {
  public boolean isValidParenthesis_20(String str) {
    if(str.isEmpty())return true;
    Stack<Character> charStack = new Stack<>();

    int curPos=0;
    while(curPos<str.length())
    {
      if(str.charAt(curPos)=='('||str.charAt(curPos)=='['||str.charAt(curPos)=='{') charStack.push(str.charAt(curPos));
      else
      {
        if(charStack.empty()) return false;
        char tmpChar = charStack.peek();
        charStack.pop();

        if(tmpChar=='(' && str.charAt(curPos)!=')'
            || tmpChar=='{' && str.charAt(curPos)!='}'
            || tmpChar=='[' && str.charAt(curPos)!=']') return false;
      }

      ++curPos;
    }

    return charStack.empty();
  }

  public List<String> genParenthesis_22(int n) {
    List<String> res = new ArrayList<String>();
    GPDFS(n, n, "", res);
    return res;
  }
  void GPDFS(int left, int right, String out, List<String> res) {
    // to avoid something like )))(((, or )(...,
    // coz it is possible to call GPDFS with left=3, right=2, etc, when first all GPDFS calls come back to call stack.
    if (left > right) return;
    if (left == 0 && right == 0) {
      res.add(out);
      return;
    }
    if(left > 0) GPDFS(left - 1, right, out + "(", res);
    if(right > 0) GPDFS(left, right - 1, out + ")", res);
  }
}
