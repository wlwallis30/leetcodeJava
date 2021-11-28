package com.wlwallis30.leetcode;

import com.sun.javafx.image.IntPixelGetter;
import java.util.*;

public class LongSubStrNoRepeatChar {
	// sliding window + hashmap, looking for no repeated substring
	public int lengthOfLongestSubStr_3(String inputStr) {
		int[] charPosMap = new int[256];
		Arrays.fill(charPosMap, 0);
		int res = 0, left = 0;
		// left: the new story begin where left, charPosMap record the previous char's position + 1( plus 1 means, the position if you meet the repeated char)
		// so everytime, need to find the max of left and current char's prev position+1 as the starting point to calculate length
		for(int right = 0; right < inputStr.length(); right++) {
			left = Math.max(left, charPosMap[inputStr.charAt(right)]);
			charPosMap[inputStr.charAt(right)] = right+1;
			res = Math.max(res, right-left+1);
		}
		
		return res;
	}

	// sliding window + hashmap, looking for substring with possible repeated letters
	public int longestSubstrWithAtMost2DistinctChar_159(String str) {
		int res = 0, left = 0;
		Map<Character, Integer> charPosMap = new HashMap<>();
		for(int right = 0; right < str.length(); right++) {
			charPosMap.put(str.charAt(right), right);
			while(charPosMap.size() > 2) {
				if(charPosMap.get(str.charAt(left)) == left) charPosMap.remove(str.charAt(left));
				++left;
			}
			res = Math.max(res, right-left+1);
		}
		
		return res;
	}

	// sliding window + hashmap, looking for substring with possible repeated letters
	public int longestSubstrWithAtMostKDistinctChar_340(String str, int K) {
		int res = 0, left = 0;
		Map<Character, Integer> charPosMap = new HashMap<>();
		for(int right = 0; right < str.length(); right++) {
			//record current char's position
			charPosMap.put(str.charAt(right), right);
			while(charPosMap.size() > K) {
				// eceba: when left=0,right=3, letter e'c stored idx is 2, not 0.
				// when left=1, letter c's stored idx is same with left, need to remove to meet K.
				if(charPosMap.get(str.charAt(left)) == left) charPosMap.remove(str.charAt(left));
				++left;
			}
			res = Math.max(res, right-left+1);
		}
		
		return res;
	}
	
	// hard, sliding window + dequeue
	/*
	Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
	Output: [3,3,5,5,6,7]
	Window position                Max
	---------------               -----
	 0   1   2   3  4  5  6  7 idx
	[1  3  -1] -3  5  3  6  7       3
	 1 [3  -1  -3] 5  3  6  7       3
	 1  3 [-1  -3  5] 3  6  7       5
	 1  3  -1 [-3  5  3] 6  7       5
	 1  3  -1  -3 [5  3  6] 7       6
	 1  3  -1  -3  5 [3  6  7]      7
	 */
	public int[] slidingWindowMax_239(int[] nums, int k) {
		int size = nums.length;
        if(size * k == 0) return new int[0];
		int[] res = new int[size-k+1];
		ArrayDeque<Integer> idxDeQ = new ArrayDeque<>();
		
		for(int i = 0; i < size; i++) {
			if(!idxDeQ.isEmpty() && idxDeQ.getFirst() == i-k) idxDeQ.removeFirst();
			while(!idxDeQ.isEmpty() && nums[i] > nums[idxDeQ.getLast()]) idxDeQ.removeLast();
			idxDeQ.addLast(i);
			if(i >= k-1) res[i-k+1] = nums[idxDeQ.getFirst()];
		}
		
		return res;
	}

	//hashset
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

	// dfs
	int longestConsecutive298(TreeNode root) {
		if(root == null) return 0;
		return helper(root, null, 1);
	}
	int helper(TreeNode cur, TreeNode curParent, int maxLen)
	{
		if(cur == null) return maxLen;
		// curParent != null, just for the root node since you pass a null! if not consecutive from curParent to cur, recount from maxlen=1;
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

	// mini dp/greedy: keep updating the count
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

	// k=1 for problem 487, sliding window, this method is not straightforward and intuitive!
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

	// more intuitive, sliding window
	public int longestOnes1004And487Better(int[] nums, int K) {
		int left = 0, max = 0, zeros = 0;
		if(nums == null || nums.length == 0){ return 0; }

		for(int right = 0 ; right < nums.length ; right++){
			// Increase count of zero whenever you see one
			if(nums[right]==0){ zeros++; }

			// Shrink the window until zeros are equal less than required k
			while(left <= right && zeros > K){
				if(nums[left] == 0){ zeros--; }
				left++;
			}

			// At every step record the length of the window
			max = Math.max(max, right - left +1);
		}

		return max;
	}

	//sliding window + hashmap
	public int characterReplacement424(String s, int k) {
		Map<Character, Integer> map = new HashMap<>();
		int left = 0, maxRepeat = 0, maxWindow = 0;

		for(int right = 0; right < s.length(); right++) {
			char ch = s.charAt(right);
			map.putIfAbsent(ch,0);
			map.put(ch, map.get(ch) + 1);

			// IMPORTANT: maxRepeat is not the accurate number of dominant character which might change, It is the historical maximum count
			//maxCnt 相当于卡了一个窗口大小，我们并不希望窗口变小，虽然窗口在滑动，但是之前是出现过跟窗口大小相同的符合题意的子串，缩小窗口没有意义，并不会使结果 res 变大，所以我们才不更新 maxCnt
			maxRepeat = Math.max(maxRepeat, map.get(ch));

			//不更新 maxCnt
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
	// sliding window + binary search
	public List<Integer> findClosestElements658Slide(int[] arr, int k, int x) {
		List<Integer> result = new ArrayList<Integer>();

		// Binary search to find the closest element
		int left = 0;
		int right = arr.length-1;
		//int right = arr.length; also works
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (arr[mid] < x) { left = mid + 1; }
			else { right = mid; }
		}

		// Initialize our sliding window's bounds, we are targeting [left+1, right-1] as the first idx when we expend the window
		// reason of using left+1, right-1, because closest one might already has idx 0 or length-1
		//[1,2,3,4,5] k=4 x=-1, then left=0, when you expand a bit by left = left-1, left then < 0, bad. so we target [left+1, right-1]
		left -= 1;
		right = left + 1; // right point to the original left, not the above

		// While the window size is less than k, right-1 -(left+1) +1 < k
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

	//hard, also using sliding window + hashmap, min window of substring for target string
	public String minWindow76(String s, String t) {
		String res = "";
		if (s.length() == 0 || t.length() == 0) { return res;}

		Map<Character, Integer> targetCharCnt = new HashMap<>();
		for (int i = 0; i < t.length(); i++) {
			char curChar = t.charAt(i);
			int count = targetCharCnt.getOrDefault(curChar, 0);
			targetCharCnt.put(curChar, count + 1);
		} // after this targetCharCnt will never change

		int required = targetCharCnt.size(); // size is the map's size, e.g. a:2, b:1, the size is 2
		int goalCnt = 0;
		int minLen = s.length()+1;

		Map<Character, Integer> windowCharCnt = new HashMap<>();
		int left = 0;
		for(int right=0; right<s.length(); ++right) {
			char curChar = s.charAt(right);
			int count = windowCharCnt.getOrDefault(curChar, 0);
			windowCharCnt.put(curChar, count + 1);

			// found a desired char and this char's count matches the target char's count
			if (targetCharCnt.containsKey(curChar)
					&& windowCharCnt.get(curChar).equals(targetCharCnt.get(curChar))) { goalCnt++; }

			// Try shrinking window till the point where it ceases to be 'desirable'.
			while (left <= right && goalCnt == required) {
				curChar = s.charAt(left);
				if(right-left+1<minLen) {
					minLen = right-left+1;
					res = s.substring(left, right+1);
				}
				// removing this char @left if it is in the targetChar map
				windowCharCnt.put(curChar, windowCharCnt.get(curChar) - 1);
				if (targetCharCnt.containsKey(curChar)
						&& windowCharCnt.get(curChar) < targetCharCnt.get(curChar)) { goalCnt--; }

				// shrinking window
				left++;
			}
		}

		return res;
	}
}
