/*
 * Created by larrywang in 2018
 * Copyright (c) https://github.com/wlwallis30/ 2018. All rights reserved.
 * Last modified on 6/15/18 1:28 AM
 */

package com.wlwallis30.leetcode;

//import com.sun.istack.internal.NotNull;

/*
 * Two sum 1: 15, 16, 18, 167, 170, 259, 560, 523, 528, 303, 304, 724.......total: 13
 * Add two numbers 2: 67, 43, 66, 369, 371, 415.... total: 7
 * Length of longest sub string 3: 159, 239, 340, 128, 298, 485, 487, 1004, 424, 658, 76.....total: 12
 * Find median for two sorted arrays 4........total: 1
 * Longest palindrome substring 5: 266, 267, 9, 125, 131, 234, 680, 647, 1216......total: 10
 * Reverse integer 7: 8, 65  .....total: 3
 * reverse bits 190: 191, 231, 338....total: 4
 * most water area 11: 42    ....total: 2
 * roman to int 13:    ........total: 1
 * common longest prefix substring 14:  ...total: 1
 * valid parenthesis 20: 22, 17, 1249, 921, 1541,301   .....total: 7
 * remove nth node from end 19: 1721    ....total: 2
 * merge two sorted linklist 21: 88, 148, 143, 147, 708, 23   ....total: 7
 * remove dup from sorted array in place 26: 27, 203, 283, 80, 1047, 1209, 977    ....total: 8
 * substring pattern strStr 28:      ....total: 1
 * swap two nodes in pair 24:    ....total: 1
 * search insert position 35: 278, 34    ....total: 3
 * next pemutation 31: 46, 47     total: 3
 * max sub array 53: 121, 122, 309, 152, 198, 213, 238,256, 276    ....total: 10
 * length of last word 58:      total: 1
 * sqrt of x 69: 367, 50,372     total: 4
 * climb chairs 70:     total: 1
 * binary tree inorder traversal 94: 144, 145,100,101, 104,110,111,102, 107, 543, 863, 1382, 515, 1522, 1161, 637, 958, 865(1123), 987  total:  21
 * convert sorted array to BST 108: 109   total: 2
 * binary tree path sum 112:257, 113, 129, 536, 124    total: 6
 * pascal triangle 118:119, 120    total: 3
 * single num 136: 137, 268, 260, 89, 201, 477     total: 7
 * linked list cycle 141: 142, 202  total: 3
 * add digits 258: (202) 263, 204, 29   total: 4
 * word pattern 205: 290, 1554  total: 3
 * reverse linkedlist 206:(234Iterative), 92,160, 156     total: 4
 * min stack 155: 716    total: 2
 * read n chars via read4 157:    total: 1
 * missing range 163: 228, 1539, 1060   total: 4
 * majority element 169:    total: 1
 * excel column sheet 168: 171    total: 2
 * factorial trailing zeros 172:      total:1
 * meeting room 252: 56, 57, 253, 616, 986     total: 6
 * closest int in BST 270:426, 938  total: 3
 * nim game 292:     total: 1
 * search in rotated sorted array 33: 81, 153, 162     total: 4
 * valid sudoku 36:    total: 1
 * count and say 38:271, 443    total: 3
 * combination sum 39: 40, (17, 22), 216, 254, 77 total: 5
 * rotate image 48:     total: 1
 * valid anagram 49: 272, 249    total: 3
 * jump game 55: 45    total: 2
 * spiral matrix 54: 59   total: 2
 * rotate linkedlist by k nodes 61: 189    total:2
 * unique path 62: 63, 64     total: 3
 * search in 2D matrix 74: 240, 1428, 1091, 1559, 935, 1102    total: 7
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
 * connect next pointers for perfect BT 116: 117, 199, 314    total: 4
 * surrounded region 130: 200, 286, 323, 261, 463, 695, 934, 694, 827, 317, 296, 1263  total: 13
 * clone graph 133:  (323), (261), 138    total: 2
 * gas station 134:     total:1
 * word break 139: 140    total: 2
 * LRU cache 146: 460, 588   total: 3
 * reverse words 151: 186    total: 2
 * evaluate reversed poland notation 150: 227, 282, 224    total: 4
 * one edit distance 161:   total: 1
 * compare two versions 165:    total: 1
 * BST iterator 173: 251, 281, 284, 341    total: 5
 * zig zag string 6: 103     total: 2
 * largest num 179:   total: 1
 * repeated DNA 187:    total: 1
 * fraction to decimal string 166:    total: 1
 * interleaving strings 97:   total: 1
 * course schedule 207: 210, 269, 953, 785    total: 5
 * building with ocean view 1762:    total: 1
 * dot product of sparse vectors 1570:     total:1
 * k th largest element in array 215: 347,  692, 1779, 973    total: 5
 * lowest common ancestor 235: 236, 1644, 1650        total: 4
 * account merge 721: 734    total: 2
 * Facebook high frequency alone: 636, 670, 791, 766, 498, 1011, 1891, 1344, 605,1868, 621, 767, 983,346,1424, 896,932, 681,939   total: 19
 * random pick index 398:  328, (528)      total: 2
 * WordAbbreviation 408:      total: 1
 * nested integer list sum: 339   total: 1
 * tic tac toe 348: 794   total: 2
 * bulb switcher 319:    total: 1
 * insert delete random 380:  total: 1
 * word ladder 126: 127     total: 2
 *********************Similar problem catalogs: total: 320
 */
// single-linked list where java LinkedList is a double linked list via Deque

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

  static String swapStr(char[] str, int i, int j) {
    char temp = str[i];
    str[i] = str[j];
    str[j] = temp;
    return str.toString();
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

  static void reverse(int[] nums, int start) {
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