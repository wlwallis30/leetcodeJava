package com.wlwallis30.leetcode;

public class FindMedianIn2SortedArray {

  //this is a better solution: https://zxi.mytechroad.com/blog/algorithms/binary-search/leetcode-4-median-of-two-sorted-arrays/
  // binary search: search for the middle two or one element for the merged array
  public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
    int n1 = nums1.length;
    int n2 = nums2.length;
    if (n1 > n2) return findMedianSortedArrays4(nums2, nums1);

    int k = (n1 + n2 + 1) / 2;
    int l = 0;
    int r = n1;

    // m1 + m2 = k, k is half length, idx k-1 and k will be the median for even case of n1+n2, idx k-1 will be median for odd case
    // m1 is the len of the nums1's elements to be used, m2 is the len of nums2's elements to be used
    //median must be from nums1[m1-1], nums1[m1], nums2[m2-1], nums2[m2]
    while (l < r) {
      int m1 = l + (r - l) / 2; //m1 as the mid
      int m2 = k - m1;
      if (nums1[m1] < nums2[m2 - 1])
        l = m1 + 1;
      else
        r = m1;
    }
    int m1 = l;
    int m2 = k - l;
    // c[k-1] must be max of nums1[m1-1], nums2[m2-1]
    int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
        m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);

    if ((n1 + n2) % 2 == 1)
      return c1;

    // c[k] must be min of nums1[m1], nums2[m2]
    int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
        m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);

    return (c1 + c2) * 0.5;
  }

  //not recommended
  public double findMedianSortedArrays_4(int[] nums1, int[] nums2) {
    int m = nums1.length, n=nums2.length;
    int total = (m+n);
    if(total%2==1) return findKth(nums1, 0, nums2, 0, total/2+1);
    else return (findKth(nums1, 0, nums2, 0, total/2)+findKth(nums1, 0, nums2, 0, total/2+1))/2.0;
  }

  int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
    if (i >= nums1.length) return nums2[j + k - 1];
    if (j >= nums2.length) return nums1[i + k - 1];
    if (k == 1) return Math.min(nums1[i], nums2[j]);
    int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
    int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
    if (midVal1 < midVal2) {
      return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
    } else {
      return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
    }
  }
}
