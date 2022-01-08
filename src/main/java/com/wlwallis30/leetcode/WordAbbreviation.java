package com.wlwallis30.leetcode;

public class WordAbbreviation {
  public boolean validWordAbbreviation408(String word, String abbr) {
    char[] w = word.toCharArray(), abb = abbr.toCharArray();
    int wPos = 0, counter = 0;
    for(int i=0; i<abb.length; i++){
      if(Character.isLetter(abb[i])){
        wPos+=counter;
        if(wPos>=w.length || abb[i]!=w[wPos++]){return false;}
        //very important, reset counter to 0 when abbr hit a letter
        counter = 0;
      }
      else{
        if(counter==0 && abb[i]=='0'){return false;}
        counter = counter*10+abb[i]-'0';
      }
    }
    // we finished abbr, but might not finish the word if so, return false. counter might zero or positive, both + wPos need to be == w.length
    return wPos+counter == w.length;
  }
}
