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

      for (int col = 1; col < rowNum; col++) {
        curRow.add(prevRow.get(col-1) + prevRow.get(col));
      }

      // The last curRow element is always 1.
      curRow.add(1);

      res.add(curRow);
    }

    return res;
  }

  // dp solution. big o is n^2
  public List<Integer> getRow_119(int rowIndex) {
    List<Integer> curr, prev = new ArrayList<>();
    prev.add(1);

    for (int row = 1; row <= rowIndex; row++) {
      curr = new ArrayList<>(row + 1);
      curr.add(1);

      for (int col = 1; col < row; col++) {
        curr.add(prev.get(col - 1) + prev.get(col));
      }
      curr.add(1);
      prev = curr;
    }

    return prev;
  }
}
