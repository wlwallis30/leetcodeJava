package com.wlwallis30.leetcode;

import java.util.*;

public class RepeatedDNA {
  public List<String> findRepeatedDnaSequences187Brute(String s) {
    int L = 10, n = s.length();
    HashSet<String> seen = new HashSet<>(), output = new HashSet<>();

    // iterate over all sequences of length L
    for (int start = 0; start < n - L + 1; ++start) {
      String tmp = s.substring(start, start + L);
      if (seen.contains(tmp)) output.add(tmp);
      seen.add(tmp);
    }
    return new ArrayList<>(output);
  }

  // mask 只需要表示 18 位，所以变成了 0x3ffff
  List<String> findRepeatedDnaSequences187Bitmask(String s) {
    if (s.length() <= 10) return new ArrayList();
    HashSet<String> res = new HashSet<>();
    HashSet<Integer> pattern = new HashSet<>();
    HashMap<Character, Integer> map = new HashMap(){{ put('A', 0); put('C', 1); put('G', 2); put('T', 3);}};
    int cur = 0;
    for (int i = 0; i < 9; ++i) cur = cur << 2 | map.get(s.charAt(i));
    for (int i = 9; i < s.length(); ++i) {
      cur = ((cur & 0x3ffff) << 2) | (map.get(s.charAt(i)));
      if (pattern.contains(cur)) res.add(s.substring(i - 9, i+1));
      else pattern.add(cur);
    }
    return new ArrayList<>(res);
  }
}
