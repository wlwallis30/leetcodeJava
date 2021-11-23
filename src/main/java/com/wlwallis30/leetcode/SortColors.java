package com.wlwallis30.leetcode;

public class SortColors {
  // using extra array and two passes
  void sortColors75(int[] nums) {
    int[] colors = new int[3];
    for (int num : nums) ++colors[num];
    for (int oneColor = 0, cur = 0; oneColor < 3; ++oneColor) {
      for (int colorCnt = 0; colorCnt < colors[oneColor]; ++colorCnt) {
        nums[cur++] = oneColor;
      }
    }
  }

  void sortColors75Onepass(int[] nums) {
    int red = 0, blue = nums.length - 1, cur = 0;
    // need to be cur <= blue, think about [1 1 1 1 0 2 2] case, red is very low idx, so you need compare cur and blue to end while loop
    // < not enough, also need =, to garuantee cur and blue both point to a 0, e.g. [1 2 0] -> [1 0 2]
    while (cur <= blue) {
      if (nums[cur] == 0) Solution.swap(nums, cur++, red++);
      else if (nums[cur] == 2) Solution.swap(nums, cur, blue--);
      else ++cur;
      //at first, cur and red = 0, so wont encounter swap two 0s at different idx
      //but possible to swap two 2s at different idx, so need to hold cur stay same
      // e.g. [0 2 1 2], cur==1, after swap,still [0 2 1 2], need to hold cur to avoid jump.
      //also possible to swap 0 and 2, then cur hold 0, still need to hold cur stay to swap possible 1 and 0(red hold a 1). e.g. [1 2 0]
    }
  }
}
