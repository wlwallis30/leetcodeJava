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

  // also refer to 341
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

  public int depthSumInverse364(List<NestedInteger> nestedList) {
    int maxDepth = findMaxDepth(nestedList);
    return weightedSum(nestedList, 1, maxDepth);
  }

  private int findMaxDepth(List<NestedInteger> list) {
    int maxDepth = 1;

    for (NestedInteger nested : list) {
      if (!nested.isInteger()) {
        maxDepth = Math.max(maxDepth, 1 + findMaxDepth(nested.getList()));
      }
    }

    return maxDepth;
  }

  private int weightedSum(List<NestedInteger> list, int depth, int maxDepth) {
    int answer = 0;
    for (NestedInteger nested : list) {
      if (nested.isInteger()) {
        answer += nested.getInteger() * (maxDepth - depth + 1);
      } else {
        answer += weightedSum(nested.getList(), depth + 1, maxDepth);
      }
    }
    return answer;
  }
}
