package com.wlwallis30.leetcode;

import java.util.*;

public class SimplifyStrPath {
  // 71
  public String simplifyPath(String path) {
    Stack<String> stack = new Stack<String>();
    // Split the input string on "/" as the delimiter
    String[] components = path.split("/");

    // when hitting "//", it will be splitted to an empty string!
    for (String directory : components) {
      if (directory.equals(".") || directory.isEmpty()) {}// or using continue;
      else if (directory.equals(".."))  {if (!stack.isEmpty()) stack.pop();} // have to keep {}, since it will be another if!!
      else stack.add(directory); // Finally, a legitimate directory name, so we add it to our stack
    }

    // Stich together all the directory names together
    StringBuilder result = new StringBuilder();
    // 15 ways to iterate in stack: https://www.techiedelight.com/iterate-through-stack-java/
    for (String dir : stack) {//iterator is in the order which reverse the order of stack
      result.append("/");
      result.append(dir);
    }

    return result.length() > 0 ? result.toString() : "/" ;
  }
}
