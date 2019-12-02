package com.wlwallis30.leetcode;

import java.util.*;

public class LongSubStrNoRepeatChar {
	public int lengthOfLongestSubStr(String inputStr) {
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
}
