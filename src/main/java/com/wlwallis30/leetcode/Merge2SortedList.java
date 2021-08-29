package com.wlwallis30.leetcode;

public class Merge2SortedList {
  public ListNode mergeTwoLists_21(ListNode list1, ListNode list2) {
    // maintain an unchanging reference to node ahead of the return node.
    ListNode dummny = new ListNode(-1);

    ListNode cur = dummny;
    while (list1 != null && list2 != null) {
      if (list1.val <= list2.val) {
        cur.next = list1;
        list1 = list1.next;
      } else {
        cur.next = list2;
        list2 = list2.next;
      }
      cur = cur.next;
    }

    // At least one of list1 and list2 can still have nodes at this point, so connect
    // the non-null list to the end of the merged list.
    cur.next = list1 == null ? list2 : list1;

    return dummny.next;
  }

}
