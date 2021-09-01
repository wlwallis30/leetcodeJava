package com.wlwallis30.leetcode;

import java.util.*;

public class BinaryTreeTraversal {
  public List < Integer > inorderTraversal94_recursive(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    inorderDFS(root, res);
    return res;
  }

  public void inorderDFS(TreeNode root, List < Integer > res) {
    if (root == null) return;

    if (root.left != null)  inorderDFS(root.left, res);
    res.add(root.val);
    if (root.right != null) inorderDFS(root.right, res);
  }

  public List < Integer > preTraversal144_recursive(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    preorderDFS(root, res);
    return res;
  }

  public void preorderDFS(TreeNode root, List < Integer > res) {
    if (root == null) return;

    res.add(root.val);
    if (root.left != null)  preorderDFS(root.left, res);
    if (root.right != null) preorderDFS(root.right, res);
  }

  public List < Integer > postTraversal145_recursive(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    postorderDFS(root, res);
    return res;
  }

  public void postorderDFS(TreeNode root, List < Integer > res) {
    if (root == null) return;

    if (root.left != null)  postorderDFS(root.left, res);
    if (root.right != null) postorderDFS(root.right, res);
    res.add(root.val);
  }

  public List < Integer > inorderTraversal94_stack(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    Stack < TreeNode > stack = new Stack < > ();
    TreeNode curr = root;
    while (curr != null || !stack.isEmpty()) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
      curr = stack.pop();
      res.add(curr.val);
      curr = curr.right;
    }
    return res;
  }

  public List < Integer > preTraversal144_stack(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    Stack < TreeNode > stack = new Stack < > ();
    TreeNode curr = root;
    while (curr != null || !stack.isEmpty()) {
      if (curr != null) {
        res.add(curr.val);
        stack.push(curr);
        curr = curr.left;
      } else {
        curr = stack.peek();
        stack.pop();
        curr = curr.right;
      }
    }

    return res;
  }
}