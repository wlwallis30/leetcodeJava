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
        CSDFS(res, i, out, candidates, target-candidates[i]);
        out.removeLast();
      }
    }
  }
}
