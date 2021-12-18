package com.wlwallis30.leetcode;

import java.util.*;
import javafx.util.*;

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

  //dfs you can use int[][] visited, but more space. just mark the vistied 1s to 0s, will be easier
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

  // undirected graph, also refer 721
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

  //hard
  int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
  private int rows;
  private int cols;

  public int largestIsland827(int[][] grid) {
    rows = grid.length;
    cols = grid[0].length;

    int[] areas = new int[rows * cols + 2];
    int areaIndex = 2;

    for(int r = 0; r < rows; r++){
      for(int c = 0; c < cols; c++){
        if(grid[r][c] != 1) continue;
        //save the area of this ones island in unique index
        // getArea: mark each island in grid[][] with index started with 2, and when dfs also count the area and store it in area[]
        areas[areaIndex] = getArea(grid, r, c, areaIndex);
        areaIndex++;
      }
    }

    int maxArea = 0;
    //max area without toggling zero
    for(int area : areas) maxArea = Math.max(area, maxArea);

    //max area after toggling zero to one
    for(int r = 0; r < rows; r++){
      for(int c = 0; c < cols; c++){
        if(grid[r][c] != 0) continue;

        Set<Integer> seenIsland = new HashSet();
        int area = 1;
        for(int[] dir : DIRECTIONS){
          int x = r + dir[0];
          int y = c + dir[1];
          if(isOutsideGrid(x, y)) continue;
          if(seenIsland.contains(grid[x][y])) continue;//same island
          //mark as seen this island
          seenIsland.add(grid[x][y]);
          //add the island area of current direction
          area += areas[grid[x][y]];
        }

        maxArea = Math.max(area, maxArea);
      }
    }

    return maxArea;
  }

  // getArea: mark each island in grid[][] with index started with 2, and when dfs also count the area and store it in area[]
  private int getArea(int[][] grid, int r, int c, int areaIndex){
    if(isOutsideGrid(r, c)) return 0;
    if(grid[r][c] != 1) return 0;
    //marked Visited and assign a unique index,
    grid[r][c] = areaIndex;
    int area = 1; //start counting the area
    for(int[] dir : DIRECTIONS){
      int x = r + dir[0];
      int y = c + dir[1];
      area += getArea(grid, x, y, areaIndex);
    }

    return area;
  }

  private boolean isOutsideGrid(int r, int c){ return r < 0 || r >= rows || c < 0 || c >= cols; }

  private int bfs(int[][] grid, int row, int col, int totalHouses) {
    // Next four directions.
    int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    int rows = grid.length;
    int cols = grid[0].length;
    int distanceSum = 0;
    int housesReached = 0;

    // Queue to do a bfs, starting from (row, col) cell.
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{ row, col });

    // Keep track of visited cells.
    boolean[][] vis = new boolean[rows][cols];
    vis[row][col] = true;

    int steps = 0;
    while (!q.isEmpty() && housesReached != totalHouses) {
      for (int i = q.size(); i > 0; --i) {
        int[] curr = q.poll();
        row = curr[0];
        col = curr[1];

        // If this cell is a house, then add the distance from source to this cell
        // and we go past from this cell.
        if (grid[row][col] == 1) {
          distanceSum += steps;
          housesReached++;
          continue;
        }

        // This cell was empty cell, hence traverse the next cells which is not a blockage.
        for (int[] dir : dirs) {
          int nextRow = row + dir[0];
          int nextCol = col + dir[1];
          if (nextRow >= 0 && nextCol >= 0 && nextRow < rows && nextCol < cols) {
            if (!vis[nextRow][nextCol] && grid[nextRow][nextCol] != 2) {
              vis[nextRow][nextCol] = true;
              q.offer(new int[]{ nextRow, nextCol });
            }
          }
        }
      }

      // After traversing one level of cells, increment the steps by 1 to reach to next level.
      steps++;
    }

    // If we did not reach all houses, then any cell visited also cannot reach all houses.
    // Set all cells visted to 2 so we do NOT check them again and return MAX_VALUE.
    if (housesReached != totalHouses) {
      for (row = 0; row < rows; row++) {
        for (col = 0; col < cols; col++) {
          if (grid[row][col] == 0 && vis[row][col]) { grid[row][col] = 2; }
        }
      }
      return Integer.MAX_VALUE;
    }

    return distanceSum;
  }

  public int shortestDistance317(int[][] grid) {
    int minDistance = Integer.MAX_VALUE;
    int rows = grid.length;
    int cols = grid[0].length;
    int totalHouses = 0;

    // count the total houses
    for (int row = 0; row < rows; ++row) {
      for (int col = 0; col < cols; ++col) {
        if (grid[row][col] == 1) { totalHouses++; }
      }
    }

    // Find the min distance sum for each empty cell.
    for (int row = 0; row < rows; ++row) {
      for (int col = 0; col < cols; ++col) {
        if (grid[row][col] == 0) { minDistance = Math.min(minDistance, bfs(grid, row, col, totalHouses)); }
      }
    }

    return minDistance == Integer.MAX_VALUE? -1 : minDistance;
  }

  /*
  Case #1: 1-0-0-0-1  Case #2: 0-1-0-1-0:  between the left-most and right-most point
  Case #3: 1-0-0-0-0-0-0-1-1. check the distribution of 1
  But the best meeting point should be at x=7 and the total distance is 8.
  In fact, the median must be the optimal meeting point
  Case #4: 1-1-0-0-1, the median is x=1, the best meeting point, if x=2, not best
  Case #5: 1-1-0-0-1-1.  any of x=1 to x=4 points and the total distance is minimized
   */
  public int minTotalDistance296(int[][] grid) {
    List<Integer> rows = new ArrayList<>();
    List<Integer> cols = new ArrayList<>();
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == 1) {
          rows.add(row);
          cols.add(col);
        }
      }
    }
    // we adding row in a sorted order, but col are not, col are cyclic, so need to sort it
    int row = rows.get(rows.size() / 2);
    Collections.sort(cols);
    int col = cols.get(cols.size() / 2);
    return minDistance1D(rows, row) + minDistance1D(cols, col);
  }

  private int minDistance1D(List<Integer> points, int origin) {
    int distance = 0;
    for (int point : points) {
      distance += Math.abs(point - origin);
    }
    return distance;
  }

  /**
   1. Find the start , box and target cordinate .
   2. Then using Start Cordinate start BFS . Create Normal Visted set
   3. There will be  3 Possibilities :
     3.1 You find the Target , return whateevr No of Moves you have done till now .
     3.2 You find the Box , increase the Moves by 1, distance + moves +1  .
     3.2 You dont find Box , so move On without increasing Moves
   NOTE : Use PriotityQueue to sort Next value by Distance to get MIN diustnace
   **/
  private static final int [][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};
  public int minPushBox1263(char[][] grid) {
    int start [] = new int[2];
    int box [] = new int[2];
    int target[] = new int[2];

    int rowSize = grid.length;
    int colSize = grid[0].length;
    boolean startFound = false;
    boolean boxFound = false;
    boolean targetFound = false;
    boolean found =false;

    for( int row =0; row < rowSize ; row++){
      for( int col =0; col< colSize ; col++){
        char curValue = grid[row][col];
        switch(curValue){
          case 'S': //start
            startFound=true;
            start[0] = row;
            start[1] = col;
            break;
          case 'B' : // box
            boxFound = true;
            box[0] = row;
            box[1] = col;
            break;
          case 'T': //target
            targetFound = true;
            target[0] = row;
            target[1] = col;
            break;
        }
        if(startFound && boxFound && targetFound){ found = true; break; }
      }
      if( found){ break; }
    }

    PriorityQueue<int[]> pq = new PriorityQueue<>( (pq1,pq2)-> pq1[0] - pq2[0] );
    pq.offer(new int[]{dist(box[0],box[1], target[0],target[1]), 0 , start[0], start[1], box[0], box[1]});

    Set<String> visited = new HashSet<>();
    while(!pq.isEmpty()){
      int size = pq.size();
      for( int i=0; i < size ; i++){
        int [] node = pq.poll();
        int dist = node[0];
        int pushes = node[1];
        int row = node[2];
        int col = node[3];
        int boxX = node[4];
        int boxY = node[5];

        // if box equals to target return
        if(boxX == target[0] && boxY== target[1]){
          return pushes;
        }
        String key = row + "-" + col +"-"+ boxX +"-"+ boxY;
        if(visited.contains(key)){ continue; } //handle this scenario before
        visited.add(key);
        // EXplore
        for(int dir []: DIRS){
          int newRow = row + dir[0];
          int newCol = col + dir[1];
          if(!isValid(newRow,newCol, rowSize, colSize, grid)){ continue; }
          // if its equal to Box, means you can push 1 move.
          if(boxX == newRow &&  boxY == newCol) {
            int newBoxX = newRow + dir[0];
            int newBoxY = newCol + dir[1];
            if(!isValid(newBoxX,newBoxY, rowSize, colSize, grid)){ continue; }

            pq.offer( new int[]{dist(newBoxX,newBoxY,target[0],target[1]) + pushes + 1 , pushes +1 , newRow,newCol,newBoxX,newBoxY} );
          }else {
            // If the new cordinate not equal to Box, means you moved to empty cell "."
            pq.offer( new int[]{dist,pushes,newRow,newCol,boxX, boxY} );
          }
        }
      }
    }
    return -1;
  }

  private boolean isValid(int row , int col , int rowSize , int colSize, char grid[][]){
    if(row < 0 || row > rowSize-1 || col < 0 || col > colSize-1 || grid [row][col] == '#'){ return false; }
    return true;
  }

  private int dist(int x, int y, int tx, int ty){ return Math.abs(x-tx)+Math.abs(y-ty); }

  //hard,
  interface Robot {
    public boolean move();
    public void turnLeft();
    public void turnRight();
    public void clean();
  }

  // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
  int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  Set<Pair<Integer, Integer>> visited2 = new HashSet();
  Robot robot;

  public void goBack() {
    //turn 180 degree
    robot.turnRight();
    robot.turnRight();
    robot.move();
    // turn 180 degree, facing the same direction
    robot.turnLeft();// turnRight also fine
    robot.turnLeft(); // turnRight also fine
  }

  public void backtrack(int row, int col, int d) {
    visited2.add(new Pair(row, col));
    robot.clean();
    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    for (int i = 0; i < 4; ++i) {
      int newD = (d + i) % 4;
      int newRow = row + directions[newD][0];
      int newCol = col + directions[newD][1];

      if (!visited2.contains(new Pair(newRow, newCol)) && robot.move()) {
        backtrack(newRow, newCol, newD);
        goBack();
      }
      // if newRow,newCol is a wall, you dont move, turn right, if you moved, since you just goBack and facing the same direction, still turn right to make clockwise
      // turn the robot following chosen direction : clockwise
      robot.turnRight();
    }
  }

  public void cleanRoom489(Robot robot) {
    this.robot = robot;
    backtrack(0, 0, 0);
  }
}
