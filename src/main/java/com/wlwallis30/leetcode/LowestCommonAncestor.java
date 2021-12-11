package com.wlwallis30.leetcode;

public class LowestCommonAncestor {
  // 235
  public TreeNode lowestCommonAncestor235(TreeNode root, TreeNode p, TreeNode q) {
    int rootVal = root.val;
    int pVal = p.val;
    int qVal = q.val;
    if (rootVal > Math.max(pVal, qVal)){ return lowestCommonAncestor235(root.left, p, q); }
    else if (rootVal < Math.min(pVal, qVal)) { return lowestCommonAncestor235(root.right, p, q); }
    else { return root; } // from definition, if rootVal == one of the pVal qVal, also LCA
  }

  // 236
  TreeNode lowestCommonAncestor236(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null  || p == root || q == root) return root;
    TreeNode left = lowestCommonAncestor236(root.left, p, q);
    TreeNode right = lowestCommonAncestor236(root.right, p , q);
    if(left!=null && right!=null) return root;
    return left!=null ? left : right;
  }
  TreeNode lowestCommonAncestor236better(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null  || p == root || q == root) return root;
    TreeNode left = lowestCommonAncestor236better(root.left, p, q);
    //optimized: if left既不是p也不是q，绝对不会存在同时时还有p或者q在 right 中
    if (left !=null  && left != p && left != q) return left;
    //also left must be p or q now, but left maybe not LCA, if right not null, the other in right's subtree, so return root
    TreeNode right = lowestCommonAncestor236better(root.right, p , q);
    if (left != null && right != null) return root;
    //left or right, one of them might be null now. if left null, means right is LCA, if left not null, left=p or q, so LCA
    return left!=null ? left : right;
  }

  //1644, in 236, both p and q exist. but here one of them might not exist, that is why you need count!
  int count = 0;
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode LCA = LCA(root, p, q);
    return count == 2 ? LCA : null;
  }

  //this method is similar to 236 except the counting part
  public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return root;
    // if put the count++ block here, [3,5,1,6,2,0,8,null,null,7,4] 5 4,
    // final result will return null since it will only get 5 and stop calling the left or right tree

    TreeNode left = LCA(root.left, p, q);
    if (left !=null  && left != p && left != q) return left;
    TreeNode right = LCA(root.right, p, q);
    //has to be placed here. for the above example, it will find 5 and return here with 5
    if (root == p || root == q) {
      count++;
      return root;
    }
    if (left != null && right != null) return root;
    return left!=null ? left : right;
  }

  //both moving, shorter head move faster to end, jump to another head.
  //e.g. shorter has 4, longer has 5, intersect at the second from end. so in the end, both move 8 steps
  //  3+1+4 = 4+1+3, that is why we jump...
  // please refer to the problem 160, this LCA is as same as 160
  public Node lowestCommonAncestor1650(Node p, Node q) {
    Node p1 = p, p2 = q;
    while (p1 != p2) {
      p1 = p1 == null ? q : p1.parent;
      p2 = p2 == null ? p : p2.parent;
    }
    return p1;
  }
  class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
  }
}
