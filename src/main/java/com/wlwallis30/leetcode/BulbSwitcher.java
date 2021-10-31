package com.wlwallis30.leetcode;

public class BulbSwitcher {
  public int bulbSwitch319(int n) {
    int res=1;
    while(res<=n/res)
      res++;
    return res-1;
  }
}
