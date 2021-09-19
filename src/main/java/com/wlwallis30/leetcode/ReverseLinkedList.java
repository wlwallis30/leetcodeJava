package com.wlwallis30.leetcode;

import java.util.List;

public class ReverseLinkedList {
  ListNode reverseList206(ListNode head) {
    if(head == null || head.next == null) return head;
    ListNode pre=null;
    ListNode cur=head;
    while(cur != null)
    {
      ListNode next=cur.next;
      cur.next=pre;
      pre=cur;
      cur=next;
    }

    return pre;
  }
  ListNode reverseBetween92(ListNode head, int m, int n) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode cur = dummy;
    ListNode prePartLast, firstToBeLast, pre=null, next=null;
    for (int i = 1; i <= m - 1; ++i) cur = cur.next;
    prePartLast = cur;
    cur=cur.next;
    firstToBeLast = cur;
    for (int i = m; i <= n; ++i) {
      next = cur.next;
      cur.next=pre;
      pre = cur;
      cur=next;
    }

    firstToBeLast.next = next;
    prePartLast.next=pre;

    return dummy.next;
  }

  //get both lengths, move the diff steps along longer one, compare one by one node
  public ListNode getIntersectionNode160(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) return null;
    int lenA = getLength(headA), lenB = getLength(headB);
    if (lenA < lenB) {
      ListNode swapNode = headA;
      headA = headB;
      headB = swapNode;
    }
    // keep A as the longer list always.
    for (int i = 0; i < Math.abs(lenA-lenB); ++i) headA = headA.next;

    while (headA != null && headB != null && headA != headB) {
      headA = headA.next;
      headB = headB.next;
    }
    return (headA != null && headB != null) ? headA : null;
  }
  int getLength(ListNode head) {
    int cnt = 0;
    while (head != null) {
      ++cnt;
      head = head.next;
    }
    return cnt;
  }

  //both moving, shorter head move faster to end, jump to another head.
  //e.g. shorter has 4, longer has 5, intersect at the second from end. so in the end, both move 7 steps
  public ListNode getIntersectionNode160JumpAnother(ListNode headA, ListNode headB) {
    if (headA == null || headB == null)
      return null;
    int steps = 0;
    ListNode a = headA, b = headB;
    while (a != b) {
      //a.next != null, this is NOT OK, if two lists  not intersected, a and b will never reach null to equal. infinite loop
      a = (a != null) ? a.next : headB;
      b = (b != null) ? b.next : headA;
      ++steps;
      System.out.println(steps);
    }

    return a;
  }

  //Every right node in the tree has a sibling (a left node that shares the same parent).
  //Every right node in the tree has no children.
  TreeNode upsideDownBinaryTree156(TreeNode root) {
    if(root == null || root.left == null) return root;

    TreeNode res = upsideDownBinaryTree156(root.left);
    root.left.left= root.right;
    root.left.right=root;

    root.left=null;
    root.right=null;
    return res;
  }

  TreeNode upsideDownBinaryTree156Iteration(TreeNode root) {
    TreeNode cur=root, nowLeft=null, nowRight=null, next;
    while(cur != null) {
      //save this left for next time.
      next=cur.left;

      // rotate for left and save
      cur.left=nowLeft;
      nowLeft=cur.right;

      //rotate for right and save
      cur.right=nowRight;
      nowRight=cur;

      cur=next;
    }

    return nowRight;
  }
}
