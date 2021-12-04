package com.wlwallis30.leetcode;

public class BulbSwitcher {
  //n=36 第36个灯泡，它的因数有(1,36), (2,18), (3,12), (4,9), (6,6), 可以看到前四个括号里成对出现的因数各不相同，
  // 括号中前面的数改变了灯泡状态，后面的数又变回去了，状态没变化，(6,6)，所以灯泡就一直是点亮状态的. 简化为了求1到n之间完全平方数的个数
  public int bulbSwitch319(int n) {
    int res=1;
    // res*res<=n
    while(res<=n/res) res++;
    return res-1;
  }
}
