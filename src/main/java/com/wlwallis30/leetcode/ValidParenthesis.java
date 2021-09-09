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

  private List<String> combinations = new ArrayList<>();
  // for java 9+, you can use: Map.of(
  //    "2", "abc",
  //    "3", "def"
  //);
  private Map<Character, String> letters = new HashMap<Character, String>() {{
    put('2', "abc");
    put('3', "def");
    put('4', "ghi");
    put('5', "jkl");
    put('6', "mno");
    put('7', "pqrs");
    put('8', "tuv");
    put('9', "wxyz");
  }};
  private String phoneDigits;

  public List<String> letterCombinations17(String digits) {
    // If the input is empty, immediately return an empty answer array
    if (digits.length() == 0) {
      return combinations;
    }

    // Initiate backtracking with an empty path and starting index of 0
    phoneDigits = digits;
    backtrack(0, new StringBuilder());
    return combinations;
  }

  private void backtrack(int index, StringBuilder path) {
    // If the path is the same length as digits, we have a complete combination
    if (path.length() == phoneDigits.length()) {
      combinations.add(path.toString());
      return; // Backtrack
    }

    // Get the letters that the current digit maps to, and loop through them
    String possibleLetters = letters.get(phoneDigits.charAt(index));
    for (char letter: possibleLetters.toCharArray()) {
      // Add the letter to our current path
      path.append(letter);
      // Move on to the next digit
      backtrack(index + 1, path);
      // Backtrack by removing the letter before moving onto the next
      path.deleteCharAt(path.length() - 1);
    }
  }
}
