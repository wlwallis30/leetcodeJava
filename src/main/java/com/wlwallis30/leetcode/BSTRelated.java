package com.wlwallis30.leetcode;

public class BSTRelated {
  // the tree is binary search tree
  int closestValue270(TreeNode root, double target) {
    int res = root.val;
    while(root != null)
    {
      if(Math.abs(root.val-target) < Math.abs(res-target)) res= root.val;
      root= target<root.val ? root.left: root.right;
    }

    return res;
  }
}
