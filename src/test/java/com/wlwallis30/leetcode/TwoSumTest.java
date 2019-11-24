/*
 *  Created by larrywang in 2018
 *  Copyright (c) https://github.com/wlwallis30/ 2018. All rights reserved.
 *  Last modified on 6/16/18 12:55 AM
 */

package com.wlwallis30.leetcode;

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
  private String resultStr;
  private String expectedStr;
  private String expectedNotEqual;
  private String expectedIntStr;

  @Before
  public void setUp() throws Exception {
    this.twoSum = new TwoSum();
    this.debug = true;
    this.resultStr = "result is: ";
    this.expectedStr = "expected is: ";
    this.expectedNotEqual = "not equal, expected is: ";
    this.expectedIntStr = "not equal, expected integer is: %d";
    System.out.println("Init before each test...");
  }

  @After
  public void tearDown() throws Exception {
    System.out.println("Done after each test");
  }

  private void printQuestionInfo (int methodIdxInClass) {
    if(this.debug) {
      System.out.println("----Now Testing----"
          + TwoSum.class.getMethods()[methodIdxInClass].getName());
    }
  }

  private void debugPrint(String message) {
    if(this.debug) {
      System.out.println(message);
    }
  }

  private void debugPrint(int num) {
    if(this.debug) {
      System.out.println(String.format("integer is %d", num));
    }
  }

  private void debugPrint(Object object) {
    if(this.debug) {
      System.out.print(object);
    }
  }

  @Test
  public void testTwoSum_1() {
    int[] nums = { 2, 7, 11, 15};
    int target = 9;
    int[] res = twoSum.twoSum_1(nums, target);
    int[] expected = {0, 1};
    Object[] expectedObj = {expected};
    Object[] resObj = {res};

    this.printQuestionInfo(0);
    this.debugPrint(this.expectedStr + Arrays.toString(expected));
    this.debugPrint(this.resultStr + Arrays.toString(res));
    assertTrue("should be [0, 1]", Arrays.deepEquals(expectedObj, resObj));
  }

  @Test
  public void testThreeSum_15() {
    int[] nums = {-1, 0, 1, 2, -1, -4};
    List<List<Integer>> res = twoSum.threeSum_15(nums);
    List<List<Integer>> expected = new LinkedList<>();
    expected.add(Arrays.asList(-1, -1, 2));
    expected.add(Arrays.asList(-1, 0, 1));

    this.printQuestionInfo(1);
    this.debugPrint(this.expectedStr + expected.toString());
    this.debugPrint(this.resultStr + res.toString());
    assertTrue(this.expectedNotEqual + expected.toString(),
        expected.equals(res));
  }

  @Test
  public void testThreeSumClosest_16() {
    int[] nums = new int[]{-1, 2, 1, -4};
    int target = 1;
    int res = twoSum.threeSum_16(nums, target);
    int expected = 2;

    this.printQuestionInfo(2);
    this.debugPrint(this.expectedStr + expected);
    this.debugPrint(this.resultStr + res);
    assertTrue(String.format(this.expectedIntStr, expected), res == expected);
    nums = new int[]{-3,-2,-5,3,-4};
    target = -1;
    expected = -2;
    res = twoSum.threeSum_16(nums, target);
    this.debugPrint(this.expectedStr + expected);
    this.debugPrint(this.resultStr + res);
    assertTrue(String.format(this.expectedIntStr, expected), res == expected);
  }
  
  @Test
  public void testThreeSumSmaller_259() {
	  int[] nums = new int[] {-2, 0, 1, 3};
	  int target = 2;
	  int res = twoSum.threeSum_259(nums, target);
	  int expected = 2;
	  this.printQuestionInfo(5);
	  this.debugPrint(this.expectedStr + expected);
	  this.debugPrint(this.resultStr + res);
	  assertTrue(String.format(this.expectedIntStr,  expected), res == expected);
  }

  @Test
  public void testFourSum_18() {
    int[] nums = {-1, 0, 1, 2, -2, 0};
    int target = 0;
    List<List<Integer>> res = twoSum.fourSum_18(nums, target);
    List<List<Integer>> expected = new LinkedList<>();
    expected.add(Arrays.asList(-2, -1, 1, 2));
    expected.add(Arrays.asList(-2,  0, 0, 2));
    expected.add(Arrays.asList(-1,  0, 0, 1));

    this.printQuestionInfo(3);
    this.debugPrint(this.expectedStr + expected.toString());
    this.debugPrint(this.resultStr + res.toString());
    assertTrue(this.expectedNotEqual + expected.toString(),
        expected.equals(res));

    nums = new int[]{0, 0, 0, 0};
    target = 1;
    res = twoSum.fourSum_18(nums, target);
    expected.clear();
    assertTrue(this.expectedNotEqual + expected.toString(),
        expected.equals(res));

    nums = new int[]{-1,-5,-5,-3,2,5,0,4};
    target = -7;
    expected.clear();
    expected.add(Arrays.asList(-5,-5,-1,4));
    expected.add(Arrays.asList(-5,-3,-1,2));
    res = twoSum.fourSum_18(nums, target);
    this.debugPrint(this.expectedStr + expected);
    this.debugPrint(this.resultStr + res);
    assertTrue(this.expectedNotEqual + expected.toString(),
        expected.equals(res));
  }

  @Test
  public void testTwoSum_167() {
    int[] nums = {2, 7, 11, 15};
    int target = 9;
    int[] res = twoSum.twoSumSorted_167(nums, target);
    int[] expected = {1, 2};
    Object[] expectedObj = {expected};
    Object[] resObj = {res};

    this.printQuestionInfo(4);
    this.debugPrint(this.expectedStr + Arrays.toString(expected));
    this.debugPrint(this.resultStr + Arrays.toString(res));
    assertTrue("should be [0, 1]", Arrays.deepEquals(expectedObj, resObj));
  }
}