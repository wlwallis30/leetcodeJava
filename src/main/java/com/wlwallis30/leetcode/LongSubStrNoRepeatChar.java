package com.wlwallis30.leetcode;

import java.util.*;

public class LongSubStrNoRepeatChar {
	public int lengthOfLongestSubStr_3(String inputStr) {
		int[] charPosMap = new int[256];
		Arrays.fill(charPosMap, -1);
		int res = 0, left = -1;
		for(int i = 0; i < inputStr.length(); i++) {
			left = Math.max(left, charPosMap[inputStr.charAt(i)]);
			charPosMap[inputStr.charAt(i)] = i;
			res = Math.max(res, i - left);
		}
		
		return res;
	}
	
	public int longestSubstrWithAtMost2DistinctChar_159(String str) {
		int res = 0, left = 0;
		Map<Character, Integer> charPosMap = new HashMap<Character, Integer>();
		for(int i = 0; i < str.length(); i++) {
			charPosMap.put(str.charAt(i), i);
			while(charPosMap.size() > 2) {
				if(charPosMap.get(str.charAt(left)) == left) charPosMap.remove(str.charAt(left));
				++left;
			}
			res = Math.max(res, i-left+1);
		}
		
		return res;
	}
	
	public int longestSubstrWithAtMostKDistinctChar_340(String str, int K) {
		int res = 0, left = 0;
		Map<Character, Integer> charPosMap = new HashMap<Character, Integer>();
		for(int i = 0; i < str.length(); i++) {
			charPosMap.put(str.charAt(i), i);
			while(charPosMap.size() > K) {
				if(charPosMap.get(str.charAt(left)) == left) charPosMap.remove(str.charAt(left));
				++left;
			}
			res = Math.max(res, i-left+1);
		}
		
		return res;
	}
	
	// hard
	public int[] slidingWindowMax_239(int[] nums, int k) {
		int size = nums.length;
        if(size * k == 0) return new int[0];
		int[] res = new int[size-k+1];
		ArrayDeque<Integer> idxDeQ = new ArrayDeque<Integer>();
		
		for(int i = 0; i < size; i++) {
			if(!idxDeQ.isEmpty() && idxDeQ.getFirst() == i-k) idxDeQ.removeFirst();
			while(!idxDeQ.isEmpty() && nums[i] > nums[idxDeQ.getLast()]) idxDeQ.removeLast();
			idxDeQ.addLast(i);
			if(i >= k-1) res[i-k+1] = nums[idxDeQ.getFirst()];
		}
		
		return res;
	}

	public int longestConsecutive128(int[] nums) {
		Set<Integer> num_set = new HashSet<>();
		for (int num : nums) { num_set.add(num); }
		int globalMaxCnt = 0;

		for (int num : num_set) {
		  // 2, 3, 4, 5, it is smart we skip 3, 4, 5, and start with 2 and count
			if (!num_set.contains(num-1)) {
				int currentNum = num;
				int curCnt = 1;
				while (num_set.contains(++currentNum)) { curCnt += 1; }
				globalMaxCnt = Math.max(globalMaxCnt, curCnt);
			}
		}

		return globalMaxCnt;
	}

	int longestConsecutive298(TreeNode root) {
		if(root == null) return 0;
		return helper(root, null, 1);
	}
	int helper(TreeNode cur, TreeNode curParent, int maxLen)
	{
		if(cur == null) return maxLen;
		// curParent != null, just for the root node since you pass a null!
		maxLen = (curParent != null && cur.val == curParent.val+1) ? maxLen+1:1;
		return Math.max(maxLen, Math.max(helper(cur.left,cur, maxLen),helper(cur.right,cur, maxLen)));
	}
	/*
	void helper(TreeNode* root, int len, int& res) {
        res = max(res, len);
        if(root->left) {
            if(root->left->val == root->val+1) helper(root->left, len+1, res);
            else helper(root->left, 1, res);
        }
        if(root->right) {
            if(root->right->val == root->val+1) helper(root->right, len+1, res);
            else helper(root->right, 1, res);
        }
    }
	 */

	public int findMaxConsecutiveOnes485(int[] nums) {
		int count = 0;
		int maxCount = 0;
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == 1) {
				// Increment the count of 1's by one.
				count += 1;
			} else {
				// Find the maximum till now.
				maxCount = Math.max(maxCount, count);
				// Reset count of 1.
				count = 0;
			}
		}
		return Math.max(maxCount, count);
	}

	// k=1 for problem 487
	public int longestOnes1004And487(int[] nums, int k) {
		int left = 0, right;
		for (right = 0; right < nums.length; right++) {
			// If we included a zero in the window we reduce the value of k.
			// Since k is the maximum zeros allowed in a window.
			if (nums[right] == 0) {
				k--;
			}
			// A negative k denotes we have consumed all allowed flips and window has
			// more than allowed zeros, thus increment left pointer by 1 to keep the window size same.
			if (k < 0) {
				// If the left element to be thrown out is zero we increase k.
				k += 1 - nums[left];
				left++;
			}
		}
		return right - left;
	}

	public int characterReplacement424(String s, int k) {
		Map<Character, Integer> map = new HashMap<>();
		int left = 0, maxRepeat = 0, maxWindow = 0;

		for(int right = 0; right < s.length(); right++) {
			char ch = s.charAt(right);
			map.putIfAbsent(ch,0);
			map.put(ch, map.get(ch) + 1);

			// IMPORTANT: maxRepeat is not the accurate number of dominant character which might change, It is the historical maximum count
			maxRepeat = Math.max(maxRepeat, map.get(ch));

			if(right - left + 1 - maxRepeat > k) {
				char remove = s.charAt(left);
				map.put(remove, map.get(remove) - 1);
				left++;
			}

			maxWindow = Math.max(maxWindow, right - left + 1);
		}

		return maxWindow;
	}

	public List<Integer> findClosestElements658Comparator(int[] arr, int k, int x) {
		List<Integer> sortedArr = new ArrayList<Integer>();
		for (int num: arr) { sortedArr.add(num); }
		// or List<Integer> intList = Arrays.stream(weights).boxed().collect(Collectors.toList())

		Collections.sort(sortedArr, (num1, num2) -> Math.abs(num1 - x) - Math.abs(num2 - x));

		// Only take k elements
		sortedArr = sortedArr.subList(0, k);

		// the order is based on the abs(num-x), not the num, Sort again to have output in ascending order.
		Collections.sort(sortedArr);
		return sortedArr;
	}

	// this is easy to think of it and not hard to figure out conditions
	public List<Integer> findClosestElements658Slide(int[] arr, int k, int x) {
		List<Integer> result = new ArrayList<Integer>();

		// Binary search to find the closest element
		int left = 0;
		int right = arr.length;
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (arr[mid] < x) { left = mid + 1; }
			else { right = mid; }
		}

		// Initialize our sliding window's bounds, we are targeting [left+1, right-1] as the first idx when we expend the window
		left -= 1;
		right = left + 1; // right point to the original left, not the above

		// While the window size is less than k
		while (right - (left+1) < k) {
			// Be careful to not go out of bounds
			if (left == -1) { right++; continue; }
			if(right == arr.length) { left--; continue;}

			// Expand the window towards the side with the closer number
			if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
				left--;
			} else {
				right++;
			}
		}

		// Build and return the window
		for (int i = left + 1; i < right; i++) { result.add(arr[i]);}
		return result;
	}

	// I do NOT recommend this algo, hard to think of it and hard to come out with the right condition for moving boundary
	//what is the biggest index the left bound could be? If there needs to be k elements, then the left bound's upper limit is arr.length - k
	// finding the left bound for binary search.
	public List<Integer> findClosestElements658BinarySearch(int[] arr, int k, int x) {
		// Initialize binary search bounds
		int left = 0;
		int right = arr.length - k;

		// Binary search against the criteria described
		while (left < right) {
			int mid = (left + right) / 2;
			// mid, and some index mid + k. The relationship between these indices is significant because only one of them could possibly be in a final answer.
			//Math.abs(x - arr[mid]) > Math.abs(arr[mid + k] - x) will fail [1,1,2,2,2,2,2,3,3] k=3 x=3, even after 65/66 test cases passed.
			//consider we look for left bound and arr is ascending order with special case of above, with abs is more accurate.
			// you can play with x with: x is smaller then arr[mid], bigger than arr[mid+k] and in between,
			// first case, but should  move further left (right=mid), 2nd case we should move further right, 3rd case, without using abs, we can judge by numX-numY itself
			// you can use above raw condition to form the logic, but after merging them you have the following.
			if (x - arr[mid] > arr[mid + k] - x) {
				left = mid + 1;
			} else {// arr[mid + k] -  arr[mid] >= 2x, range is too big, we should move further left to find a better left
				right = mid;
			}
		}

		// Create output in correct format
		List<Integer> result = new ArrayList<Integer>();
		for (int i = left; i < left + k; i++) {
			result.add(arr[i]);
		}

		return result;
	}
}
