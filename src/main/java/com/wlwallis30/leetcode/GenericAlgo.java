package com.wlwallis30.leetcode;

public class GenericAlgo {
  public void mergeSort(int[] nums, int left, int right) {
    if(left < right) {
      int mid = (left + right) / 2;
      mergeSort(nums, left, mid);
      mergeSort(nums, mid+1, right);
      mergeHelper(nums, left, mid, right);
    }
  }

  public void mergeHelper(int[] nums, int left, int mid, int right) {
    int[] tmp = new int[right-left+1];
    int leftMost = left, tmpPos = left, leftAfterMid = mid+1;
    while(left <= mid && leftAfterMid <= right) {
      if(nums[left] < nums[leftAfterMid]){
        tmp[tmpPos] = nums[left];
        ++tmpPos;
        ++left;
      }
      else {
        tmp[tmpPos] = nums[leftAfterMid];
        ++tmpPos;
        ++leftAfterMid;
      }
      // handle the leftover
     while(left <= mid) {
       tmp[tmpPos] = nums[left];
       ++left;
       ++tmpPos;
     }
     while(leftAfterMid <= right) {
       tmp[tmpPos] = nums[leftAfterMid];
       ++leftAfterMid;
       ++tmpPos;
     }
     for(int i = leftMost; i < right; ++i) {
        nums[i] = tmp[i];
     }
    }
  }

  void QuickSort(int[] nums, int left, int right) {
    if (nums.length == 0 || left > right) return;
    int i = left, j = right;
    int pivotValue = nums[(left + right) / 2];
    while (i <= j) {
      while (nums[i] < pivotValue) ++i;
      while (nums[j] > pivotValue) --j;
      if (i <= j) {
        Solution.swap(nums, i, j);
        ++i;
        --j;
      }
    }
    // i > j now!!!
    if (left < j) QuickSort(nums, left, j);
    if (i < right) QuickSort(nums, i, right);
  }
}

/*
	static void HeapSort(vector<int>& nums)
	{
		int heapBoundary = nums.size() - 1;

		// build max Heap Binary
		for (int i = heapBoundary / 2 - 1; i >= 0; --i) Heapify(nums, i, heapBoundary);

		// One by one extract an element from heap
		for (int i = heapBoundary; i >= 0; --i)
		{
			// Move current root to end
			swap(nums[i], nums[0]);
			// call max heapify on the reduced heap
			Heapify(nums, 0, i);
		}
	}
private:
	static void Heapify(vector<int>& nums, int root, int heapBoundary)
	{
		int maxIdx = root;
		int left = root * 2 + 1;
		int right = root * 2 + 2;

		if (left<heapBoundary && nums[left]>nums[maxIdx]) maxIdx = left;
		if (right<heapBoundary && nums[right] > nums[maxIdx]) maxIdx = right;

		if (maxIdx != root)
		{
			swap(nums[maxIdx], nums[root]);
			Heapify(nums, maxIdx, heapBoundary);
		}

	}
 */