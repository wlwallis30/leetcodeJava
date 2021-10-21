/*
 * Created by larrywang in 2018
 * Copyright (c) https://github.com/wlwallis30/ 2018. All rights reserved.
 * Last modified on 6/15/18 1:37 AM
 */
package com.wlwallis30.leetcode;

//import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

//the problem 170 is to design the class as following
public class TwoSum {
  private Map<Integer, Integer> twoSumMap = new HashMap<>();
  //private HashMap<Integer, Integer> xyz = new HashMap<>();

  public void add(int num) {
    twoSumMap.put(num, twoSumMap.get(num) + 1);
  }

  public Boolean find(int target) {
    // if only interested in key, use for loop,
    // Iterator it = mp.entrySet().iterator();  while (it.hasNext()) Map.Entry pair = (Map.Entry)it.next();
    for (Integer key: twoSumMap.keySet()) {
      int search = target - key;
      if(search != key && twoSumMap.containsKey(search)
          || search == key && twoSumMap.get(search) > 1) return true;
    }
    return false;
  }

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
      int left = i + 1, right = nums.length - 1;
      while (left < right) {
        int tmpSum = nums[i] + nums[left] + nums[right];
        if (tmpSum == 0) {
          res.add(Arrays.asList(nums[i], nums[left], nums[right]));
          ++left;
          while (nums[left] == nums[left - 1] && left < right)  ++left; // skip the same b
          --right;
          while (nums[right] == nums[right + 1] && left < right)  --right; // skip the same c
        } else if (tmpSum > 0) {
          --right;
          while (nums[right] == nums[right + 1] && left < right) --right;
        } else {
          ++left;
          while (nums[left] == nums[left - 1] && left < right) ++left;
        }
      }
    }
    return res;
  }

  // 3 sum closest
  public int threeSum_16 (int[] nums, int target) {
    if (nums.length < 3) return Integer.MAX_VALUE;
    int closest = nums[0] + nums[1] + nums[2];
    int diff = Math.abs(closest - target);
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
      int left = i + 1, right = nums.length - 1;
      while(left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        int tmpDiff = Math.abs(sum - target);
        if(tmpDiff < diff) {
          closest = sum;
          diff = tmpDiff;
        }
        if(sum < target) left++;
        else right--;
      }
    }
    return closest;
  }

  public List<List<Integer>> fourSum_18(int[] nums, int target) {
    List<List<Integer>> res = new LinkedList<>();
    if (nums.length < 4)
      return res;
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 3; i++) {
      // this would prevent using res.stream().distinct()....
      if (i > 0 && nums[i] == nums[i-1]) { continue; }
      for (int j = i + 1; j < nums.length - 2; j++) {
        if (j > i + 1 && nums[j] == nums[j - 1]) {
          continue;
        }
        int left = j + 1, right = nums.length - 1;
        while (left < right) {
          int sum = nums[i] + nums[j] + nums[left] + nums[right];
          if (sum == target) {
            res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
            ++left;
            while (nums[left] == nums[left - 1] && left < right)
              ++left;
            --right;
            while (nums[right] == nums[right + 1] && left < right)
              --right;
          } else if (sum > target) {
            --right;
            while (nums[right] == nums[right + 1] && left < right)
              --right;
          } else {
            ++left;
            while (nums[left] == nums[left - 1] && left < right)
              ++left;
          }
        }
      }
  }
    //List xyz = new LinkedList(new HashSet<>(res));this will make not ordered,
    //Collections.sort(xyz) will throw since inside it is a ArrayList, not sortable
    //return res.stream().distinct().collect(Collectors.toList());
    return res;
  }

  public int[] twoSumSorted_167(int[] nums, int target) {
    int[] res = new int[]{};
    int left = 0, right = nums.length - 1;
    while(left < right) {
      int tmpSum = nums[left] + nums[right];
      if (tmpSum == target) {
        res = new int[]{left + 1, right + 1};
        return res;
      } else if (tmpSum < target) ++left;
      else --right;
    }
    return res;
  }

  public int threeSum_259(int[] nums, int target) {
	  int res = 0;
	  if(nums.length < 3) return res;
	  Arrays.sort(nums);
	  for(int i=0; i<nums.length-2; ++i) {
		  int left = i+1, right = nums.length-1;
		  while(left < right) {
			  if(nums[i] + nums[left] + nums[right]<target) {
				  res += right - left;
				  ++left;
			  }
			  else --right; 
		  }
	  }
	  return res;
  }

  public int subarraySum560(int[] nums, int k) {
    int count = 0;
    for (int start = 0; start < nums.length; start++) {
      int sum=0;
      for (int end = start; end < nums.length; end++) {
        sum+=nums[end];
        if (sum == k)
          count++;
      }
    }
    return count;
  }

  public int subarraySum560_map(int[] nums, int target) {
    int count = 0, sum = 0;
    HashMap <Integer, Integer> sumOccurMap = new HashMap<>();
    //init condition for sum = 0
    sumOccurMap.put(0, 1);
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      // 3 4 7 2 -3 1 7, target = 7
      //(0,1) (3, 1) (7, 1) (14,1 later will become 2), (16,1), (13, 1), (14, 1->2), (14, 2->3)
      if (sumOccurMap.containsKey(sum - target)) count += sumOccurMap.get(sum - target);
      sumOccurMap.put(sum, sumOccurMap.getOrDefault(sum, 0) + 1);
    }
    return count;
  }
}
