package com.wlwallis30.leetcode;

public class ExcelSheetColumn {

  //we only care about the offset WRT 'A', java/c++ will auto convert char to i, vice versa
  String convertToTitle168(int columnNumber) {
    StringBuilder sb = new StringBuilder();
    while(columnNumber>0){
      columnNumber--;
      //sb.append((char)('A'+temp)); append will add from back
      sb.insert(0,(char)('A'+ columnNumber%26));
      columnNumber/=26;
    }

    return sb.toString();
  }

  int titleToNumber171(String columnTitle) {
    int res =0;
    for(int i=0;i<columnTitle.length();++i)
      res=res*26 + columnTitle.charAt(i) - 'A' + 1;
    return res;
  }
}
