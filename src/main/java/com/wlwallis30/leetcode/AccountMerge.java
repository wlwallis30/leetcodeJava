package com.wlwallis30.leetcode;

import java.util.*;

// best solution is union find.
public class AccountMerge {
  public List<List<String>> accountsMerge721_hashmap(List<List<String>> accounts) {
    Map<Integer, Set<String>> emailToAccounts = new HashMap<>(); // accountID: email sets
    Map<String, Integer> emailGroup = new HashMap<>(); // email: accountID

    for (int i = 0; i < accounts.size(); i++) {
      int accountId = i;
      List<String> accountDetails = accounts.get(accountId);
      emailToAccounts.computeIfAbsent(accountId, k -> new HashSet<>());

      for (int j = 1; j < accountDetails.size(); j++) {
        String email = accountDetails.get(j);

        //when previous accountID exists, adding all previous emails to current account's email sets
        if (emailGroup.containsKey(email) && emailGroup.get(email) != accountId) {
          int prevAccountId = emailGroup.get(email);

          // merging them in the emailAccount
          emailToAccounts.get(accountId).addAll(emailToAccounts.get(prevAccountId));

          // link/replace previous account emails to currnent accout --> merging them in emailGroup
          for (String e : emailToAccounts.get(prevAccountId)) { emailGroup.put(e, accountId); }
        }

        emailGroup.put(email, accountId);
        emailToAccounts.get(accountId).add(email);
      }
    }

    //b4 this, emailAccount still have previous account's email, clear them and refill, so we can use it for answer.
    // or we dont need to clear, just find the accountID from emailGroup and find all its emails since emailGroup already removed previous account
    emailToAccounts.clear();
    for (Map.Entry<String, Integer> entry : emailGroup.entrySet()) {
      int accountId = entry.getValue();
      String email = entry.getKey();
      emailToAccounts.computeIfAbsent(accountId,
          k -> new TreeSet<>( (a, b) -> a.compareTo(b)));

      emailToAccounts.get(accountId).add(email);
    }

    List<List<String>> ans = new ArrayList<>();
    for (Map.Entry<Integer, Set<String>> entry : emailToAccounts.entrySet()) {
      int accountId = entry.getKey();
      Set<String> emails = entry.getValue();
      String name = accounts.get(accountId).get(0);

      List<String> list = new ArrayList<>();
      list.add(name);
      list.addAll(emails);

      ans.add(list);
    }
    return ans;
  }
  //also refer to 323
  public List<List<String>> accountsMerge721_unionFind(List<List<String>> accounts) {
    Map<String, String> owners = new HashMap<>(); //email: name
    Map<String, String> parents = new HashMap<>(); // email node: parent email node
    // use treemap here simply because the question requires the emails are returned in sorted order. root email: set of emails
    Map<String, TreeSet<String>> unions = new HashMap<>();

    //init each email's parent to be itself
    for (List<String> account: accounts) {
      for (int i = 1; i<account.size(); i++) {
        String owner = account.get(0);
        String email = account.get(i);
        parents.put(email, email);
        owners.put(email, owner);
      }
    }

    //union each account's parent to be the first one in the list
    // consider, [name1, a, b], [name1, c, d], [name1, a, c] or [name1, a, b], [name1, d, c], [name1, a, c]
    // the union step here and the following step of combining TOGETHER will finally union same account into one root
    for (List<String> account: accounts) {
      String p1 = find(parents, account.get(1));
      for (int i = 2; i<account.size(); i++) {
        String p2 = find(parents, account.get(i));
        //union
        parents.put(p2, p1);
      }
    }

    //now combine the union sets
    for (List<String> account: accounts) {
      String p1 = find(parents, account.get(1));
      if (!unions.containsKey(p1)) {
        unions.put(p1, new TreeSet<>());
      }
      Set<String> emailSets = unions.get(p1);
      for (int i = 1; i<account.size(); i++) {
        emailSets.add(account.get(i));
      }
    }

    List<List<String>> res = new ArrayList<>();
    for (String rootEmail : unions.keySet()) {
      List<String> emails = new ArrayList(unions.get(rootEmail));
      emails.add(0, owners.get(rootEmail));
      res.add(emails);
    }
    return res;
  }

  //find
  public String find(Map<String, String> parents, String node) {
    while (!parents.get(node).equals(node)) {
      String curParent = parents.get(node);
      String granpa = parents.get(curParent);
      parents.put(node, granpa); //path compression
      node = granpa;
    }
    return node;
  }

  public boolean areSentencesSimilar734(String[] words1, String[] words2, List<List<String>> pairs) {
    if (words1.length != words2.length) return false;
    if (words1.length == 0) return true;

    boolean res = false;
    for (int i = 0; i < words1.length; i++) {
      String word1 = words1[i];
      String word2 = words2[i];
      if (word1.equals(word2)) res = true;
      for (int j = 0; j < pairs.size(); j++) {
        if (pairs.get(j).contains(word1) && pairs.get(j).contains(word2)) {
          res = true;
        }
      }
      if(!res) return false;
    }

    return res;
  }

  // better solution in time O(N)
  public boolean areSentencesSimilar734_hashmap(String[] sen1, String[] sen2, List<List<String>> similarPairs) {
    if (sen1.length != sen2.length) return false;

    Map<String, Set<String>>map = new HashMap<>();
    for (List<String>pair : similarPairs) {
      map.computeIfAbsent(pair.get(0),k -> new HashSet<>()).add(pair.get(1));
      map.computeIfAbsent(pair.get(1),k -> new HashSet<>()).add(pair.get(0));
    }

    for (int i = 0; i < sen1.length; i++) {
      if (sen1[i].equals(sen2[i])) continue;
      Set<String> similarWords = map.get(sen1[i]);
      if (similarWords == null || !similarWords.contains(sen2[i])) return false;
    }
    return true;
  }
}
