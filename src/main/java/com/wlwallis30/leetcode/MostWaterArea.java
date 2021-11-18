package com.wlwallis30.leetcode;

public class MostWaterArea {
  public int maxArea_11(int[] height) {
    int left = 0, right = height.length-1;
    int res = 0;
    int minHeight = Integer.MAX_VALUE;
    while(left < right) {
      minHeight = Math.min(height[left], height[right]);
      res = Math.max(res, (right - left)*minHeight);
      if(minHeight == height[left]) --right;
      else ++left;
    }
    return res;
  }

  //brute force:
  /* at each index, you need to find the minimum of max height from the left and max from the right
  O(n^2)
  Iterate the array from left to right:
          Initialize left_max=0 and right_max=0
          Iterate from the current element to the beginning of array updating:
              left_max=max(left_max,height[j])
          Iterate from the current element to the end of array updating:
              right_max=max(right_max,height[j])
          Add min(left_max,right_max)−height[i] to ans
   */
  public int trap42dp(int[] height) {
    int res = 0, mx = 0, n = height.length;
    int[] leftOrRightMax = new int[n];
    for (int i = 0; i < n; ++i) {
      leftOrRightMax[i] = mx;
      mx = Math.max(mx, height[i]);
    }
    mx = 0;
    for (int i = n - 1; i >= 0; --i) {
      //select the minimum of the height of left side and right side for this index, that is the highest water level for this idx
      leftOrRightMax[i] = Math.min(leftOrRightMax[i], mx);
      mx = Math.max(mx, height[i]);
      if (leftOrRightMax[i] - height[i] > 0) res += leftOrRightMax[i] - height[i];
    }
    return res;
  }
  //the water trapped would be dependant on height of bar in current direction (from left to right).
  // As soon as we find the bar at other end (right) is smaller, we start iterating in opposite direction (from right to left)
  // three pair comparison to consider, left and right height to decide which side, then on left or right side, compare its max
  int trap42twoPointers(int[] height){
    int left = 0, right = height.length - 1;
    int ans = 0;
    int left_max = 0, right_max = 0;
    while (left < right) {
      if (height[left] < height[right]) {
        if(height[left] >= left_max) left_max = height[left];
        else ans += (left_max - height[left]);
        ++left;
      }//moving to opposite direction since smaller of max is on the right
      else {
        if(height[right] >= right_max) right_max = height[right];
        else ans += (right_max - height[right]);// always positive since rightMax > height[right]
        --right;
      }
    }
    return ans;
  }
}