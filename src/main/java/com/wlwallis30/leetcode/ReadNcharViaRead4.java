package com.wlwallis30.leetcode;

public class ReadNcharViaRead4 {
  int read4(char[] buf4) {return 1;} // fake method to be compilable
  /**
   * @param buf Destination buffer
   * @param n   Number of characters to read
   * @return    The number of actual characters read
   */
  public int read157(char[] buf, int n) {
    char[] buf4 = new char[4];
    int copiedCharCnt = 0;
    int readCharCnt = 4;
    // init readCharCnt =4 will validate the case of only reading 3 (<4) letters.
    while(copiedCharCnt < n && readCharCnt == 4) {
      readCharCnt = read4(buf4);

      for(int i=0; i<readCharCnt;++i, ++copiedCharCnt){
        if(copiedCharCnt == n) return copiedCharCnt;
        buf[copiedCharCnt] = buf4[i];
      }
    }

    return copiedCharCnt;
  }
}
