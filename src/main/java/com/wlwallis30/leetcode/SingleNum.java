package com.wlwallis30.leetcode;

import java.util.*;

public class SingleNum {
  public int singleNumber_136BitOps(int[] nums) {
    int res = 0;
    for (int num : nums) {
      res ^= num;
    }
    return res;
  }

  //hashmap, two passes
  public int singleNumber_136Hashmap(int[] nums) {
    HashMap<Integer, Integer> hash_table = new HashMap<>();

    for (int i : nums) {
      hash_table.put(i, hash_table.getOrDefault(i, 0) + 1);
    }
    for (int i : nums) {
      if (hash_table.get(i) == 1) {
        return i;
      }
    }
    return 0;
  }

  // you can also use hash map to solve, but here to show bit moving
  int singleNumberII_137BitMove(int[] nums) {
    int res = 0;
    for (int bitPos = 0; bitPos < 32; ++bitPos) {
      int sum = 0;
      for (int num: nums) {
        sum += (num >> bitPos) & 1;
      }
      res |= (sum % 3) << bitPos;
    }
    return res;
  }

  public int missingNumber_268Hashset(int[] nums) {
    Set<Integer> numSet = new HashSet<Integer>();
    for (int num : nums) numSet.add(num);

    int expectedNumCount = nums.length + 1;
    for (int number = 0; number < expectedNumCount; number++) {
      if (!numSet.contains(number)) {
        return number;
      }
    }
    return -1;
  }

  /*
  Index	0	1	2	3
  Value	0	1	3	4
  missing to find:
  =4∧(0∧0)∧(1∧1)∧(2∧3)∧(3∧4)
  =(4∧4)∧(0∧0)∧(1∧1)∧(3∧3)∧2
  =0∧0∧0∧0∧2
  =2
   */
  public int missingNumber_268BitXor(int[] nums) {
    int missing = nums.length;
    for (int i = 0; i < nums.length; i++) {
      missing ^= i ^ nums[i];
    }
    return missing;
  }

  int missingNumber_268Math(int[] nums) {
    int sum = 0, n = nums.length;
    for (int num : nums) {
      sum += num;
    }
    return  (n * (n + 1))/2 - sum;
  }
}
