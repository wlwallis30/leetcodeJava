package com.wlwallis30.leetcode;

public class ReverseLinkedList {
  ListNode reverseList206(ListNode head) {
    if(head == null || head.next == null) return head;
    ListNode pre=null;
    ListNode cur=head;
    while(cur != null)
    {
      ListNode next=cur.next;
      cur.next=pre;
      pre=cur;
      cur=next;
    }

    return pre;
  }
  ListNode reverseBetween92(ListNode head, int m, int n) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode cur = dummy;
    ListNode prePartLast, firstToBeLast, pre=null, next=null;
    for (int i = 1; i <= m - 1; ++i) cur = cur.next;
    prePartLast = cur;
    cur=cur.next;
    firstToBeLast = cur;
    for (int i = m; i <= n; ++i) {
      next = cur.next;
      cur.next=pre;
      pre = cur;
      cur=next;
    }

    firstToBeLast.next = next;
    prePartLast.next=pre;

    return dummy.next;
  }
}
