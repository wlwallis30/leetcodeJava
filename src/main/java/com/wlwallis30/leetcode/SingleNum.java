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

  //using hashmap is also a solution, but here only listing the bit ops:
  public int[] singleNumberIII_260BitOps(int[] nums) {
    // bits kept for both of x and y which were seen only once
    int bitmaskForBoth = 0;
    for (int num : nums) bitmaskForBoth ^= num;

    // rightmost 1-bit diff between x and y
    int diffTeller = bitmaskForBoth & (-bitmaskForBoth);

    int x = 0;
    // bitmask which will contain only x, other repeated a will be off set by ^ Xor since it appears twice.
    for (int num : nums) if ((num & diffTeller) != 0) x ^= num;

    return new int[]{x, bitmaskForBoth^x};
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

  public List<Integer> grayCode89(int n) {
    int max = 1 << n; // will give 2^n, while n << 1 will do one left shift for n.
    List<Integer> res = new ArrayList<>();
    for(int i = 0; i < max; ++i) res.add((i>>1) ^ i);
    return  res;
  }
}
