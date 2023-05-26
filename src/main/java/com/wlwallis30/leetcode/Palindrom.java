  package com.wlwallis30.leetcode;

import java.util.*;

public class Palindrom {
  public String palindromLongSubstr_5(String str) {
    if(str.isEmpty()) return "".toString();
    String res = "";
    int leftPalin = 0, rightPalin = 0;
    int lenPalin = 0;
    int size = str.length();
    int[][] dp = new int[size][size];
    //end is the outer loop coz we are expanding from smallest cases
    for(int end = 0; end < size; ++end) {
      dp[end][end] = 1;
      for(int start = 0; start < end; ++start) {
        if(str.charAt(start) == str.charAt(end)
            && (dp[start+1][end-1] == 1 || start == end-1))
        { dp[start][end] = 1; }
        else dp[start][end] = 0;

        if(dp[start][end] ==1 && lenPalin < end-start+1) {
          lenPalin = end-start+1;
          leftPalin = start;
          rightPalin = end;
        }
      }
    }

    lenPalin =  Math.max(lenPalin, rightPalin-leftPalin+1);
    // java's sub string: extend to char whose index = endIndex-1
    res = str.substring(leftPalin+0, rightPalin+1);
    return res;
  }

  public boolean palindromePermutation_266(String str) {
    Map<Character, Integer> charCnt = new HashMap<>();
    for (char chartmp: str.toCharArray()) {
      if(charCnt.containsKey(chartmp)) {
        Integer tmpVal = charCnt.get(chartmp);
        charCnt.put(chartmp, ++tmpVal);
      }
      else {
        charCnt.put(chartmp, 1);
      }
      // the above better be replaced by : map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
    }
    int oddCnt = 0;
    for (char chartmp: charCnt.keySet()) {
      //      System.out.println("one key:" + chartmp);
      if(charCnt.get(chartmp) % 2 == 1) oddCnt++;
    }
    //    System.out.println("odd cnt:" + oddCnt);
    return (oddCnt==0 || oddCnt==1);
   /*  single pass and using an int array map
   * int[] map = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }
        return count <= 1;
   */
  }
  Set<String> set = new HashSet<>();
  public List<String> generatePalindromes_267(String str) {
    int[] charCnt = new int[128];
    char[] halfStr = new char[str.length() / 2];
    for (char tmpCha: str.toCharArray()) { ++charCnt[tmpCha]; }

    // a null char, you can NOT use '' as other languages can, https://stackoverflow.com/questions/18410234/how-does-one-represent-the-empty-char/18410258
    char midChar = '\0'; // or simply 0
    String midStr = ""; //just to measure how many odd chars
    String halfStrBuild = "";
    int k = 0;
    for (int i = 0; i < charCnt.length; i++) {
      if (charCnt[i] % 2 == 1) {
        midStr += (char)i;// to judge if it is a panlindrome
        midChar = (char) i;
      }
      if(midStr.length()>1) return new ArrayList<>();
      char[] tmp= new char[charCnt[i]/2];
      Arrays.fill(tmp, (char)i);
      // java supper sucks,  halfStrBuild += tmp does not work at all!
      halfStrBuild += String.valueOf(tmp);
    }
    System.out.println(halfStrBuild);
    halfStr = halfStrBuild.toCharArray();
    System.out.println(halfStr);
    permute(halfStr, 0, midChar);
    return new ArrayList <String> (set);
  }


  void swap(char[] str, int i, int j) {
    char temp = str[i];
    str[i] = str[j];
    str[j] = temp;
  }

  void permute(char[] str, int startIdx, char midChar) {
    if (startIdx == str.length) {
      set.add(new String(str) + (midChar == 0 ? "" : midChar) + new StringBuffer(new String(str)).reverse());
    } else {
      for (int i = startIdx; i < str.length; i++) {
        if (startIdx != i && str[startIdx] == str[i]) continue;
        swap(str, startIdx, i);
        permute(str, startIdx + 1, midChar);
        swap(str, startIdx, i);

      }
    }
  }

  public boolean validPalindromNum_9(int num) {
    if(num < 0) return false;
    int div = 1;
    while(num/div >= 10) div *= 10;
    int left=Integer.MAX_VALUE, right=Integer.MIN_VALUE;
    while(num > 0) {
      left = num/div;
      right = num%10;
      if(left != right) return false;
      num = (num%div)/10;
      div /= 100;
    }
    return true;
  }

  public boolean isPalindrom_125(String str) {
    int left = 0, right = str.length()-1;
    while(left < right) {
      while( left < right
          && !Character.isLetterOrDigit(str.charAt(left))) ++left;
      while(left < right
          && !Character.isLetterOrDigit(str.charAt(right))) --right;
      if(Character.toLowerCase(str.charAt(left))
          != Character.toLowerCase(str.charAt(right))) return false;
      ++left;
      --right;
    }
    return true;
  }

  public List<List<String>> partitionPalinDrom_131(String str) {
    List<List<String>> result = new ArrayList<List<String>>();
    palinSubstrDfs(0, result, new ArrayList<String>(), str);
    return result;
  }

  void palinSubstrDfs(int start, List<List<String>> result, List<String> currentList, String str) {
    if (start >= str.length()) {
      // java always pass by value, for object, it pass the obj reference's value, not the ref itself
      result.add(new ArrayList<String>(currentList));
      // not really necessory since for loop will jump out, but good to have return here.
      return;
    }
    for (int end = start; end < str.length(); end++) {
      if (isPalindrome(str, start, end)) {
        // add current substring in the currentList
        currentList.add(str.substring(start, end + 1));
        palinSubstrDfs(end + 1, result, currentList, str);
        // backtrack and remove the current substring from currentList
        currentList.remove(currentList.size() - 1);
      }
    }
  }

  boolean isPalindrome(String str, int low, int high) {
    while (low < high) {
      if (str.charAt(low++) != str.charAt(high--)) return false;
    }
    return true;
  }

  //to do: 132, 214, both hard,

  //need to be global to avoid pass-by-value for obj ref in java.
  private ListNode frontPointer;
  private boolean dfsAsStack(ListNode currentNode) {
    if (currentNode != null) {
      if (!dfsAsStack(currentNode.next)) return false;
      if (currentNode.val != frontPointer.val) return false;
      frontPointer = frontPointer.next;
    }
    return true;
  }

  public boolean isPalinLinkedList_234(ListNode head) {
    frontPointer = head;
    return dfsAsStack(head);
  }

  public boolean isPalinLinedList234Stack(ListNode head) {
    if (head == null) return true;
    // wrong, can not infer: Stack<Integer> stack = new LinkedList<>();
    LinkedList<Integer> stack = new LinkedList<>();
    ListNode cur = head;
    while(cur!=null) {
      stack.push(cur.val);
      cur = cur.next;
    }

    cur = head;
    //等于比较了两遍，but it is OK
    while(cur!=null) {
      int val = stack.pop();
      if(val != cur.val) return false;
      cur = cur.next;
    }

    return true;
  }

  //fast and slow  pointers. reverse 2nd half, O(1)
  public boolean isPalinLinedList_234_1(ListNode head) {
    if (head == null) return true;

    // Find the end of first half
    ListNode fast = head;
    ListNode slow = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    // now firstHalfEnd = slow;
    //reversing the second half
    ListNode prev2 = null;
    ListNode curr = slow.next;
    while (curr != null) {
      ListNode nextTemp = curr.next;
      curr.next = prev2;
      prev2 = curr;
      curr = nextTemp;
    }
    // Check whether or not there is a palindrome.
    ListNode prev1 = head;
    boolean result = true;
    // prev2 to control the length of iteration,
    // for odd case: 1->3->1, then 1->3, 1,   prev1=1, prev2=1, then 3 not need to be compared
    while (result && prev2 != null) {
      if (prev1.val != prev2.val) result = false;
      prev1 = prev1.next;
      prev2 = prev2.next;
    }

    // Restore the list and return the result.
    //firstHalfEnd.next = reverseList(secondHalfStart); no need to reverse if just to pass test, but good to reverse
    return result;
  }

  public boolean validPalindrome680(String s) {
    int left = 0 ;
    int right = s.length() - 1;

    while(left<right){
      if(s.charAt(left) == s.charAt(right)){
        left++;
        right--;
      } else{
        return isPalindrome(s,left+1,right) || isPalindrome(s,left,right-1);
      }
    }

    // case: not need to delete a char
    return true ;
  }

  //dp, count palindromic substring
  public int countSubstrings647(String s) {
    int n = s.length(), ans = 0;
    if (n <= 0) return 0;
    boolean[][] dp = new boolean[n][n];
    // Base case: single letter substrings
    for (int i = 0; i < n; ++i) { dp[i][i] = true; ++ans; }
    // Base case: double letter substrings
    for (int i = 0; i < n - 1; ++i) {
      dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
      ans += (dp[i][i + 1] ? 1 : 0);
    }

    // All other cases: substrings of length 3 to n
    for (int len = 3; len <= n; ++len)
      for (int start = 0, end = start + len - 1; end < n; ++start, ++end) {
        dp[start][end] = dp[start + 1][end - 1] && (s.charAt(start) == s.charAt(end));
        ans += (dp[start][end] ? 1 : 0);
      }

    return ans;
  }

  //bottom up DP, refer to 680 which is only for at most one char to remove
  public boolean isValidPalindrome1216(String s, int k) {
    int memo[][] = new int[s.length()][s.length()]; //初始化都为零 memo[i][i]=0
    // Generate all combinations of `start` and `end` in the correct order.
    //refer to 5, outer loop is end, inner loop is start, but start begins differently
    for(int end=1; end<s.length(); ++end)
      for(int start=end-1; start>=0; --start) {
        //start need to begin from close to end first, if 'start' begin from 0, it is not bottom up coz end is new and memo[start + 1][end] is not known yet
        //即便发生了 start+1>end-1, also OK, 因为初始化为零，e.g. end=1, start=end-1=0
        if (s.charAt(start) == s.charAt(end)) memo[start][end] = memo[start + 1][end - 1];

          // Case 2: Character at `start` does not equal character at `end`.
          // Either delete character at `start` or delete character at `end`
        else
          memo[start][end] = 1 + Math.min(memo[start + 1][end], memo[start][end - 1]);
      }

    // Return true if the minimum cost to create a palindrome by only deleting <= `k`.
    return memo[0][s.length() - 1] <= k;
  }
}