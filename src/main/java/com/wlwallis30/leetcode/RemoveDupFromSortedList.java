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

  ListNode deleteDuplicates82All(ListNode head) {
    if( head == null || head.next == null) return head;

    ListNode start = new ListNode(-1);
    start.next = head;
    ListNode pre = start;
    ListNode cur = head;
    //consider two cases to complete full logics:
    //  1 -> 1 -> 1-> 2-> 3-> 4
    // 1 -> 2 -> 3 -> 4 -> 4 -> 4 then when finish while(cur.next != null && cur.val == cur.next.val)
    // you will encounter the condition to tell if prev.next points to a dup node or regular node
    while(cur != null) {// no need to have cur != null condition
      while(cur.next != null && cur.val == cur.next.val)cur = cur.next;
      if(pre.next == cur) {cur = cur.next; pre = pre.next; }
      else { pre.next = cur.next; cur = pre.next;}
    }
    return start.next;
  }
}
