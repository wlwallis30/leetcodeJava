package com.wlwallis30.leetcode;

import java.util.*;

public class SearchIn2DMatrix {
  // O(log(m*n))
  public boolean searchMatrix74MimicArray(int[][] matrix, int target) {
    int m = matrix.length;
    if (m == 0) return false;
    int n = matrix[0].length;

    // binary search
    int left = 0, right = m * n - 1;
    int pivotIdx, pivotElement;
    while (left < right) {
      pivotIdx = (left + right) / 2;
      pivotElement = matrix[pivotIdx / n][pivotIdx % n];
      if (target>pivotElement) left = pivotIdx + 1;
      else right = pivotIdx;
    }

    return matrix[left/n][left%n]==target;
  }

  // O(log(m) + log(n)), better solution
  public boolean searchMatrix74(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
    int m = matrix.length, n = matrix[0].length;

    // binary search #1: looking for the right row to search for the value
    int start = 0, end = m-1;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (matrix[mid][n - 1] < target) start = mid + 1;
      else end = mid;
    }

    if (start == m) return false;
    //if (matrix[start][n - 1] == target) return true;

    int theRow = start;

    // binary search #2: looking for the target in that row
    start = 0;
    end = n-1;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (matrix[theRow][mid] < target) start = mid + 1;
      else end = mid;
    }
    return start != n && matrix[theRow][start] == target;
  }

  //左下角和右上角的数。左下角的 18，往上所有的数变小，往右所有数增加,
  //当然也可以把起始数放在右上角
  // O(m+n) coz row can be increased m times, col for n times in while loop
  public boolean searchMatrix240(int[][] matrix, int target) {
    // start our "pointer" in the bottom-left
    int row = matrix.length-1;
    int col = 0;

    while (row >= 0 && col < matrix[0].length) {
      if (matrix[row][col] > target) {
        row--;
      } else if (matrix[row][col] < target) {
        col++;
      } else { // found it
        return true;
      }
    }

    return false;
  }

  interface BinaryMatrix {
    int get(int row, int col);
    List<Integer> dimensions();
  }

  public int leftMostColumnWithOne1428(BinaryMatrix binaryMatrix) {
    int rows = binaryMatrix.dimensions().get(0);
    int cols = binaryMatrix.dimensions().get(1);
    int smallestIndex = cols;
    for (int row = 0; row < rows; row++) {
      // Binary Search for the first 1 in the row.
      int left = 0;
      int right = cols - 1;
      while (left < right) {
        int mid = (left + right) / 2;
        if (binaryMatrix.get(row, mid) == 0) { left = mid + 1; }
        else { right = mid; }
      }
      // If the last element in the search space is a 1, then this row contained a 1.
      if (binaryMatrix.get(row, left) == 1) { smallestIndex = Math.min(smallestIndex, left); }
    }
    // If smallest_index is still set to cols, then there were no 1's in the grid.
    return smallestIndex == cols ? -1 : smallestIndex;
  }

  private static final int[][] directions =
      new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

  //changing the original matrix
  //要点：直接将grid[neighbourRow][neighbourCol]用dist赋值，可以避免visited矩阵， 2. 因为使用queue，dist小的在queue的前面，所以不用Math.min来求最小
  public int shortestPathBinaryMatrix1091(int[][] grid) {
    if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
      return -1;
    }

    // Set up the BFS.
    Queue<int[]> idxQ = new ArrayDeque<>();
    grid[0][0] = 1;
    idxQ.add(new int[]{0, 0});

    // Carry out the BFS
    while (!idxQ.isEmpty()) {
      int[] cell = idxQ.remove();
      int row = cell[0];
      int col = cell[1];
      int distance = grid[row][col];
      if (row == grid.length - 1 && col == grid[0].length - 1) {
        return distance;
      }
      for (int[] neighbour : getNeighbours(row, col, grid)) {
        int neighbourRow = neighbour[0];
        int neighbourCol = neighbour[1];
        idxQ.add(new int[]{neighbourRow, neighbourCol});
        grid[neighbourRow][neighbourCol] = distance + 1;
      }
    }

    // The target was unreachable.
    return -1;
  }

  private List<int[]> getNeighbours(int row, int col, int[][] grid) {
    List<int[]> neighbours = new ArrayList<>();
    for (int i = 0; i < directions.length; i++) {
      int newRow = row + directions[i][0];
      int newCol = col + directions[i][1];
      if (newRow < 0 || newCol < 0 || newRow >= grid.length
          || newCol >= grid[0].length
          || grid[newRow][newCol] != 0) {
        continue;
      }
      neighbours.add(new int[]{newRow, newCol});
    }
    return neighbours;
  }

  public boolean containsCycle1559(char[][] grid) {
    boolean visited[][] = new boolean[grid.length][grid[0].length];
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid[0].length; j++){
        // when first time is not visited and dfs explore will visit again, then it is a loop
        if(!visited[i][j] && isMoveBack(grid,i,j,visited,-1,-1)) return true;
      }
    }
    return false;
  }
  static int moves[][] = {{1,0},{0,1},{-1,0},{0,-1}};
  //用prevX， prevY的原因： 因为要是用vis[x][y]来判断是否循环，所以不能用vis[x][y] 放在条件里判断，否则无法进入下一层call。用prevXY来追踪就好了
  public boolean isMoveBack(char grid[][], int x, int y, boolean[][] visited, int prevX, int prevY){
    //转了一圈又回来了，return true
    if(visited[x][y]) return true;
    visited[x][y] = true;
    for(int i = 0; i < moves.length; i++){
      int nextX = x+moves[i][0];
      int nextY = y+moves[i][1];
      if(nextX >= 0 && nextX < grid.length &&
          nextY >= 0 && nextY < grid[0].length &&
          (prevX != nextX  || prevY != nextY) && //this condition checks, next block to move on matrix is not previous block.
          grid[nextX][nextY] == grid[x][y] &&
          isMoveBack(grid,nextX,nextY,visited,x,y)) return true;
    }
    return false;
  }
  //1559, using step counts, but hard to write it correctly in interview
  /*
  int count = 0;
    public boolean dfs(char[][] grid, int i,int j,int[][] visited, int previ,int prevj, char c){

        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]!=c)
            return false;

        if(visited[i][j]-visited[previ][prevj]>=3)
        // 完成cycle后， 原点count已经变>=5, 但是另外一个recursive call也会进入这个cell，这时二者差>=3
            return true;

        if(visited[i][j]!=0)
            return false;

        // first time visited[i][j] is still the count, then count++, that num will be put for next visiting cell
        // in the first example,visited[0][0] at first is 0, after cycle it is 12, 12-1 = 11>3.
        visited[i][j]=count++;

        return dfs(grid,i+1,j,visited,i,j,c) || dfs(grid,i-1,j,visited,i,j,c) || dfs(grid,i,j+1,visited,i,j,c) || dfs(grid,i,j-1,visited,i,j,c);
    }
    public boolean containsCycle(char[][] grid) {

        int n = grid.length, m = grid[0].length;
        int[][]visited = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(visited[i][j]==0 && dfs(grid,i,j,visited,i,j,grid[i][j]))
                    return true;
            }
        }
        return false;
    }
   */


  //dp[i][j] represents：when there are 'i' hops left and the current number is 'j', how many combination we have
  //current number is 0 and have 2 hops left, it would be dp[2][0],then our dp array will try to get the value stored in dp[1][4] and dp[1][6]
  //our dp[2][0] will be store the value 2, which is equavalent to try out the number combination "04" and "06"
  //反过来想，可以去的位置，就表示也可能从该位置回来，所以根据当前的位置j，就可以在数组中找到上一次骑士所在的位置
  public int knightDialer935(int N) {
    Integer[][] graph = new Integer[][]{{4, 6}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0},
        {},{1, 7, 0}, {2, 6}, {1, 3}, {2, 4}};

    int MOD = (int)1e9 + 7;
    //dp[i][j] stores：when there are 'i' hops left and the current number is 'j', how many combination we have
    int[][] dp = new int[N + 1][10];
    //when it has 1 hop left on the current number, it should just return 1 (dp[0][j] IS NOT CORRECT)
    for(int j = 0; j < 10; j++) dp[1][j] = 1;
    //be careful. i start on 2 because 2 ~ N is N - 1, and i - 1 could get our basecase
    for(int i = 2; i <= N; i++){
      for(int j = 0; j < 10; j++){
        for(int neighbor : graph[j]){//current number's neighbor
          dp[i][j] += dp[i - 1][neighbor];
          //MOD should be seperated because it may alter our state transfer when number gets big
          dp[i][j] %= MOD;
        }

      }
    }
    int cnt = 0;
    for(int j = 0; j < 10;j++){
      cnt += dp[N][j];
      cnt %= MOD; //MOD should be seperated
    }
    return cnt;
  }

  // this is not the dijkastra algorithm, but visited matrix give the similar concept. Dijkstra: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
  int maximumMinimumPath1102(int[][] A) {
    int rows = A.length;
    int cols = A[0].length;
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited = new boolean[rows][cols];
    PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[2] - a[2]);   // max heap: int[2] stores the min path value encountered till now
    queue.offer(new int[]{0, 0, A[0][0]});

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int preMinInPath = cell[2];
      if (cell[0] == rows - 1 && cell[1] == cols - 1) {     // reached the end: return the min value
        return preMinInPath;
      }
      visited[cell[0]][cell[1]] = true;

      for (int[] dir : dirs) {   // explore all the 4 neighbors
        int neighborRow = dir[0] + cell[0];
        int neighborCol = dir[1] + cell[1];

        if (neighborRow < 0 || neighborCol < 0 || neighborRow >= rows
            || neighborCol >= cols || visited[neighborRow][neighborCol]) {
          continue;
        }
        // maxheap store the min along the path, but since it is max heap, the top one will guarantee it will generate the max path
        int minTillNow = Math.min(A[neighborRow][neighborCol], preMinInPath);
        queue.offer(new int[]{neighborRow, neighborCol, minTillNow});
      }
    }
    return -1;
  }
}
