package com.wlwallis30.leetcode;

public class RemoveDupInSortedArray {
  public int removeDuplicates_26(int[] nums) {
    if (nums.length == 0) return 0;
    int keepPos = 0;
    for (int probe = 1; probe < nums.length; probe++) {
      if (nums[probe] != nums[keepPos]) {
        keepPos++;
        nums[keepPos] = nums[probe];
      }
    }
    return keepPos + 1;
  }

  public int removeElement_27(int[] nums, int val) {
    int fillPos = 0;
    for (int probe = 0; probe < nums.length; probe++) {
      if (nums[probe] != val) {
        nums[fillPos] = nums[probe];
        fillPos++;
      }
    }
    return fillPos;
  }

  public ListNode removeElements_203(ListNode head, int val) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode prev = dummy, curr = head;
    while (curr != null) {
      if (curr.val == val) prev.next = curr.next;
      else prev = curr;
      curr = curr.next;
    }
    return dummy.next;
  }

  public void moveZeroes_283(int[] nums) {
    int left = 0, right = 0;
    while(right < nums.length) {
      if (nums[right] == 0) {
        right++;
      } else {
        Solution.swap(nums, left, right);
        left++;
        right++;
      }
    }
  }
}
