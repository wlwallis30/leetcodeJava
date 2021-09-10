package com.wlwallis30.leetcode;

import java.util.*;

public class CombinationSum {
  List<List<Integer>> combinationSum39(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> out = new LinkedList<>();
    int start = 0;
    CSDFS(res, start, out, candidates, target);
    return res;
  }

  // backtracking
  void CSDFS(List<List<Integer>> res, Integer start, LinkedList<Integer> out, int[] candidates, int target)
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
        out.add(candidates[i]);
        //Input: candidates = [2,3,5], target = 8 Output: [[2,2,2,2],[2,3,3],[3,5], repeat allowed, so i is next
        CSDFS(res, i, out, candidates, target-candidates[i]);
        out.removeLast();
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

  // backtracking
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

  // backtracking
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
}
