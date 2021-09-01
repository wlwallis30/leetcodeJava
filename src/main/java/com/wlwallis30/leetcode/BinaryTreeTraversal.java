package com.wlwallis30.leetcode;

import java.util.*;
import javafx.util.Pair;

public class BinaryTreeTraversal {
  public List < Integer > inorderTraversal94_recursive(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    inorderDFS(root, res);
    return res;
  }

  public void inorderDFS(TreeNode root, List < Integer > res) {
    if (root == null) return;

    if (root.left != null)  inorderDFS(root.left, res);
    res.add(root.val);
    if (root.right != null) inorderDFS(root.right, res);
  }

  public List < Integer > preTraversal144_recursive(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    preorderDFS(root, res);
    return res;
  }

  public void preorderDFS(TreeNode root, List < Integer > res) {
    if (root == null) return;

    res.add(root.val);
    if (root.left != null)  preorderDFS(root.left, res);
    if (root.right != null) preorderDFS(root.right, res);
  }

  public List < Integer > postTraversal145_recursive(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    postorderDFS(root, res);
    return res;
  }

  public void postorderDFS(TreeNode root, List < Integer > res) {
    if (root == null) return;

    if (root.left != null)  postorderDFS(root.left, res);
    if (root.right != null) postorderDFS(root.right, res);
    res.add(root.val);
  }

  public boolean isSameTree_100Recur(TreeNode p, TreeNode q) {
    // p and q are both null
    if (p == null && q == null) return true;
    // one of p and q is null
    if (q == null || p == null || p.val != q.val) return false;
    return isSameTree_100Recur(p.right, q.right) &&
        isSameTree_100Recur(p.left, q.left);
  }

  public boolean isSymmetricTree_101Recur(TreeNode root) {
    if(root == null) return true;
    return isSymDFS(root.left, root.right);
  }

  boolean isSymDFS(TreeNode curLeft, TreeNode curRight) {
    if(curLeft == null && curRight == null) return true;
    if((curLeft == null && curRight != null)
        || (curLeft != null && curRight == null)
        || curLeft.val != curRight.val) return false;
    return isSymDFS(curLeft.left, curRight.right) && isSymDFS(curLeft.right, curRight.left);
  }

  public int maxDepth_104Recur(TreeNode root) {
    // this is like post order
    if (root == null) return 0;
    int left_height = maxDepth_104Recur(root.left);
    int right_height = maxDepth_104Recur(root.right);
    return Math.max(left_height, right_height) + 1;
  }

  boolean isBalanced_110(TreeNode root) {
    if (checkDepthDFS(root) == -1) return false;
    else return true;
  }
  int checkDepthDFS(TreeNode root) {
    if (root == null) return 0;

    int left = checkDepthDFS(root.left);
    if (left == -1) return -1;
    // if left is not balanced, no need to go through right
    int right = checkDepthDFS(root.right);
    if (right == -1) return -1;

    if (Math.abs(left - right) > 1) return -1;
    else return 1 + Math.max(left, right); //must use max, otherwise not reflecting enough diff, e.g. [1,null,2,null,3]
  }

  public int minDepth_111Recur(TreeNode root) {
    if (root == null) {
      return 0;
    }

    if ((root.left == null) && (root.right == null)) {
      return 1;
    }

    int min_depth = Integer.MAX_VALUE;
    if (root.left != null) {
      min_depth = Math.min(minDepth_111Recur(root.left), min_depth);
    }
    if (root.right != null) {
      min_depth = Math.min(minDepth_111Recur(root.right), min_depth);
    }

    return min_depth + 1;
  }

  List<List<Integer>> levels = new ArrayList<List<Integer>>();
  public List<List<Integer>> levelOrder_102Recur(TreeNode root) {
    if (root == null) return levels;
    levelOrderDFS(root, 0);
    return levels;
  }

  public void levelOrderDFS(TreeNode node, int curLevel) {
    if(node == null) return;
    // start the current level
    if (levels.size() == curLevel)
      levels.add(new ArrayList<Integer>());

    // fulfil the current level
    List<Integer> curLevelList = levels.get(curLevel);
    curLevelList.add(node.val);

    // process child nodes for the next level
    if (node.left != null)
      levelOrderDFS(node.left, curLevel + 1);
    if (node.right != null)
      levelOrderDFS(node.right, curLevel + 1);
  }
//////////////////////////////////////////////// iterations///////////////////////////////////////////////////////////////////////////////////
  public List < Integer > inorderTraversal94_stack(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    Stack < TreeNode > stack = new Stack < > ();
    TreeNode curr = root;
    // for inOrder, not smooth to push root first due to the following keep pushing till most left,
    // so put curr!=null condition in while
    while (curr != null || !stack.isEmpty()) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
      curr = stack.pop();
      res.add(curr.val);
      curr = curr.right;
    }
    return res;
  }

  // similar with the above, not push root first, put it in to while
  public List < Integer > preTraversal144_stack(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    Stack < TreeNode > stack = new Stack < > ();
    TreeNode curr = root;
    while (curr != null || !stack.isEmpty()) {
      if (curr != null) {
        res.add(curr.val);
        stack.push(curr);
        curr = curr.left;
      } else {
        curr = stack.peek();
        stack.pop();
        curr = curr.right;
      }
    }

    return res;
  }

  // push root first, recommended since many other iterative algo for trees use this approach for stack approach.
  public List < Integer > preTraversal144_stackPushFirst(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    if(root == null) return res;

    Stack < TreeNode > stack = new Stack < > ();
    TreeNode curr;
    stack.push(root);
    while (!stack.isEmpty()) {
      curr = stack.peek();
      stack.pop();
      res.add(curr.val);
      if(curr.right != null) stack.push(curr.right);
      if(curr.left != null) stack.push(curr.left);
    }

    return res;
  }

  boolean isSameValNode(TreeNode p, TreeNode q) {
    if(p == null && q == null) return true;
    if(p == null || q == null || p.val != q.val) return false;
    return true;
  }

  // level order is also BFS, so using queue,
  public boolean isSameTree_100LevelOrder(TreeNode p, TreeNode q) {
    if(p == null && q == null) return true;
    // java do not allow null in ArrayDeque as queue or stack, so we need to handle first
    // this is why we need isSameValNode method!!!, then we can avoid using if(p.left != null)
    // you can add null to ArrayList, Stack and LinkedList
    if(!isSameValNode(p, q)) return false;
    ArrayDeque<TreeNode> que = new ArrayDeque<>();

    // addFirst and push are same, push used when deque is used as STACK
    que.addLast(p);
    que.addLast(q);
    while(!que.isEmpty()) {
      p = que.removeFirst();
      q = que.removeFirst();
      // here p an q should be not-null, but we can still use that method
      if(!isSameValNode(p,q)) return false;
      if(!isSameValNode(p.left, q.left)) return false;
      if(!isSameValNode(p.right, q.right)) return false;
      // now p or q should not be null anymore
      if(p.left != null) {
        // make sure push back p.left first
        que.addLast(p.left);
        que.addLast(q.left);
      }
      if(p.right != null) {
        que.addLast(p.right);
        que.addLast(q.right);
      }
    }

    return true;
  }

  // stack in java: Stack, queue in java: LinkedList, Linkedlist can also be used as stack
  // Linklist.add/addlast will add to the last, poll will get first to behave Queue, polllast will get last to be Stack
  // ArrayDequeue can be used for both Stack and Queue, and it might be faster than Stack and LinkedList
  public boolean isSymmetric_101LevelOrder(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    // small trick here, add root twice.
    q.add(root);
    q.add(root);
    while (!q.isEmpty()) {
      TreeNode t1 = q.poll();
      TreeNode t2 = q.poll();
      if (t1 == null && t2 == null) continue;
      if (t1 == null || t2 == null) return false;
      if (t1.val != t2.val) return false;
      q.add(t1.left);
      q.add(t2.right);
      q.add(t1.right);
      q.add(t2.left);
    }
    return true;
  }

  // stack is approach to fulfill recursion while queue is for level order
  public int maxDepth_104stack(TreeNode root) {
    LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
    if (root == null) return 0;
    stack.add(new Pair(root, 1));

    int depth = 0, current_depth = 0;
    while(!stack.isEmpty()) {
      Pair<TreeNode, Integer> cur = stack.pollLast();
      root = cur.getKey();
      current_depth = cur.getValue();
      if (root != null) {
        depth = Math.max(depth, current_depth);
        if(root.left != null) stack.add(new Pair(root.left, current_depth + 1));
        if(root.right != null) stack.add(new Pair(root.right, current_depth + 1));
      }
    }
    return depth;
  }

  public int minDepth_111LeverOrder(TreeNode root) {
    LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
    if (root == null) {
      return 0;
    }
    else {
      queue.add(new Pair(root, 1));
    }

    int current_depth = 0;
    while (!queue.isEmpty()) {
      Pair<TreeNode, Integer> current = queue.poll();
      root = current.getKey();
      current_depth = current.getValue();
      if ((root.left == null) && (root.right == null)) {
        // find the leaf node(not null), then break out of while loop, then it is the min and no need to use min comparison
        break;
      }
      if (root.left != null) {
        queue.add(new Pair(root.left, current_depth + 1));
      }
      if (root.right != null) {
        queue.add(new Pair(root.right, current_depth + 1));
      }
    }
    return current_depth;
  }

  public List<List<Integer>> levelOrder_102Que(TreeNode root) {
    List<List<Integer>> levels = new ArrayList<List<Integer>>();
    if (root == null) return levels;

    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(root);
    int level = 0;
    while ( !queue.isEmpty() ) {
      // start the current level
      levels.add(new ArrayList<Integer>());

      // number of elements in the current level
      int level_length = queue.size();
      for(int i = 0; i < level_length; ++i) {
        // in Linkedlist, remove will throw if empty while poll will return null
        TreeNode node = queue.remove();

        // fulfill the current level
        levels.get(level).add(node.val);

        // add child nodes of the current level
        // in the queue for the next level
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
      }
      // go to next level
      level++;
    }
    return levels;
  }
}