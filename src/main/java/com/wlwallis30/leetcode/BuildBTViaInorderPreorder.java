package com.wlwallis30.leetcode;

import java.util.*;

// both 105, 106 use inorder array+inorderIdx map to split the tree at each root's idx
public class BuildBTViaInorderPreorder {
  int preorderIndex;
  Map<Integer, Integer> inorderIndexMap;
  public TreeNode buildTree105(int[] preorder, int[] inorder) {
    preorderIndex = 0;
    // build a hashmap to store value -> its index relations
    inorderIndexMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inorderIndexMap.put(inorder[i], i);
    }

    return helperDFS(preorder, 0, preorder.length - 1);
  }

  private TreeNode helperDFS(int[] preorder, int left, int right) {
    // if there are no elements to construct the tree
    if (left > right) return null;

    // select the preorder_index element as the root and increment it
    int rootValue = preorder[preorderIndex++];
    TreeNode root = new TreeNode(rootValue);

    // build left and right subtree
    // excluding inorderIndexMap[rootValue] element because it's the root
    root.left = helperDFS(preorder, left, inorderIndexMap.get(rootValue) - 1);
    root.right = helperDFS(preorder, inorderIndexMap.get(rootValue) + 1, right);
    return root;
  }

  int post_idx;
  int[] postorder;
  int[] inorder;
  HashMap<Integer, Integer> inorderIdxMap = new HashMap<>();

  public TreeNode helperDFS1(int in_left, int in_right) {
    if (in_left > in_right)
      return null;

    // pick up post_idx element as a root
    int root_val = postorder[post_idx--];
    TreeNode root = new TreeNode(root_val);

    // root splits inorder list
    // into left and right subtrees
    int index = inorderIdxMap.get(root_val);

    // build right subtree
    root.right = helperDFS1(index + 1, in_right);
    // build left subtree
    root.left = helperDFS1(in_left, index - 1);
    return root;
  }

  public TreeNode buildTree106(int[] inorder, int[] postorder) {
    this.postorder = postorder;
    this.inorder = inorder;
    // start from the last postorder element
    post_idx = postorder.length - 1;

    int idx = 0;
    for (Integer val : inorder)
      inorderIdxMap.put(val, idx++);
    return helperDFS1(0, inorder.length - 1);
  }
}
