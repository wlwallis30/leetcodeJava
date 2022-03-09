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
    // if(root.left != null) can be removed, since this DFS has base case of root==null return
    if(root.left != null)binaryTreePathsDFS(root.left, out+"->", res);
    if(root.right != null)binaryTreePathsDFS(root.right, out+"->", res);
  }

  int sumNumbers129Recur(TreeNode root) {
    return sumNumbersDFS(root, 0);
  }
  int sumNumbersDFS(TreeNode root, int sum) {
    if (root == null) return 0;
    sum = sum  + root.val;
    //多写，要么可以减支要么可以避免犯错， you can not return: sum + sumNumbersDFS(root.left, sum*10) + sumNumbersDFS(root.right, sum*10);
    if (root.left == null && root.right == null) return sum;
    return sumNumbersDFS(root.left, sum*10) + sumNumbersDFS(root.right, sum*10);
  }

  int max_sum = Integer.MIN_VALUE;
  public int max_gain(TreeNode node) {
    if (node == null) return 0;

    // max sum on the left and right sub-trees of node
    int leftMax = max_gain(node.left);
    int rightMax = max_gain(node.right);
    // leftMax, rightMax might be negative!
    int singleSideMax = Math.max(node.val, Math.max(leftMax + node.val, rightMax + node.val));

    int maxWithNode = Math.max(node.val + leftMax + rightMax, singleSideMax);

    // update max_sum with this node in the path
    max_sum = Math.max(max_sum, maxWithNode);

    // return the max gain to its parent
    return  singleSideMax;
  }

  public int maxPathSum124(TreeNode root) {
    max_gain(root);
    return max_sum;
  }

  //////////////////////////////////////////////iterative//////////////////////////////////////////////////
  public boolean hasPathSum_112Stack(TreeNode root, int sum) {
    if (root == null)
      return false;

    // you can also user Pair<Treenode, Integer> as shown in BinaryTreeTraversal
    // Linklist.add/addlast will add to the last, poll will get first to behave Queue, polllast will get last to be Stack
    // ArrayDequeue can be used for both Stack and Queue, and it might be faster than Stack and LinkedList
    LinkedList<TreeNode> node_stack = new LinkedList<>();
    LinkedList<Integer> sum_stack = new LinkedList<>();
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

  // stack of node
  TreeNode str2tree536(String s) {
    if (s.isEmpty()) return null;
    Stack<TreeNode> stack = new Stack<>();
    for (int i = 0; i < s.length(); ++i) {
      int start = i;
      if (s.charAt(i) == ')') stack.pop();
      else if (Character.isDigit(s.charAt(i)) || s.charAt(i) == '-') {
        while (i + 1 < s.length() && Character.isDigit(s.charAt(i+1))) ++i;
        int num = Integer.parseInt(s.substring(start, i+1));
        TreeNode cur = new TreeNode(num);

        if (!stack.empty()) {
          TreeNode node = stack.peek();
          // 4(2(3)(1))(6(5)), when hitting 2, 4 does not have the left child yet. hitting 6, 4 already has 2 as left child
          if (node.left == null) node.left = cur;
          else node.right = cur;
        }
        stack.push(cur);
      }
      //hitting (, just skip, ) is enough to handle
    }
    return stack.peek();
  }
}
