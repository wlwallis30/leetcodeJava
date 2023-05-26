package com.wlwallis30.leetcode;

import java.util.*;
import javafx.util.Pair;

class Node {
  public int val;
  public Node left;
  public Node right;
  public Node next;

  public Node() {}

  public Node(int _val) {
    val = _val;
  }

  public Node(int _val, Node _left, Node _right, Node _next) {
    val = _val;
    left = _left;
    right = _right;
    next = _next;
  }
}

public class BinaryTreePointNext {
  // for perfect BT!.  O(N), space O(N)
  public Node connect116(Node root) {
    if (root == null) { return root; }
    Queue<Node> levelOrderQue = new LinkedList<>();
    levelOrderQue.add(root);
    // each level
    // while(!levelOrderQue.isEmpty())
    while (levelOrderQue.size() > 0) {
      int size = levelOrderQue.size();
      for(int i = 0; i < size; i++) {
        Node node = levelOrderQue.poll();
        // This check is important. The queue will contain nodes from 2 levels at most.
        if (i < size - 1) { node.next = levelOrderQue.peek(); }
        if (node.left != null) { levelOrderQue.add(node.left); }
        if (node.right != null) { levelOrderQue.add(node.right); }
      }
    }

    return root;
  }

  //space only O(1), using two leftmost and head/dad pointers
  public Node connect116LeftmostPointer(Node root) {
    if (root == null) { return root; }
    Node leftmost = root;
    //since perfect BT, while until the last level
    //on each current level, head/dad as parent, help their sons to connects. both leftmost and dad are on the same level each time.
    while (leftmost.left != null) {
      Node dad = leftmost;
      while (dad != null) {
        // CONNECTION for their sons, type 1
        dad.left.next = dad.right;
        // CONNECTION for sons, type 2, head/dads previously connected
        if (dad.next != null) { dad.right.next = dad.next.left; }

        // should be out side of above, consider the right most node, you wanna fail the while loop, otherwise infinite loop
        dad = dad.next;
      }
      // Move onto the next level
      leftmost = leftmost.left;
    }

    return root;
  }

  // not perfect BT, but for Q, code is same
  public Node connect117(Node root) {
    return connect116(root);
  }
  /* space O(1)
   void connect(TreeLinkNode *root) {
        if(!root) return;

        // lm means means left most
        TreeLinkNode* lm =root;
        while(lm)
        {
            TreeLinkNode* dad =lm;
            while(dad&&!dad->left&&!dad->right) dad=dad->next;
            if(!dad)return;
            lm=dad->left?dad->left:dad->right;
            TreeLinkNode* cur=lm;
            while(dad)
            {
                if(cur==dad->left)
                {
                    if(dad->right)
                    {
                        cur->next=dad->right;
                        cur=cur->next;
                    }
                  dad=dad->next;
                }
                else if(cur==dad->right) dad=dad->next;
                else
                {
                    if(!dad->left&&!dad->right) {dad=dad->next; continue;}

                    // this is the case where dad x with child jump some non child nodes to another dad y with child
                    cur->next = dad->left?dad->left:dad->right;
                    cur=cur->next;
                }
            }

        }
    }
   */


  // level order
  /* queue: Throws exception  OR  Returns special value
  Insert    add(e)                offer(e)
  Remove   remove()                poll()
  Examine  element()               peek()
   */
  public List<Integer> rightSideView199(TreeNode root) {
    if (root == null) return new ArrayList<Integer>();

    Queue<TreeNode> queue = new ArrayDeque<>(){{ offer(root); }};
    List<Integer> rightside = new ArrayList<>();

    while (!queue.isEmpty()) {
      int levelLength = queue.size();

      for(int i = 0; i < levelLength; ++i) {
        TreeNode node = queue.poll();
        // if it's the rightmost element
        if (i == levelLength - 1) {
          rightside.add(node.val);
        }

        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
    }
    return rightside;
  }
}
