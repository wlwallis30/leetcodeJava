package com.wlwallis30.leetcode;

public class ConvertSortedArryToBST {
  public TreeNode preorderDFS(int left, int right, int[] nums) {
    if (left > right) return null;

    // always choose left middle node as a root, for right middle: if ((left + right) % 2 == 1) ++p
    int p = (left + right) / 2;

    // preorder traversal: node -> left -> right
    TreeNode root = new TreeNode(nums[p]);
    root.left = preorderDFS(left, p - 1, nums);
    root.right = preorderDFS(p + 1, right, nums);
    return root;
  }

  public TreeNode sortedArrayToBST_108Recur(int[] nums) {
    return preorderDFS(0, nums.length - 1, nums);
  }


  // this is just one possible tree generated.
  TreeNode sortedListToBST109Recur(ListNode head) {
    if (head == null) return null;
    if (head.next == null) return new TreeNode(head.val);
    ListNode slow = head;
    ListNode fast = head;
    ListNode last = slow;
    while (fast.next != null && fast.next.next != null) {
      last = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    fast = slow.next;
    last.next = null;
    //slow is middle one now.
    TreeNode cur = new TreeNode(slow.val);
    // play with 1->2 subtree, without this condition, 1.left will be another 1!!
    if (head != slow) { cur.left = sortedListToBST109Recur(head); }
    cur.right = sortedListToBST109Recur(fast);
    return cur;
  }
}
