package com.wlwallis30.leetcode;

import java.util.*;

//1570
public class SparseVector {
  // Map the index to value for all non-zero values in the vector
  Map<Integer, Integer> idxValMap;

  SparseVector(int[] nums) {
    idxValMap = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] != 0) { idxValMap.put(i, nums[i]); }
    }
  }

  public int dotProduct(SparseVector vec) {
    int result = 0;

    // iterate through each non-zero element in this sparse vector
    // update the dot product if the corresponding index has a non-zero value in the other vector
    for (Integer i : this.idxValMap.keySet()) {
      if (vec.idxValMap.containsKey(i)) {
        result += this.idxValMap.get(i) * vec.idxValMap.get(i);
      }
    }
    return result;
  }
}

class SparseMtrixMultiply {
  public int[][] multiply(int[][] mat1, int[][] mat2) {
    int n = mat1.length;
    int k = mat1[0].length;
    int m = mat2[0].length;

    // Product matrix will have 'n x m' size.
    int[][] ans = new int[n][m];

    for (int rowIndex = 0; rowIndex < n; ++rowIndex) {
      for (int elementIndex = 0; elementIndex < k; ++elementIndex) {
        // If current element of mat1 is non-zero then iterate over all columns of mat2.
        if (mat1[rowIndex][elementIndex] != 0)  {
          for (int colIndex = 0; colIndex < m; ++colIndex) {
            if(mat2[elementIndex][colIndex] != 0)
              ans[rowIndex][colIndex] += mat1[rowIndex][elementIndex] * mat2[elementIndex][colIndex];
          }
        }
      }
    }

    return ans;
  }
}
