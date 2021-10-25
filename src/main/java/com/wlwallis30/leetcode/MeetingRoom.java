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

  // heap, better use heap, O(nlogn), space O(n)
  public int minMeetingRooms253(int[][] intervals) {
    if (intervals.length == 0) { return 0; }

    // Min heap to store the end time
    PriorityQueue<Integer> allocator = new PriorityQueue<Integer>((a, b) -> a - b);
    // Sort the intervals by start time
    Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]);
    // Add the first meeting
    allocator.add(intervals[0][1]);

    for (int i = 1; i < intervals.length; i++) {

      // If the room due to free up the earliest is free, assign that room to this meeting.
      if (intervals[i][0] >= allocator.peek()) { allocator.poll(); }

      // If a new room is to be assigned, then also we add to the heap, or  an old room is allocated we just polled
      allocator.add(intervals[i][1]);
    }

    return allocator.size();
  }

  //Chronological Ordering
  public int minMeetingRooms253TimeOrder(int[][] intervals) {
    if (intervals.length == 0) { return 0; }
    Integer[] start = new Integer[intervals.length];
    Integer[] end = new Integer[intervals.length];

    for (int i = 0; i < intervals.length; i++) {
      start[i] = intervals[i][0];
      end[i] = intervals[i][1];
    }

    Arrays.sort(end, (a, b) -> a -b);
    Arrays.sort(start, (a, b) -> a -b);
    int startPos = 0, endPos = 0, usedRooms = 0;

    while (startPos < intervals.length) {
      // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
      //if (start[startPos] >= end[endPos]) { usedRooms-- ; endPos++; }

      // We do this irrespective of whether a room frees up or not.
      // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
      // remain the same in that case. If no room was free, then this would increase used_rooms
      //usedRooms++;
      //startPos++;
      // easier to understand, when there is a need to open the room, else means, reuse some old room
      if (start[startPos] < end[endPos]) { usedRooms++; startPos++; }
      else { startPos++; endPos++;} // when >=, you can reuse
    }

    return usedRooms;
  }

  public String addBoldTag616(String s, String[] dict) {
    StringBuilder ans = new StringBuilder();
    int i = 0;
    while (i < s.length()) {
      //end is the index where previous match  at end-1
      int end = findLongestOverlapped(s, i, dict);
      if (i == end) {
        ans.append(s.charAt(i++));
      } else {
        ans.append("<b>").append(s.substring(i, end)).append("</b>");
        i = end;
      }
    }
    return ans.toString();
  }

  private int findLongestOverlapped(String s, int start, String[] dict) {
    int probe = start;
    for (int i = start; i <= probe; i++) {//probe will change if matched in dict
      for (String word : dict) {
        if (s.startsWith(word, i)) { probe = Math.max(probe, i + word.length()); }
      }
    }
    return probe;
  }
}
