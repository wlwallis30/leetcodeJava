package com.wlwallis30.leetcode;

import java.util.*;

public class PascalTriangle {
  public List<List<Integer>> picalsalTriangle_118(int numRows) {
    List<List<Integer>> res = new ArrayList<>();

    // Base case; first curRow is always [1].
    res.add(new ArrayList<>());
    res.get(0).add(1);

    List<Integer> prevRow;
    for (int rowNum = 1; rowNum < numRows; rowNum++) {
      List<Integer> curRow = new ArrayList<>();
      prevRow = res.get(rowNum-1);

      // The first row element is always 1.
      curRow.add(1);

      for (int j = 1; j < rowNum; j++) {
        curRow.add(prevRow.get(j-1) + prevRow.get(j));
      }

      // The last curRow element is always 1.
      curRow.add(1);

      res.add(curRow);
    }

    return res;
  }

}
