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

  public void merge2SortedArray_88(int[] nums1, int m, int[] nums2, int n) {
    int count = m + n - 1;
    --m; --n;
    while (m >= 0 && n >= 0) nums1[count--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
    while (n >= 0) nums1[count--] = nums2[n--];
  }

  ListNode sortList148(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode fast = head, slow = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    fast = slow.next;
    slow.next = null;
    slow = sortList148(head);
    fast = sortList148(fast);
    return mergeTwoLists_21(slow, fast);
  }
}
