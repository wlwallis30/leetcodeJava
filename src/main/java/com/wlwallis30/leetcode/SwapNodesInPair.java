package com.wlwallis30.leetcode;

public class SwapNodesInPair {
  public ListNode swapPairs_24dfs(ListNode head) {

    // If the list has no node or has only one node left.
    if ((head == null) || (head.next == null)) {
      return head;
    }

    // Nodes to be swapped
    ListNode firstNode = head;
    ListNode secondNode = head.next;

    // Swapping
    firstNode.next  = swapPairs_24dfs(secondNode.next);
    secondNode.next = firstNode;

    // Now the head is the second node
    return secondNode;
  }

  //better for interview
  ListNode swapPairs_24(ListNode head) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode prev = dummy, cur = head;

    while(cur != null && cur.next != null) {
      prev.next = cur.next;
      cur.next = cur.next.next;
      prev.next.next = cur;
      prev = cur;
      cur = cur.next;
    }

    return dummy.next;
  }

  //1721 is also similar, but put in RemoveNfromEnd.java
}
