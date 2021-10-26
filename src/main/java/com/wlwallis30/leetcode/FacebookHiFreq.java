package com.wlwallis30.leetcode;

import java.util.*;
import java.util.stream.Collectors;

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

  public String customSortString791(String order, String T) {
    // This is offset so that count[0] = occurrences of 'a', etc.
    int[] count = new int[26];
    for (char c: T.toCharArray()) count[c - 'a']++;
    StringBuilder ans = new StringBuilder();

    // Write all characters that occur in order, in the order of order.
    for (char c: order.toCharArray()) {
      for (int i = 0; i < count[c - 'a']; ++i) ans.append(c);
      // Setting count[char] to zero to denote, no need to write 'char' into our answer anymore.
      count[c - 'a'] = 0;
    }

    // Write all remaining characters that don't occur in order.
    // That information is specified by 'count'.
    for (char c = 'a'; c <= 'z'; ++c)
      for (int i = 0; i < count[c - 'a']; ++i) ans.append(c);

    return ans.toString();
  }

  public boolean isToeplitzMatrix766(int[][] matrix) {
    for (int r = 0; r < matrix.length; ++r)
      for (int c = 0; c < matrix[0].length; ++c)
        if (r > 0 && c > 0 && matrix[r-1][c-1] != matrix[r][c])
          return false;
    return true;
  }

  public int[] findDiagonalOrder498(int[][] matrix) {
    if (matrix == null || matrix.length == 0) { return new int[0]; }
    int N = matrix.length;
    int M = matrix[0].length;

    int row = 0, column = 0;
    // 1: up, 0: down
    int direction = 1;

    int[] result = new int[N*M];
    int r = 0;

    while (row < N && column < M) {
      result[r++] = matrix[row][column];

      // [i, j] -> [i - 1, j + 1] if going up and [i, j] -> [i + 1][j - 1] if going down.
      int new_row = row + (direction == 1 ? -1 : 1);
      int new_column = column + (direction == 1 ? 1 : -1);
      // boundary?
      if (new_row < 0 || new_row == N || new_column < 0 || new_column == M) {
        if (direction == 1) {
          // going up, current [i, j] as its tail. If [i, j + 1] is within bounds(column < M - 1, not col==M-1!),
          // then it is the next head. Otherwise, the element directly below: [i + 1, j] is the next head
          // draw a 3x3, 4x3, 4x4 to play with it
          row += (column < M - 1 ? 0 : 1) ;
          column += (column < M - 1 ? 1 : 0);

        } else {
          column += (row < N - 1 ? 0 : 1);
          row += (row < N - 1 ? 1 : 0);
        }
        // Flip the direction
        direction = 1 - direction;
      } else { row = new_row; column = new_column; }
    }

    return result;
  }

  //所以最小的船载重量至少应该是最重的那个包裹，不然上不了船了，而最大的载重量就是包裹的总重量，一条船就能拉走了
  public int shipWithinDays1011(int[] weights, int days) {
    // very slow to use the following built in func
    // List<Integer> intList = Arrays.stream(weights).boxed().collect(Collectors.toList());
    // int left = Collections.max(intList);
    // int right  = intList.stream().reduce(0, (a, b) -> a + b);

    int left = 0; // max weight
    int right = 0; // sum
    for (int weight : weights) {
      left = Math.max(left, weight);
      right += weight;
    }

    // left, right is the boundary of capacity, mid the trial capacity
    while (left < right) {
      int mid = left + (right - left) / 2, dayCnt = 1, wSum = 0;
      for (int weight : weights) {
        wSum += weight;
        if (wSum > mid) {
          //another day, reset wSum to current weight, days++
          wSum = weight;
          ++dayCnt;
        }
      }
      if (dayCnt > days) left = mid + 1;
      else right = mid;
    }
    return left;
  }
}
