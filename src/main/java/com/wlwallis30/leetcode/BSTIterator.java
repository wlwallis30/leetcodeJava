package com.wlwallis30.leetcode;

import java.util.*;
import javafx.util.*;
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
//281
class ZigzagIterator {
  private List<List<Integer>> vectors = new ArrayList<>();
  private LinkedList<Pair<Integer, Integer>> queue = new LinkedList<>();

  public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
    this.vectors.add(v1);
    this.vectors.add(v2);
    int row = 0;
    for (List<Integer> vec : this.vectors) {
      // row and col pointers, using if in case empty v1 or v2
      if (vec.size() > 0) this.queue.add(new Pair<Integer, Integer>(row, 0));
      row++;
    }
  }

  public int next() {
    // if (this.queue.size() == 0)
    // throw new Exception("Out of elements!");
    Pair<Integer, Integer> pointer = this.queue.removeFirst();
    Integer rowIndex = pointer.getKey();
    Integer colIndex = pointer.getValue();
    int res = this.vectors.get(rowIndex).get(colIndex);
    //only if still element in there
    if (colIndex+1 < this.vectors.get(rowIndex).size())
      this.queue.addLast(new Pair<>(rowIndex, ++colIndex));

    return res;
  }

  public boolean hasNext() {
    return this.queue.size() > 0;
  }
}

//284, peek 就是偷看下一个, 所以两种状态，一种是没有偷看过， null， 一种是偷看过，not null
class PeekingIterator implements Iterator<Integer> {
  private Iterator<Integer> iter;
  private Integer peekVal = null;
  public PeekingIterator(Iterator<Integer> iterator) {
    this.iter = iterator;
  }

  // Returns the next element in the iteration without advancing the iterator.
  public Integer peek() {
    //peek not saved, now saving and only saving the peek here
    if(this.peekVal == null){
      if(this.iter.hasNext()) {
        this.peekVal = this.iter.next(); // saving the peek, peek is the next to be returned by calling next()
      }else { throw new NoSuchElementException();}
    }
    return this.peekVal;
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public Integer next() {
    //check if saved/called peek b4
    if(this.peekVal != null) {
      Integer res = this.peekVal;
      this.peekVal = null;
      return res;
    }
    if(!this.iter.hasNext()) throw new NoSuchElementException();
    return this.iter.next();
  }

  @Override
  public boolean hasNext() {
    //if already peeked, it could be no more next(hasNext is false), but need have peeked to return by next();
    return this.iter.hasNext() || peekVal != null;
  }
}

 interface NestedInteger {
  boolean isInteger();
  Integer getInteger();
  List<NestedInteger> getList();
  }

  //341
class NestedIterator implements Iterator<Integer> {
  private Deque<NestedInteger> stack;// could also use LinkedList
  public NestedIterator(List<NestedInteger> nestedList) {
    this.stack = new ArrayDeque(nestedList);
  }

  @Override
  public Integer next() {
    return stack.pop().getInteger();
  }

  @Override
  public boolean hasNext() {
    //[1,[4,[6]]], keep unpacking when it is still a list
    while(!stack.isEmpty() && !stack.peek().isInteger()) {
      //peek is peekFirst, pop is remove first
      List<NestedInteger> curList = stack.pop().getList();
      for(int idx =curList.size()-1; idx>= 0; --idx){ stack.addFirst(curList.get(idx)); }
      // [[1], 2, [3]]this will not work coz the constructor already put [1], 2 [3] in the stack, [1] is on top,
      // not putting [[1], 2, [3]] on top! so the first curList is [1], not [[1], 2, [3]]
      // for(int idx = 0; idx < curList.size(); ++idx){ stack.addLast(curList.get(idx)); }

    }

    return !stack.isEmpty();
  }
}