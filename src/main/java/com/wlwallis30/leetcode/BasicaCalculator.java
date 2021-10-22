package com.wlwallis30.leetcode;

import java.util.*;

public class BasicaCalculator {
  public int calculate227(String s) {
    if (s == null || s.isEmpty()) return 0;
    int len = s.length();
    Stack<Integer> stack = new Stack<>();
    int currentNumber = 0;
    char operation = '+';
    for (int i = 0; i < len; i++) {
      char currentChar = s.charAt(i);
      if (Character.isDigit(currentChar)) { currentNumber = (currentNumber * 10) + (currentChar - '0'); }

      // hitting operators or hitting the end of string, we should calc or push num for calc
      if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
        if (operation == '-') { stack.push(-currentNumber); }
        else if (operation == '+') { stack.push(currentNumber); }
        else if (operation == '*') { stack.push(stack.pop() * currentNumber); }
        else if (operation == '/') { stack.push(stack.pop() / currentNumber); }
        operation = currentChar;
        currentNumber = 0;
      }
    }
    int result = 0;
    while (!stack.isEmpty()) {
      result += stack.pop();
    }
    return result;
  }
}
