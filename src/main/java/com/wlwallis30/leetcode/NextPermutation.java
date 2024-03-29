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
    // if it is the last permutation like  4 3 2 1 (where j=i, that is why we need j>=i in the above)
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
          // !!!: first idx to add should be "size",
          curRes.add(curRes.size(), nums[i]);
          //curRes.add(nums[i]);
          permuteDFS(res, curRes, visited, nums);
          curRes.remove(curRes.size()-1);
          //int idx = curRes.indexOf(nums[i]);
          //curRes.remove(idx);
          visited[i] = 0;
        }
      }
    }
  }

  public List<List<Integer>> permute_47(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<Integer> curRes = new ArrayList<>();
    int[] visited = new int[nums.length];
    Arrays.fill(visited, 0);
    Arrays.sort(nums);
    permuteDupDFS(res, curRes, visited, nums);

    return res;
  }

  void permuteDupDFS(List<List<Integer>> res, List<Integer> curRes, int[] visited, int[] nums) {
    if(curRes.size() >= nums.length){
      //res.add(curRes), it will NOT work since pass-by-value! curRes is a obj reference,
      // at last, curRes will point to an empty arrayList. in C++, vector.pushback might push the value
      res.add(new ArrayList<Integer>(curRes));
    } else {
      for(int i=0; i<nums.length; ++i) {
        if(visited[i] == 0) {
          // 这里的前一个数 visited 值为0，并不代表前一个数字没有被处理过，也可能是递归结束后恢复状态时将 visited 值重置为0了
          // e.g when [1, 2, 2] is one result, then 2 and 2 are popped, we are back to the top level of calling tree with result has [1], i=2,
          // now, then we dont wanna do putting [1,2] again since it is repeated pattern, and visited[1] = 0, coming from the resetting.
          //https://www.cnblogs.com/grandyang/p/4359825.html
          // we dont wanna repeat for same nums, previously : if num[i-1]==num[i], & if visit[i-1]=1, now visit[i]=0 b4 setting to 1, so [1,2,2] formed
          // but if visit[i-1]=0, and we set visit[i]=1, we just repeat the pattern since two num are same
          if(i > 0 && nums[i] == nums[i-1] && visited[i-1] == 0) continue;
          visited[i] = 1;
          curRes.add(curRes.size(),nums[i]);
          permuteDupDFS(res, curRes, visited, nums);
          curRes.remove(curRes.size()-1);
          //!!!: then size + 1, so removing idx is current size - 1(last one);
          // the following two lines will create chaos if nums= [1,1,2,2] when removing curRes since duplicate exists.
          //int idx = curRes.indexOf(nums[i]);  indexOf will return the first one, but we wanna remove the last one in Array
          //curRes.remove(idx);
          visited[i] = 0;
        }
      }
    }
  }
}