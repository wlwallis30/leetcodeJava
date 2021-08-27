  package com.wlwallis30.leetcode;

import java.util.*;

public class Palindrom {
  public String palindromLongSubstr_5(String str) {
    if(str.isEmpty()) return "".toString();
    String res = "";
    int leftPalin = 0, rightPalin = 0;
    int lenPalin = 0;
    int size = str.length();
    int[][] dp = new int[size][size];
    for(int end = 0; end < size; ++end) {
      dp[end][end] = 1;
      for(int start = 0; start < end; ++start) {
        if(str.charAt(start) == str.charAt(end)
            && (dp[start+1][end-1] == 1 || start == end-1))
        { dp[start][end] = 1; }
        else dp[start][end] = 0;

        if(dp[start][end] ==1 && lenPalin < end-start+1) {
          lenPalin = end-start+1;
          leftPalin = start;
          rightPalin = end;
        }
      }
    }

    lenPalin =  Math.max(lenPalin, rightPalin-leftPalin+1);
    // java's sub string: extend to char whose index = endIndex-1
    res = str.substring(leftPalin+0, rightPalin+1);
    return res;
  }

  public boolean palindromePermutation_266(String str) {
    Map<Character, Integer> charCnt = new HashMap<>();
    for (char chartmp: str.toCharArray()) {
      if(charCnt.containsKey(chartmp)) {
        Integer tmpVal = charCnt.get(chartmp);
        charCnt.put(chartmp, ++tmpVal);
      }
      else {
        charCnt.put(chartmp, 1);
      }
      // the above better be replaced by : map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
    }
    int oddCnt = 0;
    for (char chartmp: charCnt.keySet()) {
      //      System.out.println("one key:" + chartmp);
      if(charCnt.get(chartmp) % 2 == 1) oddCnt++;
    }
    //    System.out.println("odd cnt:" + oddCnt);
    return (oddCnt==0 || oddCnt==1);
   /*  single pass and using an int array map
   * int[] map = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }
        return count <= 1;
   */
  }
  Set<String> set = new HashSet<>();
  public List<String> generatePalindromes_267(String str) {
    int[] charCnt = new int[128];
    char[] halfStr = new char[str.length() / 2];
    for (char tmpCha: str.toCharArray()) { ++charCnt[tmpCha]; }

    // a null char, you can NOT use '' as other languages can, https://stackoverflow.com/questions/18410234/how-does-one-represent-the-empty-char/18410258
    char midChar = 0;
    String midStr = "";
    String halfStrBuild = "";
    int k = 0;
    for (int i = 0; i < charCnt.length; i++) {
      if (charCnt[i] % 2 == 1) {
        midStr += (char)i;// to judge if it is a panlindrome
        midChar = (char) i;
      }
      if(midStr.length()>1) return new ArrayList<>();
      char[] tmp= new char[charCnt[i]/2];
      Arrays.fill(tmp, (char)i);
      // java supper sucks,  halfStrBuild += tmp does not work at all!
      halfStrBuild += new StringBuilder().append(tmp);
    }
    System.out.println(halfStrBuild);
    halfStr = halfStrBuild.toCharArray();
    System.out.println(halfStr);
    permute(halfStr, 0, midChar);
    return new ArrayList <String> (set);
  }


  void swap(char[] str, int i, int j) {
    char temp = str[i];
    str[i] = str[j];
    str[j] = temp;
  }

  void permute(char[] str, int startIdx, char midChar) {
    if (startIdx == str.length) {
      set.add(new String(str) + (midChar == 0 ? "" : midChar) + new StringBuffer(new String(str)).reverse());
    } else {
      for (int i = startIdx; i < str.length; i++) {
        if (startIdx != i && str[startIdx] == str[i]) continue;
        swap(str, startIdx, i);
        permute(str, startIdx + 1, midChar);
        swap(str, startIdx, i);

      }
    }
  }
}
