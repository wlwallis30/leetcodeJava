package com.wlwallis30.leetcode;

import java.util.*;
import javafx.util.Pair;

class AtlassianHiFreq {
  public String rankTeams1366(String[] votes) {
    int totalVotes = votes.length;
    int totalTeams = votes[0].length(); // e.g. "ABC", totalTeams is 3 then
    Map<Character, int[]> map = new HashMap<>();
    List<Character> chars = new ArrayList<>();

    for(int i = 0 ; i < totalTeams; i++) {
      char team = votes[0].charAt(i);
      map.put(team, new int[totalTeams]);
      chars.add(team);
    }

    for (int i = 0 ; i < totalVotes ; i++) {
      String oneVote = votes[i];
      for(int j = 0 ; j < totalTeams; j++) {
        map.get(oneVote.charAt(j))[j]+=1;
      }
    }

    Comparator<Character> comp = (a,b) -> {
      int[] votesForA = map.get(a);
      int[] votesForB = map.get(b);
      for(int i = 0 ; i < votesForA.length; i++) {
        if(votesForA[i] < votesForB[i]) { return 1; }
        else if(votesForA[i] > votesForB[i]) { return -1; }
      }
      return a.compareTo(b);
    };
    // Arrays.sort can only be used for Arrays.

    chars.sort(comp);
    StringBuilder sb = new StringBuilder();
    for(char c : chars) { sb.append(c); }
    return sb.toString();
  }

  public int intersectionSizeTwo757(int[][] intervals) {
    int n = intervals.length;
    Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]); // Sort intervals: 1- end 2- start- O(nlogn)
    List<Integer> res = new ArrayList<>();
    res.add(intervals[0][1] - 1); // Add one before end
    res.add(intervals[0][1]); // Add end
    /*当前区间就和我们维护的集合S有三种情况:
    1. 二者完全没有交集， 取大的数字有更高的概率能和后面的区间有交集
    2. 二者有一个数字的交集，那么这个交集数字一定是区间的起始位置(否则，集合S和当前区间会有两个), 取区间的结束位置
    3. 二者有两个及两个以上数字的交集，那么不用做任何处理
     */
    for (int i = 1; i < n; i++) { // O(n)
      int start = intervals[i][0], end = intervals[i][1], size = res.size(), last = res.get(size - 1), secondLast = res.get(size - 2);
      if (start > last) { // We need to add two fresh points
        res.add(end - 1);
        res.add(end);
      } else if (start == last) res.add(end); // We already added one. We need to add the end of this interval
      else if (start > secondLast) res.add(end); // We already added last. We need one more
      // skip for start <= 2ndLast since 2ndLast and last already added in set S.
    }
    return res.size();
  }

  public int arrangeCoins441(int n) {
    if (n <= 1) return n;
    long low = 1, high = n;
    while (low < high) {
      long mid = low + (high - low) / 2;
      if (mid * (mid + 1) / 2 <= n) low = mid + 1; // without = in if condition, n=3, it will fail, so adding =, it will push the boundary 1+
      else high = mid;
    }
    return (int)low - 1;
  }

  public int[] findingUsersActiveMinutes1817(int[][] logs, int k) {
    int[] result=new int[k];
    HashMap<Integer,HashSet<Integer>> userToMin=new HashMap<>();

    for(int[] log:logs){
      int id=log[0];
      int t=log[1];
      if(!userToMin.containsKey(id)) userToMin.put(id,new HashSet<>());
      userToMin.get(id).add(t);
    }

    for(int id:userToMin.keySet()){
      int UAM=userToMin.get(id).size();
      result[UAM-1]++;
    }
    return result;
  }

  // dp[targetLen][wordLen] to build the prefix of target of length targetLen using only the wordLen leftmost columns.
    /*
            a  c  c  a
            b  b  b  b
            c  a  c  a
            dp[i][j]:
            j--->
         i  1 1 1 1 1
            0 1 2 2 4          a
            0 0 1 3 5          b
            0 0 0 0 6          a
            "aba" -> index 0 ("a cca"), index 1 ("b b bb"), index 3 ("cac a")
            "aba" -> index 0 ("a cca"), index 2 ("bb b b"), index 3 ("cac a")
            "aba" -> index 0 ("a cca"), index 1 ("b b bb"), index 3 ("acc a")
            "aba" -> index 0 ("a cca"), index 2 ("bb b b"), index 3 ("acc a")
            "aba" -> index 1 ("c a ca"), index 2 ("bb b b"), index 3 ("acc a")
            "aba" -> index 1 ("c a ca"), index 2 ("bb b b"), index 3 ("cac a")
            https://www.youtube.com/watch?v=KDOyzVIAa9w
            dp[i][j]: how many ways to form target[0:i-1] using word[0:j-1]
            1. we do not use word[j-1] to form target[i-1]:  dp[i][j] = d[i][j-1]
            2. we use:
            if(can do that): dp[i][j] += dp[i-1][j-1] * count(how many word[j-1] == target[i-1]);
     */
  public int numWays1639(String[] words, String target) {
    int alphabet = 26;
    int mod = 1000000007;
    int targetLen = target.length(), wordLen = words[0].length();
    int cnt[][] = new int[alphabet][wordLen];
    for (int j = 0; j < wordLen; j++) {
      for (String word : words) { cnt[word.charAt(j) - 'a'][j]++; }
    }

    long dp[][] = new long[targetLen + 1][wordLen + 1];
    for (int j = 0; j <= wordLen; j++) dp[0][j] = 1; // composing empty target by using len of j's word, it should be 1

    for (int i = 1; i <= targetLen; i++) {
      for (int j = 1; j <= wordLen; j++) {
        dp[i][j] = dp[i][j-1];

        dp[i][j] += cnt[target.charAt(i-1) - 'a'][j-1] * dp[i-1][j-1];
        dp[i][j] %= mod;
      }
    }
    return (int)dp[targetLen][wordLen];
  }

  // https://www.youtube.com/watch?v=vK-fDfX9gNs
  //https://github.com/happygirlzt/algorithm-illustrations/blob/master/730.%20Count%20Different%20Palindromic%20Subsequences.png
  public int countPalindromicSubsequences730(String s) {
    int n = s.length();
    char[] chars = s.toCharArray();
    long[][] dp = new long[n][n];
    long mod = 1_000_000_007;

    for (int i = n - 1; i >= 0; i--) { // check the above png site to find out why i starts from n-1: we need dp[i + 1][?] first b4 we calc dp[i][?]
      dp[i][i] = 1;
      for (int j = i + 1; j < n; j++) {
        if (chars[i] != chars[j]) {
          dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
        } else {
          dp[i][j] = dp[i + 1][j - 1] * 2;  // original dp[i + 1][j - 1] sequences now doubled after adding  d [original middle box from i+1 to j-1] d

          int left = i + 1, right = j - 1;
          while (left <= right && chars[left] != chars[i]) { left++; }

          while (left <= right && chars[right] != chars[i]) { right--; }

          if (left > right) { dp[i][j] += 2; // no same char in the box of [i+1][j-1]
          } else if (left == right) { dp[i][j] += 1;  // one same char in the box of [i+1][j-1]
          } else { dp[i][j] -= dp[left + 1][right - 1];  // two or more same char in the box of [i+1][j-1]
          }
        }
        dp[i][j] = (dp[i][j] + mod) % mod;
      }
    }
    return (int) dp[0][n - 1];
  }

  // https://www.youtube.com/watch?v=vI8-063NjRY
  /*
  s = "#" + s, 加个dummny字符叙述方便：
  长度为5的回文串，意味着我们对中间的字符没有任何要求。剩下的镜像部分，本质只是两个字符的组合。考虑到本题的元素只是数字，只有0-9共10种可能，
  所以组合的方式只有100种。结合字符串的长度是1e4，基本可以判定时间复杂度就是10^6，状态变量定义为dp1[i][j][k]表示前i个元素的子串里，
  以j和k结尾的subsequence有多少。同理定义为dp2[i][j][k]表示后i个元素逆序来看的子串里，以j和k结尾的subsequence有多少。
  这样我们枚举长度为5的回文串的中间字符位置i，则有ret+=dp[i-1][j][k]*dp[i+1][j][k].

  接下来考虑dp1[i][j][k]如何求解。依然从第i个元素下手。如果第i个元素没有贡献任何“以j,k结尾的新子串”，则有dp1[i][j][k] += dp[i-1][j][k]。
  如果第i个元素恰好是k，那么s[i]本身就可能贡献一个“以j,k结尾的新子串”，这个子串的数目取决于i之前出现了多少个j, 需要计算count[j] for k

  To create a 5 digit palindrome we do not need to care about the middle element. We just need to find subsequence of pattern XY_YX.
   Calculate number of subsequences of type XY and subsequences of type YX around any given point i and multiply them to find number
    of subsequences of type XY_YX. Since string only has digits, the time complexity will be 100*n.

    We will be maintaing the counts of digit in the list cnts
    Keep 2 arrays pre and suf to store the number of prefixes of type XY and suffixes of type YX. pre[i-1][1][2] means prefixes of type 12 before index i.
    Similarly suf[i+1][1][2] means suffixes of type 21 after index i.
    Remember given string is made of digits that is 0123456789. That's a total of 10 unique characters
    Once we have calculated the prefix and suffix lists we just need to multiply pre[i - 1][j][k] with suf[i + 1][j][k] to find number of palindromic subsequences

   */
  public int countPalindromes2484(String s) {
    int mod = 1000_000_007, n = s.length(), res = 0, cnts[] = new int[10],
        pre[][][] = new int[n][10][10], suf[][][] = new int[n][10][10];
    for (int i = 0; i < n; i++) {
      int c = s.charAt(i) - '0';
      if (i > 0)
        for (int j = 0; j < 10; j++)
          for (int k = 0; k < 10; k++) {
            //Let's say j = 5, k = 4 and curChar = 4 which means we are looking at prefix 54. pre[i][j][k] = pre[i - 1][j][k] will carry forward previous count of prefixes
            // (we just use prev 54 for prefix 54)
            // Since k == c means the current character(s[i]) matches with last digit of prefix.
            // To find total number of possibilites of prefixes of type 54 we need to know how many 5 exist BEFORE current index i.
            // This information is stored in cnts[5]. So we add cnts[5] to pre[i][5][4]
            pre[i][j][k] = pre[i - 1][j][k];
            if (k == c) pre[i][j][k] += cnts[j]; // cnts b4 index i, that is why we put cnts[c]++ later.
            pre[i][j][k] %= mod;
          }
      cnts[c]++; // cnts for c at index i
    }
    Arrays.fill(cnts, 0);
    // going from back
    for (int i = n - 1; i >= 0; i--) {
      int c = s.charAt(i) - '0';
      if (i < n - 1)
        for (int j = 0; j < 10; j++)
          for (int k = 0; k < 10; k++) {
            suf[i][j][k] = suf[i + 1][j][k];
            if (k == c) suf[i][j][k] += cnts[j];
            suf[i][j][k] %= mod;
          }
      cnts[c]++;
    }
    // this condition + pre[i-1] and suf[i+1] will eliminate s with len < 5
    for (int i = 2; i < n - 2; i++)
      for (int j = 0; j < 10; j++)
        for (int k = 0; k < 10; k++)
          res = (int)((res + (long) pre[i - 1][j][k] * suf[i + 1][j][k]) % mod);
    return res;
  }
}

// 353
class SnowGame {
  Set<Pair<Integer, Integer>> snakeSet; // you can also use <int[], Boolean>
  Deque<Pair<Integer, Integer>> snakeBody; // you can also use <int[]>
  int[][] food;
  int foodIndex;
  int width;
  int height;

  public SnowGame(int width, int height, int[][] food) {
    this.width = width;
    this.height = height;
    this.food = food;
    this.snakeSet = new HashSet<>();
    this.snakeSet.add(new Pair<>(0,0)); // intially at [0][0]
    this.snakeBody = new LinkedList<>();
    this.snakeBody.offerFirst(new Pair<>(0,0));
  }

  public int move(String direction) {
    Pair<Integer, Integer> oldHead = this.snakeBody.peekLast();
    // now still the prev head row and col
    int newHeadRow = oldHead.getKey();
    int newHeadColumn = oldHead.getValue();

    // now new head row and col
    switch (direction) {
      case "U": newHeadRow--; break;
      case "D": newHeadRow++; break;
      case "L": newHeadColumn--; break;
      case "R": newHeadColumn++; break;
    }

    Pair<Integer, Integer> newHead = new Pair<>(newHeadRow, newHeadColumn);
//    int[] newHead1 = new int[]{newHeadRow, newHeadColumn};
    Pair<Integer, Integer> currentTail = this.snakeBody.peekFirst();
    // Boundary conditions.
    boolean crossesBoundary1 = newHeadRow < 0 || newHeadRow >= this.height;
    boolean crossesBoundary2 = newHeadColumn < 0 || newHeadColumn >= this.width;
    // Checking if the snakeBody bites itself. newHead.getKey() getVal will just give row and col
    boolean bitesItself = this.snakeSet.contains(newHead) && !(newHead.getKey() == currentTail.getKey() && newHead.getValue() == currentTail.getValue());
    // If any of the terminal conditions are satisfied, then we exit with rcode -1.
    if (crossesBoundary1 || crossesBoundary2 || bitesItself) { return -1; }

    // If there's an available food item and it is on the cell occupied by the snakeBody after the move,
    // eat it. and snakeBody can grow, no need to remove tail
    if ((this.foodIndex < this.food.length) && (this.food[this.foodIndex][0] == newHeadRow) && (this.food[this.foodIndex][1] == newHeadColumn)) {
      this.foodIndex++;
    } else { //Move with No Food
      this.snakeBody.pollFirst(); // polling tail of snake, which is the head of Q
      this.snakeSet.remove(currentTail);
    }
    this.snakeBody.addLast(newHead); // adding newHead to last of Q

    // add the head to the set
    this.snakeSet.add(newHead);
    return this.snakeBody.size() - 1;
  }
}

// 359
class Logger {
  private HashMap<String, Integer> msgDict;
  public Logger() { msgDict = new HashMap<>(); }

  public boolean shouldPrintMessage(int timestamp, String message) {
    if (!this.msgDict.containsKey(message)) { this.msgDict.put(message, timestamp); return true; }

    Integer oldTimestamp = this.msgDict.get(message);
    if (timestamp - oldTimestamp >= 10) { this.msgDict.put(message, timestamp); return true; }
    else return false;
  }
}

// 359
class LoggerWithClean {
  private LinkedList<String> msgQ;
  private HashMap<String, Integer> msgMap;

  public LoggerWithClean() {
    msgQ = new LinkedList<>();
    msgMap = new HashMap<>();
  }

  public boolean shouldPrintMessage(int timestamp, String message) {
    // clean up all expried msg
    while (msgQ.size() > 0) {
      String headMsg = msgQ.getFirst();
      if (msgMap.containsKey(headMsg) && timestamp - msgMap.get(headMsg)>= 10) {
        msgQ.removeFirst();
        msgMap.remove(headMsg);
      } else
        break;
    }

    if (!msgMap.containsKey(message)) { // the msg not appear b4 or old same msg got removed
      msgQ.addLast(message);
      msgMap.put(message, timestamp);
      return true;
    } else // there is a same msg in Set whose timestamp is within 10 sec window
      return false;
  }
}

// 911
class TopVotedCandidate {
  List<Vote> winnerAtTime;
  public TopVotedCandidate(int[] persons, int[] times) {
    winnerAtTime = new ArrayList<>();
    Map<Integer, Integer> count = new HashMap<>();
    int curLeader = -1;  // current curLeader
    int curMaxCnts = 0;  // current number of votes for curLeader

    for (int i = 0; i < persons.length; ++i) {
      int p = persons[i], t = times[i];
      int c = count.getOrDefault(p, 0) + 1;
      count.put(p, c);

      if (c >= curMaxCnts) {
        if (p != curLeader) {  // lead change
          curLeader = p;
          winnerAtTime.add(new Vote(curLeader, t));
        }
        curMaxCnts = c;
      }
    }
  }

  // query method
  public int q(int t) {
    int lo = 1, hi = winnerAtTime.size();
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      if (winnerAtTime.get(mid).time <= t) lo = mid + 1; // plz take t=25 to figure out adding = in this condition
      else hi = mid;
    }

    return winnerAtTime.get(lo - 1).person; // take t=3 to figure out to use lo-1
  }
}

class Vote {
  int person, time;
  Vote(int p, int t) { person = p; time = t; }
}

// 1166
class FileSystem1166 {
  HashMap<String, Integer> paths;
  //Path of length M will have M-1 ancestors. Let's say space occupied by first ancestor is k. Second ancestor would occupy 2k and so on...M-1th ancestor would occupy k(M-1)
  //So total space is k+2k+3k+...+k(M-1) = k(M-1)*M/2 for a hashmap
  public FileSystem1166() { this.paths = new HashMap<>(); }

  public boolean createPath(String path, int value) {
    // Step-1: basic path validations
    if (path.isEmpty() || (path.length() == 1 && path.equals("/")) || this.paths.containsKey(path)) {
      return false;
    }

    int delimIndex = path.lastIndexOf("/");
    String parent = path.substring(0, delimIndex);

    // Step-2: if the parent doesn't exist. Note that "/" is a valid parent.
    if (parent.length() > 1 && !this.paths.containsKey(parent)) { return false; }

    // Step-3: add this new path and return true.
    this.paths.put(path, value);
    return true;
  }

  public int get(String path) { return this.paths.getOrDefault(path, -1); }
}