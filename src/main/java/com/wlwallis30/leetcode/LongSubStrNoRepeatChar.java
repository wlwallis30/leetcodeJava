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
}
