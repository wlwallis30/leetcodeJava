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

  public String minRemoveToMakeValid1249(String s) {
    Set<Integer> indexesToRemove = new HashSet<>();
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        stack.push(i);
      } if (s.charAt(i) == ')') {
        if (stack.isEmpty()) {
          indexesToRemove.add(i);
        } else {
          stack.pop();
        }
      }
    }
    // Put any indexes remaining on stack into the set, e.g. case of (((
    while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (!indexesToRemove.contains(i)) {
        sb.append(s.charAt(i));
      }
    }
    return sb.toString();
  }

  public String minRemoveToMakeValid_balance(String s) {
    // Pass 1: Remove all invalid ")"
    StringBuilder sb = new StringBuilder();
    int openSeen = 0;
    int balance = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') { openSeen++; balance++; }
      if (c == ')') {
        if (balance == 0) continue;
        balance--;
      }
      sb.append(c);
    }

    // Pass 2: Remove the rightmost "("
    StringBuilder result = new StringBuilder();
    int openToKeep = openSeen - balance;
    for (int i = 0; i < sb.length(); i++) {
      char c = sb.charAt(i);
      if (c == '(') {
        openToKeep--;
        if (openToKeep < 0) continue;
      }
      result.append(c);
    }

    return result.toString();
  }

  public int minAddToMakeValid921(String s) {
    char[] arr = s.toCharArray();
    int openCount = 0;
    int closeCount = 0;
    for(char c : arr){
      if(c == '('){
        openCount++;
      }else if(c == ')'){
        if(openCount > 0){
          openCount--;
        }else{
          closeCount++;
        }
      }
    }
    return closeCount + openCount;
  }

  public int minInsertions1541(String s) {
    int leftOpen=0;
    int ans=0;

    for(int i=0;i<s.length();i++){
      if(s.charAt(i)=='('){ leftOpen++; }
      // hitting ) @ idx of i
      else{
        if(i+1<s.length() && s.charAt(i+1)==')'){
          i++; // hitting another ) @i+1, so we need to advance i
          if(leftOpen>0){ leftOpen--; }
          else{ ans++; } //you need to add ( since no more leftOpen
        }
        else{ // @i+1, another ( appear(will be addressed in next iteration)
              // OR i reached the end ==> we only take care of this
          if(leftOpen>0){
            leftOpen--;
            ans++; // need to add ) here to match and decrease leftOpen
          }
          else{ ans+=2; } // leftOpen==0, we need to add two )
        }
      }
    }
    ans+=2*leftOpen;
    return ans;
  }

  /*
  left_count which represents the number of left parentheses that have been added to the expression we are building.
  right_count which represents the number of right parentheses that have been added to the expression we are building.
   */
  private Set<String> validExpressions = new HashSet<String>();
  private void recurse( String s, int index, int leftCount, int rightCount, int leftRem, int rightRem, StringBuilder expression) {
    // end of the string, just check if the resulting expression is valid or not and also if we have removed the total number of left and right
    // parentheses that we should have removed.
    if (index == s.length()) {
      if (leftRem == 0 && rightRem == 0) { this.validExpressions.add(expression.toString()); }
    } else {
      char character = s.charAt(index);
      int length = expression.length();

      // The discard case, to throw away ( or ) .
      // Note that here we have our pruning condition. no need to recurse if the remaining count for that parenthesis is == 0.
      if ((character == '(' && leftRem > 0) || (character == ')' && rightRem > 0)) {
        this.recurse( s,
            index + 1,
            leftCount,
            rightCount,
            leftRem - (character == '(' ? 1 : 0),
            rightRem - (character == ')' ? 1 : 0),
            expression);
      }

      //exploring other possible valid strings without removing current char
      expression.append(character);
      // Simply recurse one step further if the current character is not a parenthesis.
      if (character != '(' && character != ')') {
        this.recurse(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression);
      } else if (character == '(') {
        // Consider ( to be in the expression
        this.recurse(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression);
      } else if (rightCount < leftCount) {
        // Consider ) to be added in the expression, only when current rightCnt < leftCnt
        this.recurse(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression);
      }

      // Delete for backtracking.
      expression.deleteCharAt(length);
    }
  }

  //O(2^n)
  public List<String> removeInvalidParentheses301(String s) {
    int left = 0, right = 0;
    // First, we find out the number of misplaced left and right parentheses.
    for (int i = 0; i < s.length(); i++) {
      // Simply record the left one.
      if (s.charAt(i) == '(') { left++; }
      else if (s.charAt(i) == ')') {
        // If we don't have a matching left, then this is a misplaced right, record it.
        right = left == 0 ? right + 1 : right;
        // Decrement count of left parentheses because we have found a right
        // which CAN be a matching one for a left.
        left = left > 0 ? left - 1 : left;
      }
    }

    this.recurse(s, 0, 0, 0, left, right, new StringBuilder());
    return new ArrayList<String>(this.validExpressions);
  }
}
