package com.wlwallis30.leetcode;

import java.util.*;

public class ValidAnagram {
  boolean isAnagram242(String s, String t) {
    if(s.length()!=t.length())return false;
    // there are only lowercase letters
    int[] map= new int[26];
    Arrays.fill(map, 0);
    for(Character eachChar:s.toCharArray()) ++map[eachChar-'a'];
    for(Character eachChar:t.toCharArray()) --map[eachChar-'a'];

    for(int each:map){if(each<0) return false;}
    return true;
  }

  // only contains lowercase letters
  public List<List<String>> groupAnagrams49(String[] strs) {
    if (strs.length == 0) return new ArrayList();
    Map<String, List> res = new HashMap<>();
    int[] charCnt = new int[26];
    for (String s : strs) {
      Arrays.fill(charCnt, 0);
      for (char c : s.toCharArray()) charCnt[c - 'a']++;

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 26; i++) {
        sb.append(charCnt[i]);
        // encoding the string as the key
        sb.append('$');
      }
      String key = sb.toString();
      if (!res.containsKey(key)) res.put(key, new ArrayList());
      res.get(key).add(s);
    }
    return new ArrayList(res.values());
  }
}