package com.wlwallis30.leetcode;

public class ZigZagString {
  String convert6Justfill(String s, int numRows) {
    if (numRows <= 1) return s;
    String res = "";
    int i = 0, n = s.length();
    StringBuilder[] sbArray = new StringBuilder[numRows];
    //for(StringBuilder sb: sbArray) sb = new StringBuilder(""); this will still make sbArray[row] null since sb is another new object!
    for (int j = 0; j < numRows; j++) {
      sbArray[j] = new StringBuilder(); // ("") or () does not matter
    }
    while (i < n) {
      for (int row = 0; row < numRows && i < n; ++row) {
        sbArray[row].append(s.charAt(i++));
      }
      for (int row = numRows - 2; row >= 1 && i < n; --row) {
        sbArray[row].append(s.charAt(i++));
      }
    }
    for (StringBuilder sb: sbArray) res += sb.toString();
    return res;
  }

    /* to easily see the pattern
  当 n = 2 时：
  0 2 4 6 8 A C E
  1 3 5 7 9 B D F
  当 n = 3 时：
  0   4    8     C
  1 3 5 7 9 B D F
  2    6   A     E
  当 n = 4 时：
  0     6        C
  1   5 7   B  D
  2 4   8 A    E
  3    9       F
     */
  //首位两行中相邻两个元素的 index 之差跟行数是相关的，为  2*nRows - 2, 根据这个特点，可以按顺序找到所有的黑色元素在元字符串的位置
  // e.g. 0, 6 and 1, 7
  //n = 4 中的那个红色5，它的位置为 1 + 2 x 4-2 - 2 x 1 = 5, 2*nRows-2 =6
    // 5-1 is 4, B-7 is 4,  1 + 6 - x*row = 5, currently row is 1, so x = 2. (1+6 will actually give 7, you need to minus something to get 5 and that is related to some linear of row)
  public String convert6ByPattern(String s, int numRows) {
    if (numRows == 1) return s;
    StringBuilder ret = new StringBuilder();
    int n = s.length();
    int cycleLen = 2 * numRows - 2;

    for (int row = 0; row < numRows; row++) {
      for (int baseIdx = row; baseIdx < n; baseIdx += cycleLen) {
        ret.append(s.charAt(baseIdx));
        int pos = baseIdx + cycleLen - 2 * row;
        if (row != 0 && row != numRows - 1 && pos < n) ret.append(s.charAt(pos));
      }
    }
    return ret.toString();
  }
}
