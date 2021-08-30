package com.wlwallis30.leetcode;

import java.util.*;

public class NextPermutation {
  /*
  1　　2　　7　　4　　3　　1
  下一个排列为：
  1　　3　　1　　2　　4　　7
  the procedure is like:
  1　　2　　7　　4　　3　　1
<---  i  i+1       <--- j
  1　　3　　7　　4　　2　　1
  1　　3　　1　　2　　4　　7
   */

  void nextPermutation_31(int[] nums) {
    int i, j, n = nums.length;

    for (i = n - 2; i >= 0; --i) {
      if (nums[i + 1] > nums[i]) {
        for (j = n - 1; j >= i; --j) {
          if (nums[j] > nums[i]) break;
        }
        Solution.swap(nums,i, j);
        Solution.reverse(nums,i + 1);
        return;
      }
    }
    // if it is the last permutation like  4 3 2 1
    Solution.reverse(nums, 0);
  }

  public List<List<Integer>> permute_46(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<Integer> curRes = new ArrayList<>();
    int[] visited = new int[nums.length];
    Arrays.fill(visited, 0);
    permuteDFS(res, curRes, visited, nums);

    return res;
  }

  void permuteDFS(List<List<Integer>> res, List<Integer> curRes, int[] visited, int[] nums) {
    if(curRes.size() >= nums.length){
      //res.add(curRes), it will NOT work since pass-by-value! curRes is a obj reference,
      // at last, curRes will point to an empty arrayList. in C++, vector.pushback might push the value
      res.add(new ArrayList<Integer>(curRes));
    } else {
      for(int i=0; i<nums.length; ++i) {
        if(visited[i] == 0) {
          visited[i] = 1;
          curRes.add(nums[i]);
          permuteDFS(res, curRes, visited, nums);
          int idx = curRes.indexOf(nums[i]);
          curRes.remove(idx);
          visited[i] = 0;
        }
      }
    }
  }
}
