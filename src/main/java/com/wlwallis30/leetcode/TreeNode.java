package com.wlwallis30.leetcode;

public class TreeNode {
  int val;
  com.wlwallis30.leetcode.TreeNode left;
  com.wlwallis30.leetcode.TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}