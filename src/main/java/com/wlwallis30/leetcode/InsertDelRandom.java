package com.wlwallis30.leetcode;

import java.util.*;

public class InsertDelRandom {
}
//380, refert to 398 which is reservoir sampling, 380 is a set, 398 can have repeated nums, need to be equal prob to sample the repeated num
//水塘抽样的前提都是set非常大
class RandomizedSet {
  Map<Integer, Integer> valIdxMap;
  List<Integer> list;
  Random rand = new Random();

  /** Initialize your data structure here. */
  public RandomizedSet() {
    valIdxMap = new HashMap();
    list = new ArrayList();
  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
    if (valIdxMap.containsKey(val)) return false;

    valIdxMap.put(val, list.size());
    list.add(list.size(), val);
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    if (! valIdxMap.containsKey(val)) return false;

    // move the last element to the place idx of the element to delete
    int lastElement = list.get(list.size() - 1);
    int idx = valIdxMap.get(val);
    list.set(idx, lastElement);
    valIdxMap.put(lastElement, idx);
    // delete the last element
    //list.remove(idx) will have average of O(n), but removing the last is O(1)
    list.remove(list.size() - 1);
    valIdxMap.remove(val);
    return true;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    return list.get(rand.nextInt(list.size()));
  }
}
