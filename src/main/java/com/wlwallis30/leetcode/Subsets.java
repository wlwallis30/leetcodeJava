package com.wlwallis30.leetcode;

import java.util.*;

public class Subsets {
  /*
    []
    [1]
    [1 2]
    [1 2 3]
    [1 3]
    [2]
    [2 3]
    [3]
                          []
                   /          \
                  /            \
                 /              \
              [1]                []
           /       \           /    \
          /         \         /      \
       [1 2]       [1]       [2]     []
      /     \     /   \     /   \    / \
  [1 2 3] [1 2] [1 3] [1] [2 3] [2] [3] []
  可以构造一棵二叉树，左子树表示选择该层处理的节点，右子树表示不选择(removeLast)
   */
  List<List<Integer>> subsets78(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> out = new LinkedList<>();
    helperDFS(nums, 0, out, res);
    return res;
  }
  // to use removeLast, the it has to be LinkedList when passing
  void helperDFS(int[] nums, int curPos, LinkedList<Integer> out, List<List<Integer>> res) {
    res.add(new ArrayList<>(out));
    for (int i = curPos; i < nums.length; ++i) {
      out.add(nums[i]);
      helperDFS(nums, i + 1, out, res);
      out.removeLast();
    }
  }

  /*
  最开始是空集, now 处理1，就在空集上加1，为 [1]
     现在有两个子集 [] 和 [1]，
          下面来处理2，在之前的子集基础上，每个都加个2，可以分别得到 [2]，[1, 2]，
          那么现在所有的子集合为 [], [1], [2], [1, 2]
      []   before for loop
      [1]   end of 1
      [2]
      [1 2]  end of 2
      [3]
      [1 3]
      [2 3]
      [1 2 3] end of 3
   */
  public List<List<Integer>> subsets78Iteration(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    res.add(new ArrayList<>());

    for (int num : nums) {
      List<List<Integer>> newSubsets = new ArrayList<>();
      for (List<Integer> prevSubset: res) {
        //adding the num into previous res items
        //List<Integer> oneNewSubset = new ArrayList<>();
        //prevSubset.add(num);
        List<Integer> oneNewSubset = new ArrayList<>(prevSubset);
        oneNewSubset.add(num); // same as newSubsets.add(new ArrayList<Integer>(prevSubset){{add(num);}});
        newSubsets.add(oneNewSubset);
      }
      for (List<Integer> curr : newSubsets) res.add(curr);
    }
    return res;
  }
}
