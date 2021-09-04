package com.wlwallis30.leetcode;

import java.util.*;

public class MajorityElement {
  //majority element always exists in the array. appears more than ⌊n / 2⌋ times, [3,2,3,1] is not valid case
  public int majorityElement169(int[] nums) {
    int vote = 0, majorityElement = Integer.MAX_VALUE;
    for(int num: nums) {
      if(majorityElement == num) ++vote;
      // when vote cancelled out by other num or just in the loop
      else if (vote == 0) { majorityElement = num; vote = 1; }
      // some other num still have more votes, so current num will cancel the votes for tmp majority
      else --vote;
    }

    return majorityElement;
  }

  public int majorityElement169Hashmap(int[] nums) {
    Map<Integer, Integer> counts = new HashMap<>();
    for (int num : nums) {
      if (!counts.containsKey(num)) counts.put(num, 1);
      else  counts.put(num, counts.get(num)+1);
    }


    Map.Entry<Integer, Integer> majorityEntry = null;
    for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
      if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
        majorityEntry = entry;
      }
    }

    return majorityEntry.getKey();
  }
}
