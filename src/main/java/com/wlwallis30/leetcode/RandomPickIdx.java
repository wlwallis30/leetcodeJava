package com.wlwallis30.leetcode;

import java.util.*;

public class RandomPickIdx {
  private HashMap<Integer, List<Integer>> indices;
  private Random rand;
  private int[] nums;

  // 398
  public RandomPickIdx (int[] nums) {
    this.rand = new Random();
    this.indices = new HashMap<>();
    int l = nums.length;
    for (int i = 0; i < l; ++i) {
      if (!this.indices.containsKey(nums[i])) { this.indices.put(nums[i], new ArrayList<>()); }
      this.indices.get(nums[i]).add(i);
    }
  }

  public int pick(int target) {
    int counts = indices.get(target).size();
    // pick an index at random
    int randomIndex = indices.get(target).get(rand.nextInt(counts));
    return randomIndex;
  }

  /*
  1/i * i/(i+1) * (i+1)/(i+2) ....* (n-2)/(n-1) * (n-1)/n = 1/n
  This can be interpreted as
  Picking the ith number from the list of ii numbers
  Not picking the (i+1)th number from the list of (i+1) numbers. Hence picking any of the remaining i numbers.
  And so on ...
  Not picking the nth number from the list of (n) numbers. Hence picking any of the remaining (n−1) numbers.
   */
  public int pick_reservior(int target) {
    int n = this.nums.length;
    int count = 0;
    int idx = 0;
    for (int i = 0; i < n; ++i) {
      // if nums[i] is equal to target, i is a potential candidate
      if (this.nums[i] == target) {
        // increment the count of total candidates
        count++;
        // we pick the current number with probability 1 / count (reservoir sampling)
        // from any point when  nextInt(count) ==0, we pick i, so that is 1/curCount possibility.
        // if the following rounds, nextInt(count) != 0, then not picking the next "i"s, then it form 1/i * i/(i+1) * (i+1)/(i+2) ....* (n-2)/(n-1) * (n-1)/n = 1/n
        if (rand.nextInt(count) == 0) { idx = i; }
      }
    }
    return idx;
  }

  // 382
  private ListNode head;
  public RandomPickIdx(ListNode head) {
    this.head = head;
    Random rand = new Random();
  }

  /** Returns a random node's value. */
  public int getRandom328() {
    int count = 0, chosenValue = 0;
    ListNode curr = this.head;
    while (curr != null) {
      count += 1;
      // decide whether to include the element in reservoir
      if (rand.nextInt(count) == 0) chosenValue = curr.val;
      // move on to the next node
      curr = curr.next;
    }
    return chosenValue;
  }
  //528, check the TwoSum file
}
