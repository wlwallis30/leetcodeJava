package com.wlwallis30.leetcode;

import java.util.*;

public class CombinationSum {
  List<List<Integer>> combinationSum39(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> out = new ArrayList<>();
    int start = 0;
    CSDFS(res, start, out, candidates, target);
    return res;
  }

  // backtracking, from problem: The same repeated number may be chosen from candidates unlimited number of times.
  void CSDFS(List<List<Integer>> res, Integer start, List<Integer> out, int[] candidates, int target)
  {
    if(target < 0) return;
    // you need to do this new since add will just pass-by-value the reference of out, but in the end out will bill []
    // in c++, vector.pushabck(out) should just add the out "value"
    // also refer to 46, 47
    if(target ==0) res.add(new ArrayList<>(out));
    else
    {
      // distinct num and we can use one num for many times, so CSDFS(res, i,...)
      for(int i=start; i< candidates.length;++i)
      {
        out.add(candidates[i]);
        //Input: candidates = [2,3,5], target = 8 Output: [[2,2,2,2],[2,3,3],[3,5], repeat allowed, so i is next
        CSDFS(res, i, out, candidates, target-candidates[i]);
        out.remove(out.size()-1);
      }
    }
  }

  List<List<Integer>> combinationSum40(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> out = new LinkedList<>();
    int start = 0;
    Arrays.sort(candidates);
    backtrackIdxSkip(res, start, out, candidates, target);
    return res;
  }

  // backtracking, from problem: Each number in candidates may only be used once in the combination.
  private void backtrackIdxSkip(List<List<Integer>> res, Integer start, LinkedList<Integer> out, int[] candidates, int target)
  {
    if(target < 0) return;
    // you need to do this new since add will just pass-by-value the reference of out, but in the end out will bill []
    // in c++, vector.pushabck(out) should just add the out "value"
    // also refer to 46, 47
    if(target ==0) res.add(new ArrayList<>(out));
    else
    {
      for(int i=start; i< candidates.length;++i)
      {
        //candidates = [2,5,2,1,2], target = 5, one ans is [1,2,2], to avoid used twice for 2s, need to skip coz we will do it in dfs(res, i+1)
        // num might have dup, but we can use one num only once so...backtrackIdxSkip(res, i+1..)
        if(i>start && candidates[i] == candidates[i-1]) continue;
        out.add(candidates[i]);
        backtrackIdxSkip(res, i+1, out, candidates, target-candidates[i]);
        out.removeLast();
      }
    }
  }

  List<List<Integer>> combinationSum216(int k, int n) {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> out = new LinkedList<>();
    int startNum = 1;
    CSDFS3(res, startNum, out, k, n);
    return res;
  }

  // backtracking, Each number is used at most once.
  void CSDFS3(List<List<Integer>> res, Integer startNum, LinkedList<Integer> out, int k, int target)
  {
    if(out.size() ==k &&  target ==0) res.add(new ArrayList<>(out));
    else
    {
      for(int i=startNum; i< 10; ++i)
      {
        out.add(i);
        //Input: candidates = [2,3,5], target = 8 Output: [[2,2,2,2],[2,3,3],[3,5], repeat allowed, so i is next
        CSDFS3(res, i+1, out, k, target-i);
        out.removeLast();
      }
    }
  }

  List<List<Integer>> factorCombination254(int n) {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> out = new LinkedList<>();
    factorDFS(res, 2, out, n);
    return res;
  }

  // backtracking
  void factorDFS(List<List<Integer>> res, Integer startFactor, LinkedList<Integer> out, int curNum)
  {
    // size > 1 to avoid n itself
    if(curNum == 1 && out.size()>1) res.add(new ArrayList<>(out));
    else
    {
      // need to be <=, since curNum is previous num/i
      for(int i=startFactor; i<=curNum; ++i)
      {
        if(curNum%i == 0) {
          out.add(i);
          factorDFS(res, i, out, curNum/i);
          out.removeLast();
        }
      }
    }
  }

  // combination nums.  O( k * (n,k) ) since appending/adding combination will run k times for each combination
  public List<List<Integer>> combine77(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> out = new LinkedList<>();
    int curNum = 1;
    cDFS(res, out, curNum, n, k);
    return res;
  }

  //backtrack
  void cDFS(List<List<Integer>> res, LinkedList<Integer> out, int curNum, int n, int k)
  {
    if(out.size() ==k) res.add(new ArrayList<>(out));
    else {
      for(int i = curNum; i<= n; ++i) {
        out.add(i);
        cDFS(res, out,i+1, n, k);
        out.removeLast();
      }
    }
  }
}
