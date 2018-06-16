/*
 *  Created by larrywang in 2018
 *  Copyright (c) https://github.com/wlwallis30/ 2018. All rights reserved.
 *  Last modified on 6/16/18 12:55 AM
 */

package com.wlwallis30.leetcode.twosum;

import static org.junit.Assert.*;

import java.util.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class TwoSumTest {
  private TwoSum twoSum;
  private Boolean debug;
  private String debugStr;

  @Before
  public void setUp() throws Exception {
    this.twoSum = new TwoSum();
    this.debug = true;
    this.debugStr = "--------debug-------";
    System.out.println("Init before all...");
  }

  @After
  public void tearDown() throws Exception {
    System.out.println("Tearing down after all");
  }

  @Test
  public void twoSum_1() throws Exception {
    int[] nums = { 2, 7, 11, 15};
    int target = 9;
    int[] res = twoSum.twoSum_1(nums, target);
    int[] expected = {0, 1};
    Object[] expectedObj = {expected};
    Object[] resObj = {res};
    if (this.debug) {
      System.out.print(this.debugStr + res.toString() + "\n");
      System.out.print(this.debugStr + expected.toString() + "\n");
    }
    assertTrue("should be [0, 1]", Arrays.deepEquals(expectedObj, resObj));
  }

  @Test
  public void threeSum_15() throws Exception {
    int[] nums = {-1, 0, 1, 2, -1, -4};
    List<List<Integer>> res = twoSum.threeSum_15(nums);
    List<List<Integer>> expected = new LinkedList<>();
    expected.add(Arrays.asList(-1, -1, 2));
    expected.add(Arrays.asList(-1, 0, 1));
    if (this.debug) {
      System.out.print(this.debugStr + res.toString());
    }
    assertTrue("not equal, expected is: " + expected.toString(),
        expected.equals(res));
  }

}