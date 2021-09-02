package com.wlwallis30.leetcode;

public class ConvertSortedArryToBST {
  public TreeNode preorderDFS(int left, int right, int[] nums) {
    if (left > right) return null;

    // always choose left middle node as a root, for right middle: if ((left + right) % 2 == 1) ++p
    int p = (left + right) / 2;

    // preorder traversal: node -> left -> right
    TreeNode root = new TreeNode(nums[p]);
    root.left = preorderDFS(left, p - 1, nums);
    root.right = preorderDFS(p + 1, right, nums);
    return root;
  }

  public TreeNode sortedArrayToBST_108Recur(int[] nums) {
    return preorderDFS(0, nums.length - 1, nums);
  }

}
