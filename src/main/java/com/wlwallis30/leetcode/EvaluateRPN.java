package com.wlwallis30.leetcode;

import java.util.*;
import java.util.function.BiFunction;

public class EvaluateRPN {
  // reverse poland notation, negative num are allowed
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

  //basic calculator II, compare to 224, all cases will do a push coz in the end, we will add all num in stack
  public int calculate227(String s) {
    if (s == null || s.isEmpty()) return 0;
    int len = s.length();
    Stack<Integer> stack = new Stack<>();
    int currentNumber = 0;
    // operator before the second operand, 2*3+1, store * to op before hitting 3, calc when hitting +, and reset op and num
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

  // you will get a TLE i.e. time limit exceeded error if we dont track the value and just do evaluation everytime with a expression.
  //exactly 4 different recursive paths. The base case is when the value of index reaches N i.e. the length of the nums array. Hence, our complexity would be O(4^N)
  //In the worst case, we can have O(4^N) valid expressions, the base case will have O(N) for string ops, so O(N * 4^N)
  List<String> result = new ArrayList<>();
  public List<String> addOperators282(String num, int target) {
    if (num == null || num.isEmpty()) return result;
    addOperatorsRecur(num, target, 0, 0, 0, "");
    return result;
  }

  private void addOperatorsRecur(String num, int target, int start, long preValue, long preNum, String expression) {
    if (start == num.length()) {
      if (preValue == target) { result.add(expression); }
      return; //2. if not, we discard
    }

    // 1. We can choose a single digits as operands Or multi digits as operand (  1 + 2 or 12 + 34 )
    for (int i = start; i < num.length(); i++) {
      //  We don't consider a operand which is 0 as single digit operand, as operand like 0 or 01 , 023... does not make sense
      //  To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
      if (i != start && num.charAt(start) == '0') break;

      long currentDigitsValue = Long.parseLong(num.substring(start, i + 1)); // very important approach

       // We need two operands for a operator and operator can't be apply on single operand
      if (start == 0) {
        // as this is the first num/digit only, then don't apply any operator
        addOperatorsRecur(num, target, i + 1, currentDigitsValue,
            currentDigitsValue, expression + currentDigitsValue);
      } else { //We have two operands
         // '+': preValue = 12, preNum = 2 ( say we did like 10 + 2 ), currentDigitvalue = 51 then expression is 10 + 2 + 51 = 63
        addOperatorsRecur(num, target, i + 1, preValue + currentDigitsValue,
            currentDigitsValue, expression + "+" + currentDigitsValue);

         // "-":  preValue = 12 preValue = 2 ( say we did like 10 + 2 ) currentDigitvalue = 5 then expression is 10 + 2 - 5 = 7
        // So preNum would be -5
        addOperatorsRecur(num, target, i + 1, preValue - currentDigitsValue,
            -currentDigitsValue, expression + "-" + currentDigitsValue);

         // '*'; higher than + and -. So current value become = preValue - preNum + last*currentDigitvalue;
        // preValue = 12 , preNum = 2 ( let say we applied + as 10 + 2 ), currendDigitValue = 4
        // so expression become : 10 + 2 * 4,  wrong if 12 * 4 = 24. New value = 10 + 2 * 4 = 18, preNum become 2*4 = 8
        addOperatorsRecur(num, target, i + 1, preValue - preNum + preNum * currentDigitsValue,
            preNum * currentDigitsValue, expression + "*" + currentDigitsValue);
      }
    }
  }

  // basic calculator II, compare to 227, this does not have * /
  // only push when hitting (, only pop when ). when hitting + -, just do the calculation since no */, no need to push
  public int calculate224(String s) {
    Stack<Integer> stack = new Stack<>();
    int operand = 0;
    int result = 0; // For the on-going result
    int sign = 1;  // 1 means positive, -1 means negative

    // when hitting + - ) we need to calculate and reset operand = 0, when hitting (, we need to push result and sign and reset them
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (Character.isDigit(ch)) { operand = 10 * operand + (ch - '0');
      } else if (ch == '+') {
        result += sign * operand;
        sign = 1;
        // Reset operand
        operand = 0;
      } else if (ch == '-') {
        result += sign * operand;
        sign = -1;
        operand = 0;
      } else if (ch == '(') {
        // Push the result and sign on to the stack, for later
        stack.push(result);
        stack.push(sign);
        sign = 1;
        result = 0;
      } else if (ch == ')') {
        result += sign * operand;
        // ')' marks end of expression within a set of parenthesis as stack.pop() is the sign before the parenthesis
        result *= stack.pop();
        // (operand on stack) + (sign on stack * (result from parenthesis))
        result += stack.pop();
        operand = 0;
      }
    }
    //dont forget we still have the last operand and sign to add to result
    return result + (sign * operand);
  }
}
