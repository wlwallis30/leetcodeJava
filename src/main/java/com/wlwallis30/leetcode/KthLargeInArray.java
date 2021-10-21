package com.wlwallis30.leetcode;

import java.util.*;

public class KthLargeInArray {
  public int findKthLargest215(int[] nums, int k) {
    // init heap 'the smallest element first'
    PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> n1 - n2);
    // keep k largest elements in the heap
    for (int num: nums) {
      heap.add(num);
      if (heap.size() > k) heap.poll();
    }
    return heap.poll();
  }

  public int findKthLargest_quickSelect(int[] nums, int k) {
    int left = 0, right = nums.length - 1;
    while (true) {
      int pos = partition(nums, left, right);
      if (pos == k - 1) return nums[pos];
      if (pos > k - 1) right = pos - 1;
      else left = pos + 1;
    }
  }
  public int partition(int[] nums, int left, int right) {
    int pivot = nums[left], l = left + 1, r = right;
    while (l <= r) {
      if (nums[l] < pivot && nums[r] > pivot) {
        Solution.swap(nums, l++, r--);
      }
      if (nums[l] >= pivot) ++l;
      if (nums[r] <= pivot) --r;
    }
    Solution.swap(nums, left, r);
    return r;
  }

  public int[] topKFrequent347(int[] nums, int k) {
    if (k == nums.length) { return nums; }
    // O(N) time
    Map<Integer, Integer> count = new HashMap<>();
    for (int num: nums) { count.put(num, count.getOrDefault(num, 0) + 1); }
    // init heap 'the less frequent element first'
    Queue<Integer> heap = new PriorityQueue<>( (n1, n2) -> count.get(n1) - count.get(n2));

    // 2. keep k top frequent elements in the heap O(N log k) < O(N log N) time
    for (int num: count.keySet()) {
      heap.add(num);
      if (heap.size() > k) heap.poll();
    }

    int[] top = new int[k];
    for(int i = k - 1; i >= 0; --i) { top[i] = heap.poll(); }
    return top;
  }
  // 347 can also use the quickSelect method as above, just break instead of return, and get the first K frequenct in

  // 692 using the same method, the following method failed with ["aaa","aa","a"] k =3, others are fine.
  public List<String> topKFrequent692_failed(String[] words, int k) {
    if(k == words.length) return Arrays.asList(words);

    Map<String, Integer> count = new HashMap<>();
    for (String word: words) { count.put(word, count.getOrDefault(word, 0) + 1); }
    // init heap 'the less frequent element first'
    // If same frequency, compare the string. Reverse lexicographical order in the top of the heap.
    Queue<String> heap = new PriorityQueue<>( (w1, w2) -> {
      int comp =  count.get(w1) - count.get(w2);
      if (comp != 0) return comp;
      return w2.compareTo(w1);
    });

    // 2. keep k top frequent elements in the heap O(N log k) < O(N log N) time
    for (String word: count.keySet()) {
      heap.add(word);
      if (heap.size() > k) heap.poll();
    }

    String[] top = new String[k];
    for(int i=k-1; i>=0 ; --i) { top[i] = heap.poll(); }
    return Arrays.asList(top);
  }

  public List<String> topKFrequent692_good(String[] words, int k) {
    Map<String, Integer> freqMap = new HashMap<>();
    for (String word : words) {
      freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
    }
    // Compare frequency first. Lowest frequency entry in the top of the heap.
    // If same frequency, compare the string. Reverse lexicographical order in the top of the heap.
    PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>((e1, e2) -> {
      int comp = e1.getValue().compareTo(e2.getValue());
      if (comp != 0) return comp;
      return e2.getKey().compareTo(e1.getKey());
    });
    for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
      heap.add(entry);
      if (heap.size() > k) { heap.poll(); }
    }
    List<String> res = new ArrayList<>(k);
    while (!heap.isEmpty()) { res.add(heap.poll().getKey()); }
    Collections.reverse(res);
    return res;
  }

  public int nearestValidPoint1779(int x, int y, int[][] points) {
    int index = -1;
    int distance = Integer.MAX_VALUE;
    for (int i = 0; i < points.length; i++) {
      // valid point
      int[] point = points[i];
      if (point[0] == x || point[1] == y) {
        int currDist = Math.abs(point[0] - x) + Math.abs(point[1] - y);
        if (currDist < distance) {
          distance = currDist;
          index = i;
        }
      }
    }
    return index;
  }

  public int[][] kClosest973(int[][] points, int k) {
    // max heap
    PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
        (p1, p2) -> p2[0]*p2[0] + p2[1]*p2[1] - (p1[0]*p1[0] + p1[1]*p1[1]));
    for(int[] point: points) {
      heap.add(point);
      if(heap.size() > k) { heap.poll();}
    }

    int[][] top = new int[k][];
    for(int i=k-1; i>=0 ; --i) { top[i] = heap.poll(); }
    return top;
  }
}
