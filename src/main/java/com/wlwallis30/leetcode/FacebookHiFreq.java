package com.wlwallis30.leetcode;

import java.util.*;

public class FacebookHiFreq {
  public int[] exclusiveTime636(int n, List<String> logs) {
    int[] result = new int[n];
    if (n == 0 || logs == null || logs.size() == 0) {
      return result;
    }
    // This stack will store the function ids
    Deque<Integer> idStack = new ArrayDeque<>();
    int preTime = 0;
    for(String log: logs) {
      String[] splitInfo = log.split(":");
      int curTime = Integer.parseInt(splitInfo[2]);
      int curId = Integer.parseInt(splitInfo[0]);
      String action = splitInfo[1];
      if(action.equals("start")) {
        if(!idStack.isEmpty()) {
          // calc the previous call when hitting another call. but do not pop since not the previous end
          // Function is starting now
          int preId = idStack.peek();
          result[preId] += curTime - preTime;
        }
        idStack.push(curId);
        // Setting the start time for next log.
        preTime = curTime;
      } else {
        // result[stack.pop()] += curTime - prevTime + 1; same thing
        // Function is ending now.
        // Make sure to +1 to as end takes the whole unit of time.
        result[curId] += curTime-preTime+1;
        idStack.pop();
        // prevTime = resume time of the function. Thus adding 1.
        preTime = curTime + 1;
      }
    }

    return result;
  }

  // java's char and int are so hard to use for conversion
  int maximumSwap670(int num) {
    char[] res = Integer.toString(num).toCharArray();
    char[] back = Integer.toString(num).toCharArray(); // can not assign res to back, it will just copy the array ref to back
    for (int i = back.length - 2; i >= 0; --i) {
      int digit = Character.getNumericValue(back[i]);
      int digit1 = Character.getNumericValue(back[i+1]);
      // char c=Character.forDigit(a,REDIX=10); also good
      back[i] = (char)(Math.max(digit, digit1) + '0');
    }
    for (int i = 0; i < res.length; ++i) {
      if (res[i] == back[i]) continue;
      for (int j = res.length - 1; j > i; --j) {
        //scan from the left for back array and try to find corresponding max element which matches.
        if (res[j] == back[i]) {
          Solution.swap(res, i, j);
          // res.toString will just give the string of res object ref, fuxxxxxxxxxxxxxxxxk
          return Integer.parseInt(String.valueOf(res));
        }
      }
    }
    //System.out.println(back);
    return Integer.parseInt(String.valueOf(res));
  }
}