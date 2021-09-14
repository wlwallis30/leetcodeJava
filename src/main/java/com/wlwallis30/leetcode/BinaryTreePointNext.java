package com.wlwallis30.leetcode;

import java.util.*;

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
}
