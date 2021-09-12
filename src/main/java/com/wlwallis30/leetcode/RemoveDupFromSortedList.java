package com.wlwallis30.leetcode;

public class RemoveDupFromSortedList {
  ListNode deleteDuplicates83(ListNode head) {
    if( head == null || head.next == null) return head;

    ListNode cur = head;
    while(cur.next != null) {// no need to have cur != null condition
      if(cur.val == cur.next.val) cur.next =cur.next.next;
      else cur= cur.next;
    }
    return head;
  }
}
