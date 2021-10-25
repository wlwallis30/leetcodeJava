package com.wlwallis30.leetcode;

import java.util.*;

public class MeetingRoom {

  public int[][] merge56(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    LinkedList<int[]> merged = new LinkedList<>();
    for (int[] interval : intervals) {
      if (merged.isEmpty() || merged.getLast()[1] < interval[0]) { merged.add(interval); }
      // otherwise, there is overlap.
      else { merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]); }
    }
    return merged.toArray(new int[merged.size()][]);
  }

  public int[][] insertInterval57(int[][] intervals, int[] newInterval) {
    int newStart = newInterval[0], newEnd = newInterval[1];
    int idx = 0, len = intervals.length;
    LinkedList<int[]> output = new LinkedList<>();

    // add all intervals starting before newInterval
    while (idx < len && newStart > intervals[idx][0])
      output.add(intervals[idx++]);

    // add newInterval, if there is no overlap, just add the interval
    if (output.isEmpty() || output.getLast()[1] < newStart) output.add(newInterval);
      // if there is an overlap, merge with the last interval
    else { output.getLast()[1] = Math.max(output.getLast()[1], newEnd); }

    // add next intervals, merge with newInterval if needed
    while (idx < len) {
      int start = intervals[idx][0], end = intervals[idx][1];
      if (output.getLast()[1] < start) output.add(intervals[idx]);
      else { output.getLast()[1] = Math.max(output.getLast()[1], end); }
      idx++;
    }
    return output.toArray(new int[output.size()][2]);
  }

  public boolean canAttendMeetings252(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    for (int i = 0; i < intervals.length - 1; i++) {
      if (intervals[i][1] > intervals[i + 1][0]) {
        return false;
      }
    }
    return true;
  }

  public boolean canAttendMeetings252Brute(int[][] intervals) {
    for (int i = 0; i < intervals.length-1; i++) {
      for (int j = i + 1; j < intervals.length; j++) {
        if (overlap(intervals[i], intervals[j])) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean overlap(int[] interval1, int[] interval2) {
    return (interval1[0] >= interval2[0] && interval1[0] < interval2[1])
        || (interval2[0] >= interval1[0] && interval2[0] < interval1[1]);
  }
}
