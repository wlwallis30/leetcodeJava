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

  // triangle, find the minimum sum path, return the sum
  public int minimumTotal120BottomUp(List<List<Integer>> triangle) {
    // assigning the last row
    List<Integer> bottomUpDp = triangle.get(triangle.size()-1);
    //List<Integer> bottomUpDp = new ArrayList(triangle.get(triangle.size()-1));
    int n = triangle.size();
    for(int i=n-2; i>=0;--i) {
      for(int j=0; j<=i;++j) {
        int tmp = Math.min(bottomUpDp.get(j), bottomUpDp.get(j+1)) + triangle.get(i).get(j);
        bottomUpDp.set(j, tmp);
      }
    }

    /*  actually, the first line only created List ref to the trianglei's last row
        so basically this solution is inplace space, O(1)
        if you want to have a new, then new ArrayList(someOldArrayList)
     triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
    for(List<Integer> eachRow: triangle) System.out.println(eachRow); will give:
      [2]
      [3, 4]
      [6, 5, 7]
      [11, 10, 10, 3], yes, you are changing the last row due to the ref.
     */
    return bottomUpDp.get(0);
  }

  //space O(1)
  public int minimumTotalTopDown(List<List<Integer>> triangle) {
    for (int row = 1; row < triangle.size(); row++) {
      for (int col = 0; col <= row; col++) {
        int smallestAbove = Integer.MAX_VALUE;
        if (col > 0) {
          //exclude col=0, also apply for col=row, the last element
          smallestAbove = triangle.get(row - 1).get(col - 1);
        }
        // it is not else if, coz we are taking two possible pathSum from above row
        if (col < row) {
          //exclue col=row, this also applys for when col==0,
          smallestAbove = Math.min(smallestAbove, triangle.get(row - 1).get(col));
        }
        int pathSum = smallestAbove + triangle.get(row).get(col);
        triangle.get(row).set(col, pathSum);
      }
    }
    return Collections.min(triangle.get(triangle.size() - 1));
  }
}
