package com.wlwallis30.leetcode;

import java.util.*;

//1570
public class SparseVector {
  // Map the index to value for all non-zero values in the vector
  Map<Integer, Integer> idxValMap;

  SparseVector(int[] nums) {
    idxValMap = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] != 0) { idxValMap.put(i, nums[i]); }
    }
  }

  public int dotProduct(SparseVector vec) {
    int result = 0;

    // iterate through each non-zero element in this sparse vector
    // update the dot product if the corresponding index has a non-zero value in the other vector
    for (Integer i : this.idxValMap.keySet()) {
      if (vec.idxValMap.containsKey(i)) {
        result += this.idxValMap.get(i) * vec.idxValMap.get(i);
      }
    }
    return result;
  }
}
