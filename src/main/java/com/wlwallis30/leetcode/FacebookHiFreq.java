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

  //cutting ribbon: k ribbons of all the same positive integer length.
  // very important https://medium.com/swlh/binary-search-find-upper-and-lower-bound-3f07867d81fb
  public int maxLength1891(int[] ribbons, int k) {
    int low = 0;
    int high = Integer.MIN_VALUE;
    for(int r : ribbons){ high = Math.max(high, r); }

    while(low < high){
      int mid = low + (high-low+1)/2;;
      int wantedPieces = 0;
      for(int len : ribbons){ wantedPieces += (len/mid); }

      //searching for the higher bound, it is invalid now, safe to make hight = mid-1
      // mid is too big, we get fewer pieces, so try smaller piece
      if(wantedPieces < k){ high = mid-1; }
      else{ low = mid; }
    }

    return low;
  }

  public double angleClock1344(int hour, int minutes) {
    int oneMinAngle = 6;
    int oneHourAngle = 30;

    double minutesAngle = oneMinAngle * minutes;
    double hourAngle = (hour % 12 + minutes / 60.0) * oneHourAngle;

    double diff = Math.abs(hourAngle - minutesAngle);
    return Math.min(diff, 360 - diff);
  }

  public boolean canPlaceFlowers605(int[] flowerbed, int n) {
    int i = 0, count = 0;
    while (i < flowerbed.length) {
      // when i-1, i, i+1 are 0, then we can plant. also consider the corner case of i=0 and i=length-1
      if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
        flowerbed[i] = 1;
        count++;
      }
      i++;
    }
    return count >= n;
  }

  // if prodNums = [3, 3, 3, 2, 2, 3, 3], then the run-length array is [[3,3], [2,2], [3,3] and we do not merge non-adjacent nums
  public List<List<Integer>> findRLEArray1868(int[][] encoded1, int[][] encoded2) {
    int p1 = 0, p2=0;
    List<List<Integer>> res = new ArrayList<>();

    while(p1 < encoded1.length) {
      int len = Math.min(encoded1[p1][1], encoded2[p2][1]);
      int mult = encoded1[p1][0] * encoded2[p2][0];

      if(res.size() > 0 && res.get(res.size()-1).get(0) == mult) //to handle cases like [[1,3],[2,3]] * [[6,3],[3,3]] --> [[6,6]]
        res.get(res.size()-1).set(1, res.get(res.size()-1).get(1)+len); //update previous mult in res instead of adding a new one
      else // res.size() == 0 || mult != res.get(res.size()-1).get(0))
        res.add(Arrays.asList(mult, len));

      encoded1[p1][1] -= len;
      encoded2[p2][1] -= len;
      if(encoded1[p1][1] == 0) p1++;
      if(encoded2[p2][1] == 0) p2++;
    }
    return res;
  }

  //greedy and hashtable
  // ["A", "A", "A", "A", "A", "B", "B", "B", "B", "B", "C", "C", "C", "C", "C", "D", "D", "D"], n = 1
  // n is the LEAST number of spaces we need. when n = 1, we can actually place b, c, c.. items in between 'A's (the most frequent task).
  // So as long as the idles between the most frequent tasks are filled, it's guaranteed that the rest of tasks will not incur new idle time (bcs we can just insert them in between 'A's)
  public int leastInterval621(char[] tasks, int n) {
    // frequencies of the tasks
    int[] frequencies = new int[26];
    for (int t : tasks) {
      frequencies[t - 'A']++;
    }
    Arrays.sort(frequencies);
    // max frequency
    int f_max = frequencies[25];
    int idle_time = (f_max - 1) * n;

    for (int i = frequencies.length - 2; i >= 0 && idle_time > 0; --i) {
      idle_time -= Math.min(f_max - 1, frequencies[i]);
    }
    idle_time = Math.max(0, idle_time);

    return idle_time + tasks.length;
  }

  // this is O(NlogN), but from 621, you can iteratively build string via frequencies[], just need to need to write more careful code. then O(N)
  // refer to https://leetcode.com/problems/reorganize-string/discuss/1324757/Java-2-Different-Solutions, 2nd solution
  public String reorganizeString767(String S) {
    Map<Character, Integer> freq_map = new HashMap<>();
    for (char c: S.toCharArray()) {
      freq_map.put(c, freq_map.getOrDefault(c, 0) + 1);
    }
    PriorityQueue<Character> maxheap = new PriorityQueue<>( (a, b) -> freq_map.get(b) - freq_map.get(a) );
    // addAll() is adding more then one element to heap
    maxheap.addAll(freq_map.keySet());

    StringBuilder sb = new StringBuilder();
    while (maxheap.size() > 1) {
      char first = maxheap.poll();
      char second = maxheap.poll();
      sb.append(first);
      sb.append(second);
      freq_map.put(first, freq_map.get(first) - 1);
      freq_map.put(second, freq_map.get(second) - 1);
      // add() comes from Collection. returns true and throws an exception if it can't add the element,
      // offer() comes from Queue.  offer() is allowed to return false if it can't add the element.
      if (freq_map.get(first) > 0) { maxheap.offer(first); }
      if (freq_map.get(second) > 0) { maxheap.offer(second); }
    }

    if (!maxheap.isEmpty()) {
      // when there is only 1 element left in the maxheap
      if (freq_map.get(maxheap.peek()) > 1) { return ""; }
      else { sb.append(maxheap.poll()); }
    }
    return sb.toString();
  }
}
