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
    if (left !=null  && left != p && left != q) return left;
    TreeNode right = lowestCommonAncestor236(root.right, p , q);
    if (left != null && right != null) return root;
    return left!=null ? left : right;
  }

  //1644
  int count = 0;
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode LCA = LCA(root, p, q);
    return count == 2 ? LCA : null;
  }

  public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return root;
    // if put the count++ block here, [3,5,1,6,2,0,8,null,null,7,4] 5 1 will return null since it will only get 5 and stop calling the left or right tree

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
}
