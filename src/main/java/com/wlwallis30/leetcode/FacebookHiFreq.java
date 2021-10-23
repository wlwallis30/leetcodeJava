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
}
