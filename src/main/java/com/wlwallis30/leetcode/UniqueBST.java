package com.wlwallis30.leetcode;

import apple.laf.JRSUIUtils.Tree;
import java.util.*;

public class UniqueBST {
  /*
  total number of unique BST G(n), is the sum of BST F(i,n) enumerating each number i (1 <= i <= n) as a root.
  G(n)=∑F(i,n)    ----------------------------(1)
  F(3,7)=G(2)⋅G(4). To generalise from the example, we could derive the following formula:
  F(i,n)=G(i−1)⋅G(n−i)  ---------------------(2)
  By combining the formulas (1), (2), we obtain a recursive formula for G(n)G(n), i.e.
  G(n)=∑G(i−1)⋅G(n−i) ------------------------(3)
   */
  public int numTrees96(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1; // empty tree can be also consider as one case
    dp[1] = 1;

    for (int i = 2; i <= n; ++i) {
      for (int j = 1; j <= i; ++j) {
        dp[i] += dp[j - 1] * dp[i - j];
      }
    }
    return dp[n];
  }

  List<TreeNode> generateTrees95(int n) {
    if(n ==0) return (new ArrayList<>());
    return generateTreesDFS(1, n);
  }
  List<TreeNode> generateTreesDFS(int start, int end) {
    List<TreeNode> subTree = new ArrayList<>();
    if (start > end) {subTree.add(null); return subTree;}
    else {
      for (int i = start; i <= end; ++i) {
        List<TreeNode> leftSubTree = generateTreesDFS(start, i - 1);
        List<TreeNode> rightSubTree = generateTreesDFS(i + 1, end);
        for (TreeNode oneLeftNode: leftSubTree) {
          for (TreeNode oneRightNode: rightSubTree) {
            TreeNode curRoot = new TreeNode(i);
            // if using curRoot.left = leftSubTree.get(leftSubTreeIdx), leftSubTree finally will be null during recursive
            // coz java pass-by-value, for List<TN>, it pass the reference by value, ref is not changed during current, you need to create a new copy!!!
            curRoot.left = oneLeftNode;
            curRoot.right = oneRightNode;
            subTree.add(curRoot);
          }
        }
      }
    }
    return subTree;
  }

  // vist left first, then it is pure Preorder, this solution visit right first, but fine.
  public boolean isBSTPreorderDFS(TreeNode root, Integer low, Integer high) {
    // Empty trees are valid BSTs.
    if (root == null) return true;
    // The current node's value must be between low and high.
    if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
      return false;
    }
    // The left and right subtree must also be valid.
    return isBSTPreorderDFS(root.right, root.val, high) && isBSTPreorderDFS(root.left, low, root.val);
  }

  public boolean isValidBST98Preorder(TreeNode root) {
    return isBSTPreorderDFS(root, null, null);
  }

  private Deque<TreeNode> stack = new LinkedList();
  private Deque<Integer> upperLimits = new LinkedList();
  private Deque<Integer> lowerLimits = new LinkedList();

  public void update(TreeNode root, Integer low, Integer high) {
    stack.add(root);
    lowerLimits.add(low);
    upperLimits.add(high);
  }

  // this is level order by queue
  public boolean isValidBST98LevelOrderQue(TreeNode root) {
    Integer low = null, high = null, val;
    update(root, low, high);

    while (!stack.isEmpty()) {
      root = stack.poll();
      low = lowerLimits.poll();
      high = upperLimits.poll();

      if (root == null) continue;
      val = root.val;
      if (low != null && val <= low) {
        return false;
      }
      if (high != null && val >= high) {
        return false;
      }
      //pay attention here, adding root twice, and make sure the right order of "low", "high"
      update(root.right, val, high);
      update(root.left, low, val);
    }
    return true;
  }

  private Integer prev;
  public boolean isValidBST98Inorder(TreeNode root) {
    prev = null;
    return isBSTInorderDFS(root);
  }

  private boolean isBSTInorderDFS(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (!isBSTInorderDFS(root.left)) {
      return false;
    }
    if (prev != null && root.val <= prev) {
      return false;
    }
    prev = root.val;
    return isBSTInorderDFS(root.right);
  }
}
