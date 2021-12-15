package com.wlwallis30.leetcode;

public class PartitionList {
  public ListNode partitionList86(ListNode head, int x) {
    // before and after pointers moving and will create the two list
    // before_dummy and after_dummy are used to save the heads of the two lists.
    ListNode before_dummy = new ListNode(0);
    ListNode before = before_dummy;
    ListNode after_dummy = new ListNode(0);
    ListNode after = after_dummy;

    while (head != null) {
      if (head.val < x) {
        before.next = head;
        before = before.next;
      } else {
        after.next = head;
        after = after.next;
      }

      // move ahead in the original list
      head = head.next;
    }

    // Last node of "after" list would also be ending node of the reformed list
    after.next = null;

    // Once all the nodes are correctly assigned to the two lists,
    // combine them to form a single list which would be returned.
    before.next = after_dummy.next;

    return before_dummy.next;
  }
}
