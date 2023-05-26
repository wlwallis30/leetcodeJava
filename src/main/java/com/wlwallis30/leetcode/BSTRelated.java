package com.wlwallis30.leetcode;

public class BSTRelated {
  // the tree is binary search tree
  //if a target value lesser than root could be closer to the right subtree value, then it is the closest to the root first not the right subtree value
  int closestValue270(TreeNode root, double target) {
    int res = root.val;
    while(root != null)
    {
      if(Math.abs(root.val-target) < Math.abs(res-target)) res= root.val;
      root= target<root.val ? root.left: root.right;
    }

    return res;
  }

  //The number of nodes in the tree is in the range [1, many]
  public int closestValue270Recur(TreeNode root, double target) {
        int res = root.val;
        if (target < root.val && root.left!=null) {
            int l = closestValue270Recur(root.left, target);
            if (Math.abs(res - target) >= Math.abs(l - target)) res = l;
        } else if (target > root.val && root.right!=null) {
            int r = closestValue270Recur(root.right, target);
            if (Math.abs(res - target) >= Math.abs(r - target)) res = r;
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

  // the smallest (head) and the pre(last) nodes. Node.left used to be previous node(smaller val), right to be next(bigger node val)
  // (node.left points to smaller node, node.right to bigger one as original BST did, except the head and tail)
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
