package com.wlwallis30.leetcode;

public class RemoveNfromEnd {
  public ListNode removeNthFromEnd_19(ListNode head, int n) {
    if(head == null) return null;
    ListNode first = head;
    ListNode second = head;
    // Advances first pointer so that the gap between first and second is n nodes apart
    for (int i = 1; i <= n; i++) {
      first = first.next;
    }
    // if first already reach after the end node, then return the "second" node since n is always valid
    if(first == null) return head.next;
    // Move first to the end, maintaining the gap
    while (first != null) {
      first = first.next;
      second = second.next;
    }
    second.next = second.next.next;
    return head;
  }

}
