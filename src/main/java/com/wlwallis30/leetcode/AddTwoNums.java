package com.wlwallis30.leetcode;

import java.util.*;

public class AddTwoNums {
	
	public ListNode addTwoSums_2(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(-1);
		int carryBit = 0;
		ListNode curNode = dummyHead;
		
		while(l1 != null || l2 != null) {
			int digit1 = l1 == null ? 0 : l1.val;
			int digit2 = l2 == null ? 0 : l2.val;
			
			int sum = digit1 + digit2 + carryBit;
			carryBit = sum >= 10 ? 1 : 0;
			
			curNode.next = new ListNode(sum % 10);
			curNode = curNode.next;
			
			if(l1 != null) l1 = l1.next;
			if(l2 != null) l2 = l2.next;
			
		}
		
		if(carryBit == 1) curNode.next = new ListNode(1);
		
		return dummyHead.next;
	}
}
