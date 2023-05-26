package com.wlwallis30.leetcode;

import java.util.*;

//208
public class Trie {
  class TrieNode {

    // R links to node children
    private TrieNode[] links;
    private boolean isWord;

    public TrieNode() {
      links = new TrieNode[26];
    }

    public boolean containChild(char ch) {
      return links[ch - 'a'] != null;
    }

    public TrieNode getChild(char ch) {
      return links[ch - 'a'];
    }

    public void putChild(char ch, TrieNode node) {
      links[ch - 'a'] = node;
    }

    public void setWord() {
      isWord = true;
    }

    public boolean isWord() {
      return isWord;
    }
  }

  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  // Inserts a word into the trie.
  public void insert(String word) {
    TrieNode node = root;
    for (int i = 0; i < word.length(); i++) {
      char currentChar = word.charAt(i);
      if (!node.containChild(currentChar)) {
        node.putChild(currentChar, new TrieNode());
      }
      node = node.getChild(currentChar);
    }
    node.setWord();
  }

  public boolean startsWith(String word) {
    TrieNode node = root;
    for (int i = 0; i < word.length(); i++) {
      char curLetter = word.charAt(i);
      if (!node.containChild(curLetter)) return false;
      node = node.getChild(curLetter);
    }

    return true;
  }

  // Returns if the word is in the trie.
  public boolean search(String word) {
    TrieNode node = root;
    for (int i = 0; i < word.length(); i++) {
      char curLetter = word.charAt(i);
      if (!node.containChild(curLetter)) return false;
        node = node.getChild(curLetter);
      }

    return node.isWord();
  }

  //211 add and search word
  class WordDictionary {
    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
      root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
      TrieNode node = root;

      for (char ch : word.toCharArray()) {
        if (!node.containChild(ch)) {
          node.putChild(ch, new TrieNode());
        }
        node = node.getChild(ch);
      }
      node.setWord();
    }

    /** Returns if the word is in the node. 由于有".", 所以需要递归或者stack*/
    public boolean searchInNode(String word, TrieNode node, int start) {
      if(node == null) return false;
      if(start == word.length()) return node.isWord();
      char ch = word.charAt(start);
      if(ch=='.') {
        // '.' case:  check all possible nodes at this level
        for (TrieNode child: node.links) {
          if((searchInNode(word, child, start+1))) return true;
        }
        //after checking all children, failed, so false
        return false;
      } else if (!node.containChild(ch)) {
        return false;
      } else {
        // if the character is found go down to the next level in root
        node = node.getChild(ch);
        return searchInNode(word, node, start+1);
      }
    }

    // also works with for loop, another format/structure, maybe better, consistent with 208
    public boolean searchInNode(String word, TrieNode node) {
      if(node == null) return false;//preventing trouble of null pointer
      for (int i = 0; i < word.length(); ++i) {
        char ch = word.charAt(i);
        //System.out.println("idx:" + i + "  char:" + ch + "$$$");
        if(ch=='.') {
          // '.' case:  check all possible nodes at this level
          // System.out.println("recursive call, links: "+ node.links[1]);  some element of the links array is null, so after checking all children, return false.
          for (TrieNode child: node.links) { if((searchInNode(word.substring(i + 1), child))) return true; }
          return false;
        }
        else if (!node.containChild(ch)) { return false; }
        else { node = node.getChild(ch); }
      }
      return node.isWord();
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
      return searchInNode(word, root, 0);
    }
  }
}

/* using hash map to implement trie
class TrieNode {
  Map<Character, TrieNode> map = new HashMap<>();
  boolean isWordEnd = false;
  TrieNode() { }
   }
class WordDictionary {
    private TrieNode root;
    public WordDictionary() { this.root = new TrieNode(); }

  public void addWord(String word) {
    TrieNode node = root;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      if (node.map.containsKey(ch)) {
        node = node.map.get(ch);
      } else {
        TrieNode newNode = new TrieNode();
        node.map.put(ch, newNode);
        node = newNode;
      }
    }
    node.isWordEnd = true;
  }
  public boolean search(String word) {
    return search(word, 0, root);
  }

  private boolean search(String word, int i, TrieNode node) {
    if (i == word.length())
      return node.isWordEnd;
    char ch = word.charAt(i);
    if (ch == '.') {
      for (Character character : node.map.keySet()) {
        if (search(word, i + 1, node.map.get(character))) return true;
      }
      return false;
    } else {
      if (node.map.containsKey(ch)) { return search(word, i + 1, node.map.get(ch));
      }
    }
    return false;
  }
}
 */

//642: 普通trieNode就行简单修改： 1。 最低层的存储 full sentence 2。 每个trieNode维护 碰到它的 top3 list
// https://www.youtube.com/watch?v=NX68_rf_gxE&t=204s
class AutocompleteSystem {
  class TrieNode implements Comparable<TrieNode> {
    TrieNode[] children;
    String s;
    int times;
    List<TrieNode> top3;

    public TrieNode() {
      children = new TrieNode[128];
      s = null; // only the bottom node will store the full sentence coz this problem ask to return the full sentence
      times = 0;
      top3 = new ArrayList<>();
    }

    public int compareTo(TrieNode o) {
      if (this.times == o.times) {
        //正序，即升序
        return this.s.compareTo(o.s);
      }
      //倒序，即降序
      return o.times - this.times;
    }

    // each level of trie node will have the corresponding its own top3,
    // e.g. "i love you" "island", node i will store both sentences, node " " will store the first sentence,
    // node "s" will store sentence of "island"
    //this method is to update the top3 list with new leafNode/full sentence for this node
    public void updateTop3(TrieNode leafNode) {
      if (!this.top3.contains(leafNode)) {
        this.top3.add(leafNode);
      }

      Collections.sort(top3);

      if (top3.size() > 3) {
        top3.remove(top3.size() - 1);
      }
    }
  }

  TrieNode root;
  TrieNode cur;
  StringBuilder sb;
  public AutocompleteSystem(String[] sentences, int[] times) {
    root = new TrieNode();
    cur = root;
    sb = new StringBuilder();

    for (int i = 0; i < times.length; i++) {
      add(sentences[i], times[i]);
    }
  }


  //build trie tree when adding a sentence and its frequency
  public void add(String sentence, int t) {
    TrieNode tmp = root;

    List<TrieNode> visited = new ArrayList<>();
    for (char c : sentence.toCharArray()) {
      if (tmp.children[c] == null) {
        tmp.children[c] = new TrieNode();
      }

      tmp = tmp.children[c];
      visited.add(tmp);
    }

    //now reaching the leaf/bottom node, no need to update times for non-leaf node
    tmp.s = sentence;
    tmp.times += t;

    TrieNode leaf = tmp;
    //among all visited previous parent nodes and current leaf node, need to update the top3
    for (TrieNode node : visited) {
      node.updateTop3(leaf);
    }
  }

  public List<String> input(char c) {
    List<String> res = new ArrayList<>();
    if (c == '#') {
      add(sb.toString(), 1);//adding the finished inputs to dict
      //reset
      sb = new StringBuilder();
      cur = root;
      return res;
    }

    sb.append(c);
    if (cur != null) {
      cur = cur.children[c];
    }

    //if cur not on trie tree
    if (cur == null) return res;
    for (TrieNode node : cur.top3) {
      res.add(node.s);
    }

    return res;
  }
}

