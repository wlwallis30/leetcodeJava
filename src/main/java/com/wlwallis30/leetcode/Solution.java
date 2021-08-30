/*
 * Created by larrywang in 2018
 * Copyright (c) https://github.com/wlwallis30/ 2018. All rights reserved.
 * Last modified on 6/15/18 1:28 AM
 */

package com.wlwallis30.leetcode;
public class Solution {
 static void swap(char[] str, int i, int j) {
  char temp = str[i];
  str[i] = str[j];
  str[j] = temp;
 }

 static void swap(int[] nums, int i, int j) {
  int temp = nums[i];
  nums[i] = nums[j];
  nums[j] = temp;
 }

 static void reverse(int[] nums, int start) {
  int i = start, j = nums.length - 1;
  while (i < j) {
   swap(nums, i, j);
   i++;
   j--;
  }
 }
}

/*
 *Similar problem catalogs: total  53
 * Two sum 1: 15, 16, 18, 167, 170, 259.......total: 7
 * Add two numbers 2: 67, 43, 66, 369, 371.... total: 6
 * Length of longest sub string 3: 159, 239, 340.....total: 4
 * Find median for two sorted arrays 4........total: 1
 * Longest palindrome substring 5: 266, 267, 9, 125, 131, 234......total: 7
 * Reverse integer 7: 8  .....total: 2
 * reverse bits 190: 191, 231, 338....total: 4
 * most water area 11    ....total: 1
 * roman to int 13:    ........total: 1
 * common longest prefix substring 14:  ...total: 1
 * valid parenthesis 20: 22   .....total: 2
 * remove nth node from end 19: 1721    ....total: 2
 * merge two sorted linklist 21: 88    ....total: 2
 * remove dup from sorted array in place 26: 27, 203, 283    ....total: 4
 * substring pattern strStr 28:      ....total: 1
 * swap two nodes in pair 24:    ....total: 1
 * search insert position 35: 278    ....total: 2
 * next pemutation 31: 46, 47     total: 3
 * max sub array 53: 121     ....total: 2
 */
// single-linked list where java LinkedList is a double linked list via Deque
class ListNode {
 int val;
 ListNode next;
 ListNode(int x) { val = x; }
 }

 