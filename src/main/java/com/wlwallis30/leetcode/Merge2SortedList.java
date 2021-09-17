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

  ListNode insertionSortList147(ListNode head) {
    ListNode res = new ListNode(-1);
    ListNode cur;// now cur.next is null to make while loop generic even for a starting dummy node.
    ListNode toInsert = head;
    while (toInsert != null) {
      ListNode toInsertNext = toInsert.next;
      cur = res;
      while (cur.next != null && cur.next.val <= toInsert.val) {
        cur = cur.next;
      }
      toInsert.next = cur.next;
      cur.next = toInsert;
      toInsert = toInsertNext;
    }
    return res.next;
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
  // when finding the middle in linkedlist, you could use pattern while (fast.next != null && fast.next.next != null)
  void reorderList143(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) return;
    ListNode fast = head;
    ListNode slow = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    // slow is the middle for the odd num of list, is the last of 1st half for the even num of list
    ListNode last = slow.next;
    slow.next = null;
    ListNode pre = null;
    //reversing the second half
    while (last != null) {
      ListNode next = last.next;
      last.next = pre;
      pre = last;
      last = next;
    }
    while (head != null && pre != null) {
      ListNode tmp1 = head.next;
      ListNode tmp2 = pre.next;

      head.next = pre;
      pre.next = tmp1;
      pre=tmp2;
      head = tmp1;
    }
  }
}
