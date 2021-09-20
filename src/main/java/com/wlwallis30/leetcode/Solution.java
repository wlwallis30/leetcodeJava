/*
 * Created by larrywang in 2018
 * Copyright (c) https://github.com/wlwallis30/ 2018. All rights reserved.
 * Last modified on 6/15/18 1:28 AM
 */

package com.wlwallis30.leetcode;

import com.sun.istack.internal.NotNull;

/*
 * Two sum 1: 15, 16, 18, 167, 170, 259.......total: 7
 * Add two numbers 2: 67, 43, 66, 369, 371.... total: 6
 * Length of longest sub string 3: 159, 239, 340, 128, 298.....total: 6
 * Find median for two sorted arrays 4........total: 1
 * Longest palindrome substring 5: 266, 267, 9, 125, 131, 234......total: 7
 * Reverse integer 7: 8  .....total: 2
 * reverse bits 190: 191, 231, 338....total: 4
 * most water area 11    ....total: 1
 * roman to int 13:    ........total: 1
 * common longest prefix substring 14:  ...total: 1
 * valid parenthesis 20: 22, 17   .....total: 3
 * remove nth node from end 19: 1721    ....total: 2
 * merge two sorted linklist 21: 88, 148, 143, 147   ....total: 5
 * remove dup from sorted array in place 26: 27, 203, 283, 80    ....total: 5
 * substring pattern strStr 28:      ....total: 1
 * swap two nodes in pair 24:    ....total: 1
 * search insert position 35: 278, 34    ....total: 3
 * next pemutation 31: 46, 47     total: 3
 * max sub array 53: 121, 122, 309, 152, 198, 213, 238,256, 276    ....total: 10
 * length of last word 58:      total: 1
 * sqrt of x 69: 367, 50,372     total: 4
 * climb chairs 70:     total: 1
 * binary tree inorder traversal 94: 144, 145,100,101, 104,110,111,102, 107   total:  10
 * convert sorted array to BST 108: 109   total: 2
 * binary tree path sum 112:257, 113, 129    total: 4
 * pascal triangle 118:119, 120    total: 3
 * single num 136: 137, 268, 260, 89     total: 5
 * linked list cycle 141: 142, 202  total: 3
 * add digits 258: (202) 263, 204, 29   total: 4
 * word pattern 205: 290   total: 2
 * reverse linkedlist 206:(234Iterative), 92,160, 156     total: 4
 * min stack 155: 716    total: 2
 * read n chars via read4 157:    total: 1
 * missing range 163: 228   total: 2
 * majority element 169:    total: 1
 * excel column sheet 168: 171    total: 2
 * factorial trailing zeros 172:      total:1
 * meeting room 252:     total: 1
 * closest int in BST 270:  total: 1
 * nim game 292:     total: 1
 * search in rotated sorted array 33: 81, 153, 162     total: 4
 * valid sudoku 36:    total: 1
 * count and say 38:271    total: 2
 * combination sum 39: 40, (17, 22), 216, 254, 77 total: 5
 * rotate image 48:     total: 1
 * valid anagram 49: 272    total: 2
 * jump game 55: 45    total: 2
 * spiral matrix 54: 59   total: 2
 * rotate linkedlist by k nodes 61: 189    total:2
 * unique path 62: 63, 64     total: 3
 * search in 2D matrix 74: 240    total: 2
 * set matrix to zero 73: 289    total: 2
 * sort colors 75: (148)    total: 1
 * simplify dir path 71:    total: 1
 * word search 79:    total: 1
 * subset 78: 90    total: 2
 * remove dup from sorted linkedlist 83: 82   total: 2
 * partition list 86:    total: 1
 * decode way 91:   total: 1
 * unique BST 96: 95, 98, 99    total: 4
 * retore IP address 93:    total: 1
 * build BT from inorder and preorder 105: 106   total: 2
 * flatten BT to linkedlist 114:    total:1
 * connect next pointers for perfect BT 116: 117    total: 2
 * surrounded region 130: 200, 286, 323, 261   total:5
 * clone graph 133:  (323), (261), 138    total: 2
 * gas station 134:     total:1
 * word break 139:    total: 1
 * LRU cache 146: 460, 588   total: 3
 * reverse words 151: 186    total: 2
 * evaluate reversed poland notation 150:    total: 1
 * one edit distance 161:   total: 1
 * compare two versions 165:    total: 1
 * BST iterator 173: 251, 281, 284, 341    total: 5
 * zig zag string 6: 103     total: 2
 * largest num 179:   total: 1
 *********************Similar problem catalogs: total: 197
 */
// single-linked list where java LinkedList is a double linked list via Deque

public class Solution {
  static void swap(@NotNull char[] str, int i, int j) {
    char temp = str[i];
    str[i] = str[j];
    str[j] = temp;
  }

  static void swap(@NotNull int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  // calling this will not swap due to pass-by-value for object ref
//  static void swap(String str1, String str2) {
//    String tmp = str1;
//    str1 = str2;
//    str2 = tmp;
//  }
  // Strings are constant, String name="SO"; name="SE";
  // name="SE" by doing this, you are changing the value of name variable, not the String object SO itself. String are immutable in the sense, String object SO can't be modified
  //https://stackoverflow.com/questions/20396767/string-confusion-in-java

  static void reverse(@NotNull int[] nums, int start) {
    int i = start, j = nums.length - 1;
    while (i < j) {
      swap(nums, i, j);
      i++;
      j--;
    }
  }
  static void reverse(int[] nums, int start, int end) {
    while (start < end) {
      int temp = nums[start];
      nums[start] = nums[end];
      nums[end] = temp;
      start++;
      end--;
    }
  }
}

class ListNode {
 int val;
 ListNode next;
 ListNode(int x) { val = x; }
 }

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
  this.val = val;
  this.left = left;
  this.right = right;
  }
}
