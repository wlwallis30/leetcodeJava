package com.wlwallis30.leetcode;

public class FlattenBTtoLinkedList {
  // preorder flatten
  void flatten114(TreeNode root) {
    if (root == null) return;
    if (root.left != null) flatten114(root.left);
    if (root.right != null) flatten114(root.right);
    TreeNode tmp = root.right;
    root.right = root.left;
    root.left = null;
    // this is important since the left sub trees is already flattened, need to get to the last one b4 connect
    //Input: root = [1,2,5,3,4,null,6]
    //Output: [1,null,2,null,3,null,4,null,5,null,6], for node 1, you move to 2, 3, 4, b4 you connect to 5
    while (root.right != null) root = root.right;
    root.right = tmp;
  }
}
