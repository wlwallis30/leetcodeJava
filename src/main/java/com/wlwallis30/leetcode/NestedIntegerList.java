package com.wlwallis30.leetcode;

import java.util.*;

public class NestedIntegerList {
  public interface NestedInteger {
    public boolean isInteger();
    public Integer getInteger();
    public void setInteger(int value);
    public void add(NestedInteger ni);
    public List<NestedInteger> getList();
  }

  public int depthSum339(List<NestedInteger> nestedList) {
    return dfsNestSum(nestedList, 1);
  }

  private int dfsNestSum(List<NestedInteger> list, int depth) {
    int total = 0;
    for (NestedInteger nested : list) {
      if (nested.isInteger()) { total += nested.getInteger() * depth; }
      else { total += dfsNestSum(nested.getList(), depth + 1); }
    }
    return total;
  }
}
