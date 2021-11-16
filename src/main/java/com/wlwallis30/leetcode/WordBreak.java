package com.wlwallis30.leetcode;

import java.util.*;

public class WordBreak {
  //玩子数组或者子字符串且求极值的题，基本就是 DP
  // without memo, O(2^n), n is the string length, coz at each index, we could split it into two parts. At each step, we have a choice: to split or not to split
  //memo[i] 定义为范围为 [i, n] 的子字符串是否可以拆分，初始化为 -1
  //O(n^3) now,  recursion tree can go up to n^2 and the substring computation is also n.
  public boolean wordBreak139WithMemo(String s, List<String> wordDict) {
    return wordBreakMemo(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
  }

  private boolean wordBreakMemo(String s, Set<String> wordDict, int start, Boolean[] memo) {
    if (start == s.length()) { return true; }
    if (memo[start] != null) { return memo[start]; }
    for (int end = start + 1; end <= s.length(); end++) {
      if (wordDict.contains(s.substring(start, end)) && wordBreakMemo(s, wordDict, end, memo)) {
        return memo[start] = true;
      }
    }
    return memo[start] = false;
  }

  public boolean wordBreak139BFSbyQ(String s, List<String> wordDict) {
    Set<String> wordDictSet = new HashSet<>(wordDict);
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[s.length()];
    queue.add(0);
    while (!queue.isEmpty()) {
      int start = queue.remove();
      if (visited[start]) {
        continue;
      }
      for (int end = start + 1; end <= s.length(); end++) {
        //sbustring's endIdx is exclusive!
        if (wordDictSet.contains(s.substring(start, end))) {
          //the next possible word will start with "end" idx.
          queue.add(end);
          if (end == s.length()) { return true; }
        }
      }
      visited[start] = true;
    }
    return false;
  }

  // DP way is more like a human being's thinkg, check cat, then start the substring2 "sanddog" to check with dict
  // two substrings, idx range of [0, curStart-1] and [curStart, len-1]
  public boolean wordBreak139DP(String s, List<String> wordDict) {
    Set<String> wordDictSet = new HashSet<>(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int len = 1; len <= s.length(); len++) {
      //curStart index, where dp[curStart] retore the previous substring with length of "curStart" num
      // e.g. dp[4] = true, means Cats with len of 4 is in dictionary, currently we start to look substring from index of 4
      for (int curStart = 0; curStart < len; curStart++) {
        if (dp[curStart] && wordDictSet.contains(s.substring(curStart, len))) {
          dp[len] = true;
          break;
        }
      }
    }
    return dp[s.length()];
  }

  protected Set<String> wordSet;
  protected HashMap<String, List<List<String>>> memo;
  public List<String> wordBreak140(String s, List<String> wordDict) {
    wordSet = new HashSet<>();
    for (String word : wordDict) { wordSet.add(word); }
    memo = new HashMap<>();
    _wordBreak_topdown(s);

    // chain up words together
    List<String> ret = new ArrayList<>();
    for (List<String> words : memo.get(s)) {
      StringBuilder sentence = new StringBuilder();
      for (String word : words) {
        sentence.insert(0, word);
        sentence.insert(0, " ");
      }
      ret.add(sentence.toString().trim());
    }

    return ret;
  }

  protected List<List<String>> _wordBreak_topdown(String s) {
    if (s.equals("")) {
      List<List<String>> solutions = new ArrayList<>();
      solutions.add(new ArrayList<>());
      return solutions;
    }

    if (memo.containsKey(s)) {
      return memo.get(s);
    } else {
      List<List<String>> solutions = new ArrayList<>();
      memo.put(s, solutions);
    }

    for (int endIndex = 1; endIndex <= s.length(); ++endIndex) {
      String word = s.substring(0, endIndex);
      if (wordSet.contains(word)) {
        List<List<String>> subsentences = _wordBreak_topdown(s.substring(endIndex));
        for (List<String> subsentence : subsentences) {
          List<String> newSentence = new ArrayList<>(subsentence);
          newSentence.add(word);
          memo.get(s).add(newSentence);
        }
      }
    }
    return memo.get(s);
  }
}
