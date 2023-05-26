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

  //three pointers
  public List<List<Integer>> threeSum_15(int[] nums) {
    List<List<Integer>> res = new LinkedList<>();
    if (nums.length < 3) return res;
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; ++i) {
      if (nums[i] > 0) break;//after sorting, impossible to have num[i] + num after that = 0;
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
      if (i > 0 && nums[i] == nums[i-1]) { continue; } // skip same a
      for (int j = i + 1; j < nums.length - 2; j++) {
        if (j > i + 1 && nums[j] == nums[j - 1]) {  // skip same b
          continue;
        }
        int left = j + 1, right = nums.length - 1;
        while (left < right) {
          int sum = nums[i] + nums[j] + nums[left] + nums[right];
          if (sum == target) {
            res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
            ++left;
            while (nums[left] == nums[left - 1] && left < right) // skip same c
              ++left;
            --right;
            while (nums[right] == nums[right + 1] && left < right) // skip same d
              --right;
          } else if (sum > target) {
            --right;
            while (nums[right] == nums[right + 1] && left < right) --right;
          } else {
            ++left;
            while (nums[left] == nums[left - 1] && left < right) ++left;
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

  // culmulative sum, O(n^2)
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

  // first, people can think of culmulative sum/prefix sum, then upgrade to hashmap
  // some previous prefix sum + target = current prefix sum, keep moving keep finding the sub_arrays whose sum = target
  public int subarraySum560_map(int[] nums, int target) {
    int count = 0, sum = 0;
    HashMap <Integer, Integer> sumOccurMap = new HashMap<>();
    //init condition for sum = 0, cnt be 1 not 0, check 3, 4 in the example below, hitting 4, the cnt should be 2
    sumOccurMap.put(0, 1);
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      // 3 4 7 2 -3 1 4 2, target = 7
      //(0,1) (3, 1) (7, 1) (14,1 later will become 2), (16,1), (13, 1), (14, 1->2), (18, 1), (20, 1), final cnt is 4
      // you can play with // 3 4 7 2 -3 1 4 3, target = 7 as well
      // (0,1) (3, 1) (7, 1) (14,1 later will become 2), (16,1), (13, 1), (14, 1->2), (18, 1), (21, 1), then final cnt should be 5 since 14 has 2 cnt
      if (sumOccurMap.containsKey(sum - target)) count += sumOccurMap.get(sum - target);
      sumOccurMap.put(sum, sumOccurMap.getOrDefault(sum, 0) + 1);
    }
    return count;
  }

  //also refer to the above problem
  // a % c = b % c ==>  (a-b) % c = 0, building a hashmap with the mod as key and idx as value
  // [..4, 3, 3..] k=6, mod:4, mod:1, mod:4, so 3+3 can be divided by 6
  public boolean checkSubarraySum523(int[] nums, int k) {
    // maintain a hash map to store <sum % k, index>
    Map<Integer, Integer> modIdx = new HashMap<>();
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      sum %= k;
      // // case 1: culmulitive sum % k = 0, [23,2,4,6,6,...], k=7
      if (sum == 0 && i > 0) { return true; }
      // case 2: regular case
      if (modIdx.containsKey(sum) && i - modIdx.get(sum) > 1) { return true; }
      // without if, [5,3,0,0] k = 3 will keep moving (5: 0) to (5: 1) to (5: 2), etc, and when hitting 0, will not satisfy >1
      if (!modIdx.containsKey(sum)) {
        modIdx.put(sum, i);
//        System.out.println(modIdx.entrySet());
      }
    }
    return false;
  }
}

//528, prefix sum, linear search. diff from reservoir, refer to 398
class RandomPickWeight {
  private int[] prefixSums;
  private int totalSum;

  public RandomPickWeight(int[] w) {
    this.prefixSums = w;
    for (int i = 1; i < w.length; ++i) {
      this.prefixSums[i] = w[i] + this.prefixSums[i-1];
    }
    this.totalSum = this.prefixSums[w.length-1];
    // prefixSums is the ref of w, so w is also changed!!! even with prefixSums = new int[w.length]
    System.out.println(Arrays.toString(w));
  }

  //比如若权重数组为 [1, 3, 2] 的话，那么累加和数组为 [1, 4, 6]，整个的权重和为6，我们 rand() * 6，
  // 0 <= Math.random() < 1
  // 可以随机出范围 [0, 5] 内的数，随机到 0 则为第一个点，随机到 1，2，3 则为第二个点，
  // 随机到 4，5 则为第三个点，所以我们随机出一个数字x后，然后再累加和数组中查找第一个大于随机数x的数字
  public int pickIndex528() {
    double target = this.totalSum * Math.random();
    int low = 0, high = this.prefixSums.length-1;
    while (low < high) {
      // better to avoid the overflow
      int mid = low + (high - low) / 2;
      if (target > this.prefixSums[mid])
        low = mid + 1;
      else
        high = mid;
    }
    return low;
  }
}

//prefix sum, 303
class NumArray {
  private int[] sum;

  public NumArray(int[] nums) {
    sum = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
      sum[i + 1] = sum[i] + nums[i];
    }
  }

  public int sumRange(int i, int j) {
    return sum[j + 1] - sum[i];
  }

  //prefixSum, but no need to use array to record prefixSum
  public int pivotIndex724(int[] nums) {
    int sum = 0, leftsum = 0;
    for (int x: nums) sum += x;
    for (int i = 0; i < nums.length; ++i) {
      leftsum += nums[i];
      if (leftsum - nums[i] == sum - leftsum) return i;
    }
    return -1;
  }

  //prefix + hashMap
  public int maxSubArrayLen325(int[] nums, int k) {
    int prefixSum = 0;
    int res = 0;
    HashMap<Integer, Integer> indices = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      prefixSum += nums[i];

      // Check if all of the numbers seen so far sum to k.
      if (prefixSum == k) {
        res = i + 1;
      }
      // If any subarray seen so far sums to k, then update the length of the longest_subarray.
      //  sum of some part =  prefixSum-k, sum of some part= k,   these two parts sum = prefixSum
      if (indices.containsKey(prefixSum - k)) {
        res = Math.max(res, i - indices.get(prefixSum - k));
      }

      // Only add the current prefix_sum index pair to the map if the prefix_sum is not already in the map.
      //跳过原因是，这样可以是的subarray更长
      if (!indices.containsKey(prefixSum)) {
        indices.put(prefixSum, i);
      }
    }

    return res;
  }
}

//304, 一开始我们设想用dp[r][c] = dp[r][c-1] + dp[r-1][c] + matrix[r][c] - dp[r-1][c-1]， 但因为r-1, c-1会越界，所以我们让dp多一行多一列，303的道理也是类似
class NumMatrix {
  private int[][] dp;

  public NumMatrix(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return;
    dp = new int[matrix.length + 1][matrix[0].length + 1];
    for (int r = 0; r < matrix.length; r++) {
      for (int c = 0; c < matrix[0].length; c++) {
        dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
      }
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
//    return dp[row2 + 1][col2 + 1] - dp[row1-1+1][col2 + 1] - dp[row2 + 1][col1-1+1] + dp[row1-1+1][col1-1+1];
  }
}

