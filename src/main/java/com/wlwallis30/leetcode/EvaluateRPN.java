package com.wlwallis30.leetcode;

import java.util.*;
import java.util.function.BiFunction;

public class EvaluateRPN {
  // reverse poland notation
  int evalRPN150Stack(String[] tokens) {
    if(tokens.length==1) return Integer.parseInt(tokens[0]);
    Stack<Integer> numStack = new Stack<>();

    for(int i=0;i<tokens.length;++i) {
      //== and != work on object identity. While the two Strings have the same value, they are actually two different objects.
      // you do NOT use == and != when comparing two strings!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      if(!tokens[i].equals("+")&&!tokens[i].equals("-")&&!tokens[i].equals("*")&&!tokens[i].equals("/")) {
        numStack.push(Integer.parseInt(tokens[i]));
      } else {
        //for(Integer x : numStack) { System.out.println(i); }
        int x = numStack.pop();
        int y = numStack.pop();
        if(tokens[i].equals("+")) numStack.push(y+x);
        if(tokens[i].equals("-")) numStack.push(y-x);
        if(tokens[i].equals("*")) numStack.push(y*x);
        if(tokens[i].equals("/")) numStack.push(y/x);
      }
    }
    return numStack.pop();
  }

  // better coding style
  public int evalRPN150BetterWriting(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    for (String token : tokens) {
      if (!"+-*/".contains(token)) {
        stack.push(Integer.valueOf(token));
      } else {
        int x = stack.pop();
        int y = stack.pop();
        int result = 0;
        switch (token) {
          case "+": stack.push(y+x); break;
          case "-": stack.push(y-x); break;
          case "*": stack.push(y*x); break;
          case "/": stack.push(y/x); break;
        }
      }
    }

    return stack.pop();
  }

  // <T, U, R>, 1st and 2nd argument, and result
  private static final Map<String, BiFunction<Integer, Integer, Integer>> ops = new HashMap<>();

  // Ensure this only gets done once for ALL test cases.
  static {
    ops.put("+", (a, b) -> a + b);
    ops.put("-", (a, b) -> a - b);
    ops.put("*", (a, b) -> a * b);
    ops.put("/", (a, b) -> a / b);
  }

  public int evalRPN150Lamda(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    for (String token : tokens) {
      if (!ops.containsKey(token)) {
        stack.push(Integer.valueOf(token));
        continue;
      }
      int x = stack.pop();
      int y = stack.pop();
      BiFunction<Integer, Integer, Integer> operation;
      operation = ops.get(token);
      int result = operation.apply(y, x);
      stack.push(result);
    }

    return stack.pop();
  }
}
