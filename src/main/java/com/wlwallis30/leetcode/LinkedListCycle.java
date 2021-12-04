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


  //c is the distance b4 intersection, a is where slow and fast meet.
  // when slow and fast meet: 2(c+a) is twice the distance slow has gone, c+a+b+a is the distance fast goes
  // so 2(c+a) = c+a+b+a, so c+a = b+a, so c=b
  // another solution is using hashSet to store visited node, when visit again, then you find that node.
  public ListNode hasCycle_142(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode slow = head;
    ListNode fast = head;
    // && is needed
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if(slow == fast) break;
    }
    if(fast == null || fast.next == null) return null;
    ListNode nodeFrom0 = head;
    ListNode intersectNode = slow;
    while(nodeFrom0 != intersectNode) {
      nodeFrom0 = nodeFrom0.next;
      intersectNode = intersectNode.next;
    }
    return intersectNode;
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

  // in this problem, slow and fast point to different node. if you want to point to same node, you can use a flag in while, such as (..|| firstTimeFlag)
  public boolean isHappy_202TwoPointers(int n) {
    int slowRunner = n;
    int fastRunner = getNext(n);
    // when fastRunner=1 or slow and fast meet, we should jump out of while loop
    while (fastRunner != 1 && slowRunner != fastRunner) {
      slowRunner = getNext(slowRunner);
      fastRunner = getNext(getNext(fastRunner));
    }
    // ~(A && B) is ~A OR ~B. so if fastRunner == 1 does not hold, then slow == fast, equivalent.
    return fastRunner == 1;
  }
}
