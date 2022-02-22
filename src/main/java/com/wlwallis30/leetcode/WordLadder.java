package com.wlwallis30.leetcode;

import java.util.*;
import javafx.util.Pair;

public class WordLadder {
  // essentially be working with an undirected and unweighted graph with words as nodes and edges between words which differ by just one letter.
  // The problem boils down to finding the shortest path from a start node to a destination node
  // level order, BFS
  public int ladderLength127(String beginWord, String endWord, List<String> wordList) {
    Set<String> set = new HashSet<>(wordList);
    Queue<String> queue = new LinkedList<>();
    //Map<String, Integer> strSteps = new HashMap<>();  this is also works,
    // but if we remove the matched current word from set(BFS guarantee the steps so far are smallest), we dont  need this hashmap
    queue.add(beginWord);
    int wordCnt = 1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      // for this level
      for (int i = 0; i < size; i++) {
        char[] current = queue.poll().toCharArray();
        for (int j = 0; j < current.length; j++) {
          char tmp = current[j];
          // CHANGE ONE LETTER AT A TIME
          for (char c = 'a'; c <= 'z'; c++) {
            current[j] = c;
            String next = new String(current);
            // WHEN NEXT WORD IS IN THE SET
            if (set.contains(next)) {
              if (next.equals(endWord)) return wordCnt + 1;
              queue.add(next);
              set.remove(next);
            }
          }
          // HAVE TO UNDO FOR NEXT CHANGE OF LETTER
          current[j] = tmp;
        }
      }
      // this level is done, need to count
      wordCnt++;
    }
    return 0;
  }

  // bfs with queue + hashset, 由于需要return List<List<String>>, 所以需要一个Queue<List<String>> pathQ来追踪每个可能的path
  // meanwhile also need a Set<String> to remove words in each step for same level/depth of queue
  public List<List<String>> ladderLength126(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> res = new ArrayList<>();
    Set<String> wordSet = new HashSet<>(wordList);
    Queue<List<String>> pathQ = new LinkedList<>();
    List<String> starting = new ArrayList<>();
    starting.add(beginWord);
    pathQ.add(starting);

    while (!pathQ.isEmpty()) {
      int size = pathQ.size();
      // for this level
      Set<String> toRemove = new HashSet<>();
      for (int i = 0; i < size; i++) {
        List<String> curPath = pathQ.poll();
        char[] current = curPath.get(curPath.size()-1).toCharArray();
        for (int j = 0; j < current.length; j++) {
          char tmp = current[j];
          // CHANGE ONE LETTER AT A TIME
          for (char c = 'a'; c <= 'z'; c++) {
            current[j] = c;
            String next = new String(current);
            // WHEN NEXT WORD IS IN THE SET
            if (wordSet.contains(next)) {
              List<String> nextPath = new ArrayList<>(curPath);
              nextPath.add(next);
              if (next.equals(endWord))  { res.add(nextPath); }//endWord should be in the set, otherwise no path to endWord

              pathQ.add(nextPath);
              toRemove.add(next);//wordSet.remove(next) NOT working: only get one final path since you removed endWord
              //ans should be [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]], if you use wordSet.remove here, you only get one final path
            }
          }
          // HAVE TO UNDO FOR NEXT CHANGE OF LETTER
          current[j] = tmp;
        }
      }
      //only removing when this whole level is done, otherwise you will only get one final path since you removed endWord by wordSet.remove(next)
      toRemove.forEach(word -> { wordSet.remove(word); } );
      // also works toRemove.forEach(wordSet::remove);
    }
    return res;
  }
}
