package com.wlwallis30.leetcode;

import java.util.*;

public class CloneGraph {
  class Node {

    public int val;
    public List<Node> neighbors;

    public Node() {
    }

    public Node(int _val, List<Node> _neighbors) {
      val = _val;
      neighbors = _neighbors;
    }
  }

  // since node val are diff, so we can use the following map. you can also use<node,node> map for generic case.
  private HashMap <Integer, Node> visited = new HashMap <> ();
  public Node cloneGraph133DFS(Node node) {
    if (node == null) { return node; }
    // Return the clone from the visited dictionary.
    if (visited.containsKey(node.val)) { return visited.get(node.val); }

    // cloning, Note that we don't have cloned neighbors as of now, hence [].
    Node cloneNode = new Node(node.val, new ArrayList());
    // The key is original node and value being the clone node.
    visited.put(node.val, cloneNode);
    for (Node neighbor: node.neighbors) { cloneNode.neighbors.add(cloneGraph133DFS(neighbor)); }

    return cloneNode;
  }

  // BFS/queue
  public Node cloneGraph133Queue(Node node) {
    if (node == null) { return node; }
    HashMap<Node, Node> visited = new HashMap();

    // original node's queue to clone
    LinkedList<Node> queue = new LinkedList<> ();
    queue.add(node);
    Node cloned = new Node(node.val, new ArrayList<>());
    // Clone the node and put it in the visited dictionary.
    visited.put(node, cloned);

    // Start BFS traversal
    while (!queue.isEmpty()) {
      // Pop a node say "n" from the from the front of the queue.
      Node curNode = queue.remove();
      // Iterate through all the neighbors of the node "n"
      for (Node neighbor: curNode.neighbors) {
        if (!visited.containsKey(neighbor)) {
          cloned = new Node(neighbor.val, new ArrayList<>());
          visited.put(neighbor, cloned);
          // Add the newly encountered node to the queue.
          queue.add(neighbor);
        }
        // Add the clone of the neighbor to the neighbors of the clone node "n".
        cloned = visited.get(curNode);
        cloned.neighbors.add(visited.get(neighbor));
      }
    }

    // Return the clone of the node from visited.
    return visited.get(node);
  }
}

final class RandomList {
  class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }
  HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();
  // O(N) and space O(N)
  public Node copyRandomList138Recur(Node head) {
    if (head == null) { return null; }
    if (this.visitedHash.containsKey(head)) { return this.visitedHash.get(head); }
    Node node = new Node(head.val);

    this.visitedHash.put(head, node);
    node.next = this.copyRandomList138Recur(head.next);
    node.random = this.copyRandomList138Recur(head.random);

    return node;
  }

  HashMap<Node, Node> visited = new HashMap<Node, Node>();
  public Node copyRandomList138Iteration(Node head) {
    if (head == null) { return null; }
    Node oldNode = head;
    Node newNode = new Node(oldNode.val);
    this.visited.put(oldNode, newNode);
    while (oldNode != null) {
      newNode.random = this.getClonedNode(oldNode.random);
      newNode.next = this.getClonedNode(oldNode.next);
      oldNode = oldNode.next;
      newNode = newNode.next;
    }
    return this.visited.get(head);
  }

  public Node getClonedNode(Node node) {
    if(node == null) return  null;
    if (this.visited.containsKey(node)) { return this.visited.get(node); }
    this.visited.put(node, new Node(node.val));
    return this.visited.get(node);
  }

  // space O(1)
  Node copyRandomList138Weave(Node head) {
    if(head == null) return null;

    Node cur = head, curNext;
    // Weave all nodes
    while(cur != null) {
      curNext = cur.next;
      Node tmpNew = new Node(cur.val);
      tmpNew.next=curNext;
      cur.next=tmpNew;
      cur=curNext;
    }

    cur=head;
    Node newCur;
    // link the new random pointers
    while(cur != null) {
      newCur = cur.next;
      if(cur.random != null) newCur.random = cur.random.next;
      cur=newCur.next;
    }

    cur=head;
    Node newHead=head.next;
    while(cur != null) {
      newCur=cur.next;
      curNext=newCur.next;
      // unweaving
      cur.next=curNext;
      if(curNext != null) newCur.next=curNext.next;
      // advance the cur
      cur=curNext;;
    }

    return newHead;
  }
}

