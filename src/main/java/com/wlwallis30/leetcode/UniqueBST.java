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
    //you visit the left subtree first always, by using prev, b4 visit right subtree, you wanna compare cur.val and pre.val
    //it should be always > pre.val(coming from left subtree if it is cur root or from cur root if left subtree is done), otherwise invalid.
    if (prev != null && root.val <= prev) {
      return false;
    }
    prev = root.val;
    return isBSTInorderDFS(root.right);
  }

  public void swap(TreeNode a, TreeNode b) {
    int tmp = a.val;
    a.val = b.val;
    b.val = tmp;
  }
  TreeNode first = null, second = null, preVisit = null;
  //三个指针，first，second 分别表示第一个和第二个错乱位置的节点，pre 指向当前节点的中序遍历的前一个节点。
  // 这里用传统的中序遍历递归来做，不过在应该输出节点值的地方，换成了判断 pre 和当前节点值的大小
  /*
       1
      /
     3
      \
       2
       in this tree, the first will be 3, and second will be 2 then to be 1 due to inorder
        2
      /
     1
      \
       3
       the first will still be 3, second will just be 2 due to inorder.
       in both cases, first is 3, that is why we call preVisit and assign the preVisit to the first
   */
  public void findTwoSwapped(TreeNode cur) {
    if (cur == null) return;
    findTwoSwapped(cur.left);
    if (preVisit != null && cur.val < preVisit.val) {
      second = cur;
      if (first == null) first = preVisit;
      else return;
    }
    preVisit = cur;// let preVisit to be the cur, the second will be
    findTwoSwapped(cur.right);
  }

  public void recoverTree99InorderRecur(TreeNode root) {
    findTwoSwapped(root);
    swap(first, second);
  }

  // stack ops is very similar with BinaryTreeTraversal.inorderTraversal94_stack
  public void recoverTree99InorderStack(TreeNode root) {
    Deque<TreeNode> stack = new ArrayDeque();
    TreeNode x = null, y = null, pred = null;

    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.add(root);
        root = root.left;
      }
      root = stack.removeLast();
      if (pred != null && root.val < pred.val) {
        y = root;
        if (x == null) x = pred;
        else break;
      }
      pred = root;
      root = root.right;
    }
    swap(x, y);
  }
}
