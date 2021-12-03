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

  // 938
  public int rangeSumBST(TreeNode root, int L, int R) {
    if (root == null) return 0;
    if (root.val < L) return rangeSumBST(root.right, L, R);
    if (root.val > R) return rangeSumBST(root.left, L, R);
    return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
  }

  // the smallest (head) and the pre(last) nodes. Node.left used to be the previous node, right to be next
  Node head = null;
  Node pre = null;
  public Node treeToDoublyList426(Node root) {
    if (root == null) return null;
    helper1(root);
    // close DLL
    pre.right = head;
    head.left = pre;
    return head;
  }

  //essentially it is in order traversal with adding some additional processing for pre not null and null
  public void helper(Node node) {
    if (node != null) {
      helper(node.left);
      if (pre != null) {
        // link the previous node (pre)
        // with the current one (node)
        pre.right = node;
        node.left = pre;
      }
      else {
        // keep the smallest node
        // to close DLL later on
        head = node;
      }
      pre = node;
      // right
      helper(node.right);
    }
  }

  // this is a better straight solution
  public void helper1(Node node) {
    if (node != null) {
      helper1(node.left);
      // the first node case is here
      if (head == null) {
        head = node;
        pre = node;
      } else {
        pre.right = node;
        node.left= pre;
        pre = node;
      }
      helper1(node.right);
    }
  }
}
