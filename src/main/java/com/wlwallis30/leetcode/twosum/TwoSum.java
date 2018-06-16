/*
 * Created by larrywang in 2018
 * Copyright (c) https://github.com/wlwallis30/ 2018. All rights reserved.
 * Last modified on 6/15/18 1:37 AM
 */
package com.wlwallis30.leetcode.twosum;

import java.util.*;

public class TwoSum {
    public int[] twoSum_1(int[] nums, int target) {
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
          return new int[] { map.get(complement), i };
        }
        map.put(nums[i], i);
      }
      throw new IllegalArgumentException("No two sum solution");
    }

    public List<List<Integer>> threeSum_15(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums.length < 3) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ++i) {
          if (nums[i] > 0) break;
          if (i > 0 && nums[i] == nums[i - 1]) continue;
          int l = i + 1, r = nums.length - 1;
          while (l < r) {
            int tmpSum = nums[i] + nums[l] + nums[r];
            if (tmpSum == 0) {
              res.add(Arrays.asList(nums[i], nums[l], nums[r]));
              ++l;
              while (nums[l] == nums[l - 1] && l < r)  ++l; // skip the same b
              --r;
              while (nums[r] == nums[r + 1] && l < r)  --r; // skip the same c
              } else if (tmpSum > 0) {
              --r;
              while (nums[r] == nums[r + 1] && l < r) --r;
            } else {
              ++l;
              while (nums[l] == nums[l - 1] && l < r) ++l;
            }
          }
        }
        return res;
    }
}
