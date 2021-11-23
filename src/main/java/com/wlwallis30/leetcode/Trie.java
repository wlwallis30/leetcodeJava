package com.wlwallis30.leetcode;

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
      if (node.containChild(curLetter)) {
        node = node.getChild(curLetter);
      } else {
        return false;
      }
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

    /** Returns if the word is in the node. */
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
    /* bad structure,(ch=='.') and (!node.containChild(ch) need to be nested, otherwise add[a], search[.a] will fail due to separating these 2 conditions.
    and node not moving to next level, so return true
      if(node == null) return false;//preventing trouble of null pointer
      for (int i = 0; i < word.length(); ++i) {
        char ch = word.charAt(i);
        if(ch=='.') {
          // '.' case:  check all possible nodes at this level
          for (TrieNode child: node.links) { if((searchInNode(word.substring(i + 1), child))) return true; }
        } else if (!node.containChild(ch)) { return false; }
        else {
          // if the character is found go down to the next level in root
          node = node.getChild(ch);
        }
      }
      return node.isWord();
     */

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
      return searchInNode(word, root, 0);
    }
  }
}
