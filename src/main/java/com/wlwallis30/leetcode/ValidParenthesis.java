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


}
