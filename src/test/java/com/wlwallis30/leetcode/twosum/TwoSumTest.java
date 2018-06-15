package com.wlwallis30.leetcode.twosum;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class TwoSumTest {
  private TwoSum twoSum;
  private Boolean debug;

  @Before
  public void setUp() throws Exception {
    this.twoSum = new TwoSum();
    this.debug = true;
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
      System.out.print( "--------debug-------" + res.toString() + "\n");
      System.out.print( "--------debug-------" + expected.toString() + "\n");
    }
    assertTrue("should be [0, 1]", Arrays.deepEquals(expectedObj, resObj));
  }

  @Test
  public void threeSum_15() throws Exception {
  }

}