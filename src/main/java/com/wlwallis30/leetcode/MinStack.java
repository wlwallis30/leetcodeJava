package com.wlwallis30.leetcode;

import java.util.*;

// 155
public class MinStack {
  private Stack<Integer> myStack;
  private Stack<Integer> minStack;
  public MinStack() {
    myStack = new Stack<>();
    minStack = new Stack<>();
  }

  public void push(int val) {
    myStack.push(val);
    if(minStack.empty() || val<= minStack.peek()) minStack.push(val);
  }

  public void pop() {
    if(!myStack.empty()) {
      if(myStack.peek().equals(minStack.peek())) minStack.pop();
      myStack.pop();
    }
  }

  public int top() {
    if(!myStack.empty()) return myStack.peek();
    return Integer.MIN_VALUE;
  }

  public int getMin() {
    if(!minStack.empty()) return minStack.peek();
    return Integer.MIN_VALUE;
  }
}

//716
//There will be at least one element in the stack when pop, top, peekMax, or popMax is called.
// Note: at least one element.........
// For example if we add [2, 1, 5, 3, 9], we'll remember maxStack [2, 2, 5, 5, 9]
class MaxStack {
  private Stack<Integer> stack;
  private Stack<Integer> maxStack;

  public MaxStack() {
    stack = new Stack();
    maxStack = new Stack();
  }
  public void push(int x) {
    // always push current max including x into maxStack when new value x comes in
    //int max = maxStack.isEmpty() ? x : maxStack.peek();
    //maxStack.push(max > x ? max : x); same logic as following
    if(maxStack.isEmpty()) maxStack.push(x);
    else maxStack.push(maxStack.peek() > x ? maxStack.peek() : x);
    stack.push(x);
  }

  public int pop() {
    // due to the above push behavior, always pop maxStack
    maxStack.pop();
    return stack.pop();
  }

  public int top() {
    return stack.peek();
  }

  public int peekMax() {
    return maxStack.peek();
  }

  public int popMax() {
    int max = peekMax();
    Stack<Integer> buffer = new Stack();
    //for example: [2, 1, 5, 3],  maxStack [2, 2, 5, 5], will pop 3 in stack, and pop 5 in maxStack in while loop. and push 3 in buffer
    while (top() != max) buffer.push(pop());
    //[2, 1, 5],  maxStack [2, 2, 5], then pop both 5, buffer has [3] now
    pop();
    //after while loop. now [2,1,3], maxStack [2,2,3]
    while (!buffer.isEmpty()) push(buffer.pop());
    return max;
  }
//  public void push(int x) {
//    if(maxStack.isEmpty() || x>maxStack.peek()) maxStack.push(x);
//    stack.push(x);
//  }
//
//  public int pop() {
//    if(maxStack.peek() == stack.peek()) maxStack.pop();
//    return stack.pop();
//  }
//
//  public int top() {
//    return stack.peek();
//  }
//
//  public int peekMax() {
//    return maxStack.peek();
//  }
//
//  public int popMax() {
//    int max = maxStack.pop();
//    // ---> throw empty exception here when dealing with
  // exception might happen due to: [2, 1], maxStack[2], when pop happens, 2 removed in both, then [1], maxStack[], need to rewrite below popMax
//    //["MaxStack","push","push","peekMax","push","top","popMax","top","push","push","push","top","push","pop","peekMax","popMax","push","popMax","push","top","top","popMax","popMax","push","push","push","peekMax","pop","push","peekMax","push","popMax","push","peekMax","pop","top","peekMax","top","peekMax","popMax","pop","top","push","peekMax","push","top","push","push","pop","push","push","push","popMax","top","push","popMax","peekMax","peekMax","push","pop","push","popMax","push","push","popMax","peekMax","pop","push","peekMax","popMax","popMax","popMax","push","push","peekMax","peekMax","pop","pop","popMax","push","peekMax","pop","top","top","push","push","top","push","pop","push","peekMax","popMax","push","peekMax","top","top","popMax","push","push","push","popMax"]
//    //[[],[-1919630],[7447491],[],[-2983652],[],[],[],[-3334898],[8846062],[2479120],[],[7050263],[],[],[],[-3743643],[],[-6196638],[],[],[],[],[-6436831],[-2835331],[3549770],[],[],[2643568],[],[-7853726],[],[-369996],[],[],[],[],[],[],[],[],[],[-9254475],[],[4462697],[],[7055993],[-4050360],[],[7162941],[-9620570],[6295114],[],[],[6366218],[],[],[],[-4009957],[],[4552628],[],[-7488568],[9893848],[],[],[],[1477827],[],[],[],[],[333068],[6486948],[],[],[],[],[],[-4255526],[],[],[],[],[-1598000],[-5135401],[],[-6279567],[],[-9375733],[],[],[-3078338],[],[],[],[],[8800414],[-4237017],[945773],[]]
//    Stack<Integer> tmpStack = new Stack();
//    while (!stack.empty() && stack.peek() != max) tmpStack.push(stack.pop());
//    if(!stack.empty()) stack.pop();
//    while (!tmpStack.isEmpty()) stack.push(tmpStack.pop());
//    return max;
//  }
}
