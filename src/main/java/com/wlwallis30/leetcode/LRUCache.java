package com.wlwallis30.leetcode;

import java.util.*;

class DLinkedNode {
  int key;
  int value;
  DLinkedNode prev;
  DLinkedNode next;
}
// LRUCache146
public class LRUCache {
  //when adding a new node.
  private void addToHead(DLinkedNode node) {
    //Always add the new node right after head.
    node.prev = head;
    node.next = head.next;

    head.next.prev = node;
    head.next = node;
  }

  //when updating existing node
  private void moveToHead(DLinkedNode node){
    removeNode(node);
    addToHead(node);
  }

  //when evicting or updating(existing node) , called by poptail or by moveToHead
  private void removeNode(DLinkedNode node){
    DLinkedNode prev = node.prev;
    DLinkedNode next = node.next;

    prev.next = next;
    next.prev = prev;
  }

  //when evicting
  private DLinkedNode popTail() {
    DLinkedNode res = tail.prev;
    removeNode(res);
    return res;
  }

  private Map<Integer, DLinkedNode> cache = new HashMap<>();
  private int size;
  private int capacity;
  private DLinkedNode head, tail;

  public LRUCache(int capacity) {
    this.size = 0;
    this.capacity = capacity;

    head = new DLinkedNode();
    // head.prev = null;

    tail = new DLinkedNode();
    // tail.next = null;

    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    DLinkedNode node = cache.get(key);
    if (node == null) return -1;
    moveToHead(node);
    return node.value;
  }

  public void put(int key, int value) {
    DLinkedNode node = cache.get(key);
    if(node == null) {
      DLinkedNode newNode = new DLinkedNode();
      newNode.key = key;
      newNode.value = value;
      cache.put(key, newNode);
      addToHead(newNode);
      ++size;

      if(size > capacity) { DLinkedNode last = popTail(); cache.remove(last.key); --size; } //evicting
    } else { node.value = value; moveToHead(node); } //updating existing one
  }
}

// using linked hash map, LHM
class LRUCache146LHM extends LinkedHashMap<Integer, Integer>{
  private int capacity;

  public LRUCache146LHM(int capacity) {
    super(capacity, 0.75F, true);
    this.capacity = capacity;
  }

  public int get(int key) {
    return super.getOrDefault(key, -1);
  }

  public void put(int key, int value) {
    super.put(key, value);
  }

  @Override
  //protected means that the member can be accessed by any class in the same package and by subclasses even if they are in another packages
  protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
    return size() > capacity;
  }
}

//LFU 460
class LFUCache {
  final int capacity;
  int curSize;
  int minFrequency;
  Map<Integer, DLLNode> cache;
  Map<Integer, DoubleLinkedList> frequencyMap;

  public LFUCache(int capacity) {
    this.capacity = capacity;
    this.curSize = 0;
    this.minFrequency = 0;

    this.cache = new HashMap<>();
    this.frequencyMap = new HashMap<>();
  }

  public int get(int key) {
    DLLNode curNode = cache.get(key);
    if (curNode == null) {
      return -1;
    }
    updateNode(curNode);
    return curNode.val;
  }

  public void put(int key, int value) {
    // corner case: check cache capacity initialization
    if (capacity == 0) { return; }

    if (cache.containsKey(key)) {
      DLLNode curNode = cache.get(key);
      curNode.val = value;
      updateNode(curNode);
    }
    else {
      curSize++;
      if (curSize > capacity) {
        // get minimum frequency list
        DoubleLinkedList minFreqList = frequencyMap.get(minFrequency);
        cache.remove(minFreqList.tail.prev.key);
        minFreqList.removeNode(minFreqList.tail.prev);
        curSize--;
      }
      // reset min frequency to 1 because of adding new node
      minFrequency = 1;
      DLLNode newNode = new DLLNode(key, value);

      // get the list with frequency 1, and then add new node into the list, as well as into LFU cache
      DoubleLinkedList curList = frequencyMap.getOrDefault(1, new DoubleLinkedList());
      curList.addToHead(newNode);
      frequencyMap.put(1, curList);
      cache.put(key, newNode);
    }
  }

  //update when node freq >= 1(existing node/key). freq = 1 is handled by putting a new key
  public void updateNode(DLLNode curNode) {
    int curFreq = curNode.frequency;
    DoubleLinkedList curList = frequencyMap.get(curFreq);
    curList.removeNode(curNode);

    // if current list the the last list which has lowest frequency and current node is the only node in that list
    // we need to remove the entire list and then increase min frequency value by 1
    if (curFreq == minFrequency && curList.listSize == 0) { minFrequency++; }

    curNode.frequency++;
    // add current node to another list has current frequency + 1,
    // if we do not have the list with this frequency, initialize it
    DoubleLinkedList newList = frequencyMap.getOrDefault(curNode.frequency, new DoubleLinkedList());
    newList.addToHead(curNode);
    frequencyMap.put(curNode.frequency, newList);
  }

  class DLLNode {
    int key; int val; int frequency; DLLNode prev; DLLNode next;
    public DLLNode(int key, int val) { this.key = key; this.val = val; this.frequency = 1; }
  }

  class DoubleLinkedList {
    int listSize; DLLNode head; DLLNode tail;
    public DoubleLinkedList() {
      this.listSize = 0;
      this.head = new DLLNode(0, 0); this.tail = new DLLNode(0, 0);
      head.next = tail;
      tail.prev = head;
    }

    /** add new node into head of list and increase list size by 1 **/
    public void addToHead(DLLNode curNode) {
      DLLNode nextNode = head.next;
      curNode.next = nextNode;
      curNode.prev = head;
      head.next = curNode;
      nextNode.prev = curNode;
      listSize++;
    }

    /** remove input node and decrease list size by 1**/
    public void removeNode(DLLNode curNode) {
      DLLNode prevNode = curNode.prev;
      DLLNode nextNode = curNode.next;
      prevNode.next = nextNode;
      nextNode.prev = prevNode;
      listSize--;
    }
  }
}

//588
class FileSystem {
  //this class is like a trie structure, separate Directory and File List
  class Dir {
    HashMap < String, Dir > dirMap = new HashMap < > ();
    HashMap < String, String > fileMap = new HashMap < > ();
  }
  Dir root;
  public FileSystem() { root = new Dir(); }

  public List < String > ls(String path) {
    Dir curDir = root;
    List < String > filesOrWithDir = new ArrayList < > ();
    if (!path.equals("/")) {
      String[] d = path.split("/");
      // going down to the last level b4 final
      for (int i = 1; i < d.length - 1; i++) {
        curDir = curDir.dirMap.get(d[i]);
      }

      // check if the last part is a file or dir
      if (curDir.fileMap.containsKey(d[d.length - 1])) {
        filesOrWithDir.add(d[d.length - 1]);
        return filesOrWithDir;
      } else {// if it is still a dir
        curDir = curDir.dirMap.get(d[d.length - 1]);
      }
    }
    filesOrWithDir.addAll(new ArrayList < > (curDir.dirMap.keySet()));
    filesOrWithDir.addAll(new ArrayList < > (curDir.fileMap.keySet()));
    Collections.sort(filesOrWithDir);
    return filesOrWithDir;
  }

  public void mkdir(String path) {
    Dir curDir = root;
    String[] d = path.split("/");
    //split "/..." with "/" gives "" an empty string in Java, so the d[0] is ""
    for (int i = 1; i < d.length; i++) {
      if (!curDir.dirMap.containsKey(d[i])) curDir.dirMap.put(d[i], new Dir());
      curDir = curDir.dirMap.get(d[i]);
      //curDir = curDir.dirMap.getOrDefault(d[i], new Dir());
      // above can not be used, if key not exist, getOrDefault will NOT create, only return a default value!
    }
  }

  public void addContentToFile(String filePath, String content) {
    Dir curDir = root;
    String[] d = filePath.split("/");
    for (int i = 1; i < d.length - 1; i++) {
      curDir = curDir.dirMap.get(d[i]);
    }
    curDir.fileMap.put(d[d.length - 1], curDir.fileMap.getOrDefault(d[d.length - 1], "") + content);
  }

  public String readContentFromFile(String filePath) {
    Dir curDir = root;
    String[] d = filePath.split("/");
    for (int i = 1; i < d.length - 1; i++) {
      curDir = curDir.dirMap.get(d[i]);
    }
    return curDir.fileMap.get(d[d.length - 1]);
  }
}
