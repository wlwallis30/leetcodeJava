package com.wlwallis30.leetcode;

public class GasStation {
  // If there exists a solution, it is guaranteed to be unique from the problem
  //greedy: resource distribution, it means it only exist one station starting from there you keep gain enough gas to go clockwise
  //gas = [1,2,3,4,5], cost = [3,4,5,1,2], @index 4, your gain is also 3, but you just gained here, you missed the previous gain @index 3. @index 2, cost is so high!
  // 1. only when totalGain >= 0, then you can finish a circle from some station
  // 2. which station, then you need another gain to be updated and find the starting station
  int canCompleteCircuit134(int[] gas, int[] cost) {
    int res =0;
    int totalGasGain = 0, tmpGasGain =0;
    for(int i=0; i<gas.length;++i) {
      totalGasGain += gas[i]-cost[i];
      tmpGasGain += gas[i]-cost[i];
      if(tmpGasGain<0) {
        // current station not working, try next one!, no matter which station you start, totalGasGain formula will be same!
        res = i+1;
        tmpGasGain=0;
      }
    }

    if(totalGasGain<0) return -1;
    return res;
  }
}
