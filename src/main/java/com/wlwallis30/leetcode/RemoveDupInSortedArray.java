package com.wlwallis30.leetcode;

import java.util.*;

public class RemoveDupInSortedArray {
  // problem ask to return the size of new "array"
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

  //要点：对于不等于val的位置，自己赋值给自己没问题。fill idx 在过程中会跑到等于val的位置，让probe指向的值赋值过来
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

  //in place moving
  public int removeDuplicates80(int[] nums) {
    if (nums.length == 0) return 0;
    int keepPos = 0, cnt = 1, k = 2;
    //probe start from 1, so cnt = 1 for the first element
    for (int probe = 1; probe < nums.length; probe++) {
      if (nums[probe] != nums[keepPos] || cnt<k) {
        if(cnt<k && nums[probe] == nums[keepPos]) cnt++;
        else cnt = 1; //probe meet a new num
        keepPos++; // keepPos is position to be filled with num at original position or at num moving to left(due to previous dup > k)
        nums[keepPos] = nums[probe];
      }
      //this imply: else condition of nums[probe] == nums[keepPos] && cnt==k) probe++, just moving when same numb appear more than k times.
    }
    return keepPos + 1;
  }

  public int removeDuplicates80AnotherWay(int[] nums) {
    if (nums.length == 0) return 0;
    int keepPos = 0, cnt = 1, k = 2;
    //probe start from 1, so cnt = 1 for the first element
    for (int probe = 1; probe < nums.length; probe++) {
      if (nums[probe] == nums[keepPos] && cnt >= k) {} // just moving probe when same numb appear more than k times.
      else {
        if(cnt<k && nums[probe] == nums[keepPos]) cnt++;
        else cnt = 1; //probe meet a new num
        keepPos++; // keepPos is position to be filled with num at original position or at num moving to left(due to previous dup > k)
        nums[keepPos] = nums[probe];
      }
    }
    return keepPos + 1;
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

  // removing the adjacent dup
  public String removeDuplicates1047Stack(String S) {
    StringBuilder sb = new StringBuilder();
    int sbLength = 0;
    for(char character : S.toCharArray()) {
      if (sbLength != 0 && character == sb.charAt(sbLength - 1))
        sb.deleteCharAt(sbLength-- - 1);
      else {
        sb.append(character);
        sbLength++;
      }
    }
    return sb.toString();
  }

  public String removeDuplicates1209(String s, int k) {
    StringBuilder sb = new StringBuilder(s);
    Stack<Integer> counts = new Stack<>();
    for (int i = 0; i < sb.length(); ++i) {
      if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
        counts.push(1);
      } else {
        int incremented = counts.pop() + 1;
        if (incremented == k) {
          // delete(start, end),  will do for [start, end)
          sb.delete(i - k + 1, i + 1);
          i = i - k;
        } else {
          counts.push(incremented);
        }
      }
    }
    return sb.toString();
  }

  public int[] sortedSquares977(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    int left = 0;
    int right = n - 1;

    for (int i = n - 1; i >= 0; i--) {
      int square;
      if (Math.abs(nums[left]) < Math.abs(nums[right])) {
        square = nums[right];
        right--;
      } else {
        square = nums[left];
        left++;
      }
      result[i] = square * square;
    }
    return result;
  }
}
