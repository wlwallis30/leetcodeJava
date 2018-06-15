/*
 * Created by larrywang in 2018
 * Copyright (c) https://github.com/wlwallis30/ 2018. All rights reserved.
 * Last modified on 6/15/18 3:15 AM
 */
package com.wlwallis30.leetcode.twosum;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import java.lang.reflect.Array;
import org.junit.jupiter.api.*;
import java.util.Arrays;

@DisplayName("Testing Two sum tests using JUnit 5")
class TwoSumTests {
  private TwoSum twoSum;
  private Boolean debug;

  TwoSumTests() {
    this.twoSum = new TwoSum();
    this.debug = true;
  }

  @BeforeAll
  static void init() {
    System.out.println("Init before all...");
  }

  @BeforeEach
  void setupB4Each() {
    System.out.println("Setup before each test...");
  }

  @AfterEach
  void tearDownAfterEach() {
    System.out.println("Tearing down after each test");
  }

  @AfterAll
  static void tearDown() {
    System.out.println("Tearing down after all");
  }

  @Test
  @DisplayName("Two sum test in leetcode @1")
  void testTwoSum_1() {
    int[] nums = { 2, 7, 11, 15};
    int target = 9;
    int[] res = twoSum.twoSum_1(nums, target);
    int[] expected = {0, 1};
    Object[] expectedObj = {expected};
    Object[] resObj = {res};
    if (debug) {
      System.out.print( "--------debug-------" + res.toString() + "\n");
      System.out.print( "--------debug-------" + expected.toString() + "\n");
    }
    assertTrue("should be [0, 1]", Arrays.deepEquals(expectedObj, resObj));
  }

  @Test
  @DisplayName("Two sum test in leetcode @15")
  void testThreeSum_15() {
  }
}