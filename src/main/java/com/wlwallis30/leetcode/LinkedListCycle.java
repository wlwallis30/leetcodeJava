package com.wlwallis30.leetcode;

import java.util.*;

public class LinkedListCycle {

  public boolean hasCycle_141(ListNode head) {
    if (head == null) {
      return false;
    }

    ListNode slow = head;
    ListNode fast = head;
    // && is needed
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if(slow == fast) return true;
    }
    return false;
  }

  /*
  Digits	LargestNum	NextNum
    1	    9	          81
    2	    99	        162
    3	   999	        243
    4	   9999	        324
    13	9999999999999	1053
    so it is n --> log10(n)
    For a number with 3 digits, it's impossible for it to ever go larger than 243.
    This means it will have to either get stuck in a cycle below 243 or go down to 1.
    Numbers with 4 or more digits will always lose a digit at each step until they are down to 3 digits.
    So we know that at worst, the algorithm might cycle around all the numbers under 243 and then go back to a cycle OR to 1.
    Time complexity : O(243⋅3+log(n)+loglog(n)+...)= O(logn).
   */
  private int getNext(int n) {
    int totalSum = 0;
    while (n > 0) {
      int digit = n % 10;
      n = n / 10;
      totalSum += digit * digit;
    }
    return totalSum;
  }

  public boolean isHappy_202Hashset(int n) {
    Set<Integer> seen = new HashSet<>();
    while (n != 1 && !seen.contains(n)) {
      seen.add(n);
      n = getNext(n);
    }
    return n == 1;
  }

  public boolean isHappy_202TwoPointers(int n) {
    int slowRunner = n;
    int fastRunner = getNext(n);
    while (fastRunner != 1 && slowRunner != fastRunner) {
      slowRunner = getNext(slowRunner);
      fastRunner = getNext(getNext(fastRunner));
    }
    // ~(A && B) is ~A OR ~B. so if fastRunner == 1 does not hold, then slow == fast, equivalent.
    return fastRunner == 1;
  }
}
