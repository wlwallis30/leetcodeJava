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
}
