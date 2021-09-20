package com.wlwallis30.leetcode;

import java.util.*;

// 173
public class BSTIterator {
  private Stack<TreeNode> treeStack = new Stack<>();
  BSTIterator(TreeNode root) {
    while(root != null) { treeStack.push(root); root=root.left; }
  }

  /** @return whether we have a next smallest number */
  boolean hasNext() { return !treeStack.empty(); }

  /** @return the next smallest number */
  int next() {
    TreeNode cur = treeStack.pop();
    int res = cur.val;
    if(cur != null) {
      cur=cur.right;
      while(cur != null) { treeStack.push(cur); cur=cur.left; } }

    return res;
  }
}

//251
class Vector2D {
  private int[][] vector;
  private int colPos = 0;
  private int rowPos = 0;

  //[[], [2], [], [], []] is a valid input
  public Vector2D(int[][] v) {
    // We need to store a *reference* to the input vector.
    this.vector = v;
  }

  public int next() {
    // if hitting [ ...[]... ],  calling hasNext will advance to some real element
    if (!hasNext()) throw new NoSuchElementException();
    return vector[rowPos][colPos++];
  }

  public boolean hasNext() {
    // If the current rowPos and colPos point to an integer, this method does nothing.
    // Ensure the position pointers are moved such they point to an integer,
    while (rowPos < vector.length && colPos == vector[rowPos].length) {
      colPos = 0;
      rowPos++;
    }
    // If rowPos = vector.length then there are no integers left, otherwise we've stopped at an integer and so there's an integer left.
    return rowPos < vector.length;
  }
}
/*  with native iterators,
public class Vector2D {
    Iterator<List<Integer>> itrs;
    Iterator<Integer> row;
    public Vector2D(List<List<Integer>> vec2d) {
        if(vec2d == null || vec2d.size() == 0) return;
        itrs = vec2d.iterator();
        row = itrs.next().iterator();
        getNextRow();
    }

    private void getNextRow(){
        while(!row.hasNext() && itrs.hasNext()) row = itrs.next().iterator();
    }

    public int next() {
        int next = row.next();
        getNextRow();
        return next;
    }

    public boolean hasNext() {
        return row != null && row.hasNext();
    }
}
 */
