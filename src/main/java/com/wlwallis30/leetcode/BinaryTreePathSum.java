package com.wlwallis30.leetcode;

import java.util.*;

public class BinaryTreePathSum {
  public boolean hasPathSum_112Recur(TreeNode root, int sum) {
    if (root == null)
      return false;

    sum -= root.val;
    if ((root.left == null) && (root.right == null))
      return (sum == 0);
    return hasPathSum_112Recur(root.left, sum) || hasPathSum_112Recur(root.right, sum);
  }

  private void recurseTree(TreeNode node, int remainingSum, List<Integer> pathNodes, List<List<Integer>> res) {
    if (node == null) { return; }
    // try adding the current node to the path's list
    pathNodes.add(node.val);

    //leaf ? and remain is cur val
    if (remainingSum == node.val && node.left == null && node.right == null) {
      res.add(new ArrayList<>(pathNodes));
    } else {
      this.recurseTree(node.left, remainingSum - node.val, pathNodes, res); // no worries left is null, it will returned by null from next call
      this.recurseTree(node.right, remainingSum - node.val, pathNodes, res);
    }
    // tried adding b4, removing it
    pathNodes.remove(pathNodes.size() - 1);
  }

  public List<List<Integer>> pathSum113Recur(TreeNode root, int sum) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> pathNodes = new ArrayList<>();
    this.recurseTree(root, sum, pathNodes, res);
    return res;
  }

  List<String> binaryTreePaths257(TreeNode root) {
    List<String> res = new ArrayList<String>();;
    if (root == null) return res;
    binaryTreePathsDFS(root, "", res);
    return res;
  }
  void binaryTreePathsDFS(TreeNode root, String out, List<String> res) {
    if(root == null) return;
    out += root.val;
    if (root.left == null && root.right == null) res.add(out);
    if(root.left != null)binaryTreePathsDFS(root.left, out+"->", res);
    if(root.right != null)binaryTreePathsDFS(root.right, out+"->", res);
  }

  int sumNumbers129Recur(TreeNode root) {
    return sumNumbersDFS(root, 0);
  }
  int sumNumbersDFS(TreeNode root, int sum) {
    if (root == null) return 0;
    sum = sum  + root.val;
    if (root.left == null && root.right == null) return sum;
    return sumNumbersDFS(root.left, sum*10) + sumNumbersDFS(root.right, sum*10);
  }
  //////////////////////////////////////////////iterative//////////////////////////////////////////////////
  public boolean hasPathSum_112Stack(TreeNode root, int sum) {
    if (root == null)
      return false;

    // you can also user Pair<Treenode, Integer> as shown in BinaryTreeTraversal
    LinkedList<TreeNode> node_stack = new LinkedList();
    LinkedList<Integer> sum_stack = new LinkedList();
    node_stack.add(root);
    sum_stack.add(sum - root.val);

    TreeNode node;
    int curr_sum;
    while ( !node_stack.isEmpty() ) {
      node = node_stack.pollLast();
      curr_sum = sum_stack.pollLast();
      if ((node.right == null) && (node.left == null) && (curr_sum == 0))
        return true;

      if (node.right != null) {
        node_stack.add(node.right);
        sum_stack.add(curr_sum - node.right.val);
      }
      if (node.left != null) {
        node_stack.add(node.left);
        sum_stack.add(curr_sum - node.left.val);
      }
    }
    return false;
  }
}
