package com.wlwallis30.leetcode;

import java.util.*;

public class SurroundedRegions {
  //start from the boundary 0, go deeper with connected 0, mark all of them to $, other 0 in the middle will die
  void solve130(char[][] board) {
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[i].length; ++j) {
        if ((i == 0 || i == board.length - 1 || j == 0 || j == board[i].length - 1) && board[i][j] == 'O')
          solveDFS(board, i, j);
      }
    }
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[i].length; ++j) {
        if (board[i][j] == 'O') board[i][j] = 'X'; // die!
        if (board[i][j] == '$') board[i][j] = 'O'; // base only connected to 延安 can survive
      }
    }
  }
  void solveDFS(char[][]board, int i, int j) {
    if (board[i][j] == 'O') {
      board[i][j] = '$';
      if (i > 0 && board[i - 1][j] == 'O') solveDFS(board, i - 1, j);
      if (i < board.length - 1 && board[i + 1][j] == 'O') solveDFS(board, i + 1, j);
      if (j > 0 && board[i][j - 1] == 'O') solveDFS(board, i, j - 1);
      if (j < board[i].length - 1 && board[i][j + 1] == 'O') solveDFS(board, i, j + 1);
    }
  }

  // you can use int[][] visited, but more space. just mark the vistied 1s to 0s, will be easier
  public int numIslands200(char[][] grid) {
    if (grid == null || grid.length == 0) { return 0; }

    int m = grid.length;
    int n = grid[0].length;
    int res = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == '1') {
          ++res;
          islandDFS(grid, r, c);
        }
      }
    }

    return res;
  }

  void islandDFS(char[][] grid, int r, int c) {
    int m = grid.length;
    int n = grid[0].length;
    if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == '0') { return; }

    grid[r][c] = '0'; // now this cell is visited
    islandDFS(grid, r - 1, c);
    islandDFS(grid, r + 1, c);
    islandDFS(grid, r, c - 1);
    islandDFS(grid, r, c + 1);
  }

  // O(m*n * m*n)
  void wallsAndGates286(int[][]rooms) {
    for (int i = 0; i < rooms.length; ++i) {
      for (int j = 0; j < rooms[i].length; ++j) {
        if (rooms[i][j] == 0) gateDFS(rooms, i, j, 0);
      }
    }
  }
  void gateDFS(int[][]rooms, int i, int j, int val) {
    // < val, means it already visited
    if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[i].length || rooms[i][j] < val) return;
    rooms[i][j] = val;
    gateDFS(rooms, i + 1, j, val + 1);
    gateDFS(rooms, i - 1, j, val + 1);
    gateDFS(rooms, i, j + 1, val + 1);
    gateDFS(rooms, i, j - 1, val + 1);
  }

  // with Q, O(m*n), space O(m*n)
  public void wallsAndGates286ByQ(int[][] rooms) {
    int m = rooms.length;
    if (m == 0) return;
    int n = rooms[0].length;
    Queue<int[]> q = new LinkedList<>();
    for (int row = 0; row < m; row++) {
      for (int col = 0; col < n; col++) {
        if (rooms[row][col] == 0) { q.add(new int[] { row, col }); }
      }
    }
    int[] deltaRow = {0, 0, 1, -1};
    int[] deltaCol = {1, -1, 0, 0};
    while (!q.isEmpty()) {
      int[] point = q.poll();
      int row = point[0];
      int col = point[1];
      for (int i = 0; i <  4; ++ i) {
        int r = row + deltaRow[i];
        int c = col + deltaCol[i];
        if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != Integer.MAX_VALUE) { continue; }
        rooms[r][c] = rooms[row][col] + 1;
        q.add(new int[] { r, c });
      }
    }
  }

  private void graphDFS(List<Integer>[] adjList, int[] visited, int startNode) {
    visited[startNode] = 1;

    for (int i = 0; i < adjList[startNode].size(); i++) {
      if (visited[adjList[startNode].get(i)] == 0) {
        graphDFS(adjList, visited, adjList[startNode].get(i));
      }
    }
  }

  public int countComponents323(int n, int[][] edges) {
    int components = 0;
    int[] visited = new int[n];

    List<Integer>[] adjList = new ArrayList[n];
    for (int i = 0; i < n; i++) {
      adjList[i] = new ArrayList<>();
    }

    for (int i = 0; i < edges.length; i++) {
      adjList[edges[i][0]].add(edges[i][1]);
      adjList[edges[i][1]].add(edges[i][0]);
    }

    for (int i = 0; i < n; i++) {
      if (visited[i] == 0) {
        components++;
        graphDFS(adjList, visited, i);
      }
    }
    return components;
  }

  // node from 0 ~ n-1
  // undirected graph, A-B-A is not a cyclic. DFS by stack, you can also use BFS by que
  //delete the opposite direction edges from the adjacency list to detect cycle while exclude trivial cycles like A-B-A
  public boolean validTree261Stack(int n, int[][] edges) {
    List<Integer>[] adjList = new ArrayList[n];
    for (int i = 0; i < n; i++) { adjList[i] = new ArrayList<>(); }
    for (int i = 0; i < edges.length; i++) {
      adjList[edges[i][0]].add(edges[i][1]);
      adjList[edges[i][1]].add(edges[i][0]);
    }
    Stack<Integer> stack = new Stack<>();
    stack.push(0);
// Use a set to keep track of already seen nodes to
// avoid infinite looping.
    Set<Integer> seen = new HashSet<>();
    seen.add(0);
    while (!stack.isEmpty()) {
      int node = stack.pop(); // Take one off to visit.
      // Check for unseen neighbours of this node:
      for (int neighbour : adjList[node]) {
        // Check if we've already seen this node., checking cycle
        if (seen.contains(neighbour)) {
          return false;
        }
        // Otherwise, put this neighbour onto stack
        // and record that it has been seen.
        stack.push(neighbour);
        seen.add(neighbour);
        // Remove the link that goes in the opposite direction.
        // need to convert into Integer object, otherwise it is removing by index !!
        adjList[neighbour].remove((Integer)node);
      }
    }

    return seen.size() == n; //check fully connected
  }

  // similar with above, List<Integer>[] or List<List<Integer>>
  private List<List<Integer>> adjacencyList = new ArrayList<>();
  private Set<Integer> visited = new HashSet<>();
  //  O(N + E) and space O(N+E).
  public boolean validTree261Recur(int n, int[][] edges) {
    if (edges.length != n - 1) return false;
    for (int i = 0; i < n; i++) { adjacencyList.add(new ArrayList<>()); }
    for (int[] edge : edges) {
      adjacencyList.get(edge[0]).add(edge[1]);
      adjacencyList.get(edge[1]).add(edge[0]);
    }
    // We return true iff no cycles were detected, // AND the entire graph has been reached.
    return validtreeDFS(0, -1) && visited.size() == n;
  }

  // parent is to avoid the trivial cycle, like A-B-A, and detecting the real cycles.
  public boolean validtreeDFS(int node, int parent) {
    if (visited.contains(node)) return false;
    visited.add(node);
    for (int neighbour : adjacencyList.get(node)) {
      if (parent != neighbour) {
        boolean result = validtreeDFS(neighbour, node);
        if (!result) return false;
      }
    }
    return true;
  }

  //a valid tree => exactly n - 1 edges.
  // if < n-1, then it is not fully connected.
  // if > n-1, cyclic
  // if == n-1,  could be: not fully connected, and has cycle. such as 0 1 2 form a cycle, 2 edges, 3, 4 connected, so 4 edges in total.
  // n-1, also could be fully connected without cycle.
  public boolean validTree261AdvGraphTheory(int n, int[][] edges) {
    if (edges.length != n - 1) return false;
    // Make the adjacency list.
    List<List<Integer>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < n; i++) { adjacencyList.add(new ArrayList<>()); }
    for (int[] edge : edges) {
      adjacencyList.get(edge[0]).add(edge[1]);
      adjacencyList.get(edge[1]).add(edge[0]);
    }

    Stack<Integer> stack = new Stack<>();
    Set<Integer> seen = new HashSet<>();
    stack.push(0);
    seen.add(0);

    while (!stack.isEmpty()) {
      int node = stack.pop();
      for (int neighbour : adjacencyList.get(node)) {
        // to avoid trivial cycle, A-B-A
        if (seen.contains(neighbour)) continue;
        seen.add(neighbour);
        stack.push(neighbour);
      }
    }

    return seen.size() == n;
  }

  boolean validTree261UnionFind(int n, int[][] edges) {
    int[] parents = new int[n];
    Arrays.fill(parents, -1);
    // for loop is as same as UnionFind class's union
    for (int[] oneEdge : edges) {
      int rootA = find(parents, oneEdge[0]), rootB = find(parents, oneEdge[1]);
      // cycle detected
      if (rootA == rootB) return false;
      parents[rootA] = rootB;
    }
    return edges.length == n - 1;
  }
  // this is as same as UnionFind class's find and part of the constructor
  int find(int[] parents, int node) {
    while (parents[node] != -1) node = parents[node];
    return node;
  }
  class UnionFind {
    private int[] parent;
    // For efficiency, we aren't using makeset, but instead initialising
    // all the sets at the same time in the constructor.
    public UnionFind(int n) {
      parent = new int[n];
      for (int node = 0; node < n; node++) { parent[node] = node; }
    }

    // The find method, without any optimizations. It traces up the parent
    // links until it finds the root node for A, and returns that root.
    public int find(int A) {
      while (parent[A] != A) { A = parent[A]; }
      return A;
    }

    // The union method, without any optimizations. It returns True if a
    // merge happened, False if otherwise.
    public boolean union(int A, int B) {
      // Find the roots for A and B.
      int rootA = find(A), rootB = find(B);
      // Check if A and B are already in the same set.
      if (rootA == rootB) { return false; }
      // Merge the sets containing A and B.
      parent[rootA] = rootB;
      return true;
    }
  }

  public int islandPerimeter463(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    int up, down, left, right;
    int result = 0;

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (grid[r][c] == 1) {
          if (r == 0) { up = 0; }
          else { up = grid[r-1][c]; }

          if (c == 0) { left = 0; }
          else { left = grid[r][c-1]; }

          if (r == rows-1) { down = 0; }
          else { down = grid[r+1][c]; }

          if (c == cols-1) { right = 0; }
          else { right = grid[r][c+1]; }

          result += 4-(up+left+right+down);
        }
      }
    }

    return result;
  }

  // num of island reformed
  public int area(int[][] grid, int r, int c) {
    if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0)
      return 0;
    grid[r][c] = 0;
    return (1 + area(grid, r+1, c) + area(grid, r-1, c)
        + area(grid, r, c-1) + area(grid, r, c+1));
  }

  public int maxAreaOfIsland695(int[][] grid) {
    int ans = 0;
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        ans = Math.max(ans, area(grid, r, c));
      }
    }
    return ans;
  }

  public void markOneIsland(int a[][], int i, int j) {
    if (i < 0 || i >= a.length || j < 0 ||  j >= a[0].length || a[i][j] == 0 || a[i][j] == 2) return;
    a[i][j] = 2;
    markOneIsland(a,i-1,j);markOneIsland(a,i+1,j);markOneIsland(a,i,j-1);markOneIsland(a,i,j+1);
  }
  public int shortestBridge934(int[][] a) {
    boolean found1st = false;
    Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[0].length; j++) {
        if (a[i][j] == 1 && !found1st) {
          found1st = true;
          markOneIsland(a,i,j);
        }
        // now it is the second island after found the first one via DFS of markOneIsland
        if (found1st && a[i][j] == 1) q.add(new int[]{i,j});
      }
    }
    int ans = 0;
    while (!q.isEmpty()) {
      // just explore for one level for this time
      int size = q.size();
      for (int c = 0; c < size; c++) {
        int b[] = q.remove();
        int i = b[0];
        int j = b[1];
        // now landing on the island marked with 2
        if ((i > 0 && a[i-1][j] == 2) || (i < a.length-1 && a[i+1][j] == 2)
            || (j > 0 && a[i][j-1] == 2) || (j < a[0].length-1 && a[i][j+1] == 2)) return ans;

        if (i > 0 && a[i-1][j] == 0) {
          a[i-1][j] = 1;
          q.add(new int[]{i-1,j});
        }
        if (i < a.length-1 && a[i+1][j] == 0) {
          a[i+1][j] = 1;
          q.add(new int[]{i+1,j});
        }
        if (j > 0 && a[i][j-1] == 0) {
          a[i][j-1] = 1;
          q.add(new int[]{i,j-1});
        }
        if (j < a[0].length-1 && a[i][j+1] == 0) {
          a[i][j+1] = 1;
          q.add(new int[]{i,j+1});
        }
      }
      ans++;
    }
    return 0;
  }

  private int[][] grid;
  private boolean[][] visited1;
  private StringBuffer currentIsland;

  public int numDistinctIslands694(int[][] grid) {
    this.grid = grid;
    this.visited1 = new boolean[grid.length][grid[0].length];
    Set<String> islands = new HashSet<>();
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        currentIsland = new StringBuffer();
        dfs(row, col, '0');
        if (currentIsland.length() == 0) {
          continue;
        }
        islands.add(currentIsland.toString());
      }
    }
    return islands.size();
  }

  private void dfs(int row, int col, char dir) {
    if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) return;
    if (visited1[row][col] || grid[row][col] == 0) return;
    visited1[row][col] = true;
    currentIsland.append(dir);
    dfs(row + 1, col, 'D');
    dfs(row - 1, col, 'U');
    dfs(row, col + 1, 'R');
    dfs(row, col - 1, 'L');
    currentIsland.append('0');
  }
}
