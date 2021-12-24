package com.wlwallis30.leetcode;

import java.util.*;
import java.util.Map.Entry;

public class WordPattern {
  boolean isIsomorphic_205(String s, String t) {
    if(s.length()!=t.length())return false;
    int[] sHash = new int[256];
    int[] tHash = new int[256];
    Arrays.fill(sHash, -1);
    Arrays.fill(tHash, -1);
    for(int i=0;i<s.length();++i)
    {
      if(sHash[s.charAt(i)]!=tHash[t.charAt(i)]) return false;
      sHash[s.charAt(i)]=i;
      tHash[t.charAt(i)]=i;
    }

    return true;
  }

  //要求一一对应，char to two more words, or word to to two more char, in both cases, pattern breaks
  public boolean wordPattern290(String pattern, String s) {
    HashMap<Character, String> charStringMap = new HashMap<>();
    String[] words = s.split(" ");

    if (words.length != pattern.length())
      return false;

    for (Integer i = 0; i < words.length; i++) {
      char c = pattern.charAt(i);
      String w = words[i];

      if (charStringMap.containsKey(c)) {
        if (!charStringMap.get(c).equals(w))
          return false;
      } else {
        // it is a new key of char. if word already appear b4, pattern breaks
        // for hashmap, keySet, entrySet, values are useful
        //for example:
        // for(Entry<Character, String> entry: charStringMap.entrySet())
        //charStringMap.forEach((k,v) -> System.out.println(k + v)); this is lamda
        if (charStringMap.values().contains(w))
          return false;
        charStringMap.put(c, w);
      }
    }

    return true;
  }

  // hashmap can hold both char and string as keys
  public boolean wordPattern290MapBoth(String pattern, String s) {
    HashMap map_index = new HashMap();
    String[] words = s.split(" ");

    if (words.length != pattern.length())
      return false;

    for (Integer i = 0; i < words.length; i++) {
      char c = pattern.charAt(i);
      String w = words[i];

      if (!map_index.containsKey(c))
        map_index.put(c, i);

      if (!map_index.containsKey(w))
        map_index.put(w, i);

      if (map_index.get(c) != map_index.get(w))
        return false;
    }

    return true;
  }

  // one pass for dict, O(N*n), N is len of dict, n is average len of each word
  public boolean differByOne1554(String[] dict) {
    if(dict.length==0) return false;
    Set<String> set = new HashSet<>();
    for(String word: dict){
      for(int i=0; i<word.length(); i++){
        // skipping the current char, make it to *,
        String newStr = word.substring(0,i)+"*"+word.substring(i+1);
        if(set.contains(newStr))
          return true;
        set.add(newStr);
      }
    }

    return false;
  }
}
