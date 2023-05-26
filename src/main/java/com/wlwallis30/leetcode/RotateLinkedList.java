package com.wlwallis30.leetcode;

public class RotateLinkedList {
  // you can also use 2 pointers, as RemoveNthFromEnd problem, to advance to kth node from end...
  ListNode rotateRight61(ListNode head, int k) {
    if (head == null) return null;
    int n = 1;
    ListNode cur = head;
    while (cur.next != null) {
      ++n;
      cur = cur.next;
    }
    cur.next = head; // connect 5 to 1
    cur=head; // make cur point to 1
    int m = n - k%n;
    for (int i = 1; i < m; ++i) { //only moving m-1
      cur = cur.next;
    }
    head = cur.next;
    cur.next = null;
    return head;
  }

  public void rotate189Brute(int[] nums, int k) {
    // speed up the rotation
    k %= nums.length;
    int temp, previous;
    for (int i = 0; i < k; i++) {
      previous = nums[nums.length - 1];
      for (int j = 0; j < nums.length; j++) {
        temp = nums[j];
        nums[j] = previous;
        previous = temp;
      }
    }
  }

  public void rotate189ExtraArray(int[] nums, int k) {
    int[] extra = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      extra[(i + k) % nums.length] = nums[i];
    }
    // copy to nums from the new array
    for (int i = 0; i < nums.length; i++) {
      nums[i] = extra[i];
    }
  }

  // reverse total 2 times, O(2N), easy to think of
  public void rotate189Reverse(int[] nums, int k) {
    int n = nums.length;
    k %= nums.length;

    Solution.reverse(nums, 0, n-k-1);
    Solution.reverse(nums, n-k, n - 1);
    Solution.reverse(nums, 0, n - 1);
    // you can also do: reverse(nums, 0, n-1), then revers(nums, 0, k-1), then reverse(nums, k, n-1), better
  }

  public void rotate189CyclicJumpK(int[] nums, int k) {
    k = k % nums.length;
    int touched = 0;
    // touched to control the total num moved, start and curIdx to control current round of cyclic move
    for (int start = 0; touched < nums.length; start++) {
      int curIdx = start;
      int prevNum = nums[start];
      do {
        int nextIdx = (curIdx + k) % nums.length;
        int temp = nums[nextIdx];
        nums[nextIdx] = prevNum;
        prevNum = temp;
        curIdx = nextIdx;
        touched++;
      } while (start != curIdx);
    }
  }
}
