package com.wlwallis30.leetcode;

import java.util.*;
import javafx.util.Pair;

public class BinaryTreeTraversal {
  public List < Integer > inorderTraversal94_recursive(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    inorderDFS(root, res);
    return res;
  }

  public void inorderDFS(TreeNode root, List < Integer > res) {
    if (root == null) return;

    if (root.left != null)  inorderDFS(root.left, res);
    res.add(root.val);
    if (root.right != null) inorderDFS(root.right, res);
  }

  public List < Integer > preTraversal144_recursive(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    preorderDFS(root, res);
    return res;
  }

  public void preorderDFS(TreeNode root, List < Integer > res) {
    if (root == null) return;

    res.add(root.val);
    if (root.left != null)  preorderDFS(root.left, res);
    if (root.right != null) preorderDFS(root.right, res);
  }

  public List < Integer > postTraversal145_recursive(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    postorderDFS(root, res);
    return res;
  }

  public void postorderDFS(TreeNode root, List < Integer > res) {
    if (root == null) return;

    if (root.left != null)  postorderDFS(root.left, res);
    if (root.right != null) postorderDFS(root.right, res);
    res.add(root.val);
  }

  public boolean isSameTree_100Recur(TreeNode p, TreeNode q) {
    // p and q are both null
    if (p == null && q == null) return true;
    // one of p and q is null
    if (q == null || p == null || p.val != q.val) return false;
    return isSameTree_100Recur(p.right, q.right) &&
        isSameTree_100Recur(p.left, q.left);
  }

  public boolean isSymmetricTree_101Recur(TreeNode root) {
    if(root == null) return true;
    return isSymDFS(root.left, root.right);
  }

  boolean isSymDFS(TreeNode curLeft, TreeNode curRight) {
    if(curLeft == null && curRight == null) return true;
    if((curLeft == null && curRight != null)
        || (curLeft != null && curRight == null)
        || curLeft.val != curRight.val) return false;
    return isSymDFS(curLeft.left, curRight.right) && isSymDFS(curLeft.right, curRight.left);
  }

  public int maxDepth_104Recur(TreeNode root) {
    // this is like post order
    if (root == null) return 0;
    int left_height = maxDepth_104Recur(root.left);
    int right_height = maxDepth_104Recur(root.right);
    return Math.max(left_height, right_height) + 1;
  }

  boolean isBalanced_110(TreeNode root) {
    if (checkDepthDFS(root) == -1) return false;
    else return true;
  }
  int checkDepthDFS(TreeNode root) {
    if (root == null) return 0;

    int left = checkDepthDFS(root.left);
    if (left == -1) return -1;
    // if left is not balanced, no need to go through right
    int right = checkDepthDFS(root.right);
    if (right == -1) return -1;

    if (Math.abs(left - right) > 1) return -1;
    else return 1 + Math.max(left, right); //must use max, otherwise not reflecting enough diff, e.g. [1,null,2,null,3]
  }

  public int minDepth_111Recur(TreeNode root) {
    if (root == null) {
      return 0;
    }

    if ((root.left == null) && (root.right == null)) {
      return 1;
    }

    int left=Integer.MAX_VALUE, right=Integer.MAX_VALUE;
    //int min_depth = Integer.MAX_VALUE;
    // this condition should not be removed, [2,null,3,null,4,null,5,null,6], after removing, left will be 0, you should not move down since it is not path to leaf
    if (root.left != null) {
      left = minDepth_111Recur(root.left);
    }
    if (root.right != null) {
      right = minDepth_111Recur(root.right);
    }

    int min_depth = Math.min(left, right);

    return min_depth + 1;
  }

  List<List<Integer>> levels = new ArrayList<List<Integer>>();
  public List<List<Integer>> levelOrder_102Recur(TreeNode root) {
    if (root == null) return levels;
    levelOrderDFS(root, 0);
    return levels;
  }

  public List<List<Integer>> levelOrderBottom107Recur(TreeNode root) {
    if (root == null) return levels;
    levelOrderDFS(root, 0);
    Collections.reverse(levels);
    return levels;
  }

  public void levelOrderDFS(TreeNode node, int curLevel) {
    if(node == null) return;
    // start the current level
    if (levels.size() == curLevel)
      levels.add(new ArrayList<Integer>());

    // fulfil the current level
    List<Integer> curLevelList = levels.get(curLevel);
    curLevelList.add(node.val);

    // process child nodes for the next level
    if (node.left != null)
      levelOrderDFS(node.left, curLevel + 1);
    if (node.right != null)
      levelOrderDFS(node.right, curLevel + 1);
  }

  private int diameter;
  public int diameterOfBinaryTree543(TreeNode root) {
    diameter = 0;
    longestPath(root);
    return diameter;
  }
  private int longestPath(TreeNode node){
    if(node == null) return 0;
    int leftPath = longestPath(node.left);
    int rightPath = longestPath(node.right);
    diameter = Math.max(diameter, leftPath + rightPath);

    // return the longest one between left_path and right_path;
    return Math.max(leftPath, rightPath) + 1;
  }

  // it is nice to judge if bst is balanced or not first, but you can just build
  public TreeNode balanceBST1382(TreeNode root) {
    List<TreeNode> list=new ArrayList<>();
    inorder(root,list);
    return buildTree(list,0,list.size()-1);
  }

  public TreeNode buildTree(List<TreeNode> list,int start,int end){
    if(start>end){ return null; }
    int mid=start+(end-start)/2;
    TreeNode root=list.get(mid);
    root.left=buildTree(list,start,mid-1);
    root.right=buildTree(list,mid+1,end);

    return root;
  }

  public void inorder(TreeNode root,List<TreeNode> list){
    if(root==null){ return; }
    inorder(root.left,list);
    list.add(root);
    inorder(root.right,list);
  }


  //1522
  class NchildNode {
    public int val;
    public List<NchildNode> children;

    public NchildNode() { children = new ArrayList<NchildNode>(); }
    public NchildNode(int _val) { val = _val; children = new ArrayList<NchildNode>(); }
    public NchildNode(int _val, ArrayList<NchildNode> _children) { val = _val; children = _children; }
  }
  protected int diameter1 = 0;

  protected int height(NchildNode node) {
    if (node.children.size() == 0)
      return 0;

    // select the top two largest heights
    int maxHeight1 = 0, maxHeight2 = 0;
    for (NchildNode child : node.children) {
      int parentHeight = height(child) + 1;
      if (parentHeight > maxHeight1) {
        maxHeight2 = maxHeight1;
        maxHeight1 = parentHeight;
      } else if (parentHeight > maxHeight2) {
        maxHeight2 = parentHeight;
      }
      // calculate the distance between the two farthest leaves nodes.
      int distance = maxHeight1 + maxHeight2;
      this.diameter1 = Math.max(this.diameter1, distance);
    }

    return maxHeight1;
  }

  public int diameter(NchildNode root) {
    this.diameter1 = 0;
    height(root);
    return diameter1;
  }

  // exactly same with 1123, prefer that solution
  public TreeNode subtreeWithAllDeepest865(TreeNode root) {
    return dfs(root).node;
  }

  // depth is calculated from the bottom, and returned to parent level by level
  public Result dfs(TreeNode node) {
    if (node == null) return new Result(null, 0);
    Result L = dfs(node.left), R = dfs(node.right);
    if (L.dist > R.dist) return new Result(L.node, L.dist + 1);
    if (L.dist < R.dist) return new Result(R.node, R.dist + 1);
    return new Result(node, L.dist + 1);
  }

  // compose a mapping relation via class, similar to Hashmap or Pair approach
  class Result {
    TreeNode node;
    int dist;
    Result(TreeNode n, int d) {
      node = n;
      dist = d;
    }

    int deepest = 0;
    TreeNode res = new TreeNode();
    // plz also check 865. This problem is exactly same as 865. but prefer this solution
    TreeNode lcaDeepestLeaves1123(TreeNode root) {
      helper(root, 0);
      return res;
    }

    // root will have depth 1, helper is returning the max depth b/ left and right subtree
    int helper(TreeNode node, int curDepth) {
      deepest = Math.max(deepest, curDepth);
      if (node==null) return curDepth;
      int left = helper(node.left, curDepth+1);
      int right = helper(node.right, curDepth+1);
      // LCA now
      if (left == deepest && right == deepest) { res = node; }
      return Math.max(left, right);
    }
  }

//////////////////////////////////////////////// iterations///////////////////////////////////////////////////////////////////////////////////
  public List < Integer > inorderTraversal94_stack(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    Stack < TreeNode > stack = new Stack < > ();
    TreeNode curr = root;
    // for inOrder, not smooth to push root first due to the following keep pushing till most left,
    // so put curr!=null condition in while
    while (curr != null || !stack.isEmpty()) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
      curr = stack.pop();
      res.add(curr.val);
      curr = curr.right;
    }
    return res;
  }

  // similar with the above, not push root first, put it in to while
  public List < Integer > preTraversal144_stack(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    Stack < TreeNode > stack = new Stack < > ();
    TreeNode curr = root;
    while (curr != null || !stack.isEmpty()) {
      if (curr != null) {
        res.add(curr.val);
        stack.push(curr);
        curr = curr.left;
      } else {
        curr = stack.peek();
        stack.pop();
        curr = curr.right;
      }
    }

    return res;
  }

  // push root first, recommended since many other iterative algo for trees use this approach for stack approach.
  public List < Integer > preTraversal144_stackPushFirst(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    if(root == null) return res;

    Stack < TreeNode > stack = new Stack < > ();
    TreeNode curr;
    stack.push(root);
    while (!stack.isEmpty()) {
      curr = stack.peek();
      stack.pop();
      res.add(curr.val);
      if(curr.right != null) stack.push(curr.right);
      if(curr.left != null) stack.push(curr.left);
    }

    return res;
  }

  boolean isSameValNode(TreeNode p, TreeNode q) {
    if(p == null && q == null) return true;
    if(p == null || q == null || p.val != q.val) return false;
    return true;
  }

  // level order is also BFS, so using queue,
  public boolean isSameTree_100LevelOrder(TreeNode p, TreeNode q) {
    if(p == null && q == null) return true;
    // java do not allow null in ArrayDeque as queue or stack, so we need to handle first
    // this is why we need isSameValNode method!!!, then we can avoid using if(p.left != null)
    // you can add null to ArrayList, Stack and LinkedList
    if(!isSameValNode(p, q)) return false;
    ArrayDeque<TreeNode> que = new ArrayDeque<>();

    // addFirst and push are same, push used when deque is used as STACK
    que.addLast(p);
    que.addLast(q);
    while(!que.isEmpty()) {
      p = que.removeFirst();
      q = que.removeFirst();
      // here p an q should be not-null, but we can still use that method
      if(!isSameValNode(p,q)) return false;
      if(!isSameValNode(p.left, q.left)) return false;
      if(!isSameValNode(p.right, q.right)) return false;
      // now p or q should not be null anymore
      if(p.left != null) {
        // make sure push back p.left first
        que.addLast(p.left);
        que.addLast(q.left);
      }
      if(p.right != null) {
        que.addLast(p.right);
        que.addLast(q.right);
      }
    }

    return true;
  }

  // stack in java: Stack, queue in java: LinkedList, Linkedlist can also be used as stack
  // Linklist.add/addlast will add to the last, poll will get first to behave Queue, polllast will get last to be Stack
  // ArrayDequeue can be used for both Stack and Queue, and it might be faster than Stack and LinkedList
  public boolean isSymmetric_101LevelOrder(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    // small trick here, add root twice.
    q.add(root);
    q.add(root);
    while (!q.isEmpty()) {
      TreeNode t1 = q.poll();
      TreeNode t2 = q.poll();
      if (t1 == null && t2 == null) continue;
      if (t1 == null || t2 == null) return false;
      if (t1.val != t2.val) return false;
      q.add(t1.left);
      q.add(t2.right);
      q.add(t1.right);
      q.add(t2.left);
    }
    return true;
  }

  // stack is approach to fulfill recursion while queue is for level order
  public int maxDepth_104stack(TreeNode root) {
    LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
    if (root == null) return 0;
    stack.add(new Pair<>(root, 1));

    int depth = 0, current_depth = 0;
    while(!stack.isEmpty()) {
      Pair<TreeNode, Integer> cur = stack.pollLast();
      root = cur.getKey();
      current_depth = cur.getValue();
      if (root != null) {
        depth = Math.max(depth, current_depth);
        if(root.left != null) stack.add(new Pair<>(root.left, current_depth + 1));
        if(root.right != null) stack.add(new Pair<>(root.right, current_depth + 1));
      }
    }
    return depth;
  }

  public int minDepth_111LeverOrder(TreeNode root) {
    LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
    if (root == null) {
      return 0;
    }
    else {
      queue.add(new Pair<>(root, 1));
    }

    int current_depth = 0;
    while (!queue.isEmpty()) {
      Pair<TreeNode, Integer> current = queue.poll();
      root = current.getKey();
      current_depth = current.getValue();
      if ((root.left == null) && (root.right == null)) {
        // find the leaf node(not null), then break out of while loop, then it is the min and no need to use min comparison
        break;
      }
      if (root.left != null) {
        queue.add(new Pair<>(root.left, current_depth + 1));
      }
      if (root.right != null) {
        queue.add(new Pair<>(root.right, current_depth + 1));
      }
    }
    return current_depth;
  }

  public List<List<Integer>> levelOrder_102Que(TreeNode root) {
    List<List<Integer>> levels = new ArrayList<List<Integer>>();
    if (root == null) return levels;

    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(root);
    int level = 0;
    while ( !queue.isEmpty() ) {
      // start the current level
      levels.add(new ArrayList<Integer>());

      // number of elements in the current level
      int level_length = queue.size();
      for(int i = 0; i < level_length; ++i) {
        // in Linkedlist, remove will throw if empty while poll will return null
        TreeNode node = queue.remove();

        // fulfill the current level
        levels.get(level).add(node.val);

        // add child nodes of the current level
        // in the queue for the next level
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
      }
      // go to next level
      level++;
    }
    return levels;
  }

  public List<List<Integer>> levelOrderBottom107Que(TreeNode root) {
    List<List<Integer>> levels = new ArrayList<>();
    if (root == null) return levels;
    levels = levelOrder_102Que(root);

    Collections.reverse(levels);
    return levels;
  }

  Map<TreeNode, TreeNode> parentMap;
  // observe the pattern from a tree, problem is similar to level order traversal when you think target is the root, the tree is a graph
  public List<Integer> distanceK863(TreeNode root, TreeNode target, int K) {
    parentMap = new HashMap<>();
    dfs(root, null);

    Queue<TreeNode> queue = new LinkedList<>();
    Set<TreeNode> visited = new HashSet<>();
    queue.add(target);
    visited.add(target);

    int dist = 0;
    while (!queue.isEmpty()) {
      if(dist == K) {
        List<Integer> ans = new ArrayList<>();
        for (TreeNode n: queue)
          ans.add(n.val);
        return ans;
      }
      // make sure do this, queue size is changing!!!!
      int curSize = queue.size();
      for(int i=0; i<curSize; ++i) {
        TreeNode node = queue.poll();
        // make sure not adding null!!!!
        if (!visited.contains(node.left) && node.left != null) {
          visited.add(node.left); queue.offer(node.left); }
        if (!visited.contains(node.right)&& node.right != null) {
          visited.add(node.right); queue.offer(node.right); }
        TreeNode parent = parentMap.get(node);
        if (!visited.contains(parent) && parent!=null) {
          visited.add(parent); queue.offer(parent); }
      }
      dist++;
    }

    return new ArrayList<Integer>();
  }

  public void dfs(TreeNode node, TreeNode parent) {
    if (node != null) {
      parentMap.put(node, parent);
      dfs(node.left, node);
      dfs(node.right, node);
    }
  }

  public List<Integer> largestValuesEachRow515(TreeNode root) {
    List<Integer> res = new ArrayList<>() ;
    if(root==null)return res ;
    Queue<TreeNode> q = new LinkedList<>() ;
    res.add(root.val) ;

    while(!q.isEmpty()){
      int size = q.size() ;
      int max = Integer.MIN_VALUE;
      for(int i=0 ; i<size ;i++){
        //remove will throw exception while poll will return null
        TreeNode curr = q.remove() ;
        if(curr.val>=max){ max = curr.val;}

        if(curr.left!=null){ q.add(curr.left) ; }
        if(curr.right!=null){ q.add(curr.right) ; }
      }
        res.add(max) ;
    }

    return res ;
  }

  public int maxLevelSum1161LevelOrder(TreeNode root) {
    int currLevel = 1, maxLevel = 1;
    int maxSum = root.val, currSum = 0;
    LinkedList<TreeNode> queue = new LinkedList<>();
    TreeNode x;
    queue.addLast(root);

    while (!queue.isEmpty()) {
      int curSize = queue.size();
      for(int i=0; i<curSize; ++i) {
        x = queue.removeFirst();
        currSum += x.val;
        if (x.left != null) queue.addLast(x.left);
        if (x.right != null) queue.addLast(x.right);
      }
      if (currSum > maxSum) {
        maxSum = currSum;
        maxLevel = currLevel;
      }
        currSum = 0;
        currLevel++;
    }

    return maxLevel;
  }

  public List <Double> averageOfLevels637(TreeNode root) {
    List <Double> res = new ArrayList<>();
    Queue <TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      long sum = 0, count = 0;
      int size = queue.size();
      for (int i = 0; i < size; ++i) {
        TreeNode node = queue.remove();
        sum += node.val;
        count++;
        if (node.left != null)
          queue.add(node.left);
        if (node.right != null)
          queue.add(node.right);
      }
      res.add(sum * 1.0 / count);
    }

    return res;
  }

  public boolean isCompleteTree958(TreeNode root) {
    if(root==null) return true;

    boolean lastLevel=false;
    Queue<TreeNode> q=new LinkedList<>();
    q.add(root);

    while(q.size()>0){
      TreeNode temp=q.poll();

      if(temp==null) lastLevel=true;//state transitted
      else{
        if(lastLevel) return false;
        q.add(temp.left);
        q.add(temp.right);
      }
    }
    return true;
  }

  //level order + hashtable
  public List<List<Integer>> verticalTraversal987(TreeNode root) {
    List<List<Integer>> output = new ArrayList<>();
    if (root == null) { return output; }

    // step 1). BFS traversal
    Map<Integer, ArrayList<Pair<Integer, Integer>>> columnTable = new HashMap<>();
    int minColumn = 0, maxColumn = 0;
    // tuples of <column, <row, value>>
    Queue<Pair<TreeNode, Pair<Integer, Integer>>> queue = new ArrayDeque<>();
    int row = 0, column = 0;
    queue.offer(new Pair<>(root, new Pair<>(row, column)));

    while (!queue.isEmpty()) {
      Pair<TreeNode, Pair<Integer, Integer>> p = queue.poll();
      root = p.getKey();
      row = p.getValue().getKey();
      column = p.getValue().getValue();

      if (root != null) {
        columnTable.putIfAbsent(column, new ArrayList<>());
        columnTable.get(column).add(new Pair<>(row, root.val));
        minColumn = Math.min(minColumn, column);
        maxColumn = Math.max(maxColumn, column);

        queue.offer(new Pair<>(root.left, new Pair<>(row + 1, column - 1)));
        queue.offer(new Pair<>(root.right, new Pair<>(row + 1, column + 1)));
      }
    }

//    Comparator<Pair<Integer, Integer>> myCompare = new Comparator<>() {
//      @Override
//      public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
//        if (p1.getKey().equals(p2.getKey()))
//          return p1.getValue() - p2.getValue();
//        else
//          return p1.getKey() - p2.getKey();
//      }
//    };
    // step 2). retrieve the value from the columnTable, from minClo to maxCol, so it is sorted
    for (int i = minColumn; i <= maxColumn; ++i) {
      // order by both "row" and "value", node 5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
      Collections.sort(columnTable.get(i),
          (p1, p2) -> (p1.getKey().equals(p2.getKey()) ? p1.getValue()- p2.getValue(): p1.getKey() - p2.getKey())
      );

      List<Integer> sortedColumn = new ArrayList<>();
      for (Pair<Integer, Integer> p : columnTable.get(i)) {
        sortedColumn.add(p.getValue());
      }
      output.add(sortedColumn);
    }

    return output;
  }
}
