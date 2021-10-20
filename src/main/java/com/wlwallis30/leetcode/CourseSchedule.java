package com.wlwallis30.leetcode;

import java.util.*;
import java.util.stream.*;

public class CourseSchedule {
  // BFS, topology sort, queue
  public boolean canFinish207(int numCourses, int[][] prerequisites) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int i=0; i<numCourses; i++) graph.put(i, new ArrayList<>());
    int[] indegree = new int[numCourses];
    for (final int[] vertex : prerequisites) {
      graph.get(vertex[1]).add(vertex[0]);
      indegree[vertex[0]]++;
    }
    Queue<Integer> queue = new ArrayDeque<>();
    IntStream.range(0, numCourses).filter(i -> indegree[i] == 0).forEach(queue::offer);
    while (!queue.isEmpty()) {
      int current = queue.poll();
      graph.get(current).stream().filter(child -> --indegree[child] == 0).forEach(queue::offer);
      numCourses--;
    }
    return numCourses == 0;
  }

  int NOT_PROCESSED = 0, IN_PROCESS = 1, PROCESSED = 2;
  // DFS
  public boolean canFinish207DFS(int n, int[][] pre) {
    int[] visited = new int[n];
    //create adjacency list
    ArrayList<Integer>[] graph = new ArrayList[n];
    for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
    for(int i = 0; i < pre.length; i++) graph[pre[i][1]].add(pre[i][0]);

    //visit each node & its neighbors
    for(int i = 0; i < n; i++) if(isCyclic(graph, visited, i)) return false;

    return true;
  }

  public boolean isCyclic(ArrayList<Integer>[] graph, int[] visited, int src) {
    if(visited[src] == IN_PROCESS) return true; //cycle found, visited twice or more
    if(visited[src] == PROCESSED) return false; //all neighbors of src are already visited
    visited[src] = IN_PROCESS; // initially set src as IN_PROCESS
    for(int nbr : graph[src]) {
      if(isCyclic(graph, visited, nbr)) return true;
    }
    visited[src] = PROCESSED; //set PROCESSED when all neighbors of src are visited
    return false;
  }

  // BFS + inDegree
  public int[] findOrder210(int numCourses, int[][] prerequisites) {
    Map<Integer, List<Integer>> adjList = new HashMap<>();
    int[] indegree = new int[numCourses];
    int[] res = new int[numCourses];

    // Create the adjacency list representation of the graph
    for (int[] vertex: prerequisites) {
      List<Integer> lst = adjList.getOrDefault(vertex[1], new ArrayList<>());
      lst.add(vertex[0]);
      adjList.put(vertex[1], lst);
      indegree[vertex[0]] += 1;
    }

    // Add all vertices with 0 in-degree to the queue
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) { if (indegree[i] == 0) { q.add(i); } }

    int i = 0;
    // Process until the Q becomes empty
    while (!q.isEmpty()) {
      int srcNode = q.remove();
      res[i++] = srcNode;

      // Reduce the in-degree of each neighbor by 1
      if (adjList.containsKey(srcNode)) {
        for (Integer neighbor : adjList.get(srcNode)) {
          indegree[neighbor]--;

          // If in-degree of a neighbor becomes 0, add it to the Q
          if (indegree[neighbor] == 0) { q.add(neighbor); }
        }
      }
    }

    // Check to see if topological sort is possible or not.
    if (i == numCourses) {
      return res;
    }

    return new int[0];
  }

  public String alienOrder269(String[] words) {
    // Step 0: Create data structures and find all unique letters.
    Map<Character, List<Character>> adjList = new HashMap<>();
    Map<Character, Integer> indegree = new HashMap<>();
    for (String word : words) {
      for (char c : word.toCharArray()) {
        indegree.put(c, 0);
        adjList.put(c, new ArrayList<>());
      }
    }

    // Step 1: Find all edges.
    for (int i = 0; i < words.length - 1; i++) {
      String word1 = words[i];
      String word2 = words[i + 1];
      // Check that word2 is not a prefix of word1.
      if (word1.length() > word2.length() && word1.startsWith(word2)) {
        return "";
      }
      // Find the first non match and insert the corresponding relation.
      for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
        if (word1.charAt(j) != word2.charAt(j)) {
          adjList.get(word1.charAt(j)).add(word2.charAt(j));
          indegree.put(word2.charAt(j), indegree.get(word2.charAt(j)) + 1);
          break;//important to break here.
        }
      }
    }

    // Step 2: Breadth-first search.
    StringBuilder sb = new StringBuilder();
    Queue<Character> queue = new LinkedList<>();
    for (Character c : indegree.keySet()) {
      if (indegree.get(c).equals(0)) {
        queue.add(c);
      }
    }
    while (!queue.isEmpty()) {
      Character c = queue.remove();
      sb.append(c);
      for (Character next : adjList.get(c)) {
        indegree.put(next, indegree.get(next) - 1);
        if (indegree.get(next).equals(0)) {
          queue.add(next);
        }
      }
    }

    if (sb.length() < indegree.size()) {
      return "";
    }
    return sb.toString();
  }
}
