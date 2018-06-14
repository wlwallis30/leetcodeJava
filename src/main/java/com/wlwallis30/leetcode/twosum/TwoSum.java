package com.wlwallis30.leetcode.twosum;

/**
 * Created by larrywang on 6/13/18.
 */

import java.util.*;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
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

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                if (nums[i] + nums[l] + nums[r] == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (++l < r && nums[l] == nums[l - 1]) ; // skip the same b
                    while (--r > l && nums[r] == nums[r + 1]) ; // skip the same c
                } else if (nums[i] + nums[l] + nums[r] > 0) {
                    while (--r > l && nums[r] == nums[r + 1]) ;
                } else {
                    while (++l < r && nums[l] == nums[l - 1]) ;
                }
            }
        }

        return res;
    }
}
