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

  //computeIfAbsent returns "the current (existing or computed) value associated with the specified key, or null if the computed value is null"
  //putIfAbsent returns "the previous value associated with the specified key, or null if there was no mapping for the key". putIfAbsent takes the value directly.
  //https://stackoverflow.com/questions/48183999/what-is-the-difference-between-putifabsent-and-computeifabsent-in-java-8-map
  //getOrDefault 的用法会更略麻烦一点，多一行 put, refer to LRUCache.java
  public List<List<String>> groupStrings249(String[] strings) {
    List<List<String>> res = new ArrayList<>();
    Map<String, List<String>> codeStrMap = new HashMap<>();
    for(String word: strings) {
      StringBuilder code = new StringBuilder();
      for(int i=0; i<word.length(); ++i) {
        Character charCode = (char)((word.charAt(i) + 26 - word.charAt(0))%26 + 'a');
        code.append(charCode).append('$');
      }
      //if(!codeStrMap.containsKey(code.toString())) { codeStrMap.put(code.toString(), new ArrayList<>());} you need to use toString(), otherwise it is diff SB object!!!
      codeStrMap.putIfAbsent(code.toString(), new ArrayList<>()); // this is as same as above, better method to use
      codeStrMap.get(code.toString()).add(word);
    }

    for(Map.Entry <String, List<String>> entry: codeStrMap.entrySet()){ res.add(entry.getValue()); }

    return res;
  }
}